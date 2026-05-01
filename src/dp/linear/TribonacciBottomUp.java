package dp;

public class TribonacciBottomUp {

    /**
     * Returns the nth Tribonacci number.
     * T(0)=0, T(1)=1, T(2)=1, T(n)=T(n-1)+T(n-2)+T(n-3)
     *
     * Approach: Bottom-up DP (tabulation with int[] table).
     * Fill base cases, then iterate forward.
     * Time: O(n), Space: O(n)
     *
     * tribonacci(0)  → 0
     * tribonacci(1)  → 1
     * tribonacci(2)  → 1
     * tribonacci(4)  → 4
     * tribonacci(25) → 1389537
     */
    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        // State:      dp[i] = ith Tribonacci number
        // Transition: dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        // Base case:  dp[0]=0, dp[1]=1, dp[2]=1
        // Answer:     dp[n]

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(tribonacci(0));  // Expected: 0
        System.out.println(tribonacci(1));  // Expected: 1
        System.out.println(tribonacci(2));  // Expected: 1
        System.out.println(tribonacci(4));  // Expected: 4
        System.out.println(tribonacci(25)); // Expected: 1389537
    }
}