package math;

/**
 * LeetCode 3658 - GCD of Odd and Even Sums
 *
 * <p>Given an integer {@code n}, you need to calculate the greatest common divisor (GCD) of:
 * <ul>
 *   <li>{@code sumOdd}: The sum of the first {@code n} positive odd numbers.</li>
 *   <li>{@code sumEven}: The sum of the first {@code n} positive even numbers.</li>
 * </ul>
 *
 * <p><b>Mathematical Derivation:</b>
 * <ol>
 *   <li>The sum of the first {@code n} positive odd numbers (1, 3, 5, ..., 2n-1) is {@code n^2}.</li>
 *   <li>The sum of the first {@code n} positive even numbers (2, 4, 6, ..., 2n) is {@code n * (n + 1)}.</li>
 *   <li>We need to compute {@code GCD(n^2, n * (n + 1))}.</li>
 *   <li>Using the property {@code GCD(k * a, k * b) = k * GCD(a, b)}, we get:
 *       <pre>GCD(n * n, n * (n + 1)) = n * GCD(n, n + 1)</pre>
 *   </li>
 *   <li>Since {@code n} and {@code n + 1} are consecutive integers, they are always coprime.
 *       Thus, {@code GCD(n, n + 1) = 1}.
 *   </li>
 *   <li>So, {@code GCD(sumOdd, sumEven) = n * 1 = n}.</li>
 * </ol>
 *
 * @author mhnuk2007
 */
public class GCDOfOddEvenSums {

    // -------------------------------------------------------------------------
    // Approach 1: Euclidean Algorithm (Explicit Sums)
    // Time  : O(log(n^2)) = O(log n)
    // Space : O(log n) recursive call stack
    // -------------------------------------------------------------------------

    /**
     * Calculates the GCD of the sum of the first n odd numbers and the sum of the first n even numbers
     * using the explicit formulas and the Euclidean algorithm.
     *
     * @param n a positive integer
     * @return the greatest common divisor
     */
    public int gcdOfOddEvenSumsApproach1(int n) {
        return gcd(n * n, n * (n + 1));
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }

    // -------------------------------------------------------------------------
    // Approach 2: Constant Time Math Observation (Optimal)
    // Time  : O(1)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Calculates the GCD in O(1) time by leveraging the mathematical fact that
     * GCD(n^2, n(n+1)) = n * GCD(n, n+1) = n * 1 = n.
     *
     * @param n a positive integer
     * @return the greatest common divisor, which simplifies directly to n
     */
    public int gcdOfOddEvenSums(int n) {
        return n;
    }

    /**
     * Test runner for verifying both approaches.
     */
    public static void main(String[] args) {
        GCDOfOddEvenSums solver = new GCDOfOddEvenSums();

        int[] testCases = {1, 2, 3, 5, 10, 100};

        System.out.println("Running test cases for both approaches:");
        for (int n : testCases) {
            int result1 = solver.gcdOfOddEvenSumsApproach1(n);
            int result2 = solver.gcdOfOddEvenSums(n);

            boolean match = (result1 == result2) && (result2 == n);
            System.out.printf("n = %d: Approach 1 = %d, Approach 2 = %d (Pass: %b)%n",
                    n, result1, result2, match);
        }
    }
}
