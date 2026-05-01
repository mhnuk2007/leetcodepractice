package binarysearch;

import java.util.Arrays;

/**
 * LeetCode: 34 — Find First and Last Position of Element in Sorted Array
 *
 * Problem: Given a sorted array, find the starting and ending position
 * of a target value. Return [-1, -1] if not found.
 *
 * Approach 1 — Two separate methods (findFirst + findLast):
 *   Explicit and readable — each method has a single clear purpose.
 *
 * Approach 2 — Single method with isFirst flag (findIndex):
 *   DRY — same binary search logic, direction controlled by boolean.
 *   isFirst=true  → go left when found  (right = mid - 1)
 *   isFirst=false → go right when found (left  = mid + 1)
 *   Preferred — avoids duplicating the binary search skeleton.
 *
 * Time Complexity : O(log n) — two binary searches
 * Space Complexity: O(1)
 */
public class FirstAndLastPosition {

    // ─── Approach 1: Two separate methods ────────────────────────────────────
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = findFirst(nums, target);
        if (first == -1) return new int[]{-1, -1};
        return new int[]{first, findLast(nums, target)};
    }

    public static int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1, firstIdx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) { firstIdx = mid; right = mid - 1; }
            else if (nums[mid] > target)  right = mid - 1;
            else                          left  = mid + 1;
        }
        return firstIdx;
    }

    public static int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1, lastIdx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) { lastIdx = mid; left = mid + 1; }
            else if (nums[mid] > target)  right = mid - 1;
            else                          left  = mid + 1;
        }
        return lastIdx;
    }

    // ─── Approach 2: Single method with isFirst flag — preferred ─────────────
    public static int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = findIndex(nums, target, true);
        if (first == -1) return new int[]{-1, -1};
        return new int[]{first, findIndex(nums, target, false)};
    }

    public static int findIndex(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, idx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                idx = mid;
                if (isFirst) right = mid - 1; // go left  — find earlier occurrence
                else         left  = mid + 1; // go right — find later  occurrence
            } else if (nums[mid] > target) right = mid - 1;
            else                           left  = mid + 1;
        }
        return idx;
    }

    public static void main(String[] args) {

        // Test 1: Multiple occurrences
        System.out.println("=== Test 1: Multiple occurrences ===");
        System.out.println(Arrays.toString(searchRange( new int[]{5,7,7,8,8,10}, 8))); // [3,4]
        System.out.println(Arrays.toString(searchRange2(new int[]{5,7,7,8,8,10}, 8))); // [3,4]

        // Test 2: Target not found
        System.out.println("\n=== Test 2: Target not found ===");
        System.out.println(Arrays.toString(searchRange( new int[]{5,7,7,8,8,10}, 6))); // [-1,-1]
        System.out.println(Arrays.toString(searchRange2(new int[]{5,7,7,8,8,10}, 6))); // [-1,-1]

        // Test 3: Single occurrence
        System.out.println("\n=== Test 3: Single occurrence ===");
        System.out.println(Arrays.toString(searchRange( new int[]{5,7,7,8,8,10}, 10))); // [5,5]
        System.out.println(Arrays.toString(searchRange2(new int[]{5,7,7,8,8,10}, 10))); // [5,5]

        // Test 4: All same
        System.out.println("\n=== Test 4: All same ===");
        System.out.println(Arrays.toString(searchRange( new int[]{8,8,8,8,8}, 8))); // [0,4]
        System.out.println(Arrays.toString(searchRange2(new int[]{8,8,8,8,8}, 8))); // [0,4]

        // Test 5: Single element found
        System.out.println("\n=== Test 5: Single element found ===");
        System.out.println(Arrays.toString(searchRange( new int[]{8}, 8))); // [0,0]
        System.out.println(Arrays.toString(searchRange2(new int[]{8}, 8))); // [0,0]

        // Test 6: Single element not found
        System.out.println("\n=== Test 6: Single element not found ===");
        System.out.println(Arrays.toString(searchRange( new int[]{8}, 5))); // [-1,-1]
        System.out.println(Arrays.toString(searchRange2(new int[]{8}, 5))); // [-1,-1]

        // Test 7: Empty array
        System.out.println("\n=== Test 7: Empty array ===");
        System.out.println(Arrays.toString(searchRange( new int[]{}, 5))); // [-1,-1]
        System.out.println(Arrays.toString(searchRange2(new int[]{}, 5))); // [-1,-1]

        // Test 8: Target at boundaries
        System.out.println("\n=== Test 8: Target at boundaries ===");
        System.out.println(Arrays.toString(searchRange( new int[]{1,1,2,3,4,5,5}, 1))); // [0,1]
        System.out.println(Arrays.toString(searchRange2(new int[]{1,1,2,3,4,5,5}, 5))); // [5,6]
    }
}