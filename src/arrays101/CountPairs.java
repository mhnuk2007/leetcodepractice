package arrays101;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 2824: Count Pairs Whose Sum is Less Than Target
 * <p>
 * Given a 0-indexed integer array nums of length n and an integer target,
 * return the number of pairs (i, j) such that 0 <= i < j < n and nums[i] + nums[j] < target.
 * <p>
 * Example 1:
 * Input: nums = [-1,1,2,3,1], target = 2
 * Output: 3
 * Explanation: The pairs with sum less than 2 are:
 * (-1, 1) -> sum = 0 < 2
 * (-1, 2) -> sum = 1 < 2
 * (-1, 3) -> sum = 2 is not less than 2
 * (-1, 1) -> sum = 0 < 2
 * (1, 2) -> sum = 3 is not less than 2
 * (1, 3) -> sum = 4 is not less than 2
 * (2, 3) -> sum = 5 is not less than 2
 * (1, 1) -> sum = 2 is not less than 2
 * Total number of pairs whose sum is less than the target is 3.
 * <p>
 * Example 2:
 * Input: nums = [3,1,0,4,2], target = 5
 * Output: 10
 * Explanation: All pairs have a sum less than 5.
 */
public class CountPairs {

    /**
     * Counts the number of pairs (i, j) such that 0 <= i < j < n and nums[i] + nums[j] < target.
     * <p>
     * This method uses a two-pointer approach after sorting the array.
     * 1. Sort the input list `nums`.
     * 2. Initialize two pointers, `left` at the beginning and `right` at the end of the list.
     * 3. While `left` is less than `right`:
     *    a. If `nums[left] + nums[right] < target`, it means all elements from `nums[left+1]` to `nums[right-1]`
     *       when paired with `nums[left]` will also have a sum less than `target` (because the array is sorted).
     *       So, we add `right - left` to the count and increment `left`.
     *    b. If `nums[left] + nums[right] >= target`, it means `nums[right]` is too large to form a pair with `nums[left]`
     *       (and any element to its left). So, we decrement `right`.
     * <p>
     * Time Complexity: O(n log n) due to sorting. The two-pointer scan is O(n).
     * Space Complexity: O(log n) or O(n) depending on the sort implementation (for auxiliary space).
     *
     * @param nums   The list of integers.
     * @param target The target sum.
     * @return The number of pairs whose sum is less than the target.
     */
    private static int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums); // Sort the list to use two-pointer approach
        int left = 0;
        int right = nums.size() - 1;
        int count = 0;

        while (left < right) {
            if (nums.get(left) + nums.get(right) < target) {
                // If sum < target, all elements from left+1 to right will also form a valid pair with nums.get(left)
                count += (right - left);
                left++;
            } else {
                // If sum >= target, nums.get(right) is too large, move right pointer inwards
                right--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> nums1 = new ArrayList<>(List.of(-1, 1, 2, 3, 1));
        int target1 = 2;
        System.out.println("Test Case 1 (Nums: " + nums1 + ", Target: " + target1 + "): " + countPairs(nums1, target1)); // Expected: 3

        // Test Case 2
        List<Integer> nums2 = new ArrayList<>(List.of(3, 1, 0, 4, 2));
        int target2 = 5;
        System.out.println("Test Case 2 (Nums: " + nums2 + ", Target: " + target2 + "): " + countPairs(nums2, target2)); // Expected: 10

        // Test Case 3: No pairs
        List<Integer> nums3 = new ArrayList<>(List.of(1, 2, 3));
        int target3 = 0;
        System.out.println("Test Case 3 (Nums: " + nums3 + ", Target: " + target3 + "): " + countPairs(nums3, target3)); // Expected: 0

        // Test Case 4: Empty list
        List<Integer> nums4 = new ArrayList<>();
        int target4 = 10;
        System.out.println("Test Case 4 (Nums: " + nums4 + ", Target: " + target4 + "): " + countPairs(nums4, target4)); // Expected: 0

        // Test Case 5: Single element list (no pairs possible)
        List<Integer> nums5 = new ArrayList<>(List.of(5));
        int target5 = 10;
        System.out.println("Test Case 5 (Nums: " + nums5 + ", Target: " + target5 + "): " + countPairs(nums5, target5)); // Expected: 0
    }
}
