package dp;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberMap {

    /**
     * Returns maximum amount that can be robbed without robbing adjacent houses.
     *
     * Approach 1: Top-down DP (memoization with HashMap), right to left.
     * Approach 2: Bottom-up DP (tabulation with HashMap), left to right.
     * Time: O(n), Space: O(n)
     *
     * rob([1,2,3,1])   → 4
     * rob([2,7,9,3,1]) → 12
     * rob([1])         → 1
     * rob([2,1])       → 2
     */

    // Top-down
    public static int robTopDown(int[] nums) {
        return helper(nums.length - 1, nums, new HashMap<>());
    }

    private static int helper(int i, int[] nums, Map<Integer, Integer> memo) {
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);  // bug 1 fix
        if (memo.containsKey(i)) return memo.get(i);    // bug 2 fix: key is i not nums[i]
        int result = Math.max(helper(i - 1, nums, memo), nums[i] + helper(i - 2, nums, memo));
        memo.put(i, result);
        return result;
    }

    // Bottom-up
    public static int robBottomUp(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        // State:      dp[i] = max money from houses 0..i
        // Transition: dp[i] = max(dp[i-1], nums[i] + dp[i-2])
        // Base case:  dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
        // Answer:     dp[n-1]

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, nums[0]);
        dp.put(1, Math.max(nums[0], nums[1]));          // bug 3 fix

        for (int i = 2; i < n; i++) {
            if(dp.containsKey(i)) return dp.get(i);
            dp.put(i, Math.max(dp.get(i - 1), nums[i] + dp.get(i - 2)));
        }

        return dp.get(n - 1);
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