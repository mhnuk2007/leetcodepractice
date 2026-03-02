package dailychallenges;

public class MinimumSwaps {

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeros = new int[n];

        // Step 1: Count trailing zeros
        for (int i = 0; i < n; i++) {
            int count = 0;
            int j = n - 1;

            while (j >= 0 && grid[i][j] == 0) {
                count++;
                j--;
            }

            trailingZeros[i] = count;
        }

        int swaps = 0;

        // Step 2: Fix each row
        for (int i = 0; i < n; i++) {

            int requiredZeros = n - i - 1;
            int j = i;

            // Find row that satisfies requirement
            while (j < n && trailingZeros[j] < requiredZeros) {
                j++;
            }

            // If no such row
            if (j == n) {
                return -1;
            }

            // Bring row up using adjacent swaps
            while (j > i) {
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;

                swaps++;
                j--;
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        MinimumSwaps obj = new MinimumSwaps();
        int[][] arr = {
                {0, 0, 1},
                {1, 1, 0},
                {1, 0, 0}
        };

        int result = obj.minSwaps(arr);
        System.out.println(result);
    }
}