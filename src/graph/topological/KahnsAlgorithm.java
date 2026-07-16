package graph;

import java.util.*;

public class KahnsAlgorithm {
    static class Graph{
        private int v;
        private final List<List<Integer>> adj;
        Graph(int v){
            this.v = v;
            adj = new ArrayList<>();
            for(int i = 0; i < v; i++) adj.add(new ArrayList<>());
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

    private static List<Integer> topoSort(Graph graph){
        int n = graph.size();

        int[] indegree = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        // compute indegree
        for (int i = 0; i < n; i++) {
            for (int next : graph.getNeighbours(i) ){
                indegree[next]++;
                System.out.println(Arrays.toString(indegree));
            }
        }
        // add all nodes with indegree 0 into queue
        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0) queue.offer(i);
        }
        System.out.println(Arrays.toString(indegree));

        // Kahn's Algorithm BFS
        while (!queue.isEmpty()){
            int curr = queue.poll();
            result.add(curr);
            for (int neigh : graph.getNeighbours(curr)){
                indegree[neigh]--;
                if(indegree[neigh] == 0) queue.offer(neigh);
            }
        }
        if (result.size() != n) {
            return List.of(); // no topological ordering exists
        }
        return result;
    }
    public static void main(String[] args) {

        Graph graph = new Graph(6);
        graph.addEdge(5,0);
        graph.addEdge(5,2);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(2,3);
        graph.addEdge(3,1);



        List<Integer> order = topoSort(graph);

        if (order.isEmpty()) {
            System.out.println("Graph contains a cycle. Topological sort not possible.");
        } else {
            for (int node : order) {
                System.out.print(node + " ");
            }
        }


    }
}
