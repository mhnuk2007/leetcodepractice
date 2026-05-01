package strings;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 * <p>
 * Problem: Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        // TODO: Implement the sliding window solution here

        return 0;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();
        
        System.out.println("Test Case 1: " + solution.lengthOfLongestSubstring("abcabcbb")); // Expected: 3
        System.out.println("Test Case 2: " + solution.lengthOfLongestSubstring("bbbbb"));    // Expected: 1
        System.out.println("Test Case 3: " + solution.lengthOfLongestSubstring("pwwkew"));   // Expected: 3
        System.out.println("Test Case 4: " + solution.lengthOfLongestSubstring(""));         // Expected: 0
    }
}
