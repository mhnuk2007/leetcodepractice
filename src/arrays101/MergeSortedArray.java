package arrays101;

import java.util.Arrays;

/**
 * LeetCode 88: Merge Sorted Array
 * <p>
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * <p>
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * <p>
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * <p>
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 */
public class MergeSortedArray {

    /**
     * Merges two sorted arrays into the first array in-place, using a three-pointer approach.
     * <p>
     * The key insight is to merge from the end of the arrays to the beginning. This avoids overwriting
     * elements in `nums1` that we still need to compare.
     * <p>
     * We use three pointers:
     * - `p1`: Points to the last valid element in `nums1` (at index `m-1`).
     * - `p2`: Points to the last element in `nums2` (at index `n-1`).
     * - `p`: Points to the last position in the merged `nums1` array (at index `m+n-1`).
     * <p>
     * We compare the elements at `p1` and `p2` and place the larger one at position `p`.
     * We then decrement the pointer of the larger element and the `p` pointer.
     * This process continues until we have processed all elements from `nums2`.
     * <p>
     * Time Complexity: O(m + n), as we iterate through both lists once.
     * Space Complexity: O(1), as we are modifying the array in-place.
     *
     * @param nums1 The first sorted array, with space for the merged result.
     * @param m     The number of initialized elements in nums1.
     * @param nums2 The second sorted array.
     * @param n     The number of elements in nums2.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // Pointer for last element of initial nums1
        int p2 = n - 1; // Pointer for last element of nums2
        int p = m + n - 1; // Pointer for last element of merged nums1

        // Iterate backwards from the end of the arrays
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }

    public static void main(String[] args) {
        MergeSortedArray solution = new MergeSortedArray();

        // Test Case 1: Standard case
        int[] nums1_case1 = {1, 2, 3, 0, 0, 0};
        int[] nums2_case1 = {2, 5, 6};
        solution.merge(nums1_case1, 3, nums2_case1, 3);
        System.out.println("Test Case 1: " + Arrays.toString(nums1_case1)); // Expected: [1, 2, 2, 3, 5, 6]

        // Test Case 2: nums2 is empty
        int[] nums1_case2 = {1};
        int[] nums2_case2 = {};
        solution.merge(nums1_case2, 1, nums2_case2, 0);
        System.out.println("Test Case 2: " + Arrays.toString(nums1_case2)); // Expected: [1]

        // Test Case 3: nums1 is empty
        int[] nums1_case3 = {0, 0, 0};
        int[] nums2_case3 = {1, 2, 3};
        solution.merge(nums1_case3, 0, nums2_case3, 3);
        System.out.println("Test Case 3: " + Arrays.toString(nums1_case3)); // Expected: [1, 2, 3]
    }
}
