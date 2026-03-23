package binarysearch;

import java.util.Arrays;

/**
 * LeeCode 34 — Find First and Last Position of Element in Sorted Array
 *
 * Problem: Given a sorted array, find the starting and ending position
 * of a target value. Return [-1, -1] if not found.
 *
 * Approach: Two separate binary searches — Template 1 with a twist.
 *   findFirst: when target found, record index and go LEFT (right = mid-1)
 *              to find an earlier occurrence.
 *   findLast:  when target found, record index and go RIGHT (left = mid+1)
 *              to find a later occurrence.
 *   Both searches use an explicit result variable (-1 as default).
 *   This avoids Template 3's two-element post-processing complexity.
 *
 * Time Complexity : O(log n) — two binary searches
 * Space Complexity: O(1)
 */
public class FirstAndLastPosition {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = findFirst(nums, target);
        if (first == -1) return new int[]{-1, -1};   // call findFirst once only
        return new int[]{first, findLast(nums, target)};
    }

    // ─── Find first occurrence ────────────────────────────────────────────────
    // When target found: record index, keep searching LEFT for earlier occurrence
    public static int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int firstIdx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                firstIdx = mid;     // record — but keep searching left
                right = mid - 1;   // go left — earlier occurrence may exist
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return firstIdx;
    }

    // ─── Find last occurrence ─────────────────────────────────────────────────
    // When target found: record index, keep searching RIGHT for later occurrence
    public static int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int lastIdx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                lastIdx = mid;    // record — but keep searching right
                left = mid + 1;  // go right — later occurrence may exist
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return lastIdx;
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard — target appears multiple times
        System.out.println("=== Test 1: Multiple occurrences ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8))); // [3, 4]

        // Test 2: Target not in array
        System.out.println("\n=== Test 2: Target not found ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6))); // [-1, -1]

        // Test 3: Target appears once
        System.out.println("\n=== Test 3: Single occurrence ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{5, 7, 7, 8, 8, 10}, 10))); // [5, 5]

        // Test 4: All elements are the target
        System.out.println("\n=== Test 4: All same ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{8, 8, 8, 8, 8}, 8))); // [0, 4]

        // Test 5: Single element — found
        System.out.println("\n=== Test 5: Single element found ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{8}, 8))); // [0, 0]

        // Test 6: Single element — not found
        System.out.println("\n=== Test 6: Single element not found ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{8}, 5))); // [-1, -1]

        // Test 7: Empty array
        System.out.println("\n=== Test 7: Empty array ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{}, 5))); // [-1, -1]

        // Test 8: Target at boundaries
        System.out.println("\n=== Test 8: Target at start and end ===");
        System.out.println(Arrays.toString(
                searchRange(new int[]{1, 1, 2, 3, 4, 5, 5}, 1))); // [0, 1]
        System.out.println(Arrays.toString(
                searchRange(new int[]{1, 1, 2, 3, 4, 5, 5}, 5))); // [5, 6]
    }
}