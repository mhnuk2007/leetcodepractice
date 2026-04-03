package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 509 - Fibonacci Number
 *
 * Problem:
 *   Given n, return the nth Fibonacci number.
 *   F(0) = 0, F(1) = 1, F(n) = F(n-1) + F(n-2) for n > 1.
 *
 * Approach 1: Pure recursion
 *   Directly apply the recurrence relation.
 *   Recomputes the same subproblems repeatedly.
 *
 * Approach 2: Memoization (top-down DP)
 *   Cache results in a HashMap to avoid recomputation.
 *   Each subproblem computed exactly once.
 *
 * Example:
 *   n = 5 → F(5) = F(4) + F(3) = 3 + 2 = 5
 *   0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55...
 *
 * Time  : O(2^n) — pure recursion; O(n) — memoized
 * Space : O(n)   — recursion depth; O(n) — memo map
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("fib(10): " + fib(10));
        // Expected: 55

        // Test 2: base cases
        System.out.println("fib(0): " + fib(0));
        // Expected: 0
        System.out.println("fib(1): " + fib(1));
        // Expected: 1

        // Test 3: small value
        System.out.println("fib(5): " + fib(5));
        // Expected: 5

        // Test 4: pure recursive
        System.out.println("fibRec(10): " + fibRec(10));
        // Expected: 55
    }

    public static int fib(int n) {
        return fibMemo(n, new HashMap<>());
    }

    private static int fibMemo(int n, Map<Integer, Integer> map) {
        if (n <= 1) return n;                                              // base case
        if (map.containsKey(n)) return map.get(n);                        // cache hit
        int res = fibMemo(n - 1, map) + fibMemo(n - 2, map);             // recurrence
        map.put(n, res);                                                   // cache result
        return res;
    }

    public static int fibRec(int n) {
        if (n <= 1) return n;                                              // base case
        return fibRec(n - 1) + fibRec(n - 2);                            // recurrence — O(2^n)
    }
}