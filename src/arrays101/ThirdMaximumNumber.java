package arrays101;

/**
 * LeetCode 414: Third Maximum Number
 * <p>
 * Given an integer array nums, return the third distinct maximum number in this array.
 * If the third maximum does not exist, return the maximum number.
 * <p>
 * Example 1:
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 * <p>
 * Example 2:
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third maximum does not exist, so the maximum (2) is returned instead.
 * <p>
 * Example 3:
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together).
 * The third distinct maximum is 1.
 */
public class ThirdMaximumNumber {

    /**
     * Finds the third distinct maximum number in an array.
     * <p>
     * This method iterates through the array once, maintaining three variables to track the
     * first, second, and third distinct maximums.
     * <p>
     * To handle edge cases like `Integer.MIN_VALUE` gracefully, we initialize the tracking variables
     * to a value smaller than any possible integer, such as `Long.MIN_VALUE`.
     * <p>
     * For each number in the array, we check if it's a duplicate of our current maximums.
     * If not, we update the three maximums accordingly.
     * <p>
     * Finally, if the third maximum was never updated (meaning fewer than three distinct numbers exist),
     * we return the first maximum. Otherwise, we return the third maximum.
     *
     * @param nums The input array of integers.
     * @return The third distinct maximum, or the maximum if it does not exist.
     */
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;

        for (int num : nums) {
            if (num == max1 || num == max2 || num == max3) {
                continue; // Skip duplicates
            }

            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }

        // If max3 was never updated, it means there are fewer than 3 distinct elements.
        // In that case, return the overall maximum (max1).
        return (max3 == Long.MIN_VALUE) ? (int) max1 : (int) max3;
    }

    public static void main(String[] args) {
        ThirdMaximumNumber solution = new ThirdMaximumNumber();

        // Test Case 1
        int[] nums1 = {3, 2, 1};
        System.out.println("Test Case 1 (Input: [3,2,1]): " + solution.thirdMax(nums1)); // Expected: 1

        // Test Case 2
        int[] nums2 = {1, 2};
        System.out.println("Test Case 2 (Input: [1,2]): " + solution.thirdMax(nums2)); // Expected: 2

        // Test Case 3
        int[] nums3 = {2, 2, 3, 1};
        System.out.println("Test Case 3 (Input: [2,2,3,1]): " + solution.thirdMax(nums3)); // Expected: 1

        // Test Case 4: With Integer.MIN_VALUE
        int[] nums4 = {1, 2, Integer.MIN_VALUE};
        System.out.println("Test Case 4 (Input: [1,2,MIN_VALUE]): " + solution.thirdMax(nums4)); // Expected: -2147483648
    }
}
