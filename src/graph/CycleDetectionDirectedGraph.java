package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for detecting cycles in a Directed Graph using Depth First Search.
 *
 * <p><b>Definition:</b>
 * A cycle in a directed graph exists when there is a path from a vertex
 * back to itself by following directed edges.
 *
 * <p><b>Key Points:</b>
 * <ul>
 *     <li>Uses two boolean arrays: {@code vis} and {@code stack}</li>
 *     <li>{@code stack} tracks nodes in the current DFS recursion path</li>
 *     <li>A back edge is found when a neighbor is already in {@code stack}</li>
 *     <li>A visited node not in {@code stack} is a cross edge — not a cycle</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E)</p>
 * <p><b>Space Complexity:</b> O(V) (vis + stack arrays)</p>
 */
public class CycleDetectionDirectedGraph {

    /**
     * Detects a cycle in a directed graph using Depth First Search.
     *
     * @param graph adjacency list representation of the graph
     * @param v     number of vertices
     * @return {@code true} if a cycle exists, {@code false} otherwise
     */
    public static boolean hasCycle(List<List<Integer>> graph, int v) {
        boolean[] vis   = new boolean[v];
        boolean[] recStack = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                if (isCycle(graph, i, vis, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Depth First Search helper that tracks the current recursion stack.
     *
     * <p>A cycle is confirmed when a neighbor is already present in the
     * current DFS recursion path (back edge).
     *
     * @param graph adjacency list
     * @param curr  current node being visited
     * @param vis   tracks all globally visited nodes
     * @param recStack tracks nodes in the current DFS call stack
     * @return {@code true} if a cycle is detected from this node
     */
    private static boolean isCycle(List<List<Integer>> graph, int curr,
                                   boolean[] vis, boolean[] recStack) {
        vis[curr]   = true;
        recStack[curr] = true;

        for (int node : graph.get(curr)) {
            if (!vis[node]) {
                if (isCycle(graph, node, vis, recStack)) {
                    return true;
                }
            } else if (recStack[node]) {
                // Visited + in current path → back edge → cycle
                return true;
            }
        }

        recStack[curr] = false; // Remove from current path before backtracking
        return false;
    }

    /**
     * Utility method to build an empty directed graph.
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

        // ==============================================
        // Example 1: No cycle
        // ==============================================
        // 0 → 1
        int v1 = 2;
        List<List<Integer>> graph1 = createGraph(v1);
        graph1.get(0).add(1);

        System.out.println("Example 1 (No cycle):       " + (hasCycle(graph1, v1) ? "Cycle detected" : "No cycle"));


        // ==============================================
        // Example 2: Cycle
        // ==============================================
        // 0 → 1 → 0
        int v2 = 2;
        List<List<Integer>> graph2 = createGraph(v2);
        graph2.get(0).add(1);
        graph2.get(1).add(0);

        System.out.println("Example 2 (Cycle):          " + (hasCycle(graph2, v2) ? "Cycle detected" : "No cycle"));


        // ==============================================
        // Example 3: Linear chain — no cycle
        // ==============================================
        // 0 → 1 → 2 → 3
        int v3 = 4;
        List<List<Integer>> graph3 = createGraph(v3);
        graph3.get(0).add(1);
        graph3.get(1).add(2);
        graph3.get(2).add(3);

        System.out.println("Example 3 (No cycle):       " + (hasCycle(graph3, v3) ? "Cycle detected" : "No cycle"));


        // ==============================================
        // Example 4: Cycle in the middle
        // ==============================================
        // 0 → 1 → 2 → 1 (cycle), 2 → 3
        int v4 = 4;
        List<List<Integer>> graph4 = createGraph(v4);
        graph4.get(0).add(1);
        graph4.get(1).add(2);
        graph4.get(2).add(1);
        graph4.get(2).add(3);

        System.out.println("Example 4 (Cycle):          " + (hasCycle(graph4, v4) ? "Cycle detected" : "No cycle"));


        // ==============================================
        // Example 5: Cross edge — no cycle
        // ==============================================
        // 0 → 1, 0 → 2, 1 → 3, 2 → 3
        // Node 3 reachable via two paths — cross edge, not a back edge
        int v5 = 4;
        List<List<Integer>> graph5 = createGraph(v5);
        graph5.get(0).add(1);
        graph5.get(0).add(2);
        graph5.get(1).add(3);
        graph5.get(2).add(3);

        System.out.println("Example 5 (Cross edge):     " + (hasCycle(graph5, v5) ? "Cycle detected" : "No cycle"));


        // ==============================================
        // Example 6: Disconnected — cycle in one component
        // ==============================================
        // Component A: 0 → 1 (no cycle)
        // Component B: 2 → 3 → 4 → 2 (cycle)
        int v6 = 5;
        List<List<Integer>> graph6 = createGraph(v6);
        graph6.get(0).add(1);
        graph6.get(2).add(3);
        graph6.get(3).add(4);
        graph6.get(4).add(2);

        System.out.println("Example 6 (Disconnected):   " + (hasCycle(graph6, v6) ? "Cycle detected" : "No cycle"));
    }
}