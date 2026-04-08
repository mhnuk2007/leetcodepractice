package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));

    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        helper(result, "", n);

        return result;
    }

    static void helper(List<String> result, String s, int n) {
        if (s.length() == n * 2) {
            if (isValid(s))
                result.add(s);
            return;
        }

        helper(result, s + "(", n);
        helper(result, s + ")", n);
    }

    static boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else count--;

            if (count < 0) return false;
        }
        return count == 0;
    }
}