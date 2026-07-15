package slidingwindow;

/**
 * LeetCode 76 — Minimum Window Substring
 *
 * <p>Given two strings s and t of lengths m and n respectively, return the
 * minimum window substring of s such that every character in t (including
 * duplicates) is included in the window. If there is no such substring,
 * return the empty string "".
 *
 * <p><b>Approach — Sliding Window (Variable Size) with Frequency Array:</b><br>
 * We maintain a sliding window [i, j) on string s. We also keep a frequency array
 * of characters needed from t (initially populated with counts from t) and a
 * variable `required` tracking the total number of character instances we need
 * to match to have a valid window.
 *
 * <p>1. We expand the window by moving the right pointer `j` and decrementing the count
 * of s.charAt(j) in the frequency array. If that character was needed (frequency was > 0),
 * we decrement `required`.
 * <p>2. Once `required == 0`, the current window contains all characters of t. We try
 * to shrink the window from the left by moving `i` to find the minimum length.
 * <p>3. As we exclude s.charAt(i), we increment its count in the frequency array.
 * If the count becomes > 0, it means we no longer have this character satisfied in the window,
 * so we increment `required` to break the inner loop and resume expanding.
 *
 * <p>Time Complexity  — O(m + n): where m is s.length() and n is t.length().
 *                        Each character in s is visited at most twice.
 * <p>Space Complexity — O(1): auxiliary space using a fixed-size array of 128
 *                        for ASCII characters (assuming standard English letters).
 *
 * @see <a href="https://leetcode.com/problems/minimum-window-substring/">LC 76</a>
 */
public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.length() < t.length() || t.isEmpty()) return "";
        int[] counts = new int[128];
        for (char c : t.toCharArray()) counts[c]++;

        int required = t.length();
        int i = 0, j = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (j < s.length()) {
            if (counts[s.charAt(j++)]-- > 0) required--;
            while (required == 0) {
                if (j - i < minLen) {
                    minLen = j - i;
                    start = i;
                }

                if (counts[s.charAt(i++)]++ == 0) required++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        // Test 1: Standard case
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        System.out.println("Test 1: " + minWindow(s1, t1)); // Expected: "BANC"

        // Test 2: Entire string is the window
        String s2 = "a", t2 = "a";
        System.out.println("Test 2: " + minWindow(s2, t2)); // Expected: "a"

        // Test 3: No valid window possible (t is longer than s)
        String s3 = "a", t3 = "aa";
        System.out.println("Test 3: " + minWindow(s3, t3)); // Expected: ""

        // Test 4: Duplicate characters in t
        String s4 = "AA", t4 = "AA";
        System.out.println("Test 4: " + minWindow(s4, t4)); // Expected: "AA"

        // Test 5: Multiple valid windows, chooses shortest
        String s5 = "aaflslflca", t5 = "ca";
        System.out.println("Test 5: " + minWindow(s5, t5)); // Expected: "ca"

        // Test 6: Empty strings
        String s6 = "", t6 = "";
        System.out.println("Test 6: " + minWindow(s6, t6)); // Expected: ""
    }
}

