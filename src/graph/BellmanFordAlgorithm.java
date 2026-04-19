package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the Bellman-Ford Algorithm using an Adjacency List
 * converted to an Edge List internally.
 *
 * <p><b>Definition:</b>
 * Bellman-Ford finds the shortest path from a source vertex to all other
 * vertices in a weighted directed graph. Unlike Dijkstra's, it correctly
 * handles negative edge weights and detects negative weight cycles.
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Accepts an adjacency list (consistent with {@code DijkstraAlgorithm})
 *         and converts it to an edge list via {@link #buildEdgeList(Graph)}</li>
 *     <li>Relaxes all edges {@code V - 1} times — the maximum number of edges
 *         in any shortest path that contains no cycle</li>
 *     <li>A {@code V}-th relaxation pass that still reduces any distance
 *         confirms a negative weight cycle reachable from {@code src}</li>
 *     <li>The guard {@code dist[e.src] != Integer.MAX_VALUE} prevents integer
 *         overflow when adding a weight to an unreachable node's distance</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V × E) — V - 1 passes, each iterating all edges</p>
 * <p><b>Space Complexity:</b> O(V + E) — adjacency list + edge list + dist array</p>
 */
public class BellmanFordAlgorithm {

    // -----------------------------------------------------------------------
    // Supporting types
    // -----------------------------------------------------------------------

    /**
     * Represents a weighted edge destination.
     */
    static class Pair {
        /** Destination vertex. */
        int node;
        /** Edge weight (may be negative). */
        int weight;

        /**
         * Constructs a weighted edge destination.
         *
         * @param node   destination vertex
         * @param weight edge weight (may be negative)
         */
        Pair(int node, int weight) {
            this.node   = node;
            this.weight = weight;
        }
    }

    /**
     * Weighted directed graph using an adjacency list of {@link Pair}s.
     */
    static class Graph {
        /** Number of vertices. */
        int V;
        /** Adjacency list — index is source vertex, each entry is (dest, weight). */
        List<List<Pair>> adj;

        /**
         * Constructs an empty directed graph with {@code V} vertices.
         *
         * @param V number of vertices
         */
        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            int i = 0;
            while (i < V) {
                adj.add(new ArrayList<>());
                i++;
            }
        }
    }

    /**
     * Represents a directed weighted edge.
     */
    static class Edge {
        /** Source vertex. */
        int src;
        /** Destination vertex. */
        int dest;
        /** Edge weight (may be negative). */
        int weight;

        /**
         * Constructs a directed weighted edge.
         *
         * @param src    source vertex
         * @param dest   destination vertex
         * @param weight edge weight (may be negative)
         */
        Edge(int src, int dest, int weight) {
            this.src    = src;
            this.dest   = dest;
            this.weight = weight;
        }
    }

    // -----------------------------------------------------------------------
    // Graph construction
    // -----------------------------------------------------------------------

    /**
     * Adds a directed weighted edge from {@code u} to {@code v}.
     *
     * @param graph  the graph
     * @param u      source vertex
     * @param v      destination vertex
     * @param weight edge weight (may be negative)
     */
    public static void addEdge(Graph graph, int u, int v, int weight) {
        graph.adj.get(u).add(new Pair(v, weight));
    }

    /**
     * Converts the adjacency list to a flat edge list for Bellman-Ford relaxation.
     *
     * <p>Bellman-Ford is defined in terms of iterating all edges uniformly.
     * Converting upfront means the relaxation loop stays clean and avoids
     * re-deriving source vertices from the adjacency list on every pass.
     *
     * @param graph the graph to convert
     * @return flat list of all directed edges
     */
    public static List<Edge> buildEdgeList(Graph graph) {
        List<Edge> edges = new ArrayList<>();
        int u = 0;
        while (u < graph.V) {
            for (Pair p : graph.adj.get(u)) {
                edges.add(new Edge(u, p.node, p.weight));
            }
            u++;
        }
        return edges;
    }

    // -----------------------------------------------------------------------
    // Bellman-Ford Algorithm
    // -----------------------------------------------------------------------

    /**
     * Computes shortest distances from {@code src} to all other vertices
     * using the Bellman-Ford algorithm and prints the result.
     *
     * <p>Converts the adjacency list to an edge list once via
     * {@link #buildEdgeList(Graph)}, then performs {@code V - 1} relaxation
     * passes. A {@code V}-th pass that still relaxes any edge confirms a
     * negative weight cycle.
     *
     * @param graph the weighted directed graph
     * @param src   source vertex
     */
    public static void bellmanFord(Graph graph, int src) {
        int V = graph.V;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        List<Edge> edges = buildEdgeList(graph);

        // Relax all edges V - 1 times
        for (int pass = 1; pass < V; pass++) {
            for (Edge e : edges) {
                if (dist[e.src] != Integer.MAX_VALUE &&
                        dist[e.dest] > dist[e.src] + e.weight) {
                    dist[e.dest] = dist[e.src] + e.weight;
                }
            }
        }

        // V-th pass — detect negative weight cycle
        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                    dist[e.dest] > dist[e.src] + e.weight) {
                System.out.println("Graph contains a negative cycle");
                return;
            }
        }

        printDistances(dist, src, V);
    }

    // -----------------------------------------------------------------------
    // Utility
    // -----------------------------------------------------------------------

    /**
     * Prints shortest distance from {@code src} to every vertex.
     * Unreachable vertices are printed as {@code INF}.
     *
     * @param dist distance array
     * @param src  source vertex
     * @param V    number of vertices
     */
    private static void printDistances(int[] dist, int src, int V) {
        int j = 0;
        while (j < V) {
            if (dist[j] == Integer.MAX_VALUE) {
                System.out.println(src + " -> " + j + " = INF");
            } else {
                System.out.println(src + " -> " + j + " = " + dist[j]);
            }
            j++;
        }
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
        // Example 1: Your original graph
        // ==============================================
        // 0 --2--> 1 --(-4)--> 2 --2--> 3 --4--> 4
        //  \       |
        //   -4---> 2      1 --(-1)--> 4
        int V1 = 5;
        Graph graph1 = new Graph(V1);
        addEdge(graph1, 0, 1,  2);
        addEdge(graph1, 0, 2,  4);
        addEdge(graph1, 1, 2, -4);
        addEdge(graph1, 1, 4, -1);
        addEdge(graph1, 2, 3,  2);
        addEdge(graph1, 3, 4,  4);

        System.out.println("Example 1 (src=0):");
        bellmanFord(graph1, 0);
        // Expected: 0->0=0, 0->1=2, 0->2=-2, 0->3=0, 0->4=1


        // ==============================================
        // Example 2: Same graph as DijkstraAlgorithm — verify same results
        // ==============================================
        int V2 = 6;
        Graph graph2 = new Graph(V2);
        addEdge(graph2, 0, 1, 2);
        addEdge(graph2, 0, 2, 4);
        addEdge(graph2, 1, 2, 1);
        addEdge(graph2, 1, 3, 7);
        addEdge(graph2, 2, 4, 3);
        addEdge(graph2, 3, 5, 1);
        addEdge(graph2, 4, 5, 5);

        System.out.println("\nExample 2 (same graph as Dijkstra, src=0):");
        bellmanFord(graph2, 0);
        // Expected: 0->0=0, 0->1=2, 0->2=3, 0->3=9, 0->4=6, 0->5=10


        // ==============================================
        // Example 3: Negative edge — key advantage over Dijkstra's
        // ==============================================
        // 0 --4--> 1 --(-2)--> 2
        // 0 --3--> 2
        // Shortest to 2: 0→1→2 = 4 + (-2) = 2
        int V3 = 3;
        Graph graph3 = new Graph(V3);
        addEdge(graph3, 0, 1,  4);
        addEdge(graph3, 0, 2,  3);
        addEdge(graph3, 1, 2, -2);

        System.out.println("\nExample 3 (negative edge, src=0):");
        bellmanFord(graph3, 0);
        // Expected: 0->0=0, 0->1=4, 0->2=2


        // ==============================================
        // Example 4: Negative weight cycle — detected and reported
        // ==============================================
        // 0 --1--> 1 --(-3)--> 2 --1--> 1  (cycle 1→2→1 = -2)
        int V4 = 3;
        Graph graph4 = new Graph(V4);
        addEdge(graph4, 0, 1,  1);
        addEdge(graph4, 1, 2, -3);
        addEdge(graph4, 2, 1,  1);

        System.out.println("\nExample 4 (negative weight cycle):");
        bellmanFord(graph4, 0);
        // Expected: Graph contains a negative cycle


        // ==============================================
        // Example 5: Unreachable node
        // ==============================================
        // 0 --5--> 1    2 (disconnected)
        int V5 = 3;
        Graph graph5 = new Graph(V5);
        addEdge(graph5, 0, 1, 5);

        System.out.println("\nExample 5 (unreachable node, src=0):");
        bellmanFord(graph5, 0);
        // Expected: 0->0=0, 0->1=5, 0->2=INF


        // ==============================================
        // Example 6: Single node
        // ==============================================
        int V6 = 1;
        Graph graph6 = new Graph(V6);

        System.out.println("\nExample 6 (single node, src=0):");
        bellmanFord(graph6, 0);
        // Expected: 0->0=0
    }
}