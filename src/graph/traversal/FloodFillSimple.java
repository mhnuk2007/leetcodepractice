package graph;

public class FloodFillSimple {

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor != color) dfs(image, sr, sc, originalColor, color);
        return image;
    }

    private static void dfs(int[][] image, int row, int col, int originalColor, int newColor) {

        if (row < 0 || row >= image.length ||
                col < 0 || col >= image[0].length ||
                image[row][col] != originalColor) {
            return;
        }

        image[row][col] = newColor;

        dfs(image, row - 1, col, originalColor, newColor);
        dfs(image, row + 1, col, originalColor, newColor);
        dfs(image, row, col - 1, originalColor, newColor);
        dfs(image, row, col + 1, originalColor, newColor);
    }

    public static void main(String[] args) {

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1;
        int sc = 1;
        int color = 2;

        int[][] result = floodFill(image, sr, sc, color);

        printMatrix(result);
    }

    private static void printMatrix(int[][] image) {
        for (int[] row : image) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}