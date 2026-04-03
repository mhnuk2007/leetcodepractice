package recursion;

/**
 * Recursion — Core Patterns
 *
 * <p>Demonstrates the four fundamental recursion patterns:
 * <ol>
 *   <li>Linear recursion   — print, factorial, sumDigits, power</li>
 *   <li>Binary recursion   — Fibonacci</li>
 *   <li>Divide & Conquer   — binarySearch</li>
 *   <li>Tail-ish recursion — palindrome, towerOfHanoi</li>
 * </ol>
 *
 * <p><b>Every recursive method must have:</b>
 * <ul>
 *   <li>A base case (termination condition)</li>
 *   <li>A recursive call that moves toward the base case</li>
 * </ul>
 */
public class RecursionIntro {

    // ──────────────────────────────────────────────────────────────────
    // 1. PRINT 1 → N  (head recursion — call first, then print)
    //    Time: O(n)  |  Space: O(n) call stack
    // ──────────────────────────────────────────────────────────────────

    /**
     * Prints integers from 1 to n in ascending order.
     * Recurse first, print on the way back up (head recursion).
     */
    static void printAscending(int n) {
        if (n == 0) return;          // base case
        printAscending(n - 1);       // recurse toward 0
        System.out.print(n + " ");   // print on the way back
    }

    /**
     * Prints integers from n down to 1 (tail recursion — print then recurse).
     */
    static void printDescending(int n) {
        if (n == 0) return;
        System.out.print(n + " ");   // print before going deeper
        printDescending(n - 1);
    }

    // ──────────────────────────────────────────────────────────────────
    // 2. FACTORIAL  n! = n × (n-1)!
    //    Time: O(n)  |  Space: O(n)
    // ──────────────────────────────────────────────────────────────────

    /**
     * Returns n! (factorial).
     * Uses {@code long} to safely handle up to 20! without overflow.
     *
     * @param n non-negative integer
     * @return n factorial
     */
    static long factorial(int n) {
        if (n <= 1) return 1;              // base case: 0! = 1! = 1
        return (long) n * factorial(n - 1);
    }

    // ──────────────────────────────────────────────────────────────────
    // 3. FIBONACCI  F(n) = F(n-1) + F(n-2)
    //    Time: O(2^n) naive  |  Space: O(n)
    //    NOTE: deliberate naive implementation; memoization is a separate step.
    // ──────────────────────────────────────────────────────────────────

    /**
     * Returns the nth Fibonacci number (0-indexed: fib(0)=0, fib(1)=1).
     * Intentionally naive — binary recursion to study call-tree structure.
     */
    static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    // ──────────────────────────────────────────────────────────────────
    // 4. SUM OF DIGITS   e.g. 123 → 6
    //    Time: O(log₁₀ n)  |  Space: O(log₁₀ n)
    // ──────────────────────────────────────────────────────────────────

    /**
     * Returns the sum of all digits in a non-negative integer.
     * Example: sumDigits(123) → 6
     */
    static int sumDigits(int n) {
        if (n < 10) return n;              // single digit — base case
        return (n % 10) + sumDigits(n / 10);
    }

    // ──────────────────────────────────────────────────────────────────
    // 5. POWER  base^exp
    //    Time: O(exp)  |  Space: O(exp)
    //    (O(log exp) possible with fast-power — good next step)
    // ──────────────────────────────────────────────────────────────────

    /**
     * Returns base raised to a non-negative integer exponent.
     * Example: power(2, 10) → 1024
     */
    static long power(long base, int exp) {
        if (exp == 0) return 1;            // base case: anything^0 = 1
        return base * power(base, exp - 1);
    }

    // ──────────────────────────────────────────────────────────────────
    // 6. PALINDROME CHECK
    //    Time: O(n)  |  Space: O(n)
    // ──────────────────────────────────────────────────────────────────

    /**
     * Returns true if the substring s[lo..hi] is a palindrome.
     * Compares outermost characters and recurses inward.
     */
    static boolean isPalindrome(String s, int lo, int hi) {
        if (lo >= hi) return true;                         // base: 0 or 1 chars left
        if (s.charAt(lo) != s.charAt(hi)) return false;   // mismatch — not a palindrome
        return isPalindrome(s, lo + 1, hi - 1);
    }

    // ──────────────────────────────────────────────────────────────────
    // 7. BINARY SEARCH (recursive)
    //    Time: O(log n)  |  Space: O(log n) — iterative avoids stack
    // ──────────────────────────────────────────────────────────────────

    /**
     * Searches for {@code target} in a sorted array between indices [start, end].
     *
     * @return index of target, or -1 if not found
     */
    static int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end) return -1;                          // base: search space empty
        int mid = start + (end - start) / 2;                 // avoids integer overflow
        if (nums[mid] == target)  return mid;
        if (nums[mid] > target)   return binarySearch(nums, target, start, mid - 1);
        return binarySearch(nums, target, mid + 1, end);
    }

    // ──────────────────────────────────────────────────────────────────
    // 8. TOWER OF HANOI
    //    Time: O(2^n)  |  Space: O(n)  — minimum 2^n - 1 moves required
    // ──────────────────────────────────────────────────────────────────

    /**
     * Solves Tower of Hanoi: moves {@code n} disks from {@code src} to {@code dst}
     * using {@code aux} as the auxiliary peg.
     * Prints each move as: Move disk k: src → dst
     */
    static void hanoi(int n, char src, char dst, char aux) {
        if (n == 0) return;                              // base: nothing to move
        hanoi(n - 1, src, aux, dst);                    // move top n-1 to aux
        System.out.printf("Move disk %d: %c → %c%n", n, src, dst);
        hanoi(n - 1, aux, dst, src);                    // move n-1 from aux to dst
    }

    // ──────────────────────────────────────────────────────────────────
    // MAIN — test all functions with labelled output
    // ──────────────────────────────────────────────────────────────────

    public static void main(String[] args) {

        // 1. Print ascending / descending
        System.out.print("Ascending  1→5 : ");
        printAscending(5);
        System.out.println();

        System.out.print("Descending 5→1 : ");
        printDescending(5);
        System.out.println();

        // 2. Factorial
        System.out.println("10!        = " + factorial(10));   // 3628800
        System.out.println("20!        = " + factorial(20));   // 2432902008176640000

        // 3. Fibonacci sequence (first 10 terms)
        System.out.print("Fibonacci  : ");
        for (int i = 0; i < 10; i++) System.out.print(fib(i) + " ");
        System.out.println();

        // 4. Sum of digits
        System.out.println("sumDigits(9875)  = " + sumDigits(9875));  // 29

        // 5. Power
        System.out.println("2^10       = " + power(2, 10));  // 1024
        System.out.println("3^5        = " + power(3, 5));   // 243

        // 6. Palindrome
        System.out.println("'racecar' palindrome? " + isPalindrome("racecar", 0, 6));  // true
        System.out.println("'hello'   palindrome? " + isPalindrome("hello",   0, 4));  // false

        // 7. Binary search
        int[] sorted = {2, 3, 5, 6, 7, 9, 10};
        System.out.println("binarySearch(9)  index = " + binarySearch(sorted, 9,  0, 6)); // 5
        System.out.println("binarySearch(4)  index = " + binarySearch(sorted, 4,  0, 6)); // -1

        // 8. Tower of Hanoi (3 disks)
        System.out.println("\nTower of Hanoi (3 disks):");
        hanoi(3, 'A', 'C', 'B');
    }
}