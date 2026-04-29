package dp;

import java.util.Arrays;

/**
 * LeetCode 322 - Coin Change
 *
 * <p>Given an array of coin denominations and a target amount, return the fewest
 * number of coins needed to make up that amount. If it cannot be made up by any
 * combination of coins, return -1. Each coin may be used unlimited times.
 *
 * <p>Pattern: Unbounded Knapsack (minimization variant)
 * <ul>
 *   <li>Left-to-right inner loop — allows unlimited reuse of each coin</li>
 *   <li>Sentinel value (amount + 1) marks unreachable states safely without overflow</li>
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
public class CoinChange {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(amount * coins.length)
    // Space : O(amount) — memo array + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the minimum number of coins to make up amount using top-down
     * memoized recursion.
     *
     * @param coins  array of coin denominations
     * @param amount target amount
     * @return minimum coin count, or -1 if not achievable
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int result = helper(coins, amount, dp);
        return result;
    }

    /**
     * Recursive helper — returns minimum coins to make up remaining amount.
     *
     * @param coins     coin denominations
     * @param remaining remaining amount to make up
     * @param dp        memoization array indexed by amount
     * @return minimum coins needed, or Integer.MAX_VALUE if unreachable
     */
    private static int helper(int[] coins, int remaining, int[] dp) {
        if (remaining == 0) return 0;
        if (remaining < 0) return Integer.MAX_VALUE;
        if (dp[remaining] != -1) return dp[remaining];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = helper(coins, remaining - coin, dp);
            if (result != Integer.MAX_VALUE) {
                min = Math.min(min, result + 1);
            }
        }
        dp[remaining] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[remaining];
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (2D Table)
    // Time  : O(amount * coins.length)
    // Space : O(coins.length * amount)
    // -------------------------------------------------------------------------

    /**
     * Returns the minimum number of coins to make up amount using a bottom-up
     * 2D DP table.
     *
     * <p>dp[i][j] = min coins using first i coin types to make amount j.
     * Recurrence: dp[i][j] = min(
     *                 dp[i-1][j],                      skip coin i
     *                 dp[i][j - coins[i-1]] + 1        take coin i (reuse allowed)
     *             )
     *
     * @param coins  array of coin denominations
     * @param amount target amount
     * @return minimum coin count, or -1 if not achievable
     */
    public static int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int sentinel = amount + 1;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: without any coins, all amounts > 0 are unreachable
        for (int j = 1; j <= amount; j++) dp[0][j] = sentinel;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];                          // skip coin i
                if (j >= coins[i - 1]) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][j - coins[i - 1]] + 1           // take coin i (reuse)
                    );
                }
            }
        }
        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (1D Space-Optimized)
    // Time  : O(amount * coins.length)
    // Space : O(amount) — single int array
    // -------------------------------------------------------------------------

    /**
     * Returns the minimum number of coins to make up amount using a
     * space-optimized 1D DP array.
     *
     * <p>Left-to-right traversal allows each coin to be reused freely within
     * the same pass, satisfying the unbounded knapsack constraint.
     * Sentinel (amount + 1) safely marks unreachable states without overflow risk.
     *
     * @param coins  array of coin denominations
     * @param amount target amount
     * @return minimum coin count, or -1 if not achievable
     */
    public static int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {          // left to right — unbounded
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: coins=[1,2,5], amount=11 → 3 coins (5+5+1)
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Test 1 - Top-Down   : " + coinChange(coins1, amount1));  // 3
        System.out.println("Test 1 - 2D Bottom-Up: " + coinChange2(coins1, amount1)); // 3
        System.out.println("Test 1 - 1D Optimized: " + coinChange3(coins1, amount1)); // 3

        System.out.println();

        // Test Case 2: coins=[2], amount=3 → -1 (not achievable)
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Test 2 - Top-Down   : " + coinChange(coins2, amount2));  // -1
        System.out.println("Test 2 - 2D Bottom-Up: " + coinChange2(coins2, amount2)); // -1
        System.out.println("Test 2 - 1D Optimized: " + coinChange3(coins2, amount2)); // -1

        System.out.println();

        // Test Case 3: amount=0 → 0 coins needed
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Test 3 - Top-Down   : " + coinChange(coins3, amount3));  // 0
        System.out.println("Test 3 - 2D Bottom-Up: " + coinChange2(coins3, amount3)); // 0
        System.out.println("Test 3 - 1D Optimized: " + coinChange3(coins3, amount3)); // 0

        System.out.println();

        // Test Case 4: Large denomination only — tests sentinel correctness
        int[] coins4 = {5, 10};
        int amount4 = 3;
        System.out.println("Test 4 - Top-Down   : " + coinChange(coins4, amount4));  // -1
        System.out.println("Test 4 - 2D Bottom-Up: " + coinChange2(coins4, amount4)); // -1
        System.out.println("Test 4 - 1D Optimized: " + coinChange3(coins4, amount4)); // -1
    }
}