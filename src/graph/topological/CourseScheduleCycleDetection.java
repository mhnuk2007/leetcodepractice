package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for the Course Schedule problem (LeetCode 207) using DFS.
 *
 * <p><b>Problem:</b>
 * Given {@code numCourses} and a list of prerequisite pairs, determine
 * whether it is possible to finish all courses.
 *
 * <p><b>Intuition:</b>
 * Model courses as a directed graph where edge u → v means
 * "u must be taken before v". The courses can all be finished
 * if and only if the graph contains no cycle (i.e. it is a Directed Acyclic Graph - DAG).
 *
 * <p><b>Approach:</b> Depth-First Search (DFS) with Recursion Stack
 * <ul>
 *     <li>Build a directed graph from the prerequisite pairs.</li>
 *     <li>Perform DFS traversal on the graph.</li>
 *     <li>Use a recursion stack ({@code recStack}) to keep track of the vertices
 *         in the current DFS path.</li>
 *     <li>If a neighbor is already in the recursion stack, a cycle is detected.</li>
 *     <li>If any component of the graph contains a cycle, return {@code false}.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E) where V = numCourses, E = prerequisites.length.</p>
 * <p><b>Space Complexity:</b> O(V + E) for the adjacency list and recursion depth.</p>
 */
public class CourseScheduleCycleDetection {

    /**
     * Determines whether all courses can be finished.
     *
     * @param numCourses    total number of courses labelled 0 to numCourses - 1
     * @param prerequisites pairs where {@code prerequisites[i] = [a, b]}
     *                      means course b must be taken before course a
     * @return {@code true} if all courses can be finished, {@code false} if
     *         a cycle makes it impossible
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        return !hasCycle(graph, numCourses);
    }

    /**
     * Checks if the directed graph contains at least one cycle.
     *
     * @param graph adjacency list representation of the graph
     * @param v     number of vertices
     * @return {@code true} if a cycle is found, {@code false} otherwise
     */
    private static boolean hasCycle(List<List<Integer>> graph, int v) {
        boolean[] vis      = new boolean[v];
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
     * Recursive DFS helper that detects a cycle via the recursion stack.
     *
     * <p>A cycle is confirmed when a neighbor is already present in
     * {@code recStack} — meaning it is part of the current DFS path.
     *
     * @param graph    adjacency list representation of the graph
     * @param curr     the current vertex being visited
     * @param vis      tracks all globally visited vertices
     * @param recStack tracks vertices in the current DFS call stack
     * @return {@code true} if a cycle is detected from {@code curr}
     */
    private static boolean isCycle(List<List<Integer>> graph, int curr,
                                   boolean[] vis, boolean[] recStack) {
        vis[curr]      = true;
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
     * Builds a directed adjacency list from the prerequisite pairs.
     *
     * <p>Edge direction: prerequisite → dependent (u must come before v).
     *
     * @param numCourses    number of vertices
     * @param prerequisites edge list where {@code [a, b]} means b → a (b is prerequisite for a)
     * @return adjacency list representation of the graph
     */
    private static List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]); // edge[1] is prerequisite of edge[0]
        }
        return graph;
    }

    /**
     * Main method to demonstrate the Course Schedule cycle detection with various test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Example 1: No cycle — can finish
        // 0 → 1 (take 0 before 1)
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("Example 1 (Expected: true):  " + canFinish(numCourses1, prerequisites1));

        // Example 2: Cycle — cannot finish
        // 0 → 1 → 0 (mutual dependency)
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Example 2 (Expected: false): " + canFinish(numCourses2, prerequisites2));

        // Example 3: No prerequisites
        int numCourses3 = 4;
        int[][] prerequisites3 = {};
        System.out.println("Example 3 (Expected: true):  " + canFinish(numCourses3, prerequisites3));

        // Example 4: Linear chain — no cycle
        // 0 → 1 → 2 → 3
        int numCourses4 = 4;
        int[][] prerequisites4 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Example 4 (Expected: true):  " + canFinish(numCourses4, prerequisites4));

        // Example 5: Cycle in the middle
        // 0 → 1 → 2 → 1 (cycle between 1 and 2)
        int numCourses5 = 3;
        int[][] prerequisites5 = {{1, 0}, {2, 1}, {1, 2}};
        System.out.println("Example 5 (Expected: false): " + canFinish(numCourses5, prerequisites5));

        // Example 6: Disconnected — cycle in one component
        // Component A: 0 → 1 (no cycle)
        // Component B: 2 → 3 → 2 (cycle)
        int numCourses6 = 4;
        int[][] prerequisites6 = {{1, 0}, {3, 2}, {2, 3}};
        System.out.println("Example 6 (Expected: false): " + canFinish(numCourses6, prerequisites6));
    }
}