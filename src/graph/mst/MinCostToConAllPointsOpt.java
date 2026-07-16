package graph;

import java.util.Arrays;

public class MinCostToConAllPointsOpt {
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] inMst = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        int minCost = 0;
        for (int i = 0; i < n; i++) {
            int node = -1;
            for (int j = 0; j < n; j++) {
                if (!inMst[j] && (node == -1 || minDist[j] < minDist[node])) node = j;
            }
            inMst[node] = true;
            minCost += minDist[node];

            for (int next = 0; next < n; next++) {
                if (!inMst[next])
                    minDist[next] = Math.min(minDist[next], Math.abs(points[node][0] - points
                            [next][0]) + Math.abs(points[node][1] - points
                            [next][1]));
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[][] points = {
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        };
        System.out.println("Minimum cost to connect all points: " + minCostConnectPoints(points));
    }
}
