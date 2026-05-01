package graph;

/**
 * LeetCode 200 - Number of Islands
 * <p>
 * Problem:
 * Given an m x n 2D binary grid of '1's (land) and '0's (water),
 * return the number of islands. An island is surrounded by water and
 * formed by connecting adjacent lands horizontally or vertically.
 * <p>
 * Approach: DFS flood-fill
 * Scan every cell. When an unvisited land cell ('1') is found, increment
 * the island count and DFS to mark the entire connected island as visited.
 * Each DFS call consumes one full island.
 * <p>
 * Example:
 * grid = [["1","1","1","1","0"],
 *         ["1","1","0","1","0"],
 *         ["1","1","0","0","0"],
 *         ["0","0","0","0","0"]]
 * result: 1
 * <p>
 * Time  : O(m·n) — every cell visited at most once
 * Space : O(m·n) — visited array and recursion stack
 */
public class NumberOfIslands {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        // Test 1: single island
        // Expected: 1
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Test 1: " + numIslands(grid1));

        // Test 2: multiple islands
        // Expected: 3
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Test 2: " + numIslands(grid2));

        // Test 3: no land
        // Expected: 0
        char[][] grid3 = {
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        System.out.println("Test 3: " + numIslands(grid3));

        // Test 4: all land
        // Expected: 1
        char[][] grid4 = {
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        System.out.println("Test 4: " + numIslands(grid4));

        // Test 5: single cell land
        // Expected: 1
        char[][] grid5 = {{'1'}};
        System.out.println("Test 5: " + numIslands(grid5));

        // Test 6: single cell water
        // Expected: 0
        char[][] grid6 = {{'0'}};
        System.out.println("Test 6: " + numIslands(grid6));

        // Test 7: diagonal land — not connected
        // Expected: 4
        char[][] grid7 = {
                {'1', '0', '1'},
                {'0', '1', '0'},
                {'1', '0', '1'}
        };
        System.out.println("Test 7: " + numIslands(grid7));
    }

    /**
     * Returns the number of islands in the given grid.
     *
     * @param grid m x n grid of '1' (land) and '0' (water)
     * @return number of distinct islands
     */
    private static int numIslands(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    dfs(grid, visited, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Flood-fills all land cells connected to (row, col), marking them visited.
     *
     * @param grid    m x n grid of '1' (land) and '0' (water)
     * @param visited tracks which cells have been visited
     * @param row     current row
     * @param col     current column
     */
    private static void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if (grid[row][col] == '0' || visited[row][col]) return;
        visited[row][col] = true;
        for (int[] d : DIRECTIONS) {
            dfs(grid, visited, row + d[0], col + d[1]);
        }
    }
}