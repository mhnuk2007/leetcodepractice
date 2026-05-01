package arrays;

/**
 * Leetcode 42 - Trapping Rain Water
 * <p>
 * Problem:
 * Given n non-negative integers representing an elevation map where the
 * width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * Approach: Brute Force
 * For each index i, scan left for maxLeft and scan right for maxRight.
 * water[i] = min(maxLeft, maxRight) - height[i]
 * Skip first and last bars — they can never hold water.
 * <p>
 * Example:
 * height   = [0, 1, 0, 2, 1, 0, 1, 3, 1, 0, 1, 2]
 * water    = [0, 0, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0] → sum = 6
 * <p>
 * Time  : O(n²)  — nested scan per index
 * Space : O(1)
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater trw = new TrappingRainWater();

        // Test 1: example from problem
        System.out.println(trw.trapBrute(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 1, 0, 1, 2})); // Expected: 6

        // Test 2: second example
        System.out.println(trw.trapBrute(new int[]{4, 2, 0, 3, 2, 5}));                    // Expected: 9

        // Test 3: no water — strictly increasing
        System.out.println(trw.trapBrute(new int[]{1, 2, 3, 4, 5}));                        // Expected: 0

        // Test 4: no water — strictly decreasing
        System.out.println(trw.trapBrute(new int[]{5, 4, 3, 2, 1}));                        // Expected: 0

        // Test 5: single valley
        System.out.println(trw.trapBrute(new int[]{3, 0, 3}));                              // Expected: 3

        // Test 6: single element — no water possible
        System.out.println(trw.trapBrute(new int[]{5}));                                    // Expected: 0
    }

    public int trapBrute(int[] height) {
        int n = height.length;
        int water = 0;
        // 0, 1, 0, 2, 1, 0, 1, 3, 1, 0, 1, 2
        for (int i = 1; i < n - 1; i++) {
            int leftMax = 0, rightMax = 0;
            for (int l = 0; l <= i; l++) {
                leftMax = Math.max(leftMax, height[l]);
            }
            for (int r = i; r <= n - 1; r++) {
                rightMax = Math.max(rightMax, height[r]);
            }
            water += Math.min(leftMax, rightMax) - height[i];
        }


        return water;
    }
}