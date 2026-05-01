package arrays;

/**
 * LeetCode 1582: Special Positions in a Binary Matrix
 * <p>
 * Given an m x n binary matrix mat, return the number of special positions in mat.
 * <p>
 * A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0.
 * <p>
 * Example 1:
 * Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
 * Output: 1
 * Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
 * <p>
 * Example 2:
 * Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 * Explanation: (0, 0), (1, 1) and (2, 2) are all special positions.
 * <p>
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * mat[i][j] is either 0 or 1.
 */
public class SpecialPositionsInBinaryMatrix {

    /**
     * Finds the number of special positions in a binary matrix using a two-pass approach.
     *
     * @param mat The input m x n binary matrix.
     * @return The number of special positions.
     */
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowSum = new int[m];
        int[] colSum = new int[n];

        // 1. First pass: Pre-calculate the sum of 1s in each row and each column.
        //    Store these sums in two separate arrays (rowSum and colSum).
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 1) {
                    rowSum[row]++;
                    colSum[col]++;
                }
            }
        }

        int specialPositions = 0;
        // 2. Second pass: Iterate through the matrix. A position (row, col) is special if:
        //    a) mat[row][col] is 1
        //    b) The sum of its row is 1 (meaning only this 1 exists in the row)
        //    c) The sum of its column is 1 (meaning only this 1 exists in the column)
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 1 && rowSum[row] == 1 && colSum[col] == 1) {
                    specialPositions++;
                }
            }
        }

        return specialPositions;
    }

    public static void main(String[] args) {
        SpecialPositionsInBinaryMatrix solution = new SpecialPositionsInBinaryMatrix();

        // Test Case 1
        int[][] mat1 = {
                {1, 0, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        System.out.println("Test Case 1: " + solution.numSpecial(mat1)); // Expected: 1

        // Test Case 2
        int[][] mat2 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Test Case 2: " + solution.numSpecial(mat2)); // Expected: 3

        // Test Case 3
        int[][] mat3 = {
                {0, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println("Test Case 3: " + solution.numSpecial(mat3)); // Expected: 2
    }
}
