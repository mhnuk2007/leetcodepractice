package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 18 — 4Sum
 *
 * <p>Given an array nums of n integers, return an array of all the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 * <ul>
 *   <li>0 <= a, b, c, d < n</li>
 *   <li>a, b, c, and d are distinct.</li>
 *   <li>nums[a] + nums[b] + nums[c] + nums[d] == target</li>
 * </ul>
 * You may return the answer in any order.
 *
 * <p><b>Approach — Two Pointers with Sorting, Pruning & Overflow Prevention:</b><br>
 * 1. Sort the input array `nums`.
 * 2. Fix the first element `nums[i]` and prune early if the minimum possible sum
 *    is too large or the maximum possible sum is too small.
 * 3. Fix the second element `nums[j]` and apply similar pruning.
 * 4. Use two pointers `k` (left) and `l` (right) to find the remaining two elements
 *    such that the sum matches the target.
 * 5. Handle duplicate values carefully to ensure all returned quadruplets are unique.
 * 6. Use 64-bit integer arithmetic (`long`) for the sum calculation to prevent integer overflow.
 *
 * <p>Time Complexity  — O(N³): where N is the length of nums. With pruning, the average
 *                        case is significantly faster.
 * <p>Space Complexity — O(N): or O(log N) depending on the sorting algorithm implementation.
 *
 * @see <a href="https://leetcode.com/problems/4sum/">LC 18</a>
 */
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Avoid duplicate quadruplets for the first position
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Pruning 1: The minimum possible sum starting with i is too large
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;

            // Pruning 2: The maximum possible sum starting with i is too small
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Avoid duplicate quadruplets for the second position
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Pruning 3: The minimum possible sum starting with i and j is too large
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;

                // Pruning 4: The maximum possible sum starting with i and j is too small
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        // Skip duplicates for third and fourth positions
                        while (k < l && nums[k] == nums[k + 1]) k++;
                        while (k < l && nums[l] == nums[l - 1]) l--;
                        k++;
                        l--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Test 1: Standard case
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println("Test 1: " + fourSum(nums1, target1));
        // Expected: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]

        // Test 2: All duplicates
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println("Test 2: " + fourSum(nums2, target2));
        // Expected: [[2, 2, 2, 2]]

        // Test 3: Overflow prevention
        int[] nums3 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target3 = 0;
        System.out.println("Test 3: " + fourSum(nums3, target3));
        // Expected: [] (true sum is 4,000,000,000 which overflows standard int sum to -294,967,296)

        // Test 4: Too few elements
        int[] nums4 = {1, 2, 3};
        int target4 = 6;
        System.out.println("Test 4: " + fourSum(nums4, target4));
        // Expected: []
    }
}

