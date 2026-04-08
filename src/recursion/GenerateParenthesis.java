package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));

    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        backtrack(result, sb, 0, 0, n);

        return result;
    }

    static void backtrack(List<String> result, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            result.add(sb.toString());
            return;
        }

        if (open < n){
            sb.append("(");
            backtrack(result, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
                }
        if (close < open){
            sb.append(")");
            backtrack(result, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}