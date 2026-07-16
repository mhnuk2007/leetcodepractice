package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    private static class Graph{
        private int v;
        private final List<List<Integer>> adj;
        Graph(int v){
            this.v = v;
            adj = new ArrayList<>();
            for(int i = 0; i < v; i++){
                adj.add(new ArrayList<>());
            }
        }
        void addEdge(int u, int v){
            adj.get(u).add(v);
        }
        int size(){
            return v;
        }
        List<Integer> getNeighbours(int node){
            return adj.get(node);
        }
    }

    private static void topologicalSort(Graph graph){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if(!visited[i]) dfs(graph, i, visited, stack);
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }

    private static void dfs(Graph graph, int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for(int v : graph.getNeighbours(u)){
            if(!visited[v]) dfs(graph, v, visited, stack);
        }
        stack.push(u);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);

        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Topological Order:");
        topologicalSort(graph);
    }
}
