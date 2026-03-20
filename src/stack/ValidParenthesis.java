package stack;

import java.util.Stack;

public class ValidParenthesis {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }

        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        String s1 = "{()[]{}]";
        String s2 = "([)]";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));


    }

}
