package graph;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Solution for the Course Schedule problem (LeetCode 207).
 *
 * <p><b>Problem:</b>
 * Given {@code numCourses} and a list of prerequisite pairs, determine
 * whether it is possible to finish all courses.
 *
 * <p><b>Intuition:</b>
 * Model courses as a directed graph where edge u → v means
 * "u must be taken before v". The courses can all be finished
 * if and only if the graph contains no cycle (i.e. it is a DAG).
 *
 * <p><b>Approach:</b> Kahn's Algorithm (BFS / in-degree)
 * <ul>
 *     <li>Build a directed graph and compute in-degrees in a single pass
 *         over the prerequisite pairs.</li>
 *     <li>Enqueue all courses with in-degree 0 (no prerequisites).</li>
 *     <li>Process each course, reducing the in-degree of its dependents;
 *         enqueue any dependent that reaches in-degree 0.</li>
 *     <li>If all courses are processed, no cycle exists → return {@code true}.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V + E) where V = numCourses, E = prerequisites.length</p>
 * <p><b>Space Complexity:</b> O(V + E) for the adjacency list and in-degree array</p>
 */
public class CourseScheduleOptimal {

    /**
     * Determines whether all courses can be finished.
     *
     * @param numCourses    total number of courses labeled 0 to numCourses - 1
     * @param prerequisites pairs where {@code prerequisites[i] = [a, b]}
     *                      means course b must be taken before course a
     * @return {@code true} if all courses can be finished, {@code false} if
     *         a cycle makes it impossible
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Single pass — build graph and compute in-degrees simultaneously
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;

            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return processed == numCourses;
    }

    /**
     * Main method with test cases.
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