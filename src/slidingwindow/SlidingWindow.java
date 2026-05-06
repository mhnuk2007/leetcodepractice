package slidingwindow;

/**
 * Maximum Sum Subarray of Size K (Fixed Sliding Window)
 *
 * <p>Given an integer array nums and a window size k, find the maximum sum
 * of any contiguous subarray of size k.
 *
 * <p>Approaches (ordered brute force to optimized):
 * <ol>
 *   <li>Brute Force (Nested Loops)  -- O(nk) time, O(1) space</li>
 *   <li>Sliding Window              -- O(n)  time, O(1) space</li>
 *   <li>Prefix Style                -- O(n)  time, O(1) space</li>
 * </ol>
 *
 * <p>Fixed sliding window pattern:
 * <pre>
 *   1. Expand window by adding element at right pointer (end)
 *   2. Once window reaches size k, update maxSum
 *   3. Shrink window by removing element at left pointer (start)
 *   4. Slide forward
 * </pre>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class SlidingWindow {

    // -------------------------------------------------------------------------
    // Input Validation
    // -------------------------------------------------------------------------

    /**
     * Validates input before any sliding window operation.
     *
     * @param nums input array
     * @param k    window size
     * @throws IllegalArgumentException if input is null, empty, or k is out of range
     */
    private static void validate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            throw new IllegalArgumentException(
                    "Invalid input: nums=" + (nums == null ? "null" : "length " + nums.length) + ", k=" + k);
    }

    // -------------------------------------------------------------------------
    // Approach 1: Brute Force (Nested Loops)
    // Time  : O(n * k) -- recomputes sum for every window from scratch
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using brute force.
     *
     * <p>For each starting index i, sums k consecutive elements from scratch.
     * Handles all-negative arrays correctly via {@code Integer.MIN_VALUE} sentinel.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArrayBrute(int[] nums, int k) {
        validate(nums, k);
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i <= n - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Sliding Window (Optimized)
    // Time  : O(n) -- each element added and removed exactly once
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using a sliding window.
     *
     * <p>Expands window by advancing {@code end}. Once window hits size k,
     * updates maxSum then shrinks from {@code start}. Single pass -- O(n).
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArraySliding(int[] nums, int k) {
        validate(nums, k);
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            windowSum += nums[j];
            if (j >= k - 1) {
                maxSum = Math.max(windowSum, maxSum);
                windowSum -= nums[i++];
            }
        }
        return maxSum;
    }

    // -------------------------------------------------------------------------
    // Approach 3: Prefix Style
    // Time  : O(n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using a two-phase loop.
     *
     * <p>Phase 1 (i = 0..k-1): accumulates the first window sum.
     * Phase 2 (i = k..n-1): slides by adding nums[i] and subtracting nums[i-k].
     * {@code maxSum} is seeded with the first window sum after phase 1 --
     * correctly handles all-negative arrays without {@code Integer.MIN_VALUE} init.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArrayPrefix(int[] nums, int k) {
        validate(nums, k);
        int windowSum = 0;
        int maxSum;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        maxSum = windowSum;                        // seed with first window sum
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];   // slide: add new, remove old
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    // -------------------------------------------------------------------------
    // Main -- Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: General case with negatives
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Test 1 - Expected 16 (3+6+7):");
        System.out.println("  Brute Force  : " + maxSubArrayBrute(nums1, k1));
        System.out.println("  Sliding      : " + maxSubArraySliding(nums1, k1));
        System.out.println("  Prefix Style : " + maxSubArrayPrefix(nums1, k1));

        System.out.println();

        // Test Case 2: All positive numbers
        int[] nums2 = {2, 1, 5, 1, 3, 2};
        int k2 = 3;
        System.out.println("Test 2 - Expected 9 (5+1+3):");
        System.out.println("  Brute Force  : " + maxSubArrayBrute(nums2, k2));
        System.out.println("  Sliding      : " + maxSubArraySliding(nums2, k2));
        System.out.println("  Prefix Style : " + maxSubArrayPrefix(nums2, k2));

        System.out.println();

        // Test Case 3: All negative -- verifies sentinel correctness
        int[] nums3 = {-1, -2, -3, -4, -5};
        int k3 = 2;
        System.out.println("Test 3 - Expected -3 (-1+-2):");
        System.out.println("  Brute Force  : " + maxSubArrayBrute(nums3, k3));
        System.out.println("  Sliding      : " + maxSubArraySliding(nums3, k3));
        System.out.println("  Prefix Style : " + maxSubArrayPrefix(nums3, k3));

        System.out.println();

        // Test Case 4: Window size = array length
        int[] nums4 = {1, 2, 3, 4};
        int k4 = 4;
        System.out.println("Test 4 - Expected 10 (1+2+3+4):");
        System.out.println("  Brute Force  : " + maxSubArrayBrute(nums4, k4));
        System.out.println("  Sliding      : " + maxSubArraySliding(nums4, k4));
        System.out.println("  Prefix Style : " + maxSubArrayPrefix(nums4, k4));

        System.out.println();

        // Test Case 5: Window size = 1
        int[] nums5 = {5, -2, 8, 1, 3};
        int k5 = 1;
        System.out.println("Test 5 - Expected 8:");
        System.out.println("  Brute Force  : " + maxSubArrayBrute(nums5, k5));
        System.out.println("  Sliding      : " + maxSubArraySliding(nums5, k5));
        System.out.println("  Prefix Style : " + maxSubArrayPrefix(nums5, k5));

        System.out.println();

        // Test Case 6: Mixed positive and negative
        int[] nums6 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int k6 = 4;
        System.out.println("Test 6 - Expected 6 (4-1+2+1):");
        System.out.println("  Brute Force  : " + maxSubArrayBrute(nums6, k6));
        System.out.println("  Sliding      : " + maxSubArraySliding(nums6, k6));
        System.out.println("  Prefix Style : " + maxSubArrayPrefix(nums6, k6));

        System.out.println();

        // Test Case 7: Validation -- invalid inputs
        try {
            maxSubArraySliding(null, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 (null input)  : " + e.getMessage());
        }
        try {
            maxSubArraySliding(new int[]{1, 2}, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 (k > length)  : " + e.getMessage());
        }
        try {
            maxSubArraySliding(new int[]{}, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 (empty array) : " + e.getMessage());
        }
    }
}