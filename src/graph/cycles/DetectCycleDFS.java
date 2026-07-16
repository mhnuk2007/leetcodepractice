package graph;

public class DetectCycleDFS {
    private static boolean hasCycle(GraphSimple graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                if (hasCycleDfs(graph, i, -1, visited)) return true;
            }
        }
        return false;

    }

    private static boolean hasCycleDfs(GraphSimple graph, int u, int parent, boolean[] visited) {
        visited[u] = true;
        for (int v : graph.getNeighbours(u)) {
            if (!visited[v]) {
                if (hasCycleDfs(graph, v, u, visited)) return true;
            } else if (v != parent)
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        GraphSimple graph = new GraphSimple(6);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        System.out.println("Has cycle: " + hasCycle(graph));
    }
}
