package graph;

import java.util.Arrays;

/**
 * <h2>Disjoint Set Union (Union-Find)</h2>
 *
 * <p><b>Problem:</b> Maintain a collection of disjoint sets supporting two
 * operations efficiently:
 * <ul>
 *   <li>{@code find(x)} — return the representative (root) of the set
 *       containing {@code x}.</li>
 *   <li>{@code union(a, b)} — merge the sets containing {@code a} and
 *       {@code b}.</li>
 * </ul>
 * </p>
 *
 * <p><b>Approach:</b>
 * Each element starts as its own parent. {@code find} walks up the parent
 * chain to the root, applying <em>path compression</em> on the way back so
 * future calls on any node in the same component are nearly O(1).
 * {@code union} links the root of the smaller-rank tree under the root of
 * the larger-rank tree (<em>union by rank</em>), keeping the tree shallow.</p>
 *
 * <p><b>Example (step-by-step):</b>
 * <pre>
 *   elements:  a b c d e f g h   (mapped to indices 0-7)
 *
 *   union(0,1) → root: 0         {a,b} {c} {d} {e} {f} {g} {h}
 *   union(2,3) → root: 2         {a,b} {c,d} {e} {f} {g} {h}
 *   union(4,5) → root: 4         {a,b} {c,d} {e,f} {g} {h}
 *   union(6,7) → root: 6         {a,b} {c,d} {e,f} {g,h}
 *   union(0,2) → root: 0         {a,b,c,d} {e,f} {g,h}
 *   union(4,6) → root: 4         {a,b,c,d} {e,f,g,h}
 *   union(0,4) → root: 0         {a,b,c,d,e,f,g,h}
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(α(n)) per operation — α is the inverse
 * Ackermann function, effectively O(1) for all practical inputs.</p>
 * <p><b>Space Complexity:</b> O(n) — parent and rank arrays.</p>
 */
public class DisjointSetUnion {

    // ---------------------------------------------------------------------- //
    //  DSU implementation                                                      //
    // ---------------------------------------------------------------------- //

    /**
     * Union-Find data structure with path compression and union by rank.
     */
    static class DSU {

        /** {@code parent[i]} is the parent of node {@code i}; root if {@code parent[i] == i}. */
        private final int[] parent;

        /**
         * {@code rank[i]} is an upper bound on the height of the subtree rooted
         * at {@code i}. Used to keep trees shallow during union.
         */
        private final int[] rank;

        /**
         * Initialises a DSU with {@code n} singleton sets (0 … n-1).
         *
         * @param n number of elements
         */
        DSU(int n) {
            parent = new int[n];
            rank   = new int[n];            // zero-filled — rank starts at 0
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        /**
         * Returns the root (representative) of the set containing {@code x},
         * applying path compression so all nodes on the path point directly
         * to the root after this call.
         *
         * @param x element to find root of
         * @return root of the set containing {@code x}
         */
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);  // path compression
            return parent[x];
        }

        /**
         * Merges the sets containing {@code a} and {@code b}.
         * If they already belong to the same set this is a no-op.
         *
         * <p>Union by rank attaches the root of the shallower tree under the
         * root of the taller tree, so the combined tree grows no taller than
         * necessary. Rank is incremented only when two equal-rank trees merge.</p>
         *
         * @param a first element
         * @param b second element
         */
        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) return;     // already in the same set

            if      (rank[rootA] < rank[rootB]) parent[rootA] = rootB;
            else if (rank[rootA] > rank[rootB]) parent[rootB] = rootA;
            else {
                parent[rootA] = rootB;
                rank[rootB]++;
            }
        }

        /**
         * Returns {@code true} if {@code a} and {@code b} belong to the same set.
         *
         * @param a first element
         * @param b second element
         * @return {@code true} if {@code find(a) == find(b)}
         */
        boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Walks through the 7-step union sequence from the class
     * comment, then verifies connectivity and isolation with additional cases.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        // ------------------------------------------------------------------ //
        // Test 1: Step-by-step union sequence (8 elements, indices 0-7)      //
        //   a=0 b=1 c=2 d=3 e=4 f=5 g=6 h=7                                 //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: Step-by-step union sequence ===");
        {
            DSU dsu = new DSU(8);

            dsu.union(0, 1);    // {a,b}
            dsu.union(2, 3);    // {c,d}
            dsu.union(4, 5);    // {e,f}
            dsu.union(6, 7);    // {g,h}
            dsu.union(0, 2);    // {a,b,c,d}
            dsu.union(4, 6);    // {e,f,g,h}
            dsu.union(0, 4);    // {a,b,c,d,e,f,g,h}

            boolean allSame = dsu.connected(0, 7);
            System.out.println("All 8 elements in one set : "
                    + allSame + (allSame ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 2: Connectivity check before and after union                   //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: Connectivity before and after union ===");
        {
            DSU dsu = new DSU(5);

            boolean before = dsu.connected(1, 3);
            System.out.println("1 and 3 connected before union : "
                    + before + (!before ? " [PASS]" : " [FAIL]"));

            dsu.union(1, 2);
            dsu.union(2, 3);

            boolean after = dsu.connected(1, 3);
            System.out.println("1 and 3 connected after union  : "
                    + after + (after ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 3: Isolated components — different sets stay separate          //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: Isolated components ===");
        {
            DSU dsu = new DSU(6);

            dsu.union(0, 1);
            dsu.union(2, 3);
            // 4 and 5 are untouched

            boolean separated = !dsu.connected(0, 2);
            boolean isolated4 = !dsu.connected(0, 4);
            System.out.println("{0,1} and {2,3} not connected : "
                    + separated + (separated ? " [PASS]" : " [FAIL]"));
            System.out.println("Node 4 isolated from 0        : "
                    + isolated4 + (isolated4 ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 4: Redundant union (same set) — no-op, no crash                //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: Redundant union is a no-op ===");
        {
            DSU dsu = new DSU(4);

            dsu.union(0, 1);
            int rootBefore = dsu.find(0);
            dsu.union(0, 1);    // duplicate — should change nothing
            int rootAfter = dsu.find(0);

            boolean stable = (rootBefore == rootAfter);
            System.out.println("Root unchanged after duplicate union : "
                    + stable + (stable ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 5: Path compression — all nodes point to root after find       //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 5: Path compression ===");
        {
            DSU dsu = new DSU(5);

            // Build a chain: 0 → 1 → 2 → 3 → 4
            // Bypassing union-by-rank to force a deep chain for demonstration
            dsu.parent[0] = 1;
            dsu.parent[1] = 2;
            dsu.parent[2] = 3;
            dsu.parent[3] = 4;
            dsu.parent[4] = 4;

            int root = dsu.find(0);         // triggers path compression
            boolean compressed = (dsu.parent[0] == root);
            System.out.println("Node 0 points directly to root after find : "
                    + compressed + (compressed ? " [PASS]" : " [FAIL]"));
        }
    }
}