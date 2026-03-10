package arrays101;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1346: Check If N and Its Double Exist
 * <p>
 * Given an array arr of integers, check if there exist two indices i and j such that:
 * - i != j
 * - 0 <= i, j < arr.length
 * - arr[i] == 2 * arr[j]
 * <p>
 * Example 1:
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: N = 10 is the double of M = 5, that is, 10 = 2 * 5.
 * <p>
 * Example 2:
 * Input: arr = [7,1,14,11]
 * Output: true
 * Explanation: N = 14 is the double of M = 7, that is, 14 = 2 * 7.
 * <p>
 * Example 3:
 * Input: arr = [3,1,7,11]
 * Output: false
 * Explanation: In this case does not exist N and M, such that N = 2 * M.
 */
public class CheckIfNAndItsDoubleExist {

    /**
     * Checks if there exist two distinct indices i and j such that arr[i] == 2 * arr[j].
     * <p>
     * This approach uses a HashSet for efficient lookups. It iterates through the array, and for each number `num`,
     * it checks if its double (`num * 2`) or its half (`num / 2`) already exists in the set.
     * <p>
     * The check for `num / 2` must also ensure that `num` is an even number (`num % 2 == 0`),
     * otherwise, an integer division like 7/2 would incorrectly match with 3.
     * <p>
     * By adding numbers to the set after checking, we ensure that we don't match a number with itself.
     *
     * @param arr The input array of integers.
     * @return true if such a pair exists, false otherwise.
     */
    public boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            // Check if the double of the current number has been seen
            // OR if the half of the current number has been seen (and num is even)
            if (seen.contains(num * 2) || (num % 2 == 0 && seen.contains(num / 2))) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        CheckIfNAndItsDoubleExist solution = new CheckIfNAndItsDoubleExist();

        // Test Case 1
        int[] arr1 = {10, 2, 5, 3};
        System.out.println("Test Case 1 (Input: [10,2,5,3]): " + solution.checkIfExist(arr1)); // Expected: true

        // Test Case 2
        int[] arr2 = {7, 1, 14, 11};
        System.out.println("Test Case 2 (Input: [7,1,14,11]): " + solution.checkIfExist(arr2)); // Expected: true

        // Test Case 3
        int[] arr3 = {3, 1, 7, 11};
        System.out.println("Test Case 3 (Input: [3,1,7,11]): " + solution.checkIfExist(arr3)); // Expected: false

        // Test Case 4: With zero
        int[] arr4 = {0, 0};
        System.out.println("Test Case 4 (Input: [0,0]): " + solution.checkIfExist(arr4)); // Expected: true
    }
}
