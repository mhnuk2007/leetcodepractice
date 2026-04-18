package graph;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Solution for the Course Schedule II problem (LeetCode 210) using DFS.
 *
 * <p><b>Problem:</b>
 * Given {@code numCourses} and a list of prerequisite pairs, return the ordering
 * of courses to finish all courses. Return an empty array if it is impossible.
 *
 * <p><b>Intuition:</b>
 * An ordering exists if and only if the graph is a DAG. The ordering is a
 * topological sort — for every edge u → v, u appears before v in the result.
 *
 * <p><b>Approach:</b> DFS with three-state tracking (post-order)
 * <ul>
 *     <li>0 — unvisited</li>
 *     <li>1 — in the current DFS path (cycle if seen again)</li>
 *     <li>2 — fully processed (safe to skip)</li>
 * </ul>
 * <p>Nodes are pushed onto a stack after all their neighbours are processed
 * (post-order). Popping the stack yields the topological order.
 *
 * <p><b>Time Complexity:</b> O(V + E) where V = numCourses, E = prerequisites.length.</p>
 * <p><b>Space Complexity:</b> O(V + E) for the adjacency list and recursion depth.</p>
 */
public class CourseScheduleIIDfs {

    /**
     * Returns a valid course ordering, or an empty array if a cycle exists.
     *
     * @param numCourses    total number of courses labelled 0 to numCourses - 1
     * @param prerequisites pairs where {@code prerequisites[i] = [a, b]}
     *                      means course b must be taken before course a
     * @return topological order of courses, or {@code int[0]} if impossible
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }

        int[] state = new int[numCourses];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycleDfs(i, graph, state, stack)) {
                return new int[0]; // Cycle detected — no valid ordering
            }
        }

        int[] order = new int[numCourses];
        int idx = 0;
        while (!stack.isEmpty()) {
            order[idx++] = stack.pop();
        }
        return order;
    }

    /**
     * DFS helper that detects cycles and records post-order onto the stack.
     *
     * <p>Nodes are pushed after all neighbours are fully processed,
     * producing reverse topological order on the stack.
     *
     * @param src   the current vertex being visited
     * @param graph adjacency list
     * @param stat  three-state visit tracker: 0 = unvisited, 1 = in path, 2 = done
     * @param stack accumulates nodes in reverse topological order
     * @return {@code true} if a cycle is detected from {@code src}
     */
    private static boolean hasCycleDfs(int src, List<List<Integer>> graph,
                                       int[] stat, Deque<Integer> stack) {
        stat[src] = 1;

        for (int neighbor : graph.get(src)) {
            if (stat[neighbor] == 1) return true;                              // Back edge → cycle
            if (stat[neighbor] == 0 && hasCycleDfs(neighbor, graph, stat, stack)) return true;
        }

        stat[src] = 2;
        stack.push(src); // Post-order: push after all neighbours are done
        return false;
    }

    /**
     * Main method with test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Example 1: Linear chain — one valid order
        // 0 → 1 → 2 → 3
        int numCourses1 = 4;
        int[][] prerequisites1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Example 1 (Expected: [0,1,2,3]): "
                + java.util.Arrays.toString(findOrder(numCourses1, prerequisites1)));

        // Example 2: Cycle — impossible
        // 0 → 1 → 0
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Example 2 (Expected: []):         "
                + java.util.Arrays.toString(findOrder(numCourses2, prerequisites2)));

        // Example 3: No prerequisites — any order valid
        int numCourses3 = 3;
        int[][] prerequisites3 = {};
        System.out.println("Example 3 (Expected: any order):  "
                + java.util.Arrays.toString(findOrder(numCourses3, prerequisites3)));

        // Example 4: DAG — multiple valid orders
        //   5 → 0
        //   5 → 2
        //   4 → 0
        //   4 → 1
        //   2 → 3
        //   3 → 1
        int numCourses4 = 6;
        int[][] prerequisites4 = {{1, 0}, {2, 5}, {0, 5}, {0, 4}, {1, 4}, {3, 2}, {1, 3}};
        System.out.println("Example 4 (Expected valid order): "
                + java.util.Arrays.toString(findOrder(numCourses4, prerequisites4)));

        // Example 5: Disconnected — cycle in one component
        // Component A: 0 → 1 (no cycle)
        // Component B: 2 → 3 → 2 (cycle)
        int numCourses5 = 4;
        int[][] prerequisites5 = {{1, 0}, {3, 2}, {2, 3}};
        System.out.println("Example 5 (Expected: []):         "
                + java.util.Arrays.toString(findOrder(numCourses5, prerequisites5)));

        // Example 6: Single course, no prerequisites
        int numCourses6 = 1;
        int[][] prerequisites6 = {};
        System.out.println("Example 6 (Expected: [0]):        "
                + java.util.Arrays.toString(findOrder(numCourses6, prerequisites6)));
    }
}