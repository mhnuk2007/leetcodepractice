package binarysearch.advanced;

/**
 * LeetCode 74 — Search a 2D Matrix
 *
 * <p>You are given an m x n integer matrix matrix with the following two properties:
 * <ul>
 *   <li>Each row is sorted in non-decreasing order.</li>
 *   <li>The first integer of each row is greater than the last integer of the previous row.</li>
 * </ul>
 * Given an integer target, return true if target is in matrix or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * <p><b>Approach — Virtual 1D Binary Search:</b><br>
 * Since the matrix has row-to-row sorted continuity, we can treat the entire
 * m x n matrix as a single sorted 1D array of size m * n.
 * We can then map any 1D index `mid` back to its 2D coordinates:
 * <ul>
 *   <li>`row = mid / n`</li>
 *   <li>`col = mid % n`</li>
 * </ul>
 * This allows us to run standard binary search directly on the matrix.
 *
 * <p>Time Complexity  — O(log(m * n)): where m is the number of rows and n is the number of columns.
 * <p>Space Complexity — O(1): auxiliary space.
 *
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix/">LC 74</a>
 */
public class SearchIn2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = m * n - 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test 1: Standard case - Target exists
        int[][] matrix1 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target1 = 3;
        System.out.println("Test 1: " + searchMatrix(matrix1, target1)); // Expected: true

        // Test 2: Standard case - Target does not exist
        int target2 = 13;
        System.out.println("Test 2: " + searchMatrix(matrix1, target2)); // Expected: false

        // Test 3: Single element matrix - Target exists
        int[][] matrix3 = {{1}};
        int target3 = 1;
        System.out.println("Test 3: " + searchMatrix(matrix3, target3)); // Expected: true

        // Test 4: Empty matrix
        int[][] matrix4 = {};
        int target4 = 5;
        System.out.println("Test 4: " + searchMatrix(matrix4, target4)); // Expected: false
    }
}

