package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    static class Edge{
        int v;
        int w;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static void addEdge(List<List<Edge>> graph, int u, int v, int w) {
        graph.get(u).add(new Edge(v, w));
        graph.get(v).add(new Edge(u, w));
    }
    static class Pair implements Comparable<Pair>{
        int node;
        int weight;
        Pair(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static int prim(List<List<Edge>> graph, int src, int n){
        boolean[] inMst = new boolean[n];
        int mstCost = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(src, 0));
        while (!pq.isEmpty()){
            Pair curr = pq.poll();
            int u = curr.node;
            int d = curr.weight;
            if(inMst[u]) continue;
            mstCost+=d;
            inMst[u] = true;
            for(Edge e : graph.get(u)){
                int v = e.v;
                int w = e.w;
                if(!inMst[v])pq.offer(new Pair(v, w));
            }
        }
        return mstCost;
    }

    public static void main(String[] args) {

        int n = 5;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        addEdge(graph,0,1,2);
        addEdge(graph,0,3,6);
        addEdge(graph,1,2,3);
        addEdge(graph,1,3,8);
        addEdge(graph,1,4,5);
        addEdge(graph,2,4,7);
        addEdge(graph,3,4,9);

        System.out.println("MST Weight = " + prim(graph, 0, n));
    }


}
