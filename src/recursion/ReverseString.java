package recursion;

import java.util.Arrays;

/**
 * LeetCode 344 - Reverse String
 *
 * Problem:
 *   Given a character array s, reverse it in-place using O(1) extra memory.
 *
 * Approach: Two-pointer recursion
 *   Swap characters at start and end, then recurse inward (start+1, end-1).
 *   Base case: start >= end → all swaps done.
 *
 * Example:
 *   s = ['a','b','c','d','e']
 *   swap(0,4) → ['e','b','c','d','a']
 *   swap(1,3) → ['e','d','c','b','a']
 *   start >= end → done
 *
 * Time  : O(n) — n/2 swaps
 * Space : O(n) — recursion depth
 */
public class ReverseString {

    public static void main(String[] args) {
        // Test 1: odd length
        char[] s1 = "abcde".toCharArray();
        System.out.println("Original: " + Arrays.toString(s1));
        reverseString(s1);
        System.out.println("Reversed: " + Arrays.toString(s1));
        // Expected: [e,d,c,b,a]

        // Test 2: even length
        char[] s2 = "abcd".toCharArray();
        reverseString(s2);
        System.out.println("Reversed: " + Arrays.toString(s2));
        // Expected: [d,c,b,a]

        // Test 3: single character
        char[] s3 = "a".toCharArray();
        reverseString(s3);
        System.out.println("Reversed: " + Arrays.toString(s3));
        // Expected: [a]
    }

    public static void reverseString(char[] s) {
        helper(s, 0, s.length - 1);
    }

    private static void helper(char[] s, int start, int end) {
        if (start >= end) return;                                          // base case — done
        char temp = s[start];
        s[start++]  = s[end];
        s[end--]    = temp;                                                  // swap start and end
        helper(s, start, end);                                    // recurse inward
    }
}