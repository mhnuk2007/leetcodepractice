

/**
 * LeetCode: 263 - Ugly Number
 *
 * <p>An ugly number is a positive integer whose prime factors are limited to
 * {@code 2}, {@code 3}, and {@code 5}. Given an integer {@code n}, return
 * {@code true} if {@code n} is an ugly number.
 *
 * <p><b>Approach:</b> Repeated division.
 * <ul>
 *   <li>Divide out all factors of 2, 3, and 5.</li>
 *   <li>If the remainder is 1, every prime factor was covered — ugly.</li>
 *   <li>If the remainder is anything else, a disqualifying prime factor
 *       remains — not ugly.</li>
 * </ul>
 *
 * <p><b>Trace:</b>
 * <pre>
 * n=12: 12/2=6 → 6/2=3 → 3/3=1 → return true  ✓
 * n=14: 14/2=7 → 7%3≠0, 7%5≠0 → remainder=7 → return false ✓
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Time:  O(log n) — each division reduces n by at least half</li>
 *   <li>Space: O(1)</li>
 * </ul>
 */
public class UglyNumber {

    public static void main(String[] args) {
        // TC1: 1 — by convention, 1 is ugly (no prime factors)
        // Expected: true
        System.out.println("TC1  n=1   → " + isUgly(1));

        // TC2: LC example 1 — 6 = 2 × 3
        // Expected: true
        System.out.println("TC2  n=6   → " + isUgly(6));

        // TC3: LC example 2 — 1 (covered above, restate for LC numbering)
        // 8 = 2 × 2 × 2
        // Expected: true
        System.out.println("TC3  n=8   → " + isUgly(8));

        // TC4: LC example 3 — 14 = 2 × 7, contains prime factor 7
        // Expected: false
        System.out.println("TC4  n=14  → " + isUgly(14));

        // TC5: n=0 — not a positive integer
        // Expected: false
        System.out.println("TC5  n=0   → " + isUgly(0));

        // TC6: negative number — not a positive integer
        // Expected: false
        System.out.println("TC6  n=-6  → " + isUgly(-6));

        // TC7: pure power of 2
        // 16 = 2^4 → Expected: true
        System.out.println("TC7  n=16  → " + isUgly(16));

        // TC8: pure power of 3
        // 27 = 3^3 → Expected: true
        System.out.println("TC8  n=27  → " + isUgly(27));

        // TC9: pure power of 5
        // 125 = 5^3 → Expected: true
        System.out.println("TC9  n=125 → " + isUgly(125));

        // TC10: product of all three factors
        // 30 = 2 × 3 × 5 → Expected: true
        System.out.println("TC10 n=30  → " + isUgly(30));

        // TC11: large prime — 7 cannot be divided by 2, 3, or 5
        // Expected: false
        System.out.println("TC11 n=7   → " + isUgly(7));

        // TC12: 15 = 3 × 5 → Expected: true
        System.out.println("TC12 n=15  → " + isUgly(15));

        // TC13: large ugly number — 2^a × 3^b × 5^c
        // 360 = 2^3 × 3^2 × 5 → Expected: true
        System.out.println("TC13 n=360 → " + isUgly(360));

        // TC14: large non-ugly number — contains prime factor 11
        // 2310 = 2 × 3 × 5 × 7 × 11 → Expected: false
        System.out.println("TC14 n=2310 → " + isUgly(2310));
    }

    /**
     * Returns {@code true} if {@code n} is an ugly number.
     * Divides out all factors of 2, 3, and 5; checks if remainder is 1.
     *
     * @param n integer to test
     * @return {@code true} if n is a positive integer whose only prime
     *         factors are 2, 3, and 5; {@code false} otherwise
     */
    private static boolean isUgly(int n) {
        if (n <= 0) return false;
        for (int factor : new int[]{2, 3, 5}) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}