package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAdjacencyList {
    static class Edge {
        int v;
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.dist, other.dist);
        }
    }


    private static int[] dijkstra(List<List<Edge>> graph, int src, int vertices) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            int d = curr.dist;

            if (d > dist[u]) continue;

            for(Edge e : graph.get(u)){
                int v = e.v;
                int w = e.w;
                if(dist[v] > d + w){
                    dist[v] = d + w;
                    pq.offer(new Pair(v, dist[v]));
                }
            }

        }
        return dist;
    }

    public static void main(String[] args) {
        int vertices = 6;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(2, 4));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 7));
        graph.get(2).add(new Edge(4, 3));
        graph.get(3).add(new Edge(5, 1));
        graph.get(4).add(new Edge(3, 2));
        graph.get(4).add(new Edge(5, 5));

        System.out.println(Arrays.toString(dijkstra(graph, 0, vertices)));
    }
}