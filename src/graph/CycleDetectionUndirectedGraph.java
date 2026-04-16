package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for detecting cycles in an Undirected Graph.
 *
 * <p><b>Definition:</b>
 * A cycle in an undirected graph exists when there is a path from a vertex
 * back to itself without retracing any edge.
 *
 * <p><b>Approaches:</b>
 * <ul>
 *     <li><b>DFS:</b> Track visited nodes and parent; if a visited neighbor
 *         is not the parent, a back edge (cycle) is found.</li>
 *     <li><b>Union-Find (DSU):</b> For each edge, if both endpoints already
 *         share the same root, adding the edge creates a cycle.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E) for DFS, O(E · α(V)) for Union-Find</p>
 * <p><b>Space Complexity:</b> O(V)</p>
 */
public class CycleDetectionUndirectedGraph {

    // ─────────────────────────────────────────────────────────────
    // Approach 1: DFS
    // ─────────────────────────────────────────────────────────────

    /**
     * Detects a cycle in an undirected graph using Depth First Search.
     *
     * @param graph adjacency list representation of the graph
     * @param v     number of vertices
     * @return {@code true} if a cycle exists, {@code false} otherwise
     */
    public static boolean hasCycleDFS(List<List<Integer>> graph, int v) {
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Depth First Search helper that checks for back edges.
     *
     * @param node    current node being visited
     * @param parent  parent of the current node in DFS tree
     * @param graph   adjacency list
     * @param visited visited array
     * @return {@code true} if a cycle is detected from this node
     */
    private static boolean dfs(int node, int parent,
                               List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, graph, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                // Visited neighbor that is not the parent → back edge → cycle
                return true;
            }
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Approach 2: Union-Find (Disjoint Set Union)
    // ─────────────────────────────────────────────────────────────

    /**
     * Detects a cycle in an undirected graph using Union-Find with
     * path compression and union by rank.
     *
     * @param edges edge list where each int[] is {u, v}
     * @param v     number of vertices
     * @return {@code true} if a cycle exists, {@code false} otherwise
     */
    public static boolean hasCycleUnionFind(int[][] edges, int v) {
        int[] parent = new int[v];
        int[] rank   = new int[v];

        for (int i = 0; i < v; i++) parent[i] = i;

        for (int[] edge : edges) {
            int pu = find(parent, edge[0]);
            int pv = find(parent, edge[1]);

            if (pu == pv) return true; // Same component → cycle

            union(parent, rank, pu, pv);
        }
        return false;
    }

    /**
     * Finds the root of a node with path compression.
     *
     * @param parent parent array
     * @param x      node to find root of
     * @return root of {@code x}
     */
    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]); // Path compression
        }
        return parent[x];
    }

    /**
     * Merges two components by rank.
     *
     * @param parent parent array
     * @param rank   rank array
     * @param x      root of first component
     * @param y      root of second component
     */
    private static void union(int[] parent, int[] rank, int x, int y) {
        if (rank[x] < rank[y])      parent[x] = y;
        else if (rank[x] > rank[y]) parent[y] = x;
        else { parent[y] = x; rank[x]++; }
    }

    // ─────────────────────────────────────────────────────────────
    // Utility
    // ─────────────────────────────────────────────────────────────

    /**
     * Utility method to build an empty undirected graph.
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
     * Utility method to add an undirected edge to the graph.
     *
     * @param graph adjacency list
     * @param u     first vertex
     * @param v     second vertex
     */
    public static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    // ─────────────────────────────────────────────────────────────
    // Main — test cases
    // ─────────────────────────────────────────────────────────────

    /**
     * Main method with test cases for both approaches.
     */
    public static void main(String[] args) {

        // ==============================================
        // Test Case 1: Graph with a cycle
        // ==============================================
        // 0 - 1 - 3
        // |       |
        // 2 - 4 --+
        int v1 = 5;
        List<List<Integer>> graph1 = createGraph(v1);
        addEdge(graph1, 0, 1);
        addEdge(graph1, 0, 2);
        addEdge(graph1, 1, 3);
        addEdge(graph1, 2, 4);
        addEdge(graph1, 3, 4); // closes the cycle

        int[][] edges1 = {{0,1},{0,2},{1,3},{2,4},{3,4}};

        System.out.println("Test 1 - DFS         (Expected: true):  " + hasCycleDFS(graph1, v1));
        System.out.println("Test 1 - Union-Find  (Expected: true):  " + hasCycleUnionFind(edges1, v1));


        // ==============================================
        // Test Case 2: Tree — no cycle
        // ==============================================
        // 0 - 1 - 3
        //   \ |
        //     2
        int v2 = 4;
        List<List<Integer>> graph2 = createGraph(v2);
        addEdge(graph2, 0, 1);
        addEdge(graph2, 0, 2);
        addEdge(graph2, 1, 3);

        int[][] edges2 = {{0,1},{0,2},{1,3}};

        System.out.println("Test 2 - DFS         (Expected: false): " + hasCycleDFS(graph2, v2));
        System.out.println("Test 2 - Union-Find  (Expected: false): " + hasCycleUnionFind(edges2, v2));


        // ==============================================
        // Test Case 3: Single node, no edges
        // ==============================================
        int v3 = 1;
        List<List<Integer>> graph3 = createGraph(v3);

        int[][] edges3 = {};

        System.out.println("Test 3 - DFS         (Expected: false): " + hasCycleDFS(graph3, v3));
        System.out.println("Test 3 - Union-Find  (Expected: false): " + hasCycleUnionFind(edges3, v3));


        // ==============================================
        // Test Case 4: Disconnected graph with cycle in one component
        // ==============================================
        // Component A: 0 - 1 - 2 - 0 (cycle)
        // Component B: 3 - 4          (no cycle)
        int v4 = 5;
        List<List<Integer>> graph4 = createGraph(v4);
        addEdge(graph4, 0, 1);
        addEdge(graph4, 1, 2);
        addEdge(graph4, 2, 0); // cycle in component A
        addEdge(graph4, 3, 4);

        int[][] edges4 = {{0,1},{1,2},{2,0},{3,4}};

        System.out.println("Test 4 - DFS         (Expected: true):  " + hasCycleDFS(graph4, v4));
        System.out.println("Test 4 - Union-Find  (Expected: true):  " + hasCycleUnionFind(edges4, v4));


        // ==============================================
        // Test Case 5: Two nodes, one edge — no cycle
        // ==============================================
        int v5 = 2;
        List<List<Integer>> graph5 = createGraph(v5);
        addEdge(graph5, 0, 1);

        int[][] edges5 = {{0,1}};

        System.out.println("Test 5 - DFS         (Expected: false): " + hasCycleDFS(graph5, v5));
        System.out.println("Test 5 - Union-Find  (Expected: false): " + hasCycleUnionFind(edges5, v5));


        // ==============================================
        // Test Case 6: Self-loop
        // ==============================================
        int v6 = 3;
        List<List<Integer>> graph6 = createGraph(v6);
        addEdge(graph6, 0, 1);
        graph6.get(2).add(2); // self-loop on node 2

        int[][] edges6 = {{0,1},{2,2}};

        System.out.println("Test 6 - DFS         (Expected: true):  " + hasCycleDFS(graph6, v6));
        System.out.println("Test 6 - Union-Find  (Expected: true):  " + hasCycleUnionFind(edges6, v6));
    }
}