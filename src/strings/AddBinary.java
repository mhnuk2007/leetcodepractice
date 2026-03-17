package strings;

/**
 * LeetCode 67 - Add Binary
 *
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Approach 1: Swap so a is always longer, single right-to-left for loop.
 *   - Loop drives on i (longer string), j guarded for shorter string
 *   - carry accumulates both bits and previous carry in one int
 *   - Final carry appended after loop if nonzero
 *
 * Approach 2: Single while loop, no swap needed.
 *   - carry & 1  extracts current output bit  (equivalent to carry % 2)
 *   - carry >>= 1 shifts to next carry        (equivalent to carry / 2)
 *   - Loop condition handles remaining bits AND final carry in one clause
 *
 * Time Complexity:  O(max(m, n)) — single pass, both approaches
 * Space Complexity: O(max(m, n)) — result StringBuilder
 */
public class AddBinary {

    // Approach 1: for loop with swap guarantee
    public static String addBinary1(String a, String b) {
        int m = a.length(), n = b.length();
        if (m < n) return addBinary1(b, a);  // ensure a is always longer

        StringBuilder res = new StringBuilder();
        int carry = 0;

        for (int i = m - 1, j = n - 1; i >= 0; i--, j--) {
            carry += a.charAt(i) - '0';
            if (j >= 0) carry += b.charAt(j) - '0';
            res.append(carry % 2);
            carry /= 2;
        }

        if (carry > 0) res.append(carry);    // flush final carry

        return res.reverse().toString();
    }

    // Approach 2: while loop, no swap needed
    public static String addBinary2(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            if (i >= 0) carry += a.charAt(i--) - '0';
            if (j >= 0) carry += b.charAt(j--) - '0';
            res.append(carry & 1);           // carry % 2
            carry >>= 1;                     // carry / 2
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        // Test case 1: basic carry propagation
        System.out.println(addBinary1("11", "1"));        // 100
        System.out.println(addBinary2("11", "1"));        // 100

        // Test case 2: equal length with multiple carries
        System.out.println(addBinary1("1010", "1011"));   // 10101
        System.out.println(addBinary2("1010", "1011"));   // 10101

        // Test case 3: both zero
        System.out.println(addBinary1("0", "0"));         // 0
        System.out.println(addBinary2("0", "0"));         // 0

        // Test case 4: asymmetric lengths
        System.out.println(addBinary1("1", "111"));       // 1000
        System.out.println(addBinary2("1", "111"));       // 1000

        // Test case 5: all ones — max carry chain
        System.out.println(addBinary1("1111", "1111"));   // 11110
        System.out.println(addBinary2("1111", "1111"));   // 11110

        // Test case 6: equal length final carry
        System.out.println(addBinary1("1", "1"));         // 10
        System.out.println(addBinary2("1", "1"));         // 10
    }
}