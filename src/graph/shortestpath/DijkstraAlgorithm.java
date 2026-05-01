package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of Dijkstra's Algorithm for Single-Source Shortest Path.
 *
 * <p><b>Definition:</b>
 * Dijkstra's Algorithm finds the shortest path from a source vertex to all
 * other vertices in a weighted graph with non-negative edge weights.
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Uses a min-heap (priority queue) to greedily process the closest unvisited node</li>
 *     <li>Maintains a {@code dist[]} array initialised to {@code Integer.MAX_VALUE}</li>
 *     <li>Skips stale heap entries via the guard {@code curr.weight > dist[curr.node]}</li>
 *     <li>Only works with non-negative edge weights — use Bellman-Ford for negative weights</li>
 *     <li>Unreachable nodes are reported as {@code INF}</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O((V + E) log V) — each edge may trigger a heap insertion</p>
 * <p><b>Space Complexity:</b> O(V + E) — adjacency list + dist array + heap</p>
 */
public class DijkstraAlgorithm {

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
         * @param weight edge weight
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
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
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
     * @param weight edge weight (must be non-negative)
     */
    public static void addEdge(Graph graph, int u, int v, int weight) {
        graph.adj.get(u).add(new Pair(v, weight));
    }

    // -----------------------------------------------------------------------
    // Dijkstra's Algorithm
    // -----------------------------------------------------------------------

    /**
     * Computes shortest distances from {@code src} to all other vertices
     * and prints the result.
     *
     * <p>Uses {@code Integer.compare} in the comparator to avoid integer
     * overflow that would occur with simple subtraction on large weights.
     *
     * <p>The stale-entry guard {@code if (curr.weight > dist[curr.node]) continue}
     * discards heap entries that were superseded by a shorter path found later.
     *
     * @param graph the weighted directed graph
     * @param src   source vertex
     */
    public static void dijkstra(Graph graph, int src) {
        int[] dist = new int[graph.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        minHeap.offer(new Pair(src, 0));

        while (!minHeap.isEmpty()) {
            Pair curr = minHeap.poll();

            if (curr.weight > dist[curr.node]) continue; // Stale entry — skip

            for (Pair p : graph.adj.get(curr.node)) {
                if (dist[p.node] > dist[curr.node] + p.weight) {
                    dist[p.node] = dist[curr.node] + p.weight;
                    minHeap.offer(new Pair(p.node, dist[p.node]));
                }
            }
        }

        printDistances(dist, src, graph.V);
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
        int i = 0;
        while (i < V) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(src + " -> " + i + " = INF");
            } else {
                System.out.println(src + " -> " + i + " = " + dist[i]);
            }
            i++;
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
        // Example 1: Your original graph from source 1
        // ==============================================
        // 0 --2--> 1 --1--> 2 --3--> 4 --5--> 5
        //  \       |                            ^
        //   -4---> 2        3 --1--> 5          |
        //          +------7--------> 3 ---------+
        int V1 = 6;
        Graph graph1 = new Graph(V1);
        addEdge(graph1, 0, 1, 2);
        addEdge(graph1, 0, 2, 4);
        addEdge(graph1, 1, 2, 1);
        addEdge(graph1, 1, 3, 7);
        addEdge(graph1, 2, 4, 3);
        addEdge(graph1, 3, 5, 1);
        addEdge(graph1, 4, 5, 5);

        System.out.println("Example 1 (src=1):");
        dijkstra(graph1, 1);
        // Expected: 1->0=INF, 1->1=0, 1->2=1, 1->3=7, 1->4=4, 1->5=8


        // ==============================================
        // Example 2: Same graph from source 0
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

        System.out.println("\nExample 2 (src=0):");
        dijkstra(graph2, 0);
        // Expected: 0->0=0, 0->1=2, 0->2=3, 0->3=9, 0->4=6, 0->5=10


        // ==============================================
        // Example 3: Linear chain
        // ==============================================
        // 0 --1--> 1 --2--> 2 --3--> 3
        int V3 = 4;
        Graph graph3 = new Graph(V3);
        addEdge(graph3, 0, 1, 1);
        addEdge(graph3, 1, 2, 2);
        addEdge(graph3, 2, 3, 3);

        System.out.println("\nExample 3 (linear chain, src=0):");
        dijkstra(graph3, 0);
        // Expected: 0->0=0, 0->1=1, 0->2=3, 0->3=6


        // ==============================================
        // Example 4: Relaxation — shorter path via detour
        // ==============================================
        // 0 --10--> 1
        // 0 --1---> 2 --1--> 1   (shorter: 0→2→1 = 2)
        int V4 = 3;
        Graph graph4 = new Graph(V4);
        addEdge(graph4, 0, 1, 10);
        addEdge(graph4, 0, 2, 1);
        addEdge(graph4, 2, 1, 1);

        System.out.println("\nExample 4 (relaxation, src=0):");
        dijkstra(graph4, 0);
        // Expected: 0->0=0, 0->1=2, 0->2=1


        // ==============================================
        // Example 5: Unreachable nodes
        // ==============================================
        // 0 --5--> 1    2 (disconnected)
        int V5 = 3;
        Graph graph5 = new Graph(V5);
        addEdge(graph5, 0, 1, 5);

        System.out.println("\nExample 5 (unreachable node, src=0):");
        dijkstra(graph5, 0);
        // Expected: 0->0=0, 0->1=5, 0->2=INF
    }
}