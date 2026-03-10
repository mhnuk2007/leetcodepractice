package arrays101;

import java.util.Arrays;

/**
 * LeetCode 941: Valid Mountain Array
 * <p>
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that arr is a mountain array if and only if:
 * - arr.length >= 3
 * - There exists some i with 0 < i < arr.length - 1 such that:
 *   - arr[0] < arr[1] < ... < arr[i-1] < arr[i]
 *   - arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * <p>
 * Example 1:
 * Input: arr = [2,1]
 * Output: false
 * <p>
 * Example 2:
 * Input: arr = [3,5,5]
 * Output: false
 * <p>
 * Example 3:
 * Input: arr = [0,3,2,1]
 * Output: true
 */
public class ValidMountainArray {

    /**
     * Checks if an array is a valid mountain array using a one-pass "walk" approach.
     * <p>
     * The algorithm simulates walking up the mountain and then walking down.
     * 1. Walk up: Iterate from the beginning as long as the array is strictly increasing.
     * 2. Peak check: After the "walk up" loop, the pointer `i` should not be at the beginning (no uphill part)
     *    or at the end (no downhill part). If it is, the array is not a valid mountain.
     * 3. Walk down: Continue iterating from the peak as long as the array is strictly decreasing.
     * 4. Final check: If the pointer `i` has successfully reached the end of the array, it means
     *    the array consists of a single uphill climb followed by a single downhill climb,
     *    which defines a valid mountain array.
     *
     * @param arr The input array of integers.
     * @return true if the array is a valid mountain array, false otherwise.
     */
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }

        int i = 0;

        // 1. Walk up the mountain
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // 2. Check if the peak is valid (not the first or last element)
        if (i == 0 || i == n - 1) {
            return false;
        }

        // 3. Walk down the mountain
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        // 4. Check if we reached the end of the array
        return i == n - 1;
    }

    public static void main(String[] args) {
        ValidMountainArray solution = new ValidMountainArray();

        // Test Case 1
        int[] arr1 = {2, 1};
        System.out.println("Test Case 1 (Input: " + Arrays.toString(arr1) + "): " + solution.validMountainArray(arr1)); // Expected: false

        // Test Case 2
        int[] arr2 = {3, 5, 5};
        System.out.println("Test Case 2 (Input: " + Arrays.toString(arr2) + "): " + solution.validMountainArray(arr2)); // Expected: false

        // Test Case 3
        int[] arr3 = {0, 3, 2, 1};
        System.out.println("Test Case 3 (Input: " + Arrays.toString(arr3) + "): " + solution.validMountainArray(arr3)); // Expected: true

        // Test Case 4: No downhill
        int[] arr4 = {0, 1, 2, 3};
        System.out.println("Test Case 4 (Input: " + Arrays.toString(arr4) + "): " + solution.validMountainArray(arr4)); // Expected: false

        // Test Case 5: No uphill
        int[] arr5 = {3, 2, 1, 0};
        System.out.println("Test Case 5 (Input: " + Arrays.toString(arr5) + "): " + solution.validMountainArray(arr5)); // Expected: false
    }
}
