package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * String Permutations
 *
 * Problem:
 *   Given a string, return all possible permutations of its characters.
 *
 * Approach: Swap-based recursion on char[]
 *   Convert String to char[] once so swaps are in-place.
 *   Fix one character at position idx by swapping it with every character
 *   from idx to end. Recurse for idx+1, then swap back to restore order.
 *
 * Example:
 *   str = "abc"
 *   idx=0: swap(0,0)→"abc", swap(0,1)→"bac", swap(0,2)→"cba"
 *   idx=1: for each above, fix next position similarly
 *   idx=2: base case — record permutation
 *
 * Time  : O(n·n!) — n! permutations, each String construction costs O(n)
 * Space : O(n)    — recursion depth, excluding output storage
 */
public class StringPermutations {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("str = \"abc\": " + permute("abc"));
        // Expected: [abc, acb, bac, bca, cba, cab]

        // Test 2: two characters
        System.out.println("str = \"ab\": " + permute("ab"));
        // Expected: [ab, ba]

        // Test 3: single character
        System.out.println("str = \"a\": " + permute("a"));
        // Expected: [a]
    }

    public static List<String> permute(String str) {
        List<String> result = new ArrayList<>();
        getPermutations(str.toCharArray(), 0, result);                     // convert once
        return result;
    }

    private static void getPermutations(char[] chars, int idx, List<String> result) {
        if (idx == chars.length) {
            result.add(new String(chars));                                 // O(n) construction
            return;
        }
        for (int i = idx; i < chars.length; i++) {
            swap(chars, idx, i);                                           // fix chars[i] at position idx
            getPermutations(chars, idx + 1, result);
            swap(chars, idx, i);                                           // backtrack
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}