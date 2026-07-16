package arrays.matrix;

import java.util.Arrays;

/**
 * LeetCode 73 — Set Matrix Zeroes
 *
 * <p>Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in-place.
 *
 * <p><b>Approach — In-Place State Markers with O(1) Space:</b><br>
 * 1. Use the first row and first column of the matrix itself to store the state of whether
 *    that row or column should be set to 0.
 * 2. Use two boolean flags, `flagr` and `flagc`, to track if the first row and first column
 *    themselves initially contain any 0's.
 * 3. Iterate through the rest of the matrix (from row 1, col 1) and if `matrix[i][j] == 0`,
 *    mark `matrix[i][0] = 0` and `matrix[0][j] = 0`.
 * 4. Iterate again through the rest of the matrix and update elements to 0 if their row
 *    or column marker is 0.
 * 5. Finally, update the first row and first column using the flags `flagr` and `flagc`.
 *
 * <p>Time Complexity  — O(M * N): where M is the number of rows and N is the number of columns.
 * <p>Space Complexity — O(1): auxiliary space.
 *
 * @see <a href="https://leetcode.com/problems/set-matrix-zeroes/">LC 73</a>
 */
public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagr = false;
        boolean flagc = false;

        // Check if first row has any zeroes
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagr = true;
                break;
            }
        }

        // Check if first column has any zeroes
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagc = true;
                break;
            }
        }

        // Use first row and first column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set zeroes based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out the first row if needed
        if (flagr) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero out the first column if needed
        if (flagc) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        // Test 1: Standard case with zeroes in middle
        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(matrix1);
        System.out.println("Test 1: " + Arrays.deepToString(matrix1));
        // Expected: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]

        // Test 2: Zeroes in first row and column
        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes(matrix2);
        System.out.println("Test 2: " + Arrays.deepToString(matrix2));
        // Expected: [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]

        // Test 3: No zeroes
        int[][] matrix3 = {
                {1, 2},
                {3, 4}
        };
        setZeroes(matrix3);
        System.out.println("Test 3: " + Arrays.deepToString(matrix3));
        // Expected: [[1, 2], [3, 4]]
    }
}
