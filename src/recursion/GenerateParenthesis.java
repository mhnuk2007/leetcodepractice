package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22 - Generate Parentheses
 * <p>
 * Problem:
 * Given n pairs of parentheses, generate all combinations of well-formed
 * parentheses strings.
 * <p>
 * Approach: Backtracking with open/close counters (pruning during construction)
 * Track how many '(' and ')' have been placed so far.
 * - Add '(' only when open < n       (more opening brackets available)
 * - Add ')' only when close < open   (a matching '(' exists to close)
 * This guarantees every explored path is valid — no post-generation filtering needed.
 * <p>
 * Example:
 * n = 3 → ["((()))","(()())","(())()","()(())","()()()"]
 * n = 1 → ["()"]
 * <p>
 * Time  : O(4^n / sqrt(n)) — nth Catalan number of valid strings, each length 2n
 * Space : O(n) recursion depth + O(Catalan(n)) output storage
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("n=3: " + generateParenthesis(3));
        // Expected: ["((()))","(()())","(())()","()(())","()()()"]

        // Test 2: base case
        System.out.println("n=1: " + generateParenthesis(1));
        // Expected: ["()"]

        // Test 3: two pairs
        System.out.println("n=2: " + generateParenthesis(2));
        // Expected: ["(())", "()()"]
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder sb,
                                  int open, int close, int n) {
        if (sb.length() == n * 2) {
            result.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtrack(result, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);   // backtrack
        }
        if (close < open) {
            sb.append(')');
            backtrack(result, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);   // backtrack
        }
    }
}