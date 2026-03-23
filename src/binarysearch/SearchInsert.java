package binarysearch;

/**
 * LC 35 — Search Insert Position
 *
 * Problem: Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index where
 * it would be inserted in order.
 *
 * Approach: Binary search — standard left/right template.
 *   Same skeleton as LC 704 Binary Search.
 *   Only difference: return start instead of -1 when not found.
 *   When the loop ends, start points to the correct insertion position
 *   because it always moves one step past the last valid position.
 *
 * Why return start (not end, not mid):
 *   Loop exits when start > end.
 *   At that point start sits exactly where target would be inserted
 *   to keep the array sorted — it has "overshot" the last comparison
 *   by exactly one position to the right.
 *
 * Pattern   : Binary Search
 * Difficulty: Easy
 *
 * Similar Problems:
 *   LC 704  Binary Search                         (return -1 instead of start)
 *   LC 34   Find First and Last Position          (two binary searches)
 *   LC 153  Find Minimum in Rotated Sorted Array  (binary search on condition)
 *   LC 875  Koko Eating Bananas                   (binary search on answer)
 *
 * Time Complexity : O(log n) — search space halves each iteration
 * Space Complexity: O(1)
 */
public class SearchInsert {

    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end   = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2; // overflow-safe midpoint

            if      (nums[mid] == target) return mid;        // found
            else if (nums[mid] >  target) end   = mid - 1;  // search left half
            else                          start = mid + 1;  // search right half
        }

        // not found — start is the insertion position
        return start;
    }

    public static void main(String[] args) {

        // Test 1: LeetCode example — target exists
        System.out.println("=== Test 1: Target exists ===");
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5)); // 2

        // Test 2: LeetCode example — insert in middle
        System.out.println("\n=== Test 2: Insert in middle ===");
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2)); // 1

        // Test 3: LeetCode example — insert at end
        System.out.println("\n=== Test 3: Insert at end ===");
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7)); // 4

        // Test 4: Insert at beginning
        System.out.println("\n=== Test 4: Insert at beginning ===");
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0)); // 0

        // Test 5: Target is first element
        System.out.println("\n=== Test 5: Target at index 0 ===");
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 1)); // 0

        // Test 6: Target is last element
        System.out.println("\n=== Test 6: Target at last index ===");
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 6)); // 3

        // Test 7: Single element — found
        System.out.println("\n=== Test 7: Single element found ===");
        System.out.println(searchInsert(new int[]{5}, 5)); // 0

        // Test 8: Single element — insert before
        System.out.println("\n=== Test 8: Single element insert before ===");
        System.out.println(searchInsert(new int[]{5}, 3)); // 0

        // Test 9: Single element — insert after
        System.out.println("\n=== Test 9: Single element insert after ===");
        System.out.println(searchInsert(new int[]{5}, 7)); // 1

        // Test 10: Two elements — insert between
        System.out.println("\n=== Test 10: Two elements insert between ===");
        System.out.println(searchInsert(new int[]{1, 3}, 2)); // 1
    }
}