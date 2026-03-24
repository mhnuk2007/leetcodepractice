package binarysearch;

/**
 * LeetCode 704 — Binary Search
 * <p>
 * Problem: Given a sorted array of distinct integers and a target,
 * return the index of target. Return -1 if not found.
 * <p>
 * Approach: Divide search space in half each iteration.
 * If arr[mid] == target → found, return mid.
 * If target < arr[mid]  → target in left half  → right = mid - 1
 * If target > arr[mid]  → target in right half → left  = mid + 1
 * <p>
 * Why mid = left + (right-left)/2 and NOT (left+right)/2:
 * (left + right) can overflow Integer.MAX_VALUE when both are large.
 * left + (right-left)/2 is mathematically identical but overflow-safe.
 * <p>
 * Time Complexity : O(log n) — search space halves each iteration
 * Space Complexity: O(1)
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // overflow-safe midpoint

            if (arr[mid] == target) return mid;       // found
            else if (arr[mid] > target) right = mid - 1; // search left half
            else left = mid + 1; // search right half
        }
        return -1; // not found
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, 5, 7, 9, 10, 12};

        // Test 1: Target not in array
        System.out.println("=== Test 1: Target not present ===");
        System.out.println(binarySearch(nums, 6));  // -1

        // Test 2: Target in middle area
        System.out.println("\n=== Test 2: Target in middle ===");
        System.out.println(binarySearch(nums, 5));  // 2

        // Test 3: Target is last element
        System.out.println("\n=== Test 3: Target at end ===");
        System.out.println(binarySearch(nums, 12)); // 6

        // Test 4: Target is first element
        System.out.println("\n=== Test 4: Target at start ===");
        System.out.println(binarySearch(nums, 2));  // 0

        // Test 5: Single element — found
        System.out.println("\n=== Test 5: Single element found ===");
        System.out.println(binarySearch(new int[]{5}, 5)); // 0

        // Test 6: Single element — not found
        System.out.println("\n=== Test 6: Single element not found ===");
        System.out.println(binarySearch(new int[]{5}, 3)); // -1

        // Test 7: Two elements
        System.out.println("\n=== Test 7: Two elements ===");
        System.out.println(binarySearch(new int[]{1, 3}, 3)); // 1
        System.out.println(binarySearch(new int[]{1, 3}, 1)); // 0
        System.out.println(binarySearch(new int[]{1, 3}, 2)); // -1
    }
}