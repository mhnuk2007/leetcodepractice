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
 * Approach: Backtracking row by row
 * Place exactly one queen per row. For each column in the current row,
 * check if placing is safe (no conflict in column or either diagonal),
 * recurse to the next row, then backtrack by removing the queen.
 * <p>
 * isSafe checks:
 * - Column      : scan entire column upward
 * - Left diagonal  : scan up-left  (row--, col--)
 * - Right diagonal : scan up-right (row--, col++)
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
 * Space : O(n²) — board storage, O(n) recursion depth
 */
public class NQueens {

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

    private static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        placeQueens(board, 0, n, result);
        return result;
    }

    private static void placeQueens(char[][] board, int row, int n, List<List<String>> result) {
        if (row == n) {
            result.add(construct(board));
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                placeQueens(board, row + 1, n, result);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < n ; i--, j++)
            if (board[i][j] == 'Q') return false;

        return true;
    }

    private static List<String> construct(char[][] board) {
        List<String> current = new ArrayList<>();
        for(char[] row : board) current.add(new String(row));
        return current;
    }
}