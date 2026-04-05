package recursion;

/**
 * LeetCode 50 - Pow(x, n)
 *
 * Problem:
 *   Implement pow(x, n) which calculates x raised to the power n.
 *
 * Approach 1: Iterative fast power (binary exponentiation)
 *   If current exponent is odd, multiply result by x.
 *   Square x and halve the exponent each iteration.
 *
 * Approach 2: Recursive fast power
 *   half = pow(x, n/2)
 *   If n is even → half * half
 *   If n is odd  → half * half * x
 *   Handle negative n by computing pow(x, |n|) then taking reciprocal.
 *
 * Example:
 *   x=2, n=10  → 1024.0
 *   x=2, n=-2  → 0.25
 *   x=2, n=0   → 1.0
 *
 * Time  : O(log n) — halve exponent each step
 * Space : O(1) — iterative; O(log n) — recursive call stack
 */
public class PowXN {

    public static void main(String[] args) {
        // Test 1: positive exponent
        System.out.println("myPow(2, 10):      " + myPow(2, 10));
        // Expected: 1024.0

        // Test 2: negative exponent
        System.out.println("myPow(2, -2):      " + myPow(2, -2));
        // Expected: 0.25

        // Test 3: zero exponent
        System.out.println("myPow(2, 0):       " + myPow(2, 0));
        // Expected: 1.0

        // Test 4: recursive positive
        System.out.println("myPowRec(2, 10):   " + myPowRec(2, 10));
        // Expected: 1024.0

        // Test 5: recursive negative
        System.out.println("myPowRec(2, -2):   " + myPowRec(2, -2));
        // Expected: 0.25

        // Test 6: MIN_VALUE edge case
        System.out.println("myPow(2, MIN):     " + myPow(2, Integer.MIN_VALUE));
        System.out.println("myPowRec(2, MIN):  " + myPowRec(2, Integer.MIN_VALUE));
    }

    public static double myPow(double x, int n) {
        if (x == 1 || n == 0) return 1;
        if (n == 1) return x;
        double result = 1.0;
        long m = Math.abs((long) n);
        while (m > 0) {
            if (m % 2 == 1) result *= x;                                  // odd — multiply current x
            x *= x;                                                        // square the base
            m /= 2;                                                        // halve the exponent
        }
        return n > 0 ? result : 1 / result;                               // handle negative n
    }

    public static double myPowRec(double x, int n) {
        double result = helper(x, Math.abs((long) n));                    // safe abs with long
        return n > 0 ? result : 1 / result;                               // handle negative n
    }

    private static double helper(double x, long n) {
        if (x == 1 || n == 0) return 1;
        if (n == 1) return x;                                              // base case
        double half = helper(x, n / 2);                                   // recurse on half
        return (n % 2 == 0) ? half * half : half * half * x;             // even or odd
    }
}