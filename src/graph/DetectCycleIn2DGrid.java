package graph;

/**
 * Solution for the detect cycles in a 2D grid of characters (Leetcode: 1559).
 *
 * <p><b>Problem Definition:</b>
 * Given a 2D grid, a cycle is a path of length 4 or more that starts and ends
 * at the same cell, moving only in 4 directions (up, down, left, right) and
 * only through cells containing the same character.
 *
 * <p><b>Key Implementation Details:</b>
 * <ul>
 *     <li>Uses Depth First Search (DFS) for traversal.</li>
 *     <li>Maintains a {@code visited} array to track explored cells.</li>
 *     <li>Uses {@code parentRow} and {@code parentCol} to avoid immediately moving back to the predecessor.</li>
 *     <li>Only moves to adjacent cells if they have the same character as the current cell.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(N * M) — each cell is visited at most once.</p>
 * <p><b>Space Complexity:</b> O(N * M) — for the visited array and recursion stack.</p>
 */
public class DetectCycleIn2DGrid {

    /**
     * Main method with comprehensive test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test Case 1: Standard 4x4 square cycle
        char[][] grid1 = {
                {'a', 'a', 'a', 'a'},
                {'a', 'b', 'b', 'a'},
                {'a', 'b', 'b', 'a'},
                {'a', 'a', 'a', 'a'}
        };
        System.out.println("Test 1 (Expected: YES): " + (containsCycle(grid1) ? "YES" : "NO"));

        // Test Case 2: No cycle (Z-shape path)
        char[][] grid2 = {
                {'c', 'c', 'c', 'a'},
                {'a', 'a', 'c', 'a'},
                {'a', 'c', 'c', 'a'},
                {'a', 'a', 'a', 'a'}
        };
        System.out.println("Test 2 (Expected:  NO): " + (containsCycle(grid2) ? "YES" : "NO"));

        // Test Case 3: 2x2 cycle (minimum size)
        char[][] grid3 = {
                {'d', 'd'},
                {'d', 'd'}
        };
        System.out.println("Test 3 (Expected: YES): " + (containsCycle(grid3) ? "YES" : "NO"));

        // Test Case 4: Multiple characters, cycle in one
        char[][] grid4 = {
                {'a', 'b', 'e', 'b'},
                {'b', 'e', 'b', 'a'},
                {'e', 'b', 'a', 'e'},
                {'a', 'a', 'a', 'a'}
        };
        System.out.println("Test 4 (Expected:  NO): " + (containsCycle(grid4) ? "YES" : "NO"));

        // Test Case 5: Complex L-shaped cycle
        char[][] grid5 = {
                {'a', 'a', 'a', 'a', 'a'},
                {'a', 'b', 'b', 'b', 'a'},
                {'a', 'b', 'a', 'b', 'a'},
                {'a', 'b', 'b', 'b', 'a'},
                {'a', 'a', 'a', 'a', 'a'}
        };
        System.out.println("Test 5 (Expected: YES): " + (containsCycle(grid5) ? "YES" : "NO"));
    }

    /**
     * Checks if the given grid contains any cycle of the same characters.
     *
     * @param grid 2D grid of characters
     * @return true if a cycle exists, false otherwise
     */
    public static boolean containsCycle(char[][] grid) {
        int rows = grid.length;
        if (rows == 0) return false;
        int cols = grid[0].length;

        final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    if (isCycleDFS(grid, i, j, -1, -1, visited, DIRECTIONS)) return true;
                }
            }
        }
        return false;
    }

    /**
     * Internal DFS helper to detect cycles.
     *
     * @param grid       the grid to search
     * @param row        current row index
     * @param col        current column index
     * @param parentRow  row index of the cell we came from
     * @param parentCol  column index of the cell we came from
     * @param visited    tracking of visited cells
     * @param directions allowed movement directions
     * @return true if a cycle is found starting from this cell
     */
    private static boolean isCycleDFS(char[][] grid, int row, int col, int parentRow, int parentCol,
                                      boolean[][] visited, int[][] directions) {
        visited[row][col] = true;

        for (int[] d : directions) {
            int nr = row + d[0];
            int nc = col + d[1];

            // 1. Boundary check and Character match check
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == grid[row][col]) {
                
                // 2. Skip the cell we just came from
                if (nr == parentRow && nc == parentCol) {
                    continue;
                }
                
                // 3. If neighbor is already visited and is NOT the parent, it's a cycle
                if (visited[nr][nc]) {
                    return true;
                }
                
                // 4. Recurse
                if (isCycleDFS(grid, nr, nc, row, col, visited, directions)) {
                    return true;
                }
            }
        }
        return false;
    }
}
