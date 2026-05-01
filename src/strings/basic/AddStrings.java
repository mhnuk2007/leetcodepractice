package strings;

/**
 * LeetCode 415 - Add Strings
 *
 * Given two non-negative integers num1 and num2 as strings, return their sum
 * as a string without converting directly to int/long or using BigInteger.
 *
 * Approach 1: for loop — swap so num1 is always longer, j guarded
 *   - Same pattern as addBinary1
 *   - carry % 10 extracts current digit, carry / 10 produces next carry
 *
 * Approach 2: while loop — independent pointers, carry in loop condition
 *   - Same pattern as addBinary2
 *   - Cleaner: no swap needed, handles all cases in one clause
 *
 * Time Complexity:  O(max(m, n)) — single pass, both approaches
 * Space Complexity: O(max(m, n)) — result StringBuilder
 */
public class AddStrings {

    // Approach 1: for loop with swap guarantee
    public static String addStrings1(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        if (m < n) return addStrings1(num2, num1);  // ensure num1 is always longer

        StringBuilder res = new StringBuilder();
        int carry = 0;

        for (int i = m - 1, j = n - 1; i >= 0; i--, j--){
            carry += num1.charAt(i) - '0';
            if (j >= 0)  carry += num2.charAt(j) - '0';
            res.append(carry % 10);
            carry /= 10;
        }
        if (carry != 0) res.append(carry);

        return res.reverse().toString();
    }

    // Approach 2: while loop, no swap needed
    public static String addStrings2(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0){
            if (i >= 0) carry += num1.charAt(i--) - '0';
            if (j >= 0) carry += num2.charAt(j--) - '0';
            res.append(carry % 10);
            carry /= 10;
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        // Test case 1: basic addition
        System.out.println(addStrings1("11", "9"));       // 20
        System.out.println(addStrings2("11", "9"));       // 20

        // Test case 2: equal length with carry chain
        System.out.println(addStrings1("456", "777"));    // 1233
        System.out.println(addStrings2("456", "777"));    // 1233

        // Test case 3: both zero
        System.out.println(addStrings1("0", "0"));        // 0
        System.out.println(addStrings2("0", "0"));        // 0

        // Test case 4: asymmetric lengths
        System.out.println(addStrings1("1", "999"));      // 1000
        System.out.println(addStrings2("1", "999"));      // 1000

        // Test case 5: all nines — max carry chain
        System.out.println(addStrings1("9999", "9999"));  // 19998
        System.out.println(addStrings2("9999", "9999"));  // 19998

        // Test case 6: equal length final carry
        System.out.println(addStrings1("1", "9"));        // 10
        System.out.println(addStrings2("1", "9"));        // 10
    }
}