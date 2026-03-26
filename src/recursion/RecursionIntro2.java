package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion — Core Patterns (Upgraded)
 *
 * <p>Covers every pattern tested in coding interviews:
 * <ol>
 *   <li>Linear recursion   — printAscending, printDescending, factorial, sumDigits</li>
 *   <li>Binary recursion   — fib (naive)</li>
 *   <li>Memoized recursion — fibMemo  (top-down DP)</li>
 *   <li>Fast power         — fastPow  (O(log n) exponentiation)</li>
 *   <li>Two-pointer style  — isPalindrome</li>
 *   <li>Divide & Conquer   — binarySearch</li>
 *   <li>String recursion   — reverse</li>
 *   <li>Backtracking       — subsets, permutations, combinationSum</li>
 *   <li>Classic puzzle     — towerOfHanoi</li>
 * </ol>
 *
 * <p><b>The two laws every recursive function must obey:</b>
 * <ul>
 *   <li>Base case  — a condition that returns without recursing</li>
 *   <li>Progress   — every recursive call moves strictly toward the base case</li>
 * </ul>
 *
 * <p><b>Interview mindset (the inductive leap):</b>
 * Don't trace every nested call in your head. Trust that solve(n-1) already
 * returns the correct answer for the smaller problem — your only job is:
 * "given the correct sub-answer, how do I build the answer for n?"
 */
public class RecursionIntro2 {

    // ══════════════════════════════════════════════════════════════════
    //  1. PRINT  (head recursion vs tail recursion)
    //     Time: O(n)  |  Space: O(n) call stack
    // ══════════════════════════════════════════════════════════════════

    /**
     * Prints 1 → n (ascending).
     *
     * <p>HEAD recursion: recurse first, act on the way BACK UP.
     * The print statement executes during stack unwinding.
     *
     * <p>Call tree for n=3:
     * <pre>
     *   printAscending(3)
     *   └─ printAscending(2)
     *      └─ printAscending(1)
     *         └─ printAscending(0)  ← base case, returns
     *         prints 1              ← unwind starts
     *      prints 2
     *   prints 3
     * </pre>
     */
    static void printAscending(int n) {
        if (n == 0) return;          // base case
        printAscending(n - 1);       // recurse FIRST  (head)
        System.out.print(n + " ");   // act on the way BACK
    }

    /**
     * Prints n → 1 (descending).
     *
     * <p>TAIL recursion: act first, recurse last.
     * The print happens before going deeper — no deferred work on the stack.
     */
    static void printDescending(int n) {
        if (n == 0) return;
        System.out.print(n + " ");   // act FIRST  (tail)
        printDescending(n - 1);      // recurse LAST
    }

    // ══════════════════════════════════════════════════════════════════
    //  2. FACTORIAL   n! = n × (n−1)!
    //     Time: O(n)  |  Space: O(n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Returns n factorial as a {@code long}.
     * {@code long} is required — {@code int} silently overflows at 13!
     *
     * <p>Recurrence: fact(n) = n × fact(n−1),  fact(0) = 1
     */
    static long factorial(int n) {
        if (n <= 1) return 1;                    // base: 0! = 1! = 1
        return (long) n * factorial(n - 1);
    }

