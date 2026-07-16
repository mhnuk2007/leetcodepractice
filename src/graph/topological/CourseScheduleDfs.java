package graph;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleDfs {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        boolean[] recPath = new boolean[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][1];
            int v = prerequisites[i][0];
            graph.get(u).add(v);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(graph, i, visited, recPath)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(List<List<Integer>> graph, int u, boolean[] visited, boolean[] recPath) {
        visited[u] = true;
        recPath[u] = true;
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                if (dfs(graph, v, visited, recPath)) return true;
            } else if (recPath[v]) return true;
        }
        recPath[u] = false;
        return false;
    }

    public static void main(String[] args) {
        int numCourses = 2;

        int[][] prerequisites = {
                {1, 0}
        };

        System.out.println(canFinish(numCourses, prerequisites)); // true

        int[][] prerequisites2 = {
                {1, 0},
                {0, 1}
        };

        System.out.println(canFinish(numCourses, prerequisites2)); // false
    }
}
