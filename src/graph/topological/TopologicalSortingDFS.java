package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Utility class for performing Topological Sorting on a Directed Acyclic Graph (DAG).
 *
 * <p><b>Definition:</b>
 * Topological Sorting is a linear ordering of vertices such that
 * for every directed edge u → v, vertex u appears before v in the ordering.
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Applicable only to Directed Acyclic Graphs (DAGs)</li>
 *     <li>Uses DFS to ensure dependencies are processed before the node</li>
 *     <li>Stores result using a stack (reverse postorder)</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E)</p>
 * <p><b>Space Complexity:</b> O(V) (stack + visited array)</p>
 */
public class TopologicalSortingDFS {

    /**
     * Performs topological sorting on the given graph.
     *
     * @param graph adjacency list representation of graph
     * @param v     number of vertices
     * @return list representing topological order
     */
    public static List<Integer> topoSort(List<List<Integer>> graph, int v) {
        boolean[] visited = new boolean[v];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * Depth First Search helper function.
     *
     * @param node    current node
     * @param graph   adjacency list
     * @param visited visited array
     * @param stack   stack to store ordering
     */
    private static void dfs(int node, List<List<Integer>> graph,
                            boolean[] visited, Deque<Integer> stack) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, stack);
            }
        }

        // Push after visiting all neighbors (post-order)
        stack.push(node);
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
        // One possible output: [5, 4, 2, 3, 1, 0]


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

        System.out.println("Test 6 (Cycle - output invalid): " + topoSort(graph6, v6));
        // Note: This implementation does NOT detect cycles
    }
}