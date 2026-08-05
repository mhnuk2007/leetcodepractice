package graph.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Leetcode: 3310 - Remove Methods from Project
public class RemoveMethodsFromProject {
    public static List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;
        int[] indegree = new int[n];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            indegree[v]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbour : adj.get(curr)) {
                indegree[neighbour]--;
                if (!suspicious[neighbour]) {
                    queue.offer(neighbour);
                    suspicious[neighbour] = true;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        boolean cantRemove = false;
        for (int i = 0; i < n; i++) {
            if (suspicious[i] && indegree[i] > 0) {
                cantRemove = true;
                break;
            }
            ;
            if (!suspicious[i]) result.add(i);
        }

        if (cantRemove) {
            result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
        }

        return result;
    }

    public static List<Integer> remainingMethodsII(int n, int k, int[][] invocations) {
        // build adjacency list
        List<Integer>[] adj = buildAdjacencyList(n, invocations);

        // find suspicious methods
        boolean[] suspicious = findSuspiciousMethods(n, k, adj);

        // check isolation
        boolean isolationBroken = checkIsolation(invocations, suspicious);

        List<Integer> result = new ArrayList<>();

        // if isolation broken return all methods
        if (isolationBroken) {
            allMethods(n, result);
        }

        // else return remaining method
        else {
            remainingMethods(n, suspicious, result);
        }
        return result;

    }

    private static void remainingMethods(int n, boolean[] suspicious, List<Integer> result) {
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) result.add(i);
        }
    }

    private static void allMethods(int n, List<Integer> result) {
        for (int i = 0; i < n; i++) {
            result.add(i);
        }
    }

    private static boolean checkIsolation(int[][] invocations, boolean[] suspicious) {
        boolean isolationBroken = false;
        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];
            if (!suspicious[u] && suspicious[v]) {
                isolationBroken = true;
                break;
            }
        }
        return isolationBroken;
    }

    private static boolean[] findSuspiciousMethods(int n, int k, List<Integer>[] adj) {
        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbour : adj[curr]) {
                if (!suspicious[neighbour]) {
                    suspicious[neighbour] = true;
                    queue.offer(neighbour);
                }
            }
        }
        return suspicious;
    }

    private static List<Integer>[] buildAdjacencyList(int n, int[][] invocations) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : invocations) {
            adj[edge[0]].add(edge[1]);
        }
        return adj;
    }


    public static void main(String[] args) {
        int n = 5, k = 0;
        int[][] invocations = new int[][]{{1, 2}, {0, 2}, {0, 1}, {3, 4}};

        System.out.println(remainingMethods(n, k, invocations));
        System.out.println(remainingMethodsII(n, k, invocations));
    }
}
