package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiStageGraph {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    /**
     * Returns minimum cost from src to dest in a multistage graph.
     * Also prints the optimal path using a next-hop array d[].
     *
     * Approach: Bottom-up DP, process vertices right to left.
     * cost[i] = min cost to reach dest from vertex i.
     * d[i]    = next hop on the optimal path from vertex i.
     *
     * Base case:  cost[dest] = 0
     * Transition: cost[i] = min(edge.weight + cost[edge.dest]) for all edges from i
     * Answer:     cost[src]
     *
     * Time: O(V + E), Space: O(V)
     *
     * minCost(graph, 1, 12) → 16  path: 1 → 2 → 7 → 10 → 12
     */
    public static int minCost(List<Edge>[] graph, int src, int dest) {
        int n = graph.length - 1;

        int[] cost = new int[n + 1];
        int[] d    = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[dest] = 0;

        for (int i = n - 1; i >= src; i--) {
            int minCost = Integer.MAX_VALUE;
            for (Edge edge : graph[i]) {
                if (cost[edge.dest] != Integer.MAX_VALUE) {
                    int candidate = edge.weight + cost[edge.dest];
                    if (candidate < minCost) {
                        minCost = candidate;
                        d[i] = edge.dest;
                    }
                }
            }
            cost[i] = minCost;
        }

        printPath(d, src, dest);
        return cost[src];
    }

    private static void printPath(int[] d, int src, int dest) {
        System.out.print("Path: ");
        int cur = src;
        while (cur != dest) {
            System.out.print(cur + " → ");
            cur = d[cur];
        }
        System.out.println(dest);
    }

    public static void main(String[] args) {
        int v = 12;
        List<Edge>[] graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        // Stage 1
        graph[1].add(new Edge(1, 2, 9));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 4, 3));
        graph[1].add(new Edge(1, 5, 2));

        // Stage 2
        graph[2].add(new Edge(2, 6, 4));
        graph[2].add(new Edge(2, 7, 2));
        graph[2].add(new Edge(2, 8, 1));
        graph[3].add(new Edge(3, 6, 2));
        graph[3].add(new Edge(3, 7, 7));
        graph[4].add(new Edge(4, 8, 11));
        graph[5].add(new Edge(5, 7, 11));
        graph[5].add(new Edge(5, 8, 8));

        // Stage 3
        graph[6].add(new Edge(6, 9,  6));
        graph[6].add(new Edge(6, 10, 5));
        graph[7].add(new Edge(7, 9,  4));
        graph[7].add(new Edge(7, 10, 3));
        graph[8].add(new Edge(8, 10, 5));
        graph[8].add(new Edge(8, 11, 6));

        // Stage 4
        graph[9].add(new Edge(9,  12, 4));
        graph[10].add(new Edge(10, 12, 2));
        graph[11].add(new Edge(11, 12, 5));

        System.out.println("Min cost: " + minCost(graph, 1, 12)); // Expected: 16
    }
}