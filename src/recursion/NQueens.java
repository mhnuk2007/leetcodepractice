package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 51 - N-Queens
 * <p>
 * Problem:
 * Place n queens on an n×n chessboard such that no two queens attack each other.
 * Return all distinct solutions as a list of boards, each represented as
 * a list of strings ('Q' = queen, '.' = empty).
 * <p>
 * Approach: Backtracking row by row with O(1) conflict checks
 * Place exactly one queen per row. For each column in the current row,
 * check if placing is safe using three boolean arrays instead of board scans,
 * recurse to the next row, then backtrack by resetting all three arrays.
 * <p>
 * Conflict detection (all O(1)):
 * - cols[col]        : true if any queen occupies this column
 * - diags1[row-col+n-1] : true if any queen occupies this left diagonal (\)
 *                         all cells on the same '\' diagonal share row - col
 * - diags2[row+col]     : true if any queen occupies this right diagonal (/)
 *                         all cells on the same '/' diagonal share row + col
 * No row check needed — exactly one queen placed per row by design.
 * <p>
 * Example:
 * n = 4 → two solutions:
 * [".Q..",    ["..Q.",
 * "...Q",     "Q...",
 * "Q...",     "...Q",
 * "..Q."]     ".Q.."]
 * <p>
 * Time  : O(n!) — at most n choices in row 0, n-1 in row 1, and so on
 * Space : O(n²) — board storage; O(n) for the three boolean arrays; O(n) recursion depth
 */public class NQueens {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("n = 4:");
        solveNQueens(4).forEach(System.out::println);
        // Expected: 2 solutions

        // Test 2: single queen
        System.out.println("n = 1:");
        solveNQueens(1).forEach(System.out::println);
        // Expected: [["Q"]]

        // Test 3: no solution
        System.out.println("n = 2: " + solveNQueens(2));
        // Expected: []

        // Test 4: solution count
        System.out.println("n = 5: " + solveNQueens(5).size() + " solutions");
        // Expected: 10 solutions
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        boolean[] cols = new boolean[n];
        boolean[] diags1 = new boolean[2 * n - 1];
        boolean[] diags2 = new boolean[2 * n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        placeQueens(board, 0, n, cols, diags1, diags2, result);
        return result;
    }

    private static void placeQueens(
            char[][] board,
            int row, int n,
            boolean[] cols,
            boolean[] diags1,
            boolean[] diags2,
            List<List<String>> result) {
        if (row == n) {
            result.add(boardToString(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;
            if (cols[col] || diags1[d1] || diags2[d2]) continue;
            cols[col] = diags1[d1] = diags2[d2] = true;
            board[row][col] = 'Q';
            placeQueens(board, row + 1, n, cols, diags1, diags2, result);
            board[row][col] = '.';
            cols[col] = diags1[d1] = diags2[d2] = false;
        }
    }

    private static List<String> boardToString(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) result.add(new String(row));
        return result;
    }

}