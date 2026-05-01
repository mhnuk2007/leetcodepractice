package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 994 - Rotting Oranges
 * <p>
 * Problem:
 * Given an m x n grid where each cell is either:
 *   0 — empty, 1 — fresh orange, 2 — rotten orange
 * Every minute, any fresh orange 4-directionally adjacent to a rotten orange
 * becomes rotten. Return the minimum number of minutes until no fresh orange
 * remains, or -1 if impossible.
 * <p>
 * Approach: Multi-source BFS with per-node timestamp
 * Seed the queue with every initially rotten orange at time=0. As each node
 * is polled, spread rot to valid fresh neighbours at time+1. Track the
 * running maximum timestamp seen — this becomes the answer. Mutating
 * grid[i][j] to 2 on enqueue prevents re-visiting without a separate
 * visited array.
 * <p>
 * Example:
 * grid = [[2,1,1],
 *         [1,1,0],
 *         [0,1,1]]
 * <p>
 * seed  → queue: [(0,0,t=0)]
 * poll (0,0,0) → enqueue (0,1,1),(1,0,1)       minutes=0
 * poll (0,1,1) → enqueue (0,2,2),(1,1,2)       minutes=1
 * poll (1,0,1) → no new fresh neighbours       minutes=1
 * poll (0,2,2) → no fresh neighbours           minutes=2
 * poll (1,1,2) → enqueue (2,1,3)               minutes=2
 * poll (2,1,3) → enqueue (2,2,4)               minutes=3
 * poll (2,2,4) → no fresh neighbours           minutes=4
 * freshCount=0 → return 4 ✅
 * <p>
 * Time  : O(m·n) — every cell enqueued and processed at most once
 * Space : O(m·n) — queue holds at most all cells
 */
public class RottingOranges {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        // Test 1: standard case
        // Expected: 4
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Test 1: " + orangesRotting(grid1));

        // Test 2: impossible — fresh orange isolated
        // Expected: -1
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println("Test 2: " + orangesRotting(grid2));

        // Test 3: no fresh oranges
        // Expected: 0
        int[][] grid3 = {
                {0, 2}
        };
        System.out.println("Test 3: " + orangesRotting(grid3));

        // Test 4: single rotten orange
        // Expected: 0
        int[][] grid4 = {{2}};
        System.out.println("Test 4: " + orangesRotting(grid4));

        // Test 5: single fresh orange — impossible
        // Expected: -1
        int[][] grid5 = {{1}};
        System.out.println("Test 5: " + orangesRotting(grid5));

        // Test 6: all empty
        // Expected: 0
        int[][] grid6 = {{0}};
        System.out.println("Test 6: " + orangesRotting(grid6));

        // Test 7: multi-source — two rotten oranges spreading simultaneously
        // Expected: 2
        int[][] grid7 = {
                {2, 1, 1, 1, 2}
        };
        System.out.println("Test 7: " + orangesRotting(grid7));

        // Test 8: large grid — all fresh reachable
        // Expected: 3
        int[][] grid8 = {
                {2, 1, 0},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Test 8: " + orangesRotting(grid8));

        // Test 9: entire grid rotten from start
        // Expected: 0
        int[][] grid9 = {
                {2, 2, 2},
                {2, 2, 2},
                {2, 2, 2}
        };
        System.out.println("Test 9: " + orangesRotting(grid9));

        // Test 10: single row — chain reaction
        // Expected: 4
        int[][] grid10 = {
                {2, 1, 1, 1, 1}
        };
        System.out.println("Test 10: " + orangesRotting(grid10));
    }

    /**
     * Returns the minimum minutes for all fresh oranges to rot, or -1 if impossible.
     *
     * @param grid m x n grid of 0 (empty), 1 (fresh), 2 (rotten)
     * @return minimum minutes elapsed, or -1 if any fresh orange cannot be reached
     */
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Node> queue = new ArrayDeque<>();
        int freshCount = 0;

        // Step 1: seed queue with all rotten oranges, count fresh
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) queue.offer(new Node(i, j, 0));
                else if (grid[i][j] == 1) freshCount++;
            }
        }

        if (freshCount == 0) return 0;
        if (queue.isEmpty()) return -1;

        // Step 2: BFS — spread rot to fresh neighbours, track max timestamp
        int minutes = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            minutes = Math.max(minutes, node.time);
            for (int[] d : DIRECTIONS) {
                int i = node.row + d[0];
                int j = node.col + d[1];
                if (i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == 1) {
                    grid[i][j] = 2;
                    freshCount--;
                    queue.offer(new Node(i, j, node.time + 1));
                }
            }
        }

        return freshCount == 0 ? minutes : -1;
    }

    /**
     * Represents a cell in the grid with its position and the time it became rotten.
     */
    static class Node {
        int row;
        int col;
        int time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}