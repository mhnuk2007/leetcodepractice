package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Kruskal's Algorithm for Minimum Spanning Tree (MST).
 *
 * <p><b>Definition:</b>
 * A Minimum Spanning Tree is a subset of edges that connects all vertices
 * in a weighted undirected graph with the minimum possible total edge weight,
 * containing no cycles. Kruskal's builds it by selecting the smallest edges
 * first while avoiding cycles using a Disjoint Set Union (DSU).
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Greedy algorithm — always picks the globally smallest remaining edge</li>
 *     <li>Uses DSU with path compression and union by rank to detect cycles
 *         in O(α(V)) ≈ O(1) per edge</li>
 *     <li>Works directly on an edge list — no adjacency list needed</li>
 *     <li>Unlike Prim's, does not grow from a source — processes edges globally</li>
 * </ul>
 *
 * <p><b>Kruskal's vs Prim's:</b>
 * <ul>
 *     <li>Kruskal's sorts edges globally and uses DSU — better for sparse graphs</li>
 *     <li>Prim's uses a min-heap and grows from a source — better for dense graphs</li>
 *     <li>Both produce the same MST weight for a given graph</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(E log E) — dominated by sorting edges</p>
 * <p><b>Space Complexity:</b> O(V + E) — DSU arrays + edge list</p>
 */
public class KruskalAlgorithm {

    // -----------------------------------------------------------------------
    // Edge representation
    // -----------------------------------------------------------------------

    /**
     * Represents a weighted undirected edge.
     */
    static class Edge {
        /** First vertex. */
        int src;
        /** Second vertex. */
        int dest;
        /** Edge weight (non-negative). */
        int weight;

        /**
         * Constructs a weighted undirected edge.
         *
         * @param src    first vertex
         * @param dest   second vertex
         * @param weight edge weight (non-negative)
         */
        Edge(int src, int dest, int weight) {
            this.src    = src;
            this.dest   = dest;
            this.weight = weight;
        }
    }

    // -----------------------------------------------------------------------
    // DSU (Disjoint Set Union)
    // -----------------------------------------------------------------------

    /**
     * Finds the root of {@code u} with path compression.
     *
     * <p>Path compression flattens the tree so future calls on any node
     * in the same component are nearly O(1).
     *
     * @param parent parent array
     * @param u      node to find root of
     * @return root of the component containing {@code u}
     */
    static int findParent(int[] parent, int u) {
        if (parent[u] != u) parent[u] = findParent(parent, parent[u]);
        return parent[u];
    }

    /**
     * Merges two components given their respective roots.
     *
     * <p>Union by rank keeps the tree shallow by always attaching the
     * smaller-rank tree under the larger-rank root.
     *
     * @param pu     root of the first component
     * @param pv     root of the second component
     * @param parent parent array
     * @param rank   rank array
     */
    static void unionSet(int pu, int pv, int[] parent, int[] rank) {
        if (pu == pv) return;

        if (rank[pu] < rank[pv]) {
            parent[pu] = pv;
        } else if (rank[pu] > rank[pv]) {
            parent[pv] = pu;
        } else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }

    // -----------------------------------------------------------------------
    // Kruskal's Algorithm
    // -----------------------------------------------------------------------

