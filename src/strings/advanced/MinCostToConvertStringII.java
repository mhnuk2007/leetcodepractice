package strings.advanced;

import java.util.*;

public class MinCostToConvertStringII{
    static class Edge implements Comparable<Edge>{
        String dest;
        long weight;
        public Edge(String dest, long weight){
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    private static final long INFINITY = Long.MAX_VALUE / 2;
    private static Map<String, List<Edge>> buildGraph(String[] original, String[] changed, int[] cost){
        Map<String, List<Edge>> graph = new HashMap<>();
        for(int i = 0; i < original.length; i++){
            graph.computeIfAbsent(original[i], k -> new ArrayList<>());
        }
        for(int i = 0; i < changed.length; i++){
            graph.get(original[i]).add(new Edge(changed[i], cost[i]));
        }
        return graph;
    }
    private static Set<Integer> collectValidLengths(String[] original){
        Set<Integer> validLengths = new HashSet<>();
        for (int i = 0; i < original.length; i++) {
            validLengths.add(original[i].length());
        }
        return validLengths;
    }

    public static long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        if(cost.length != original.length) return -1;
        Map<String, List<Edge>> graph = buildGraph(original, changed, cost);
        Set<Integer> validLength = collectValidLengths(original);
        long[] memo = new long[source.length() + 1];
        Arrays.fill(memo, -1);
        Map<String, Map<String, Long>> shortestPathCache = new HashMap<>();
        long result = solve(0, source, target, graph, validLength, memo, shortestPathCache);
        return result >= INFINITY ? -1 : result;
    }

    private static Map<String, Long> dijkstra(String src, Map<String, List<Edge>> graph){
        Map<String, Long> dist = new HashMap<>();
        dist.put(src, 0L);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(src, 0));
        while(!pq.isEmpty()){
            Edge current = pq.poll();
            if(current.weight > dist.getOrDefault(current.dest, INFINITY)) continue;
            for (Edge next: graph.getOrDefault(current.dest, Collections.emptyList())){
                long candidate = current.weight + next.weight;
                if (candidate < dist.getOrDefault(next.dest, INFINITY)) {
                    dist.put(next.dest, candidate);
                    pq.offer(new Edge(next.dest, candidate));
                }
            }
        }
        return dist;
    }
    private static long shortestPath(String src, String dest,
                                     Map<String, List<Edge>> graph,
                                     Map<String, Map<String, Long>> shortestPathCache){
        Map<String, Long> distFromSrc = shortestPathCache.computeIfAbsent(src, s -> dijkstra(s, graph));
        return distFromSrc.getOrDefault(dest, INFINITY);

    }
    private static long solve(int idx, String source, String target,
                              Map<String, List<Edge>> graph, Set<Integer> validLengths,
                              long[] memo, Map<String, Map<String, Long>> shortestPathCache){
        if(idx >= source.length()) return 0;
        if(memo[idx] != -1) return memo[idx];
        long best = INFINITY;
        if(source.charAt(idx) == target.charAt(idx)) best = Math.min(best, solve(idx + 1, source, target, graph, validLengths, memo, shortestPathCache));
        for (int len : validLengths) {
            if (idx + len > source.length()) continue;
            String sourceSpan = source.substring(idx, idx + len);
            if(!graph.containsKey(sourceSpan)) continue;
            String targetSpan = target.substring(idx, idx + len);
            long spanCost = shortestPath(sourceSpan, targetSpan, graph, shortestPathCache);
            long remaining = solve(idx + len, source, target, graph, validLengths, memo, shortestPathCache);
            best = Math.min(best, spanCost + remaining);
        }

        return memo[idx] = best;
    }

    public static void main(String[] args) {
        runExample(
                "abcd", "acbe",
                new String[]{"a", "b", "c", "c", "e", "d"},
                new String[]{"b", "c", "b", "e", "b", "e"},
                new int[]{2, 5, 5, 1, 2, 20},
                28
        );

        runExample(
                "abcdefgh", "acdeeghh",
                new String[]{"bcd", "fgh", "thh"},
                new String[]{"cde", "thh", "ghh"},
                new int[]{1, 3, 5},
                9
        );
    }

    private static void runExample(String source, String target, String[] original,
                                   String[] changed, int[] cost, long expected) {
        long actual = minimumCost(source, target, original, changed, cost);
        String status = actual == expected ? "PASS" : "FAIL";
        System.out.printf("[%s] source=%s target=%s -> %d (expected %d)%n",
                status, source, target, actual, expected);
    }
}
