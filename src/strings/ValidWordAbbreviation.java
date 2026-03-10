package strings;

/**
 * LeetCode 408: Valid Word Abbreviation
 * <p>
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths.
 * For example, "substitution" can be abbreviated as "s10n" ("s" + "ubstitutio" + "n"), "sub4u4n", etc.
 * <p>
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * <p>
 * Example 1:
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * <p>
 * Example 2:
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 */
public class ValidWordAbbreviation {

    /**
     * Checks if a given abbreviation is valid for a word.
     * <p>
     * This method uses a two-pointer approach to iterate through the word and the abbreviation simultaneously.
     * - `i`: Pointer for the `word`.
     * - `j`: Pointer for the `abbr`.
     * <p>
     * The logic handles two cases for the character at `abbr[j]`:
     * 1. If it's a digit:
     *    - It cannot be a leading zero ('0').
     *    - We parse the full number from the abbreviation.
     *    - We advance the `word` pointer `i` by that number.
     * 2. If it's a letter:
     *    - We compare the characters from `word` and `abbr`. If they don't match, it's invalid.
     *    - We advance both pointers.
     * <p>
     * The abbreviation is valid only if both pointers reach the end of their respective strings at the same time.
     *
     * @param word The original word.
     * @param abbr The abbreviation.
     * @return true if the abbreviation is valid, false otherwise.
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0; // Pointer for word
        int j = 0; // Pointer for abbr

        while (i < word.length() && j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                // Abbreviation numbers cannot have leading zeros.
                if (abbr.charAt(j) == '0') {
                    return false;
                }

                int num = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j) - '0');
                    j++;
                }
                i += num; // Advance the word pointer
            } else {
                if (word.charAt(i) != abbr.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }

        // Both pointers must reach the end of their strings for a valid match.
        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        ValidWordAbbreviation solution = new ValidWordAbbreviation();

        // Test Case 1
        String word1 = "internationalization", abbr1 = "i12iz4n";
        System.out.println("Test Case 1 (word=\"" + word1 + "\", abbr=\"" + abbr1 + "\"): " + solution.validWordAbbreviation(word1, abbr1)); // Expected: true

        // Test Case 2
        String word2 = "apple", abbr2 = "a2e";
        System.out.println("Test Case 2 (word=\"" + word2 + "\", abbr=\"" + abbr2 + "\"): " + solution.validWordAbbreviation(word2, abbr2)); // Expected: false

        // Test Case 3: Leading zero in abbreviation
        String word3 = "a", abbr3 = "01";
        System.out.println("Test Case 3 (word=\"" + word3 + "\", abbr=\"" + abbr3 + "\"): " + solution.validWordAbbreviation(word3, abbr3)); // Expected: false

        // Test Case 4: Mismatched lengths
        String word4 = "hi", abbr4 = "2i";
        System.out.println("Test Case 4 (word=\"" + word4 + "\", abbr=\"" + abbr4 + "\"): " + solution.validWordAbbreviation(word4, abbr4)); // Expected: false
    }
}
