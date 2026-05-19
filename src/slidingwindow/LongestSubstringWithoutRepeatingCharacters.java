package slidingwindow;

import java.util.*;

/**
 * LeetCode 3 — Longest Substring Without Repeating Characters
 *
 * <p>Given a string {@code s}, find the length of the longest substring
 * without repeating characters.
 *
 * <p><b>Approach 1 — HashSet with While Shrink (intuitive brute-ish):</b><br>
 * Maintain a HashSet of characters in the current window. When a duplicate
 * is detected, shrink the window from the left one step at a time until the
 * duplicate is removed. O(N) amortized — each character is added and removed
 * at most once — but O(N²) worst case in terms of inner-loop iterations.
 *
 * <p><b>Approach 2 — Frequency Map with While Shrink:</b><br>
 * Same shrink strategy using a frequency map instead of a set. Detects the
 * duplicate when {@code freq[ch] > 1}. Equivalent complexity to Approach 1.
 *
 * <p><b>Approach 3 — Last Seen Index HashMap:</b><br>
 * Store the last seen index of each character. On duplicate detection, jump
 * the left pointer directly to {@code lastIndex[ch] + 1}, eliminating the
 * inner while loop entirely. The {@code >= i} guard prevents the left pointer
 * from moving backwards when a character was last seen before the current
 * window started. True O(N).
 *
 * <p><b>Approach 4 — Last Seen Index Array (ASCII optimised):</b><br>
 * Replace the HashMap with a fixed-size {@code int[128]} array indexed by
 * ASCII character code. Array lookup has no hashing overhead, making this
 * the fastest approach for ASCII input. Initialised to {@code -1} so that
 * unvisited characters never satisfy the {@code >= i} guard at {@code i = 0}.
 *
 * <p>Time  — O(N²) worst case: Approaches 1, 2 (inner while loop)
 * <p>Time  — O(N):             Approaches 3, 4 (direct pointer jump)
 * <p>Space — O(k):             Approaches 1, 2, 3 — k = unique chars in window
 * <p>Space — O(1):             Approach 4 — fixed 128-element array
 *
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">LC 3</a>
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // -------------------------------------------------------------------------
    // Approach 1 — HashSet with While Shrink
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a HashSet window.
     *
     * <p>Expands right pointer each step, adding the character to the set.
     * When a duplicate is detected (set already contains the character),
     * shrinks the window from the left one character at a time until the
     * duplicate is removed, then adds the new character.
     *
     * <p>Most intuitive implementation — easiest to derive under pressure.
     *
     * @param s input string
     * @return length of the longest substring without repeating characters
     */
    public static int hashSetWhileShrink(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);

            while (set.contains(ch)) {
                set.remove(s.charAt(i));
                i++;
            }

            set.add(ch);
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Approach 2 — Frequency Map with While Shrink
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a character frequency map.
     *
     * <p>Increments frequency of the incoming character on each right-pointer
     * step. When frequency exceeds 1, shrinks the window from the left one
     * step at a time until the duplicate is removed.
     *
     * <p>Equivalent to Approach 1 in complexity. The frequency map makes the
     * duplicate condition more explicit ({@code freq[ch] > 1}) and scales
     * naturally to problems that need exact counts per character.
     *
     * @param s input string
     * @return length of the longest substring without repeating characters
     */
    public static int frequencyMapWhileShrink(String s) {
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
    // Approach 3 — Last Seen Index HashMap
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a last-seen-index HashMap.
     *
     * <p>Stores the most recent index of each character. On duplicate detection,
     * jumps the left pointer directly to {@code lastIndex[ch] + 1}, eliminating
     * the inner while loop entirely.
     *
     * <p>The {@code >= i} guard is critical: without it, a character last seen
     * before the current window would incorrectly move the left pointer backwards.
     * For example, in {@code "abba"} when the second {@code 'a'} is processed,
     * the first {@code 'a'} was at index 0, which is before the current window
     * start — so the guard correctly ignores it.
     *
     * @param s input string
     * @return length of the longest substring without repeating characters
     */
    public static int lastSeenIndexHashMap(String s) {
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
    // Approach 4 — Last Seen Index Array (ASCII optimised)
    // -------------------------------------------------------------------------

    /**
     * Finds the longest substring length using a fixed-size last-seen-index array.
     *
     * <p>Replaces the HashMap with an {@code int[128]} array indexed by ASCII
     * character code (0–127). Array lookup has no hashing overhead, making this
     * the fastest approach for ASCII input.
     *
     * <p>Initialised to {@code -1} so that unvisited characters produce
     * {@code lastIndex[ch] = -1}, which never satisfies the {@code >= i} guard
     * at {@code i = 0}, correctly treating them as unseen.
     *
     * <p>Not suitable for Unicode input — use Approach 3 instead.
     *
     * @param s input string (ASCII characters only, codes 0–127)
     * @return length of the longest substring without repeating characters
     */
    public static int lastSeenIndexArray(String s) {
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

        System.out.printf("%-17s %-6s %-6s %-6s %-6s%n",
                "Input", "App1", "App2", "App3", "App4");
        System.out.println("-".repeat(42));

        for (String s : tests) {
            System.out.printf("%-17s %-6d %-6d %-6d %-6d%n",
                    "\"" + s + "\"",
                    hashSetWhileShrink(s),
                    frequencyMapWhileShrink(s),
                    lastSeenIndexHashMap(s),
                    lastSeenIndexArray(s));
        }
    }
}