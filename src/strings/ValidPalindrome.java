package strings;

/**
 * LeetCode 125: Valid Palindrome
 * <p>
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
 * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * <p>
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * <p>
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 */
public class ValidPalindrome {

    /**
     * Determines if a string is a palindrome, considering only alphanumeric characters and ignoring case.
     * <p>
     * This method uses a two-pointer approach.
     * - `left` pointer starts at the beginning of the string.
     * - `right` pointer starts at the end of the string.
     * <p>
     * The pointers move towards each other. At each step:
     * 1. If the character at `left` is not alphanumeric, `left` is incremented.
     * 2. If the character at `right` is not alphanumeric, `right` is decremented.
     * 3. If both characters are alphanumeric, they are converted to lowercase and compared.
     *    If they are not the same, the string is not a palindrome, and we return false.
     *    If they are the same, we move both pointers inwards.
     * <p>
     * If the loop completes without returning false, it means the string is a palindrome.
     *
     * @param s The input string.
     * @return true if the string is a valid palindrome, false otherwise.
     */
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();

        // Test Case 1
        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("Test Case 1 (Input: \"" + s1 + "\"): " + solution.isPalindrome(s1)); // Expected: true

        // Test Case 2
        String s2 = "race a car";
        System.out.println("Test Case 2 (Input: \"" + s2 + "\"): " + solution.isPalindrome(s2)); // Expected: false

        // Test Case 3
        String s3 = " ";
        System.out.println("Test Case 3 (Input: \"" + s3 + "\"): " + solution.isPalindrome(s3)); // Expected: true

        // Test Case 4
        String s4 = "0P";
        System.out.println("Test Case 4 (Input: \"" + s4 + "\"): " + solution.isPalindrome(s4)); // Expected: false
    }
}
