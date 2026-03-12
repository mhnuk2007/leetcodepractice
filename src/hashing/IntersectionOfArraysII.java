package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 350 - Intersection of Two Arrays II
 *
 * Problem:
 * Given two integer arrays nums1 and nums2, return their intersection.
 * Each element in the result must appear as many times as it appears
 * in both arrays. Result can be in any order.
 *
 * Example 1:
 *   Input : nums1 = [1,2,2,1], nums2 = [2,2]
 *   Output: [2,2]
 *
 * Example 2:
 *   Input : nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *   Output: [4,9]  (each appears once in nums1)
 *
 * Difference from LC 349:
 *   LC 349 returns unique elements only.
 *   LC 350 returns elements with correct multiplicity (count matters).
 *
 * Approach 1 - int[1000] frequency array: O(n + m) time, O(1) space
 *   Count frequency of each number in nums1 using fixed array.
 *   Scan nums2 — if freq[num] > 0, include in result and decrement.
 *   Works when values are bounded (0..999 per constraints).
 *   Fix: toArray() cannot produce int[] from List<Integer> — manual copy needed.
 *
 * Approach 2 - Sort + Two Pointers: O(n log n + m log m) time, O(1) space
 *   Sort both arrays. Walk two pointers:
 *   - Equal → add to result, advance both.
 *   - nums1[i] < nums2[j] → advance i.
 *   - nums1[i] > nums2[j] → advance j.
 *   Best when arrays are already sorted or follow-up asks for sorted output.
 *
 * Follow-up considerations:
 *   - If nums1 is much smaller → use HashMap on nums1, scan nums2.
 *   - If arrays are sorted → use two pointers directly, no extra space.
 *   - If nums2 doesn't fit in memory → stream nums2, use HashMap on nums1.
 *
 * Time Complexity : O(n + m) — Approach 1
 * Space Complexity: O(1)     — fixed array size independent of input
 */
public class IntersectionOfArraysII {

    public static void main(String[] args) {
        // Test 1: duplicates in result
        System.out.println(Arrays.toString(
                intersectFreqArray(new int[]{1, 2, 2, 1}, new int[]{2, 2})));     // [2,2]

        // Test 2: partial overlap
        System.out.println(Arrays.toString(
                intersectFreqArray(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}))); // [9,4]

        // Test 3: no intersection
        System.out.println(Arrays.toString(
                intersectFreqArray(new int[]{1, 2, 3}, new int[]{4, 5, 6})));     // []

        // Test 4: one array is subset of other
        System.out.println(Arrays.toString(
                intersectFreqArray(new int[]{1, 2, 3}, new int[]{1, 2})));       // [1,2]

        // Test 5: all same elements
        System.out.println(Arrays.toString(
                intersectFreqArray(new int[]{2, 2, 2}, new int[]{2, 2})));       // [2,2]

        // Test 6: single element match
        System.out.println(Arrays.toString(
                intersectFreqArray(new int[]{1}, new int[]{1})));             // [1]
    }

    // Approach 1 — int[1000] frequency array ← optimal for bounded values
    // O(n + m) time | O(1) space
    // Fix from original: List<Integer> cannot be converted to int[] via toArray()
    // Must manually copy — Java arrays don't support auto-unboxing of Integer[]→int[]
    public static int[] intersectFreqArray(int[] nums1, int[] nums2) {
        int[] freq = new int[1001]; // constraint: 0 <= nums[i] <= 1000

        // count frequency of each number in nums1
        for (int num : nums1) freq[num]++;

        List<Integer> result = new ArrayList<>();

        // scan nums2 — include if freq remaining > 0, then decrement
        for (int num : nums2) {
            if (freq[num] > 0) {
                result.add(num);
                freq[num]--; // decrement so duplicates are counted correctly
            }
        }

        // manual int[] conversion — toArray(new Integer[0]) gives Integer[], not int[]
        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) arr[i] = result.get(i);
        return arr;
    }

    // Approach 2 — Sort + Two Pointers
    // O(n log n + m log m) time | O(1) space
    // Best when: arrays are already sorted, or follow-up requires sorted output
    public static int[] intersectTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]); // match — include in result
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;                  // nums1 behind — advance it
            } else {
                j++;                  // nums2 behind — advance it
            }
        }

        int[] arr = new int[result.size()];
        for (int k = 0; k < result.size(); k++) arr[k] = result.get(k);
        return arr;
    }
}