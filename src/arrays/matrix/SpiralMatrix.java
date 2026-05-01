package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 54 - Spiral Matrix
 *
 * Given an m x n matrix, return all elements in spiral order.
 *
 * Approach: Direction-flip with shrinking row/col counts
 *   - Traverse horizontally (cols steps), then vertically (rows steps)
 *   - Shrink the dimension just traversed, flip direction
 *
 * Time Complexity:  O(m × n) — every element visited exactly once
 * Space Complexity: O(1)     — result list excluded
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int rows = matrix.length, cols = matrix[0].length;
        int row = 0, col = -1, dir = 1;

        while (rows > 0 && cols > 0) {
            // Traverse horizontally
            for (int j = 0; j < cols; j++) {
                col += dir;
                result.add(matrix[row][col]);
            }
            rows--;

            // Traverse vertically
            for (int i = 0; i < rows; i++) {
                row += dir;
                result.add(matrix[row][col]);
            }
            cols--;
            dir *= -1;                          // flip direction
        }
        return result;
    }

    public static void main(String[] args) {
        // Test case 1: 3×3 → [1,2,3,6,9,8,7,4,5]
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("3×3: " + spiralOrder(m1));

        // Test case 2: 3×4 → [1,2,3,4,8,12,11,10,9,5,6,7]
        int[][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println("3×4: " + spiralOrder(m2));

        // Test case 3: single row → [1,2,3]
        int[][] m3 = {{1, 2, 3}};
        System.out.println("1×3: " + spiralOrder(m3));

        // Test case 4: single column → [1,2,3]
        int[][] m4 = {{1}, {2}, {3}};
        System.out.println("3×1: " + spiralOrder(m4));
    }
}