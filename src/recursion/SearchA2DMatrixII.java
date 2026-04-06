package recursion;

/**
 * LeetCode 240 - Search a 2D Matrix II
 *
 * Problem:
 *   Given an m x n matrix where:
 *     - Each row is sorted in ascending order (left to right).
 *     - Each column is sorted in ascending order (top to bottom).
 *   Determine if a given target value exists in the matrix.
 *
 * Approach 1: Recursive (top-right corner search)
 *   Start at the top-right corner (row=0, col=n-1).
 *   - If matrix[i][j] == target → found.
 *   - If matrix[i][j] >  target → move left  (j--), eliminating this column.
 *   - If matrix[i][j] <  target → move down  (i++), eliminating this row.
 *   Recurse until out of bounds or target found.
 *
 * Approach 2: Iterative (same top-right corner logic)
 *   Same elimination strategy as the recursive approach,
 *   implemented with a while loop instead of call stack.
 *   Preferred in practice — avoids recursion overhead and stack-overflow
 *   risk on very large matrices.
 *
 * Example 1:
 *   matrix = [[ 1, 4, 7,11,15],     target = 5  →  true
 *             [ 2, 5, 8,12,19],
 *             [ 3, 6, 9,16,22],
 *             [10,13,14,17,24],
 *             [18,21,23,26,30]]
 *
 * Example 2:
 *   Same matrix, target = 20  →  false
 *
 * Time  : O(m + n) — at most m + n steps; each step eliminates a row or column
 * Space : O(m + n) recursive (call stack depth), O(1) iterative
 */
public class SearchA2DMatrixII {

    // ------------------------------------------------------------------ main

    public static void main(String[] args) {

        int[][] matrix = {
                { 1,  4,  7, 11, 15},
                { 2,  5,  8, 12, 19},
                { 3,  6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        // --- Test 1: target present (interior element) ---
        System.out.println("searchMatrix    (target=5):  " + searchMatrix(matrix, 5));
        System.out.println("searchMatrixIter(target=5):  " + searchMatrixIter(matrix, 5));
        // Expected: true, true

        // --- Test 2: target not present ---
        System.out.println("searchMatrix    (target=20): " + searchMatrix(matrix, 20));
        System.out.println("searchMatrixIter(target=20): " + searchMatrixIter(matrix, 20));
        // Expected: false, false

        // --- Test 3: target is the minimum (top-left corner) ---
        System.out.println("searchMatrix    (target=1):  " + searchMatrix(matrix, 1));
        System.out.println("searchMatrixIter(target=1):  " + searchMatrixIter(matrix, 1));
        // Expected: true, true

        // --- Test 4: target is the maximum (bottom-right corner) ---
        System.out.println("searchMatrix    (target=30): " + searchMatrix(matrix, 30));
        System.out.println("searchMatrixIter(target=30): " + searchMatrixIter(matrix, 30));
        // Expected: true, true

        // --- Test 5: target smaller than all elements ---
        System.out.println("searchMatrix    (target=0):  " + searchMatrix(matrix, 0));
        System.out.println("searchMatrixIter(target=0):  " + searchMatrixIter(matrix, 0));
        // Expected: false, false

        // --- Test 6: target larger than all elements ---
        System.out.println("searchMatrix    (target=31): " + searchMatrix(matrix, 31));
        System.out.println("searchMatrixIter(target=31): " + searchMatrixIter(matrix, 31));
        // Expected: false, false

        // --- Test 7: 1x1 matrix, target matches ---
        int[][] single = {{7}};
        System.out.println("searchMatrix    (1x1, hit):  " + searchMatrix(single, 7));
        System.out.println("searchMatrixIter(1x1, hit):  " + searchMatrixIter(single, 7));
        // Expected: true, true

        // --- Test 8: 1x1 matrix, target missing ---
        System.out.println("searchMatrix    (1x1, miss): " + searchMatrix(single, 3));
        System.out.println("searchMatrixIter(1x1, miss): " + searchMatrixIter(single, 3));
        // Expected: false, false

        // --- Test 9: single-row matrix ---
        int[][] oneRow = {{1, 3, 5, 7, 9}};
        System.out.println("searchMatrix    (1-row, 5):  " + searchMatrix(oneRow, 5));
        System.out.println("searchMatrixIter(1-row, 5):  " + searchMatrixIter(oneRow, 5));
        // Expected: true, true

        // --- Test 10: single-column matrix ---
        int[][] oneCol = {{2}, {4}, {6}, {8}};
        System.out.println("searchMatrix    (1-col, 6):  " + searchMatrix(oneCol, 6));
        System.out.println("searchMatrixIter(1-col, 6):  " + searchMatrixIter(oneCol, 6));
        // Expected: true, true
    }

    // ------------------------------------------------------- public API

    /**
     * Searches for {@code target} in a sorted 2D matrix using recursion.
     *
     * <p>Starts from the top-right corner and recurses by eliminating one
     * row or one column per call, depending on the comparison result.
     *
     * @param matrix a non-null, non-empty m×n matrix sorted row-wise and
     *               column-wise in ascending order
     * @param target the integer value to search for
     * @return {@code true} if {@code target} exists in {@code matrix},
     *         {@code false} otherwise
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        return helper(matrix, target, 0, matrix[0].length - 1);
    }

    /**
     * Searches for {@code target} in a sorted 2D matrix using an iterative
     * top-right corner walk.
     *
     * <p>Each iteration eliminates either the current column (value too large)
     * or the current row (value too small), guaranteeing O(m + n) termination.
     * Preferred over the recursive variant for large inputs as it uses O(1)
     * extra space.
     *
     * @param matrix a non-null, non-empty m×n matrix sorted row-wise and
     *               column-wise in ascending order
     * @param target the integer value to search for
     * @return {@code true} if {@code target} exists in {@code matrix},
     *         {@code false} otherwise
     */
    public static boolean searchMatrixIter(int[][] matrix, int target) {
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target)  j--;   // current column too large → eliminate it
            else                        i++;   // current row too small    → eliminate it
        }
        return false;
    }

    // ------------------------------------------------------- private helpers

    /**
     * Recursive helper that searches the sub-region of {@code matrix} reachable
     * from position {@code (i, j)} by moving left or downward.
     *
     * @param matrix the full matrix passed by reference (no copying)
     * @param target the value being searched
     * @param i      current row index (moves downward on each recursive call
     *               when the current value is less than {@code target})
     * @param j      current column index (moves left on each recursive call
     *               when the current value exceeds {@code target})
     * @return {@code true} if {@code target} is found, {@code false} if the
     *         search region is exhausted
     */
    static boolean helper(int[][] matrix, int target, int i, int j) {
        if (i >= matrix.length || j < 0) return false;          // out of bounds → not found
        if (matrix[i][j] == target)      return true;           // found
        if (matrix[i][j] > target)       return helper(matrix, target, i, j - 1); // eliminate column
        return helper(matrix, target, i + 1, j);                // eliminate row
    }
}