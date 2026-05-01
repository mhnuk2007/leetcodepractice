package dp;

import java.util.Arrays;

public class MinCostClimbingStairs {
    public static int minCostClimbingStairsBottomUp(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public static int minCostClimbingStairsTopDown(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return Math.min(helper(n - 1, cost, dp), helper(n - 2, cost, dp));
    }

    public static int helper(int i, int[] cost, int[] dp) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        if (dp[i] != -1) return dp[i];
        dp[i] = cost[i] + Math.min(helper(i - 1, cost, dp), helper(i - 2, cost, dp));
        return dp[i];
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairsBottomUp(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairsTopDown(new int[]{10, 15, 20}));

    }
}
