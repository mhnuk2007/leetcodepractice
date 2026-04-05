package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 *   Cache results by "row,col" key to avoid recomputation.
 *
 * Example:
 *   rowIndex = 4 → [1, 4, 6, 4, 1]
 *   rowIndex = 5 → [1, 5, 10, 10, 5, 1]
 *
 * Time  : O(n)   — iterative; O(n²) — recursive with memoization
 * Space : O(n)   — iterative; O(n²) — memo map
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
        long k = 1;
        for (int i = 0; i <= rowIndex; i++) {
            result.add((int) k);
            k = k * (rowIndex - i) / (i + 1);                             // next binomial coefficient
        }
        return result;
    }

    public static List<Integer> getRowRec(int rowIndex) {
        Map<String, Integer> memo = new HashMap<>();                        // shared across all cols
        List<Integer> result = new ArrayList<>(rowIndex + 1);
        for (int col = 0; col <= rowIndex; col++) {
            result.add(getRowHelper(rowIndex, col, memo));
        }
        return result;
    }

    private static int getRowHelper(int row, int col, Map<String, Integer> memo) {
        if (col == 0 || col == row) return 1;                             // edges always 1
        String key = row + "," + col;
        if (memo.containsKey(key)) return memo.get(key);                  // cache hit
        int result = getRowHelper(row - 1, col - 1, memo)
                + getRowHelper(row - 1, col, memo);                    // Pascal's identity
        memo.put(key, result);                                             // cache result
        return result;
    }
}