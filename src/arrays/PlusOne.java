package arrays;

import java.util.Arrays;

/**
 * LeetCode 66 - Plus One
 *
 * Problem:
 *   Given a large integer represented as a digit array (most significant first),
 *   increment the integer by one and return the resulting digit array.
 *   No leading zeros in the input.
 *
 * Approach: Simulate carry propagation right to left
 *   Initialize carry = 1 (the +1 we want to add).
 *   Iterate right to left:
 *     sum = digits[i] + carry
 *     digits[i] = sum % 10
 *     carry     = sum / 10
 *   If carry remains after the loop → all digits were 9 → prepend 1 to a new array.
 *
 * Example 1: [1,2,3] → [1,2,4]  (no carry)
 * Example 2: [4,9,9] → [5,0,0]  (carry propagates left, stops at 4)
 * Example 3: [9,9,9] → [1,0,0,0] (carry survives → new array of size n+1)
 *
 * Time  : O(n)
 * Space : O(1) in-place, O(n) only when all digits are 9
 */
public class PlusOne {

    public static void main(String[] args) {
        PlusOne solution = new PlusOne();

        // Test 1: no carry — simple increment
        System.out.println(Arrays.toString(solution.plusOne(new int[]{1, 2, 3})));  // Expected: [1, 2, 4]

        // Test 2: carry propagates partway
        System.out.println(Arrays.toString(solution.plusOne(new int[]{4, 9, 9})));  // Expected: [5, 0, 0]

        // Test 3: all 9s — requires new array
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9, 9, 9})));  // Expected: [1, 0, 0, 0]

        // Test 4: single digit 9
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9})));         // Expected: [1, 0]

        // Test 5: single digit, no carry
        System.out.println(Arrays.toString(solution.plusOne(new int[]{4, 3, 2, 1}))); // Expected: [4, 3, 2, 2]
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1;                              // the +1 we are adding

        for (int i = n - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) return digits;          // early exit — no more propagation needed
        }

        // carry still 1 → all digits were 9 → need one extra digit at front
        int[] result = new int[n + 1];
        result[0] = 1;                              // remaining carry — rest are already 0
        return result;
    }
}