package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOrangesSimple {
    static class Pair {
        int row;
        int col;
        int time;

        Pair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int minutes = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<Pair> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int i = p.row;
            int j = p.col;
            int time = p.time;
            minutes = Math.max(minutes, time);
            // top
            if (i - 1 >= 0 && !visited[i - 1][j] && grid[i - 1][j] == 1) {
                queue.offer(new Pair(i - 1, j, time + 1));
                visited[i - 1][j] = true;
            }
            // Bottom
            if (i + 1 < grid.length && !visited[i + 1][j] && grid[i + 1][j] == 1) {
                queue.offer(new Pair(i + 1, j, time + 1));
                visited[i + 1][j] = true;
            }

            // Left
            if (j - 1 >= 0 && !visited[i][j - 1] && grid[i][j - 1] == 1) {
                queue.offer(new Pair(i, j - 1, time + 1));
                visited[i][j - 1] = true;
            }

            // Right
            if (j + 1 < grid[0].length && !visited[i][j + 1] && grid[i][j + 1] == 1) {
                queue.offer(new Pair(i, j + 1, time + 1));
                visited[i][j + 1] = true;
            }

        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) return -1;
            }
        }
        return minutes;

    }

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        System.out.println("Minutes to rot all oranges: " + orangesRotting(grid));
    }
}
