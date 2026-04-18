package graph;

import java.util.*;

/**
 * <h2>Dijkstra's Algorithm — Single-Source Shortest Paths</h2>
 *
 * <p><b>Problem:</b> Given a directed, weighted graph with non-negative edge weights
 * and a source vertex, compute the shortest distance from the source to every
 * reachable vertex, and reconstruct the shortest path to any target.</p>
 *
 * <p><b>Approach:</b> Uses a min-heap (PriorityQueue) to greedily finalize the
 * nearest unvisited vertex at each step. A {@code visited[]} boolean array ensures
 * each vertex is relaxed at most once, giving the lazy-deletion variant of
 * Dijkstra's algorithm.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   0 --2-- 1 --7-- 3
 *   |       |       |
 *   4       1       1
 *   |       |       |
 *   2 --3-- 4 --2-- 5
 *
 *   dijkstra(6, graph, 0, parent) → [0, 2, 3, 8, 6, 9]
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O((V + E) log V) — each edge relaxation may push
 * to the heap; each of the V poll operations costs O(log V).</p>
 * <p><b>Space Complexity:</b> O(V + E) — adjacency list + dist/parent/visited arrays
 * + heap.</p>
 *
 * <p><b>Constraint:</b> All edge weights must be non-negative.</p>
 */
public class DijkstraAlgorithm {

    // ---------------------------------------------------------------------- //
    //  Data types                                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Dual-purpose record used both as an adjacency-list entry (node + edge weight)
     * and as a priority-queue state (node + tentative distance).
     *
     * <p>Keeping a single type avoids casting and keeps heap insertions readable.</p>
     */
    public static class Pair {

        /** Target vertex index. */
        final int node;

        /**
         * Edge weight when stored in the adjacency list;
         * tentative distance from source when stored in the priority queue.
         */
        final int distance;

        /**
         * Constructs a {@code Pair} with the given node and distance/weight.
         *
         * @param node     target vertex index (≥ 0)
         * @param distance edge weight or tentative distance (≥ 0)
         */
        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // ---------------------------------------------------------------------- //
    //  Graph builder                                                           //
    // ---------------------------------------------------------------------- //

    /**
     * Appends a directed edge u → v with weight {@code w} to the adjacency list.
     *
     * <p>For an undirected graph call this twice: once for (u, v, w) and once
     * for (v, u, w).</p>
     *
     * @param graph adjacency list of size ≥ max(u, v) + 1
     * @param u     source vertex of the directed edge
     * @param v     destination vertex of the directed edge
     * @param w     edge weight (must be ≥ 0 for Dijkstra's to be correct)
     */
    public static void addEdge(List<List<Pair>> graph, int u, int v, int w) {
        graph.get(u).add(new Pair(v, w));
    }

    // ---------------------------------------------------------------------- //
    //  Core algorithm                                                          //
    // ---------------------------------------------------------------------- //

    /**
     * Runs Dijkstra's algorithm from {@code src} and returns the shortest-distance
     * array.
     *
     * <p><b>Approach:</b> Lazy-deletion min-heap variant.  Stale heap entries
     * (a node re-pushed after a better path was already finalized) are discarded
     * via the {@code visited[]} guard rather than performing a decrease-key
     * operation, simplifying the implementation while preserving correctness.</p>
     *
     * <p><b>Overflow guard:</b> Before relaxing an edge the method checks
     * {@code dist[node] != Integer.MAX_VALUE} to prevent integer overflow when
     * adding weight to an "infinity" distance.</p>
     *
     * @param V      number of vertices (vertices are labelled 0 … V-1)
     * @param graph  adjacency list; {@code graph.get(u)} holds all (v, weight) pairs
     *               for edges leaving {@code u}
     * @param src    source vertex (0 ≤ src &lt; V)
     * @param parent output array of length V; {@code parent[v]} is set to the
     *               predecessor of {@code v} on the shortest path from {@code src},
     *               or {@code -1} if {@code v} is unreachable or is the source
     * @return {@code dist[]} where {@code dist[v]} is the shortest distance from
     *         {@code src} to {@code v}, or {@link Integer#MAX_VALUE} if unreachable
     * @throws IllegalArgumentException if {@code src} is outside [0, V)
     */
    public static int[] dijkstra(int V, List<List<Pair>> graph, int src, int[] parent) {

        if (src < 0 || src >= V) {
            throw new IllegalArgumentException("Invalid source node: " + src);
        }

        int[] dist       = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist,   Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[src] = 0;

        // Min-heap ordered by tentative distance
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node  = curr.node;

            if (visited[node]) continue;    // stale entry — discard
            visited[node] = true;

            for (Pair neighbor : graph.get(node)) {
                int adj    = neighbor.node;
                int weight = neighbor.distance;

                // Guard against Integer.MAX_VALUE + weight overflow
                if (dist[node] != Integer.MAX_VALUE
                        && dist[node] + weight < dist[adj]) {

                    dist[adj]   = dist[node] + weight;
                    parent[adj] = node;
                    pq.offer(new Pair(adj, dist[adj]));
                }
            }
        }

        return dist;
    }

    // ---------------------------------------------------------------------- //
    //  Path reconstruction                                                     //
    // ---------------------------------------------------------------------- //

