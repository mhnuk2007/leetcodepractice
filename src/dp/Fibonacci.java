package dp;

import java.util.Arrays;

public class Fibonacci {

    /**
     * Returns the nth Fibonacci number using top-down DP (memoization).
     *
     * Approach: Recursive + int[] cache, sentinel -1
     * Time: O(n), Space: O(n)
     *
     * fib(0) → 0
     * fib(1) → 1
     * fib(6) → 8
     * fib(10) → 55
     */
    public int fibTopDown(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n, dp);
    }

    private static int helper(int n, int[] dp) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (dp[n] != -1) return dp[n];
        int result = helper(n - 1, dp) + helper(n - 2, dp);
        dp[n] = result;
        return result;
    }

    /**
     * Returns the nth Fibonacci number using bottom-up DP (tabulation).
     *
     * Approach: Iterative, fill dp[] from base cases upward
     * Time: O(n), Space: O(n)
     *
     * fib(0) → 0
     * fib(1) → 1
     * fib(6) → 8
     * fib(10) → 55
     */
    public int fibBottomUp(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Returns the nth Fibonacci number using bottom-up DP (space optimised).
     *
     * Approach: Only prev two values needed, no array required
     * Time: O(n), Space: O(1)
     *
     * fib(0) → 0
     * fib(1) → 1
     * fib(6) → 8
     * fib(10) → 55
     */
    public int fibOptimised(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int prev2 = 0, prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();

        System.out.println(fib.fibTopDown(0));  // Expected: 0
        System.out.println(fib.fibTopDown(1));  // Expected: 1
        System.out.println(fib.fibTopDown(6));  // Expected: 8
        System.out.println(fib.fibTopDown(10)); // Expected: 55

        System.out.println(fib.fibBottomUp(0));  // Expected: 0
        System.out.println(fib.fibBottomUp(1));  // Expected: 1
        System.out.println(fib.fibBottomUp(6));  // Expected: 8
        System.out.println(fib.fibBottomUp(10)); // Expected: 55

        System.out.println(fib.fibOptimised(0));  // Expected: 0
        System.out.println(fib.fibOptimised(1));  // Expected: 1
        System.out.println(fib.fibOptimised(6));  // Expected: 8
        System.out.println(fib.fibOptimised(10)); // Expected: 55
    }
}