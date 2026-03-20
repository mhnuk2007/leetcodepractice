package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode: 150 — Evaluate Reverse Polish Notation
 *
 * Problem: Evaluate an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand is an integer.
 * Division truncates toward zero.
 *
 * Approach: Use a stack. For every token:
 *   - if number  → push onto stack
 *   - if operator → pop two operands, apply operator, push result
 * Final answer is the only element remaining on the stack.
 *
 * Example:
 * ["2","1","+","3","*"] → (2+1)*3 = 9
 * ["4","13","5","/","+"] → 4+(13/5) = 6
 *
 * Time Complexity : O(n) — single pass through tokens
 * Space Complexity: O(n) — stack holds at most n/2 numbers
 */
public class ReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                }
                case "-" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                }
                default  -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {

        // Test 1: Addition then multiply — (2+1)*3
        System.out.println("=== Test 1: (2+1)*3 ===");
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        // expected: 9

        // Test 2: Division then addition — 4+(13/5)
        System.out.println("\n=== Test 2: 4+(13/5) ===");
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        // expected: 6

        // Test 3: Mixed operators — ((10*(6/((9+3)*-11)))+17)+5
        System.out.println("\n=== Test 3: Complex expression ===");
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        // expected: 22

        // Test 4: Subtraction order — 5-3
        System.out.println("\n=== Test 4: 5-3 ===");
        System.out.println(evalRPN(new String[]{"5", "3", "-"}));
        // expected: 2

        // Test 5: Division truncates toward zero — 7/2
        System.out.println("\n=== Test 5: 7/2 truncated ===");
        System.out.println(evalRPN(new String[]{"7", "2", "/"}));
        // expected: 3

        // Test 6: Negative numbers
        System.out.println("\n=== Test 6: Negative numbers ===");
        System.out.println(evalRPN(new String[]{"3", "-4", "*"}));
        // expected: -12

        // Test 7: Single number
        System.out.println("\n=== Test 7: Single number ===");
        System.out.println(evalRPN(new String[]{"42"}));
        // expected: 42
    }
}