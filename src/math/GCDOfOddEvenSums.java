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
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Time Complexity: O(1) — The result is calculated in constant time.</li>
 *   <li>Space Complexity: O(1) — No auxiliary space is used.</li>
 * </ul>
 *
 * @author mhnuk2007
 */
public class GCDOfOddEvenSums {

    /**
     * Calculates the GCD of the sum of the first n odd numbers and the sum of the first n even numbers.
     *
     * @param n a positive integer
     * @return the greatest common divisor of sumOdd and sumEven, which simplifies to n
     */
    public int gcdOfOddEvenSums(int n) {
        return n;
    }

    /**
     * Interactive test runner for verifying the solution against the mathematical sums.
     */
    public static void main(String[] args) {
        GCDOfOddEvenSums solver = new GCDOfOddEvenSums();

        // Let's test for various n values and verify the mathematical property
        int[] testCases = {1, 2, 3, 5, 10, 100};

        for (int n : testCases) {
            long sumOdd = 0;
            for (int i = 1; i <= n; i++) {
                sumOdd += (2L * i - 1);
            }

            long sumEven = 0;
            for (int i = 1; i <= n; i++) {
                sumEven += (2L * i);
            }

            long calculatedGcd = gcd(sumOdd, sumEven);
            int solverResult = solver.gcdOfOddEvenSums(n);

            System.out.printf("n = %d: sumOdd = %d, sumEven = %d, GCD(sumOdd, sumEven) = %d, Solver Result = %d (Pass: %b)%n",
                    n, sumOdd, sumEven, calculatedGcd, solverResult, calculatedGcd == solverResult);
        }
    }

    /**
     * Euclidean algorithm helper to find GCD of two long values.
     */
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
