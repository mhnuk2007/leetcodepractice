package arrays;

/**
 * LC 42 - Trapping Rain Water
 *
 * Problem:
 *   Given n non-negative integers representing an elevation map where the
 *   width of each bar is 1, compute how much water it can trap after raining.
 *
 * Approach: Prefix / Suffix Arrays
 *   Precompute:
 *     maxLeft[i]  = max height from index 0   to i   (left wall at i)
 *     maxRight[i] = max height from index i   to n-1 (right wall at i)
 *   Then:
 *     water[i] = min(maxLeft[i], maxRight[i]) - height[i]
 *
 * Example:
 *   height   = [0, 1, 0, 2, 1, 0, 1, 3, 1, 0, 1, 2]
 *   maxLeft  = [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3]
 *   maxRight = [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2]
 *   water    = [0, 0, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0] → sum = 9
 *
 * Time  : O(n)
 * Space : O(n)
 */
public class TrappingRainWaterPrefix {

    public static void main(String[] args) {
        TrappingRainWaterPrefix trw = new TrappingRainWaterPrefix();

        // Test 1: LC example 1
        System.out.println(trw.trapPrefix(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 1, 0, 1, 2})); // Expected: 9

        // Test 2: LC example 2
        System.out.println(trw.trapPrefix(new int[]{4, 2, 0, 3, 2, 5}));                    // Expected: 9

        // Test 3: no water — strictly increasing
        System.out.println(trw.trapPrefix(new int[]{1, 2, 3, 4, 5}));                        // Expected: 0

        // Test 4: no water — strictly decreasing
        System.out.println(trw.trapPrefix(new int[]{5, 4, 3, 2, 1}));                        // Expected: 0

        // Test 5: single valley
        System.out.println(trw.trapPrefix(new int[]{3, 0, 3}));                              // Expected: 3

        // Test 6: single element — no water possible
        System.out.println(trw.trapPrefix(new int[]{5}));                                    // Expected: 0
    }

    public int trapPrefix(int[] height) {
        int n = height.length;

        int[] maxLeft  = new int[n];
        int[] maxRight = new int[n];

        // Build left max — maxLeft[i] = max(height[0..i])
        maxLeft[0] = height[0];
        for (int l = 1; l < n; l++)
            maxLeft[l] = Math.max(maxLeft[l - 1], height[l]);

        // Build right max — maxRight[i] = max(height[i..n-1])
        maxRight[n - 1] = height[n - 1];
        for (int r = n - 2; r >= 0; r--)
            maxRight[r] = Math.max(maxRight[r + 1], height[r]);

        // Accumulate water at each index
        int water = 0;
        for (int i = 0; i < n; i++)
            water += Math.min(maxLeft[i], maxRight[i]) - height[i];

        return water;
    }
}