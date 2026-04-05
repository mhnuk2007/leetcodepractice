package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 70 - Climbing Stairs
 *
 * Problem:
 *   You are climbing a staircase with n steps. Each time you can climb
 *   either 1 or 2 steps. Return the number of distinct ways to reach the top.
 *
 * Approach 1: Pure recursion
 *   At each step, choose to take 1 or 2 steps.
 *   Ways(n) = Ways(n-1) + Ways(n-2)
 *   Base case: n == 0 → 1 way (reached top), n < 0 → 0 ways (overstepped)
 *   Recomputes same subproblems repeatedly — O(2^n).
 *
 * Approach 2: Memoization (top-down DP)
 *   Cache results in a HashMap to avoid recomputation.
 *   Each subproblem computed exactly once — O(n).
 *
 * Example:
 *   n = 5 → 8 ways
 *   n = 3 → [1,1,1], [1,2], [2,1] = 3 ways
 *
 * Note: Ways(n) follows the Fibonacci sequence — same recurrence relation.
 *
 * Time  : O(2^n) — pure recursion; O(n) — memoized
 * Space : O(n)   — recursion depth; O(n) — memo map
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("climbStairs(5):     " + climbStairs(5));
        // Expected: 8

        System.out.println("climbStairsMemo(5): " + climbStairsMemo(5));
        // Expected: 8

        // Test 2: base case
        System.out.println("climbStairs(1):     " + climbStairs(1));
        // Expected: 1

        // Test 3: two steps
        System.out.println("climbStairs(2):     " + climbStairs(2));
        // Expected: 2

        // Test 4: three steps
        System.out.println("climbStairs(3):     " + climbStairs(3));
        // Expected: 3
    }

    public static int climbStairs(int n) {
        if (n < 0) return 0;                                               // overstepped
        if (n == 0) return 1;                                              // reached top
        return climbStairs(n - 1) + climbStairs(n - 2);                   // take 1 or 2 steps
    }

    public static int climbStairsMemo(int n) {
        return helper(n, new HashMap<>());
    }

    private static int helper(int n, Map<Integer, Integer> map) {
        if (n < 0) return 0;                                               // overstepped
        if (n == 0) return 1;                                              // reached top
        if (map.containsKey(n)) return map.get(n);                        // cache hit
        int res = helper(n - 1, map) + helper(n - 2, map);               // take 1 or 2 steps
        map.put(n, res);                                                   // cache result
        return res;
    }
}