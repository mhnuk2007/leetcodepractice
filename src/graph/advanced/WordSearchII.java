package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>LC 212 · Word Search II</h2>
 *
 * <p><b>Problem:</b> Given an {@code m × n} character grid and a list of
 * target words, return all words that can be formed by tracing adjacent cells
 * (horizontal or vertical), using each cell at most once per word.</p>
 *
 * <p><b>Approach (Trie + Backtracking + Trie pruning):</b>
 * Insert all words into a Trie. For each board cell, if the character exists
 * as a child of the trie root, launch DFS guided by the trie. Each step only
 * descends into characters that exist in the trie — branches with no matching
 * words are skipped entirely.
 *
 * <p>When a word is found, {@code node.word} is nulled to prevent duplicates.
 * After all four directions are explored, if the current trie node has no
 * remaining children and no word, it is unlinked from its parent
 * (<em>trie pruning</em>) — future DFS calls will see {@code null} and skip
 * that branch without descending into it.</p>
 *
 * <p><b>Why Trie over repeated LC 79:</b>
 * Running LC 79 per word costs O(W · m · n · 4^L). The Trie shares prefix
 * traversal — one DFS pass from a cell explores all words simultaneously.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   board = [["o","a","a","n"],
 *            ["e","t","a","e"],
 *            ["i","h","k","r"],
 *            ["i","f","l","v"]]
 *   words  = ["oath","pea","eat","rain"]
 *   Output = ["eat","oath"]
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(m · n · 4^L) worst case — L = max word
 * length. Trie pruning reduces this substantially in practice.</p>
 * <p><b>Space Complexity:</b> O(W · L) — trie storage for W words of
 * average length L.</p>
 */
public class WordSearchII {

    // ---------------------------------------------------------------------- //
    //  Constants                                                               //
    // ---------------------------------------------------------------------- //

    /** Four cardinal directions: up, right, down, left. */
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // ---------------------------------------------------------------------- //
    //  TrieNode                                                                //
    // ---------------------------------------------------------------------- //

    /**
     * A node in the Trie.
     *
     * <p>{@code word} is non-null only at the terminal node of an inserted
     * word. It serves as both the "is-end" flag and the word store, so no
     * separate boolean is needed. It is nulled when the word is collected to
     * prevent duplicate results.</p>
     */
    static class TrieNode {

        /** Children indexed by {@code c - 'a'} for lowercase a–z. */
        final TrieNode[] children = new TrieNode[26];

        /**
         * The complete word ending at this node, or {@code null} if this node
         * is not a word terminal (or the word has already been collected).
         */
        String word;

        /**
         * Returns {@code true} if all 26 child slots are {@code null}.
         * Used during trie pruning to decide whether to unlink this node
         * from its parent after a word is collected.
         *
         * @return {@code true} if this node has no remaining children
         */
        boolean isEmpty() {
            for (TrieNode child : children) {
                if (child != null) return false;
            }
            return true;
        }
    }

    // ---------------------------------------------------------------------- //
    //  Trie                                                                    //
    // ---------------------------------------------------------------------- //

    /**
     * Minimal Trie supporting word insertion only.
     * Stores the complete word string at terminal nodes for O(1) retrieval
     * during board DFS — avoids reconstructing the word from the path.
     */
    static class Trie {

        /** Sentinel root — holds no character; only its children do. */
        final TrieNode root = new TrieNode();

