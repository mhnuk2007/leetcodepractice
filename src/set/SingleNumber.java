package set;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 136 - Single Number
 *
 * Problem:
 * Given a non-empty array of integers where every element appears twice
 * except for one, find and return that single element.
 * Must run in O(n) time and O(1) extra space.
 *
 * Example 1:
 *   Input : [2, 2, 1]        →  Output: 1
 *
 * Example 2:
 *   Input : [4, 1, 2, 1, 2]  →  Output: 4
 *
 * Example 3:
 *   Input : [1]              →  Output: 1
 *
 * Approach 1 - Brute Force: O(n²) time, O(1) space
 *   For each element, scan the rest of the array to find a duplicate.
 *   If no duplicate found, that element is the single number.
 *
 * Approach 2 - HashSet: O(n) time, O(n) space
 *   Add each number to the set. If it already exists, remove it (pair found).
 *   The one remaining element in the set is the single number.
 *
 * Approach 3 - XOR Bit Manipulation: O(n) time, O(1) space  ← optimal
 *   XOR all elements together. Pairs cancel out (a ^ a = 0).
 *   The single number remains (a ^ 0 = a).
 *   XOR is commutative and associative so order does not matter.
 *
 *   Example: [4, 1, 2, 1, 2]
 *     4 ^ 1 ^ 2 ^ 1 ^ 2
 *     = 4 ^ (1^1) ^ (2^2)
 *     = 4 ^ 0 ^ 0
 *     = 4 ✅
 */
public class SingleNumber {

    public static void main(String[] args) {
        // Test 1: single at end
        System.out.println(singleNumberXOR(new int[]{2, 2, 1}));        // 1

        // Test 2: single at start
        System.out.println(singleNumberXOR(new int[]{4, 1, 2, 1, 2})); // 4

        // Test 3: only one element
        System.out.println(singleNumberXOR(new int[]{1}));              // 1

        // Test 4: negative numbers
        System.out.println(singleNumberXOR(new int[]{-1, -1, -2}));    // -2

        // Test 5: larger array
        System.out.println(singleNumberXOR(new int[]{7, 3, 5, 3, 7})); // 5
    }

    // Approach 1 — Brute Force: O(n²) time, O(1) space
    private static int singleNumberBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean found = false;
            for (int j = 0; j < nums.length; j++) {
                // check if any OTHER index has the same value
                if (i != j && nums[i] == nums[j]) {
                    found = true; // duplicate found — not the single number
                    break;
                }
            }
            if (!found) return nums[i]; // no duplicate — this is the answer
        }
        return -1;
    }

    // Approach 2 — HashSet: O(n) time, O(n) space
    private static int singleNumberSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num); // pair found — cancel out
            } else {
                set.add(num);    // first occurrence — track it
            }
        }
        return set.iterator().next(); // only the single number remains
    }

    // Approach 3 — XOR Bit Manipulation: O(n) time, O(1) space ← optimal
    private static int singleNumberXOR(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // pairs cancel (a^a=0), single survives (a^0=a)
        }
        return result;
    }
}