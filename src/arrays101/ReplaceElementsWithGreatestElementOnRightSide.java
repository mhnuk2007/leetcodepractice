package arrays101;

import java.util.Arrays;

/**
 * LeetCode 1299: Replace Elements with Greatest Element on Right Side
 * <p>
 * Given an array arr, replace every element in that array with the greatest element among the elements to its right,
 * and replace the last element with -1.
 * <p>
 * After doing so, return the array.
 * <p>
 * Example 1:
 * Input: arr = [17,18,5,4,6,1]
 * Output: [18,6,6,6,1,-1]
 * <p>
 * Example 2:
 * Input: arr = [400]
 * Output: [-1]
 */
public class ReplaceElementsWithGreatestElementOnRightSide {

    /**
     * Replaces each element with the greatest element to its right in a single pass.
     * <p>
     * This optimal approach iterates through the array from right to left.
     * It maintains a variable `maxFromRight` which stores the maximum value encountered so far during the traversal.
     * <p>
     * For each element `arr[i]`:
     * 1. We store the original value of `arr[i]` in a temporary variable `temp`.
     * 2. We replace `arr[i]` with the current `maxFromRight`.
     * 3. We update `maxFromRight` to be the maximum of its current value and `temp`.
     * <p>
     * This ensures that each element is replaced by the maximum of all elements to its right.
     * The initial value of `maxFromRight` is -1, which correctly handles the replacement for the last element.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param arr The input array to be modified.
     * @return The modified array.
     */
    public int[] replaceElements(int[] arr) {
        int maxFromRight = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i]; // Store the current element
            arr[i] = maxFromRight; // Replace with the max found so far
            maxFromRight = Math.max(maxFromRight, temp); // Update the max for the next iteration
        }
        return arr;
    }

    public static void main(String[] args) {
        ReplaceElementsWithGreatestElementOnRightSide solution = new ReplaceElementsWithGreatestElementOnRightSide();

        // Test Case 1
        int[] arr1 = {17, 18, 5, 4, 6, 1};
        System.out.println("Test Case 1 Input: " + Arrays.toString(arr1));
        solution.replaceElements(arr1);
        System.out.println("Test Case 1 Output: " + Arrays.toString(arr1)); // Expected: [18, 6, 6, 6, 1, -1]
        System.out.println("--------------------");

        // Test Case 2
        int[] arr2 = {400};
        System.out.println("Test Case 2 Input: " + Arrays.toString(arr2));
        solution.replaceElements(arr2);
        System.out.println("Test Case 2 Output: " + Arrays.toString(arr2)); // Expected: [-1]
    }
}
