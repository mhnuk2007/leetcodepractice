package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 119 - Pascal's Triangle II
 *
 * Problem:
 *   Given an integer rowIndex, return the rowIndex-th (0-indexed) row
 *   of Pascal's triangle.
 *
 * Approach 1: Iterative binomial coefficient
 *   C(n, i) = C(n, i-1) * (n-i+1) / i
 *   Compute each element from the previous in a single pass.
 *
 * Approach 2: Recursive Pascal's identity with memoization
 *   C(n, k) = C(n-1, k-1) + C(n-1, k)
 *   Base case: col == 0 or col == row → return 1
 *   Cache results in Integer[][] to avoid recomputation.
 *
 * Example:
 *   rowIndex = 4 → [1, 4, 6, 4, 1]
 *   rowIndex = 5 → [1, 5, 10, 10, 5, 1]
 *
 * Time  : O(n)  — iterative; O(n²) — recursive with memoization
 * Space : O(n)  — iterative; O(n²) — memo array
 */
public class PascalsTriangleII {

    public static void main(String[] args) {
        // Test 1: zero index
        System.out.println("Row 0 iterative: " + getRow(0));
        // Expected: [1]

        // Test 2: single pair
        System.out.println("Row 1 iterative: " + getRow(1));
        // Expected: [1, 1]

        // Test 3: standard case
        System.out.println("Row 4 iterative: " + getRow(4));
        // Expected: [1, 4, 6, 4, 1]

        // Test 4: standard case
        System.out.println("Row 5 iterative: " + getRow(5));
        // Expected: [1, 5, 10, 10, 5, 1]

        // Test 5: recursive with memoization
        System.out.println("Row 5 recursive: " + getRowRec(5));
        // Expected: [1, 5, 10, 10, 5, 1]
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>(rowIndex + 1);
        long value = 1;
        for (int i = 0; i <= rowIndex; i++) {
            result.add((int) value);
            value = value * (rowIndex - i) / (i + 1);                     // next binomial coefficient
        }
        return result;
    }

    public static List<Integer> getRowRec(int rowIndex) {
        Integer[][] memo = new Integer[rowIndex + 1][rowIndex + 1];
        List<Integer> result = new ArrayList<>();
        for (int col = 0; col <= rowIndex; col++) {
            result.add(combination(rowIndex, col, memo));                  // compute each element
        }
        return result;
    }

    private static int combination(int row, int col, Integer[][] memo) {
        if (col == 0 || col == row) return 1;                             // edges always 1
        if (memo[row][col] != null) return memo[row][col];                // cache hit
        memo[row][col] = combination(row - 1, col - 1, memo)
                + combination(row - 1, col, memo);                 // Pascal's identity
        return memo[row][col];                                             // cache and return
    }
}