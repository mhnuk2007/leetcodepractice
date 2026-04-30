package dp;

/**
 * LeetCode 740 - Delete and Earn
 *
 * <p>Given an integer array nums, you can delete any element and earn points
 * equal to its value. Deleting a value forces deletion of all occurrences of
 * value-1 and value+1. Return the maximum points you can earn.
 *
 * <p>Key Reduction (House Robber on points array):
 * <pre>
 *   points[i] = i * count(i)  — total gain from taking all elements of value i
 *   Taking value i blocks value i-1 and i+1 → identical to House Robber
 * </pre>
 *
 * <p>Pattern: House Robber variant
 * <ul>
 *   <li>State      : dp[i] = max points using values 0..i</li>
 *   <li>Transition : dp[i] = max(dp[i-1], dp[i-2] + points[i])</li>
 *   <li>Base case  : dp[0] = points[0], dp[1] = max(points[0], points[1])</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - recursive with cache on points array</li>
 *   <li>Bottom-Up DP (1D Table)   - iterative House Robber on points array</li>
 *   <li>Bottom-Up DP (O(1) Space) - two variables instead of full dp array</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class DeleteAndEarn {

    // -------------------------------------------------------------------------
    // Shared helper — builds points array from nums
    // points[i] = i * frequency(i) = total gain from taking all elements of value i
    // -------------------------------------------------------------------------

    private static int[] buildPoints(int[] nums) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);
        int[] points = new int[maxVal + 1];
        for (int num : nums) points[num] += num;
        return points;
    }

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(n + maxVal)
    // Space : O(maxVal) — memo array + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum points using top-down memoized recursion on the
     * points array — equivalent to House Robber (top-down).
     *
     * @param nums input array of positive integers
     * @return maximum points earnable
     */
    public static int deleteAndEarn(int[] nums) {
        int[] points = buildPoints(nums);
        int[] memo = new int[points.length];
        java.util.Arrays.fill(memo, -1);
        return helper(points, points.length - 1, memo);
    }

    /**
     * Recursive helper — returns max points using values 0..i.
     *
     * @param points precomputed points array
     * @param i      current value index
     * @param memo   memoization array
     * @return max points from values 0..i
     */
    private static int helper(int[] points, int i, int[] memo) {
        if (i < 0) return 0;
        if (i == 0) return points[0];
        if (memo[i] != -1) return memo[i];
        int skip = helper(points, i - 1, memo);
        int take = points[i] + helper(points, i - 2, memo);
        return memo[i] = Math.max(skip, take);
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (1D Table)
    // Time  : O(n + maxVal)
    // Space : O(maxVal)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum points using a bottom-up 1D DP array — House Robber
     * applied directly to the points array.
     *
     * <p>dp[i] = max points achievable considering values 0..i.
     * Recurrence: dp[i] = max(dp[i-1], dp[i-2] + points[i])
     *
     * @param nums input array of positive integers
     * @return maximum points earnable
     */
    public static int deleteAndEarn2(int[] nums) {
        int[] points = buildPoints(nums);
        int maxVal = points.length - 1;
        int[] dp = new int[maxVal + 1];
        dp[0] = points[0];
        if (maxVal >= 1) dp[1] = Math.max(points[0], points[1]);
        for (int i = 2; i <= maxVal; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + points[i]);
        }
        return dp[maxVal];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (O(1) Space)
    // Time  : O(n + maxVal)
    // Space : O(maxVal) for points array, O(1) extra DP space
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum points using two variables instead of a full dp array.
     *
     * <p>Since dp[i] only depends on dp[i-1] and dp[i-2], the entire array
     * can be replaced with two rolling variables: prev2 and prev1.
     *
     * @param nums input array of positive integers
     * @return maximum points earnable
     */
    public static int deleteAndEarn3(int[] nums) {
        int[] points = buildPoints(nums);
        int maxVal = points.length - 1;
        if (maxVal == 0) return points[0];
        int prev2 = points[0];
        int prev1 = Math.max(points[0], points[1]);
        for (int i = 2; i <= maxVal; i++) {
            int curr = Math.max(prev1, prev2 + points[i]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: [3,4,2] → take 3 and 2 (delete 4) → 3+2=5... or take 4 → 4
        // Actually take 4 deletes 3, so best is take 3+2=5? No: take 4 alone=4, take 3+2=5
        int[] nums1 = {3, 4, 2};
        System.out.println("Test 1 - Top-Down   : " + deleteAndEarn(nums1));  // 6
        System.out.println("Test 1 - 1D Bottom-Up: " + deleteAndEarn2(nums1)); // 6
        System.out.println("Test 1 - O(1) Space  : " + deleteAndEarn3(nums1)); // 6

        System.out.println();

        // Test Case 2: [2,2,3,3,3,4] → points[2]=4, points[3]=9, points[4]=4
        // Take 3 (skip 2,4) → 9, or take 2+4=8. Best: 9+0=9? or 4+4=8. Answer: 9
        int[] nums2 = {2, 2, 3, 3, 3, 4};
        System.out.println("Test 2 - Top-Down   : " + deleteAndEarn(nums2));  // 9
        System.out.println("Test 2 - 1D Bottom-Up: " + deleteAndEarn2(nums2)); // 9
        System.out.println("Test 2 - O(1) Space  : " + deleteAndEarn3(nums2)); // 9

        System.out.println();

        // Test Case 3: [2,2,2,3,3,3,4] → points[2]=6, points[3]=9, points[4]=4
        // Take 2+4=10 or take 3=9. Best: 10
        int[] nums3 = {2, 2, 2, 3, 3, 3, 4};
        System.out.println("Test 3 - Top-Down   : " + deleteAndEarn(nums3));  // 10
        System.out.println("Test 3 - 1D Bottom-Up: " + deleteAndEarn2(nums3)); // 10
        System.out.println("Test 3 - O(1) Space  : " + deleteAndEarn3(nums3)); // 10

        System.out.println();

        // Test Case 4: [1,1,1,2,4,5,5,5,6]
        // points[1]=3, points[2]=2, points[4]=4, points[5]=15, points[6]=6
        // Take 1+4+15=20? 1 blocks 2, 5 blocks 4 and 6 → 3+4+15=22
        int[] nums4 = {1, 1, 1, 2, 4, 5, 5, 5, 6};
        System.out.println("Test 4 - Top-Down   : " + deleteAndEarn(nums4));  // 22
        System.out.println("Test 4 - 1D Bottom-Up: " + deleteAndEarn2(nums4)); // 22
        System.out.println("Test 4 - O(1) Space  : " + deleteAndEarn3(nums4)); // 22
    }
}