package graph;

public class DSU {
    int[] parent;
    int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // path compression
    public int find(int x) {
        if (parent[x] != x) return parent[x] = find(parent[x]);
        return parent[x];
    }

    // union by rank
    public void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv) return;
        if (rank[pu] < rank[pv]) parent[pu] = pv;
        else if (rank[pu] > rank[pv]) parent[pv] = pu;
        else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }


    public static void main(String[] args) {
        DSU dsu = new DSU(7);

        dsu.union(0, 1);
        dsu.union(1, 2);
        dsu.union(3, 4);

        System.out.println(dsu.connected(0, 2)); // true
        System.out.println(dsu.connected(0, 4)); // false

        dsu.union(2, 4);

        System.out.println(dsu.connected(0, 4)); // true
    }
}
