package arrays;

import java.util.Arrays;

/**
 * LeetCode 561 - Array Partition
 * <p>
 * Problem:
 * Given an integer array nums of 2n integers, group these integers into n
 * pairs such that the sum of min(a, b) of each pair is maximized.
 * Return the maximized sum.
 * <p>
 * Key insight:
 * After sorting, the optimal strategy is always to pair adjacent elements.
 * Pairing nums[0] with nums[1], nums[2] with nums[3], etc. ensures we
 * lose the smallest possible value in each pair.
 * The answer is sum of elements at even indices after sorting.
 * <p>
 * Approach 1 — Sort + Pick Even Indices:
 * Sort the array. Sum every element at index 0, 2, 4...
 * Time: O(n log n)  Space: O(1)
 * <p>
 * Approach 2 — Counting Sort (optimized for bounded input):
 * Use a frequency array since values are in [-10000, 10000].
 * Simulate the sorted order by iterating through the count array.
 * Track position parity (even/odd) to know when to add to sum.
 * Drain each bucket fully with an inner while loop before moving on.
 * Time: O(n + k)  Space: O(k)  where k = 20001
 * <p>
 * Example:
 * nums = [1, 4, 3, 2]
 * sorted → [1, 2, 3, 4]
 * pairs  → (1,2), (3,4)
 * sum    → min(1,2) + min(3,4) = 1 + 3 = 4
 * <p>
 * Time  : O(n log n) Approach 1 | O(n + k) Approach 2
 * Space : O(1)       Approach 1 | O(k)     Approach 2
 */
public class ArrayPartition {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println(arrayPairSum(new int[]{1, 4, 3, 2}));         // Expected: 4
        System.out.println(arrayPairSumV2(new int[]{1, 4, 3, 2}));       // Expected: 4

        // Test 2: all same values
        System.out.println(arrayPairSum(new int[]{1, 1, 1, 1}));         // Expected: 2
        System.out.println(arrayPairSumV2(new int[]{1, 1, 1, 1}));       // Expected: 2

        // Test 3: negative numbers
        System.out.println(arrayPairSum(new int[]{6, 2, 6, 5, 1, 2}));   // Expected: 9
        System.out.println(arrayPairSumV2(new int[]{6, 2, 6, 5, 1, 2})); // Expected: 9

        // Test 4: single pair
        System.out.println(arrayPairSum(new int[]{-1, -2}));             // Expected: -2
        System.out.println(arrayPairSumV2(new int[]{-1, -2}));           // Expected: -2

        // Test 5: boundary values
        System.out.println(arrayPairSum(new int[]{-10000, 10000}));      // Expected: -10000
        System.out.println(arrayPairSumV2(new int[]{-10000, 10000}));    // Expected: -10000
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Approach 1 — Sort + Pick Even Indices
    // Time: O(n log n)  Space: O(1)
    // ─────────────────────────────────────────────────────────────────────────
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length; i += 2)
            maxSum += nums[i];
        return maxSum;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Approach 2 — Counting Sort
    // Values in [-10000, 10000] → offset by k=10000 → indices [0, 20000].
    // Iterate through count array in order (simulates sorted traversal).
    // Track parity: even position → add to sum; odd position → skip (it's the
    // larger of the pair). Use inner while to fully drain each bucket.
    // Time: O(n + k)  Space: O(k)
    // ─────────────────────────────────────────────────────────────────────────
    public static int arrayPairSumV2(int[] nums) {
        final int K = 10000;
        int[] counts = new int[2 * K + 1];

        for (int num : nums) {
            counts[num + K]++;
        }

        int sum = 0;
        boolean even = true;

        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                if (even) sum += i - K;
                even = !even;
                counts[i]--;
            }
        }
        return sum;
    }
}