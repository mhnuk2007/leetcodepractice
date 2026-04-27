package dp;

import java.util.Arrays;

public class TribonacciTopDown {

    /**
     * Returns the nth Tribonacci number.
     * T(0)=0, T(1)=1, T(2)=1, T(n)=T(n-1)+T(n-2)+T(n-3)
     *
     * Approach: Top-down DP (memoization with int[] cache).
     * Recurse from n down to base cases, cache results on the way back.
     * Time: O(n), Space: O(n)
     *
     * tribonacci(0)  → 0
     * tribonacci(1)  → 1
     * tribonacci(2)  → 1
     * tribonacci(4)  → 4
     * tribonacci(25) → 1389537
     */
    public int tribonacci(int n) {
        int[] dp = new int[Math.max(n + 1, 3)];
        Arrays.fill(dp, -1);
        return helper(n, dp);
    }

    private static int helper(int n, int[] dp) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (dp[n] != -1) return dp[n];
        dp[n] = helper(n - 1, dp) + helper(n - 2, dp) + helper(n - 3, dp);
        return dp[n];
    }

    public static void main(String[] args) {
        TribonacciTopDown t = new TribonacciTopDown();
        System.out.println(t.tribonacci(0));  // Expected: 0
        System.out.println(t.tribonacci(1));  // Expected: 1
        System.out.println(t.tribonacci(2));  // Expected: 1
        System.out.println(t.tribonacci(4));  // Expected: 4
        System.out.println(t.tribonacci(25)); // Expected: 1389537
    }
}