package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate all subsets (Power Set) using include/exclude recursion.
 */
public class Subsets {

    public static void main(String[] args) {
        // Test 1: Standard case
        System.out.println("Subsets of [1,2,3]: " + subsets(new int[]{1, 2, 3}));
        // Expected: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]

        // Test 2: Single element
        System.out.println("Subsets of [5]: " + subsets(new int[]{5}));
        // Expected: [[5], []]

        // Test 3: Empty array
        System.out.println("Subsets of []: " + subsets(new int[]{}));
        // Expected: [[]]
    }

    /**
     * Generates all subsets (power set) of the given array.
     *
     * Approach : Include/exclude recursion — at each index, branch into
     *            two choices: include nums[idx] or skip it.
     *
     * Time  : O(2^n) — 2 choices per element, n elements
     * Space : O(n)   — recursion depth (excluding output)
     *
     * @param nums input array
     * @return all 2^n subsets
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsets(int[] nums, int idx, List<Integer> current, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Include nums[idx]
        current.add(nums[idx]);
        generateSubsets(nums, idx + 1, current, result);

        // Exclude nums[idx] (backtrack)
        current.remove(current.size() - 1);
        generateSubsets(nums, idx + 1, current, result);
    }
}