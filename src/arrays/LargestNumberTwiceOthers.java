package arrays;

/**
 * LeetCode 747 - Largest Number At Least Twice of Others
 *
 * Problem:
 *   Given an integer array nums, return the index of the largest element
 *   if it is at least twice as large as every other element.
 *   If no such element exists, return -1.
 *
 * Approach: Single Pass — Track max and second max
 *   Find max and nextMax in one pass.
 *   If max >= 2 * nextMax → max is dominant, find and return its index.
 *   Second pass only to find the index of max.
 *
 * Example:
 *   nums = [3, 6, 1, 0]
 *   max = 6, nextMax = 3
 *   6 >= 2*3 → true → return index 1
 *
 * Time  : O(n)  — two passes
 * Space : O(1)
 */
public class LargestNumberTwiceOthers {

    public static void main(String[] args) {
        // Test 1: dominant element exists
        System.out.println(dominantIndex(new int[]{3, 6, 1, 0}));      // Expected: 1

        // Test 2: no dominant element
        System.out.println(dominantIndex(new int[]{1, 2, 3, 4}));      // Expected: -1

        // Test 3: dominant element at index 0
        System.out.println(dominantIndex(new int[]{10, 2, 3, 1}));     // Expected: 0

        // Test 4: dominant element at last index
        System.out.println(dominantIndex(new int[]{1, 2, 3, 10}));     // Expected: 3

        // Test 5: single element — always dominant (nothing to compare)
        System.out.println(dominantIndex(new int[]{1}));                // Expected: 0

        // Test 6: exactly twice — boundary condition
        System.out.println(dominantIndex(new int[]{0, 0, 2}));         // Expected: 2
    }

    private static int dominantIndex(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int nextMax = Integer.MIN_VALUE;

        // Pass 1: find max and second max
        for (int num : nums) {
            if (num > max) {
                nextMax = max;   // old max becomes second max
                max = num;
            } else if (num > nextMax) {
                nextMax = num;   // update second max only
            }
        }

        // Pass 2: find index of max if it is at least twice nextMax
        for (int i = 0; i < n; i++) {
            if (nums[i] == max && max >= 2 * nextMax) return i;
        }
        return -1;
    }
}