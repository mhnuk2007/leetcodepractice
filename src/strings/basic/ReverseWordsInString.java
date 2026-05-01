package strings;

/**
 * LeetCode 151 - Reverse Words in a String
 * <p>
 * Problem:
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters.
 * The returned string should not contain leading/trailing spaces,
 * and multiple spaces between words must be reduced to a single space.
 * <p>
 * Approach: Trim → Split → Two-Pointer Reverse → Join
 * 1. trim()    — removes leading/trailing whitespace
 * 2. split("\\s+") — splits on one or more spaces, handles multiple spaces
 * 3. Two-pointer reverse the words array in-place
 * 4. String.join(" ", arr) — rejoins with single space between words
 * <p>
 * Example:
 * Input:  "  the   sky  is    blue  "
 * trim → "the   sky  is    blue"
 * split → ["the", "sky", "is", "blue"]
 * reverse → ["blue", "is", "sky", "the"]
 * join  → "blue is sky the"
 * <p>
 * Time  : O(n)
 * Space : O(n)  — split creates a new array
 */
public class ReverseWordsInString {

    public static void main(String[] args) {
        // Test 1: multiple spaces between words + leading/trailing spaces
        System.out.println(reverseWords("  the   sky  is    blue  ")); // Expected: blue is sky the

        // Test 2: single word
        System.out.println(reverseWords("hello"));                      // Expected: hello

        // Test 3: two words
        System.out.println(reverseWords("a good"));                     // Expected: good a

        // Test 4: already reversed, single spaces
        System.out.println(reverseWords("blue is sky the"));            // Expected: the sky is blue

        // Test 5: single char words
        System.out.println(reverseWords("a b c d"));                    // Expected: d c b a
    }

    public static String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");

        int left = 0, right = arr.length - 1;
        while (left < right) {
            String temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }

        return String.join(" ", arr);
    }
}