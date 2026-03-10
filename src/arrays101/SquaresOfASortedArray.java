package arrays101;

import java.util.Arrays;

/**
 * LeetCode 977: Squares of a Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order,
 * return an array of the squares of each number sorted in non-decreasing order.
 * <p>
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100]. After sorting, it becomes [0,1,9,16,100].
 * <p>
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class SquaresOfASortedArray {

    /**
     * Returns an array of the squares of each number from a sorted input array, in sorted order.
     * <p>
     * This optimal approach uses a two-pointer technique to build the result array in O(n) time.
     * Since the original array is sorted, the largest squared values will be at the ends of the array
     * (due to negative numbers becoming positive when squared).
     * <p>
     * We use two pointers:
     * - `left`: Starts at the beginning of the input array.
     * - `right`: Starts at the end of the input array.
     * <p>
     * We compare the absolute values of the elements at `left` and `right`. The larger squared value
     * is placed at the end of the `result` array, and the corresponding pointer is moved inwards.
     * We fill the `result` array from right to left.
     *
     * @param nums A sorted array of integers.
     * @return A new array containing the squares of the input numbers, sorted.
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1; // Pointer to fill the result array from the end

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        return result;
    }

    public static void main(String[] args) {
        SquaresOfASortedArray solution = new SquaresOfASortedArray();

        // Test Case 1
        int[] nums1 = {-4, -1, 0, 3, 10};
        System.out.println("Test Case 1 Input: " + Arrays.toString(nums1));
        int[] result1 = solution.sortedSquares(nums1);
        System.out.println("Test Case 1 Output: " + Arrays.toString(result1)); // Expected: [0, 1, 9, 16, 100]
        System.out.println("--------------------");

        // Test Case 2
        int[] nums2 = {-7, -3, 2, 3, 11};
        System.out.println("Test Case 2 Input: " + Arrays.toString(nums2));
        int[] result2 = solution.sortedSquares(nums2);
        System.out.println("Test Case 2 Output: " + Arrays.toString(result2)); // Expected: [4, 9, 9, 49, 121]
    }
}
