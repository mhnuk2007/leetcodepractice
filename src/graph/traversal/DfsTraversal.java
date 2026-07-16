package graph;

public class DfsTraversal {
    private static void dfs(GraphSimple graph){
        boolean[] visited = new boolean[graph.size()];
        for (int i = 1; i < graph.size(); i++) {
            if(!visited[i]) dfsHelper(graph, i, visited);
        }
    }

    private static void dfsHelper(GraphSimple graph, int u, boolean[] visited) {
        System.out.print(u + " ");
        visited[u] = true;
        for(int v : graph.getNeighbours(u)){
            if(!visited[v]) dfsHelper(graph, v, visited);
        }

    }

    public static void main(String[] args) {
        GraphSimple graph = new GraphSimple(6);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        dfs(graph);
    }
}
