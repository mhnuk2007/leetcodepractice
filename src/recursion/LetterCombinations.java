package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 17 - Letter Combinations of a Phone Number
 * <p>
 * Problem:
 * Given a string of digits (2-9), return all possible letter combinations
 * that the number could represent, based on a telephone keypad mapping.
 * Return an empty list for empty input.
 * <p>
 * Approach: Backtracking digit by digit
 * For each digit, iterate over its mapped letters. Append each letter to a
 * shared StringBuilder, recurse to the next digit, then backtrack by removing
 * the last character. When sb.length() == digits.length(), a full combination
 * is complete — add its snapshot to result.
 * Phone map stored as a String[] indexed by digit value (digit - '0'),
 * giving O(1) access with no boxing overhead.
 * <p>
 * Example:
 * "23" → ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * ""   → []
 * "2"  → ["a","b","c"]
 * <p>
 * Time  : O(4^n * n) — at most 4 letters per digit, n digits, each combination length n
 * Space : O(n) recursion depth; O(4^n * n) output storage
 */
public class LetterCombinations {

    private static final String[] PHONE = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        // Test 1: standard two-digit case
        System.out.println("\"23\": " + letterCombinations("23"));
        // Expected: [ad, ae, af, bd, be, bf, cd, ce, cf]

        // Test 2: empty input
        System.out.println("\"\"  : " + letterCombinations(""));
        // Expected: []

        // Test 3: single digit
        System.out.println("\"2\" : " + letterCombinations("2"));
        // Expected: [a, b, c]

        // Test 4: digit with four letters
        System.out.println("\"7\" : " + letterCombinations("7"));
        // Expected: [p, q, r, s]

        // Test 5: three digits
        System.out.println("\"223\": " + letterCombinations("223"));
        // Expected: 12 combinations
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) return result;
        backtrack(result, digits, 0, new StringBuilder());
        return result;
    }

    private static void backtrack(List<String> result, String digits,
                                  int idx, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        for (char c : PHONE[digits.charAt(idx) - '0'].toCharArray()) {
            sb.append(c);
            backtrack(result, digits, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);           // backtrack
        }
    }
}