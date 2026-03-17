package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 119 - Pascal's Triangle II
 *
 * Given an integer rowIndex, return the rowIndex-th (0-indexed) row
 * of Pascal's triangle.
 *
 * Approach: Iterative binomial coefficient
 *   C(n, i) = C(n, i-1) * (n - i + 1) / i
 *
 * Time Complexity:  O(n) — single pass over rowIndex elements
 * Space Complexity: O(n) — result list only, no auxiliary storage
 */
public class PascalsTriangleII {

    public static List<Integer> getRow(int rowIndex) {
            List<Integer> result = new ArrayList<>(rowIndex + 1);
            long k = 1;
            for (int i = 0; i <= rowIndex; i++) {
                result.add((int) k);
                k = k * (rowIndex - i) / (i + 1); // compute next element
            }
            return result;
        }


    public static void main(String[] args) {
        // Test case 1: rowIndex = 0 → [1]
        System.out.println("Row 0: " + getRow(0));

        // Test case 2: rowIndex = 1 → [1, 1]
        System.out.println("Row 1: " + getRow(1));

        // Test case 3: rowIndex = 4 → [1, 4, 6, 4, 1]
        System.out.println("Row 4: " + getRow(4));

        // Test case 4: rowIndex = 5 → [1, 5, 10, 10, 5, 1]
        System.out.println("Row 5: " + getRow(5));
    }
}