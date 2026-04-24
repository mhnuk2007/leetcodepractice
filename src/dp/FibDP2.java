package dp;

import java.util.Arrays;

public class FibDP2 {

    public static int fib(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return fibdp(n, dp);
    }

    public static int fibdp(int n, int[] dp) {
        if (n <= 1) return dp[n] = n;

        if (dp[n] != -1) return dp[n];

        return dp[n] = fibdp(n - 1, dp) + fibdp(n - 2, dp);
    }

    public static void main(String[] args) {
        System.out.println(fib(4));
    }
}