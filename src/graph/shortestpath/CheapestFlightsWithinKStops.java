import java.util.*;

public class CheapestFlightsWithinKStops {
    private record Flight(int city, int price){
    }
    private record FlightState(int city, int totalCost, int stopsTaken){
    }

    public static List<List<Flight>> buildGraph(int[][] edges, int v){
        List<List<Flight>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) adj.get(edge[0]).add(new Flight(edge[1], edge[2]));
        return adj;
    }
    public static int bfs(List<List<Flight>> adj, int src, int dst, int stops){

        int[] costs = new int[adj.size()];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        Queue<FlightState> queue = new ArrayDeque<>();
        queue.offer(new FlightState(src, 0, 0));
        while (!queue.isEmpty()){
            FlightState curr = queue.poll();
            int currNode = curr.city();
            int currCost = curr.totalCost();
            int currStop = curr.stopsTaken();

            if(currStop > stops) continue;

            for(Flight p : adj.get(currNode)){
                int nextNode = p.city();
                int nextCost = p.price();

                int newCost = currCost + nextCost;

                if(costs[nextNode] > newCost){
                    costs[nextNode] = newCost;
                    queue.offer(new FlightState(nextNode, newCost, currStop+1));
                }
            }


        }
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];

    }
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Flight>> adj = buildGraph(flights, n);
        return bfs(adj, src, dst, k);

    }

    public static void main(String[] args) {
        int n = 4;
        int src = 0;
        int dst = 3;
        int k = 1;
        int[][] flights = {
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}
        };

        System.out.println(findCheapestPrice(n, flights, src, dst, k));

    }
}
