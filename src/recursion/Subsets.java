package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 78 - Subsets
 *
 * Problem:
 *   Given an integer array nums of unique elements, return all possible subsets
 *   (the power set). The solution set must not contain duplicate subsets.
 *
 * Approach: Include/Exclude Recursion
 *   At each index, make two choices:
 *     1. Include nums[idx] in the current subset
 *     2. Exclude nums[idx] from the current subset
 *   When idx == nums.length, record the current subset.
 *
 * Example:
 *   nums = [1, 2, 3]
 *   Include 1 → Include 2 → Include 3 → [1,2,3]
 *                         → Exclude 3 → [1,2]
 *            → Exclude 2 → Include 3 → [1,3]
 *                         → Exclude 3 → [1]
 *   Exclude 1 → Include 2 → Include 3 → [2,3]
 *                         → Exclude 3 → [2]
 *             → Exclude 2 → Include 3 → [3]
 *                          → Exclude 3 → []
 *
 * Time  : O(n·2^n) — 2^n subsets, each copy costs O(n)
 * Space : O(n)     — recursion depth, excluding output storage
 */
public class Subsets {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("nums = [1,2,3]: " + subsets(new int[]{1, 2, 3}));
        // Expected: [[1,2,3],[1,2],[1,3],[1],[2,3],[2],[3],[]]

        // Test 2: single element
        System.out.println("nums = [5]: " + subsets(new int[]{5}));
        // Expected: [[5],[]]

        // Test 3: two elements
        System.out.println("nums = [1,2]: " + subsets(new int[]{1, 2}));
        // Expected: [[1,2],[1],[2],[]]

        // Test 4: edge case — empty array
        System.out.println("nums = []: " + subsets(new int[]{}));
        // Expected: [[]]
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsets(int[] nums, int idx, List<Integer> current, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(current));                           // O(n) copy
            return;
        }

        current.add(nums[idx]);                                            // include nums[idx]
        generateSubsets(nums, idx + 1, current, result);

        current.remove(current.size() - 1);                               // exclude nums[idx] (backtrack)
        generateSubsets(nums, idx + 1, current, result);
    }
}