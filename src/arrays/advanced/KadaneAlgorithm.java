package arrays.advanced;

/**
 * LeetCode 53 — Maximum Subarray (Kadane's Algorithm)
 *
 * <p>Given an integer array nums, find the subarray with the largest sum,
 * and return its sum.
 *
 * <p><b>Approach — Kadane's Algorithm (Dynamic Programming):</b><br>
 * We iterate through the array, maintaining two variables:
 * 1. `currentSum`: The maximum subarray sum ending at the current index.
 *    For each element, we decide whether to add it to the existing subarray
 *    (`currentSum + nums[i]`) or start a new subarray from this element (`nums[i]`).
 * 2. `maxSum`: The overall maximum subarray sum seen so far.
 *
 * <p>Key Insight:
 * If `currentSum` becomes negative, starting a new subarray is always better,
 * as a negative prefix would only decrease the sum of any subsequent subarray.
 *
 * <p>Time Complexity  — O(N): where N is the length of nums. We do a single pass.
 * <p>Space Complexity — O(1): auxiliary space.
 *
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">LC 53</a>
 */
public class KadaneAlgorithm {

    public static int maxSubArraySum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        // Test 1: Standard case
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 1: " + maxSubArraySum(nums1)); // Expected: 6 (Subarray: [4, -1, 2, 1])

        // Test 2: Single element
        int[] nums2 = {1};
        System.out.println("Test 2: " + maxSubArraySum(nums2)); // Expected: 1

        // Test 3: All negative numbers
        int[] nums3 = {-5, -4, -3, -2, -1};
        System.out.println("Test 3: " + maxSubArraySum(nums3)); // Expected: -1

        // Test 4: Positive and negative values with large sums
        int[] nums4 = {5, 4, -1, 7, 8};
        System.out.println("Test 4: " + maxSubArraySum(nums4)); // Expected: 23

        // Test 5: Empty array
        int[] nums5 = {};
        System.out.println("Test 5: " + maxSubArraySum(nums5)); // Expected: 0
    }
}

