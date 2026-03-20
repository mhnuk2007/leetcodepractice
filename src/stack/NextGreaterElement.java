package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 496 — Next Greater Element I
 *
 * Problem: Given two arrays nums1 and nums2 where nums1 is a subset of nums2,
 * for each element in nums1 find the next greater element in nums2.
 * The next greater element of x is the first element to its right that is
 * greater than x. If none exists return -1.
 *
 * Approach: Monotonic stack on nums2 to precompute next greater for every
 * element, stored in a HashMap. Then look up each nums1 element in O(1).
 *
 * Example:
 * nums1 = [4,1,2], nums2 = [1,3,4,2]
 * next greater: 1→3, 3→-1, 4→-1, 2→-1
 * result = [-1, 3, -1]
 *
 * Time Complexity : O(m + n) — one pass over nums2, one pass over nums1
 * Space Complexity: O(n)     — map and stack both hold at most n elements
 */
public class NextGreaterElement {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        // build next greater map from nums2 using monotonic stack
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num); // num is the next greater for popped element
            }
            stack.push(num);
        }

        // remaining elements in stack have no next greater
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // look up each nums1 element in the precomputed map
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

    public static void main(String[] args) {

        // Test 1: Standard case
        System.out.println("=== Test 1: Standard case ===");
        System.out.println(Arrays.toString(nextGreaterElement(
            new int[]{4, 1, 2},
            new int[]{1, 3, 4, 2}
        )));
        // expected: [-1, 3, -1]

        // Test 2: LeetCode example 2
        System.out.println("\n=== Test 2: All have next greater ===");
        System.out.println(Arrays.toString(nextGreaterElement(
            new int[]{2, 4},
            new int[]{1, 2, 3, 4}
        )));
        // expected: [3, -1]

        // Test 3: nums1 is single element — exists in nums2 with next greater
        System.out.println("\n=== Test 3: Single element with next greater ===");
        System.out.println(Arrays.toString(nextGreaterElement(
            new int[]{1},
            new int[]{1, 3, 2}
        )));
        // expected: [3]

        // Test 4: nums1 is single element — no next greater
        System.out.println("\n=== Test 4: Single element no next greater ===");
        System.out.println(Arrays.toString(nextGreaterElement(
            new int[]{3},
            new int[]{1, 2, 3}
        )));
        // expected: [-1]

        // Test 5: Strictly decreasing nums2 — all -1
        System.out.println("\n=== Test 5: Decreasing nums2 ===");
        System.out.println(Arrays.toString(nextGreaterElement(
            new int[]{4, 3, 2},
            new int[]{4, 3, 2, 1}
        )));
        // expected: [-1, -1, -1]
    }
}