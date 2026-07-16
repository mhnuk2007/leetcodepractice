package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordAdjacencyList {
    static class Edge {
        int v;
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private static int[] bellmanFord(List<List<Edge>> graph, int src, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;
            for (int u = 0; u < n; u++) {
                for(Edge e : graph.get(u)){
                    int v = e.v;
                    int w = e.w;
                    if(dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        updated = true;
                    }
                }
            }
            if(!updated) break;
        }

        for (int u = 0; u < n; u++) {
            for(Edge e : graph.get(u)){
                int v = e.v;
                int w = e.w;
                if(dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + w) throw new RuntimeException("Negative Cycle detected");
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        // Graph 1 (No negative cycle)
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(2, 4));
        graph.get(1).add(new Edge(2, -4));
        graph.get(1).add(new Edge(4, -1));
        graph.get(2).add(new Edge(3, 2));

        System.out.println("Graph 1:");
        System.out.println(Arrays.toString(bellmanFord(graph, 0, n)));

        // -------------------------------

        // Graph 2 (Contains a negative weight cycle)
        int n2 = 4;
        List<List<Edge>> graph2 = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            graph2.add(new ArrayList<>());
        }

        graph2.get(0).add(new Edge(1, 1));
        graph2.get(1).add(new Edge(2, -1));
        graph2.get(2).add(new Edge(3, -1));
        graph2.get(3).add(new Edge(1, -1)); // Creates cycle: 1 -> 2 -> 3 -> 1

        System.out.println("\nGraph 2:");
        System.out.println(Arrays.toString(bellmanFord(graph2, 0, n2)));
    }
}