    /**
     * Reconstructs the shortest path from the source to {@code target} by
     * walking the {@code parent[]} array backwards, then reversing the result.
     *
     * <p>Call this only when {@code dist[target] != Integer.MAX_VALUE}; if
     * {@code target} is unreachable the returned list will not start at the
     * source vertex.</p>
     *
     * <p><b>Time Complexity:</b> O(V) in the worst case (path length ≤ V).</p>
     *
     * @param parent parent array populated by {@link #dijkstra}
     * @param target destination vertex
     * @return ordered list of vertex indices forming the shortest path,
     *         starting at the source and ending at {@code target}
     */
    public static List<Integer> getPath(int[] parent, int target) {
        List<Integer> path = new ArrayList<>();

        for (int v = target; v != -1; v = parent[v]) {
            path.add(v);
        }

        Collections.reverse(path);
        return path;
    }

    // ---------------------------------------------------------------------- //
    //  Helper                                                                  //
    // ---------------------------------------------------------------------- //

    /**
     * Initialises and returns an empty adjacency list for a graph of {@code V}
     * vertices.
     *
     * @param V number of vertices
     * @return mutable adjacency list with {@code V} empty inner lists
     */
    private static List<List<Pair>> buildGraph(int V) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        return graph;
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Exercises Dijkstra's algorithm across several labelled
     * scenarios: standard path, disconnected graph, single vertex, direct
     * multi-hop comparison, and invalid source guard.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        // ------------------------------------------------------------------ //
        // Test 1: Standard directed graph — distances and path reconstruction //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: Standard Directed Graph (V=6, src=0) ===");
        {
            int V = 6;
            List<List<Pair>> graph = buildGraph(V);

            addEdge(graph, 0, 1, 2);
            addEdge(graph, 0, 2, 4);
            addEdge(graph, 1, 2, 1);
            addEdge(graph, 1, 3, 7);
            addEdge(graph, 2, 4, 3);
            addEdge(graph, 4, 3, 2);
            addEdge(graph, 3, 5, 1);
            addEdge(graph, 4, 5, 5);

            int src      = 0;
            int[] parent = new int[V];
            int[] dist   = dijkstra(V, graph, src, parent);

            int[] expected = {0, 2, 3, 8, 6, 9};
            System.out.println("Distances from source " + src + ":");
            boolean pass = true;
            for (int i = 0; i < V; i++) {
                String got = (dist[i] == Integer.MAX_VALUE) ? "INF" : String.valueOf(dist[i]);
                String status = dist[i] == expected[i] ? "PASS" : "FAIL";
                if (!status.equals("PASS")) pass = false;
                System.out.printf("  %d → %d | expected %-4d got %-4s [%s]%n",
                        src, i, expected[i], got, status);
            }

            int target = 5;
            List<Integer> path = getPath(parent, target);
            System.out.println("Path 0 → 5: " + path);
            System.out.println("Expected:   [0, 1, 2, 4, 3, 5]");
            System.out.println("Overall: " + (pass ? "PASS" : "FAIL"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 2: Disconnected graph — unreachable node must show INF         //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: Disconnected Graph — unreachable vertex ===");
        {
            int V = 4;
            List<List<Pair>> graph = buildGraph(V);

            // Vertex 3 is completely isolated
            addEdge(graph, 0, 1, 1);
            addEdge(graph, 1, 2, 2);

            int[] parent = new int[V];
            int[] dist   = dijkstra(V, graph, 0, parent);

            System.out.println("dist[3] → expected INF, got "
                    + (dist[3] == Integer.MAX_VALUE ? "INF [PASS]" : dist[3] + " [FAIL]"));
            System.out.println("dist[2] → expected 3,   got "
                    + dist[2] + (dist[2] == 3 ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 3: Single vertex — distance to itself is 0                     //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: Single-Vertex Graph ===");
        {
            int V = 1;
            List<List<Pair>> graph = buildGraph(V);
            int[] parent = new int[V];
            int[] dist   = dijkstra(V, graph, 0, parent);

            System.out.println("dist[0] → expected 0, got "
                    + dist[0] + (dist[0] == 0 ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 4: Direct edge vs. longer multi-hop must pick the shorter one  //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: Direct vs Multi-Hop — picks shortest ===");
        {
            int V = 3;
            List<List<Pair>> graph = buildGraph(V);

            // 0 → 2 directly costs 10; via 1 costs 1 + 2 = 3
            addEdge(graph, 0, 1,  1);
            addEdge(graph, 1, 2,  2);
            addEdge(graph, 0, 2, 10);

            int[] parent = new int[V];
            int[] dist   = dijkstra(V, graph, 0, parent);

            System.out.println("dist[2] → expected 3, got "
                    + dist[2] + (dist[2] == 3 ? " [PASS]" : " [FAIL]"));
            System.out.println("Path 0 → 2: " + getPath(parent, 2) + " (expected [0, 1, 2])");
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 5: Zero-weight edges — should be handled without issue         //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 5: Zero-Weight Edges ===");
        {
            int V = 3;
            List<List<Pair>> graph = buildGraph(V);
            addEdge(graph, 0, 1, 0);
            addEdge(graph, 1, 2, 0);

            int[] parent = new int[V];
            int[] dist   = dijkstra(V, graph, 0, parent);

            System.out.println("dist[2] → expected 0, got "
                    + dist[2] + (dist[2] == 0 ? " [PASS]" : " [FAIL]"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 6: Invalid source — must throw IllegalArgumentException        //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 6: Invalid Source (src=-1) ===");
        {
            List<List<Pair>> graph = buildGraph(3);
            int[] parent = new int[3];
            try {
                dijkstra(3, graph, -1, parent);
                System.out.println("FAIL — expected exception not thrown");
            } catch (IllegalArgumentException e) {
                System.out.println("PASS — caught: " + e.getMessage());
            }
        }
    }
}