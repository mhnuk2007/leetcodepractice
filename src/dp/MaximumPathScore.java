package dp;

import java.util.Arrays;

/**
 * LeetCode 3742 - Maximum Path Score in a Grid
 *
 * <p>Given an m x n grid where each cell contains 0, 1, or 2, and an integer k.
 * Move only right or down from (0,0) to (m-1, n-1). Each cell contributes:
 * <ul>
 *   <li>0 -> score +0, cost +0</li>
 *   <li>1 -> score +1, cost +1</li>
 *   <li>2 -> score +2, cost +1</li>
 * </ul>
 * Return the maximum score with total cost <= k, or -1 if no valid path exists.
 *
 * <p>Pattern: 3D DP (grid path + cost budget)
 * <ul>
 *   <li>State      : dp[i][j][c] = max score at cell (i,j) with cost c spent</li>
 *   <li>Transition : dp[i][j][c] = grid[i][j] + max(dp[i-1][j][c-cost], dp[i][j-1][c-cost])</li>
 *   <li>Base case  : starting cell dp[0][0][startCost] = grid[0][0]</li>
 *   <li>Answer     : max over dp[m-1][n-1][0..k], or -1 if all unreachable</li>
 * </ul>
 *
 * <p>Sentinel strategy: -2 marks unvisited in Approach 1 (int[][][]).
 * Unreachable states (Integer.MIN_VALUE) are not cached to avoid collision.
 * Approaches 2 and 3 use -1 as the unreachable sentinel.
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - recursive with (i, j, newCost) cache</li>
 *   <li>Bottom-Up DP (3D Table)   - iterative tabulation</li>
 *   <li>Bottom-Up DP (2D Optimized) - two row slices instead of full 3D table</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class MaximumPathScore {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(m * n * k)
    // Space : O(m * n * k) -- memo table + O(m + n) recursion stack
    // -------------------------------------------------------------------------

    public static int maxPathScore(int[][] grid, int k) {
        int[][][] dp = new int[grid.length][grid[0].length][k + 1];
        for (int[][] surface : dp)
            for (int[] row : surface)
                Arrays.fill(row, -2);
        int result = helper(grid, k, 0, 0, 0, dp);
        return result == Integer.MIN_VALUE ? -1 : result;
    }

    private static int helper(int[][] grid, int k, int i, int j, int cost, int[][][] dp) {
        if (i >= grid.length || j >= grid[0].length) return Integer.MIN_VALUE;
        int newCost = cost + (grid[i][j] != 0 ? 1 : 0);
        if (newCost > k) return Integer.MIN_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        if (dp[i][j][newCost] != -2) return dp[i][j][newCost];
        int goRight = helper(grid, k, i, j + 1, newCost, dp);
        int goDown  = helper(grid, k, i + 1, j, newCost, dp);
        int best = Math.max(goRight, goDown);
        if (best == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return dp[i][j][newCost] = grid[i][j] + best;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (3D Table)
    // Time  : O(m * n * k)
    // Space : O(m * n * k)
    // -------------------------------------------------------------------------

    public static int maxPathScore2(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][k + 1];
        for (int[][] surface : dp)
            for (int[] row : surface)
                Arrays.fill(row, -1);

        int startCost = (grid[0][0] != 0) ? 1 : 0;
        if (startCost <= k) dp[0][0][startCost] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int cellCost  = (grid[i][j] != 0) ? 1 : 0;
                int cellScore = grid[i][j];
                for (int c = cellCost; c <= k; c++) {
                    int best = -1;
                    if (i > 0 && dp[i - 1][j][c - cellCost] != -1)
                        best = Math.max(best, dp[i - 1][j][c - cellCost]);
                    if (j > 0 && dp[i][j - 1][c - cellCost] != -1)
                        best = Math.max(best, dp[i][j - 1][c - cellCost]);
                    if (best != -1) dp[i][j][c] = best + cellScore;
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++)
            if (dp[m - 1][n - 1][c] != -1)
                ans = Math.max(ans, dp[m - 1][n - 1][c]);
        return ans;
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (2D Space-Optimized)
    // Time  : O(m * n * k)
    // Space : O(n * k) -- two row slices instead of full 3D table
    // -------------------------------------------------------------------------

    public static int maxPathScore3(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] prev = new int[n][k + 1];
        int[][] curr = new int[n][k + 1];
        for (int[] row : prev) Arrays.fill(row, -1);
        for (int[] row : curr) Arrays.fill(row, -1);

        int startCost = (grid[0][0] != 0) ? 1 : 0;
        if (startCost <= k) prev[0][startCost] = grid[0][0];

        for (int i = 0; i < m; i++) {
            if (i > 0) for (int[] row : curr) Arrays.fill(row, -1);
            for (int j = (i == 0 ? 1 : 0); j < n; j++) {
                int cellCost  = (grid[i][j] != 0) ? 1 : 0;
                int cellScore = grid[i][j];
                int[][] slice = (i == 0) ? prev : curr;
                for (int c = cellCost; c <= k; c++) {
                    int best = -1;
                    if (i > 0 && prev[j][c - cellCost] != -1)
                        best = Math.max(best, prev[j][c - cellCost]);
                    if (j > 0 && slice[j - 1][c - cellCost] != -1)
                        best = Math.max(best, slice[j - 1][c - cellCost]);
                    if (best != -1) slice[j][c] = best + cellScore;
                }
            }
            if (i > 0) {
                int[][] temp = prev;
                prev = curr;
                curr = temp;
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++)
            if (prev[n - 1][c] != -1)
                ans = Math.max(ans, prev[n - 1][c]);
        return ans;
    }

    // -------------------------------------------------------------------------
    // Main -- Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        int[][] grid1 = {{0, 1}, {2, 0}};
        System.out.println("Test 1 - Top-Down   : " + maxPathScore(grid1, 1));  // 2
        System.out.println("Test 1 - 3D Bottom-Up: " + maxPathScore2(grid1, 1)); // 2
        System.out.println("Test 1 - 2D Optimized: " + maxPathScore3(grid1, 1)); // 2
        System.out.println();

        int[][] grid2 = {{0, 1}, {1, 2}};
        System.out.println("Test 2 - Top-Down   : " + maxPathScore(grid2, 2));  // 3
        System.out.println("Test 2 - 3D Bottom-Up: " + maxPathScore2(grid2, 2)); // 3
        System.out.println("Test 2 - 2D Optimized: " + maxPathScore3(grid2, 2)); // 3
        System.out.println();

        int[][] grid3 = {{0, 1}, {1, 2}};
        System.out.println("Test 3 - Top-Down   : " + maxPathScore(grid3, 1));  // -1
        System.out.println("Test 3 - 3D Bottom-Up: " + maxPathScore2(grid3, 1)); // -1
        System.out.println("Test 3 - 2D Optimized: " + maxPathScore3(grid3, 1)); // -1
        System.out.println();

        int[][] grid4 = {{2, 2}, {2, 2}};
        System.out.println("Test 4 - Top-Down   : " + maxPathScore(grid4, 3));  // 6
        System.out.println("Test 4 - 3D Bottom-Up: " + maxPathScore2(grid4, 3)); // 6
        System.out.println("Test 4 - 2D Optimized: " + maxPathScore3(grid4, 3)); // 6
        System.out.println();

        int[][] grid5 = {{2}};
        System.out.println("Test 5 - Top-Down   : " + maxPathScore(grid5, 1));  // 2
        System.out.println("Test 5 - 3D Bottom-Up: " + maxPathScore2(grid5, 1)); // 2
        System.out.println("Test 5 - 2D Optimized: " + maxPathScore3(grid5, 1)); // 2
        System.out.println();

        int[][] grid6 = {{2}};
        System.out.println("Test 6 - Top-Down   : " + maxPathScore(grid6, 0));  // -1
        System.out.println("Test 6 - 3D Bottom-Up: " + maxPathScore2(grid6, 0)); // -1
        System.out.println("Test 6 - 2D Optimized: " + maxPathScore3(grid6, 0)); // -1
    }
}