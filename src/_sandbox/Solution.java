package _sandbox;

/**
 * LeetCode 43 — Multiply Strings.
 *
 * <p>Given two non-negative integers {@code num1} and {@code num2}
 * represented as strings, return the product, also as a string, without
 * converting either input to a native integer type (they may be arbitrarily
 * large).
 *
 * <p><b>Approach:</b> grade-school long multiplication, simulated with an
 * array of digit positions. Multiplying digit {@code num1[i]} by digit
 * {@code num2[j]} contributes to result positions {@code i+j} (the carry
 * position) and {@code i+j+1} (the units position for that digit-pair).
 * Since both numbers together are at most {@code len1+len2} digits long,
 * that's the array's fixed size; leading zeros are trimmed when building
 * the final string.
 *
 * <p>Time: O(len1 * len2) — every pair of digits is multiplied once.
 * <br>Space: O(len1 + len2) for the digit-position array.
 */
public class Solution {

    /**
     * Returns the product of {@code num1} and {@code num2} as a decimal
     * string, with no leading zeros (except the single-character result
     * "0" when either input is zero).
     */
    private static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len1 = num1.length();
        int len2 = num2.length();
        int[] digitPositions = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int carryPos = i + j;
                int unitsPos = i + j + 1;

                int sum = product + digitPositions[unitsPos];
                digitPositions[unitsPos] = sum % 10;
                digitPositions[carryPos] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int digit : digitPositions) {
            if (!(result.length() == 0 && digit == 0)) {
                result.append(digit);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        runExample("498828660196", "840477629533", "419254329864656431168468");
        runExample("2", "3", "6");
        runExample("123", "456", "56088");
        runExample("0", "0", "0");
        runExample("1", "0", "0");
        runExample("9", "9", "81");
        runExample("99", "99", "9801");
    }

    private static void runExample(String num1, String num2, String expected) {
        String actual = multiply(num1, num2);
        String status = actual.equals(expected) ? "PASS" : "FAIL";
        System.out.printf("[%s] %s * %s -> %s (expected %s)%n",
                status, num1, num2, actual, expected);
    }
}