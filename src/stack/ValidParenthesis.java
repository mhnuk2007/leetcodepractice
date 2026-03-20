package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 20 — Valid Parentheses
 *
 * Problem: Given a string containing '(', ')', '{', '}', '[', ']',
 * determine if the input string is valid. A string is valid if every
 * open bracket is closed by the same type in the correct order.
 *
 * Approach: For every opening bracket push the expected closing bracket.
 * For every closing bracket check if it matches the top of the stack.
 * At the end the stack must be empty.
 *
 * Time Complexity : O(n) — single pass through the string
 * Space Complexity: O(n) — stack holds at most n/2 characters
 */
public class ValidParenthesis {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        // Test 1: All bracket types — valid
        System.out.println("=== Test 1: Mixed valid ===");
        System.out.println(isValid("()[]{}"));   // true

        // Test 2: Interleaved — invalid
        System.out.println("\n=== Test 2: Interleaved ===");
        System.out.println(isValid("([)]"));     // false

        // Test 3: Single pair — valid
        System.out.println("\n=== Test 3: Single pair ===");
        System.out.println(isValid("()"));       // true

        // Test 4: Nested — valid
        System.out.println("\n=== Test 4: Nested ===");
        System.out.println(isValid("{[()]}"));   // true

        // Test 5: Unclosed bracket — invalid
        System.out.println("\n=== Test 5: Unclosed ===");
        System.out.println(isValid("([]"));      // false

        // Test 6: Closing without opening — invalid
        System.out.println("\n=== Test 6: Closing without opening ===");
        System.out.println(isValid("]"));        // false

        // Test 7: Empty string — valid
        System.out.println("\n=== Test 7: Empty string ===");
        System.out.println(isValid(""));         // true

        // Test 8: Only opening brackets — invalid
        System.out.println("\n=== Test 8: Only opening ===");
        System.out.println(isValid("((("));      // false
    }
}