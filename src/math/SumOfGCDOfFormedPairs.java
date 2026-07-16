package math;

import java.util.Arrays;

/**
 * LeetCode 3867 — Sum of GCD of Formed Pairs
 *
 * <p>Given an integer array nums, we perform the following operations:
 * <ol>
 *   <li>For each element nums[i], calculate the GCD of nums[i] and the running maximum value up to index i.</li>
 *   <li>Sort the resulting values.</li>
 *   <li>Form pairs of elements from the outside in (two-pointer style: i and j where i starts at 0 and j at n - 1),
 *       and calculate the sum of the GCD of each formed pair.</li>
 * </ol>
 * Return the final sum.
 *
 * <p><b>Approach 1 — Recursive GCD with Prefix Array:</b><br>
 * Uses a separate prefix array to store the intermediate GCD calculations. GCD is calculated
 * using recursion.
 * Time Complexity  — O(N log N + N log(max_val)): where N is the length of nums.
 * Space Complexity — O(N): for the prefix GCD array.
 *
 * <p><b>Approach 2 — Iterative GCD In-Place (Optimized):</b><br>
 * Replaces elements of the original array in-place to save memory, and uses an iterative
 * approach for GCD calculation to avoid call-stack overhead.
 * Time Complexity  — O(N log N + N log(max_val))
 * Space Complexity — O(1): auxiliary space.
 *
 * @see <a href="https://leetcode.com/problems/sum-of-gcd-of-formed-pairs/">LC 3867</a>
 */
public class SumOfGCDOfFormedPairs {

    // -------------------------------------------------------------------------
    // Approach 1: Recursive GCD with Prefix Array
    // -------------------------------------------------------------------------
    public static long gcdSumApproach1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int max = 0;
        int[] prefixGCD = new int[n];
        
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            prefixGCD[i] = gcdRecursive(nums[i], max);
        }
        
        Arrays.sort(prefixGCD);
        int i = 0;
        int j = n - 1;
        long sum = 0;
        while (i < j) {
            sum += gcdRecursive(prefixGCD[i], prefixGCD[j]);
            i++;
            j--;
        }
        return sum;
    }

    private static int gcdRecursive(int x, int y) {
        if (y == 0) return x;
        return gcdRecursive(y, x % y);
    }

    // -------------------------------------------------------------------------
    // Approach 2: Iterative GCD In-Place (Optimized)
    // -------------------------------------------------------------------------
    public static long gcdSumApproach2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) max = nums[i];
            nums[i] = gcdIterative(nums[i], max);
        }
        
        Arrays.sort(nums);
        int i = 0;
        int j = n - 1;
        long sum = 0;
        while (i < j) {
            sum += gcdIterative(nums[i], nums[j]);
            i++;
            j--;
        }
        return sum;
    }

    private static int gcdIterative(int x, int y) {
        while (y != 0) {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    public static void main(String[] args) {
        // Test 1: Standard case
        int[] nums1 = {6, 4, 8};
        System.out.println("Test 1 (Approach 1): " + gcdSumApproach1(nums1.clone())); // Expected: 2 (gcd(2, 8))
        System.out.println("Test 1 (Approach 2): " + gcdSumApproach2(nums1.clone())); // Expected: 2

        // Test 2: Another standard case
        int[] nums2 = {4, 6, 8, 12};
        System.out.println("Test 2 (Approach 1): " + gcdSumApproach1(nums2.clone())); // Expected: 6 (gcd(4,12) + gcd(6,8) = 4 + 2 = 6)
        System.out.println("Test 2 (Approach 2): " + gcdSumApproach2(nums2.clone())); // Expected: 6

        // Test 3: Single element (no pairs can be formed)
        int[] nums3 = {10};
        System.out.println("Test 3 (Approach 1): " + gcdSumApproach1(nums3.clone())); // Expected: 0
        System.out.println("Test 3 (Approach 2): " + gcdSumApproach2(nums3.clone())); // Expected: 0

        // Test 4: Empty input
        int[] nums4 = {};
        System.out.println("Test 4 (Approach 1): " + gcdSumApproach1(nums4.clone())); // Expected: 0
        System.out.println("Test 4 (Approach 2): " + gcdSumApproach2(nums4.clone())); // Expected: 0
    }
}
