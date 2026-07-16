package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class DetectCycleBFS {
    private static class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    private static boolean hasCycle(GraphSimple graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                if (hasCycleBFS(graph, i, visited)) return true;
            }

        }
        return false;
    }

    private static boolean hasCycleBFS(GraphSimple graph, int u, boolean[] visited) {

        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(u, -1));
        visited[u] = true;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int node = p.node;
            int parent = p.parent;

            for (int v : graph.getNeighbours(node)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new Pair(v, node));
                } else if (v != parent) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GraphSimple graph = new GraphSimple(6);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        System.out.println("Has cycle: " + hasCycle(graph));
    }
}