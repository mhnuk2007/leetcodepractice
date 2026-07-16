package graph;

import java.util.ArrayList;
import java.util.List;

public class TarjanAlgorithm {
    static int time;
    static List<List<Integer>> result;

    private static void dfs(
            List<List<Integer>> graph,
            int u,
            int parent,
            int[] disc,
            int[] low,
            boolean[] vis
            ){
        vis[u] = true;
        disc[u] = low[u] = ++time;

        for(int v : graph.get(u)){
            if(v == parent) continue;
            if(!vis[v]){
                dfs(graph, v, u, disc, low, vis);
                low[u] = Math.min(low[u], low[v]);
                if(low[v] > disc[u]){
                    result.add(List.of(u,v));
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static List<List<Integer>> tarjan(int n, List<List<Integer>> graph){
        time = 0;
        result= new ArrayList<>();
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!vis[i]) dfs(graph, i, -1, disc, low, vis);
        }
        return result;
    }

    public static void main(String[] args) {

        int n = 5;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);
        addEdge(graph, 1, 3);
        addEdge(graph, 3, 4);

        TarjanAlgorithm obj = new TarjanAlgorithm();
        List<List<Integer>> bridges = obj.tarjan(n, graph);

        System.out.println(bridges);
    }

    static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
}
