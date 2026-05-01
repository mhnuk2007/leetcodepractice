package graph;

import java.util.ArrayList;

/**
 * AllPaths — Find all paths between two vertices (modified DFS + backtracking)
 *
 * <p>Finds and prints every simple path from a source vertex to a destination
 * vertex in an undirected graph. A simple path visits no vertex more than once.
 *
 * <p><b>Approach:</b> Modified DFS with backtracking.
 * <ul>
 *   <li>Mark the current vertex visited before exploring neighbours.</li>
 *   <li>On reaching the destination, print the accumulated path string.</li>
 *   <li>After returning from a recursive call, unmark the current vertex
 *       (backtrack) so it is available for other paths.</li>
 * </ul>
 *
 * <p><b>Structure:</b>
 * <pre>
 * graph[0] → [1, 2]       graph[4] → [2, 3, 5]
 * graph[1] → [0, 3]       graph[5] → [3, 4, 6]
 * graph[2] → [0, 4]       graph[6] → [5]
 * graph[3] → [1, 4, 5]
 * </pre>
 *
 * <p><b>Trace (src=0, dest=5):</b>
 * <pre>
 * paths(0) → paths(1) → paths(3) → paths(4) → paths(2) → dead end
 *                                           → paths(5) → print 0 -> 1 -> 3 -> 4 -> 5 ✓
 *                                  paths(5) → print 0 -> 1 -> 3 -> 5 ✓
 *            paths(2) → paths(4) → paths(3) → paths(1) → dead end
 *                                           → paths(5) → print 0 -> 2 -> 4 -> 3 -> 5 ✓
 *                                  paths(5) → print 0 -> 2 -> 4 -> 5 ✓
 * (more paths via deeper combinations...)
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Time:  O(V!) worst case — exponential in dense graphs</li>
 *   <li>Space: O(V)  — visited array + recursion stack depth</li>
 * </ul>
 */
public class AllPaths {

    /**
     * Represents a directed edge from {@code src} to {@code dest}.
     */
    static class Edge {

        /** Source vertex of this edge. */
        int src;

        /** Destination vertex of this edge. */
        int dest;

        /**
         * Constructs a directed edge.
         *
         * @param src  source vertex
         * @param dest destination vertex
         */
        Edge(int src, int dest) {
            this.src  = src;
            this.dest = dest;
        }
    }

    /**
     * Initialises each index of the graph array with an empty {@link ArrayList}.
     *
     * @param graph adjacency list array to initialise; length = number of vertices
     */
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));

        // Find all simple paths from vertex 0 to vertex 5
        System.out.println("All paths from 0 to 5:");
        paths(graph, 0, 5, new boolean[V], "");

        // Find all simple paths from vertex 0 to vertex 6
        System.out.println("\nAll paths from 0 to 6:");
        paths(graph, 0, 6, new boolean[V], "");
    }

    /**
     * Prints all simple paths from {@code curr} to {@code end} using
     * modified DFS with backtracking.
     *
     * <p>Marks {@code curr} visited before recursing and unmarks it after
     * (backtrack) so the same vertex can appear in other paths explored
     * from parent calls.
     *
     * <p><b>Key insight:</b> unlike standard DFS where visited is never
     * reset, here {@code visited[curr] = false} after the loop allows the
     * node to be reused on a completely different path from an ancestor.
     *
     * @param graph   adjacency list array
     * @param curr    current vertex being explored
     * @param end     destination vertex
     * @param visited tracks vertices on the current active path
     * @param path    string representation of the path so far
     */
    public static void paths(ArrayList<Edge>[] graph, int curr, int end,
                             boolean[] visited, String path) {
        // mark current as visited
        visited[curr] = true;

        // add current node to path
        path = path + curr;

        // base case — reached destination
        if (curr == end) {
            System.out.println(path);
            visited[curr] = false; // backtrack
            return;
        }

        // explore neighbours
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge edge = graph[curr].get(i);
            if (!visited[edge.dest]) {
                paths(graph, edge.dest, end, visited, path + " -> ");
            }
        }

        // backtrack — allow this vertex to be used on other paths
        visited[curr] = false;
    }
}