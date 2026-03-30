package recursion;

/**
 * LeetCode 37 - Sudoku Solver
 * <p>
 * Problem:
 * Fill all '.' cells in a 9×9 board so that each row, column, and 3×3 box
 * contains digits '1'–'9' with no repetition. Exactly one solution exists.
 * <p>
 * Approach: Backtracking cell by cell (left to right, top to bottom)
 * Skip pre-filled cells. For empty cells, try digits '1'–'9'.
 * If a digit is safe (no conflict in row, column, or 3×3 box), place it
 * and recurse to the next cell. If recursion fails, backtrack (reset to '.').
 * <p>
 * Example:
 * board[0] = ['5','3','.','.','7','.','.','.','.']
 * After solving → ['5','3','4','6','7','8','9','1','2']
 * <p>
 * Time  : O(9^m) — m = number of empty cells, 9 choices each
 * Space : O(m)   — recursion depth
 */
public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // Test 1: standard case
        solveSudoku(board);
        System.out.println("Solved board:");
        for (char[] row : board) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
        // Expected: fully filled valid sudoku board
    }

    public static void solveSudoku(char[][] board) {
        int n = board.length;
        solve(board, 0, 0, n);
    }

    private static boolean solve(char[][] board, int row, int col, int n) {
        if (row == n) return true;                                         // all rows filled → solved

        int nextRow = (col + 1 == n) ? row + 1 : row;
        int nextCol = (col + 1 == n) ? 0 : col + 1;                       // wrap 0; }             // wrap to next row

        if (board[row][col] != '.') return solve(board, nextRow, nextCol, n);  // skip pre-filled

        for (char digit = '1'; digit <= '9'; digit++) {
            if (isSafe(board, row, col, digit, n)) {
                board[row][col] = digit;                                   // place digit
                if (solve(board, nextRow, nextCol, n)) return true;       // propagate success
                board[row][col] = '.';                                     // backtrack
            }
        }
        return false;                                                      // no digit worked
    }

    private static boolean isSafe(char[][] board, int row, int col, char digit, int n) {
        for (int i = 0; i < n; i++)
            if (board[row][i] == digit || board[i][col] == digit) return false;  // row/col check

        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++)
            for (int j = boxCol; j < boxCol + 3; j++)
                if (board[i][j] == digit) return false;                    // 3×3 box check

        return true;
    }
}