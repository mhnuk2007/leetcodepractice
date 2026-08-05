package dp.memoization;
// Leetcode: 1140 - Stone Game II
public class StoneGameII {
    private static int stoneGame(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) suffixSum[i] = suffixSum[i + 1] + piles[i];
        Integer[][] dp = new Integer[n][n + 1];
        return solve(piles, 0, 1, n, suffixSum, dp);
    }

    private static int solve(int[] piles, int i, int m, int n, int[] suffixSum, Integer[][] dp) {
        // piles      = [2, 7, 9, 4, 4]
        // suffixSum  = [26, 24, 17, 8, 4, 0]

        /*
        solve(0, 1)
        Remaining stones = 26

        j = 1
            opponent = solve(1, 1) = 16
            current  = 26 - 16 = 10

        j = 2
            opponent = solve(2, 2) = 17
            current  = 26 - 17 = 9

        dp[0][1] = max(10, 9) = 10


        solve(1, 1)
        Remaining stones = 24

        j = 1
            opponent = solve(2, 1) = 13
            current  = 24 - 13 = 11

        j = 2
            opponent = solve(3, 2) = 8
            current  = 24 - 8 = 16

        dp[1][1] = max(11, 16) = 16


        solve(2, 1)
        Remaining stones = 17

        j = 1
            opponent = solve(3, 1) = 8
            current  = 17 - 8 = 9

        j = 2
            opponent = solve(4, 2) = 4
            current  = 17 - 4 = 13

        dp[2][1] = max(9, 13) = 13


        solve(3, 1)
        2 * M = 2
        Remaining piles = 2

        Can take all remaining piles.

        return suffixSum[3] = 8


        solve(4, 2)
        2 * M = 4
        Remaining piles = 1

        Can take all remaining piles.

        return suffixSum[4] = 4


        solve(2, 2)
        2 * M = 4
        Remaining piles = 3

        Can take all remaining piles.

        return suffixSum[2] = 17
        */
        if (i == n) return 0;
        if (2 * m >= n - i) return suffixSum[i];
        if (dp[i][m] != null) return dp[i][m];
        int best = 0;
        for (int j = 1; j <= 2 * m; j++) {
            if (i + j > n) break;
            int opponent = solve(piles, i + j, Math.max(m, j), n, suffixSum, dp);
            best = Math.max(best, suffixSum[i] - opponent);
        }
        return dp[i][m] = best;
    }

    public static void main(String[] args) {
        int[] piles = {2, 7, 9, 4, 4};
        System.out.println(stoneGame(piles)); // 10

        piles = new int[]{1, 2, 3, 4, 5, 100};
        System.out.println(stoneGame(piles)); // 104
    }
}
