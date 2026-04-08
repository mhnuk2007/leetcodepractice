package recursion;

import java.util.*;

/**
 * LeetCode 22 - Generate Parentheses
 * <p>
 * Problem:
 * Given n pairs of parentheses, generate all combinations of well-formed
 * parentheses strings.
 * <p>
 * Approach 1: Backtracking with open/close counters (pruning during construction)
 * Track how many '(' and ')' have been placed so far.
 * - Add '(' only when open < n       (more opening brackets available)
 * - Add ')' only when close < open   (a matching '(' exists to close)
 * This guarantees every explored path is valid — no post-generation filtering needed.
 * <p>
 * Approach 2: Iterative BFS with open/close state in queue
 * Each queue entry carries a State(string, open, close) state object.
 * Same pruning rules apply — only enqueue valid next states.
 * Eliminates post-generation validation entirely.
 * <p>
 * Example:
 * n = 3 → ["((()))","(()())","(())()","()(())","()()()"]
 * n = 1 → ["()"]
 * n = 2 → ["(())", "()()"]
 * <p>
 * Time  : O(4^n / sqrt(n)) — nth Catalan number of valid strings, each length 2n
 * Space : O(n) recursion depth + O(Catalan(n)) output storage
 */
public class GenerateParenthesis {

    record State(String str, int open, int close) {
    }

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("n=3 recursive: " + generateParenthesis(3));
        System.out.println("n=3 iterative: " + generateParenthesisIter(3));
        // Expected: ["((()))","(()())","(())()","()(())","()()()"]

        // Test 2: base case
        System.out.println("n=1 recursive: " + generateParenthesis(1));
        System.out.println("n=1 iterative: " + generateParenthesisIter(1));
        // Expected: ["()"]

        // Test 3: two pairs
        System.out.println("n=2 recursive: " + generateParenthesis(2));
        System.out.println("n=2 iterative: " + generateParenthesisIter(2));
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
            sb.deleteCharAt(sb.length() - 1);           // backtrack
        }
        if (close < open) {
            sb.append(')');
            backtrack(result, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);           // backtrack
        }
    }

    public static List<String> generateParenthesisIter(int n) {
        List<String> result = new ArrayList<>();
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State("", 0, 0));
        while (!queue.isEmpty()) {
            State p = queue.poll();
            if (p.str.length() == n * 2) {
                result.add(p.str);                      // pruning guarantees validity
                continue;
            }
            if (p.open < n)        queue.add(new State(p.str + "(", p.open + 1, p.close));
            if (p.close < p.open)  queue.add(new State(p.str + ")", p.open, p.close + 1));
        }
        return result;
    }
}