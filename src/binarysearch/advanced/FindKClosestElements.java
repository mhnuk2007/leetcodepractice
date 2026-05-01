package binarysearch;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 658 - Find K Closest Elements
 *
 * <p>Given a sorted integer array arr, two integers k and x, return the k closest
 * integers to x in the array. The result should also be sorted in ascending order.
 *
 * <p>An integer a is closer to x than b if:
 * - |a - x| < |b - x|, OR
 * - |a - x| == |b - x| and a < b (prefer smaller element on tie)
 *
 * <p>Constraints:
 * - 1 <= k <= arr.length <= 10^4
 * - arr is sorted in ascending order
 * - -10^4 <= arr[i], x <= 10^4
 *
 * <p>Time Complexity: O(log(n - k) + k) - binary search + result collection
 * Space Complexity: O(1) - excluding output list
 */
public class FindKClosestElements {

    /**
     * Binary search on the left boundary of the window.
     *
     * Key insight: the answer is always a contiguous subarray of size k.
     * So we binary search for the best starting index `left` of that window.
     *
     * At each mid, we compare the two candidates at the window edges:
     *   - arr[mid]         → left candidate
     *   - arr[mid + k]     → right candidate
     *
     * If (x - arr[mid]) > (arr[mid + k] - x), the left candidate is farther,
     * so we shift the window right: left = mid + 1.
     * Otherwise, the right candidate is farther (or tie → prefer smaller),
     * so we keep or shift left: right = mid.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare distance of left edge vs right edge of the window
            if (x - arr[mid] > arr[mid + k] - x) {
                // left edge is farther → shift window right
                left = mid + 1;
            } else {
                // right edge is farther or tie → keep window here or shift left
                right = mid;
            }
        }

        // Collect k elements starting from left
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    // ─── Test Cases ──────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        FindKClosestElements sol = new FindKClosestElements();

        // TC1: Standard case
        // Input: arr=[1,2,3,4,5], k=4, x=3  Expected: [1,2,3,4]
        System.out.println("TC1: " + sol.findClosestElements(new int[]{1,2,3,4,5}, 4, 3));

        // TC2: x is smaller than all elements
        // Input: arr=[1,2,3,4,5], k=4, x=-1  Expected: [1,2,3,4]
        System.out.println("TC2: " + sol.findClosestElements(new int[]{1,2,3,4,5}, 4, -1));

        // TC3: x is larger than all elements
        // Input: arr=[1,2,3,4,5], k=4, x=10  Expected: [2,3,4,5]
        System.out.println("TC3: " + sol.findClosestElements(new int[]{1,2,3,4,5}, 4, 10));

        // TC4: Tie-breaking — prefer smaller element
        // Input: arr=[1,2,3,4,5], k=2, x=3  Expected: [2,3] not [3,4]
        System.out.println("TC4: " + sol.findClosestElements(new int[]{1,2,3,4,5}, 2, 3));

        // TC5: x exists in array
        // Input: arr=[1,3,5,7,9], k=3, x=5  Expected: [3,5,7]
        System.out.println("TC5: " + sol.findClosestElements(new int[]{1,3,5,7,9}, 3, 5));

        // TC6: k equals array length — return entire array
        // Input: arr=[1,2,3], k=3, x=2  Expected: [1,2,3]
        System.out.println("TC6: " + sol.findClosestElements(new int[]{1,2,3}, 3, 2));

        // TC7: Single element array
        // Input: arr=[5], k=1, x=3  Expected: [5]
        System.out.println("TC7: " + sol.findClosestElements(new int[]{5}, 1, 3));
    }
}