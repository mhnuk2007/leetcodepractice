package dp.optimization;

/**
 * LeetCode 96 - Unique Binary Search Trees
 *
 * <p>Given an integer n, return the number of structurally unique BSTs which
 * have exactly n nodes of unique values from 1 to n.
 *
 * <p>Key Insight — Catalan Number:
 * <pre>
 *   C(0) = 1, C(1) = 1
 *   C(n) = sum of C(i-1) * C(n-i) for i = 1..n
 *
 *   When i is the root:
 *     left subtree  has i-1 nodes → C(i-1) unique BSTs
 *     right subtree has n-i nodes → C(n-i) unique BSTs
 *     total trees with root i     → C(i-1) * C(n-i)
 * </pre>
 *
 * <p>The answer is the nth Catalan number:
 * <pre>
 *   C(n) = C(2n, n) / (n + 1)
 * </pre>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Plain Recursion         — no memoization, exponential time</li>
 *   <li>Catalan Formula         — O(n) math, most efficient</li>
 *   <li>Top-Down DP (Memo)      — recursion + cache, O(n²)</li>
 *   <li>Bottom-Up DP (Table)    — iterative tabulation, O(n²)</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class UniqueBST {

    // -------------------------------------------------------------------------
    // Approach 1: Plain Recursion (no memoization)
    // Time  : O(3^n / 2) — exponential but ~50% fewer recursive calls via symmetry
    // Space : O(n)   — recursion stack depth
    // -------------------------------------------------------------------------

    /**
     * Returns the number of unique BSTs with n nodes using plain recursion.
     *
     * <p>For each choice of root i (1..n), the left subtree has i-1 nodes
     * and the right subtree has n-i nodes. Total = product of both counts.
     *
     * @param n number of nodes
     * @return count of structurally unique BSTs
     */
    public int numTrees(int n) {
        if (n <= 1) return 1;
        int ans = 0;
        for (int i = 1; i <= n / 2; i++)
            ans += 2 * numTrees(i - 1) * numTrees(n - i); // symmetric pair
        if (n % 2 == 1) ans += numTrees(n / 2) * numTrees(n / 2); // middle root
        return ans;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Catalan Number Formula
    // Time  : O(n)
    // Space : O(1)
    // -------------------------------------------------------------------------

    /**
     * Returns the number of unique BSTs with n nodes using the closed-form
     * Catalan number formula.
     *
     * <pre>
     *   C(n) = C(2n, n) / (n + 1)
     *        = product of (2 * (2i + 1) / (i + 2)) for i = 1..n-1
     * </pre>
     *
     * <p>Uses {@code long} to prevent overflow during intermediate multiplication.
     *
     * @param n number of nodes
     * @return count of structurally unique BSTs
     */
    public int numTrees2(int n) {
        long ans = 1;
        for (int i = 1; i < n; i++) ans = ans * 2 * (2 * i + 1) / (i + 2);
        return (int) ans;
    }

    // -------------------------------------------------------------------------
    // Approach 3: Top-Down DP (Memoization)
    // Time  : O(n²/2) — n states, each computed in O(n/2) via symmetry
    // Space : O(n)   — memo array + recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the number of unique BSTs with n nodes using top-down memoization.
     *
     * @param n number of nodes
     * @return count of structurally unique BSTs
     */
    public int numTrees3(int n) {
        int[] dp = new int[n + 1];
        return helper(n, dp);
    }

    /**
     * Recursive helper with memoization.
     *
     * @param n  remaining nodes to arrange
     * @param dp memoization array indexed by node count
     * @return number of unique BSTs for n nodes
     */
    private int helper(int n, int[] dp) {
        if (n <= 1) return 1;
        if (dp[n] != 0) return dp[n];
        int ans = 0;
        for (int i = 1; i <= n / 2; i++)
            ans += 2 * helper(i - 1, dp) * helper(n - i, dp); // symmetric pair
        if (n % 2 == 1) ans += helper(n / 2, dp) * helper(n / 2, dp); // middle root
        return dp[n] = ans;
    }

    // -------------------------------------------------------------------------
    // Approach 4: Bottom-Up DP (Tabulation)
    // Time  : O(n²)
    // Space : O(n)
    // -------------------------------------------------------------------------

    /**
     * Returns the number of unique BSTs with n nodes using bottom-up tabulation
     * with symmetry optimization.
     *
     * <p>dp[i] = number of unique BSTs with i nodes.
     * Recurrence: dp[i] = sum of dp[j-1] * dp[i-j] for j = 1..i
     * Base case : dp[0] = dp[1] = 1
     *
     * <p>Symmetry optimization: C(j-1) * C(i-j) == C(i-j) * C(j-1) for mirror roots,
     * so each symmetric pair is counted once and doubled. The middle root (when i is odd)
     * is handled separately. Reduces inner loop iterations by ~50%.
     *
     * @param n number of nodes
     * @return count of structurally unique BSTs
     */
    public int numTrees4(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++)
                dp[i] += 2 * dp[j - 1] * dp[i - j];  // symmetric pair
            if (i % 2 == 1)
                dp[i] += dp[i / 2] * dp[i / 2];       // middle root (odd i only)
        }
        return dp[n];
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        UniqueBST sol = new UniqueBST();

        // Test Case 1: n=1 → 1 unique BST
        System.out.println("Test 1 (n=1)  A1: " + sol.numTrees(1)  + "  A2: " + sol.numTrees2(1)  + "  A3: " + sol.numTrees3(1)  + "  A4: " + sol.numTrees4(1));  // 1

        // Test Case 2: n=2 → 2 unique BSTs
        System.out.println("Test 2 (n=2)  A1: " + sol.numTrees(2)  + "  A2: " + sol.numTrees2(2)  + "  A3: " + sol.numTrees3(2)  + "  A4: " + sol.numTrees4(2));  // 2

        // Test Case 3: n=3 → 5 unique BSTs
        System.out.println("Test 3 (n=3)  A1: " + sol.numTrees(3)  + "  A2: " + sol.numTrees2(3)  + "  A3: " + sol.numTrees3(3)  + "  A4: " + sol.numTrees4(3));  // 5

        // Test Case 4: n=4 → 14 unique BSTs
        System.out.println("Test 4 (n=4)  A1: " + sol.numTrees(4)  + "  A2: " + sol.numTrees2(4)  + "  A3: " + sol.numTrees3(4)  + "  A4: " + sol.numTrees4(4));  // 14

        // Test Case 5: n=5 → 42 unique BSTs
        System.out.println("Test 5 (n=5)  A1: " + sol.numTrees(5)  + "  A2: " + sol.numTrees2(5)  + "  A3: " + sol.numTrees3(5)  + "  A4: " + sol.numTrees4(5));  // 42

        // Test Case 6: n=19 → 1767263190
        System.out.println("Test 6 (n=19) A1: " + sol.numTrees(19) + "  A2: " + sol.numTrees2(19) + "  A3: " + sol.numTrees3(19) + "  A4: " + sol.numTrees4(19)); // 1767263190
    }
}