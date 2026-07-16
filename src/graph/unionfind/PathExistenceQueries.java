package graph;

import java.util.ArrayList;
import java.util.List;

public class PathExistenceQueries {
    static List<List<Integer>> graph;

    public static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(Math.abs(nums[i] - nums[j]) <= maxDiff){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        boolean[] result = new boolean[queries.length];

        for(int q = 0; q < queries.length; q++){
            boolean[] visited = new boolean[n];
            int src =  queries[q][0];
            int dest = queries[q][1];

            result[q] = dfs(src, dest, visited);
        }
        return result;
    }
    private static boolean dfs(int u, int dest, boolean[] visited) {
        if (u == dest) return true;

        visited[u] = true;

        for (int v : graph.get(u)) {
            if (!visited[v] && dfs(v, dest, visited)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        /*
        Input: n = 4, nums = [2,5,6,8],
        maxDiff = 2,
        queries = [[0,1],[0,2],[1,3],[2,3]]
        Output: [false,false,true,true]
         */

        int n = 4;
        int[] nums = {2, 5, 6, 8};
        int maxDiff = 2;
        int[][] queries = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3}
        };

        System.out.println("Output: " + java.util.Arrays.toString(pathExistenceQueries(n, nums, maxDiff, queries)));
    }
}
