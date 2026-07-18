package strings.advanced;

import java.util.Arrays;

public class MinCostToConvertStringI {

    public static long minimumCost(String source, String target,
                            char[] original, char[] changed, int[] cost) {
        int n = source.length();
        final long INF = Long.MAX_VALUE / 2;
        long[][] dp = new long[26][26];
        for (long[] row : dp) Arrays.fill(row, INF);
        for (int i = 0; i < 26; i++) dp[i][i] = 0;

        for (int i = 0; i < original.length; i++) {
            int src = original[i] - 'a';
            int dest = changed[i] - 'a';
            dp[src][dest] = Math.min(dp[src][dest], cost[i]);

        }
        // Floyd warshal
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) dp[i][j] = dp[i][k] + dp[k][j];
                }

            }

        }
        long ans = 0;
        for (int i = 0; i < n; i++){
            int src =  source.charAt(i) - 'a';
            int dest = target.charAt(i) - 'a';
            if(src != dest){
                if(dp[src][dest] >= INF) return -1;
                ans += dp[src][dest];
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        // Example 1
        String source = "abcd";
        String target = "acbe";
        char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
        char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
        int[] cost = {2, 5, 5, 1, 2, 20};

        long answer = minimumCost(source, target, original, changed, cost);

        System.out.println("Answer: " + answer);
    }
}
