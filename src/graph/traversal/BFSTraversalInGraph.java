package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Utility class for performing Breadth-First Search (BFS) traversal on a graph.
 *
 * <p>BFS explores a graph level by level, visiting all neighbors of a node
 * before moving to the next level.
 *
 * <p>Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges.
 * <p>Space Complexity: O(V) to store the visited array and the queue.
 */
public class BFSTraversalInGraph {

    /**
     * Represents a graph using an adjacency list.
     */
    static class Graph {
        /** Number of vertices in the graph. */
        int v;
        /** Adjacency list representation of the graph. */
        List<List<Integer>> adj;

        /**
         * Constructs a new Graph with the specified number of vertices.
         *
         * @param v number of vertices
         */
        public Graph(int v) {
            this.v = v;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }
    }

    // Private constructor to prevent instantiation
    private BFSTraversalInGraph() {
    }

    /**
     * Main method to demonstrate BFS traversal with various test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Example 1: Basic connected graph
        int v1 = 5;
        Graph graph1 = new Graph(v1);
        addEdge(graph1, 0, 1);
        addEdge(graph1, 0, 2);
        addEdge(graph1, 1, 3);
        addEdge(graph1, 1, 4);

        System.out.println("Example 1: " + traverseGraph(graph1, v1));

        // Example 2: Linear chain
        int v2 = 4;
        Graph graph2 = new Graph(v2);
        addEdge(graph2, 0, 1);
        addEdge(graph2, 1, 2);
        addEdge(graph2, 2, 3);

        System.out.println("Example 2: " + traverseGraph(graph2, v2));

        // Example 3: Disconnected graph
        int v3 = 5;
        Graph graph3 = new Graph(v3);
        addEdge(graph3, 0, 1);
        addEdge(graph3, 1, 2);
        addEdge(graph3, 3, 4);

        System.out.println("Example 3: " + traverseGraph(graph3, v3));

        // Example 4: Single node
        int v4 = 1;
        Graph graph4 = new Graph(v4);

        System.out.println("Example 4: " + traverseGraph(graph4, v4));

        // Example 5: Complete graph
        int v5 = 4;
        Graph graph5 = new Graph(v5);
        addEdge(graph5, 0, 1);
        addEdge(graph5, 0, 2);
        addEdge(graph5, 0, 3);
        addEdge(graph5, 1, 2);
        addEdge(graph5, 1, 3);
        addEdge(graph5, 2, 3);

        System.out.println("Example 5: " + traverseGraph(graph5, v5));

        // Example 6: Graph with cycle
        int v6 = 4;
        Graph graph6 = new Graph(v6);
        addEdge(graph6, 0, 1);
        addEdge(graph6, 1, 2);
        addEdge(graph6, 2, 0);
        addEdge(graph6, 2, 3);

        System.out.println("Example 6: " + traverseGraph(graph6, v6));
    }

    /**
     * Performs BFS traversal on the entire graph, handling disconnected components.
     *
     * @param graph the graph to traverse
     * @param v     the number of vertices in the graph
     * @return a list of vertices in the order they were visited during BFS
     */
    public static List<Integer> traverseGraph(Graph graph, int v) {
        boolean[] visited = new boolean[v];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                performBFS(graph, i, visited, result);
            }
        }

        return result;
    }

    /**
     * Internal helper method to perform BFS starting from a specific node.
     *
     * @param graph   the graph to traverse
     * @param node    the starting vertex for this BFS component
     * @param visited an array tracking visited vertices
     * @param result  the list to which visited vertices are added
     */
    private static void performBFS(Graph graph,
                                   int node,
                                   boolean[] visited,
                                   List<Integer> result) {

        Queue<Integer> queue = new ArrayDeque<>();

        visited[node] = true;
        queue.offer(node);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            for (int neighbor : graph.adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    /**
     * Adds an undirected edge between two vertices.
     *
     * @param graph the graph to which the edge is added
     * @param u     the first vertex
     * @param v     the second vertex
     */
    public static void addEdge(Graph graph, int u, int v) {
        graph.adj.get(u).add(v);
        graph.adj.get(v).add(u);
    }
}
