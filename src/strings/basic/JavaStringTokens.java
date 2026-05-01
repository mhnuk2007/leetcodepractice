package strings;

import java.util.Arrays;

/**
 * HackerRank Problem: Java String Tokens
 * <p>
 * Given a string, s, matching the regular expression [A-Za-z !,?._'@]+,
 * split the string into tokens. We define a token to be one or more consecutive English alphabetic letters.
 * Then, print the number of tokens, followed by each token on a new line.
 * <p>
 * Example:
 * s = "He is a very very good boy, isn't he?"
 * The tokens are "He", "is", "a", "very", "very", "good", "boy", "isn", "t", "he".
 * Output:
 * 10
 * He
 * is
 * a
 * very
 * very
 * good
 * boy
 * isn
 * t
 * he
 */
public class JavaStringTokens {

    /**
     * Splits a string into tokens based on non-alphabetic delimiters.
     *
     * @param s The input string.
     */
    public void tokenizeString(String s) {
        // Trim leading/trailing whitespace to handle cases where the string starts/ends with delimiters.
        s = s.trim();

        // If the string is empty after trimming, there are no tokens.
        if (s.isEmpty()) {
            System.out.println(0);
            return;
        }

        // Split the string by one or more consecutive non-alphabetic characters.
        String[] tokens = s.split("[^a-zA-Z]+");

        System.out.println(tokens.length);
        for (String token : tokens) {
            System.out.println(token);
        }
    }

    public static void main(String[] args) {
        JavaStringTokens solution = new JavaStringTokens();

        // Test Case 1
        String s1 = "He is a very very good boy, isn't he?";
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output:");
        solution.tokenizeString(s1);
        System.out.println("--------------------");

        // Test Case 2
        String s2 = "           YES      leading spaces        are valid,    problemsetters are         evillllll";
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output:");
        solution.tokenizeString(s2);
        System.out.println("--------------------");

        // Test Case 3: Only delimiters
        String s3 = ".,, .!";
        System.out.println("--- Test Case 3 ---");
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output:");
        solution.tokenizeString(s3);
        System.out.println("--------------------");
    }
}
