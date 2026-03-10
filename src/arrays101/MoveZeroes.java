package arrays101;

import java.util.Arrays;

/**
 * LeetCode 283: Move Zeroes
 * <p>
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class MoveZeroes {

    /**
     * Moves all 0's to the end of the array while maintaining the relative order of the non-zero elements.
     * This is done in-place using a two-pointer approach.
     * <p>
     * The `left` pointer keeps track of the position where the next non-zero element should be placed.
     * The `right` pointer iterates through the array.
     * <p>
     * When `right` encounters a non-zero element, it swaps it with the element at `left`,
     * effectively moving the non-zero element to its correct position and incrementing `left`.
     * If `right` encounters a zero, `left` remains stationary, waiting for the next non-zero element.
     *
     * @param nums The array to modify.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int left = 0; // Pointer for the position to place the next non-zero element
        int right = 0; // Pointer to iterate through the array

        while (right < nums.length) {
            if (nums[right] != 0) {
                // If the element at 'right' is non-zero, swap it with the element at 'left'
                // This effectively moves non-zero elements to the front
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++; // Advance 'left' pointer as a non-zero element has been placed
            }
            right++; // Always advance 'right' pointer
        }
    }

    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();

        // Test Case 1
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.println("Original Array 1: " + Arrays.toString(nums1));
        solution.moveZeroes(nums1);
        System.out.println("Processed Array 1: " + Arrays.toString(nums1)); // Expected: [1, 3, 12, 0, 0]
        System.out.println("--------------------");

        // Test Case 2
        int[] nums2 = {0, 0, 1};
        System.out.println("Original Array 2: " + Arrays.toString(nums2));
        solution.moveZeroes(nums2);
        System.out.println("Processed Array 2: " + Arrays.toString(nums2)); // Expected: [1, 0, 0]
        System.out.println("--------------------");

        // Test Case 3
        int[] nums3 = {1, 0};
        System.out.println("Original Array 3: " + Arrays.toString(nums3));
        solution.moveZeroes(nums3);
        System.out.println("Processed Array 3: " + Arrays.toString(nums3)); // Expected: [1, 0]
        System.out.println("--------------------");

        // Test Case 4
        int[] nums4 = {1, 2, 3};
        System.out.println("Original Array 4: " + Arrays.toString(nums4));
        solution.moveZeroes(nums4);
        System.out.println("Processed Array 4: " + Arrays.toString(nums4)); // Expected: [1, 2, 3]
        System.out.println("--------------------");

        // Test Case 5
        int[] nums5 = {0};
        System.out.println("Original Array 5: " + Arrays.toString(nums5));
        solution.moveZeroes(nums5);
        System.out.println("Processed Array 5: " + Arrays.toString(nums5)); // Expected: [0]
        System.out.println("--------------------");
    }
}
