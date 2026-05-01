package dp;

/**
 * LeetCode 494 - Target Sum
 *
 * <p>Given an integer array nums and an integer target, assign either '+' or '-'
 * to each element and return the number of ways to reach the target sum.
 *
 * <p>Key Reduction (0/1 Knapsack — Count Ways):
 * <pre>
 *   Let P = subset assigned '+', N = subset assigned '-'
 *   P - N = target
 *   P + N = total
 *   Solving: P = (target + total) / 2
 * </pre>
 * <p>The problem reduces to: count subsets of nums with sum == P.
 * Valid only when (target + total) is even and |target| <= total.
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - recursive with cache</li>
 *   <li>Bottom-Up DP (2D Table) - iterative tabulation</li>
 *   <li>Bottom-Up DP (1D Optimized) - space-optimized with right-to-left traversal</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class TargetSum {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(n * subSetSum)
    // Space : O(n * subSetSum) — memo table + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the number of ways to assign +/- signs to reach target using
     * top-down memoized recursion.
     *
     * @param nums   input array of non-negative integers
     * @param target the desired sum
     * @return number of valid sign assignments
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) total += num;
        if ((target + total) % 2 != 0 || Math.abs(target) > total) return 0;
        int subSetSum = (target + total) / 2;
        if (subSetSum < 0) return 0;
        Integer[][] dp = new Integer[nums.length][subSetSum + 1];
        return helper(nums, 0, subSetSum, dp);
    }

    /**
     * Recursive helper — counts subsets of nums[i..] that sum to remaining.
     *
     * @param nums      input array
     * @param i         current index
     * @param remaining remaining sum to achieve
     * @param dp        memoization table
     * @return count of valid subsets
     */
    private static int helper(int[] nums, int i, int remaining, Integer[][] dp) {
        if (remaining == 0 && i == nums.length) return 1;
        if (i == nums.length || remaining < 0) return 0;
        if (dp[i][remaining] != null) return dp[i][remaining];
        int skip = helper(nums, i + 1, remaining, dp);
        int take = (nums[i] <= remaining) ? helper(nums, i + 1, remaining - nums[i], dp) : 0;
        return dp[i][remaining] = skip + take;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (2D Table)
    // Time  : O(n * subSetSum)
    // Space : O(n * subSetSum)
    // -------------------------------------------------------------------------

    /**
     * Returns the number of ways to assign +/- signs to reach target using
     * a bottom-up 2D DP table.
     *
     * <p>dp[i][j] = number of ways to achieve sum j using first i elements.
     * Recurrence: dp[i][j] = dp[i-1][j]                          (skip)
     *                       + dp[i-1][j - nums[i-1]]              (take, if fits)
     *
     * @param nums   input array of non-negative integers
     * @param target the desired sum
     * @return number of valid sign assignments
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        int total = 0;
        for (int num : nums) total += num;
        if ((target + total) % 2 != 0 || Math.abs(target) > total) return 0;
        int subSetSum = (target + total) / 2;
        if (subSetSum < 0) return 0;
        int n = nums.length;
        int[][] dp = new int[n + 1][subSetSum + 1];

        // Base case: one way to achieve sum 0 — pick nothing
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= subSetSum; j++) {
                dp[i][j] = dp[i - 1][j];                          // skip nums[i-1]
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];       // take nums[i-1]
                }
            }
        }
        return dp[n][subSetSum];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (1D Space-Optimized)
    // Time  : O(n * subSetSum)
    // Space : O(subSetSum) — single int array
    // -------------------------------------------------------------------------

    /**
     * Returns the number of ways to assign +/- signs to reach target using
     * a space-optimized 1D DP array.
     *
     * <p>Right-to-left traversal prevents reuse of the same element within
     * a single pass, preserving the 0/1 knapsack constraint.
     * dp[j] accumulates the count of subsets summing to j.
     *
     * @param nums   input array of non-negative integers
     * @param target the desired sum
     * @return number of valid sign assignments
     */
    public static int findTargetSumWays3(int[] nums, int target) {
        int total = 0;
        for (int num : nums) total += num;
        if ((target + total) % 2 != 0 || Math.abs(target) > total) return 0;
        int subSetSum = (target + total) / 2;
        if (subSetSum < 0) return 0;
        int[] dp = new int[subSetSum + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = subSetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[subSetSum];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: Classic example — 5 ways to reach target 3
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Test 1 - Top-Down   : " + findTargetSumWays(nums1, target1));  // 5
        System.out.println("Test 1 - 2D Bottom-Up: " + findTargetSumWays2(nums1, target1)); // 5
        System.out.println("Test 1 - 1D Optimized: " + findTargetSumWays3(nums1, target1)); // 5

        System.out.println();

        // Test Case 2: Single element equals target
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Test 2 - Top-Down   : " + findTargetSumWays(nums2, target2));  // 1
        System.out.println("Test 2 - 2D Bottom-Up: " + findTargetSumWays2(nums2, target2)); // 1
        System.out.println("Test 2 - 1D Optimized: " + findTargetSumWays3(nums2, target2)); // 1

        System.out.println();

        // Test Case 3: Target unreachable — |target| > total
        int[] nums3 = {1, 2};
        int target3 = 4;
        System.out.println("Test 3 - Top-Down   : " + findTargetSumWays(nums3, target3));  // 0
        System.out.println("Test 3 - 2D Bottom-Up: " + findTargetSumWays2(nums3, target3)); // 0
        System.out.println("Test 3 - 1D Optimized: " + findTargetSumWays3(nums3, target3)); // 0

        System.out.println();

        // Test Case 4: Odd sum check — (target + total) is odd, no valid split
        int[] nums4 = {1, 2};
        int target4 = 0;
        System.out.println("Test 4 - Top-Down   : " + findTargetSumWays(nums4, target4));  // 0
        System.out.println("Test 4 - 2D Bottom-Up: " + findTargetSumWays2(nums4, target4)); // 0
        System.out.println("Test 4 - 1D Optimized: " + findTargetSumWays3(nums4, target4)); // 0

        System.out.println();

        // Test Case 5: Negative subSetSum guard — target drives sum negative
        int[] nums5 = {1};
        int target5 = -2;
        System.out.println("Test 5 - Top-Down   : " + findTargetSumWays(nums5, target5));  // 0
        System.out.println("Test 5 - 2D Bottom-Up: " + findTargetSumWays2(nums5, target5)); // 0
        System.out.println("Test 5 - 1D Optimized: " + findTargetSumWays3(nums5, target5)); // 0
    }
}