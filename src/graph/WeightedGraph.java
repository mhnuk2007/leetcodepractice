package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

/**
 * WeightedGraph — Weighted Directed Adjacency List Representation
 *
 * <p>Represents a weighted directed graph using an array of {@link ArrayList}s
 * where each index corresponds to a vertex and holds all outgoing edges with
 * their weights.
 *
 * <p><b>Structure:</b>
 * <pre>
 * graph[0] → [(0→1, w=5), (0→2, w=3)]
 * graph[1] → [(1→3, w=2)]
 * graph[2] → [(2→1, w=1), (2→3, w=6)]
 * graph[3] → []
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Space: O(V + E) — one list per vertex, one entry per edge</li>
 *   <li>Add edge: O(1)</li>
 *   <li>Get neighbours: O(degree(v))</li>
 * </ul>
 */
public class WeightedGraph {

    /**
     * Represents a weighted directed edge from {@code src} to {@code dest}.
     */
    static class Edge {

        /** Source vertex of this edge. */
        int src;

        /** Destination vertex of this edge. */
        int dest;

        /** Cost or distance assigned to this edge. */
        int weight;

        /**
         * Constructs a weighted directed edge.
         *
         * @param src    source vertex
         * @param dest   destination vertex
         * @param wt cost or distance of traversing this edge
         */
        public Edge(int src, int dest, int wt) {
            this.src    = src;
            this.dest   = dest;
            this.weight = wt;
        }
    }

    /**
     * Initialises each index of the graph array with an empty {@link ArrayList}.
     *
     * @param graph adjacency list array to initialise; length = number of vertices
     */
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        int v = 4;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);

        graph[0].add(new Edge(0, 1, 5));
        graph[0].add(new Edge(0, 2, 3));
        graph[1].add(new Edge(1, 3, 2));
        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 6));

        System.out.println("=== Weighted adjacency list ===");
        printGraph(graph, v);

        System.out.println("\n=== BFS from vertex 0 ===");
        bfs(graph, v, 0);

        System.out.println("\n=== DFS from vertex 0 (recursive) ===");
        boolean[] visited = new boolean[v];
        dfs(graph, 0, visited);
        System.out.println();

        System.out.println("\n=== DFS from vertex 0 (iterative) ===");
        dfsIterative(graph, v, 0);
    }

    // -----------------------------------------------------------------------
    // Print
    // -----------------------------------------------------------------------

    /**
     * Prints all adjacency lists in src → [(dest, w), ...] format.
     *
     * @param graph adjacency list array
     * @param v     number of vertices
     */
    public static void printGraph(ArrayList<Edge>[] graph, int v) {
        for (int i = 0; i < v; i++) {
            System.out.print(i + " → ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.dest + ", w=" + e.weight + ") ");
            }
            System.out.println();
        }
    }

    // -----------------------------------------------------------------------
    // BFS
    // -----------------------------------------------------------------------

    /**
     * Breadth-first traversal from {@code start}.
     * Visits vertices level by level using a queue.
     * Weight is available during traversal but BFS does not use it for
     * ordering — use Dijkstra for shortest weighted path.
     *
     * <p><b>Trace (start=0):</b>
     * <pre>
     * queue=[0]       visited=[T,F,F,F]
     * poll 0 → enqueue 1(w=5), 2(w=3)     queue=[1,2]
     * poll 1 → enqueue 3(w=2)             queue=[2,3]
     * poll 2 → 1 visited, enqueue 3?      queue=[3]    (3 already visited)
     * poll 3 → no neighbours              queue=[]
     * order: 0 1 2 3
     * </pre>
     *
     * @param graph adjacency list array
     * @param v     number of vertices
     * @param start source vertex
     */
    public static void bfs(ArrayList<Edge>[] graph, int v, int start) {
        boolean[] visited = new boolean[v];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (Edge e : graph[node]) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    queue.offer(e.dest);
                }
            }
        }
        System.out.println();
    }

    // -----------------------------------------------------------------------
    // DFS recursive
    // -----------------------------------------------------------------------

    /**
     * Depth-first traversal — recursive.
     * Goes as deep as possible along each branch before backtracking.
     * Weight is available during traversal for path-cost accumulation.
     *
     * <p><b>Trace (start=0):</b>
     * <pre>
     * dfs(0) → print 0 → dfs(1, w=5)
     *   dfs(1) → print 1 → dfs(3, w=2)
     *     dfs(3) → print 3 → no neighbours
     *   back to dfs(0) → dfs(2, w=3)
     *     dfs(2) → print 2 → 1 visited, 3 visited
     * order: 0 1 3 2
     * </pre>
     *
     * @param graph   adjacency list array
     * @param node    current vertex
     * @param visited visited array shared across recursive calls
     */
    public static void dfs(ArrayList<Edge>[] graph, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (Edge e : graph[node]) {
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    // -----------------------------------------------------------------------
    // DFS iterative
    // -----------------------------------------------------------------------

    /**
     * Depth-first traversal — iterative using an explicit stack.
     * Equivalent to recursive DFS; avoids stack overflow on deep graphs.
     *
     * <p><b>Note:</b> iterative DFS may produce a different visit order than
     * recursive DFS — both are valid depth-first traversals.
     *
     * <p><b>Trace (start=0):</b>
     * <pre>
     * stack=[0]
     * pop 0 → print 0 → push 1(w=5), 2(w=3)   stack=[1,2]
     * pop 2 → print 2 → push 1(visited), 3     stack=[1,3]
     * pop 3 → print 3 → no neighbours          stack=[1]
     * pop 1 → print 1 → push 3(visited)        stack=[]
     * order: 0 2 3 1
     * </pre>
     *
     * @param graph adjacency list array
     * @param v     number of vertices
     * @param start source vertex
     */
    public static void dfsIterative(ArrayList<Edge>[] graph, int v, int start) {
        boolean[] visited = new boolean[v];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited[node]) continue;
            visited[node] = true;
            System.out.print(node + " ");
            for (Edge e : graph[node]) {
                if (!visited[e.dest]) stack.push(e.dest);
            }
        }
        System.out.println();
    }
}