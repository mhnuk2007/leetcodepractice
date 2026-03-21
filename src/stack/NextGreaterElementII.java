package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LC 503 — Next Greater Element II
 *
 * Problem: Given a circular integer array, return the next greater number
 * for every element. The next greater number of x is the first greater
 * number to its traversing order next in the array (circular).
 * If none exists return -1.
 *
 * Approach: Simulate two passes over the array using index % n.
 * - First pass  (i < n)  : push indices onto monotonic stack
 * - Second pass (i >= n) : resolve remaining unmatched indices
 * Only push during the first pass — avoids duplicate index entries.
 * Pre-fill result with -1 so unresolved elements need no update.
 *
 * Example:
 * nums = [1, 2, 1]
 * result = [2, -1, 2]
 *
 * Time Complexity : O(n) — each index pushed and popped at most once
 * Space Complexity: O(n) — stack holds at most n indices
 */
public class NextGreaterElementII {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            int index = i % n;
            while (!stack.isEmpty() && nums[index] > nums[stack.peek()])
                result[stack.pop()] = nums[index]; // index is next greater for popped element
            if (i < n) stack.push(index);          // only push during first pass
        }
        return result;
    }

    public static void main(String[] args) {

        // Test 1: Standard case
        System.out.println("=== Test 1: Standard case ===");
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 1})));
        // expected: [2, -1, 2]

        // Test 2: Strictly increasing — last wraps around to first
        System.out.println("\n=== Test 2: Strictly increasing ===");
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 3})));
        // expected: [2, 3, -1]

        // Test 3: Strictly decreasing — all wrap around to first element
        System.out.println("\n=== Test 3: Strictly decreasing ===");
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{3, 2, 1})));
        // expected: [-1, 3, 3]

        // Test 4: All equal — no next greater exists
        System.out.println("\n=== Test 4: All equal ===");
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{5, 5, 5})));
        // expected: [-1, -1, -1]

        // Test 5: Single element — wraps to itself, no greater
        System.out.println("\n=== Test 5: Single element ===");
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{7})));
        // expected: [-1]

        // Test 6: Max at middle — wraps for elements after it
        System.out.println("\n=== Test 6: Max at middle ===");
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 5, 3, 2, 4})));
        // expected: [5, -1, 4, 4, 5]
    }
}