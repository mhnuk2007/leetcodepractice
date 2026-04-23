package recursion;

/**
 * <h2>LC 79 · Word Search</h2>
 *
 * <p><b>Problem:</b> Given an {@code m × n} character grid and a string
 * {@code word}, return {@code true} if {@code word} exists in the grid.
 * The word must be formed from adjacent cells (horizontally or vertically),
 * and each cell may be used at most once per path.</p>
 *
 * <p><b>Approach (Backtracking + In-place visited marking):</b>
 * For every cell, launch DFS attempting to match {@code word} character by
 * character. Before recursing into a cell, mark it with a sentinel
 * ({@code '$'}) so it cannot be reused on the current path. After all four
 * directions are explored, restore the original character (backtrack).
 * The sentinel approach avoids a separate {@code visited[][]} array.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   board = [["A","B","C","E"],
 *            ["S","F","C","S"],
 *            ["A","D","E","E"]]
 *
 *   word = "ABCCED" → true   (A→B→C→C→E→D)
 *   word = "SEE"    → true   (S→E→E bottom-right)
 *   word = "ABCB"   → false  (B cannot be reused)
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(m · n · 4^L) — L = word length; each cell
 * launches DFS branching into at most 4 directions per step.</p>
 * <p><b>Space Complexity:</b> O(L) — recursion depth equals word length.</p>
 */
public class WordSearch {

    /** Four cardinal directions: up, right, down, left. */
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // ---------------------------------------------------------------------- //
    //  Solution                                                                //
    // ---------------------------------------------------------------------- //

    /**
     * Returns {@code true} if {@code word} can be traced through adjacent
     * cells in {@code board}, using each cell at most once per path.
     *
     * <p>Iterates over every cell as a potential starting point and delegates
     * to {@link #find} for DFS matching.</p>
     *
     * @param board 2-D character grid (non-null, non-empty)
     * @param word  target word (non-null, non-empty)
     * @return {@code true} if the word exists in the grid
     */
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (find(board, i, j, 0, word)) return true;
            }
        }
        return false;
    }

    /**
     * DFS backtracking helper. Attempts to match {@code word.charAt(idx)}
     * at cell {@code (row, col)}, then recurses into all four neighbours
     * for the next character.
     *
     * <p><b>Visited marking:</b> The matched cell is temporarily overwritten
     * with {@code '$'} before recursing and restored afterwards. This prevents
     * the same cell from being used twice on the same path without allocating
     * a separate visited array.</p>
     *
     * <p><b>Restore timing:</b> {@code board[row][col] = temp} executes after
     * all four directions have been tried — not inside the direction loop —
     * so each direction searches the same unmodified board state.</p>
     *
     * @param board 2-D character grid
     * @param row   current row
     * @param col   current column
     * @param idx   index of the character in {@code word} to match next
     * @param word  target word
     * @return {@code true} if the remaining suffix of {@code word} starting
     *         at {@code idx} can be matched from cell {@code (row, col)}
     */
    private boolean find(char[][] board, int row, int col, int idx, String word) {
        if (idx == word.length()) return true;                          // full word matched

        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length) return false;    // out of bounds
        if (board[row][col] != word.charAt(idx)) return false;          // wrong char or visited ('$')

        char temp = board[row][col];
        board[row][col] = '$';                                          // mark visited

        for (int[] d : DIRECTIONS) {
            if (find(board, row + d[0], col + d[1], idx + 1, word)) return true;
        }

        board[row][col] = temp;                                         // restore — backtrack
        return false;
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Covers the three LC examples, a self-crossing path that
     * must be rejected, a single-cell board, and a word longer than the board.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        WordSearch sol = new WordSearch();

        // ------------------------------------------------------------------ //
        // Test 1: LC example — ABCCED exists                                  //
        //   A B C E                                                           //
        //   S F C S                                                           //
        //   A D E E                                                           //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: ABCCED ===");
        char[][] board1 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        check(sol.exist(board1, "ABCCED"), true);

        // ------------------------------------------------------------------ //
        // Test 2: LC example — SEE exists                                     //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: SEE ===");
        char[][] board2 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        check(sol.exist(board2, "SEE"), true);

        // ------------------------------------------------------------------ //
        // Test 3: LC example — ABCB rejected (B cannot be reused)            //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: ABCB (cell reuse rejected) ===");
        char[][] board3 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        check(sol.exist(board3, "ABCB"), false);

        // ------------------------------------------------------------------ //
        // Test 4: Single cell — exact match                                   //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: Single cell match ===");
        char[][] board4 = {{'A'}};
        check(sol.exist(board4, "A"), true);

        // ------------------------------------------------------------------ //
        // Test 5: Single cell — no match                                      //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 5: Single cell no match ===");
        char[][] board5 = {{'A'}};
        check(sol.exist(board5, "B"), false);

        // ------------------------------------------------------------------ //
        // Test 6: Word longer than available cells                             //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 6: Word longer than board ===");
        char[][] board6 = {{'A','B'},{'C','D'}};
        check(sol.exist(board6, "ABCDA"), false);   // would need A twice

        // ------------------------------------------------------------------ //
        // Test 7: Spiral path — uses every cell exactly once                  //
        //   A B                                                               //
        //   D C                                                               //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 7: Spiral path ABCD ===");
        char[][] board7 = {{'A','B'},{'D','C'}};
        check(sol.exist(board7, "ABCD"), true);
    }

    /**
     * Prints a single assertion result.
     *
     * @param actual   result from {@link #exist}
     * @param expected expected result
     */
    private static void check(boolean actual, boolean expected) {
        System.out.println("Expected " + expected + ", got " + actual
                + (actual == expected ? " [PASS]" : " [FAIL]"));
        System.out.println();
    }
}