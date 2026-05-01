package binarysearch;

/**
 * LC 153 — Find Minimum in Rotated Sorted Array
 *
 * Problem: Given a sorted array rotated at an unknown pivot,
 * find the minimum element. All values are distinct.
 *
 * Key insight: compare nums[mid] to nums[right] — NOT nums[left].
 *   nums[mid] < nums[right] → right half is sorted → min is in left half
 *                             (or IS mid) → right = mid
 *   nums[mid] > nums[right] → left half is sorted → min is in right half
 *                             (past mid) → left = mid + 1
 *
 * Why compare to right and not left:
 *   nums[left] is ambiguous — in [1,2,3,4,5] (no rotation) nums[left]
 *   is the minimum AND nums[left] <= nums[mid], which would incorrectly
 *   suggest min is in the right half. nums[right] is a stable anchor —
 *   the minimum is always to the left of (or equal to) the right pointer.
 *
 * Template: Template 2 — left < right, exits with 1 element.
 *   right = mid     (NOT mid-1 — mid could be the minimum)
 *   left  = mid + 1 (mid is confirmed too large, skip it)
 *
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 *
 * Related:
 *   LC 154 Find Minimum II — same approach with duplicates (right-- when ambiguous)
 *   LC 33  Search Rotated  — identify sorted half then check target range
 */
public class FindMinRotatedArray {

    public static int findMin(int[] nums) {
        // ── Pre-processing ──────────────────────────────────────────────────
        int left  = 0;
        int right = nums.length - 1;

        // ── Execution ───────────────────────────────────────────────────────
        while (left < right) {            // Template 2 — exits with 1 element
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right])
                right = mid;             // mid could be min — keep it
            else
                left = mid + 1;          // mid > right — min is past mid
        }

        // ── Post-processing ─────────────────────────────────────────────────
        return nums[left];               // 1 element remains — this is the min
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard — rotated
        System.out.println("=== Test 1: Standard rotated ===");
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2})); // 1

        // Test 2: LeetCode standard — longer rotation
        System.out.println("\n=== Test 2: Longer rotation ===");
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // 0

        // Test 3: Not rotated — min is first element
        System.out.println("\n=== Test 3: Not rotated ===");
        System.out.println(findMin(new int[]{1, 2, 3, 4, 5})); // 1

        // Test 4: Rotated by one — min is last element
        System.out.println("\n=== Test 4: Rotated by one ===");
        System.out.println(findMin(new int[]{2, 3, 4, 5, 1})); // 1

        // Test 5: Single element
        System.out.println("\n=== Test 5: Single element ===");
        System.out.println(findMin(new int[]{5})); // 5

        // Test 6: Two elements — rotated
        System.out.println("\n=== Test 6: Two elements rotated ===");
        System.out.println(findMin(new int[]{3, 1})); // 1

        // Test 7: Two elements — not rotated
        System.out.println("\n=== Test 7: Two elements not rotated ===");
        System.out.println(findMin(new int[]{1, 3})); // 1

        // Test 8: Min is at the middle
        System.out.println("\n=== Test 8: Min in middle ===");
        System.out.println(findMin(new int[]{5, 6, 7, 1, 2, 3, 4})); // 1

        // Test 9: Large array — min at various positions
        System.out.println("\n=== Test 9: Large rotation ===");
        System.out.println(findMin(new int[]{6,7,8,9,10,1,2,3,4,5})); // 1
    }
}