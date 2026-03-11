package twopointer;

/**
 * LeetCode 2486 - Append Characters to String to Make Subsequence
 *
 * Problem:
 * Given two strings 's' and 't', return the minimum number of characters
 * that need to be appended to the end of 's' so that 't' becomes a
 * subsequence of 's'.
 *
 * A subsequence is a string that can be derived from another string by
 * deleting some (or no) characters without changing the order of remaining characters.
 *
 * Example 1:
 *   Input : s = "coaching", t = "coading"
 *   Output: 4
 *   Explanation: "coa" is matched as subsequence in s, "ding" must be appended.
 *
 * Example 2:
 *   Input : s = "abcde", t = "a"
 *   Output: 0
 *   Explanation: "a" is already a subsequence of s, nothing to append.
 *
 * Example 3:
 *   Input : s = "z", t = "abcde"
 *   Output: 5
 *   Explanation: No character of t matches s, all 5 must be appended.
 *
 * Approach: Two Pointers
 *   - Use pointer i for s, pointer j for t.
 *   - When characters match, advance both pointers.
 *   - When they don't match, advance only i (skip s's character).
 *   - After s is exhausted, j marks how far we got in t.
 *   - Remaining characters in t (t.length() - j) must be appended.
 *
 * Time Complexity : O(n) — single pass through s
 * Space Complexity: O(1) — no extra data structures
 */
public class AppendCharacters {

    public static void main(String[] args) {
        // Test 1: partial match — "coa" matched, "ding" must be appended
        System.out.println(appendCharacters("coaching", "coading")); // 4

        // Test 2: t fully matched as subsequence — nothing to append
        System.out.println(appendCharacters("abcde", "a")); // 0

        // Test 3: no match at all — entire t must be appended
        System.out.println(appendCharacters("z", "abcde")); // 5

        // Test 4: non-contiguous subsequence match — 'a' and 'c' matched
        System.out.println(appendCharacters("abc", "ac")); // 0

        // Test 5: s has only one 'a', t needs two — one must be appended
        System.out.println(appendCharacters("a", "aa")); // 1
    }

    private static int appendCharacters(String s, String t) {
        int i = 0; // pointer for s
        int j = 0; // pointer for t (tracks how much of t is matched)

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                // characters match — advance both pointers
                i++;
                j++;
            } else {
                // no match — skip current character in s, keep j in place
                i++;
            }
        }

        // j = number of characters of t already matched as subsequence in s
        // remaining characters (t.length() - j) must be appended
        return t.length() - j;
    }
}