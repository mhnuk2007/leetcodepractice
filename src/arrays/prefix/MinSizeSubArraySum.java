package arrays;

/**
 * LC 209 - Minimum Size Subarray Sum
 *
 * Problem:
 *   Given an array of positive integers nums and a positive integer target,
 *   return the minimal length of a subarray whose sum is >= target.
 *   If no such subarray exists, return 0.
 *
 * Approach: Sliding Window (Variable Size)
 *   Expand window by moving right pointer and adding nums[right] to sum.
 *   Once sum >= target, record window length then shrink from the left
 *   to find the minimum valid window before continuing to expand.
 *
 * Key insight:
 *   Because all elements are positive, shrinking the window always decreases
 *   the sum — which guarantees the while loop terminates correctly.
 *   This property is what makes the sliding window valid here.
 *
 * Example:
 *   nums = [2, 3, 1, 2, 4, 3],  target = 7
 *   right=4: sum=2+3+1+2+4=12 >= 7 → len=5, shrink → sum=10,9,8,7,3
 *   best window found: [4, 3] → length 2
 *
 * Time  : O(n)  — each element added and removed at most once
 * Space : O(1)
 */
public class MinSizeSubArraySum {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));    // Expected: 2

        // Test 2: entire array needed
        System.out.println(minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1})); // Expected: 0 (impossible)

        // Test 3: single element satisfies target
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));              // Expected: 1

        // Test 4: no valid subarray
        System.out.println(minSubArrayLen(100, new int[]{1, 2, 3}));            // Expected: 0

        // Test 5: whole array is the answer
        System.out.println(minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));       // Expected: 5

        // Test 6: first element alone satisfies
        System.out.println(minSubArrayLen(3, new int[]{3, 1, 1}));              // Expected: 1
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];                                     // expand window

            while (sum >= target) {                                 // shrink while valid
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}