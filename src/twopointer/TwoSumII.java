package twopointer;

import java.util.Arrays;

/**
 * LeetCode 167: Two Sum II - Input Array Is Sorted
 * <p>
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * <p>
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * <p>
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * <p>
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * <p>
 * Your solution must use only constant extra space.
 * <p>
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 */
public class TwoSumII {

    /**
     * Finds two numbers in a sorted array that add up to a target, using the two-pointer technique.
     * <p>
     * Since the array is sorted, we can use two pointers to efficiently find the pair.
     * - `left` pointer starts at the beginning of the array.
     * - `right` pointer starts at the end of the array.
     * <p>
     * We check the sum of the values at the `left` and `right` pointers:
     * - If the sum is less than the target, we need a larger sum, so we move the `left` pointer to the right.
     * - If the sum is greater than the target, we need a smaller sum, so we move the `right` pointer to the left.
     * - If the sum equals the target, we have found our solution.
     * <p>
     * This approach is highly efficient, running in O(n) time and O(1) space.
     *
     * @param numbers A 1-indexed sorted array of integers.
     * @param target  The target sum.
     * @return A 1-indexed array containing the indices of the two numbers.
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                // Found the pair, return 1-based indices
                return new int[]{left + 1, right + 1};
            }
        }
        // According to the problem, a solution always exists, so this is unreachable.
        // Included for completeness.
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSumII solution = new TwoSumII();

        // Test Case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1 (Input: " + Arrays.toString(nums1) + ", Target: " + target1 + "): " + Arrays.toString(solution.twoSum(nums1, target1))); // Expected: [1, 2]

        // Test Case 2
        int[] nums2 = {2, 3, 4};
        int target2 = 6;
        System.out.println("Test Case 2 (Input: " + Arrays.toString(nums2) + ", Target: " + target2 + "): " + Arrays.toString(solution.twoSum(nums2, target2))); // Expected: [1, 3]

        // Test Case 3
        int[] nums3 = {-1, 0};
        int target3 = -1;
        System.out.println("Test Case 3 (Input: " + Arrays.toString(nums3) + ", Target: " + target3 + "): " + Arrays.toString(solution.twoSum(nums3, target3))); // Expected: [1, 2]
    }
}
