package dp;

/**
 * LeetCode 416 - Partition Equal Subset Sum
 *
 * <p>Given an integer array nums, return true if you can partition the array into two subsets
 * such that the sum of the elements in both subsets is equal, or false otherwise.
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - 0/1 Knapsack recursive with cache</li>
 *   <li>Bottom-Up DP (2D Table) - 0/1 Knapsack iterative</li>
 *   <li>Bottom-Up DP (1D Optimized) - Space-optimized with right-to-left traversal</li>
 * </ol>
 *
 * <p>Key Insight: The problem reduces to finding a subset with sum == total/2.
 * This is the classic 0/1 Knapsack decision problem where each element can be
 * used at most once.
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class PartitionEqualSubSetSum {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(n * target)
    // Space : O(n * target) — memo table + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Determines if the array can be partitioned using top-down memoized recursion.
     *
     * @param nums input array of positive integers
     * @return true if equal partition is possible, false otherwise
     */
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        Boolean[][] dp = new Boolean[nums.length][target + 1];
        return helper(nums, 0, target, dp);
    }

    /**
     * Recursive helper with memoization.
     *
     * @param nums   input array
     * @param i      current index
     * @param target remaining sum to achieve
     * @param dp     memoization table (Boolean to distinguish null from false)
     * @return true if subset sum == target is achievable from index i onward
     */
    private static boolean helper(int[] nums, int i, int target, Boolean[][] dp) {
        if (target == 0) return true;
        if (i == nums.length || target < 0) return false;
        if (dp[i][target] != null) return dp[i][target];
        if (nums[i] <= target && helper(nums, i + 1, target - nums[i], dp)) {
            return dp[i][target] = true;
        }
        return dp[i][target] = helper(nums, i + 1, target, dp);
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (2D Table)
    // Time  : O(n * target)
    // Space : O(n * target)
    // -------------------------------------------------------------------------

    /**
     * Determines if the array can be partitioned using a bottom-up 2D DP table.
     *
     * <p>dp[i][j] = true if a subset sum of j is achievable using elements nums[0..i-1].
     * Recurrence: dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i-1]]  (if j >= nums[i-1])
     *
     * @param nums input array of positive integers
     * @return true if equal partition is possible, false otherwise
     */
    public static boolean canPartition2(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base case: sum of 0 is always achievable (pick no elements)
        for (int i = 0; i <= n; i++) dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean skip = dp[i - 1][j];
                boolean take = (j >= nums[i - 1]) && dp[i - 1][j - nums[i - 1]];
                dp[i][j] = skip || take;
            }
        }
        return dp[n][target];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (1D Space-Optimized)
    // Time  : O(n * target)
    // Space : O(target) — single boolean array
    // -------------------------------------------------------------------------

    /**
     * Determines if the array can be partitioned using a space-optimized 1D DP array.
     *
     * <p>Right-to-left traversal of j prevents reuse of the same element within
     * a single pass, preserving the 0/1 knapsack constraint.
     *
     * @param nums input array of positive integers
     * @return true if equal partition is possible, false otherwise
     */
    public static boolean canPartition3(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[target]) return true; // early exit
        }
        return dp[target];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: Expected true  ([1,5] and [11,5] → both sum to 11... wait, [1,5,5]=11, [11]=11)
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Test 1 - Top-Down   : " + canPartition(nums1));  // true
        System.out.println("Test 1 - 2D Bottom-Up: " + canPartition2(nums1)); // true
        System.out.println("Test 1 - 1D Optimized: " + canPartition3(nums1)); // true

        System.out.println();

        // Test Case 2: Expected false (odd total is impossible)
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Test 2 - Top-Down   : " + canPartition(nums2));  // false
        System.out.println("Test 2 - 2D Bottom-Up: " + canPartition2(nums2)); // false
        System.out.println("Test 2 - 1D Optimized: " + canPartition3(nums2)); // false

        System.out.println();

        // Test Case 3: Single element — cannot partition
        int[] nums3 = {1};
        System.out.println("Test 3 - Top-Down   : " + canPartition(nums3));  // false
        System.out.println("Test 3 - 2D Bottom-Up: " + canPartition2(nums3)); // false
        System.out.println("Test 3 - 1D Optimized: " + canPartition3(nums3)); // false

        System.out.println();

        // Test Case 4: Two equal elements — trivially partitionable
        int[] nums4 = {1, 1};
        System.out.println("Test 4 - Top-Down   : " + canPartition(nums4));  // true
        System.out.println("Test 4 - 2D Bottom-Up: " + canPartition2(nums4)); // true
        System.out.println("Test 4 - 1D Optimized: " + canPartition3(nums4)); // true
    }
}