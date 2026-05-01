package dp;

import java.util.Arrays;

/**
 * 0/1 Knapsack Problem
 *
 * Given n items each with a weight and a value, and a knapsack with a fixed
 * capacity, find the maximum total value that can be placed in the knapsack.
 * Each item can be taken at most once (0/1 — take it or leave it).
 *
 * Pattern : 0/1 Knapsack
 * State   : dp[i][w] = max value using first i items with capacity w
 * Choice  : for each item i, either skip it or take it (if weight fits)
 *
 * Companies: Amazon, Google, Microsoft, Meta
 */
public class Knapsack01 {

    // -------------------------------------------------------------------------
    // Approach 1 — Top-Down DP (Recursion + Memoization)
    // -------------------------------------------------------------------------
    //
    // State:      dp[i][w] = max value using items 0..i with remaining capacity w
    // Transition: dp[i][w] = max(
    //                 skip = helper(i+1, w),
    //                 take = profits[i] + helper(i+1, w - weights[i])  if weights[i] <= w
    //             )
    // Base case:  i == n  →  0  (no items left)
    //             w == 0  →  0  (no capacity left)
    // Answer:     helper(0, capacity)
    //
    // Time : O(n * capacity)
    // Space: O(n * capacity)  —  memo table + recursion stack
    //
    // knapsack([2,3,4,5], [3,4,5,6], 5) → 7
    // knapsack([1,2,3],   [6,10,12], 5) → 22
    // knapsack([1],       [10],      1) → 10
    // knapsack([3,4,5],   [4,5,6],   3) → 4

    public static int knapsackTopDown(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(0, weights, profits, capacity, dp);
    }

    private static int helper(int i, int[] weights, int[] profits, int capacity, int[][] dp) {
        if (i == weights.length || capacity == 0) return 0;
        if (dp[i][capacity] != -1) return dp[i][capacity];

        int skip = helper(i + 1, weights, profits, capacity, dp);
        int take = 0;
        if (weights[i] <= capacity) {
            take = profits[i] + helper(i + 1, weights, profits, capacity - weights[i], dp);
        }

        dp[i][capacity] = Math.max(skip, take);
        return dp[i][capacity];
    }

    // -------------------------------------------------------------------------
    // Approach 2 — Bottom-Up DP (Tabulation)
    // -------------------------------------------------------------------------
    //
    // State:      dp[i][w] = max value using first i items with capacity w
    // Transition: dp[i][w] = dp[i-1][w]                                    (skip)
    //             dp[i][w] = max(dp[i][w],
    //                            dp[i-1][w - weights[i-1]] + profits[i-1]) (take, if fits)
    // Base case:  dp[0][w] = 0 for all w  (no items)
    //             dp[i][0] = 0 for all i  (no capacity)  — guaranteed by Java zero-init
    // Answer:     dp[n][capacity]
    //
    // Time : O(n * capacity)
    // Space: O(n * capacity)

    public static int knapsackBottomUp(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w];                                   // skip item i
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i - 1][w - weights[i - 1]] + profits[i - 1] // take item i
                    );
                }
            }
        }

        return dp[n][capacity];
    }

    // -------------------------------------------------------------------------
    // Approach 3 — Space-Optimised Bottom-Up DP (1D Array)
    // -------------------------------------------------------------------------
    //
    // Observation: dp[i][w] only depends on row dp[i-1], so we can compress
    //              the 2D table into a single 1D array.
    //
    // State:      dp[w] = max value achievable with capacity w
    // Transition: dp[w] = max(dp[w], dp[w - weights[i]] + profits[i])
    // Base case:  dp[w] = 0 for all w  (no items processed yet)
    // Answer:     dp[capacity]
    //
    // Critical detail — iterate capacity RIGHT TO LEFT:
    //   If we iterated left to right, dp[w - weights[i]] would already reflect
    //   item i being taken, allowing the same item to be used more than once.
    //   Right-to-left ensures we always read the "previous row" value — enforcing
    //   the 0/1 constraint (each item used at most once).
    //   (Left-to-right iteration is correct for unbounded knapsack / Coin Change.)
    //
    // Time : O(n * capacity)
    // Space: O(capacity)

    public static int knapsackOptimised(int[] weights, int[] profits, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weights.length; i++) {
            for (int w = capacity; w >= weights[i]; w--) { // right to left — critical
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + profits[i]);
            }
        }

        return dp[capacity];
    }

    // -------------------------------------------------------------------------
    // Main — test all three approaches against identical cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        int[] w1 = {2, 3, 4, 5}, v1 = {3, 4, 5, 6};
        int c1 = 5;
        int[] w2 = {1, 2, 3}, v2 = {6, 10, 12};
        int c2 = 5;
        int[] w3 = {1}, v3 = {10};
        int c3 = 1;
        int[] w4 = {3, 4, 5}, v4 = {4, 5, 6};
        int c4 = 3;

        System.out.println("--- Top-Down (Memoization) ---");
        System.out.println(knapsackTopDown(w1, v1, c1));  // Expected: 7
        System.out.println(knapsackTopDown(w2, v2, c2));  // Expected: 22
        System.out.println(knapsackTopDown(w3, v3, c3));  // Expected: 10
        System.out.println(knapsackTopDown(w4, v4, c4));  // Expected: 4

        System.out.println("--- Bottom-Up (Tabulation) ---");
        System.out.println(knapsackBottomUp(w1, v1, c1)); // Expected: 7
        System.out.println(knapsackBottomUp(w2, v2, c2)); // Expected: 22
        System.out.println(knapsackBottomUp(w3, v3, c3)); // Expected: 10
        System.out.println(knapsackBottomUp(w4, v4, c4)); // Expected: 4

        System.out.println("--- Space-Optimised (1D Array) ---");
        System.out.println(knapsackOptimised(w1, v1, c1)); // Expected: 7
        System.out.println(knapsackOptimised(w2, v2, c2)); // Expected: 22
        System.out.println(knapsackOptimised(w3, v3, c3)); // Expected: 10
        System.out.println(knapsackOptimised(w4, v4, c4)); // Expected: 4
    }
}