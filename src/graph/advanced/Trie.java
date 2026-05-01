package graph;

/**
 * <h2>LC 208 · Implement Trie (Prefix Tree)</h2>
 *
 * <p><b>Problem:</b> Implement a trie supporting three operations:
 * {@code insert}, {@code search}, and {@code startsWith}.</p>
 *
 * <p><b>Approach:</b> Each {@code TrieNode} holds an array of 26 children
 * (one per lowercase letter a–z) and an {@code isEnd} flag marking whether
 * any inserted word ends at that node. A shared private {@code traverse}
 * helper walks the trie one character at a time and returns the node reached,
 * or {@code null} if the path breaks. {@code search} reuses it and checks
 * {@code isEnd}; {@code startsWith} reuses it and checks only that the node
 * is non-null.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   insert("the"), insert("there"), insert("their"), insert("any")
 *
 *   search("the")     → true   (exact word inserted)
 *   search("th")      → false  (prefix only — isEnd is false)
 *   startsWith("th")  → true   (path exists)
 *   startsWith("xyz") → false  (path broken at 'x')
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(L) per operation — L = length of word or
 * prefix.</p>
 * <p><b>Space Complexity:</b> O(N · L · 26) — N words of average length L,
 * each node holding a 26-slot child array.</p>
 */
public class Trie {

    // ---------------------------------------------------------------------- //
    //  TrieNode                                                                //
    // ---------------------------------------------------------------------- //

    /**
     * A single node in the trie.
     *
     * <p>Children are indexed by character offset: {@code 'a'=0 … 'z'=25}.
     * A {@code null} slot means no word with that character at this depth
     * has been inserted.</p>
     */
    static class TrieNode {

        /** Child nodes; {@code null} slots indicate absent prefixes. */
        final TrieNode[] children = new TrieNode[26];

        /** {@code true} if an inserted word ends exactly at this node. */
        boolean isEnd;
    }

    // ---------------------------------------------------------------------- //
    //  Fields and constructor                                                  //
    // ---------------------------------------------------------------------- //

    /** Root node — never holds a character; only its children do. */
    private final TrieNode root;

    /** Initialises an empty trie. */
    public Trie() {
        root = new TrieNode();
    }

    // ---------------------------------------------------------------------- //
    //  Public API                                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Inserts {@code word} into the trie.
     *
     * <p>Walks from the root one character at a time, creating any missing
     * child nodes along the way. Sets {@code isEnd = true} on the node
     * reached after the last character.</p>
     *
     * @param word lowercase English word to insert (non-null, non-empty)
     */
    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }

        node.isEnd = true;
    }

    /**
     * Returns {@code true} if {@code word} was previously inserted in full.
     *
     * <p>Uses {@link #traverse} to reach the final node, then checks
     * {@code isEnd} — distinguishing an exact word from a prefix that was
     * never inserted as a standalone word.</p>
     *
     * @param word word to look up
     * @return {@code true} if {@code word} exists as a complete inserted word
     */
    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns {@code true} if any inserted word starts with {@code prefix}.
     *
     * <p>Uses {@link #traverse} to check that the prefix path exists.
     * Does not check {@code isEnd} — the prefix need not be a complete
     * word itself.</p>
     *
     * @param prefix prefix to check
     * @return {@code true} if at least one inserted word begins with
     *         {@code prefix}
     */
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    // ---------------------------------------------------------------------- //
    //  Private helper                                                          //
    // ---------------------------------------------------------------------- //

    /**
     * Walks the trie along the characters of {@code s} and returns the node
     * reached after the last character, or {@code null} if any character's
     * child slot is missing.
     *
     * <p>Shared by {@link #search} and {@link #startsWith} to avoid
     * duplicating the traversal loop.</p>
     *
     * @param s string to traverse (word or prefix)
     * @return the node at the end of the path, or {@code null} if not found
     */
    private TrieNode traverse(String s) {
        TrieNode node = root;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return null;
            node = node.children[idx];
        }

        return node;
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Inserts a word set then verifies {@code search} and
     * {@code startsWith} across exact words, prefixes, absent words, and
     * post-insert growth.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        Trie trie = new Trie();
        for (String w : new String[]{"the", "a", "there", "any", "their"}) {
            trie.insert(w);
        }

        // ------------------------------------------------------------------ //
        // Test 1: search — exact inserted words                               //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: search — exact words ===");
        check("search(\"the\")",   trie.search("the"),   true);
        check("search(\"a\")",     trie.search("a"),     true);
        check("search(\"there\")", trie.search("there"), true);
        check("search(\"their\")", trie.search("their"), true);
        check("search(\"any\")",   trie.search("any"),   true);
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 2: search — prefixes and absent words must return false        //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: search — not inserted ===");
        check("search(\"th\")",    trie.search("th"),    false); // prefix only
        check("search(\"an\")",    trie.search("an"),    false); // prefix only
        check("search(\"thor\")",  trie.search("thor"),  false); // never inserted
        check("search(\"xyz\")",   trie.search("xyz"),   false); // no path at all
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 3: startsWith — valid prefixes                                 //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: startsWith — valid prefixes ===");
        check("startsWith(\"th\")",  trie.startsWith("th"),  true);
        check("startsWith(\"the\")", trie.startsWith("the"), true); // full word is also a prefix
        check("startsWith(\"an\")",  trie.startsWith("an"),  true);
        check("startsWith(\"a\")",   trie.startsWith("a"),   true);
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 4: startsWith — absent prefixes                                //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: startsWith — absent prefixes ===");
        check("startsWith(\"in\")",  trie.startsWith("in"),  false);
        check("startsWith(\"xyz\")", trie.startsWith("xyz"), false);
        check("startsWith(\"thx\")", trie.startsWith("thx"), false);
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 5: trie grows correctly after initial build                    //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 5: insert after initial build ===");
        check("search(\"anthem\") before insert",  trie.search("anthem"),     false);
        check("startsWith(\"anth\") before insert", trie.startsWith("anth"),  false);
        trie.insert("anthem");
        check("search(\"anthem\") after insert",   trie.search("anthem"),     true);
        check("startsWith(\"anth\") after insert",  trie.startsWith("anth"),  true);
    }

    /**
     * Prints a labelled assertion with PASS/FAIL result.
     *
     * @param label    description of the check
     * @param actual   actual result
     * @param expected expected result
     */
    private static void check(String label, boolean actual, boolean expected) {
        System.out.println(label + " → expected " + expected + ", got " + actual
                + (actual == expected ? " [PASS]" : " [FAIL]"));
    }
}