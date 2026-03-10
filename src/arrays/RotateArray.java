package arrays;

import java.util.Arrays;

/**
 * LeetCode 189: Rotate Array
 * <p>
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 */
public class RotateArray {

    /**
     * Rotates an array to the right by k steps in-place.
     * <p>
     * This optimal approach uses a three-step reversal algorithm:
     * 1. Reverse the entire array. This places the elements that should be at the beginning
     *    (the last k elements) at the start, but in reverse order.
     * 2. Reverse the first k elements. This corrects their order.
     * 3. Reverse the remaining n-k elements. This corrects their order as well.
     * <p>
     * This method achieves rotation in O(n) time and O(1) space.
     *
     * @param nums The array to be rotated.
     * @param k    The number of steps to rotate to the right.
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k % nums.length == 0) {
            return;
        }
        int n = nums.length;
        k = k % n; // Handle cases where k is greater than the array length

        // Step 1: Reverse the entire array
        reverse(nums, 0, n - 1);
        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1);
        // Step 3: Reverse the remaining n-k elements
        reverse(nums, k, n - 1);
    }

    /**
     * Helper method to reverse a portion of an array in-place.
     *
     * @param nums  The array to modify.
     * @param start The starting index (inclusive).
     * @param end   The ending index (inclusive).
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        RotateArray solution = new RotateArray();

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("Original Array 1: " + Arrays.toString(nums1));
        solution.rotate(nums1, k1);
        System.out.println("Rotated by " + k1 + ": " + Arrays.toString(nums1)); // Expected: [5, 6, 7, 1, 2, 3, 4]
        System.out.println("--------------------");

        // Test Case 2
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;
        System.out.println("Original Array 2: " + Arrays.toString(nums2));
        solution.rotate(nums2, k2);
        System.out.println("Rotated by " + k2 + ": " + Arrays.toString(nums2)); // Expected: [3, 99, -1, -100]
        System.out.println("--------------------");

        // Test Case 3: k > n
        int[] nums3 = {1, 2};
        int k3 = 3;
        System.out.println("Original Array 3: " + Arrays.toString(nums3));
        solution.rotate(nums3, k3);
        System.out.println("Rotated by " + k3 + ": " + Arrays.toString(nums3)); // Expected: [2, 1]
    }
}
