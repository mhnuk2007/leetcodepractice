package recursion;

/**
 * Sum of First N Natural Numbers
 *
 * Problem:
 *   Given a positive integer n, return the sum of first n natural numbers
 *   using recursion.
 *
 * Approach: Linear recursion
 *   sum(n) = n + sum(n-1)
 *   Base case: n == 1 → return 1
 *
 * Example:
 *   n = 5 → 5 + 4 + 3 + 2 + 1 = 15
 *   n = 1 → 1
 *
 * Time  : O(n) — one recursive call per decrement
 * Space : O(n) — recursion depth
 */
public class SumOfNNums {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("n=10: " + sumOfN(10));
        // Expected: 55

        // Test 2: small input
        System.out.println("n=5: " + sumOfN(5));
        // Expected: 15

        // Test 3: base case
        System.out.println("n=1: " + sumOfN(1));
        // Expected: 1
    }

    private static int sumOfN(int n) {
        if (n == 1) return 1;                                              // base case
        return n + sumOfN(n - 1);                                         // recursive case
    }
}