        /**
         * Inserts {@code word} into the trie, storing the word string at
         * the terminal node.
         *
         * @param word lowercase word to insert
         */
        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.word = word;
        }
    }

    // ---------------------------------------------------------------------- //
    //  Solution                                                                //
    // ---------------------------------------------------------------------- //

    /**
     * Returns all words from {@code words} that exist in {@code board}.
     *
     * <p>Builds a trie from all target words then scans every cell. For each
     * cell whose character matches a trie-root child, launches DFS.</p>
     *
     * @param board 2-D character grid (non-null, non-empty)
     * @param words target words to search for (non-null)
     * @return list of found words in discovery order, each at most once
     */
    public static List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String w : words) trie.insert(w);

        List<String> result = new ArrayList<>();
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (trie.root.children[board[i][j] - 'a'] != null) {
                    dfs(board, i, j, trie.root, result);
                }
            }
        }
        return result;
    }

    /**
     * DFS backtracking from {@code (row, col)}, guided by the trie.
     *
     * <p><b>Word collection:</b> When {@code node.word != null} the current
     * path spells a target word. The word is added to results and
     * {@code node.word} is nulled to prevent duplicate collection.</p>
     *
     * <p><b>Trie pruning:</b> After all four directions are explored, if the
     * current node has no remaining children and no word, the parent's pointer
     * to it is set to {@code null}. Future DFS calls reaching the same parent
     * will see {@code null} and skip this branch without descending.</p>
     *
     * <p><b>Visited marking:</b> The current cell is overwritten with
     * {@code '$'} before recursing and restored afterwards.</p>
     *
     * @param board  2-D character grid
     * @param row    current row
     * @param col    current column
     * @param parent trie node whose child slot corresponds to
     *               {@code board[row][col]}
     * @param result accumulator for found words
     */
    private static void dfs(char[][] board, int row, int col,
                            TrieNode parent, List<String> result) {
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length) return;

        char c = board[row][col];
        if (c == '$') return;                               // visited on current path

        int idx = c - 'a';
        TrieNode node = parent.children[idx];
        if (node == null) return;                           // no trie path — prune

        if (node.word != null) {
            result.add(node.word);
            node.word = null;                               // null — prevents duplicate
        }

        board[row][col] = '$';                              // mark visited

        for (int[] d : DIRECTIONS) {
            dfs(board, row + d[0], col + d[1], node, result);
        }

        board[row][col] = c;                                // restore — backtrack

        // Trie pruning: dead node — unlink from parent so future DFS skips it
        if (node.isEmpty() && node.word == null) {
            parent.children[idx] = null;
        }
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Covers the LC example, no-match input, single-cell words,
     * duplicate word suppression, and overlapping prefix words.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        // ------------------------------------------------------------------ //
        // Test 1: LC example — eat, oath found; pea, rain not in board       //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: LC example ===");
        {
            char[][] board = {
                    {'o','a','a','n'},
                    {'e','t','a','e'},
                    {'i','h','k','r'},
                    {'i','f','l','v'}
            };
            List<String> result = findWords(board, new String[]{"oath","pea","eat","rain"});
            result.sort(null);
            System.out.println("Expected : [eat, oath]");
            System.out.println("Got      : " + result
                    + (result.equals(List.of("eat","oath")) ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 2: No words found                                               //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: No words found ===");
        {
            char[][] board = {{'a','b'},{'c','d'}};
            List<String> result = findWords(board, new String[]{"xyz","efg"});
            System.out.println("Expected : []");
            System.out.println("Got      : " + result
                    + (result.isEmpty() ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 3: Single-cell word                                             //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: Single-cell word ===");
        {
            char[][] board = {{'a'}};
            List<String> result = findWords(board, new String[]{"a"});
            System.out.println("Expected : [a]");
            System.out.println("Got      : " + result
                    + (result.equals(List.of("a")) ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 4: Duplicate word in input — collected only once               //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: Duplicate word suppression ===");
        {
            char[][] board = {{'a','b'},{'c','d'}};
            List<String> result = findWords(board, new String[]{"ab","ab"});
            System.out.println("Expected : [ab]");
            System.out.println("Got      : " + result
                    + (result.size() == 1 && result.get(0).equals("ab") ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 5: Overlapping prefix words both findable                      //
        //   a b                                                               //
        //   c d    "ab" and "abdc" both traceable                            //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 5: Overlapping prefix words ===");
        {
            char[][] board = {{'a','b'},{'c','d'}};
            List<String> result = findWords(board, new String[]{"ab","abdc"});
            result.sort(null);
            System.out.println("Expected : [ab, abdc]");
            System.out.println("Got      : " + result
                    + (result.equals(List.of("ab","abdc")) ? " [PASS]" : " [FAIL]"));
        }
    }
}