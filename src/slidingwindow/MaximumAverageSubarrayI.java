package slidingwindow;

import java.util.Arrays;

/**
 * LeetCode 643 - Maximum Average Subarray I.
 *
 * <p>Given an integer array {@code nums} and an integer {@code k}, find the
 * contiguous subarray of length {@code k} that has the maximum average value,
 * and return that maximum average.
 *
 * <p><b>Approach 1 - Single Pass Sliding Window:</b><br>
 * Maintain a running sum; once the window reaches size {@code k}, subtract the
 * element leaving the left side on every subsequent step.
 *
 * <p><b>Approach 2 - Explicit Window Build then Slide:</b><br>
 * Build the first window sum in a dedicated loop, then slide one element at a
 * time. Intent is immediately visible; no sentinel or in-loop guards needed.
 *
 * <p>Time Complexity:  O(N) — both approaches
 * <p>Space Complexity: O(1) — both approaches
 *
 * @see <a href="https://leetcode.com/problems/maximum-average-subarray-i/">LC 643</a>
 */
public class MaximumAverageSubarrayI {

    // -------------------------------------------------------------------------
    // Approach 1: Single Pass Sliding Window
    // -------------------------------------------------------------------------

    /**
     * Finds the maximum average using a single loop.
     *
     * <p>The window expands until it reaches size {@code k}, then slides by
     * adding the incoming element and dropping the outgoing element each step.
     *
     * @param nums input array, length >= k
     * @param k    fixed window size
     * @return maximum average of any contiguous subarray of length k
     */
    private static double findMaxAverageSinglePass(int[] nums, int k) {
        double maxAvg = Double.NEGATIVE_INFINITY;
        double sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                if (i > k - 1) sum -= nums[i - k];
                maxAvg = Math.max(maxAvg, sum / k);
            }
        }
        return maxAvg;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Explicit Build then Slide
    // -------------------------------------------------------------------------

    /**
     * Finds the maximum average by first building the initial window, then sliding.
     *
     * <p>Separating the build phase from the slide phase eliminates in-loop
     * boundary guards and makes the sliding window pattern immediately readable.
     *
     * @param nums input array, length >= k
     * @param k    fixed window size
     * @return maximum average of any contiguous subarray of length k
     */
    private static double findMaxAverageExplicitSlide(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];

        double maxAvg = sum / k;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxAvg = Math.max(maxAvg, sum / k);
        }
        return maxAvg;
    }

    // -------------------------------------------------------------------------
    // Main
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        int[][] testNums = {
            {1, 12, -5, -6, 50, 3},   // Expected: 12.75000
            {5},                        // Expected: 5.00000
            {0, 4, 0, 3, 2},           // Expected: 3.50000
            {-1, -12, -5, -6, -50, 3}, // Expected: -1.00000
        };
        int[] testK = {4, 1, 2, 2};

        for (int t = 0; t < testNums.length; t++) {
            int[]  nums = testNums[t];
            int    k    = testK[t];
            double ap1  = findMaxAverageSinglePass(nums, k);
            double ap2  = findMaxAverageExplicitSlide(nums, k);
            System.out.printf("nums=%-25s k=%d | Approach1=%.5f | Approach2=%.5f%n",
                Arrays.toString(nums), k, ap1, ap2);
        }
    }
}