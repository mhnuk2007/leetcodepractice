package strings;

import java.util.Arrays;

/**
 * LeetCode 344: Reverse String
 * <p>
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * <p>
 * You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * <p>
 * Example 2:
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class ReverseString {

    /**
     * Reverses a character array in-place using a two-pointer approach.
     * <p>
     * - `left` pointer starts at the beginning of the array.
     * - `right` pointer starts at the end of the array.
     * <p>
     * The pointers move towards each other, swapping the characters at their respective positions
     * until they meet in the middle. This ensures the entire array is reversed with O(1) extra space.
     *
     * @param s The character array to be reversed.
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        ReverseString solution = new ReverseString();

        // Test Case 1
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("Test Case 1 Input: " + Arrays.toString(s1));
        solution.reverseString(s1);
        System.out.println("Test Case 1 Output: " + Arrays.toString(s1)); // Expected: [o, l, l, e, h]
        System.out.println("--------------------");

        // Test Case 2
        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        System.out.println("Test Case 2 Input: " + Arrays.toString(s2));
        solution.reverseString(s2);
        System.out.println("Test Case 2 Output: " + Arrays.toString(s2)); // Expected: [h, a, n, n, a, H]
        System.out.println("--------------------");
    }
}
