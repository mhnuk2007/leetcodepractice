import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    public static List<List<int[]>> buildGraph(int[][] edges, int v){
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) adj.add(new ArrayList<>());

        for(int[] edge : edges) adj.get(edge[0]).add(new int[]{edge[1], edge[2]});

        return adj;
    }

    public static int dijksta(List<List<int[]>> adj, int src){
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        minHeap.offer(new int[]{src, 0});

        while (!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int currNode = curr[0];
            int currDist = curr[1];

            if(currDist > dist[currNode]) continue;
            for (int[] edge : adj.get(currNode)){
                int node = edge[0];
                int weight = edge[1];
                if(dist[node] > currDist + weight){
                    dist[node] = currDist + weight;
                    minHeap.offer(new int[]{node, dist[node]});
                }
            }
        }
        int max = 0;

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, dist[i]);
        }

        return max;
    }
    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = buildGraph(times, n);
        return dijksta(adj, k);


    }

    public static void main(String[] args) {
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int n = 4;
        int k = 2;

        System.out.println(networkDelayTime(times, n, k));
    }
}

