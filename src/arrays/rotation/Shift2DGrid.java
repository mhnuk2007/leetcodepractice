package arrays.rotation;

import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid {
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int total = rows * cols;

        k %= total;

        int[][] shiftedGrid = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int idx = row * cols + col;
                int newIdx = (idx + k) % total;
                int newRow = newIdx / cols;
                int newCol = newIdx % cols;
                shiftedGrid[newRow][newCol] = grid[row][col];
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : shiftedGrid) {
            List<Integer> list = new ArrayList<>();
            for (int val : row) list.add(val);
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        runExample(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                },
                1,
                List.of(
                        List.of(9, 1, 2),
                        List.of(3, 4, 5),
                        List.of(6, 7, 8)
                )
        );

        runExample(
                new int[][]{
                        {3, 8, 1, 9},
                        {19, 7, 2, 5},
                        {4, 6, 11, 10},
                        {12, 0, 21, 13}
                },
                4,
                List.of(
                        List.of(12, 0, 21, 13),
                        List.of(3, 8, 1, 9),
                        List.of(19, 7, 2, 5),
                        List.of(4, 6, 11, 10)
                )
        );

        runExample(
                new int[][]{
                        {1},
                        {2},
                        {3},
                        {4},
                        {7},
                        {6},
                        {5}
                },
                23,
                List.of(
                        List.of(6),
                        List.of(5),
                        List.of(1),
                        List.of(2),
                        List.of(3),
                        List.of(4),
                        List.of(7)
                )
        );
    }

    private static void runExample(int[][] grid, int k, List<List<Integer>> expected) {
        List<List<Integer>> actual = shiftGrid(grid, k);
        String status = actual.equals(expected) ? "PASS" : "FAIL";

        System.out.printf(
                "[%s] k=%d%nInput:%n%sExpected: %s%nActual:   %s%n%n",
                status,
                k,
                java.util.Arrays.deepToString(grid),
                expected,
                actual
        );
    }
}
