package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 797 - All Paths From Source to Target
 * <p>
 * Problem:
 * Given a directed acyclic graph (DAG) of n nodes labeled 0 to n-1, find all
 * possible paths from node 0 to node n-1 and return them in any order.
 * The graph is given as an adjacency list where graph[i] is a list of all
 * nodes you can visit from node i.
 * <p>
 * Approach: DFS with backtracking
 * Explore every path from source to target using DFS. Add the current node to
 * the path, recurse into each neighbour, then remove the current node on the
 * way back. No visited set is needed — the graph is a DAG so cycles are
 * impossible.
 * <p>
 * Example:
 * graph = [[1,2],[3],[3],[]]
 * <p>
 * start at 0 → path=[0]
 *   visit 1  → path=[0,1]
 *     visit 3  → path=[0,1,3] → end, record copy
 *     backtrack→ path=[0,1]
 *   backtrack  → path=[0]
 *   visit 2  → path=[0,2]
 *     visit 3  → path=[0,2,3] → end, record copy
 *     backtrack→ path=[0,2]
 *   backtrack  → path=[0]
 * <p>
 * result: [[0,1,3],[0,2,3]]
 * <p>
 * Time  : O(2^n · n) — up to 2^n paths, each costs O(n) to copy
 * Space : O(n)       — recursion depth and path list, excluding output
 */
public class AllPathsSourceTarget {

    public static void main(String[] args) {
        // Test 1: standard case — two paths
        // Expected: [[0,1,3],[0,2,3]]
        int[][] graph1 = {{1, 2}, {3}, {3}, {}};
        System.out.println("Test 1: " + allPathsSourceTarget(graph1));

        // Test 2: single direct path
        // Expected: [[0,1]]
        int[][] graph2 = {{1}, {}};
        System.out.println("Test 2: " + allPathsSourceTarget(graph2));

        // Test 3: single node — source is target
        // Expected: [[0]]
        int[][] graph3 = {{}};
        System.out.println("Test 3: " + allPathsSourceTarget(graph3));

        // Test 4: linear chain
        // Expected: [[0,1,2,3]]
        int[][] graph4 = {{1}, {2}, {3}, {}};
        System.out.println("Test 4: " + allPathsSourceTarget(graph4));

        // Test 5: fully connected DAG — many paths
        // Expected: [[0,1,3],[0,1,2,3],[0,2,3]]
        int[][] graph5 = {{1, 2}, {2, 3}, {3}, {}};
        System.out.println("Test 5: " + allPathsSourceTarget(graph5));

        // Test 6: wide fan-out — all nodes connect directly to target
        // Expected: [[0,1,4],[0,2,4],[0,3,4]]
        int[][] graph6 = {{1, 2, 3}, {4}, {4}, {4}, {}};
        System.out.println("Test 6: " + allPathsSourceTarget(graph6));
    }

    /**
     * Returns all paths from node 0 to node n-1 in the given DAG.
     *
     * @param graph adjacency list representation of the DAG
     * @return list of all paths from source to target
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(graph, result, 0, graph.length - 1, new ArrayList<>());
        return result;
    }

    /**
     * Explores all paths from curr to end via DFS, backtracking after each branch.
     *
     * @param graph  adjacency list representation of the DAG
     * @param result accumulator for completed paths
     * @param curr   current node being visited
     * @param end    target node (n-1)
     * @param path   current path being built
     */
    private static void dfs(int[][] graph, List<List<Integer>> result,
                             int curr, int end, List<Integer> path) {
        path.add(curr);

        if (curr == end) {
            result.add(new ArrayList<>(path));
        }

        for (int next : graph[curr]) {
            dfs(graph, result, next, end, path);
        }

        path.remove(path.size() - 1);
    }
}