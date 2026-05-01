package arrays;

import java.util.Arrays;

/**
 * LeetCode 1480: Running Sum of 1d Array
 *
 * Given an array nums, return the running sum of nums.
 * The running sum at index i is the sum of nums[0] through nums[i].
 */
public class RunningSum1d {

    /**
     * Computes the running sum in-place using a single pass.
     *
     * @param nums Input array
     * @return Array where each element is the running sum up to that index
     */
    public int[] runningSum(int[] nums) {
        if (nums == null) {
            return null;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum;
        }
        return nums;
    }

    public static void main(String[] args) {
        RunningSum1d solution = new RunningSum1d();

        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Test Case 1: " + Arrays.toString(solution.runningSum(nums1)));
        // Expected: [1, 3, 6, 10]

        int[] nums2 = {1, 1, 1, 1, 1};
        System.out.println("Test Case 2: " + Arrays.toString(solution.runningSum(nums2)));
        // Expected: [1, 2, 3, 4, 5]

        int[] nums3 = {3, 1, 2, 10, 1};
        System.out.println("Test Case 3: " + Arrays.toString(solution.runningSum(nums3)));
        // Expected: [3, 4, 6, 16, 17]
    }
}
