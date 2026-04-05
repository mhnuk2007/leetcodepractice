package recursion;

/**
 * LeetCode 779 - K-th Symbol in Grammar
 *
 * Problem:
 *   Row 1: "0". Each subsequent row is built by replacing '0' with "01"
 *   and '1' with "10". Return the k-th symbol (1-indexed) in row n.
 *
 * Pattern:
 *   Row 1: 0
 *   Row 2: 0 1
 *   Row 3: 0 1 1 0
 *   Row 4: 0 1 1 0 1 0 0 1
 *   Left half of row n = row n-1 (same)
 *   Right half of row n = row n-1 flipped
 *
 * Approach 1: Brute force
 *   Build each row as a string up to row n. O(2^n) time and space — TLE for large n.
 *
 * Approach 2: Iterative bit counting
 *   Convert k to 0-indexed. Count set bits (1s) in k.
 *   Each set bit represents a flip — odd flips → 1, even flips → 0.
 *   Shorthand: Integer.bitCount(k-1) % 2
 *
 * Approach 3: Recursive
 *   k is odd  → left child  → same value as parent kthGrammar(n-1, (k+1)/2)
 *   k is even → right child → flipped value of parent kthGrammar(n-1, k/2)
 *   Base case: n == 1 → return 0
 *
 * Approach 4: Tail recursion
 *   Carry flip count as parameter — no pending work after recursive call.
 *   k is odd  → left child  → same (no flip)
 *   k is even → right child → flip (increment flips)
 *
 * Example:
 *   kthGrammar(4, 5) → 1
 *   kthGrammar(1, 1) → 0
 *
 * Time  : O(2^n) — brute force; O(log n) — all other approaches
 * Space : O(2^n) — brute force; O(1) — iterative; O(log n) — recursive call stack
 */
public class KthSymbolInGrammar {

    public static void main(String[] args) {
        // Test 1: base case
        System.out.println("kthGrammar(1,1):         " + kthGrammar(1, 1));
        // Expected: 0

        // Test 2: standard case — all approaches
        System.out.println("kthGrammar(4,5):         " + kthGrammar(4, 5));
        // Expected: 1
        System.out.println("kthGrammarIter(4,5):     " + kthGrammarIter(4, 5));
        // Expected: 1
        System.out.println("kthGrammarIter2(4,5):    " + kthGrammarIter2(4, 5));
        // Expected: 1
        System.out.println("kthGrammarRec(4,5):      " + kthGrammarRec(4, 5));
        // Expected: 1
        System.out.println("kthGrammarTailIter(4,5): " + kthGrammarTailIter(4, 5));
        // Expected: 1
        System.out.println("kthGrammarTailRec(4,5):  " + kthGrammarTailRec(4, 5));
        // Expected: 1

        // Test 3: large n — only O(log n) approaches can handle this
        System.out.println("kthGrammarIter(30,k):     " + kthGrammarIter(30, 434991989));
        System.out.println("kthGrammarIter2(30,k):    " + kthGrammarIter2(30, 434991989));
        System.out.println("kthGrammarRec(30,k):      " + kthGrammarRec(30, 434991989));
        System.out.println("kthGrammarTailIter(30,k): " + kthGrammarTailIter(30, 434991989));
        System.out.println("kthGrammarTailRec(30,k):  " + kthGrammarTailRec(30, 434991989));
    }

    // Brute force — O(2^n) time and space
    public static int kthGrammar(int n, int k) {
        String row = "0";
        for (int i = 1; i < n; i++) {
            StringBuilder next = new StringBuilder();
            for (int j = 0; j < row.length(); j++) {
                next.append(row.charAt(j) == '0' ? "01" : "10");
            }
            row = next.toString();
        }
        return row.charAt(k - 1) - '0';
    }

    // Iterative bit counting — O(log n)
    public static int kthGrammarIter(int n, int k) {
        k = k - 1;                                                         // convert to 0-indexed
        int flips = 0;
        while (k > 0) {
            if (k % 2 == 1) flips++;                                       // right child → flip
            k /= 2;
        }
        return flips % 2;                                                  // odd flips → 1, even → 0
    }

    // Iterative bit counting shorthand — O(log n)
    public static int kthGrammarIter2(int n, int k) {
        return Integer.bitCount(k - 1) % 2;                               // count set bits in 0-indexed k
    }

    // Recursive — O(log n)
    public static int kthGrammarRec(int n, int k) {
        if (n == 1) return 0;                                              // base case — row 1 is always 0
        int parent = kthGrammarRec(n - 1, (k + 1) / 2);                  // find parent value
        return (k % 2 == 0) ? 1 - parent : parent;                        // even=right=flipped, odd=left=same
    }

    // Tail recursive iterative equivalent — O(log n)
    public static int kthGrammarTailIter(int n, int k) {
        int flips = 0;
        while (n > 1) {
            if (k % 2 == 0) flips++;                                       // even=right child → flip
            k = (k + 1) / 2;                                              // move to parent
            n--;
        }
        return flips % 2;                                                  // odd flips → 1, even → 0
    }

    // Tail recursive — O(log n)
    public static int kthGrammarTailRec(int n, int k) {
        return helper(n, k, 0);
    }

    private static int helper(int n, int k, int flips) {
        if (n == 1) return flips % 2;                                      // base case — apply accumulated flips
        if (k % 2 == 1) return helper(n - 1, (k + 1) / 2, flips);        // odd=left=same
        return helper(n - 1, k / 2, flips + 1);                           // even=right=flip
    }
}