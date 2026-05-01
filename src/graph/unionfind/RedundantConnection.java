package graph;

import java.util.*;

/**
 * <h2>LC 684 · Redundant Connection</h2>
 *
 * <p><b>Problem:</b> A tree of {@code n} nodes (labelled 1 … n) had one extra
 * undirected edge added. Given the resulting edge list, return the redundant
 * edge — the one whose removal restores the tree.</p>
 *
 * <p><b>Approach 1 — Incremental DFS:</b> Build the graph one edge at a time.
 * Before adding edge (u, v), run DFS from {@code u}. If {@code v} is already
 * reachable, a path u→…→v exists — adding u-v would close a cycle, so this
 * edge is redundant. Otherwise add the edge and continue.</p>
 *
 * <p><b>Approach 2 — Incremental BFS:</b> Identical logic but uses an explicit
 * queue instead of the call stack. Eliminates stack overflow risk on deep
 * graphs and is otherwise equivalent to DFS.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   Input:  [[1,2],[1,3],[2,3]]       Input:  [[1,2],[2,3],[3,4],[1,4],[1,5]]
 *
 *   1 - 2                             5 - 1 - 2
 *    \ /                                  |   |
 *     3   → redundant: [2,3]             4 - 3  → redundant: [1,4]
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(n²) — reachability check per edge, each O(n)
 * worst case.</p>
 * <p><b>Space Complexity:</b> O(n) — adjacency list + visited array.</p>
 *
 * <p><b>Note:</b> Union-Find solves this in O(n · α(n)) ≈ O(n) and is the
 * preferred interview approach. DFS/BFS are included to illustrate the
 * incremental reachability pattern.</p>
 */
public class RedundantConnection {

    // ---------------------------------------------------------------------- //
    //  Approach 1 — DFS                                                       //
    // ---------------------------------------------------------------------- //

    /**
     * Returns the redundant edge using incremental DFS reachability.
     *
     * <p>For each edge (u, v), checks whether {@code v} is already reachable
     * from {@code u} in the graph built so far. If yes, this edge closes a
     * cycle and is returned immediately. Otherwise the edge is added.</p>
     *
     * @param edges array of undirected edges; each edge is {u, v}, 1-indexed
     * @return the redundant edge {u, v}, or an empty array if none found
     */
    private static int[] findRedundantConnectionDFS(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (dfs(adj, u, v, new boolean[n + 1])) return edge;  // cycle detected

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new int[]{};
    }

    /**
     * Returns {@code true} if {@code target} is reachable from {@code src}
     * via DFS over the current adjacency list.
     *
     * @param adj     adjacency list of edges added so far
     * @param src     node to search from
     * @param target  node to find
     * @param visited visited array, fresh per top-level call
     * @return {@code true} if {@code target} is reachable from {@code src}
     */
    private static boolean dfs(List<List<Integer>> adj, int src, int target,
                               boolean[] visited) {
        if (src == target) return true;
        visited[src] = true;

        for (int neighbor : adj.get(src)) {
            if (!visited[neighbor]) {
                if (dfs(adj, neighbor, target, visited)) return true;
            }
        }
        return false;
    }

    // ---------------------------------------------------------------------- //
    //  Approach 2 — BFS                                                       //
    // ---------------------------------------------------------------------- //

    /**
     * Returns the redundant edge using incremental BFS reachability.
     *
     * <p>Identical logic to {@link #findRedundantConnectionDFS} but uses an
     * explicit queue — no stack overflow risk on deep graphs.</p>
     *
     * @param edges array of undirected edges; each edge is {u, v}, 1-indexed
     * @return the redundant edge {u, v}, or an empty array if none found
     */
    private static int[] findRedundantConnectionBFS(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (bfs(adj, u, v)) return edge;                       // cycle detected

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new int[]{};
    }

    /**
     * Returns {@code true} if {@code target} is reachable from {@code src}
     * via BFS over the current adjacency list.
     *
     * @param adj    adjacency list of edges added so far
     * @param src    node to search from
     * @param target node to find
     * @return {@code true} if {@code target} is reachable from {@code src}
     */
    private static boolean bfs(List<List<Integer>> adj, int src, int target) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> queue = new ArrayDeque<>();                 // ArrayDeque over LinkedList

        queue.offer(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == target) return true;

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    // Approach 3 DSU
    private static int[] findRedundantConnectionDSU(int[][] edges) {
        int n = edges.length;
        DSU dsu = new DSU(n + 1);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (dsu.find(u) == dsu.find(v)) return edge;
            dsu.union(u, v);
        }
        return new int[0];

    }

    static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;
            if (rank[px] < rank[py]) parent[px] = py;
            else if (rank[px] > rank[py]) parent[py] = px;
            else {
                parent[py] = px;
                rank[px]++;
            }

        }
    }


    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Runs labelled test cases covering both LC examples, a full
     * cycle closed by the last edge, and a star graph with one extra edge.
     * Both DFS and BFS are verified on every test.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        // ------------------------------------------------------------------ //
        // Test 1: LC example 1                                                //
        //   1 - 2                                                             //
        //    \ /     redundant: [2,3]                                         //
        //     3                                                               //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: LC example 1 ===");
        run(new int[][]{{1, 2}, {1, 3}, {2, 3}}, new int[]{2, 3});

        // ------------------------------------------------------------------ //
        // Test 2: LC example 2                                                //
        //   5 - 1 - 2                                                        //
        //       |   |   redundant: [1,4]                                     //
        //       4 - 3                                                         //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: LC example 2 ===");
        run(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}, new int[]{1, 4});

        // ------------------------------------------------------------------ //
        // Test 3: Cycle closed by last edge                                   //
        //   1 - 2 - 3 - 4 - 5 - 1    redundant: [5,1]                       //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: Cycle closed by last edge ===");
        run(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 1}}, new int[]{5, 1});

        // ------------------------------------------------------------------ //
        // Test 4: Star graph with one extra edge                              //
        //   1 - 2, 1 - 3, 1 - 4, 2 - 3    redundant: [2,3]                 //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: Star + extra edge ===");
        run(new int[][]{{1, 2}, {1, 3}, {1, 4}, {2, 3}}, new int[]{2, 3});
    }

    /**
     * Runs both DFS and BFS on {@code edges}, prints results, and asserts
     * each matches {@code expected}.
     *
     * @param edges    input edge array
     * @param expected expected redundant edge
     */
    private static void run(int[][] edges, int[] expected) {
        int[] dfs = findRedundantConnectionDFS(edges);
        int[] bfs = findRedundantConnectionBFS(edges);

        System.out.println("Expected : " + Arrays.toString(expected));
        System.out.println("DFS      : " + Arrays.toString(dfs)
                + (Arrays.equals(dfs, expected) ? " [PASS]" : " [FAIL]"));
        System.out.println("BFS      : " + Arrays.toString(bfs)
                + (Arrays.equals(bfs, expected) ? " [PASS]" : " [FAIL]"));
        System.out.println();
    }
}