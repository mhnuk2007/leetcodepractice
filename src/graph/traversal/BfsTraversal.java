package graph;

import java.util.ArrayDeque;
import java.util.Queue;
public class BfsTraversal{
    private static void bfs(GraphSimple graph){
        boolean[] visited = new boolean[graph.size()];
        for (int i = 1; i < graph.size(); i++) {
            if(!visited[i]) bfsHelper(graph, i, visited);
        }
    }

    private static void bfsHelper(GraphSimple graph, int u, boolean[] visited) {
        visited[u] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(u);
        while (!queue.isEmpty()){
            int node = queue.poll();
            System.out.print(node + " ");
            for (int v : graph.getNeighbours(node)){
                if(!visited[v]){
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphSimple graph = new GraphSimple(6);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        bfs(graph);
    }
}
