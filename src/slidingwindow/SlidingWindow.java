package slidingwindow;

/**
 * Maximum Sum Subarray of Size K (Fixed Sliding Window)
 *
 * <p>Given an integer array nums and a window size k, find the maximum sum
 * of any contiguous subarray of size k.
 *
 * <p>Approaches (ordered brute force → optimized):
 * <ol>
 *   <li>Brute Force (Nested Loops)         — O(nk) time, O(1) space</li>
 *   <li>Two-Pointer While Loop             — O(n) time, O(1) space</li>
 *   <li>Two-Pointer While Loop (i/j style) — O(n) time, O(1) space</li>
 *   <li>For-Loop Sliding (subtract style)  — O(n) time, O(1) space</li>
 *   <li>For-Loop Sliding (prefix style)    — O(n) time, O(1) space</li>
 * </ol>
 *
 * <p>Fixed sliding window pattern:
 * <pre>
 *   1. Expand window by adding element at right pointer
 *   2. Once window reaches size k, update maxSum
 *   3. Shrink window by removing element at left pointer
 *   4. Slide forward
 * </pre>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class SlidingWindow {

    // -------------------------------------------------------------------------
    // Approach 1: Brute Force (Nested Loops)
    // Time  : O(n * k) — recomputes sum for every window from scratch
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using brute force.
     *
     * <p>For each starting index, sums k consecutive elements.
     * {@code sum} is reset before each window to avoid accumulation.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArraySum3(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i <= n - k; i++) {
            int sum = 0;
            for (int j = i; j <= i + k - 1; j++) {
                sum += nums[j];
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Two-Pointer While Loop (start/end style)
    // Time  : O(n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using a two-pointer
     * while loop with named start/end pointers.
     *
     * <p>Expands window by advancing end. Once window hits size k,
     * updates maxSum then shrinks from start.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArraySum(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;

        while (end < nums.length) {
            windowSum += nums[end];               // expand
            if (end >= k - 1) {
                maxSum = Math.max(maxSum, windowSum); // update when window full
                windowSum -= nums[start++];           // shrink
            }
            end++;
        }
        return maxSum;
    }

    // -------------------------------------------------------------------------
    // Approach 3: Two-Pointer While Loop (i/j style)
    // Time  : O(n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using a two-pointer
     * while loop with i/j index naming convention.
     *
     * <p>Functionally identical to Approach 2 — uses i/j naming common
     * in competitive programming contexts.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArraySum4(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        while (j < n) {
            windowSum += nums[j];                  // expand
            if (j >= k - 1) {
                maxSum = Math.max(windowSum, maxSum); // update when window full
                windowSum -= nums[i++];               // shrink
            }
            j++;
        }
        return maxSum;
    }

    // -------------------------------------------------------------------------
    // Approach 4: For-Loop Sliding (subtract current, add next style)
    // Time  : O(n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using a for-loop
     * that updates and shrinks within a single loop body.
     *
     * <p>When {@code j >= k-1}, the window is full: update maxSum,
     * then subtract the outgoing element.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArraySum5(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        int i = 0;
        for (int j = 0; j < n; j++) {
            windowSum += nums[j];                  // expand
            if (j >= k - 1) {
                maxSum = Math.max(windowSum, maxSum); // update when window full
                windowSum -= nums[i++];               // shrink
            }
        }
        return maxSum;
    }

    // -------------------------------------------------------------------------
    // Approach 5: For-Loop Sliding (prefix-style: nums[i] - nums[i-k])
    // Time  : O(n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum sum of any k-sized subarray using a two-phase
     * for-loop: build the first window, then slide using difference update.
     *
     * <p>Phase 1 (i = 0..k-1): accumulate first window sum.
     * Phase 2 (i = k..n-1): slide by adding nums[i] and subtracting nums[i-k].
     * {@code maxSum = windowSum} after phase 1 seeds the comparison correctly
     * for all-negative arrays.
     *
     * @param nums input array
     * @param k    window size
     * @return maximum subarray sum of size k
     */
    private static int maxSubArraySum2(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
            maxSum = windowSum;                    // seeds with first window sum
        }
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];   // slide: add new, remove old
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Test 1 - Expected 16 (3+6+7):");
        System.out.println("  Brute Force : " + maxSubArraySum3(nums1, k1));
        System.out.println("  While start : " + maxSubArraySum(nums1, k1));
        System.out.println("  While i/j   : " + maxSubArraySum4(nums1, k1));
        System.out.println("  For subtract: " + maxSubArraySum5(nums1, k1));
        System.out.println("  For prefix  : " + maxSubArraySum2(nums1, k1));

        System.out.println();

        int[] nums2 = {2, 1, 5, 1, 3, 2};
        int k2 = 3;
        System.out.println("Test 2 - Expected 9 (5+1+3):");
        System.out.println("  Brute Force : " + maxSubArraySum3(nums2, k2));
        System.out.println("  While start : " + maxSubArraySum(nums2, k2));
        System.out.println("  While i/j   : " + maxSubArraySum4(nums2, k2));
        System.out.println("  For subtract: " + maxSubArraySum5(nums2, k2));
        System.out.println("  For prefix  : " + maxSubArraySum2(nums2, k2));

        System.out.println();

        // All negative — verifies Integer.MIN_VALUE sentinel correctness
        int[] nums3 = {-1, -2, -3, -4, -5};
        int k3 = 2;
        System.out.println("Test 3 - Expected -3 (-1+-2):");
        System.out.println("  Brute Force : " + maxSubArraySum3(nums3, k3));
        System.out.println("  While start : " + maxSubArraySum(nums3, k3));
        System.out.println("  While i/j   : " + maxSubArraySum4(nums3, k3));
        System.out.println("  For subtract: " + maxSubArraySum5(nums3, k3));
        System.out.println("  For prefix  : " + maxSubArraySum2(nums3, k3));

        System.out.println();

        // Window = array length
        int[] nums4 = {1, 2, 3, 4};
        int k4 = 4;
        System.out.println("Test 4 - Expected 10 (1+2+3+4):");
        System.out.println("  Brute Force : " + maxSubArraySum3(nums4, k4));
        System.out.println("  While start : " + maxSubArraySum(nums4, k4));
        System.out.println("  While i/j   : " + maxSubArraySum4(nums4, k4));
        System.out.println("  For subtract: " + maxSubArraySum5(nums4, k4));
        System.out.println("  For prefix  : " + maxSubArraySum2(nums4, k4));

        System.out.println();

        // Window size = 1
        int[] nums5 = {5, -2, 8, 1, 3};
        int k5 = 1;
        System.out.println("Test 5 - Expected 8:");
        System.out.println("  Brute Force : " + maxSubArraySum3(nums5, k5));
        System.out.println("  While start : " + maxSubArraySum(nums5, k5));
        System.out.println("  While i/j   : " + maxSubArraySum4(nums5, k5));
        System.out.println("  For subtract: " + maxSubArraySum5(nums5, k5));
        System.out.println("  For prefix  : " + maxSubArraySum2(nums5, k5));

        System.out.println();

        // Mixed positive and negative
        int[] nums6 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int k6 = 4;
        System.out.println("Test 6 - Expected 6 (4-1+2+1):");
        System.out.println("  Brute Force : " + maxSubArraySum3(nums6, k6));
        System.out.println("  While start : " + maxSubArraySum(nums6, k6));
        System.out.println("  While i/j   : " + maxSubArraySum4(nums6, k6));
        System.out.println("  For subtract: " + maxSubArraySum5(nums6, k6));
        System.out.println("  For prefix  : " + maxSubArraySum2(nums6, k6));
    }
}