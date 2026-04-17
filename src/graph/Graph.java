package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * Adjacency-list representation of a directed graph.
 *
 * <p><b>Structure:</b>
 * <pre>
 * vertex 0 → [2]
 * vertex 1 → [2, 3]
 * vertex 2 → [0, 1, 3]
 * vertex 3 → [1, 2]
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Space: O(V + E) — one list per vertex, one entry per edge</li>
 *   <li>{@link #addEdge(int, int)}: O(1)</li>
 *   <li>{@link #neighbours(int)}: O(degree(v))</li>
 * </ul>
 */
public class Graph {

    // -----------------------------------------------------------------------
    // Edge
    // -----------------------------------------------------------------------

    /**
     * Represents a directed edge from {@code src} to {@code dest}.
     */
    static class Edge {

        /** Source vertex of this edge. */
        final int src;

        /** Destination vertex of this edge. */
        final int dest;

        /**
         * Constructs a directed edge.
         *
         * @param src  source vertex
         * @param dest destination vertex
         */
        public Edge(int src, int dest) {
            this.src  = src;
            this.dest = dest;
        }

        @Override
        public String toString() {
            return src + " → " + dest;
        }
    }

    // -----------------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------------

    /** Number of vertices in this graph. */
    private final int v;

    /** Adjacency list — index is the source vertex. */
    private final List<List<Edge>> adj;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    /**
     * Constructs an empty directed graph with {@code v} vertices and no edges.
     *
     * @param v number of vertices; must be positive
     * @throws IllegalArgumentException if {@code v} is not positive
     */
    public Graph(int v) {
        if (v <= 0) throw new IllegalArgumentException("Vertex count must be positive, got: " + v);
        this.v   = v;
        this.adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // -----------------------------------------------------------------------
    // Graph construction
    // -----------------------------------------------------------------------

    /**
     * Adds a directed edge from {@code src} to {@code dest}.
     *
     * @param src  source vertex
     * @param dest destination vertex
     * @return this graph, for call chaining
     * @throws IllegalArgumentException if either vertex is out of range
     */
    public Graph addEdge(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);
        adj.get(src).add(new Edge(src, dest));
        return this;
    }

    /**
     * Returns an unmodifiable view of all edges leaving {@code vertex}.
     *
     * @param vertex source vertex
     * @return list of outgoing edges
     */
    public List<Edge> neighbours(int vertex) {
        validateVertex(vertex);
        return java.util.Collections.unmodifiableList(adj.get(vertex));
    }

    /** @return number of vertices in this graph */
    public int vertexCount() {
        return v;
    }

    // -----------------------------------------------------------------------
    // BFS
    // -----------------------------------------------------------------------

    /**
     * Breadth-first traversal from {@code start}.
     *
     * <p>Visits vertices level by level. Guarantees shortest path (in hops)
     * in an unweighted graph.
     *
     * <p><b>Trace (start=0):</b>
     * <pre>
     * queue=[0]       visited=[T,F,F,F]
     * poll 0 → enqueue 2                    queue=[2]
     * poll 2 → enqueue 1,3 (0 visited)      queue=[1,3]
     * poll 1 → 2,3 already visited          queue=[3]
     * poll 3 → 1,2 already visited          queue=[]
     * order: 0 2 1 3
     * </pre>
     *
     * @param start source vertex
     * @return vertices in BFS visit order
     */
    public List<Integer> bfs(int start) {
        validateVertex(start);
        boolean[] visited = new boolean[v];
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (Edge e : adj.get(node)) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    queue.offer(e.dest);
                }
            }
        }
        return result;
    }

    /**
     * Breadth-first traversal over all vertices, including disconnected components.
     *
     * @return vertices in BFS visit order across all components
     */
    public List<Integer> bfsAll() {
        boolean[] visited = new boolean[v];
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    result.add(node);

                    for (Edge e : adj.get(node)) {
                        if (!visited[e.dest]) {
                            visited[e.dest] = true;
                            queue.offer(e.dest);
                        }
                    }
                }
            }
        }
        return result;
    }

    // -----------------------------------------------------------------------
    // DFS
    // -----------------------------------------------------------------------

    /**
     * Depth-first traversal from {@code start} — recursive.
     *
     * <p>Goes as deep as possible along each branch before backtracking.
     *
     * <p><b>Trace (start=0):</b>
     * <pre>
     * dfs(0) → visit 0 → dfs(2)
     *   dfs(2) → visit 2 → 0 visited → dfs(1)
     *     dfs(1) → visit 1 → 2 visited → dfs(3)
     *       dfs(3) → visit 3 → 1,2 visited
     * order: 0 2 1 3
     * </pre>
     *
     * @param start source vertex
     * @return vertices in DFS visit order
     */
    public List<Integer> dfs(int start) {
        validateVertex(start);
        boolean[] visited = new boolean[v];
        List<Integer> result = new ArrayList<>();
        dfsRecursive(start, visited, result);
        return result;
    }

    /**
     * Depth-first traversal from {@code start} — iterative using an explicit stack.
     *
     * <p>Avoids stack overflow on very deep graphs. May produce a different
     * visit order than recursive DFS — both are valid depth-first traversals.
     *
     * <p><b>Trace (start=0):</b>
     * <pre>
     * stack=[0]
     * pop 0 → visit 0 → push 2               stack=[2]
     * pop 2 → visit 2 → push 1,3             stack=[1,3]
     * pop 3 → visit 3 → 1,2 visited          stack=[1]
     * pop 1 → visit 1 → 2,3 visited          stack=[]
     * order: 0 2 3 1
     * </pre>
     *
     * @param start source vertex
     * @return vertices in DFS visit order
     */
    public List<Integer> dfsIterative(int start) {
        validateVertex(start);
        boolean[] visited = new boolean[v];
        List<Integer> result = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited[node]) continue;
            visited[node] = true;
            result.add(node);

            for (Edge e : adj.get(node)) {
                if (!visited[e.dest]) {
                    stack.push(e.dest);
                }
            }
        }
        return result;
    }

    /**
     * Depth-first traversal over all vertices, including disconnected components.
     *
     * @return vertices in DFS visit order across all components
     */
    public List<Integer> dfsAll() {
        boolean[] visited = new boolean[v];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfsRecursive(i, visited, result);
            }
        }
        return result;
    }

    // -----------------------------------------------------------------------
    // Print
    // -----------------------------------------------------------------------

    /**
     * Prints all adjacency lists in {@code vertex → [dest, dest, ...]} format.
     */
    public void printGraph() {
        for (int i = 0; i < v; i++) {
            System.out.print(i + " → ");
            for (Edge e : adj.get(i)) {
                System.out.print(e.dest + " ");
            }
            System.out.println();
        }
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    private void dfsRecursive(int node, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);
        for (Edge e : adj.get(node)) {
            if (!visited[e.dest]) {
                dfsRecursive(e.dest, visited, result);
            }
        }
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= v) {
            throw new IllegalArgumentException(
                    "Vertex " + vertex + " out of range [0, " + (v - 1) + "]");
        }
    }

    // -----------------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        Graph graph = new Graph(4)
                .addEdge(0, 2)
                .addEdge(1, 2)
                .addEdge(1, 3)
                .addEdge(2, 0)
                .addEdge(2, 1)
                .addEdge(2, 3)
                .addEdge(3, 1)
                .addEdge(3, 2);

        System.out.println("=== Adjacency list ===");
        graph.printGraph();

        System.out.println("\n=== BFS from vertex 0 ===");
        System.out.println(graph.bfs(0));

        System.out.println("\n=== DFS from vertex 0 (recursive) ===");
        System.out.println(graph.dfs(0));

        System.out.println("\n=== DFS from vertex 0 (iterative) ===");
        System.out.println(graph.dfsIterative(0));

        // Disconnected graph
        Graph disconnected = new Graph(5)
                .addEdge(0, 1)
                .addEdge(1, 0)
                .addEdge(3, 4)
                .addEdge(4, 3);

        System.out.println("\n=== Disconnected — BFS all components ===");
        System.out.println(disconnected.bfsAll());

        System.out.println("\n=== Disconnected — DFS all components ===");
        System.out.println(disconnected.dfsAll());
    }
}