package math;

/**
 * LeetCode 788 - Rotated Digits
 *
 * <p>Given an integer n, return how many numbers in the range [1, n] are good.
 * A number is good if after rotating each digit individually by 180 degrees,
 * the resulting number is valid and different from the original.
 *
 * <p>Digit rotation rules:
 * <ul>
 *   <li>0 -> 0, 1 -> 1, 8 -> 8 (valid, unchanged)</li>
 *   <li>2 -> 5, 5 -> 2, 6 -> 9, 9 -> 6 (valid, changed)</li>
 *   <li>3 -> invalid, 4 -> invalid, 7 -> invalid</li>
 * </ul>
 *
 * <p>A number is "good" if:
 * <ol>
 *   <li>No digit belongs to {3, 4, 7} -- would make rotation invalid</li>
 *   <li>At least one digit belongs to {2, 5, 6, 9} -- result must differ from original</li>
 * </ol>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Brute Force with per-digit method calls</li>
 *   <li>Optimized with precomputed digit state lookup array</li>
 * </ol>
 *
 * <p>Note: Digit DP can solve this in O(log n) for very large n,
 * but brute force is sufficient for LeetCode constraints (n <= 10000).
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class RotatedDigits {

    // -------------------------------------------------------------------------
    // Approach 1: Brute Force with Per-Digit Method Calls
    // Time  : O(n * d) where d = digits in n ~ O(n log n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the count of good numbers in the range [1, n].
     *
     * @param n upper bound (inclusive)
     * @return count of good numbers
     */
    public static int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) count++;
        }
        return count;
    }

    /**
     * Returns true if {@code num} is a good number.
     *
     * <p>A number is good if all digits are valid under 180-degree rotation
     * AND at least one digit changes value (result differs from original).
     *
     * @param num the number to check
     * @return true if good, false otherwise
     */
    private static boolean isGood(int num) {
        boolean changed = false;
        while (num > 0) {
            int digit = num % 10;
            if (!isValid(digit)) return false;         // invalid digit -- early exit
            if (isRotating(digit)) changed = true;     // at least one digit changes
            num /= 10;
        }
        return changed;                                // must differ from original
    }

    /**
     * Returns true if {@code digit} produces a valid result under 180-degree rotation.
     * Invalid digits: 3, 4, 7.
     *
     * @param digit single digit 0-9
     * @return true if valid
     */
    static boolean isValid(int digit) {
        return digit != 3 && digit != 4 && digit != 7;
    }

    /**
     * Returns true if {@code digit} changes value under 180-degree rotation.
     * Changing digits: 2 -> 5, 5 -> 2, 6 -> 9, 9 -> 6.
     *
     * @param digit single digit 0-9
     * @return true if digit changes after rotation
     */
    static boolean isRotating(int digit) {
        return digit == 2 || digit == 5 || digit == 6 || digit == 9;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Optimized with Precomputed Digit State Lookup
    // Time  : O(n * d) ~ O(n log n) -- same complexity, lower constant
    // Space : O(1)
    //
    // Replaces two method calls and multiple branch conditions per digit with
    // a single array index lookup. STATE[d] encodes:
    //   0 = invalid          (3, 4, 7)
    //   1 = valid, unchanged (0, 1, 8)
    //   2 = valid, changed   (2, 5, 6, 9)
    // -------------------------------------------------------------------------

    private static final int[] STATE = {1, 1, 2, 0, 0, 2, 2, 0, 1, 2};

    /**
     * Returns the count of good numbers in the range [1, n] using a
     * precomputed digit state lookup array.
     *
     * @param n upper bound (inclusive)
     * @return count of good numbers
     */
    public static int rotatedDigits2(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood2(i)) count++;
        }
        return count;
    }

    /**
     * Returns true if {@code num} is a good number using STATE lookup.
     *
     * @param num the number to check
     * @return true if good, false otherwise
     */
    private static boolean isGood2(int num) {
        boolean changed = false;
        while (num > 0) {
            int state = STATE[num % 10];
            if (state == 0) return false;              // invalid digit -- early exit
            if (state == 2) changed = true;            // digit changes after rotation
            num /= 10;
        }
        return changed;                                // must differ from original
    }

    // -------------------------------------------------------------------------
    // Main -- Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: n=10 -> good: 2,5,6,9,10 -> 5
        System.out.println("Test 1 (n=10)    A1: " + rotatedDigits(10)    + "  A2: " + rotatedDigits2(10));    // 5

        // Test Case 2: n=1 -> no good numbers (1 is unchanged)
        System.out.println("Test 2 (n=1)     A1: " + rotatedDigits(1)     + "  A2: " + rotatedDigits2(1));     // 0

        // Test Case 3: n=2 -> good: 2 -> 1
        System.out.println("Test 3 (n=2)     A1: " + rotatedDigits(2)     + "  A2: " + rotatedDigits2(2));     // 1

        // Test Case 4: n=9 -> good: 2,5,6,9 -> 4
        System.out.println("Test 4 (n=9)     A1: " + rotatedDigits(9)     + "  A2: " + rotatedDigits2(9));     // 4

        // Test Case 5: n=100
        System.out.println("Test 5 (n=100)   A1: " + rotatedDigits(100)   + " A2: " + rotatedDigits2(100));   // 19

        // Test Case 6: max constraint
        System.out.println("Test 6 (n=10000) A1: " + rotatedDigits(10000) + " A2: " + rotatedDigits2(10000)); // 1980
    }
}