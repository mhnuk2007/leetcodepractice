import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinCostToConnectAllPoints {
    private record Edge(int src, int dst, int wt) {
    }
    public static int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>();
        int vertices = points.length;

        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, dist));
            }

        }

        return kruskal(edges, vertices);
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int[] rank, int pu, int pv) {
        if (pu == pv) return;
        if (rank[pu] < rank[pv]) parent[pu] = pv;
        else if (rank[pu] > rank[pv]) parent[pv] = pu;
        else {
            parent[pv] = pu;
            rank[pu]++;
        }

    }


    private static int kruskal(List<Edge> edges, int vertices) {

        Collections.sort(edges, (a,b)-> Integer.compare(a.wt(), b.wt()));
        int[] parent = new int[vertices];
        int[] rank = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int minWt = 0;
        int edgesUsed = 0;

        for (Edge edge : edges) {
            int pu = find(parent, edge.src());
            int pv = find(parent, edge.dst());

            if (pu != pv) {
                minWt += edge.wt();
                union(parent, rank, pu, pv);
                edgesUsed++;
            }

            if (edgesUsed == vertices - 1) break;

        }

        return minWt;
    }

    public static void main(String[] args) {
        int points[][] = {
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        };
        System.out.println(minCostConnectPoints(points));


    }
}
