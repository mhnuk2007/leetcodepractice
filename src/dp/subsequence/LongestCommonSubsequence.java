package dp;

/**
 * LeetCode 1143 - Longest Common Subsequence
 *
 * <p>Given two strings text1 and text2, return the length of their longest
 * common subsequence. A subsequence is a sequence derived from a string by
 * deleting some (or no) characters without changing the relative order of
 * the remaining characters.
 *
 * <p>Pattern: 2D DP on two sequences
 * <ul>
 *   <li>Match    → dp[i][j] = 1 + dp[i-1][j-1]</li>
 *   <li>Mismatch → dp[i][j] = max(dp[i-1][j], dp[i][j-1])</li>
 * </ul>
 *
 * <p>Foundation for related problems:
 * <ul>
 *   <li>LC 1092 Shortest Common Supersequence  — length = n + m - LCS</li>
 *   <li>LC 583  Delete Operations for Two Strings — deletes = n + m - 2 * LCS</li>
 *   <li>LC 712  Minimum ASCII Delete Sum        — weighted delete variant</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - recursive with cache</li>
 *   <li>Bottom-Up DP (2D Table)   - iterative tabulation</li>
 *   <li>Bottom-Up DP (1D Optimized) - space-optimized with two rows</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class LongestCommonSubsequence {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(n * m)
    // Space : O(n * m) — memo table + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the length of the longest common subsequence using top-down
     * memoized recursion.
     *
     * @param text1 first input string
     * @param text2 second input string
     * @return length of LCS
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        Integer[][] dp = new Integer[n][m];
        return helper(text1, text2, n - 1, m - 1, dp);
    }

    /**
     * Recursive helper — returns LCS length for text1[0..i] and text2[0..j].
     *
     * @param text1 first string
     * @param text2 second string
     * @param i     current index in text1
     * @param j     current index in text2
     * @param dp    memoization table
     * @return LCS length
     */
    private static int helper(String text1, String text2, int i, int j, Integer[][] dp) {
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != null) return dp[i][j];
        if (text1.charAt(i) == text2.charAt(j)) {
            return dp[i][j] = 1 + helper(text1, text2, i - 1, j - 1, dp);
        }
        return dp[i][j] = Math.max(
                helper(text1, text2, i - 1, j, dp),
                helper(text1, text2, i, j - 1, dp)
        );
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (2D Table)
    // Time  : O(n * m)
    // Space : O(n * m)
    // -------------------------------------------------------------------------

    /**
     * Returns the length of the longest common subsequence using a bottom-up
     * 2D DP table.
     *
     * <p>dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1].
     * Recurrence:
     * <pre>
     *   text1[i-1] == text2[j-1] → dp[i][j] = 1 + dp[i-1][j-1]
     *   otherwise                → dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * </pre>
     * Base case: dp[0][j] = dp[i][0] = 0 — guaranteed by Java zero-init.
     *
     * @param text1 first input string
     * @param text2 second input string
     * @return length of LCS
     */
    public static int longestCommonSubsequence2(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (1D Space-Optimized — single array + prevDiag)
    // Time  : O(n * m)
    // Space : O(m) — single array, no second row allocation
    // -------------------------------------------------------------------------

    /**
     * Returns the length of the longest common subsequence using a fully
     * space-optimized single 1D array with a {@code prevDiag} variable.
     *
     * <p>In the two-array approach, dp[i][j] reads from three cells:
     * <ul>
     *   <li>prev[j]   → dp[i-1][j]   — skip text1[i], available as dp[j] before overwrite</li>
     *   <li>curr[j-1] → dp[i][j-1]   — skip text2[j], available as dp[j-1] after overwrite</li>
     *   <li>prev[j-1] → dp[i-1][j-1] — diagonal, lost once dp[j] is overwritten in place</li>
     * </ul>
     * <p>{@code prevDiag} saves {@code dp[j]} before overwriting it, providing the diagonal
     * value for the next iteration — eliminating the need for a second array entirely.
     *
     * @param text1 first input string
     * @param text2 second input string
     * @return length of LCS
     */
    public static int longestCommonSubsequence3(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            int prevDiag = 0;                                    // tracks dp[i-1][j-1]
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];                                // save before overwrite
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + prevDiag;                       // match: diagonal + 1
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);         // mismatch: max of top or left
                }
                prevDiag = temp;                                 // diagonal for next j
            }
        }
        return dp[m];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: LCS = "ace" → length 3
        System.out.println("Test 1 - Top-Down   : " + longestCommonSubsequence("abcde", "ace"));  // 3
        System.out.println("Test 1 - 2D Bottom-Up: " + longestCommonSubsequence2("abcde", "ace")); // 3
        System.out.println("Test 1 - 1D Optimized: " + longestCommonSubsequence3("abcde", "ace")); // 3

        System.out.println();

        // Test Case 2: Identical strings — LCS = full string → length 3
        System.out.println("Test 2 - Top-Down   : " + longestCommonSubsequence("abc", "abc"));  // 3
        System.out.println("Test 2 - 2D Bottom-Up: " + longestCommonSubsequence2("abc", "abc")); // 3
        System.out.println("Test 2 - 1D Optimized: " + longestCommonSubsequence3("abc", "abc")); // 3

        System.out.println();

        // Test Case 3: No common characters → length 0
        System.out.println("Test 3 - Top-Down   : " + longestCommonSubsequence("abc", "def"));  // 0
        System.out.println("Test 3 - 2D Bottom-Up: " + longestCommonSubsequence2("abc", "def")); // 0
        System.out.println("Test 3 - 1D Optimized: " + longestCommonSubsequence3("abc", "def")); // 0

        System.out.println();

        // Test Case 4: One empty string → length 0
        System.out.println("Test 4 - Top-Down   : " + longestCommonSubsequence("", "abc"));  // 0
        System.out.println("Test 4 - 2D Bottom-Up: " + longestCommonSubsequence2("", "abc")); // 0
        System.out.println("Test 4 - 1D Optimized: " + longestCommonSubsequence3("", "abc")); // 0

        System.out.println();

        // Test Case 5: Single character match
        System.out.println("Test 5 - Top-Down   : " + longestCommonSubsequence("a", "a"));  // 1
        System.out.println("Test 5 - 2D Bottom-Up: " + longestCommonSubsequence2("a", "a")); // 1
        System.out.println("Test 5 - 1D Optimized: " + longestCommonSubsequence3("a", "a")); // 1
    }
}