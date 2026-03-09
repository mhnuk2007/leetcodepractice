package dp;

/**
 * LeetCode 3129: Find All Possible Stable Binary Arrays I
 *
 * A binary array is stable if:
 * - Has exactly 'zero' 0s and 'one' 1s
 * - No subarray of length > limit has all same digits
 *
 * Approach: Top-Down DP with Memoization
 * - Try placing consecutive 0s or 1s (up to limit)
 * - Alternate between 0s and 1s to avoid exceeding limit
 * - Cache results to avoid recomputation
 *
 * Time Complexity: O(zero * one * limit)
 * Space Complexity: O(zero * one)
 */
public class FindAllPossibleStableArraysI {

    private static final int MOD = 1_000_000_007;
    private Long[][][] memo;

    public static void main(String[] args) {
        FindAllPossibleStableArraysI solution = new FindAllPossibleStableArraysI();

        // Test Case 1: Basic case
        System.out.println("Test Case 1: " + solution.numberOfStableArrays(1, 1, 2));
        // Expected: 2 ([0,1] and [1,0])

        // Test Case 2: Tight limit
        System.out.println("Test Case 2: " + solution.numberOfStableArrays(1, 2, 1));
        // Expected: 1 ([1,0,1])

        // Test Case 3: Multiple possibilities
        System.out.println("Test Case 3: " + solution.numberOfStableArrays(3, 3, 2));
        // Expected: 14

        // Test Case 4: Asymmetric case
        System.out.println("Test Case 4: " + solution.numberOfStableArrays(2, 3, 1));
        // Expected: 3 ([0,1,0,1,1], [1,0,1,0,1], [1,1,0,1,0])

        // Test Case 5: Edge case - all same digit
        System.out.println("Test Case 5: " + solution.numberOfStableArrays(5, 0, 5));
        // Expected: 1 ([0,0,0,0,0])

        // Test Case 6: Larger case
        System.out.println("Test Case 6: " + solution.numberOfStableArrays(10, 10, 3));
        // Expected: large number (memoization essential)

        // Test Case 7: Limit equals total
        System.out.println("Test Case 7: " + solution.numberOfStableArrays(2, 2, 4));
        // Expected: 6 (all permutations valid)
    }

    /**
     * Main entry point
     * Initialize memoization and try starting with either 0s or 1s
     *
     * @param zero  Total number of 0s needed
     * @param one   Total number of 1s needed
     * @param limit Maximum consecutive same digits allowed
     * @return Number of valid stable binary arrays modulo 10^9 + 7
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        // memo[zerosLeft][onesLeft][placeZeros]
        // placeZeros: 1 if next we place zeros, 0 if next we place ones
        memo = new Long[zero + 1][one + 1][2];

        // Try starting by placing ones first (then zeros)
        long startWithOnes = solve(zero, one, false, limit);

        // Try starting by placing zeros first (then ones)
        long startWithZeros = solve(zero, one, true, limit);

        return (int)((startWithOnes + startWithZeros) % MOD);
    }

    /**
     * Recursive solver with memoization
     *
     * @param zerosLeft  Number of 0s remaining to place
     * @param onesLeft   Number of 1s remaining to place
     * @param placeZeros true = place 0s next, false = place 1s next
     * @param limit      Maximum consecutive same digits allowed
     * @return Number of valid stable arrays from this state
     */
    private long solve(int zerosLeft, int onesLeft, boolean placeZeros, int limit) {
        // Base case: successfully placed all digits
        if (zerosLeft == 0 && onesLeft == 0) {
            return 1;
        }

        // Check memoization cache
        int flag = placeZeros ? 1 : 0;
        if (memo[zerosLeft][onesLeft][flag] != null) {
            return memo[zerosLeft][onesLeft][flag];
        }

        long result = 0;

        if (placeZeros) {
            // Place consecutive 0s (try lengths from 1 to limit)
            for (int len = 1; len <= Math.min(zerosLeft, limit); len++) {
                // After placing 0s, next we must place 1s
                result = (result + solve(zerosLeft - len, onesLeft, false, limit)) % MOD;
            }
        } else {
            // Place consecutive 1s (try lengths from 1 to limit)
            for (int len = 1; len <= Math.min(onesLeft, limit); len++) {
                // After placing 1s, next we must place 0s
                result = (result + solve(zerosLeft, onesLeft - len, true, limit)) % MOD;
            }
        }

        // Cache and return result
        return memo[zerosLeft][onesLeft][flag] = result;
    }
}