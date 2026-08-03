package dp.memoization;
// Leetcode: 877 - Stone Game

public class StoneGameI {
    private static boolean stoneGameBottomUp(int[] piles){
        int n = piles.length;
        Integer[][] dp = new Integer[n][n];
        return solve(piles, 0, n - 1, dp) > 0;
    }

    private static int solve(int[] piles, int left, int right, Integer[][] dp){
        if (left == right) return piles[left];
        if (dp[left][right] != null) return dp[left][right];
        int takeLeft = solve(piles, left + 1, right, dp);
        int takeRight = solve(piles, left, right - 1, dp);
        return dp[left][right] = Math.max(takeLeft, takeRight);
    }

    private static boolean stoneGameTopDown(int[] piles){
        int n = piles.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = piles[i];
        }

        for(int left = n -2; left >= 0; left--){
            for(int right = left + 1; right < n; right++){
                int takeLeft = piles[left] - dp[right];
                int takeRight = piles[right] - dp[right - 1];
                dp[right] = Math.max(takeLeft, takeRight);
            }
        }
        return dp[n - 1] > 0;
    }

    public static void main(String[] args) {
        int[] piles = {5,3,4,5};
        System.out.println(stoneGameBottomUp(piles) ? "Alice wins!" : "Bob wins!");
        System.out.println(stoneGameTopDown(piles) ? "Alice wins!" : "Bob wins!");

    }
}
