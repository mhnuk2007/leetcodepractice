package dp;

import java.util.Arrays;

/**
 * LeetCode 518 - Coin Change II
 *
 * <p>Given an array of coin denominations and a target amount, return the number
 * of combinations that make up that amount. Each coin may be used unlimited times.
 * The order of coins does not matter (combinations, not permutations).
 *
 * <p>Pattern: Unbounded Knapsack (count ways variant)
 * <ul>
 *   <li>Left-to-right inner loop — allows unlimited reuse of each coin</li>
 *   <li>dp[j] += dp[j - coin] — accumulates combination count</li>
 *   <li>dp[0] = 1 — one way to make amount 0 (pick nothing)</li>
 * </ul>
 *
 * <p>Contrast with LC 494 Target Sum (also count ways):
 * <ul>
 *   <li>LC 494 — 0/1 knapsack, right-to-left (each element used once)</li>
 *   <li>LC 518 — unbounded knapsack, left-to-right (each coin reusable)</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - recursive with cache</li>
 *   <li>Bottom-Up DP (2D Table) - iterative tabulation</li>
 *   <li>Bottom-Up DP (1D Optimized) - space-optimized unbounded knapsack</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class CoinChangeII {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(coins.length * amount)
    // Space : O(coins.length * amount) — memo table + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the number of combinations to make up amount using top-down
     * memoized recursion.
     *
     * @param amount target amount
     * @param coins  array of coin denominations
     * @return number of combinations
     */
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(coins, 0, amount, dp);
    }

    /**
     * Recursive helper — counts combinations using coins[i..] to make up remaining.
     *
     * <p>Stays at index i on take (unbounded — coin reusable), moves to i+1 on skip.
     *
     * @param coins     coin denominations
     * @param i         current coin index
     * @param remaining remaining amount to make up
     * @param dp        memoization table indexed by [coin index][remaining amount]
     * @return number of valid combinations
     */
    private static int helper(int[] coins, int i, int remaining, int[][] dp) {
        if (remaining == 0) return 1;
        if (i == coins.length || remaining < 0) return 0;
        if (dp[i][remaining] != -1) return dp[i][remaining];
        int skip = helper(coins, i + 1, remaining, dp);
        int take = (coins[i] <= remaining) ? helper(coins, i, remaining - coins[i], dp) : 0;
        return dp[i][remaining] = skip + take;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (2D Table)
    // Time  : O(coins.length * amount)
    // Space : O(coins.length * amount)
    // -------------------------------------------------------------------------

    /**
     * Returns the number of combinations to make up amount using a bottom-up
     * 2D DP table.
     *
     * <p>dp[i][j] = number of combinations using first i coin types to make amount j.
     * Recurrence: dp[i][j] = dp[i-1][j]                      (skip coin i)
     *                       + dp[i][j - coins[i-1]]           (take coin i, reuse allowed)
     *
     * @param amount target amount
     * @param coins  array of coin denominations
     * @return number of combinations
     */
    public static int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: one way to make amount 0 regardless of coins available
        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];                      // skip coin i
                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]];      // take coin i (reuse)
                }
            }
        }
        return dp[n][amount];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (1D Space-Optimized)
    // Time  : O(coins.length * amount)
    // Space : O(amount) — single int array
    // -------------------------------------------------------------------------

    /**
     * Returns the number of combinations to make up amount using a
     * space-optimized 1D DP array.
     *
     * <p>Left-to-right traversal allows each coin to be reused freely within
     * the same pass, satisfying the unbounded knapsack constraint.
     * dp[0] = 1 seeds the count — one way to make amount 0.
     *
     * @param amount target amount
     * @param coins  array of coin denominations
     * @return number of combinations
     */
    public static int change3(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {             // left to right — unbounded
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: coins=[1,2,5], amount=5 → 4 combinations
        // (5), (1+2+2), (1+1+1+2), (1+1+1+1+1)
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println("Test 1 - Top-Down   : " + change(amount1, coins1));  // 4
        System.out.println("Test 1 - 2D Bottom-Up: " + change2(amount1, coins1)); // 4
        System.out.println("Test 1 - 1D Optimized: " + change3(amount1, coins1)); // 4

        System.out.println();

        // Test Case 2: coins=[2], amount=3 → 0 (odd amount, only even coin)
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Test 2 - Top-Down   : " + change(amount2, coins2));  // 0
        System.out.println("Test 2 - 2D Bottom-Up: " + change2(amount2, coins2)); // 0
        System.out.println("Test 2 - 1D Optimized: " + change3(amount2, coins2)); // 0

        System.out.println();

        // Test Case 3: amount=0 → 1 (one way — pick nothing)
        int[] coins3 = {1, 2, 5};
        int amount3 = 0;
        System.out.println("Test 3 - Top-Down   : " + change(amount3, coins3));  // 1
        System.out.println("Test 3 - 2D Bottom-Up: " + change2(amount3, coins3)); // 1
        System.out.println("Test 3 - 1D Optimized: " + change3(amount3, coins3)); // 1

        System.out.println();

        // Test Case 4: Single coin equals amount → 1
        int[] coins4 = {5};
        int amount4 = 5;
        System.out.println("Test 4 - Top-Down   : " + change(amount4, coins4));  // 1
        System.out.println("Test 4 - 2D Bottom-Up: " + change2(amount4, coins4)); // 1
        System.out.println("Test 4 - 1D Optimized: " + change3(amount4, coins4)); // 1
    }
}