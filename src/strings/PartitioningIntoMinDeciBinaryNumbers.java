package strings;

/**
 * LeetCode 1689: Partitioning Into Minimum Number Of Deci-Binary Numbers
 * <p>
 * A deci-binary number is a decimal number that consists only of digits 0 and 1, with no leading zeros.
 * For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
 * <p>
 * Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers
 * that you need to sum up to n.
 * <p>
 * Example 1:
 * Input: n = "32"
 * Output: 3
 * Explanation: 10 + 11 + 11 = 32
 * <p>
 * Example 2:
 * Input: n = "82734"
 * Output: 8
 * <p>
 * Example 3:
 * Input: n = "27346209830709182346"
 * Output: 9
 */
public class PartitioningIntoMinDeciBinaryNumbers {

    /**
     * Finds the minimum number of deci-binary numbers needed to sum up to n.
     * <p>
     * The key insight for this problem is that to form a digit 'd' at any position,
     * we need to sum up 'd' deci-binary numbers that have a '1' at that same position.
     * For example, to get the '8' in "82734", we need at least eight deci-binary numbers
     * with a '1' in that position (e.g., 10000, 10000, ... eight times).
     * <p>
     * Therefore, the minimum number of deci-binary numbers required for the entire string 'n'
     * is determined by the largest digit present in 'n'. The maximum digit dictates the
     * minimum number of '1's we need to stack up in any single position.
     *
     * @param n A string representing a positive decimal integer.
     * @return The minimum number of deci-binary numbers required, which is simply the largest digit in the string.
     */
    public int minPartitions(String n) {
        int maxDigit = 0;
        for (char c : n.toCharArray()) {
            int digit = c - '0';
            if (digit > maxDigit) {
                maxDigit = digit;
            }
            // Optimization: If we find a '9', we can't do better, so we can exit early.
            if (maxDigit == 9) {
                return 9;
            }
        }
        return maxDigit;
    }

    public static void main(String[] args) {
        PartitioningIntoMinDeciBinaryNumbers solution = new PartitioningIntoMinDeciBinaryNumbers();

        // Test Case 1
        String n1 = "32";
        System.out.println("Test Case 1 (Input: \"32\"): " + solution.minPartitions(n1)); // Expected: 3

        // Test Case 2
        String n2 = "82734";
        System.out.println("Test Case 2 (Input: \"82734\"): " + solution.minPartitions(n2)); // Expected: 8

        // Test Case 3
        String n3 = "27346209830709182346";
        System.out.println("Test Case 3 (Input: \"27346209830709182346\"): " + solution.minPartitions(n3)); // Expected: 9
    }
}
