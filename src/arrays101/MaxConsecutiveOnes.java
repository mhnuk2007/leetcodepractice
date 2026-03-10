package arrays101;

/**
 * LeetCode 485: Max Consecutive Ones
 * <p>
 * Given a binary array nums, return the maximum number of consecutive 1s in the array.
 * <p>
 * Example 1:
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * <p>
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */
public class MaxConsecutiveOnes {

    /**
     * Finds the maximum number of consecutive 1s in a binary array.
     * <p>
     * This method iterates through the array once, keeping track of two variables:
     * - `currentCount`: The number of consecutive 1s in the current sequence.
     * - `maxCount`: The maximum number of consecutive 1s found so far.
     * <p>
     * If the current number is 1, `currentCount` is incremented.
     * If the current number is 0, the sequence of 1s is broken, so `currentCount` is reset to 0.
     * After each step, `maxCount` is updated to be the maximum of its current value and `currentCount`.
     *
     * @param nums The input binary array.
     * @return The maximum number of consecutive 1s.
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                // When we see a 0, the streak is broken.
                currentCount = 0;
            }
            // Update the max count found so far.
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

        // Test Case 1
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        System.out.println("Test Case 1 (Input: [1,1,0,1,1,1]): " + solution.findMaxConsecutiveOnes(nums1)); // Expected: 3

        // Test Case 2
        int[] nums2 = {1, 0, 1, 1, 0, 1};
        System.out.println("Test Case 2 (Input: [1,0,1,1,0,1]): " + solution.findMaxConsecutiveOnes(nums2)); // Expected: 2

        // Test Case 3: All zeros
        int[] nums3 = {0, 0, 0};
        System.out.println("Test Case 3 (Input: [0,0,0]): " + solution.findMaxConsecutiveOnes(nums3)); // Expected: 0

        // Test Case 4: All ones
        int[] nums4 = {1, 1, 1, 1};
        System.out.println("Test Case 4 (Input: [1,1,1,1]): " + solution.findMaxConsecutiveOnes(nums4)); // Expected: 4
    }
}
