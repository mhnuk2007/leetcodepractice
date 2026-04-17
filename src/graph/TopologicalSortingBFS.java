package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Utility class for performing Topological Sorting on a Directed Acyclic Graph (DAG)
 * using Kahn's Algorithm (BFS / in-degree approach).
 *
 * <p><b>Definition:</b>
 * Topological Sorting is a linear ordering of vertices such that
 * for every directed edge u → v, vertex u appears before v in the ordering.
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Applicable only to Directed Acyclic Graphs (DAGs)</li>
 *     <li>Computes in-degree for every vertex before traversal begins</li>
 *     <li>Enqueues all vertices with in-degree 0 (no dependencies) as starting points</li>
 *     <li>On processing a vertex, reduces the in-degree of its neighbours;
 *         any neighbour that reaches in-degree 0 is enqueued next</li>
 *     <li>Detects cycles: if the result does not contain all {@code v} vertices,
 *         the graph has a cycle and a full topological order does not exist</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E)</p>
 * <p><b>Space Complexity:</b> O(V) (in-degree array + queue)</p>
 */
public class TopologicalSortingBFS {

    /**
     * Performs topological sorting on the given graph using Kahn's Algorithm.
     *
     * <p>Returns an empty list if the graph contains a cycle.
     *
     * @param graph adjacency list representation of the graph
     * @param v     number of vertices
     * @return list representing topological order, or empty list if a cycle exists
     */
    public static List<Integer> topoSort(List<List<Integer>> graph, int v) {
        int[] inDegree = computeInDegree(graph, v);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If result does not contain all vertices, a cycle exists
        if (result.size() != v) {
            return new ArrayList<>();
        }

        return result;
    }

    /**
     * Computes the in-degree of every vertex in the graph.
     *
     * <p>In-degree is the number of directed edges pointing into a vertex.
     * Vertices with in-degree 0 have no dependencies and are valid starting points.
     *
     * @param graph adjacency list representation of the graph
     * @param v     number of vertices
     * @return array where index {@code i} holds the in-degree of vertex {@code i}
     */
    private static int[] computeInDegree(List<List<Integer>> graph, int v) {
        int[] inDegree = new int[v];
        for (int node = 0; node < v; node++) {
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]++;
            }
        }
        return inDegree;
    }

    /**
     * Utility method to build an empty graph.
     *
     * @param v number of vertices
     * @return adjacency list
     */
    public static List<List<Integer>> createGraph(int v) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Main method with test cases.
     */
    public static void main(String[] args) {

        // =========================
        // Test Case 1: Basic DAG
        // =========================
        int v1 = 6;
        List<List<Integer>> graph1 = createGraph(v1);

        graph1.get(5).add(2);
        graph1.get(5).add(0);
        graph1.get(4).add(0);
        graph1.get(4).add(1);
        graph1.get(2).add(3);
        graph1.get(3).add(1);

        System.out.println("Test 1 (Expected valid order): " + topoSort(graph1, v1));
        // One possible output: [4, 5, 0, 2, 1, 3]


        // =========================
        // Test Case 2: Single Node
        // =========================
        int v2 = 1;
        List<List<Integer>> graph2 = createGraph(v2);

        System.out.println("Test 2 (Expected: [0]): " + topoSort(graph2, v2));


        // =========================
        // Test Case 3: Linear Graph
        // =========================
        // 0 → 1 → 2 → 3
        int v3 = 4;
        List<List<Integer>> graph3 = createGraph(v3);

        graph3.get(0).add(1);
        graph3.get(1).add(2);
        graph3.get(2).add(3);

        System.out.println("Test 3 (Expected: [0, 1, 2, 3]): " + topoSort(graph3, v3));


        // =========================
        // Test Case 4: Multiple Components
        // =========================
        int v4 = 5;
        List<List<Integer>> graph4 = createGraph(v4);

        graph4.get(0).add(1);
        graph4.get(3).add(4);

        System.out.println("Test 4 (Multiple components): " + topoSort(graph4, v4));


        // =========================
        // Test Case 5: No Edges
        // =========================
        int v5 = 3;
        List<List<Integer>> graph5 = createGraph(v5);

        System.out.println("Test 5 (Any order valid): " + topoSort(graph5, v5));


        // =========================
        // Test Case 6: Cycle (Invalid for topo sort)
        // =========================
        int v6 = 3;
        List<List<Integer>> graph6 = createGraph(v6);

        graph6.get(0).add(1);
        graph6.get(1).add(2);
        graph6.get(2).add(0); // cycle

        System.out.println("Test 6 (Cycle - expected: []): " + topoSort(graph6, v6));
        // Note: Unlike DFS, this implementation DETECTS cycles and returns []
    }
}