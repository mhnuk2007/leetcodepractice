package graph;

import java.util.Arrays;

public class Kruskal {
    private static int kruskal(int[][] edges, int n){
        Arrays.sort(edges, (a,b)-> Integer.compare(a[2], b[2]));
        DSU dsu = new DSU(n);
        int minCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if(!dsu.connected(u, v)){
                dsu.union(u, v);
                minCost+=w;
            }
        }
        return minCost;
    }
    public static void main(String[] args) {
        // {source, destination, weight}
        int[][] edges = {
                {0, 1, 4},
                {0, 2, 3},
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 4},
                {3, 4, 2},
                {4, 5, 6}
        };


        int vertices = 6;
        System.out.println("Total MST Weight = " + kruskal(edges, vertices));
    }
}
