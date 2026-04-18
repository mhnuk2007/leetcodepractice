package graph;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Solution for the Course Schedule II problem (LeetCode 210) using BFS.
 *
 * <p><b>Problem:</b>
 * Given {@code numCourses} and a list of prerequisite pairs, return a valid
 * ordering of courses to finish all courses. Return an empty array if impossible.
 *
 * <p><b>Intuition:</b>
 * Model courses as a directed graph where edge u → v means
 * "u must be taken before v". A valid ordering exists if and only if
 * the graph contains no cycle (i.e. it is a DAG). The ordering is the
 * topological sort of that DAG.
 *
 * <p><b>Approach:</b> Kahn's Algorithm (BFS / in-degree)
 * <ul>
 *     <li>Build a directed graph and compute in-degrees in a single pass
 *         over the prerequisite pairs.</li>
 *     <li>Enqueue all courses with in-degree 0 (no prerequisites).</li>
 *     <li>Process each course, reducing the in-degree of its dependents;
 *         enqueue any dependent that reaches in-degree 0.</li>
 *     <li>If all courses are processed, return the order; otherwise a cycle
 *         exists — return an empty array.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E) where V = numCourses, E = prerequisites.length</p>
 * <p><b>Space Complexity:</b> O(V + E) for the adjacency list and in-degree array</p>
 */
public class CourseScheduleIIBfs {

    /**
     * Returns a valid course ordering, or an empty array if a cycle exists.
     *
     * @param numCourses    total number of courses labelled 0 to numCourses - 1
     * @param prerequisites pairs where {@code prerequisites[i] = [a, b]}
     *                      means course b must be taken before course a
     * @return topological order of all courses, or {@code int[0]} if impossible
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Single pass — build graph and compute in-degrees simultaneously
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numCourses];
        int processed = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[processed++] = node;

            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return processed == numCourses ? order : new int[0];
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
                + Arrays.toString(findOrder(numCourses1, prerequisites1)));

        // Example 2: Cycle — impossible
        // 0 → 1 → 0
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Example 2 (Expected: []):         "
                + Arrays.toString(findOrder(numCourses2, prerequisites2)));

        // Example 3: No prerequisites — any order valid
        int numCourses3 = 3;
        int[][] prerequisites3 = {};
        System.out.println("Example 3 (Expected: any order):  "
                + Arrays.toString(findOrder(numCourses3, prerequisites3)));

        // Example 4: DAG — multiple valid orders
        //   5 → 0, 5 → 2
        //   4 → 0, 4 → 1
        //   2 → 3, 3 → 1
        int numCourses4 = 6;
        int[][] prerequisites4 = {{1, 0}, {2, 5}, {0, 5}, {0, 4}, {1, 4}, {3, 2}, {1, 3}};
        System.out.println("Example 4 (Expected valid order): "
                + Arrays.toString(findOrder(numCourses4, prerequisites4)));

        // Example 5: Disconnected — cycle in one component
        // Component A: 0 → 1 (no cycle)
        // Component B: 2 → 3 → 2 (cycle)
        int numCourses5 = 4;
        int[][] prerequisites5 = {{1, 0}, {3, 2}, {2, 3}};
        System.out.println("Example 5 (Expected: []):         "
                + Arrays.toString(findOrder(numCourses5, prerequisites5)));

        // Example 6: Single course, no prerequisites
        int numCourses6 = 1;
        int[][] prerequisites6 = {};
        System.out.println("Example 6 (Expected: [0]):        "
                + Arrays.toString(findOrder(numCourses6, prerequisites6)));
    }
}