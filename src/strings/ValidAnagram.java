package strings;

/**
 * LeetCode 242: Valid Anagram
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {

    /**
     * Determines if two strings are anagrams using a frequency counting approach.
     * <p>
     * This optimal approach uses an integer array of size 26 to act as a frequency map for the English alphabet.
     * 1. It first checks if the strings have different lengths, which would immediately mean they cannot be anagrams.
     * 2. It iterates through both strings simultaneously. For each character in `s`, it increments its corresponding
     *    counter in the frequency map. For each character in `t`, it decrements the counter.
     * 3. After the loop, it iterates through the frequency map. If the strings are anagrams, all counts should be zero.
     *    If any count is non-zero, it means the character frequencies did not match.
     * <p>
     * Time Complexity: O(n), where n is the length of the strings.
     * Space Complexity: O(1), because the frequency map's size (26) is constant.
     *
     * @param s The first string.
     * @param t The second string.
     * @return true if t is an anagram of s, false otherwise.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();

        // Test Case 1: Valid anagram
        String s1 = "anagram", t1 = "nagaram";
        System.out.println("Test Case 1 (s=\"anagram\", t=\"nagaram\"): " + solution.isAnagram(s1, t1)); // Expected: true

        // Test Case 2: Invalid anagram
        String s2 = "rat", t2 = "car";
        System.out.println("Test Case 2 (s=\"rat\", t=\"car\"): " + solution.isAnagram(s2, t2)); // Expected: false

        // Test Case 3: Different lengths
        String s3 = "a", t3 = "ab";
        System.out.println("Test Case 3 (s=\"a\", t=\"ab\"): " + solution.isAnagram(s3, t3)); // Expected: false

        // Test Case 4: Same characters, different frequencies
        String s4 = "aacc", t4 = "ccaa";
        System.out.println("Test Case 4 (s=\"aacc\", t=\"ccaa\"): " + solution.isAnagram(s4, t4)); // Expected: true
    }
}
