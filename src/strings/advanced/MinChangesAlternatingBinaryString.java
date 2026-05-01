package strings;

/**
 * LeetCode 1758: Minimum Changes To Make Alternating Binary String
 * <p>
 * You are given a string s consisting only of the digits '0' and '1'.
 * In one operation, you can change any '0' to a '1' or vice versa.
 * <p>
 * The string is called alternating if no two adjacent characters are equal.
 * For example, the string "010" is alternating, while the string "011" is not.
 * <p>
 * Return the minimum number of operations needed to make s alternating.
 * <p>
 * Example 1:
 * Input: s = "0100"
 * Output: 1
 * Explanation: If you change the last character to '1', s becomes "0101", which is alternating.
 * <p>
 * Example 2:
 * Input: s = "10"
 * Output: 0
 * Explanation: s is already alternating.
 * <p>
 * Example 3:
 * Input: s = "1111"
 * Output: 2
 * Explanation: You can change s to "1010" or "0101", both require 2 operations.
 */
public class MinChangesAlternatingBinaryString {

    /**
     * Calculates the minimum operations to make a binary string alternating.
     * <p>
     * An alternating binary string can only have two forms:
     * 1. Starts with '0' (e.g., "010101...")
     * 2. Starts with '1' (e.g., "101010...")
     * <p>
     * This method calculates the cost (number of changes) to transform the input string `s`
     * into the pattern that starts with '0'. Let's call this `startWithZeroCost`.
     * <p>
     * The cost to transform `s` into the other pattern (starting with '1') is simply `s.length() - startWithZeroCost`.
     * This is because for every position, if a character does not match the "start with 0" pattern, it must match
     * the "start with 1" pattern.
     * <p>
     * The final answer is the minimum of these two costs.
     *
     * @param s The input binary string.
     * @return The minimum number of operations.
     */
    public int minOperations(String s) {
        int startWithZeroCost = 0;
        for (int i = 0; i < s.length(); i++) {
            // Check against the "0101..." pattern
            if (i % 2 == 0) { // Even indices should be '0'
                if (s.charAt(i) != '0') {
                    startWithZeroCost++;
                }
            } else { // Odd indices should be '1'
                if (s.charAt(i) != '1') {
                    startWithZeroCost++;
                }
            }
        }

        // The cost to make it "1010..." is the total length minus the cost to make it "0101..."
        int startWithOneCost = s.length() - startWithZeroCost;

        return Math.min(startWithZeroCost, startWithOneCost);
    }

    public static void main(String[] args) {
        MinChangesAlternatingBinaryString solution = new MinChangesAlternatingBinaryString();

        // Test Case 1
        String s1 = "0100";
        System.out.println("Test Case 1 (Input: \"0100\"): " + solution.minOperations(s1)); // Expected: 1

        // Test Case 2
        String s2 = "10";
        System.out.println("Test Case 2 (Input: \"10\"): " + solution.minOperations(s2));   // Expected: 0

        // Test Case 3
        String s3 = "1111";
        System.out.println("Test Case 3 (Input: \"1111\"): " + solution.minOperations(s3)); // Expected: 2

        // Test Case 4
        String s4 = "00110";
        System.out.println("Test Case 4 (Input: \"00110\"): " + solution.minOperations(s4)); // Expected: 2
    }
}
