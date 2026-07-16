package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][1];
            int v = prerequisites[i][0];
            graph.get(u).add(v);
        }

        int[] state = new int[numCourses];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && dfs(graph, i, state, stack)) return new int[0];
        }

        int[] order = new int[numCourses];
        int idx = 0;
        while (!stack.isEmpty()) order[idx++] = stack.pop();
        return order;
    }

    private static boolean dfs(List<List<Integer>> graph, int u, int[] state, Deque<Integer> stack) {
        state[u] = 1;
        for (int v : graph.get(u)) {
            if (state[v] == 1) return true;
            if (state[v] == 0 && dfs(graph, v, state, stack)) return true;
        }
        state[u] = 2;
        stack.push(u);
        return false;
    }

    public static void main(String[] args) {

        int numCourses1 = 2;
        int[][] prerequisites1 = {
                {1, 0}
        };

        int[] result1 = CourseScheduleII.findOrder(numCourses1, prerequisites1);
        print(result1);

        int numCourses2 = 4;
        int[][] prerequisites2 = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };

        int[] result2 = CourseScheduleII.findOrder(numCourses2, prerequisites2);
        print(result2);

        int numCourses3 = 2;
        int[][] prerequisites3 = {
                {1, 0},
                {0, 1}
        };

        int[] result3 = CourseScheduleII.findOrder(numCourses3, prerequisites3);
        print(result3);
    }

    private static void print(int[] arr) {
        if (arr.length == 0) {
            System.out.println("[] (cycle detected)");
            return;
        }
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


}
