package graph;

import java.util.ArrayList;
import java.util.List;

public class DetectCycleDirectedDFS {
    static class Graph{
        int v;
        List<List<Integer>> adj;
        public Graph(int v){
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

    private static boolean isCycle(Graph graph){
        int n = graph.size();
        boolean[] visited = new boolean[n];
        boolean[] recPath = new boolean[n];
        for (int i = 1; i < n; i++) {
            if(!visited[i]){
                if(isCycleDirDFS(graph, i, visited, recPath)) return true;
            }
        }
        return false;

    }

    private static boolean isCycleDirDFS(Graph graph, int u, boolean[] visited, boolean[] recPath) {
        visited[u] = true;
        recPath[u] = true;
        for(int v : graph.getNeighbours(u)){
            if(!visited[v]){
                if(isCycleDirDFS(graph, v, visited, recPath)) return true;
            }
            else if(recPath[v]) return true;
        }
        recPath[u] = false;
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        //graph.addEdge(3,1);
        graph.addEdge(3,4);
        graph.addEdge(4,5);

        System.out.println("Graph contains cycle: " + isCycle(graph));

    }
}
