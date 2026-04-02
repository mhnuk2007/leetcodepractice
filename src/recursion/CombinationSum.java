package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 39 - Combination Sum
 *
 * Problem:
 *   Given an array of distinct integers candidates and a target integer,
 *   return all unique combinations where chosen numbers sum to target.
 *   The same number may be chosen unlimited times.
 *
 * Approach: Include/Exclude recursion
 *   Include candidates[idx] → recurse with same idx (reuse allowed)
 *   Exclude candidates[idx] → recurse with idx+1 (move to next)
 *   Base case: target == 0 → record combination    
 *   Prune:     target < 0 or idx == length → return
 *
 * Example:
 *   candidates = [2,3,6,7], target = 7
 *   Include 2 → Include 2 → Include 3 → target=0 → [2,2,3] ✓
 *   Include 7 → target=0 → [7] ✓
 *
 * Time  : O(2^t) — t = target, include/exclude choice at each level
 * Space : O(t)   — recursion depth (target / min candidate)
 */
public class CombinationSum {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("candidates=[2,3,6,7], target=7: " + combinationSum(new int[]{2, 3, 6, 7}, 7));
        // Expected: [[2,2,3],[7]]

        // Test 2: multiple combinations
        System.out.println("candidates=[2,3,5], target=8: " + combinationSum(new int[]{2, 3, 5}, 8));
        // Expected: [[2,2,2,2],[2,3,3],[3,5]]

        // Test 3: no combination
        System.out.println("candidates=[2], target=3: " + combinationSum(new int[]{2}, 3));
        // Expected: []

        // Test 4: single element match
        System.out.println("candidates=[1], target=1: " + combinationSum(new int[]{1}, 1));
        // Expected: [[1]]
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        solve(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private static void solve(int[] candidates, int idx, int target,
                              List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));                          // valid combination found
            return;
        }
        if (idx == candidates.length || target < 0) return;               // pruned

        current.add(candidates[idx]);
        solve(candidates, idx, target - candidates[idx], current, result); // include — reuse allowed
        current.remove(current.size() - 1);

        solve(candidates, idx + 1, target, current, result);              // exclude — move forward
    }
}