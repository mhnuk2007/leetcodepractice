package twopointer;

/**
 * LeetCode 287: Find the Duplicate Number
 * <p>
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * <p>
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and using only constant extra space.
 * <p>
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * <p>
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * <p>
 * Example 3:
 * Input: nums = [1,1]
 * Output: 1
 */
public class FindTheDuplicateNumber {

    /**
     * Finds the duplicate number in an array using Floyd's Tortoise and Hare (Cycle Detection) algorithm.
     *
     * @param nums An array of n + 1 integers where each integer is in the range [1, n].
     * @return The single repeated number.
     */
    public int findDuplicate(int[] nums) {
        // TODO: Implement the cycle detection solution here.
        // Phase 1: Find the intersection point in the cycle.
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // Phase 2: Find the entrance to the cycle.
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        FindTheDuplicateNumber solution = new FindTheDuplicateNumber();

        // Test Case 1
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("Test Case 1 (Input: [1,3,4,2,2]): " + solution.findDuplicate(nums1)); // Expected: 2

        // Test Case 2
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("Test Case 2 (Input: [3,1,3,4,2]): " + solution.findDuplicate(nums2)); // Expected: 3

        // Test Case 3
        int[] nums3 = {1, 1};
        System.out.println("Test Case 3 (Input: [1,1]): " + solution.findDuplicate(nums3)); // Expected: 1

        // Test Case 4
        int[] nums4 = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
        System.out.println("Test Case 4 (Input: [2,5,9,6,9,3,8,9,7,1]): " + solution.findDuplicate(nums4)); // Expected: 9
    }
}
