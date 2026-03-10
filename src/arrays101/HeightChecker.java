package arrays101;

import java.util.Arrays;

/**
 * LeetCode 1051: Height Checker
 * <p>
 * A school is trying to take an annual photo of all the students.
 * The students are asked to stand in a single file line in non-decreasing order by height.
 * Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
 * <p>
 * You are given an integer array heights representing the current order that the students are standing in.
 * Each heights[i] is the height of the ith student in line (0-indexed).
 * <p>
 * Return the number of indices where heights[i] != expected[i].
 * <p>
 * Example 1:
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation:
 * heights:  [1,1,4,2,1,3]
 * expected: [1,1,1,2,3,4]
 * Indices 2, 4, and 5 do not match.
 * <p>
 * Example 2:
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 */
public class HeightChecker {

    /**
     * Approach 1: Comparison with Sorted Array (O(n log n) time)
     * <p>
     * This is the most intuitive approach.
     * 1. Create a sorted copy of the `heights` array, which represents the `expected` order.
     * 2. Iterate through both arrays and count the number of indices where the elements do not match.
     *
     * @param heights The current order of students.
     * @return The number of mismatched indices.
     */
    public int heightChecker_sorting(int[] heights) {
        int[] expected = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expected);

        int mismatches = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                mismatches++;
            }
        }
        return mismatches;
    }

    /**
     * Approach 2: Counting Sort (O(n) time)
     * <p>
     * Since the heights are limited to a small range (1 to 100), we can use a counting sort approach for a more optimal solution.
     * 1. Create a frequency map (an array `counts`) to store the count of each height.
     * 2. Iterate through the original `heights` array. For each position `i`, determine what the expected height should be.
     *    We do this by finding the smallest height `currentHeight` that still has a non-zero count in our frequency map.
     * 3. Compare `heights[i]` with `currentHeight`. If they don't match, increment the mismatch counter.
     * 4. Decrement the count for `currentHeight` in the frequency map, as we have now "placed" one student of that height.
     *
     * @param heights The current order of students.
     * @return The number of mismatched indices.
     */
    public int heightChecker_counting(int[] heights) {
        int[] counts = new int[101]; // Heights are between 1 and 100
        for (int h : heights) {
            counts[h]++;
        }

        int mismatches = 0;
        int currentHeight = 1;

        for (int i = 0; i < heights.length; i++) {
            // Find the next available height in the sorted order
            while (counts[currentHeight] == 0) {
                currentHeight++;
            }

            // If the student at the current position doesn't have the expected height
            if (heights[i] != currentHeight) {
                mismatches++;
            }

            // Decrement the count for the current expected height, as we've "placed" this student
            counts[currentHeight]--;
        }
        return mismatches;
    }

    public static void main(String[] args) {
        HeightChecker solution = new HeightChecker();
        int[] heights1 = {1, 1, 4, 2, 1, 3};
        int[] heights2 = {5, 1, 2, 3, 4};

        System.out.println("--- Using Sorting Method ---");
        System.out.println("Test Case 1: " + solution.heightChecker_sorting(heights1)); // Expected: 3
        System.out.println("Test Case 2: " + solution.heightChecker_sorting(heights2)); // Expected: 5

        System.out.println("\n--- Using Counting Sort Method ---");
        System.out.println("Test Case 1: " + solution.heightChecker_counting(heights1)); // Expected: 3
        System.out.println("Test Case 2: " + solution.heightChecker_counting(heights2)); // Expected: 5
    }
}
