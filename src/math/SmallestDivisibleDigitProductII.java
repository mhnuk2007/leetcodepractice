package math;

// LeetCode 3348 - Smallest Divisible Digit Product II

public class SmallestDivisibleDigitProductII {
    // DP array to store the min digits required for pairs of (twos,threes)
    static int[][] DP = new int[60][40];
    static int[][] digitFactors = new int[10][4];
    static int[] primes = {2, 3, 5, 7};

    static {
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 40; j++) {
                if (i == 0 && j == 0) {
                    DP[i][j] = 0;
                    continue;
                }
                int res = 1000000;
                if (i > 0) {
                    res = Math.min(res, 1 + DP[Math.max(0, i - 1)][j]); // Digit 2
                    res = Math.min(res, 1 + DP[Math.max(0, i - 2)][j]); // Digit 4
                    res = Math.min(res, 1 + DP[Math.max(0, i - 3)][j]); // Digit 8
                }
                if (j > 0) {
                    res = Math.min(res, 1 + DP[i][Math.max(0, j - 1)]); // Digit 3
                    res = Math.min(res, 1 + DP[i][Math.max(0, j - 2)]); // Digit 9
                }

                if (i > 0 || j > 0) {
                    res = Math.min(res, 1 + DP[Math.max(0, i - 1)][Math.max(0, j - 1)]); // Digit 6
                }
                DP[i][j] = res;
            }
        }

        // cache the specific configurations of factors per digit
        for (int d = 1; d <= 9; d++) {
            int tmp = d;
            for (int j = 0; j < 4; j++) {
                while (tmp % primes[j] == 0) {
                    tmp /= primes[j];
                    digitFactors[d][j]++;
                }
            }
        }
    }

    private static String smallestNumber(String num, long t) {
        long temp = t;
        int[] req = new int[4];
        for (int i = 0; i < 4; i++) {
            while (temp % primes[i] == 0) {
                req[i]++;
                temp /= primes[i];
            }
        }

        if (temp > 1) return "-1";

        int n = num.length();
        int[][] P = new int[n + 1][4];
        boolean isValid = true;
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                isValid = false;
                if (firstZero == n) firstZero = i;
            }
            for (int j = 0; j < 4; j++) P[i + 1][j] = P[i][j] + digitFactors[d][j];
        }

        if (isValid)
            if (P[n][0] >= req[0] && P[n][1] >= req[1] && P[n][2] >= req[2] && P[n][3] >= req[3]) return num;

        for (int i = Math.min(n - 1, firstZero); i >= 0; i--) {
            int d = num.charAt(i) - '0';
            for (int j = d + 1; j <= 9; j++) {
                int req2 = Math.max(0, req[0] - P[i][0] - digitFactors[j][0]);
                int req3 = Math.max(0, req[1] - P[i][1] - digitFactors[j][1]);
                int req5 = Math.max(0, req[2] - P[i][2] - digitFactors[j][2]);
                int req7 = Math.max(0, req[3] - P[i][3] - digitFactors[j][3]);

                int k = n - 1 - i;
                if (req5 + req7 + DP[req2][req3] <= k)
                    return num.substring(0, i) + j + smallestSuffix(req2, req3, req5, req7, k);
            }
        }
        int L = Math.max(n + 1, req[2] + req[3] + DP[req[0]][req[1]]);
        return smallestSuffix(req[0], req[1], req[2], req[3], L);


    }

    private static String smallestSuffix(int req2, int req3, int req5, int req7, int length) {
        if (length == 0) return "";
        StringBuilder sb = new StringBuilder(length);
        for (int pos = 1; pos <= length; pos++) {
            for (int c = 1; c <= 9; c++) {
                int newReq2 = Math.max(0, req2 - digitFactors[c][0]);
                int newReq3 = Math.max(0, req3 - digitFactors[c][1]);
                int newReq5 = Math.max(0, req5 - digitFactors[c][2]);
                int newReq7 = Math.max(0, req7 - digitFactors[c][3]);

                int remainingLength = length - pos;
                if (newReq5 + newReq7 + DP[newReq2][newReq3] <= remainingLength) {
                    sb.append(c);
                    req2 = newReq2;
                    req3 = newReq3;
                    req5 = newReq5;
                    req7 = newReq7;
                    break;
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(smallestNumber("1234", 256));  // 1488
        System.out.println(smallestNumber("12355", 50));  // 12355
        System.out.println(smallestNumber("11111", 26));  // -1
        System.out.println(smallestNumber("1", 48));       // 68
        System.out.println(smallestNumber("1", 35));       // 57
    }
}