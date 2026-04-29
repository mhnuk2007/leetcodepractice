package dp;

import java.util.Arrays;

/**
 *
 *
 * Given n items each with a weight and a value, and a knapsack with a fixed
 * capacity, find the maximum total value that can be placed in the knapsack.
 * Each item can be taken any number of times (unbounded — reuse allowed).
 *
 * Pattern : Unbounded Knapsack
 * State   : dp[i][w] = max value using first i items with capacity w (items reusable)
 * Choice  : for each item i, either skip it or take it repeatedly (if weight fits)
 *
 * Key difference from 0/1 Knapsack:
 *   0/1        → take item i moves to i+1  (item exhausted)
 *   Unbounded  → take item i stays at i    (item still available)
 *   1D array   → 0/1 iterates RIGHT TO LEFT (prevent reuse)
 *              → Unbounded iterates LEFT TO RIGHT (allow reuse)
 *
 * Companies: Amazon, Google, Microsoft, Meta
 */
public class UnboundedKnapsack {

    // -------------------------------------------------------------------------
    // Approach 1 — Top-Down DP (Recursion + Memoization)
    // -------------------------------------------------------------------------
    //
    // State:      dp[i][w] = max value using items 0..i with remaining capacity w
    // Transition: dp[i][w] = max(
    //                 skip = helper(i+1, w),
    //                 take = profits[i] + helper(i, w - weights[i])   ← stays at i (unbounded)
    //             )
    // Base case:  i == n  →  0  (no items left)
    //             w == 0  →  0  (no capacity left)
    // Answer:     helper(0, capacity)
    //
    // Time : O(n * capacity)
    // Space: O(n * capacity)  —  memo table + recursion stack
    //
    // unbounded([2,3,4,5], [3,4,5,6], 5) → 6   (item 0 twice: 2+2=4≤5, profit=3+3=6... wait: item 1 once=4, item 0 once=3 → 7? No: w=3+2=5, p=4+3=7)
    // unbounded([1,2,3],   [6,10,12], 5) → 30  (item 0, weight 1, profit 6 → take 5 times)
    // unbounded([1],       [10],      1) → 10
    // unbounded([3,4,5],   [4,5,6],   3) → 4

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
            take = profits[i] + helper(i, weights, profits, capacity - weights[i], dp); // stay at i
        }

        dp[i][capacity] = Math.max(skip, take);
        return dp[i][capacity];
    }

    // -------------------------------------------------------------------------
    // Approach 2 — Bottom-Up DP (Tabulation)
    // -------------------------------------------------------------------------
    //
    // State:      dp[i][w] = max value using first i items with capacity w
    // Transition: dp[i][w] = dp[i-1][w]                                      (skip)
    //             dp[i][w] = max(dp[i][w],
    //                            dp[i][w - weights[i-1]] + profits[i-1])      (take, if fits)
    //                            ↑ dp[i] not dp[i-1] — same row, allows reuse
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
                dp[i][w] = dp[i - 1][w];                                     // skip item i
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i][w - weights[i - 1]] + profits[i - 1]       // take item i (reuse)
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
    // Observation: dp[i][w] only depends on dp[i-1] (skip) and dp[i][...] (take),
    //              so we can compress to a single 1D array.
    //
    // State:      dp[w] = max value achievable with capacity w
    // Transition: dp[w] = max(dp[w], dp[w - weights[i]] + profits[i])
    // Base case:  dp[w] = 0 for all w  (no items processed yet)
    // Answer:     dp[capacity]
    //
    // Critical detail — iterate capacity LEFT TO RIGHT:
    //   Left-to-right means dp[w - weights[i]] already reflects item i being
    //   taken in the current pass — allowing the same item to be reused freely.
    //   This is exactly the unbounded constraint.
    //   (Right-to-left is correct for 0/1 Knapsack where reuse is forbidden.)
    //
    // Time : O(n * capacity)
    // Space: O(capacity)

    public static int knapsackOptimised(int[] weights, int[] profits, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weights.length; i++) {
            for (int w = weights[i]; w <= capacity; w++) { // left to right — critical
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
        System.out.println(knapsackTopDown(w1, v1, c1));  // Expected: 7  (w=2+3=5, p=3+4=7)
        System.out.println(knapsackTopDown(w2, v2, c2));  // Expected: 30 (item 0 x5: w=1*5, p=6*5)
        System.out.println(knapsackTopDown(w3, v3, c3));  // Expected: 10
        System.out.println(knapsackTopDown(w4, v4, c4));  // Expected: 4  (item 0 x1: w=3, p=4)

        System.out.println("--- Bottom-Up (Tabulation) ---");
        System.out.println(knapsackBottomUp(w1, v1, c1)); // Expected: 7
        System.out.println(knapsackBottomUp(w2, v2, c2)); // Expected: 30
        System.out.println(knapsackBottomUp(w3, v3, c3)); // Expected: 10
        System.out.println(knapsackBottomUp(w4, v4, c4)); // Expected: 4

        System.out.println("--- Space-Optimised (1D Array) ---");
        System.out.println(knapsackOptimised(w1, v1, c1)); // Expected: 7
        System.out.println(knapsackOptimised(w2, v2, c2)); // Expected: 30
        System.out.println(knapsackOptimised(w3, v3, c3)); // Expected: 10
        System.out.println(knapsackOptimised(w4, v4, c4)); // Expected: 4
    }
}