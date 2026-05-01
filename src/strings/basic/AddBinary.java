package strings;

import java.math.BigInteger;

/**
 * LeetCode 67 - Add Binary
 *
 * Approach 1: for loop, swap to ensure a is longer
 * Approach 2: while loop, independent pointers, carry in loop condition
 * Approach 3: XOR + AND + shift via BigInteger — simulates hardware adder
 *   - x ^ y  → sum without carry
 *   - x & y  → carry positions
 *   - << 1   → shift carry into next bit position
 *   - repeat until carry (y) is zero
 *
 * Time Complexity:  O(max(m, n)) — all approaches
 * Space Complexity: O(max(m, n)) — all approaches
 */
public class AddBinary {

    // Approach 1: for loop with swap guarantee
    public static String addBinary1(String a, String b) {
        int m = a.length(), n = b.length();
        if (m < n) return addBinary1(b, a);

        StringBuilder res = new StringBuilder();
        int carry = 0;

        for (int i = m - 1, j = n - 1; i >= 0; i--, j--) {
            carry += a.charAt(i) - '0';
            if (j >= 0) carry += b.charAt(j) - '0';
            res.append(carry % 2);
            carry /= 2;
        }
        if (carry > 0) res.append(carry);
        return res.reverse().toString();
    }

    // Approach 2: while loop, no swap needed
    public static String addBinary2(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            if (i >= 0) carry += a.charAt(i--) - '0';
            if (j >= 0) carry += b.charAt(j--) - '0';
            res.append(carry & 1);
            carry >>= 1;
        }
        return res.reverse().toString();
    }

    // Approach 3: XOR + AND + shift via BigInteger
    public static String addBinary3(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = BigInteger.ZERO;

        while (!y.equals(zero)) {
            BigInteger carry = x.and(y).shiftLeft(1);  // carry positions, shifted
            x = x.xor(y);                              // sum without carry
            y = carry;                                  // carry becomes next addend
        }
        return x.toString(2);
    }

    public static void main(String[] args) {
        // Test case 1: basic carry propagation
        System.out.println(addBinary1("11", "1"));       // 100
        System.out.println(addBinary2("11", "1"));       // 100
        System.out.println(addBinary3("11", "1"));       // 100

        // Test case 2: equal length with multiple carries
        System.out.println(addBinary1("1010", "1011"));  // 10101
        System.out.println(addBinary2("1010", "1011"));  // 10101
        System.out.println(addBinary3("1010", "1011"));  // 10101

        // Test case 3: both zero
        System.out.println(addBinary1("0", "0"));        // 0
        System.out.println(addBinary2("0", "0"));        // 0
        System.out.println(addBinary3("0", "0"));        // 0

        // Test case 4: asymmetric lengths
        System.out.println(addBinary1("1", "111"));      // 1000
        System.out.println(addBinary2("1", "111"));      // 1000
        System.out.println(addBinary3("1", "111"));      // 1000

        // Test case 5: all ones — max carry chain
        System.out.println(addBinary1("1111", "1111"));  // 11110
        System.out.println(addBinary2("1111", "1111"));  // 11110
        System.out.println(addBinary3("1111", "1111"));  // 11110

        // Test case 6: equal length final carry
        System.out.println(addBinary1("1", "1"));        // 10
        System.out.println(addBinary2("1", "1"));        // 10
        System.out.println(addBinary3("1", "1"));        // 10
    }
}