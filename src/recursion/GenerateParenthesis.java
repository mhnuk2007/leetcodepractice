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
        backtrack(result, "", n);

        return result;
    }

    private static void backtrack(List<String> result, String current, int n) {
        if (current.length() == n * 2) {
            if (validString(current)) {
                result.add(current);
            }
            return;
        }
        backtrack(result, current + "(", n);
        backtrack(result, current + ")", n);

    }

    private static boolean validString(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') count++;
            else count--;
            if (count < 0) return false;
        }
        return count == 0;

    }
}