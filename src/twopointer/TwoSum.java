package twopointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1: Two Sum
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 */
public class TwoSum {

    /**
     * Finds two numbers in an array that add up to a specific target using a one-pass HashMap.
     * <p>
     * The approach is to iterate through the array once. For each element `nums[i]`, we calculate
     * the complement `target - nums[i]`. We then check if this complement already exists in our map.
     * - If it exists, we have found our pair, and we return their indices.
     * - If it does not exist, we add the current number `nums[i]` and its index `i` to the map
     *   to be checked against future elements.
     * <p>
     * This ensures we find the solution in a single pass.
     * <p>
     * Time Complexity: O(n), because we iterate through the array once. HashMap operations (get, put) take O(1) on average.
     * Space Complexity: O(n), as in the worst case, we might store all n elements in the HashMap.
     *
     * @param nums   The input array of integers.
     * @param target The target sum.
     * @return An array containing the indices of the two numbers, or an empty array if no solution is found.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }
        // According to the problem statement, a solution always exists.
        // Returning an empty array for the case of no solution found.
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        // Test Case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Test Case 1: Input: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Output: " + Arrays.toString(result1)); // Expected: [0, 1]
        System.out.println("--------------------");

        // Test Case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Test Case 2: Input: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Output: " + Arrays.toString(result2)); // Expected: [1, 2]
        System.out.println("--------------------");

        // Test Case 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Test Case 3: Input: " + Arrays.toString(nums3) + ", Target: " + target3);
        System.out.println("Output: " + Arrays.toString(result3)); // Expected: [0, 1]
        System.out.println("--------------------");
    }
}
