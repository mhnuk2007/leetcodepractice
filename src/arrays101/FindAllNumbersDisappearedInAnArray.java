package arrays101;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 448: Find All Numbers Disappeared in an Array
 * <p>
 * Given an array nums of n integers where nums[i] is in the range [1, n],
 * return an array of all the integers in the range [1, n] that do not appear in nums.
 * <p>
 * Example 1:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * <p>
 * Example 2:
 * Input: nums = [1,1]
 * Output: [2]
 */
public class FindAllNumbersDisappearedInAnArray {

    /**
     * Approach 1: Using a HashSet (O(n) space)
     * <p>
     * This approach uses extra space to keep track of the numbers present in the input array.
     * 1. Add all numbers from the input array into a HashSet.
     * 2. Iterate from 1 to n. If a number is not found in the HashSet, add it to the result list.
     *
     * @param nums The input array.
     * @return A list of disappeared numbers.
     */
    public List<Integer> findDisappearedNumbers_hashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            seen.add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!seen.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Approach 2: In-Place Modification (O(1) space)
     * <p>
     * This optimal approach uses the array itself as a hash map.
     * 1. Iterate through the array. For each number `num`, find its corresponding index (`index = abs(num) - 1`).
     * 2. Mark the number at this index as negative (`nums[index] = -abs(nums[index])`). This indicates that the number `index + 1` has been seen.
     *    We use `abs()` because a value might have already been negated by a previous operation.
     * 3. After the first pass, iterate through the array again. If `nums[i]` is still positive, it means the number `i + 1` was never seen. Add `i + 1` to the result list.
     *
     * @param nums The input array.
     * @return A list of disappeared numbers.
     */
    public List<Integer> findDisappearedNumbers_inPlace(int[] nums) {
        // Pass 1: Mark seen numbers by negating the value at the corresponding index
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        // Pass 2: Collect all numbers that were not seen (i.e., indices with positive values)
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        
        // (Optional) Restore the array to its original state if needed by external callers
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray solution = new FindAllNumbersDisappearedInAnArray();

        // --- Test with HashSet method ---
        System.out.println("--- Testing with HashSet ---");
        int[] nums1_hash = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Test Case 1: " + solution.findDisappearedNumbers_hashSet(nums1_hash)); // Expected: [5, 6]

        int[] nums2_hash = {1, 1};
        System.out.println("Test Case 2: " + solution.findDisappearedNumbers_hashSet(nums2_hash)); // Expected: [2]

        // --- Test with In-Place method ---
        System.out.println("\n--- Testing In-Place ---");
        int[] nums1_inplace = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Test Case 1: " + solution.findDisappearedNumbers_inPlace(nums1_inplace)); // Expected: [5, 6]

        int[] nums2_inplace = {1, 1};
        System.out.println("Test Case 2: " + solution.findDisappearedNumbers_inPlace(nums2_inplace)); // Expected: [2]
    }
}
