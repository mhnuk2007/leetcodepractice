package arrays101;

import java.util.Arrays;

/**
 * LeetCode 27: Remove Element
 * <p>
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The relative order of the elements may be changed.
 * <p>
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Example 1:
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * <p>
 * Example 2:
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 */
public class RemoveElement {

    /**
     * Removes all instances of a given value in-place and returns the new length of the array.
     * <p>
     * This method uses a two-pointer approach.
     * - `insertIndex`: A slow pointer that keeps track of the position where the next element that is NOT `val` should be placed.
     * - `i`: A fast pointer that iterates through the entire array.
     * <p>
     * We iterate through the array with `i`. If `nums[i]` is not equal to `val`, we copy its value to `nums[insertIndex]`
     * and then increment `insertIndex`. This effectively overwrites the elements that are equal to `val` with
     * subsequent elements that are not.
     * <p>
     * The final value of `insertIndex` is the new length of the array without the specified value.
     * <p>
     * Time Complexity: O(n), as we iterate through the array once.
     * Space Complexity: O(1), as the modification is done in-place.
     *
     * @param nums The array to modify.
     * @param val  The value to remove.
     * @return The new length of the array (k).
     */
    public int removeElement(int[] nums, int val) {
        int insertIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public static void main(String[] args) {
        RemoveElement solution = new RemoveElement();

        // Test Case 1
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int k1 = solution.removeElement(nums1, val1);
        System.out.println("Test Case 1: k = " + k1 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums1, k1))); // Expected: k = 2, Nums: [2, 2]

        // Test Case 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int k2 = solution.removeElement(nums2, val2);
        System.out.println("Test Case 2: k = " + k2 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums2, k2))); // Expected: k = 5, Nums: [0, 1, 3, 0, 4] (order may vary)

        // Test Case 3: No elements to remove
        int[] nums3 = {1, 2, 3, 4, 5};
        int val3 = 6;
        int k3 = solution.removeElement(nums3, val3);
        System.out.println("Test Case 3: k = " + k3 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums3, k3))); // Expected: k = 5, Nums: [1, 2, 3, 4, 5]

        // Test Case 4: All elements to remove
        int[] nums4 = {3, 3, 3, 3};
        int val4 = 3;
        int k4 = solution.removeElement(nums4, val4);
        System.out.println("Test Case 4: k = " + k4 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums4, k4))); // Expected: k = 0, Nums: []
    }
}