    /**
     * Computes the total weight of the Minimum Spanning Tree using
     * Kruskal's algorithm.
     *
     * <p>Sorts all edges by weight ascending, then greedily adds each edge
     * whose endpoints belong to different DSU components. An edge is skipped
     * if both endpoints share the same root — adding it would form a cycle.
     *
     * @param n     number of vertices
     * @param edges list of all undirected weighted edges
     * @return total weight of the MST
     */
    public static int kruskal(int n, List<Edge> edges) {
        Collections.sort(edges, (a, b) -> Integer.compare(a.weight, b.weight));

        int[] parent = new int[n];
        int[] rank   = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i]   = 0;
        }

        int minWeight = 0;
        int edgesUsed = 0;

        for (Edge e : edges) {
            int pu = findParent(parent, e.src);
            int pv = findParent(parent, e.dest);

            if (pu != pv) {                      // Different components — no cycle
                minWeight += e.weight;
                unionSet(pu, pv, parent, rank);  // Efficient: uses pre-computed roots
                edgesUsed++;
            }

            if (edgesUsed == n - 1) break;      // Optimization: MST is complete
        }

        return minWeight;
    }

    // -----------------------------------------------------------------------
    // Main — test cases
    // -----------------------------------------------------------------------

    /**
     * Main method with test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // ==============================================
        // Example 1: Same graph as PrimsAlgorithm — verify same MST weight
        // ==============================================
        //     2       3
        //  0 --- 1 --- 3
        //  |   / |     |
        // 6|  8  |5   7|
        //  | /   |     |
        //  2 --- 4 --- 5
        //     4     9
        int V1 = 6;
        List<Edge> edges1 = new ArrayList<>();
        edges1.add(new Edge(0, 1, 2));
        edges1.add(new Edge(0, 2, 6));
        edges1.add(new Edge(1, 2, 8));
        edges1.add(new Edge(1, 3, 3));
        edges1.add(new Edge(1, 4, 5));
        edges1.add(new Edge(2, 4, 4));
        edges1.add(new Edge(3, 5, 7));
        edges1.add(new Edge(4, 5, 9));

        System.out.println("Example 1 (Expected: 21): " + kruskal(V1, edges1));
        // MST edges: 0-1(2), 1-3(3), 2-4(4), 1-4(5), 3-5(7) = 21


        // ==============================================
        // Example 2: Triangle
        // ==============================================
        //  0 --1-- 1
        //   \     /
        //    3   2
        //     \ /
        //      2
        int V2 = 3;
        List<Edge> edges2 = new ArrayList<>();
        edges2.add(new Edge(0, 1, 1));
        edges2.add(new Edge(1, 2, 2));
        edges2.add(new Edge(0, 2, 3));

        System.out.println("Example 2 (Expected:  3): " + kruskal(V2, edges2));
        // MST edges: 0-1(1), 1-2(2) = 3


        // ==============================================
        // Example 3: Linear chain
        // ==============================================
        // 0 --4-- 1 --3-- 2 --2-- 3
        int V3 = 4;
        List<Edge> edges3 = new ArrayList<>();
        edges3.add(new Edge(0, 1, 4));
        edges3.add(new Edge(1, 2, 3));
        edges3.add(new Edge(2, 3, 2));

        System.out.println("Example 3 (Expected:  9): " + kruskal(V3, edges3));
        // MST edges: 2-3(2), 1-2(3), 0-1(4) = 9


        // ==============================================
        // Example 4: Square — skips the expensive edge
        // ==============================================
        //  0 --1-- 1
        //  |       |
        //  5       2
        //  |       |
        //  2 --3-- 3
        int V4 = 4;
        List<Edge> edges4 = new ArrayList<>();
        edges4.add(new Edge(0, 1, 1));
        edges4.add(new Edge(1, 3, 2));
        edges4.add(new Edge(3, 2, 3));
        edges4.add(new Edge(0, 2, 5)); // skipped — would form a cycle

        System.out.println("Example 4 (Expected:  6): " + kruskal(V4, edges4));
        // MST edges: 0-1(1), 1-3(2), 3-2(3) = 6


        // ==============================================
        // Example 5: Single node
        // ==============================================
        int V5 = 1;
        List<Edge> edges5 = new ArrayList<>();

        System.out.println("Example 5 (Expected:  0): " + kruskal(V5, edges5));
        // No edges — MST cost = 0


        // ==============================================
        // Example 6: Already a tree — all edges included
        // ==============================================
        // 0 --1-- 1 --1-- 2 --1-- 3 --1-- 4
        int V6 = 5;
        List<Edge> edges6 = new ArrayList<>();
        edges6.add(new Edge(0, 1, 1));
        edges6.add(new Edge(1, 2, 1));
        edges6.add(new Edge(2, 3, 1));
        edges6.add(new Edge(3, 4, 1));

        System.out.println("Example 6 (Expected:  4): " + kruskal(V6, edges6));
        // MST = entire graph — total weight: 4
    }
}