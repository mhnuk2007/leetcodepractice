package dp;

/**
 * LeetCode 3130: Find All Possible Stable Binary Arrays II
 *
 * A binary array is stable if:
 * - It has exactly 'zero' 0s and 'one' 1s
 * - No subarray of length > limit has all the same digits
 *
 * Optimized DP (O(zero * one)) using prefix sums:
 * dp0[i][j] = number of valid arrays with i zeros and j ones, ending with 0
 * dp1[i][j] = number of valid arrays with i zeros and j ones, ending with 1
 *
 * Transitions:
 * dp0[i][j] = sum(dp1[i-k][j]) for k = 1..min(limit, i)
 * dp1[i][j] = sum(dp0[i][j-k]) for k = 1..min(limit, j)
 *
 * Use prefix sums to compute each transition in O(1).
 */
public class FindAllPossibleStableArraysII {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        FindAllPossibleStableArraysII solution = new FindAllPossibleStableArraysII();

        // Test Case 1: Basic case
        System.out.println("Test Case 1: " + solution.numberOfStableArrays(1, 1, 2));
        // Expected: 2 ([0,1] and [1,0])

        // Test Case 2: Tight limit
        System.out.println("Test Case 2: " + solution.numberOfStableArrays(1, 2, 1));
        // Expected: 1 ([1,0,1])

        // Test Case 3: Symmetric counts
        System.out.println("Test Case 3: " + solution.numberOfStableArrays(3, 3, 2));
        // Expected: 14

        // Test Case 4: All zeros (valid only if within limit)
        System.out.println("Test Case 4: " + solution.numberOfStableArrays(4, 0, 3));
        // Expected: 0 (run length exceeds limit)

        // Test Case 5: Large limit (all permutations valid)
        System.out.println("Test Case 5: " + solution.numberOfStableArrays(2, 2, 5));
        // Expected: 6
    }

    /**
     * Counts stable binary arrays modulo 1e9+7.
     *
     * @param zero  Number of zeros required
     * @param one   Number of ones required
     * @param limit Maximum consecutive same digits allowed
     * @return Number of stable arrays modulo 1e9+7
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        if (zero == 0 && one == 0) {
            return 1;
        }

        int[][] dp0 = new int[zero + 1][one + 1];
        int[][] dp1 = new int[zero + 1][one + 1];
        int[][] pref0 = new int[zero + 1][one + 1];
        int[][] pref1 = new int[zero + 1][one + 1];

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                if (i == 0 && j == 0) {
                    // leave as 0
                } else {
                    if (i > 0) {
                        if (j == 0) {
                            dp0[i][0] = (i <= limit) ? 1 : 0;
                        } else {
                            long total = pref1[i - 1][j];
                            int idx = i - 1 - limit;
                            if (idx >= 0) {
                                total -= pref1[idx][j];
                            }
                            dp0[i][j] = mod(total);
                        }
                    }

                    if (j > 0) {
                        if (i == 0) {
                            dp1[0][j] = (j <= limit) ? 1 : 0;
                        } else {
                            long total = pref0[i][j - 1];
                            int idx = j - 1 - limit;
                            if (idx >= 0) {
                                total -= pref0[i][idx];
                            }
                            dp1[i][j] = mod(total);
                        }
                    }
                }

                pref0[i][j] = (dp0[i][j] + (j > 0 ? pref0[i][j - 1] : 0)) % MOD;
                pref1[i][j] = (dp1[i][j] + (i > 0 ? pref1[i - 1][j] : 0)) % MOD;
            }
        }

        long result = (dp0[zero][one] + (long) dp1[zero][one]) % MOD;
        return (int) result;
    }

    private int mod(long value) {
        value %= MOD;
        if (value < 0) {
            value += MOD;
        }
        return (int) value;
    }
}
