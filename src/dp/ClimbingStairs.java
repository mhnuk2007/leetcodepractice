package dp;

import java.util.Arrays;
import java.util.HashMap;

public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n, dp);
    }
    private int helper(int n, int[] dp) {
        if (n == 0 || n == 1) return 1;
        if (dp[n] != -1) return dp[n];
        int result = helper(n - 1, dp) + helper(n - 2, dp);
        dp[n] = result;
        return result;
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        System.out.println(cs.climbStairs(5));
    }
}
