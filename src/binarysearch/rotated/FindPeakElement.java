package binarysearch;

/**
 * LeetCode 162 - Find Peak Element
 *
 * <p>A peak element is an element strictly greater than its neighbors.
 * Given a 0-indexed integer array nums, find a peak element and return its index.
 * You may assume nums[-1] = nums[n] = -∞ (virtual sentinels at boundaries).
 * If the array contains multiple peaks, return the index of any peak.
 *
 * <p>Constraints:
 * - 1 <= nums.length <= 1000
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - nums[i] != nums[i + 1] for all valid i
 *
 * <p>Time Complexity: O(log n) - binary search halves the search space each iteration
 * Space Complexity: O(1) - constant extra space
 */
public class FindPeakElement {

    /**
     * Binary search approach.
     *
     * Key insight: if nums[mid] < nums[mid + 1], the right half is "ascending"
     * and must contain a peak. Otherwise, the left half (including mid) contains one.
     *
     * Why this works:
     * - If nums[mid] < nums[mid+1], then nums[mid+1] is either a peak itself
     *   OR there's a bigger element to its right — either way a peak exists in [mid+1, hi].
     * - If nums[mid] > nums[mid+1], by the same logic a peak exists in [lo, mid].
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // Descending slope → peak is at mid or to the left
                right = mid;
            } else {
                // Ascending slope → peak is to the right
                left = mid + 1;
            }
        }

        // lo == right, converged to a peak
        return left;
    }

    // ─── Test Cases ──────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        FindPeakElement sol = new FindPeakElement();

        // TC1: Standard — peak in the middle
        // Input: [1, 2, 3, 1]  Expected: 2
        int r1 = sol.findPeakElement(new int[]{1, 2, 3, 1});
        System.out.println("TC1: " + r1 + (r1 == 2 ? " ✓" : " ✗"));

        // TC2: Multiple peaks — any valid index accepted
        // Input: [1, 2, 1, 3, 5, 6, 4]  Expected: 1 or 5
        int r2 = sol.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4});
        boolean ok2 = r2 == 1 || r2 == 5;
        System.out.println("TC2: " + r2 + (ok2 ? " ✓" : " ✗"));

        // TC3: Single element — it is trivially a peak
        // Input: [1]  Expected: 0
        int r3 = sol.findPeakElement(new int[]{1});
        System.out.println("TC3: " + r3 + (r3 == 0 ? " ✓" : " ✗"));

        // TC4: Two elements — larger one is the peak
        // Input: [2, 1]  Expected: 0
        int r4 = sol.findPeakElement(new int[]{2, 1});
        System.out.println("TC4: " + r4 + (r4 == 0 ? " ✓" : " ✗"));

        // TC5: Strictly ascending — last element is the peak
        // Input: [1, 2, 3, 4, 5]  Expected: 4
        int r5 = sol.findPeakElement(new int[]{1, 2, 3, 4, 5});
        System.out.println("TC5: " + r5 + (r5 == 4 ? " ✓" : " ✗"));

        // TC6: Strictly descending — first element is the peak
        // Input: [5, 4, 3, 2, 1]  Expected: 0
        int r6 = sol.findPeakElement(new int[]{5, 4, 3, 2, 1});
        System.out.println("TC6: " + r6 + (r6 == 0 ? " ✓" : " ✗"));

        // TC7: Peak at boundary (last index)
        // Input: [1, 3, 2, 4]  Expected: 1 or 3
        int r7 = sol.findPeakElement(new int[]{1, 3, 2, 4});
        boolean ok7 = r7 == 1 || r7 == 3;
        System.out.println("TC7: " + r7 + (ok7 ? " ✓" : " ✗"));
    }
}