package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * LC 20 — Valid Parentheses
 *
 * Problem: Given a string containing '(', ')', '{', '}', '[', ']',
 * determine if the input string is valid. A string is valid if every
 * open bracket is closed by the same type in the correct order.
 *
 * Approach: Preload a map of opener → closer. For every opener push
 * its expected closer. For every closer validate against stack top.
 *
 * Time Complexity : O(n) — single pass
 * Space Complexity: O(n) — stack holds at most n/2 entries
 */
public class ValidParenthesisMap {

    private static final Map<Character, Character> map = Map.of(
            '(', ')',
            '{', '}',
            '[', ']'
    );

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()){
            if (map.containsKey(c)){
                stack.push(map.get(c));
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        // Test 1: Mixed valid
        System.out.println("=== Test 1: Mixed valid ===");
        System.out.println(isValid("()[]{}"));  // true

        // Test 2: Interleaved — invalid
        System.out.println("\n=== Test 2: Interleaved ===");
        System.out.println(isValid("([)]"));    // false

        // Test 3: Nested — valid
        System.out.println("\n=== Test 3: Nested ===");
        System.out.println(isValid("{[()]}"));  // true

        // Test 4: Unclosed bracket — invalid
        System.out.println("\n=== Test 4: Unclosed ===");
        System.out.println(isValid("([]"));     // false

        // Test 5: Closing without opening — invalid
        System.out.println("\n=== Test 5: Closing without opening ===");
        System.out.println(isValid("]"));       // false

        // Test 6: Empty string — valid
        System.out.println("\n=== Test 6: Empty string ===");
        System.out.println(isValid(""));        // true

        // Test 7: Only opening brackets — invalid
        System.out.println("\n=== Test 7: Only opening ===");
        System.out.println(isValid("((("));     // false
    }
}