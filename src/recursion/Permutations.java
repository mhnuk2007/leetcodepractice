package recursion;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

/**
 * LeetCode 46 - Permutations
 *
 * Problem:
 *   Given an array nums of distinct integers, return all possible permutations
 *   in any order.
 *
 * Approach: Swap-based recursion
 *   Fix one element at position idx by swapping it with every element from
 *   idx to end. Recurse for idx+1, then swap back to restore original order.
 *
 * Example:
 *   nums = [1, 2, 3]
 *   idx=0: swap(0,0)→[1,2,3], swap(0,1)→[2,1,3], swap(0,2)→[3,2,1]
 *   idx=1: for each above, fix next position similarly
 *   idx=2: base case — record permutation
 *
 * Time  : O(n·n!) — n! permutations, each copy costs O(n)
 * Space : O(n)    — recursion depth, excluding output storage
 */
public class Permutations {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("nums = [1,2,3]: " + permute(new int[]{1, 2, 3}));
        // Expected: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        // Test 2: two elements
        System.out.println("nums = [1,2]: " + permute(new int[]{1, 2}));
        // Expected: [[1,2],[2,1]]

        // Test 3: single element
        System.out.println("nums = [1]: " + permute(new int[]{1}));
        // Expected: [[1]]
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        getPermutations(list, 0, result);
        return result;
    }

    private static void getPermutations(List<Integer> nums, int idx, List<List<Integer>> result) {
        if (idx == nums.size()) {
            result.add(new ArrayList<>(nums));                             // O(n) copy
            return;
        }
        for (int i = idx; i < nums.size(); i++) {
            swap(nums, idx, i);                                            // fix nums[i] at position idx
            getPermutations(nums, idx + 1, result);
            swap(nums, idx, i);                                            // backtrack
        }
    }
}