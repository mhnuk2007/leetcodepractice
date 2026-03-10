package arrays101;

import java.util.Arrays;

/**
 * LeetCode 26: Remove Duplicates from Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place
 * such that each unique element appears only once. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * <p>
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 */
public class RemoveDuplicates {

    /**
     * Removes duplicates from a sorted array in-place using a two-pointer technique.
     * <p>
     * The approach uses a "slow" pointer (`insertIndex`) and a "fast" pointer (`i`).
     * - `insertIndex`: Points to the position where the next unique element should be placed.
     * - `i`: Iterates through the entire array to find unique elements.
     * <p>
     * The `insertIndex` starts at 1 (since the first element is always unique). The fast pointer `i` also starts at 1.
     * We iterate through the array with `i`. If we find an element `nums[i]` that is different from the previous one `nums[i-1]`,
     * it means we have found a new unique element. We then place this unique element at `nums[insertIndex]` and increment `insertIndex`.
     * <p>
     * The final value of `insertIndex` will be the count of unique elements.
     * <p>
     * Time Complexity: O(n), as we iterate through the array once.
     * Space Complexity: O(1), as we modify the array in-place.
     *
     * @param nums The sorted input array with duplicates.
     * @return The number of unique elements (k).
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int insertIndex = 1; // Pointer for the position of the next unique element
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the previous one, it's unique
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();

        // Test Case 1
        int[] nums1 = {1, 1, 2};
        int k1 = solution.removeDuplicates(nums1);
        System.out.println("Test Case 1: k = " + k1 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums1, k1))); // Expected: k = 2, Nums: [1, 2]

        // Test Case 2
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = solution.removeDuplicates(nums2);
        System.out.println("Test Case 2: k = " + k2 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums2, k2))); // Expected: k = 5, Nums: [0, 1, 2, 3, 4]

        // Test Case 3: No duplicates
        int[] nums3 = {1, 2, 3, 4, 5};
        int k3 = solution.removeDuplicates(nums3);
        System.out.println("Test Case 3: k = " + k3 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums3, k3))); // Expected: k = 5, Nums: [1, 2, 3, 4, 5]

        // Test Case 4: All duplicates
        int[] nums4 = {1, 1, 1, 1, 1};
        int k4 = solution.removeDuplicates(nums4);
        System.out.println("Test Case 4: k = " + k4 + ", Nums: " + Arrays.toString(Arrays.copyOf(nums4, k4))); // Expected: k = 1, Nums: [1]
    }
}
