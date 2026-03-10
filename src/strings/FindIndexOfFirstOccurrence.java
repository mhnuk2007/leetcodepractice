package strings;

/**
 * LeetCode 28: Find the Index of the First Occurrence in a String
 * <p>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6. The first occurrence is at index 0, so we return 0.
 * <p>
 * Example 2:
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 */
public class FindIndexOfFirstOccurrence {

    /**
     * Approach 1: Using Java's built-in indexOf method.
     * This is the simplest and most practical solution for production code.
     *
     * @param haystack The string to be searched.
     * @param needle   The string to search for.
     * @return The index of the first occurrence, or -1 if not found.
     */
    public int strStrSimple(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * Approach 2: Manual Sliding Window (Brute-Force).
     * This approach manually checks for the needle at every possible starting position in the haystack.
     * It's a common expectation in coding interviews to demonstrate this underlying logic.
     *
     * @param haystack The string to be searched.
     * @param needle   The string to search for.
     * @return The index of the first occurrence, or -1 if not found.
     */
    public int strStrManual(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int hLen = haystack.length();
        int nLen = needle.length();
        if (hLen < nLen) {
            return -1;
        }

        // Iterate through the haystack, but only up to the last possible starting point.
        for (int i = 0; i <= hLen - nLen; i++) {
            // Check if the substring of haystack starting at i matches the needle.
            int j;
            for (j = 0; j < nLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break; // Mismatch found, break the inner loop.
                }
            }
            // If the inner loop completed without a break, we found a match.
            if (j == nLen) {
                return i;
            }
        }

        return -1; // No match found.
    }

    public static void main(String[] args) {
        FindIndexOfFirstOccurrence solution = new FindIndexOfFirstOccurrence();

        String haystack1 = "sadbutsad";
        String needle1 = "sad";
        System.out.println("Manual   - Haystack: \"" + haystack1 + "\", Needle: \"" + needle1 + "\": " + solution.strStrManual(haystack1, needle1)); // Expected: 0
        System.out.println("Simple   - Haystack: \"" + haystack1 + "\", Needle: \"" + needle1 + "\": " + solution.strStrSimple(haystack1, needle1)); // Expected: 0
        System.out.println("--------------------");

        String haystack2 = "leetcode";
        String needle2 = "leeto";
        System.out.println("Manual   - Haystack: \"" + haystack2 + "\", Needle: \"" + needle2 + "\": " + solution.strStrManual(haystack2, needle2)); // Expected: -1
        System.out.println("Simple   - Haystack: \"" + haystack2 + "\", Needle: \"" + needle2 + "\": " + solution.strStrSimple(haystack2, needle2)); // Expected: -1
        System.out.println("--------------------");

        String haystack3 = "hello";
        String needle3 = "ll";
        System.out.println("Manual   - Haystack: \"" + haystack3 + "\", Needle: \"" + needle3 + "\": " + solution.strStrManual(haystack3, needle3)); // Expected: 2
        System.out.println("Simple   - Haystack: \"" + haystack3 + "\", Needle: \"" + needle3 + "\": " + solution.strStrSimple(haystack3, needle3)); // Expected: 2
    }
}
