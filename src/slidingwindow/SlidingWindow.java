package slidingwindow;

public class SlidingWindow {

    public static void main(String[] args) {

        // Test Case 1: General case with negatives
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Test 1: " + maxSubArraySum(nums1, k1)); // Expected: 16 ([3,6,7])

        // Test Case 2: All positive numbers
        int[] nums2 = {2, 1, 5, 1, 3, 2};
        int k2 = 3;
        System.out.println("Test 2: " + maxSubArraySum(nums2, k2)); // Expected: 9 ([5,1,3])

        // Test Case 3: All negative numbers
        int[] nums3 = {-1, -2, -3, -4, -5};
        int k3 = 2;
        System.out.println("Test 3: " + maxSubArraySum(nums3, k3)); // Expected: -3 ([-1,-2])

        // Test Case 4: Window size = array length
        int[] nums4 = {1, 2, 3, 4};
        int k4 = 4;
        System.out.println("Test 4: " + maxSubArraySum(nums4, k4)); // Expected: 10 ([1,2,3,4])

        // Test Case 5: Window size = 1
        int[] nums5 = {5, -2, 8, 1, 3};
        int k5 = 1;
        System.out.println("Test 5: " + maxSubArraySum(nums5, k5)); // Expected: 8

        // Test Case 6: Mixed positive and negative
        int[] nums6 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int k6 = 4;
        System.out.println("Test 6: " + maxSubArraySum(nums6, k6)); // Expected: 6 ([4,-1,2,1])
    }

    /**
     * Find maximum sum of any contiguous subarray of size k
     *
     * Sliding Window Pattern (Fixed Size):
     * 1. Expand window by adding element at 'end'
     * 2. Once window reaches size k, start updating maxSum
     * 3. Shrink window by removing element at 'start'
     * 4. Slide window forward
     *
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - only using variables
     *
     * @param nums input array
     * @param k window size
     * @return maximum sum of any k-sized subarray
     */
    private static int maxSubArraySum(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;

        while (end < nums.length) {
            // Expand window: add element at end
            windowSum += nums[end];

            // Once window reaches size k
            if (end >= k - 1) {
                // Update maximum
                maxSum = Math.max(maxSum, windowSum);

                // Shrink window: remove element at start
                windowSum -= nums[start];
                start++;
            }

            // Move end pointer forward
            end++;
        }

        return maxSum;
    }
}