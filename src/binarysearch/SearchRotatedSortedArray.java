package binarysearch;

/**
 * LeetCode 33 — Search in Rotated Sorted Array
 *
 * Problem: Given a rotated sorted array of distinct integers and a target,
 * return the index of target, or -1 if not found.
 *
 * A rotated sorted array looks like: [4, 5, 6, 7, 0, 1, 2]
 * It was originally [0,1,2,4,5,6,7] rotated at pivot index 4.
 *
 * Key insight: even after rotation, ONE half is always sorted.
 *   If nums[left] <= nums[mid] → left half is sorted
 *   else                       → right half is sorted
 *
 * Once we know which half is sorted, we check if target lies within it.
 * If yes → search that half. If no → search the other half.
 *
 * Approach: Template 1 binary search with sorted-half identification.
 *
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */
public class SearchRotatedSortedArray {

    public static int search(int[] nums, int target) {
        // ── Pre-processing ─────────────────────────────────────────────────
        int left  = 0;
        int right = nums.length - 1;

        // ── Execution ──────────────────────────────────────────────────────
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid; // found

            // determine which half is sorted
            if (nums[left] <= nums[mid]) {
                // left half [left..mid] is sorted
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1; // target in sorted left half
                else
                    left = mid + 1;  // target in right half
            } else {
                // right half [mid..right] is sorted
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;  // target in sorted right half
                else
                    right = mid - 1; // target in left half
            }
        }

        // ── Post-processing ────────────────────────────────────────────────
        return -1; // not found
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard — target exists
        System.out.println("=== Test 1: Target exists ===");
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4)); // 0

        // Test 2: LeetCode standard — target not found
        System.out.println("\n=== Test 2: Target not found ===");
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1

        // Test 3: Single element — found
        System.out.println("\n=== Test 3: Single element found ===");
        System.out.println(search(new int[]{1}, 1)); // 0

        // Test 4: Single element — not found
        System.out.println("\n=== Test 4: Single element not found ===");
        System.out.println(search(new int[]{1}, 2)); // -1

        // Test 5: Not rotated — acts as plain binary search
        System.out.println("\n=== Test 5: Not rotated ===");
        System.out.println(search(new int[]{1, 2, 3, 4, 5}, 3)); // 2
        System.out.println(search(new int[]{1, 2, 3, 4, 5}, 6)); // -1

        // Test 6: Target at boundaries
        System.out.println("\n=== Test 6: Target at boundaries ===");
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2)); // 6
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7)); // 3

        // Test 7: Two elements — rotated
        System.out.println("\n=== Test 7: Two elements rotated ===");
        System.out.println(search(new int[]{3, 1}, 1)); // 1
        System.out.println(search(new int[]{3, 1}, 3)); // 0

        // Test 8: Rotated at first position — fully sorted
        System.out.println("\n=== Test 8: Pivot at index 0 ===");
        System.out.println(search(new int[]{1, 2, 3, 4, 5, 6}, 5)); // 4

        // Test 9: Target is the pivot element
        System.out.println("\n=== Test 9: Target is pivot ===");
        System.out.println(search(new int[]{6, 7, 1, 2, 3, 4, 5}, 1)); // 2
    }
}