package strings;

/**
 * LC 557 - Reverse Words in a String III
 *
 * Problem:
 *   Given a string s, reverse the order of characters in each word
 *   while preserving whitespace and initial word order.
 *   Words are separated by a single space. No leading/trailing spaces.
 *
 * Approach 1 — char[] two-pointer per word:
 *   Convert to char[]. Scan left to right tracking word boundaries.
 *   Reverse each word in-place with two pointers. Build result string once.
 *   Time: O(n)  Space: O(n) — for char[] and output String
 *
 * Approach 2 — split / reverse / join:
 *   Split by space, reverse each word using StringBuilder.reverse(),
 *   rejoin with String.join().
 *   Time: O(n)  Space: O(n) — more allocations, cleaner code
 *
 * Comparison with LC 151 and LC 186:
 *   LC 151: reverse word ORDER     → String input  → O(n) space
 *   LC 186: reverse word ORDER     → char[] input  → O(1) space (in-place)
 *   LC 557: reverse word CHARS     → String input  → O(n) space minimum
 *
 * Example:
 *   Input : "Let's take LeetCode contest"
 *   Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * Time  : O(n)
 * Space : O(n)
 */
public class ReverseWordsInStringIII {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println(reverseWords("Let's take LeetCode contest")); // Expected: s'teL ekat edoCteeL tsetnoc

        // Test 2: single word
        System.out.println(reverseWords("hello"));                        // Expected: olleh

        // Test 3: single char words
        System.out.println(reverseWords("a b c"));                        // Expected: a b c

        // Test 4: two words
        System.out.println(reverseWords("the sky"));                      // Expected: eht yks

        // Test 5: palindrome word — unchanged
        System.out.println(reverseWords("racecar level"));                // Expected: racecar level
    }

    private static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        for (int j = 0; j <= n; j++) {
            if (j == n || arr[j] == ' ') {
                reverse(arr, i, j - 1);
                i = j + 1;
            }
        }
        return new String(arr);
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

}
