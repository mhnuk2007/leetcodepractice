package dp;

public class BottonUpSum {
    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n ; i++) {
            dp[i] = i + dp[i - 1];
        }
        System.out.println(dp[n]);
    }
}
