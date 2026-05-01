package strings;

/**
 * LeetCode 680: Valid Palindrome II
 * <p>
 * Given a string s, return true if the s can be a palindrome after deleting at most one character from it.
 * <p>
 * Example 1:
 * Input: s = "aba"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * <p>
 * Example 3:
 * Input: s = "abc"
 * Output: false
 */
public class PalindromeII {

    /**
     * Checks if a string can become a palindrome by deleting at most one character.
     * <p>
     * This method uses a two-pointer approach to scan the string from both ends.
     * When a mismatch is found between `s.charAt(i)` and `s.charAt(j)`, it means one of these
     * two characters must be deleted.
     * <p>
     * We then check two possibilities:
     * 1. Is the substring from `i+1` to `j` a valid palindrome? (i.e., we "deleted" `s.charAt(i)`)
     * 2. Is the substring from `i` to `j-1` a valid palindrome? (i.e., we "deleted" `s.charAt(j)`)
     * <p>
     * If either of these checks returns true, the original string can become a palindrome.
     * If the initial loop completes without any mismatches, the string is already a palindrome.
     *
     * @param s The input string.
     * @return true if the string can be a palindrome after at most one deletion, false otherwise.
     */
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                // If a mismatch is found, check the two sub-problems
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * Helper method to check if a substring is a palindrome.
     */
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeII solution = new PalindromeII();

        // Test Case 1
        String s1 = "aba";
        System.out.println("Test Case 1 (Input: \"aba\"): " + solution.validPalindrome(s1)); // Expected: true

        // Test Case 2
        String s2 = "abca";
        System.out.println("Test Case 2 (Input: \"abca\"): " + solution.validPalindrome(s2)); // Expected: true

        // Test Case 3
        String s3 = "abc";
        System.out.println("Test Case 3 (Input: \"abc\"): " + solution.validPalindrome(s3)); // Expected: false

        // Test Case 4
        String s4 = "deeee";
        System.out.println("Test Case 4 (Input: \"deeee\"): " + solution.validPalindrome(s4)); // Expected: true
    }
}
