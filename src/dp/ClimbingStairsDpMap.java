package dp;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairsDpMap {

    /**
     * Returns the number of distinct ways to climb n stairs,
     * taking 1 or 2 steps at a time.
     *
     * Approach: Top-down DP (memoization)
     * Time: O(n), Space: O(n)
     *
     * climbStairs(1) → 1
     * climbStairs(2) → 2
     * climbStairs(5) → 8
     */
    public int climbStairs(int n) {
        return helper(n, new HashMap<>());
    }

    private static int helper(int n, Map<Integer, Integer> map) {
        if (n == 0 || n == 1) return 1;
        if (map.containsKey(n)) return map.get(n);
        int result = helper(n - 1, map) + helper(n - 2, map);
        map.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        ClimbingStairsDpMap cs = new ClimbingStairsDpMap();
        System.out.println(cs.climbStairs(1)); // Expected: 1
        System.out.println(cs.climbStairs(2)); // Expected: 2
        System.out.println(cs.climbStairs(5)); // Expected: 8
    }
}