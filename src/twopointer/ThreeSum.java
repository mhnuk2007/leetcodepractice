package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15: 3Sum
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * <p>
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 */
public class ThreeSum {

    /**
     * Finds all unique triplets in the array which give the sum of zero.
     * <p>
     * The approach uses a combination of sorting and the two-pointer technique.
     * 1. Sort the input array. This allows us to easily handle duplicates and use the two-pointer method.
     * 2. Iterate through the array with a primary pointer `i`. For each `nums[i]`, we will try to find two other numbers that sum to `-nums[i]`.
     * 3. To avoid duplicate triplets, we skip any `nums[i]` that is the same as the one before it.
     * 4. For each `nums[i]`, use two pointers, `left` (starting at `i+1`) and `right` (starting at the end of the array), to find a pair that sums to the target.
     *    - If `sum < 0`, we need a larger sum, so we move `left` to the right.
     *    - If `sum > 0`, we need a smaller sum, so we move `right` to the left.
     *    - If `sum == 0`, we've found a triplet. We add it to our result list.
     * 5. After finding a valid triplet, we move both `left` and `right` pointers and skip any subsequent duplicate elements to ensure the uniqueness of triplets.
     * <p>
     * Time Complexity: O(n^2). Sorting takes O(n log n), and the nested loop (outer loop for `i` and inner two-pointer scan) takes O(n^2).
     * Space Complexity: O(1) or O(n), depending on the space complexity of the sorting algorithm used. We don't use extra space other than the result list.
     *
     * @param nums The input array of integers.
     * @return A list of all unique triplets that sum to zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element of the triplet
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for the second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for the third element
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move pointers to find the next unique pair
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        // Test Case 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test Case 1: Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + solution.threeSum(nums1)); // Expected: [[-1, -1, 2], [-1, 0, 1]]
        System.out.println("--------------------");

        // Test Case 2: No solution
        int[] nums2 = {0, 1, 1};
        System.out.println("Test Case 2: Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + solution.threeSum(nums2)); // Expected: []
        System.out.println("--------------------");

        // Test Case 3: Array with all zeros
        int[] nums3 = {0, 0, 0, 0};
        System.out.println("Test Case 3: Input: " + Arrays.toString(nums3));
        System.out.println("Output: " + solution.threeSum(nums3)); // Expected: [[0, 0, 0]]
        System.out.println("--------------------");
    }
}
