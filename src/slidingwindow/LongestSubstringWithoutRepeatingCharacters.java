package slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3 - Longest Substring Without Repeating Characters.
 *
 * <p>Given a string {@code s}, find the length of the longest substring
 * without repeating characters.
 *
 * <p><b>Approach 1 - Frequency Map with While Shrink:</b><br>
 * Maintain a character frequency map. When a duplicate is detected, shrink
 * the window from the left one step at a time until the duplicate is removed.
 * Correct but O(N²) worst case due to the inner while loop.
 *
 * <p><b>Approach 2 - Last Seen Index HashMap:</b><br>
 * Store the last seen index of each character. On duplicate detection, jump
 * the left pointer directly to lastIndex[ch] + 1, eliminating the inner loop.
 * The {@code >= i} guard prevents the left pointer from moving backwards.
 *
 * <p><b>Approach 3 - Last Seen Index Array (ASCII optimized):</b><br>
 * Replace the HashMap with a fixed-size int[128] array for O(1) array lookup
 * with no hashing overhead. Valid for ASCII input (character codes 0–127).
 *
 * <p>Time Complexity:  O(N²) — Approach 1 worst case
 * <p>Time Complexity:  O(N)  — Approach 2 and 3
 * <p>Space Complexity: O(k)  — Approach 1 and 2, k = unique characters in window
 * <p>Space Complexity: O(1)  — Approach 3, fixed 128-element array
 *
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">LC 3</a>
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // -------------------------------------------------------------------------
    // Approach 1: Frequency Map with While Shrink
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a character frequency map.
     *
     * <p>Expands the right pointer each step, incrementing the frequency of
     * the incoming character. When a duplicate is detected (frequency > 1),
     * shrinks the window from the left one step at a time until the duplicate
     * is removed. O(N²) worst case due to the inner while loop.
     *
     * @param s input string
     * @return length of the longest substring without repeating characters
     */
    private static int frequencyMapWhileShrink(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int maxLen = 0;
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            while (freq.get(ch) > 1) {
                char leftCh = s.charAt(i);
                freq.put(leftCh, freq.get(leftCh) - 1);
                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Last Seen Index HashMap
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a last-seen-index HashMap.
     *
     * <p>Stores the most recent index of each character. On duplicate detection,
     * jumps the left pointer directly to {@code lastIndex[ch] + 1}, avoiding
     * the step-by-step shrink of Approach 1. The {@code >= i} guard ensures
     * the left pointer never moves backwards when a character was last seen
     * before the current window started.
     *
     * @param s input string
     * @return length of the longest substring without repeating characters
     */
    private static int lastSeenIndexHashMap(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLen = 0;
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);

            if (lastIndex.containsKey(ch) && lastIndex.get(ch) >= i) {
                i = lastIndex.get(ch) + 1;   // jump left pointer directly
            }

            lastIndex.put(ch, j);
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Approach 3: Last Seen Index Array (ASCII optimized)
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a fixed-size last-seen-index array.
     *
     * <p>Replaces the HashMap with an {@code int[128]} array indexed by ASCII
     * character code. Array lookup has no hashing overhead, making this the
     * fastest approach for ASCII input. Initialised to -1 so that unvisited
     * characters never satisfy the {@code >= i} guard at i=0.
     *
     * @param s input string (ASCII characters only, codes 0–127)
     * @return length of the longest substring without repeating characters
     */
    private static int lastSeenIndexArray(String s) {
        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);
        int maxLen = 0;
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);

            if (lastIndex[ch] >= i) {
                i = lastIndex[ch] + 1;
            }

            lastIndex[ch] = j;
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Main
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        String[] tests = {
            "abcabcbb",   // Expected: 3  ("abc")
            "bbbbb",      // Expected: 1  ("b")
            "pwwkew",     // Expected: 3  ("wke")
            "",           // Expected: 0  (empty string)
            "abba",       // Expected: 2  (guard check: last 'a' before window)
            "dvdf",       // Expected: 3  ("vdf")
            " ",          // Expected: 1  (single space)
            "aab",        // Expected: 2  ("ab")
        };

        System.out.printf("%-15s %-6s %-6s %-6s%n",
            "Input", "App1", "App2", "App3");
        System.out.println("-".repeat(36));

        for (String s : tests) {
            System.out.printf("%-15s %-6d %-6d %-6d%n",
                "\"" + s + "\"",
                frequencyMapWhileShrink(s),
                lastSeenIndexHashMap(s),
                lastSeenIndexArray(s));
        }
    }
}