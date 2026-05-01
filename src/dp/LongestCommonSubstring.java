package dp;

/**
 * Longest Common Substring (Classic Interview Problem)
 *
 * <p>Given two strings s1 and s2, return the length of their longest common
 * substring. A substring is a contiguous sequence of characters — unlike a
 * subsequence, gaps are not allowed.
 *
 * <p>Key distinction from Longest Common Subsequence (LC 1143):
 * <pre>
 *   LCS (subsequence) : mismatch → max(dp[i-1][j], dp[i][j-1])  — gaps allowed
 *   LCS (substring)   : mismatch → 0                             — reset on break
 *   LCS answer        : dp[n][m]
 *   Substring answer  : max over all dp[i][j]
 * </pre>
 *
 * <p>Pattern: 2D DP on two sequences (diagonal dependency only)
 * <ul>
 *   <li>State      : dp[i][j] = length of longest common substring ending at
 *                    s1[i-1] and s2[j-1]</li>
 *   <li>Transition : match   → dp[i][j] = 1 + dp[i-1][j-1]</li>
 *                    mismatch → dp[i][j] = 0</li>
 *   <li>Answer     : max dp[i][j] across all i, j</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Bottom-Up DP (2D Table)    - standard tabulation</li>
 *   <li>Bottom-Up DP (1D Optimized) - single array, right-to-left traversal</li>
 *   <li>Bottom-Up DP (Diagonal)    - iterate diagonals, O(1) extra space per diagonal</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class LongestCommonSubstring {

    // -------------------------------------------------------------------------
    // Approach 1: Bottom-Up DP (2D Table)
    // Time  : O(n * m)
    // Space : O(n * m)
    // -------------------------------------------------------------------------

    /**
     * Returns the length of the longest common substring using a bottom-up
     * 2D DP table.
     *
     * <p>dp[i][j] = length of longest common substring ending exactly at
     * s1[i-1] and s2[j-1]. On mismatch, dp[i][j] resets to 0, breaking
     * the contiguous chain. The global maximum is tracked across all cells.
     *
     * @param s1 first input string
     * @param s2 second input string
     * @return length of longest common substring
     */
    public static int longestCommonSubstring(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // contiguous chain broken — reset
                }
            }
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (1D Space-Optimized)
    // Time  : O(n * m)
    // Space : O(m) — single array, right-to-left traversal
    // -------------------------------------------------------------------------

    /**
     * Returns the length of the longest common substring using a space-optimized
     * 1D DP array.
     *
     * <p>Since dp[i][j] only depends on dp[i-1][j-1] (the diagonal), the 2D
     * table compresses to a single array. Right-to-left traversal ensures
     * dp[j-1] still holds the previous row's value (dp[i-1][j-1]) when
     * dp[j] is computed — preventing overwrite corruption.
     *
     * @param s1 first input string
     * @param s2 second input string
     * @return length of longest common substring
     */
    public static int longestCommonSubstring2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[] dp = new int[m + 1];
        int maxLen = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {   // right to left — preserves diagonal
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[j] = 1 + dp[j - 1];   // dp[j-1] = old dp[i-1][j-1]
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;                // reset — no diagonal needed
                }
            }
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Approach 3: Diagonal Traversal
    // Time  : O(n * m)
    // Space : O(1) extra — no DP array, traverse each diagonal directly
    // -------------------------------------------------------------------------

    /**
     * Returns the length of the longest common substring by traversing all
     * diagonals of the implicit DP table without storing it.
     *
     * <p>Each diagonal represents a fixed offset (i - j). A common substring
     * ending at (i, j) extends from the diagonal above-left. By scanning each
     * diagonal top-to-bottom, a running count replaces the need for any array.
     * There are n + m - 1 diagonals in total (starting from top-right and
     * sweeping to bottom-left).
     *
     * @param s1 first input string
     * @param s2 second input string
     * @return length of longest common substring
     */
    public static int longestCommonSubstring3(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int maxLen = 0;

        // Diagonals starting from first row (i=0, j=0..m-1)
        for (int startJ = 0; startJ < m; startJ++) {
            int len = 0;
            for (int i = 0, j = startJ; i < n && j < m; i++, j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    maxLen = Math.max(maxLen, ++len);
                } else {
                    len = 0;
                }
            }
        }

        // Diagonals starting from first column (i=1..n-1, j=0)
        for (int startI = 1; startI < n; startI++) {
            int len = 0;
            for (int i = startI, j = 0; i < n && j < m; i++, j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    maxLen = Math.max(maxLen, ++len);
                } else {
                    len = 0;
                }
            }
        }

        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: LCS substring = "cde" → length 3
        System.out.println("Test 1 - 2D Table   : " + longestCommonSubstring("abcde", "afcde"));  // 3
        System.out.println("Test 1 - 1D Optimized: " + longestCommonSubstring2("abcde", "afcde")); // 3
        System.out.println("Test 1 - Diagonal   : " + longestCommonSubstring3("abcde", "afcde"));  // 3

        System.out.println();

        // Test Case 2: Identical strings — entire string is common
        System.out.println("Test 2 - 2D Table   : " + longestCommonSubstring("abc", "abc"));  // 3
        System.out.println("Test 2 - 1D Optimized: " + longestCommonSubstring2("abc", "abc")); // 3
        System.out.println("Test 2 - Diagonal   : " + longestCommonSubstring3("abc", "abc"));  // 3

        System.out.println();

        // Test Case 3: No common characters → 0
        System.out.println("Test 3 - 2D Table   : " + longestCommonSubstring("abc", "xyz"));  // 0
        System.out.println("Test 3 - 1D Optimized: " + longestCommonSubstring2("abc", "xyz")); // 0
        System.out.println("Test 3 - Diagonal   : " + longestCommonSubstring3("abc", "xyz"));  // 0

        System.out.println();

        // Test Case 4: Common substring not at start or end
        System.out.println("Test 4 - 2D Table   : " + longestCommonSubstring("xabcy", "zabc")); // 3
        System.out.println("Test 4 - 1D Optimized: " + longestCommonSubstring2("xabcy", "zabc")); // 3
        System.out.println("Test 4 - Diagonal   : " + longestCommonSubstring3("xabcy", "zabc")); // 3

        System.out.println();

        // Test Case 5: Single character match
        System.out.println("Test 5 - 2D Table   : " + longestCommonSubstring("a", "a"));  // 1
        System.out.println("Test 5 - 1D Optimized: " + longestCommonSubstring2("a", "a")); // 1
        System.out.println("Test 5 - Diagonal   : " + longestCommonSubstring3("a", "a"));  // 1

        System.out.println();

        // Test Case 6: One empty string → 0
        System.out.println("Test 6 - 2D Table   : " + longestCommonSubstring("", "abc"));  // 0
        System.out.println("Test 6 - 1D Optimized: " + longestCommonSubstring2("", "abc")); // 0
        System.out.println("Test 6 - Diagonal   : " + longestCommonSubstring3("", "abc"));  // 0
    }
}