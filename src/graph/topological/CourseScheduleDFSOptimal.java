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
 * <p><b>Approach:</b> Depth-First Search (DFS) with three-state tracking
 * <ul>
 *     <li>Build a directed adjacency list for O(1) neighbour lookup per node.</li>
 *     <li>Perform DFS traversal on the graph.</li>
 *     <li>Use a three-state array ({@code stat}) to track each vertex:
 *         0 = unvisited, 1 = in current DFS path, 2 = fully processed.</li>
 *     <li>If a neighbor has {@code stat == 1}, it is in the current path →
 *         back edge → cycle detected.</li>
 *     <li>If a neighbor has {@code stat == 2}, it is already proven safe → skip.</li>
 *     <li>If any component of the graph contains a cycle, return {@code false}.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E) where V = numCourses, E = prerequisites.length.</p>
 * <p><b>Space Complexity:</b> O(V + E) for the adjacency list and recursion depth.</p>
 */
public class CourseScheduleDFSOptimal {

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
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]); // prereq[1] is prerequisite of prereq[0]
        }

        int[] stat = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (stat[i] == 0 && hasCycleDfs(i, graph, stat)) return false;
        }
        return true;
    }

    /**
     * Recursive DFS helper that detects a cycle via three-state tracking.
     *
     * <p>A cycle is confirmed when a neighbor has {@code stat == 1},
     * meaning it is already part of the current DFS path (back edge).
     * Neighbors with {@code stat == 2} are fully processed and safe — skipped.
     *
     * @param src   the current vertex being visited
     * @param graph adjacency list for O(1) neighbour access
     * @param stat  three-state visit tracker: 0 = unvisited, 1 = in path, 2 = done
     * @return {@code true} if a cycle is detected from {@code src}
     */
    private static boolean hasCycleDfs(int src, List<List<Integer>> graph, int[] stat) {
        stat[src] = 1;

        for (int neighbor : graph.get(src)) {
            if (stat[neighbor] == 1) return true;                               // Back edge → cycle
            if (stat[neighbor] == 0 && hasCycleDfs(neighbor, graph, stat)) return true;
        }

        stat[src] = 2; // Fully processed — proven safe
        return false;
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