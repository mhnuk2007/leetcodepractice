package binarysearch;

/**
 * LeetCode 81 — Search in Rotated Sorted Array II
 *
 * Problem: Same as LC 33 but the array may contain duplicates.
 * Return true if target exists, false otherwise.
 *
 * Key difference from LC 33:
 *   When nums[left] == nums[mid], we cannot determine which half is sorted.
 *   Example: [3, 1, 3, 3, 3] — left=3, mid=3, both halves could be sorted.
 *   Solution: skip the ambiguity by doing left++ (shrink from left by 1).
 *
 * This is the ONLY change from LC 33 — one extra else-if branch.
 *
 * Worst case: [3,3,3,3,1,3] searching for 1
 *   Every iteration hits the ambiguous case → left++ each time → O(n)
 * Average case: O(log n) — duplicates are rare in practice
 *
 * Time Complexity : O(log n) average, O(n) worst case (all duplicates)
 * Space Complexity: O(1)
 */
public class SearchRotatedSortedArrayII {

    public static boolean search(int[] nums, int target) {
        // ── Pre-processing ─────────────────────────────────────────────────
        int left  = 0;
        int right = nums.length - 1;

        // ── Execution ──────────────────────────────────────────────────────
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return true;  // found

            // ── The one addition vs LC 33 ──────────────────────────────────
            // nums[left] == nums[mid] → ambiguous — cannot tell which half is sorted
            // Example: [3,3,3,1,3] → left=3, mid=3 — could be either side
            // Fix: shrink left by 1 to skip the duplicate and retry
            if (nums[left] == nums[mid]) {
                left++;                            // skip duplicate — O(n) worst case
                continue;
            }

            // ── Identical to LC 33 from here ──────────────────────────────
            if (nums[left] < nums[mid]) {
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
        return false; // not found
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard — with duplicates, target exists
        System.out.println("=== Test 1: Target exists with duplicates ===");
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0)); // true
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 2)); // true

        // Test 2: LeetCode standard — target not found
        System.out.println("\n=== Test 2: Target not found ===");
        System.out.println(search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3)); // false

        // Test 3: All duplicates — worst case O(n)
        System.out.println("\n=== Test 3: All duplicates ===");
        System.out.println(search(new int[]{3, 3, 3, 3, 3}, 3)); // true
        System.out.println(search(new int[]{3, 3, 3, 3, 3}, 1)); // false

        // Test 4: Ambiguous case — duplicate at boundary hides pivot
        System.out.println("\n=== Test 4: Ambiguous boundary ===");
        System.out.println(search(new int[]{3, 1, 3, 3, 3}, 1)); // true
        System.out.println(search(new int[]{3, 3, 3, 1, 3}, 1)); // true

        // Test 5: No rotation — plain sorted with duplicates
        System.out.println("\n=== Test 5: No rotation ===");
        System.out.println(search(new int[]{1, 1, 2, 3, 4, 4, 5}, 4)); // true
        System.out.println(search(new int[]{1, 1, 2, 3, 4, 4, 5}, 6)); // false

        // Test 6: Single element — found
        System.out.println("\n=== Test 6: Single element found ===");
        System.out.println(search(new int[]{1}, 1)); // true

        // Test 7: Single element — not found
        System.out.println("\n=== Test 7: Single element not found ===");
        System.out.println(search(new int[]{1}, 2)); // false

        // Test 8: Two elements
        System.out.println("\n=== Test 8: Two elements ===");
        System.out.println(search(new int[]{3, 1}, 1)); // true
        System.out.println(search(new int[]{1, 1}, 1)); // true
        System.out.println(search(new int[]{1, 3}, 2)); // false

        // Test 9: Duplicates at both ends — classic ambiguous case
        System.out.println("\n=== Test 9: Duplicates at both ends ===");
        System.out.println(search(new int[]{1, 3, 1, 1, 1}, 3)); // true
        System.out.println(search(new int[]{1, 3, 1, 1, 1}, 2)); // false
    }
}