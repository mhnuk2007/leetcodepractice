package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 77 - Combinations
 * <p>
 * Problem:
 * Given two integers n and k, return all possible combinations of k numbers
 * chosen from the range [1, n]. Order does not matter.
 * <p>
 * Approach: Backtracking with pruning
 * Build combinations incrementally. At each step, add a number >= start to
 * avoid duplicates, recurse, then remove it. Pruning: if fewer than
 * (k - current.size()) numbers remain in [i..n], skip — can't complete.
 * <p>
 * Example:
 * n = 4, k = 2 → [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * <p>
 * Time  : O(C(n,k) * k) — C(n,k) combinations, each copied in O(k)
 * Space : O(k) recursion depth + O(C(n,k) * k) output storage
 */
public class Combinations {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("n=4, k=2: " + combine(4, 2));
        // Expected: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]

        // Test 2: k == n (only one combination)
        System.out.println("n=3, k=3: " + combine(3, 3));
        // Expected: [[1,2,3]]

        // Test 3: k == 1 (each number alone)
        System.out.println("n=4, k=1: " + combine(4, 1));
        // Expected: [[1],[2],[3],[4]]
    }


    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int end, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= end - (k - current.size()) + 1; i++) {
            current.add(i);
            backtrack(i + 1, end, k, current, result);
            current.remove(current.size() - 1);
        }
    }
}