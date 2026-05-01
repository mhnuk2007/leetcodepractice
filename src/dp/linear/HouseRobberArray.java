package dp;

import java.util.Arrays;

public class HouseRobberArray {

    /**
     * Returns maximum amount that can be robbed without robbing adjacent houses.
     *
     * Approach 1: Top-down DP (memoization with int[] cache), right to left.
     * Approach 2: Bottom-up DP (tabulation with int[] table), left to right.
     * Time: O(n), Space: O(n)
     *
     * rob([1,2,3,1])   → 4
     * rob([2,7,9,3,1]) → 12
     * rob([1])         → 1
     * rob([2,1])       → 2
     */

    // Top-down
    public static int robTopDown(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return helper(nums.length - 1, nums, dp);
    }

    private static int helper(int i, int[] nums, int[] dp) {
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);
        if (dp[i] != -1) return dp[i];
        dp[i] = Math.max(helper(i - 1, nums, dp), nums[i] + helper(i - 2, nums, dp));
        return dp[i];
    }

    // Bottom-up
    public static int robBottomUp(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        // State:      dp[i] = max money from houses 0..i
        // Transition: dp[i] = max(dp[i-1], nums[i] + dp[i-2])
        // Base case:  dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
        // Answer:     dp[n-1]

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(robTopDown(new int[]{1, 2, 3, 1}));    // Expected: 4
        System.out.println(robTopDown(new int[]{2, 7, 9, 3, 1})); // Expected: 12
        System.out.println(robTopDown(new int[]{1}));              // Expected: 1
        System.out.println(robTopDown(new int[]{2, 1}));           // Expected: 2

        System.out.println(robBottomUp(new int[]{1, 2, 3, 1}));    // Expected: 4
        System.out.println(robBottomUp(new int[]{2, 7, 9, 3, 1})); // Expected: 12
        System.out.println(robBottomUp(new int[]{1}));              // Expected: 1
        System.out.println(robBottomUp(new int[]{2, 1}));           // Expected: 2
    }
}