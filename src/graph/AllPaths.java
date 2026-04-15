package graph;

import java.util.ArrayList;

public class AllPaths {
    static class Edge {
        int src;
        int dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));

        paths(graph, 0, 5, new boolean[V], "");
    }
    //modified dfs
    public static void paths(ArrayList<Edge>[] graph, int curr, int end, boolean[] visited, String path) {

        // mark current as visited
        visited[curr] = true;

        // add current node to path
        path = path + curr;

        // base case
        if (curr == end) {
            System.out.println(path);
            visited[curr] = false; // backtrack
            return;
        }

        // explore neighbors
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge edge = graph[curr].get(i);

            if (!visited[edge.dest]) {
                paths(graph, edge.dest, end, visited, path + " -> ");
            }
        }

        // backtrack
        visited[curr] = false;
    }
}
