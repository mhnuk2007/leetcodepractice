package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TopologicalSorting {
    public static void main(String[] args) {
        int v = 6;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        // Directed edges
        graph.get(5).add(2);
        graph.get(5).add(0);
        graph.get(4).add(0);
        graph.get(4).add(1);
        graph.get(2).add(3);
        graph.get(3).add(1);

        System.out.println("Topological Sort (DFS): " + topoSort(graph, v));
    }

    private static List<Integer> topoSort(List<List<Integer>> graph, int v) {
        boolean[] visited = new boolean[v];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < v; i++) {
            if (!visited[i]) dfs(i, graph, visited, stack);
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited, Deque<Integer> stack) {
        visited[node] = true;
        for (int nei : graph.get(node)) {
            if (!visited[nei]) dfs(nei, graph, visited, stack);
        }
        stack.push(node);
    }

}
