package arrays101;

import java.util.Arrays;

/**
 * LeetCode 1089: Duplicate Zeros
 * <p>
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
 * <p>
 * Note that elements beyond the length of the original array are not written.
 * Do the above modifications to the input array in place and do not return anything.
 * <p>
 * Example 1:
 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * <p>
 * Example 2:
 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 */
public class DuplicateZeros {

    /**
     * Approach 1: Using an auxiliary array (O(n) space).
     * This is a straightforward approach where we build the result in a temporary array
     * and then copy it back to the original array.
     *
     * @param arr The array to modify.
     */
    public void duplicateZeros_extraSpace(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        int j = 0; // Pointer for the temp array

        for (int i = 0; i < n && j < n; i++) {
            if (arr[i] == 0) {
                temp[j++] = 0;
                if (j < n) {
                    temp[j++] = 0;
                }
            } else {
                temp[j++] = arr[i];
            }
        }

        // Copy the result from temp back to the original array
        System.arraycopy(temp, 0, arr, 0, n);
    }

    /**
     * Approach 2: In-place modification (O(1) space).
     * This is the optimal approach, which uses a two-pass strategy.
     * <p>
     * Pass 1: Count the number of zeros that will be duplicated (`possibleDups`).
     * This helps determine how many elements will be shifted out of the array's bounds.
     * We iterate from left to right, stopping when we've accounted for all positions in the final array.
     * <p>
     * Pass 2: Iterate backwards from the last valid element.
     * We copy elements from their original position to their new, shifted position.
     * When we encounter a zero, we duplicate it. Working backwards prevents us from overwriting
     * elements before we have read them.
     *
     * @param arr The array to modify.
     */
    public void duplicateZeros_inPlace(int[] arr) {
        int n = arr.length;
        int possibleDups = 0;
        int lastIndex = n - 1;

        // Pass 1: Find the number of zeros to duplicate and the last element to consider.
        for (int i = 0; i <= lastIndex - possibleDups; i++) {
            if (arr[i] == 0) {
                // Edge case: If the last element to be considered is a zero,
                // it can't be duplicated as there's no space. We just copy it once.
                if (i == lastIndex - possibleDups) {
                    arr[lastIndex] = 0;
                    lastIndex--;
                    break;
                }
                possibleDups++;
            }
        }

        // Pass 2: Iterate backwards to shift and duplicate.
        int last = lastIndex - possibleDups;
        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + possibleDups] = 0;
                possibleDups--;
                arr[i + possibleDups] = 0;
            } else {
                arr[i + possibleDups] = arr[i];
            }
        }
    }

    public static void main(String[] args) {
        DuplicateZeros solution = new DuplicateZeros();

        // --- Test with Extra Space method ---
        System.out.println("--- Testing with Extra Space ---");
        int[] arr1_extra = {1, 0, 2, 3, 0, 4, 5, 0};
        solution.duplicateZeros_extraSpace(arr1_extra);
        System.out.println("Test Case 1: " + Arrays.toString(arr1_extra)); // Expected: [1,0,0,2,3,0,0,4]

        int[] arr2_extra = {1, 2, 3};
        solution.duplicateZeros_extraSpace(arr2_extra);
        System.out.println("Test Case 2: " + Arrays.toString(arr2_extra)); // Expected: [1,2,3]

        // --- Test with In-Place method ---
        System.out.println("\n--- Testing In-Place ---");
        int[] arr1_inplace = {1, 0, 2, 3, 0, 4, 5, 0};
        solution.duplicateZeros_inPlace(arr1_inplace);
        System.out.println("Test Case 1: " + Arrays.toString(arr1_inplace)); // Expected: [1,0,0,2,3,0,0,4]

        int[] arr2_inplace = {1, 2, 3};
        solution.duplicateZeros_inPlace(arr2_inplace);
        System.out.println("Test Case 2: " + Arrays.toString(arr2_inplace)); // Expected: [1,2,3]

        int[] arr3_inplace = {8, 4, 5, 0, 0, 0, 0, 7};
        solution.duplicateZeros_inPlace(arr3_inplace);
        System.out.println("Test Case 3: " + Arrays.toString(arr3_inplace)); // Expected: [8,4,5,0,0,0,0,0]
    }
}
