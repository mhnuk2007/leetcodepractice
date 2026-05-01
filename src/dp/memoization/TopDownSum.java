package dp;

import java.util.Arrays;

public class TopDownSum {
    public static int sum(int n, int[] dp) {
        if (n == 0) return 0;
        if (dp[n] != -1) return dp[n];
        return dp[n] = n + sum(n - 1, dp);
    }


    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(sum(n, dp));
    }
}
