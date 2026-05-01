package dp;

/**
 * LeetCode 1770 - Maximum Score from Performing Multiplication Operations
 *
 * <p>Given an array nums of n integers and an array multipliers of m integers,
 * perform exactly m operations. In each operation, pick either the leftmost or
 * rightmost element from nums, multiply it with the current multiplier, add the
 * result to your score, and remove the element from nums.
 * Return the maximum score after all m operations.
 *
 * <p>Key Insight — State Reduction:
 * <pre>
 *   At operation mul, if left elements taken = left, then:
 *   right elements taken = mul - left
 *   right pointer        = n - 1 - (mul - left)
 *   → State is fully described by (mul, left) alone — O(m²) unique states
 * </pre>
 *
 * <p>Pattern: 2D DP on interval / two-pointer state
 * <ul>
 *   <li>State      : dp[mul][left] = max score from operation mul onward,
 *                    given left elements taken from the left so far</li>
 *   <li>Transition : dp[mul][left] = max(
 *                      multipliers[mul] * nums[left]  + dp[mul+1][left+1],  // take left
 *                      multipliers[mul] * nums[right] + dp[mul+1][left]     // take right
 *                    )</li>
 *   <li>Base case  : mul == m → 0 (no operations remaining)</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Top-Down DP (Memoization) - recursive with (mul, left) cache</li>
 *   <li>Bottom-Up DP (2D Table)   - iterative tabulation</li>
 *   <li>Bottom-Up DP (1D Optimized) - space-optimized with single row</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class MaxScoreMulOps {

    // -------------------------------------------------------------------------
    // Approach 1: Top-Down DP (Memoization)
    // Time  : O(m²) — m² unique (mul, left) states, O(1) per state
    // Space : O(m²) — memo table + O(m) recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum score using top-down memoized recursion.
     *
     * @param nums        input array of integers
     * @param multipliers array of multipliers applied in order
     * @return maximum score after m operations
     */
    public static int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        Integer[][] dp = new Integer[m][m];
        return helper(0, 0, nums, multipliers, dp);
    }

    /**
     * Recursive helper — returns max score from operation mul onward.
     *
     * @param mul         current operation index
     * @param left        number of elements taken from the left so far
     * @param nums        input array
     * @param multipliers multipliers array
     * @param dp          memoization table indexed by [mul][left]
     * @return max score from operation mul onward
     */
    private static int helper(int mul, int left, int[] nums, int[] multipliers, Integer[][] dp) {
        if (mul == multipliers.length) return 0;
        if (dp[mul][left] != null) return dp[mul][left];
        int right = nums.length - 1 - (mul - left);
        int takeLeft  = multipliers[mul] * nums[left]  + helper(mul + 1, left + 1, nums, multipliers, dp);
        int takeRight = multipliers[mul] * nums[right] + helper(mul + 1, left,     nums, multipliers, dp);
        return dp[mul][left] = Math.max(takeLeft, takeRight);
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-Up DP (2D Table)
    // Time  : O(m²)
    // Space : O(m²)
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum score using a bottom-up 2D DP table.
     *
     * <p>dp[mul][left] = max score achievable from operation mul onward,
     * given that left elements have been taken from the left.
     * Filled in reverse — from mul = m down to mul = 0.
     * Base case: dp[m][left] = 0 for all left (guaranteed by Java zero-init).
     *
     * @param nums        input array of integers
     * @param multipliers array of multipliers applied in order
     * @return maximum score after m operations
     */
    public static int maximumScore2(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1];

        // Fill from last operation back to first
        for (int mul = m - 1; mul >= 0; mul--) {
            for (int left = mul; left >= 0; left--) {
                int right = n - 1 - (mul - left);
                int takeLeft  = multipliers[mul] * nums[left]  + dp[mul + 1][left + 1];
                int takeRight = multipliers[mul] * nums[right] + dp[mul + 1][left];
                dp[mul][left] = Math.max(takeLeft, takeRight);
            }
        }
        return dp[0][0];
    }

    // -------------------------------------------------------------------------
    // Approach 3: Bottom-Up DP (Space-Optimized — two alternating rows)
    // Time  : O(m²)
    // Space : O(m) — two rows of size m+1 instead of full m x m table
    // -------------------------------------------------------------------------

    /**
     * Returns the maximum score using two alternating 1D rows instead of the
     * full 2D table.
     *
     * <p>dp[mul][left] reads from dp[mul+1][left] and dp[mul+1][left+1].
     * A single array cannot be safely overwritten in place because dp[left+1]
     * (next row) would be corrupted before it is read for takeLeft. Two
     * alternating rows (curr and next) correctly preserve the previous row
     * during each pass — analogous to the LCS two-row optimization.
     *
     * @param nums        input array of integers
     * @param multipliers array of multipliers applied in order
     * @return maximum score after m operations
     */
    public static int maximumScore3(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[] next = new int[m + 1]; // represents dp[mul+1]
        int[] curr = new int[m + 1]; // represents dp[mul]

        for (int mul = m - 1; mul >= 0; mul--) {
            for (int left = 0; left <= mul; left++) {
                int right     = n - 1 - (mul - left);
                int takeLeft  = multipliers[mul] * nums[left]  + next[left + 1];
                int takeRight = multipliers[mul] * nums[right] + next[left];
                curr[left]    = Math.max(takeLeft, takeRight);
            }
            int[] temp = next;
            next = curr;
            curr = temp;
        }
        return next[0];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: nums=[1,2,3], multipliers=[3,2,1]
        // Optimal: take right(3)*3=9, take right(2)*2=4, take right(1)*1=1 → 14
        int[] nums1 = {1, 2, 3};
        int[] multipliers1 = {3, 2, 1};
        System.out.println("Test 1 - Top-Down   : " + maximumScore(nums1, multipliers1));  // 14
        System.out.println("Test 1 - 2D Bottom-Up: " + maximumScore2(nums1, multipliers1)); // 14
        System.out.println("Test 1 - 1D Optimized: " + maximumScore3(nums1, multipliers1)); // 14

        System.out.println();

        // Test Case 2: nums=[-5,-3,-3,-2,7,1], multipliers=[-10,-5,3,4,6]
        // Expected: 102
        int[] nums2 = {-5, -3, -3, -2, 7, 1};
        int[] multipliers2 = {-10, -5, 3, 4, 6};
        System.out.println("Test 2 - Top-Down   : " + maximumScore(nums2, multipliers2));  // 102
        System.out.println("Test 2 - 2D Bottom-Up: " + maximumScore2(nums2, multipliers2)); // 102
        System.out.println("Test 2 - 1D Optimized: " + maximumScore3(nums2, multipliers2)); // 102

        System.out.println();

        // Test Case 3: Single operation — pick max of first and last
        int[] nums3 = {1, 2, 3};
        int[] multipliers3 = {5};
        System.out.println("Test 3 - Top-Down   : " + maximumScore(nums3, multipliers3));  // 15
        System.out.println("Test 3 - 2D Bottom-Up: " + maximumScore2(nums3, multipliers3)); // 15
        System.out.println("Test 3 - 1D Optimized: " + maximumScore3(nums3, multipliers3)); // 15

        System.out.println();

        // Test Case 4: All negative multipliers — picking smallest nums maximizes score
        int[] nums4 = {-1, -2, -3};
        int[] multipliers4 = {-1, -2, -3};
        System.out.println("Test 4 - Top-Down   : " + maximumScore(nums4, multipliers4));  // 14
        System.out.println("Test 4 - 2D Bottom-Up: " + maximumScore2(nums4, multipliers4)); // 14
        System.out.println("Test 4 - 1D Optimized: " + maximumScore3(nums4, multipliers4)); // 14
    }
}