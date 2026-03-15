package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 118 - Pascal's Triangle
 *
 * Problem:
 *   Given an integer numRows, return the first numRows of Pascal's triangle.
 *   Each number is the sum of the two numbers directly above it.
 *   First and last element of every row is always 1.
 *
 * Approach: Row-by-row construction
 *   Row 0: [1]
 *   Row i: starts with 1, middle elements = prev[j-1] + prev[j], ends with 1.
 *
 * Example:
 *   numRows = 5
 *   Row 0: [1]
 *   Row 1: [1, 1]
 *   Row 2: [1, 2, 1]
 *   Row 3: [1, 3, 3, 1]
 *   Row 4: [1, 4, 6, 4, 1]
 *
 * Time  : O(n²)  — total elements across all rows = n*(n+1)/2
 * Space : O(n²)  — storing all rows
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        PascalsTriangle solution = new PascalsTriangle();

        // Test 1: standard case
        System.out.println("numRows = 5:");
        solution.generate(5).forEach(System.out::println);
        // Expected: [1] [1,1] [1,2,1] [1,3,3,1] [1,4,6,4,1]

        // Test 2: single row
        System.out.println("numRows = 1:");
        solution.generate(1).forEach(System.out::println);
        // Expected: [1]

        // Test 3: two rows
        System.out.println("numRows = 2:");
        solution.generate(2).forEach(System.out::println);
        // Expected: [1] [1,1]

        // Test 4: edge case — zero rows
        System.out.println("numRows = 0: " + solution.generate(0));
        // Expected: []
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);                                              // first and last always 1
                } else {
                    row.add(result.get(i - 1).get(j - 1) +
                            result.get(i - 1).get(j));                      // sum of two above
                }
            }
            result.add(row);
        }
        return result;
    }
}