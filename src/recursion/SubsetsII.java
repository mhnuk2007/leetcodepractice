package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 90 - Subsets II
 *
 * Problem:
 *   Given an integer array nums that may contain duplicates, return all possible
 *   subsets (the power set). The solution set must not contain duplicate subsets.
 *
 * Approach: Include/Exclude Recursion with Duplicate Skipping
 *   Sort the array first so duplicates are adjacent.
 *   At each index, make two choices:
 *     1. Include nums[idx] in the current subset
 *     2. Exclude nums[idx] — skip ALL elements equal to nums[idx] on the
 *        exclude branch to avoid generating duplicate subsets.
 *
 * Example:
 *   nums = [1, 2, 2]  →  sorted: [1, 2, 2]
 *   Include 1 → Include 2 → Include 2 → [1,2,2]
 *                         → Skip  2   → [1,2]
 *            → Skip both 2s           → [1]
 *   Skip  1  → Include 2 → Include 2  → [2,2]
 *                         → Skip  2   → [2]
 *            → Skip both 2s           → []
 *
 * Time  : O(n·2^n) — 2^n subsets in worst case (no duplicates), each copy costs O(n)
 * Space : O(n)     — recursion depth, excluding output storage
 */
public class SubsetsII {

    public static void main(String[] args) {
        // Test 1: one duplicate pair
        System.out.println("nums = [1,2,2]: " + subsetsWithDup(new int[]{1, 2, 2}));
        // Expected: [[],[1],[1,2],[1,2,2],[2],[2,2]]

        // Test 2: multiple duplicates
        System.out.println("nums = [4,4,4,1,4]: " + subsetsWithDup(new int[]{4, 4, 4, 1, 4}));
        // Expected: [[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]

        // Test 3: all same elements
        System.out.println("nums = [2,2,2]: " + subsetsWithDup(new int[]{2, 2, 2}));
        // Expected: [[],[2],[2,2],[2,2,2]]

        // Test 4: no duplicates
        System.out.println("nums = [1,2,3]: " + subsetsWithDup(new int[]{1, 2, 3}));
        // Expected: [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);                                                 // required for duplicate detection
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsets(int[] nums, int idx, List<Integer> current, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(current));                          // O(n) copy
            return;
        }

        current.add(nums[idx]);                                            // include nums[idx]
        generateSubsets(nums, idx + 1, current, result);
        current.remove(current.size() - 1);

        int i = idx + 1;                                                   // exclude nums[idx]
        while (i < nums.length && nums[i] == nums[idx]) {                 // skip all duplicates
            i++;
        }
        generateSubsets(nums, i, current, result);
    }
}