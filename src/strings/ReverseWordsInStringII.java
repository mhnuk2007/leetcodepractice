package strings;

import java.util.Arrays;

/**
 * LC 186 - Reverse Words in a String II
 *
 * Problem:
 *   Given a character array s, reverse the order of the words in-place.
 *   A word is a sequence of non-space characters.
 *   Words are separated by exactly one space. No leading/trailing spaces.
 *   Must solve in-place — O(1) extra space.
 *
 * Approach: Reverse All → Reverse Each Word
 *   Step 1: Reverse the entire array
 *           → words are in correct order but each word is internally reversed
 *   Step 2: Reverse each word individually
 *           → each word's characters are corrected
 *
 * Why O(1) space is possible here but NOT in LC 151:
 *   LC 151 — input is String (immutable) → must allocate new structures
 *   LC 186 — input is char[] (mutable)   → can swap in-place, zero allocations
 *
 * Example:
 *   s       = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
 *   Step 1  = ['e','u','l','b',' ','s','i',' ','y','k','s',' ','e','h','t']
 *   Step 2  = ['b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e']
 *
 * Time  : O(n)  — each character touched exactly twice (once per step)
 * Space : O(1)  — only two int pointers, no allocations
 */
public class ReverseWordsInStringII {

    public static void main(String[] args) {
        char[] s;

        // Test 1: standard case
        s = "the sky is blue".toCharArray();
        reverseWords(s);
        System.out.println(Arrays.toString(s));
        // Expected: [b, l, u, e,  , i, s,  , s, k, y,  , t, h, e]

        // Test 2: two words
        s = "a good".toCharArray();
        reverseWords(s);
        System.out.println(Arrays.toString(s));
        // Expected: [g, o, o, d,  , a]

        // Test 3: single word — no change
        s = "hello".toCharArray();
        reverseWords(s);
        System.out.println(Arrays.toString(s));
        // Expected: [h, e, l, l, o]

        // Test 4: single char words
        s = "a b c d".toCharArray();
        reverseWords(s);
        System.out.println(Arrays.toString(s));
        // Expected: [d,  , c,  , b,  , a]
    }
    public static void reverseWords(char[] s) {
        int n = s.length;
        reverse(s, 0, n - 1);
        int left = 0;
        for (int right = 0; right <= n; right++) {
            if (right == n || s[right] == ' ') {
                reverse(s, left, right - 1);
                left = right + 1;
            }
        }

    }
    public static void reverse(char[] s, int left, int right){
        while (left < right){
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }
}