    // ══════════════════════════════════════════════════════════════════
    //  3. FIBONACCI  — naive vs memoized
    //     Naive:  Time O(2^n)  |  Space O(n)
    //     Memo:   Time O(n)    |  Space O(n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Naive Fibonacci — intentionally O(2^n) to study the call tree.
     *
     * <p>Call tree for fib(4):
     * <pre>
     *              fib(4)
     *            /        \
     *         fib(3)      fib(2)       ← fib(2) computed TWICE
     *        /    \       /    \
     *     fib(2) fib(1) fib(1) fib(0)
     *     /    \
     *  fib(1) fib(0)
     * </pre>
     * Total calls for fib(n) ≈ 2^n. fib(50) ≈ 10^15 calls — unusable.
     *
     * <p>Always mention this problem in interviews before the interviewer asks.
     */
    static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * Memoized Fibonacci — top-down DP.  Time O(n), Space O(n).
     *
     * <p>Caller must pass {@code dp} initialised to -1:
     * <pre>int[] dp = new int[n + 1]; Arrays.fill(dp, -1);</pre>
     *
     * <p>How it works: before computing fib(n), check the cache.
     * Each sub-problem is solved exactly once — the tree collapses
     * into a straight chain of n calls.
     *
     * <p>Interview upgrade path: naive → memo (top-down DP) → tabulation (bottom-up DP)
     */
    static int fibMemo(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];         // cache hit — skip recomputation
        dp[n] = fibMemo(n - 1, dp) + fibMemo(n - 2, dp);
        return dp[n];
    }

    // ══════════════════════════════════════════════════════════════════
    //  4. POWER  — naive O(n) vs fast-power O(log n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Naive power — O(exp) time.  Good to show first, then optimise.
     */
    static long power(long base, int exp) {
        if (exp == 0) return 1;
        return base * power(base, exp - 1);
    }

    /**
     * Fast power (binary exponentiation) — O(log n) time.
     *
     * <p>Key insight: base^exp = (base^(exp/2))^2
     * Halving the exponent at each step gives O(log n) depth.
     *
     * <pre>
     *   fastPow(2, 10)
     *   → half = fastPow(2, 5)
     *        → half = fastPow(2, 2)
     *             → half = fastPow(2, 1)
     *                  → half = fastPow(2, 0) = 1
     *                  odd: 2 × 1 × 1 = 2
     *             even: 2 × 2 = 4
     *        odd: 2 × 4 × 4 = 32
     *   even: 32 × 32 = 1024  ✓
     * </pre>
     */
    static long fastPow(long base, long exp) {
        if (exp == 0) return 1;
        long half = fastPow(base, exp / 2);
        if (exp % 2 == 0) return half * half;       // even exponent
        else              return base * half * half; // odd exponent
    }

    // ══════════════════════════════════════════════════════════════════
    //  5. SUM OF DIGITS   123 → 1+2+3 = 6
    //     Time: O(log₁₀ n)  |  Space: O(log₁₀ n)
    // ══════════════════════════════════════════════════════════════════

    /** Returns the sum of all decimal digits in n. */
    static int sumDigits(int n) {
        if (n < 10) return n;                        // single digit
        return (n % 10) + sumDigits(n / 10);         // last digit + rest
    }

    // ══════════════════════════════════════════════════════════════════
    //  6. REVERSE STRING   (return-type string recursion)
    //     Time: O(n)  |  Space: O(n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Reverses a string recursively.
     *
     * <p>Pattern: strip the first character, reverse the rest,
     * then append the first character at the end.
     *
     * <pre>
     *   reverse("hello")
     *   = reverse("ello") + 'h'
     *   = reverse("llo") + 'e' + 'h'
     *   = ...
     *   = "olleh"
     * </pre>
     *
     * <p>Note: {@code substring} creates new String objects at each level.
     * An iterative two-pointer or StringBuilder solution is preferred in production.
     * The value here is practising "return-type" recursion.
     */
    static String reverse(String s) {
        if (s.isEmpty()) return s;                           // base case
        return reverse(s.substring(1)) + s.charAt(0);       // tail + head
    }

    // ══════════════════════════════════════════════════════════════════
    //  7. PALINDROME CHECK
    //     Time: O(n)  |  Space: O(n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Returns true if s[lo..hi] is a palindrome.
     * Shrinks inward from both ends — two-pointer style, done recursively.
     */
    static boolean isPalindrome(String s, int lo, int hi) {
        if (lo >= hi)               return true;   // 0 or 1 chars left
        if (s.charAt(lo) != s.charAt(hi)) return false;
        return isPalindrome(s, lo + 1, hi - 1);
    }

    // ══════════════════════════════════════════════════════════════════
    //  8. BINARY SEARCH
    //     Time: O(log n)  |  Space: O(log n)  (iterative: O(1))
    // ══════════════════════════════════════════════════════════════════

    /**
     * Recursive binary search on a sorted array.
     * Recurrence: T(n) = T(n/2) + O(1)  →  O(log n) by Master Theorem.
     */
    static int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;         // prevents int overflow
        if (nums[mid] == target) return mid;
        if (nums[mid] > target)  return binarySearch(nums, target, start, mid - 1);
        return                          binarySearch(nums, target, mid + 1, end);
    }

    // ══════════════════════════════════════════════════════════════════
    //  9. BACKTRACKING — SUBSETS  (LeetCode 78)
    //     Time: O(2^n)  |  Space: O(n) recursion depth
    // ══════════════════════════════════════════════════════════════════

    /**
     * Generates all 2^n subsets of nums[] and prints each one.
     *
     * <p>Decision tree for nums=[1,2,3], shown left=include, right=skip:
     * <pre>
     *                    []
     *             /             \
     *          [1]              []
     *        /     \          /    \
     *      [1,2]  [1]       [2]   []
     *      / \    / \       / \   / \
     *  [1,2,3][1,2][1,3][1][2,3][2][3][]
     * </pre>
     *
     * <p>Template (memorise this — it unlocks 20+ LeetCode problems):
     * <pre>
     *   choose   → recurse   → un-choose   (backtrack)
     * </pre>
     *
     * @param nums    input array
     * @param index   current position in nums
     * @param current subset being built (shared, mutated in-place)
     * @param result  accumulates all complete subsets
     */
    static void subsets(int[] nums, int index, List<Integer> current,
                        List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current)); // snapshot — don't add the live list
            return;
        }

        // Branch 1: INCLUDE nums[index]
        current.add(nums[index]);
        subsets(nums, index + 1, current, result);
        current.remove(current.size() - 1);   // backtrack — undo the choice

        // Branch 2: SKIP nums[index]
        subsets(nums, index + 1, current, result);
    }

    // ══════════════════════════════════════════════════════════════════
    // 10. BACKTRACKING — PERMUTATIONS  (LeetCode 46)
    //     Time: O(n × n!)  |  Space: O(n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Generates all n! permutations of nums[].
     *
     * <p>Decision tree for [1,2,3]:
     * <pre>
     *   swap(0,0)   swap(0,1)   swap(0,2)
     *   [1,2,3]     [2,1,3]     [3,2,1]
     *   /   \       /   \       /   \
     * [1,2,3][1,3,2] ... ...  ...  ...
     * </pre>
     *
     * <p>In-place swap technique: at each depth level, choose which element
     * occupies position {@code start} by swapping it there, recurse on the
     * remainder, then swap back (backtrack).
     */
    static void permutations(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int x : nums) perm.add(x);
            result.add(perm);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);                     // choose: put nums[i] at position start
            permutations(nums, start + 1, result);    // recurse on the rest
            swap(nums, start, i);                     // backtrack: restore original order
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    // ══════════════════════════════════════════════════════════════════
    // 11. BACKTRACKING — COMBINATION SUM  (LeetCode 39)
    //     Time: O(2^(target/min))  |  Space: O(target/min) depth
    // ══════════════════════════════════════════════════════════════════

    /**
     * Finds all combinations from candidates[] that sum to target.
     * Each candidate can be reused unlimited times.
     *
     * <p>Example: candidates=[2,3,6,7], target=7
     * <pre>
     *   [2,2,3], [2,5 — wait, 5 not in array], [7]
     *   → output: [[2,2,3], [7]]
     * </pre>
     *
     * <p>Key difference from subsets: we don't skip to index+1 after including
     * an element — we stay at the same index (allow reuse).
     * We do prune early if remaining < 0 to avoid useless branches.
     *
     * @param candidates distinct positive integers
     * @param target     target sum
     * @param index      current position (candidates[0..index-1] already decided)
     * @param current    combination being built
     * @param result     accumulates all valid combinations
     */
    static void combinationSum(int[] candidates, int target, int index,
                               List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));  // found a valid combination
            return;
        }
        if (target < 0 || index == candidates.length) return; // prune

        // Branch 1: INCLUDE candidates[index] again (allow reuse — stay at same index)
        current.add(candidates[index]);
        combinationSum(candidates, target - candidates[index], index, current, result);
        current.remove(current.size() - 1);        // backtrack

        // Branch 2: SKIP candidates[index] (move to next)
        combinationSum(candidates, target, index + 1, current, result);
    }

    // ══════════════════════════════════════════════════════════════════
    // 12. TOWER OF HANOI
    //     Time: O(2^n) — minimum 2^n − 1 moves required  |  Space: O(n)
    // ══════════════════════════════════════════════════════════════════

    /**
     * Solves Tower of Hanoi — moves n disks from src to dst using aux.
     *
     * <p>Why O(2^n)? T(n) = 2·T(n−1) + 1  →  T(n) = 2^n − 1.
     * For n=3: 7 moves. For n=10: 1023 moves. For n=64: 1.8 × 10^19 moves.
     *
     * <p>The elegant insight: to move n disks from A to C,
     * first solve the sub-problem of moving n−1 disks from A to B,
     * then move the largest disk directly, then solve n−1 disks from B to C.
     */
    static void hanoi(int n, char src, char dst, char aux) {
        if (n == 0) return;
        hanoi(n - 1, src, aux, dst);                          // step 1: n-1 to aux
        System.out.printf("  Move disk %2d: %c → %c%n", n, src, dst); // step 2: largest to dst
        hanoi(n - 1, aux, dst, src);                          // step 3: n-1 from aux to dst
    }

    // ══════════════════════════════════════════════════════════════════
    //  MAIN — labelled test output for every function
    // ══════════════════════════════════════════════════════════════════

    public static void main(String[] args) {

        // ── 1. Print ─────────────────────────────────────────────────
        System.out.print("Ascending  1→5 : ");
        printAscending(5);
        System.out.println();

        System.out.print("Descending 5→1 : ");
        printDescending(5);
        System.out.println();

        // ── 2. Factorial ─────────────────────────────────────────────
        System.out.println("10!            = " + factorial(10));    // 3628800
        System.out.println("20!            = " + factorial(20));    // 2432902008176640000

        // ── 3. Fibonacci — naive vs memoized ─────────────────────────
        System.out.print("fib(0..9) naive : ");
        for (int i = 0; i < 10; i++) System.out.print(fib(i) + " ");
        System.out.println();

        int n = 10;
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, -1);
        System.out.println("fib(10) memo   = " + fibMemo(n, dp));   // 55

        // ── 4. Power — naive vs fast ──────────────────────────────────
        System.out.println("power(2,10)    = " + power(2, 10));     // 1024
        System.out.println("fastPow(2,10)  = " + fastPow(2, 10));   // 1024
        System.out.println("fastPow(2,62)  = " + fastPow(2, 62));   // 4611686018427387904

        // ── 5. Sum of digits ──────────────────────────────────────────
        System.out.println("sumDigits(9875)= " + sumDigits(9875));   // 29

        // ── 6. Reverse string ─────────────────────────────────────────
        System.out.println("reverse(hello) = " + reverse("hello"));  // olleh

        // ── 7. Palindrome ─────────────────────────────────────────────
        System.out.println("isPalin(racecar)= " + isPalindrome("racecar", 0, 6)); // true
        System.out.println("isPalin(hello)  = " + isPalindrome("hello",   0, 4)); // false

        // ── 8. Binary search ──────────────────────────────────────────
        int[] sorted = {2, 3, 5, 6, 7, 9, 10};
        System.out.println("bSearch(9)     = " + binarySearch(sorted, 9, 0, 6)); // 5
        System.out.println("bSearch(4)     = " + binarySearch(sorted, 4, 0, 6)); // -1

        // ── 9. Subsets ────────────────────────────────────────────────
        List<List<Integer>> subsetResult = new ArrayList<>();
        subsets(new int[]{1, 2, 3}, 0, new ArrayList<>(), subsetResult);
        System.out.println("subsets([1,2,3])= " + subsetResult);
        // [[], [3], [2], [2,3], [1], [1,3], [1,2], [1,2,3]]

        // ── 10. Permutations ──────────────────────────────────────────
        List<List<Integer>> permResult = new ArrayList<>();
        permutations(new int[]{1, 2, 3}, 0, permResult);
        System.out.println("perms([1,2,3])  = " + permResult);
        // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1],[3,1,2]]

        // ── 11. Combination sum ───────────────────────────────────────
        List<List<Integer>> combResult = new ArrayList<>();
        combinationSum(new int[]{2, 3, 6, 7}, 7, 0, new ArrayList<>(), combResult);
        System.out.println("combSum(7)      = " + combResult);
        // [[2,2,3],[7]]

        // ── 12. Tower of Hanoi ────────────────────────────────────────
        System.out.println("\nTower of Hanoi (3 disks):");
        hanoi(3, 'A', 'C', 'B');
        // 7 moves total
    }
}