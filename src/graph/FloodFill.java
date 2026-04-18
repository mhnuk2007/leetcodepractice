package graph;

import java.util.Arrays;

/**
 * Solution for the Flood Fill problem (LeetCode 733).
 *
 * <p><b>Problem:</b>
 * Given an {@code m x n} grid {@code image}, a starting pixel {@code (sr, sc)},
 * and a {@code color}, perform a flood fill starting from {@code (sr, sc)}.
 * All pixels connected 4-directionally with the same original color as the
 * starting pixel must be recolored to {@code color}.
 *
 * <p><b>Intuition:</b>
 * Classic DFS on a grid. From the source pixel, recursively visit all
 * 4-directional neighbours that share the original color and recolor them.
 * Early exit if the source pixel is already the target color — nothing to do.
 *
 * <p><b>Approach:</b> Depth-First Search (DFS)
 * <ul>
 *     <li>Record the original color at {@code (sr, sc)}.</li>
 *     <li>If original color already equals {@code color}, return immediately.</li>
 *     <li>DFS from {@code (sr, sc)}: recolor the pixel, then recurse on each
 *         valid 4-directional neighbour that still holds the original color.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(M × N) — each pixel visited at most once</p>
 * <p><b>Space Complexity:</b> O(M × N) — recursion depth in the worst case</p>
 */
public class FloodFill {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    /**
     * Performs flood fill on the image starting from pixel {@code (sr, sc)}.
     *
     * @param image 2D grid of pixel colors
     * @param sr    row of the starting pixel
     * @param sc    column of the starting pixel
     * @param color target color to apply
     * @return the modified image after flood fill
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        if (originalColor == color) return image;

        int rows = image.length;
        int cols = image[0].length;
        dfs(image, sr, sc, originalColor, color, rows, cols);

        return image;
    }

    /**
     * DFS helper that recolors connected pixels sharing {@code originalColor}.
     *
     * <p>Guard clause at entry handles out-of-color pixels, keeping each
     * call site clean — no pre-check needed before recursing.
     *
     * @param image         the image grid
     * @param row           current row
     * @param col           current column
     * @param originalColor color to replace
     * @param color         new color to apply
     * @param rows          total row count
     * @param cols          total column count
     */
    private static void dfs(int[][] image, int row, int col,
                            int originalColor, int color,
                            int rows, int cols) {
        if (image[row][col] != originalColor) return;

        image[row][col] = color;

        for (int[] d : DIRECTIONS) {
            int nr = row + d[0];
            int nc = col + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                dfs(image, nr, nc, originalColor, color, rows, cols);
            }
        }
    }

    /**
     * Main method with test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Example 1: Standard fill — spreads from (1,1) replacing all 1s with 2
        // [[1,1,1],    [[2,2,2],
        //  [1,1,0],  →  [2,2,0],
        //  [1,0,1]]     [2,0,1]]
        int[][] image1 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("Example 1 (Expected: [[2,2,2],[2,2,0],[2,0,1]]): "
                + Arrays.deepToString(floodFill(image1, 1, 1, 2)));

        // Example 2: Source already target color — early exit, no change
        int[][] image2 = {{0, 0, 0}, {0, 0, 0}};
        System.out.println("Example 2 (Expected: [[0,0,0],[0,0,0]]):         "
                + Arrays.deepToString(floodFill(image2, 0, 0, 0)));

        // Example 3: Single pixel grid
        int[][] image3 = {{5}};
        System.out.println("Example 3 (Expected: [[9]]):                     "
                + Arrays.deepToString(floodFill(image3, 0, 0, 9)));

        // Example 4: Fill does not cross different-color boundary
        // [[1,1,0],    [[2,2,0],
        //  [1,0,1],  →  [2,0,1],
        //  [0,1,1]]     [0,1,1]]
        int[][] image4 = {{1, 1, 0}, {1, 0, 1}, {0, 1, 1}};
        System.out.println("Example 4 (Expected: [[2,2,0],[2,0,1],[0,1,1]]): "
                + Arrays.deepToString(floodFill(image4, 0, 0, 2)));

        // Example 5: Entire grid same color — all recolored
        int[][] image5 = {{3, 3}, {3, 3}};
        System.out.println("Example 5 (Expected: [[7,7],[7,7]]):             "
                + Arrays.deepToString(floodFill(image5, 0, 0, 7)));

        // Example 6: Corner start
        int[][] image6 = {{0, 0, 0}, {0, 1, 1}};
        System.out.println("Example 6 (Expected: [[1,1,1],[1,1,1]]):         "
                + Arrays.deepToString(floodFill(image6, 1, 1, 1)));
    }
}