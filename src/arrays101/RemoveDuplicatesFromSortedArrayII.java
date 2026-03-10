package arrays101;

import java.util.Arrays;

/**
 * LeetCode 80: Remove Duplicates from Sorted Array II
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place
 * such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * <p>
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 9, nums = [0,0,1,1,2,2,3,3,4,_]
 */
public class RemoveDuplicatesFromSortedArrayII {

    /**
     * Removes duplicates from a sorted array such that each unique element appears at most twice.
     * <p>
     * This method uses an efficient two-pointer approach.
     * - `insertIndex`: A slow pointer that points to the position where the next valid element should be placed.
     * - `i`: A fast pointer that iterates through the array.
     * <p>
     * The core idea is that an element `nums[i]` is valid and should be kept if it is greater than
     * the element at `insertIndex - 2`. This condition elegantly handles the "at most twice" rule.
     * The first two elements are always valid, so we start `insertIndex` at 2.
     * <p>
     * We iterate from the third element (`i = 2`). If `nums[i]` is different from `nums[insertIndex - 2]`,
     * it means we are not introducing a third duplicate, so we place `nums[i]` at `nums[insertIndex]` and advance `insertIndex`.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums The sorted input array.
     * @return The number of elements after removal (k).
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int insertIndex = 2; // Start from the third position
        for (int i = 2; i < nums.length; i++) {
            // If the current element is not the same as the one two positions before the insertIndex,
            // it's safe to include it.
            if (nums[i] != nums[insertIndex - 2]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArrayII solution = new RemoveDuplicatesFromSortedArrayII();

        // Test Case 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        System.out.println("Test Case 1 Input: " + Arrays.toString(nums1));
        int k1 = solution.removeDuplicates(nums1);
        System.out.println("Test Case 1 Output: k = " + k1 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums1, k1))); // Expected: k = 5, Nums: [1, 1, 2, 2, 3]
        System.out.println("--------------------");

        // Test Case 2
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Test Case 2 Input: " + Arrays.toString(nums2));
        int k2 = solution.removeDuplicates(nums2);
        System.out.println("Test Case 2 Output: k = " + k2 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums2, k2))); // Expected: k = 9, Nums: [0, 0, 1, 1, 2, 2, 3, 3, 4]
        System.out.println("--------------------");

        // Test Case 3: No duplicates to remove
        int[] nums3 = {1, 2, 3};
        System.out.println("Test Case 3 Input: " + Arrays.toString(nums3));
        int k3 = solution.removeDuplicates(nums3);
        System.out.println("Test Case 3 Output: k = " + k3 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums3, k3))); // Expected: k = 3, Nums: [1, 2, 3]
    }
}
