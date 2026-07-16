package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphSimple {
    private int v;
    private List<List<Integer>> adj;

    public GraphSimple(int v){
        this.v = v;
        adj = new ArrayList<>();
        for(int i = 0; i < v; i++){
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public List<Integer> getNeighbours(int node){
        return adj.get(node);
    }

    public int size(){
        return v;
    }

}
