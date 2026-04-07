package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 52 - N-Queens II
 * <p>
 * Problem:
 * Place n queens on an n×n chessboard such that no two queens attack each other.
 * Return the number of distinct solutions (not the boards themselves).
 * <p>
 * Approach: Backtracking row by row — count only, no board stored
 * Identical conflict detection to NQueens (LC 51): three boolean arrays for
 * columns, left diagonals (\), and right diagonals (/) give O(1) safety checks.
 * The only difference is that reaching row == n increments a counter instead
 * of capturing a board snapshot, so no char[][] or boardToString is needed.
 * <p>
 * Example:
 * n = 4 → 2
 * n = 1 → 1
 * n = 5 → 10
 * <p>
 * Time  : O(n!) — same traversal as LC 51
 * Space : O(n)  — three boolean arrays + O(n) recursion depth; no board storage
 */

public class NQueensII {
    public static void main(String[] args) {
        System.out.println("n = 4 → " + totalNQueens(4));   // Expected: 2
        System.out.println("n = 1 → " + totalNQueens(1));   // Expected: 1
        System.out.println("n = 5 → " + totalNQueens(5));   // Expected: 10
    }

    public static int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diags1 = new boolean[2 * n - 1];
        boolean[] diags2 = new boolean[2 * n - 1];
        return countBoards(0, n, cols, diags1, diags2);
    }

    private static int countBoards(
            int row, int n,
            boolean[] cols,
            boolean[] diags1,
            boolean[] diags2) {
        if (row == n) return 1;
        int count = 0;
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;
            if (cols[col] || diags1[d1] || diags2[d2]) continue;
            cols[col] = diags1[d1] = diags2[d2] = true;
            count += countBoards(row + 1, n, cols, diags1, diags2);
            cols[col] = diags1[d1] = diags2[d2] = false;
        }
        return count;
    }

}


