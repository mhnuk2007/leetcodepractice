package arrays;

import java.util.Arrays;

/**
 * LeetCode 747: Largest Number At Least Twice of Others
 * <p>
 * You are given an integer array nums where the largest integer is unique.
 * <p>
 * Determine whether the largest element in the array is at least twice as much as every other number in the array.
 * If it is, return the index of the largest element. Otherwise, return -1.
 * <p>
 * Example 1:
 * Input: nums = [3,6,1,0]
 * Output: 1
 * Explanation: 6 is the largest integer. For every other number x, 6 is more than twice as big as x.
 * The index of value 6 is 1, so we return 1.
 */
public class LargestNumberTwiceOthers {

    /**
     * Determines if the largest element is at least twice as much as every other number.
     * <p>
     * This optimal method finds the largest and second largest elements, and the index of the
     * largest element, all in a single pass.
     * <p>
     * 1. Initialize `max` and `secondMax` to the smallest possible value, and `maxIndex` to -1.
     * 2. Iterate through the array:
     *    - If the current number `nums[i]` is greater than `max`, it becomes the new max. The old `max`
     *      becomes the new `secondMax`, and we record the current index `i` as `maxIndex`.
     *    - Otherwise, if `nums[i]` is greater than `secondMax`, it simply becomes the new `secondMax`.
     * 3. After the loop, we check if `max >= 2 * secondMax`. If this condition holds, it means the largest
     *    element is dominant, and we return its stored index.
     *
     * @param nums The input array of integers.
     * @return The index of the largest element if the condition is met, otherwise -1.
     */
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int max = -1;
        int secondMax = -1;
        int maxIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }

        return (max >= secondMax * 2) ? maxIndex : -1;
    }

    public static void main(String[] args) {
        LargestNumberTwiceOthers solution = new LargestNumberTwiceOthers();

        // Test 1: dominant element exists
        int[] nums1 = {3, 6, 1, 0};
        System.out.println("Test 1 (Input: " + Arrays.toString(nums1) + "): " + solution.dominantIndex(nums1)); // Expected: 1

        // Test 2: no dominant element
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test 2 (Input: " + Arrays.toString(nums2) + "): " + solution.dominantIndex(nums2)); // Expected: -1

        // Test 3: dominant element at index 0
        int[] nums3 = {10, 2, 3, 1};
        System.out.println("Test 3 (Input: " + Arrays.toString(nums3) + "): " + solution.dominantIndex(nums3)); // Expected: 0

        // Test 4: single element — always dominant
        int[] nums4 = {1};
        System.out.println("Test 4 (Input: " + Arrays.toString(nums4) + "): " + solution.dominantIndex(nums4)); // Expected: 0

        // Test 5: exactly twice — boundary condition
        int[] nums5 = {0, 0, 2, 1};
        System.out.println("Test 5 (Input: " + Arrays.toString(nums5) + "): " + solution.dominantIndex(nums5)); // Expected: 2
    }
}
