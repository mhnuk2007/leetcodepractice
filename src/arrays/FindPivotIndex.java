package arrays;

/**
 * LeetCode 724 - Find Pivot Index
 *
 * Problem:
 *   Given an array of integers nums, return the pivot index — the index where
 *   the sum of all elements to the left equals the sum of all elements to the right.
 *   If no such index exists, return -1.
 *   If there are multiple pivot indexes, return the leftmost one.
 *   Elements directly at the pivot index are not included in either side sum.
 *
 * Approach 1 — Prefix Sum (Two Pass):
 *   Pass 1: compute totalSum.
 *   Pass 2: track leftSum as we iterate.
 *     rightSum = totalSum - leftSum - nums[i]
 *     If leftSum == rightSum → pivot found.
 *   Time: O(n)  Space: O(1)
 *
 * Approach 2 — Prefix Sum (Your approach — also valid):
 *   Initialize rightSum = totalSum, leftSum = 0.
 *   At each step: subtract nums[i] from rightSum, build leftSum from previous element.
 *   Time: O(n)  Space: O(1)
 *
 * Example:
 *   nums = [1, 7, 3, 6, 5, 6]
 *   total = 28
 *   i=0: left=0,  right=28-0-1=27  → no
 *   i=1: left=1,  right=28-1-7=20  → no
 *   i=2: left=8,  right=28-8-3=17  → no
 *   i=3: left=11, right=28-11-6=11 → pivot at index 3 ✓
 */
public class FindPivotIndex {

    public static void main(String[] args) {
        // Test 1: pivot exists in the middle
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));   // Expected: 3

        // Test 2: no pivot exists
        System.out.println(pivotIndex(new int[]{1, 2, 3}));             // Expected: -1

        // Test 3: pivot at index 0 — left sum is 0
        System.out.println(pivotIndex(new int[]{2, 1, -1}));            // Expected: 0

        // Test 4: pivot at last index — right sum is 0
        System.out.println(pivotIndex(new int[]{-1, 1, 2}));            // Expected: 2

        // Test 5: single element — left and right both 0
        System.out.println(pivotIndex(new int[]{1}));                    // Expected: 0

        // Test 6: all zeros
        System.out.println(pivotIndex(new int[]{0, 0, 0}));             // Expected: 0 (leftmost)
    }

    /**
     * Approach 1 — Two Pass Prefix Sum (Optimal and cleaner)
     * rightSum at index i = totalSum - leftSum - nums[i]
     * No need to maintain a separate rightSum variable.
     */
    public static int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) return i;
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * Approach 2 — Two Pass with explicit rightSum
     * Subtract from rightSum and build leftSum from previous element.
     */
    public static int pivotIndexV2(int[] nums) {
        int leftSum = 0, rightSum = 0, n = nums.length;

        for (int i = 0; i < n; i++)
            rightSum += nums[i];

        for (int i = 0; i < n; i++) {
            rightSum -= nums[i];
            if (i > 0) leftSum += nums[i - 1];
            if (leftSum == rightSum) return i;
        }
        return -1;
    }
}