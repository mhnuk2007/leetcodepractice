package recursion;

/**
 * LeetCode 37 - Sudoku Solver
 * <p>
 * Problem:
 * Fill all '.' cells in a 9×9 board so that each row, column, and 3×3 box
 * contains digits '1'–'9' with no repetition. Exactly one solution exists.
 * <p>
 * Approach: Backtracking with O(1) conflict checks via boolean arrays
 * Three 9×9 boolean arrays track digit usage per row, column, and 3×3 box.
 * Initialised from pre-filled cells before recursion begins.
 * For each empty cell, try digits '1'–'9': mark all three arrays, recurse,
 * unmark on backtrack. Eliminates the O(n) board scan of isSafe entirely.
 * <p>
 * Conflict detection (all O(1)):
 * - rows[r][d]   : digit d+1 already placed in row r
 * - cols[c][d]   : digit d+1 already placed in column c
 * - boxes[b][d]  : digit d+1 already placed in box b, where b = (r/3)*3 + (c/3)
 * <p>
 * Example:
 * board[0] = ['5','3','.','.','7','.','.','.','.']
 * After solving → ['5','3','4','6','7','8','9','1','2']
 * <p>
 * Time  : O(9^m) — m = number of empty cells, 9 choices each
 * Space : O(1)   — boolean arrays are fixed 9×9; O(m) recursion depth
 */
public class SudokuSolver2 {

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

    private static void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int d = board[r][c] - '1';
                    int b = (r / 3) * 3 + (c / 3);
                    rows[r][d] = cols[c][d] = boxes[b][d] = true;
                }
            }
        }
        solve(board, 0, 0, rows, cols, boxes);
    }

    private static boolean solve(char[][] board, int row, int col, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (row == 9) return true;
        int nextRow = (col + 1 == 9) ? row + 1 : row;
        int nextCol = (col + 1 == 9) ? 0 : col + 1;

        if(board[row][col] != '.') return solve(board, nextRow, nextCol, rows, cols, boxes);
        int b = (row / 3) * 3 + (col / 3);
        for (int d = 0; d < 9; d++) {
            if(rows[row][d] || cols[col][d] || boxes[b][d]) continue;
            rows[row][d] = cols[col][d] = boxes[b][d] = true;
            board[row][col] = (char) ('1' + d);
            if(solve(board, nextRow, nextCol, rows, cols, boxes)) return true;
            board[row][col] = '.';
            rows[row][d] = cols[col][d] = boxes[b][d] = false;
        }
        return false;
    }

}