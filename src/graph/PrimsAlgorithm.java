package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of Prim's Algorithm for Minimum Spanning Tree (MST).
 *
 * <p><b>Definition:</b>
 * A Minimum Spanning Tree is a subset of edges that connects all vertices
 * in a weighted undirected graph with the minimum possible total edge weight,
 * containing no cycles.
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Greedily grows the MST one edge at a time by always picking the
 *         minimum weight edge connecting the MST to an unvisited vertex</li>
 *     <li>Uses a min-heap ordered by edge weight — same structure as Dijkstra's,
 *         but the heap key is the raw edge weight, not cumulative distance</li>
 *     <li>Requires an undirected graph — each edge is added in both directions</li>
 *     <li>Produces a spanning tree of exactly {@code V - 1} edges</li>
 *     <li>{@code inMST[]} replaces the stale-entry check used in Dijkstra's:
 *         once a vertex is included its cost is final</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O((V + E) log V) — each edge may trigger a heap insertion</p>
 * <p><b>Space Complexity:</b> O(V + E) — adjacency list + inMST array + heap</p>
 */
public class PrimsAlgorithm {

    // -----------------------------------------------------------------------
    // Supporting types
    // -----------------------------------------------------------------------

    /**
     * Represents a weighted edge destination.
     */
    static class Pair {
        /** Destination vertex. */
        int node;
        /** Edge weight. */
        int weight;

        /**
         * Constructs a weighted edge destination.
         *
         * @param node   destination vertex
         * @param weight edge weight (non-negative)
         */
        Pair(int node, int weight) {
            this.node   = node;
            this.weight = weight;
        }
    }

    /**
     * Weighted undirected graph using an adjacency list of {@link Pair}s.
     */
    static class Graph {
        /** Number of vertices. */
        int V;
        /** Adjacency list — index is source vertex, each entry is (dest, weight). */
        List<List<Pair>> adj;

        /**
         * Constructs an empty undirected graph with {@code V} vertices.
         *
         * @param V number of vertices
         */
        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }
    }

    // -----------------------------------------------------------------------
    // Graph construction
    // -----------------------------------------------------------------------

    /**
     * Adds an undirected weighted edge between {@code u} and {@code v}.
     *
     * @param graph  the graph
     * @param u      first vertex
     * @param v      second vertex
     * @param weight edge weight (non-negative)
     */
    public static void addEdge(Graph graph, int u, int v, int weight) {
        graph.adj.get(u).add(new Pair(v, weight));
        graph.adj.get(v).add(new Pair(u, weight)); // undirected
    }

    // -----------------------------------------------------------------------
    // Prim's Algorithm
    // -----------------------------------------------------------------------

    /**
     * Computes the total weight of the Minimum Spanning Tree starting from
     * {@code src} using Prim's algorithm.
     *
     * <p>The min-heap yields the cheapest edge crossing the MST boundary.
     * {@code inMST[]} ensures each vertex is included exactly once — once
     * a vertex is polled and marked, any later heap entries for it are
     * discarded via the {@code if (inMST[u]) continue} guard.
     *
     * <p>Unlike Dijkstra's, the heap key is the raw edge weight rather than
     * a cumulative distance — MST cost depends only on individual edge weights.
     *
     * @param V     number of vertices
     * @param graph the weighted undirected graph
     * @param src   source vertex to start building the MST from
     * @return total weight of the MST
     */
    public static int prims(int V, Graph graph, int src) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        boolean[] inMST = new boolean[V];
        minHeap.offer(new Pair(src, 0));
        int mstCost = 0;

        while (!minHeap.isEmpty()) {
            Pair curr = minHeap.poll();
            int u = curr.node;
            int w = curr.weight;

            if (inMST[u]) continue; // Already in MST — stale entry
            inMST[u]  = true;
            mstCost  += w;

            for (Pair p : graph.adj.get(u)) {
                if (!inMST[p.node]) {
                    minHeap.offer(new Pair(p.node, p.weight));
                }
            }
        }

        return mstCost;
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
        // Example 1: Standard MST graph
        // ==============================================
        //     2       3
        //  0 --- 1 --- 3
        //  |   / |     |
        // 6|  8  |5   7|
        //  | /   |     |
        //  2 --- 4 --- 5
        //     4     9
        int V1 = 6;
        Graph graph1 = new Graph(V1);
        addEdge(graph1, 0, 1, 2);
        addEdge(graph1, 0, 2, 6);
        addEdge(graph1, 1, 2, 8);
        addEdge(graph1, 1, 3, 3);
        addEdge(graph1, 1, 4, 5);
        addEdge(graph1, 2, 4, 4);
        addEdge(graph1, 3, 5, 7);
        addEdge(graph1, 4, 5, 9);

        System.out.println("Example 1 (Expected: 21): " + prims(V1, graph1, 0));
        // MST edges: 0-1(2), 1-3(3), 2-4(4), 1-4(5), 3-5(7) = 21


        // ==============================================
        // Example 2: Simple triangle
        // ==============================================
        //  0 --1-- 1
        //   \     /
        //    3   2
        //     \ /
        //      2
        int V2 = 3;
        Graph graph2 = new Graph(V2);
        addEdge(graph2, 0, 1, 1);
        addEdge(graph2, 1, 2, 2);
        addEdge(graph2, 0, 2, 3);

        System.out.println("Example 2 (Expected:  3): " + prims(V2, graph2, 0));
        // MST edges: 0-1(1), 1-2(2) = 3


        // ==============================================
        // Example 3: Linear chain
        // ==============================================
        // 0 --4-- 1 --3-- 2 --2-- 3
        int V3 = 4;
        Graph graph3 = new Graph(V3);
        addEdge(graph3, 0, 1, 4);
        addEdge(graph3, 1, 2, 3);
        addEdge(graph3, 2, 3, 2);

        System.out.println("Example 3 (Expected:  9): " + prims(V3, graph3, 0));
        // MST edges: 0-1(4), 1-2(3), 2-3(2) = 9


        // ==============================================
        // Example 4: Square — skips the expensive edge
        // ==============================================
        //  0 --1-- 1
        //  |       |
        //  5       2
        //  |       |
        //  2 --3-- 3
        int V4 = 4;
        Graph graph4 = new Graph(V4);
        addEdge(graph4, 0, 1, 1);
        addEdge(graph4, 1, 3, 2);
        addEdge(graph4, 3, 2, 3);
        addEdge(graph4, 0, 2, 5); // skipped — 0-2(5) costs more than 0-1-3-2

        System.out.println("Example 4 (Expected:  6): " + prims(V4, graph4, 0));
        // MST edges: 0-1(1), 1-3(2), 3-2(3) = 6


        // ==============================================
        // Example 5: Single node
        // ==============================================
        int V5 = 1;
        Graph graph5 = new Graph(V5);

        System.out.println("Example 5 (Expected:  0): " + prims(V5, graph5, 0));
        // No edges — MST cost = 0
    }
}