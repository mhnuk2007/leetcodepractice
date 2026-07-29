package strings.basic;

public class SmallestPalindromicRearrangementII {

    private static final long LIMIT = 1_000_001L;

    public static String smallestPalindrome(String s, int k) {
        int n = s.length();
        int halfLen = n / 2;

        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int[] halfFreq = new int[26];
        char midChar = 0;

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;

            if ((freq[i] & 1) == 1) {
                midChar = (char) ('a' + i);
            }
        }

        long remainingWays = countWays(halfFreq, halfLen);
        // Number of valid permutations that can still be formed
        // from the current multiset of the left half.
        if (remainingWays < k) {
            // Not enough distinct palindromic rearrangements.
            return "";
        }

        char[] result = new char[n];

        if ((n & 1) == 1) result[halfLen] = midChar;
        int remaining = halfLen;
        long rank = k;

        // Greedily construct the left half one character at a time.
        for (int i = 0; i < halfLen; i++) {
            for (int j = 0; j < 26; j++) {
                int count = halfFreq[j];
                if (count == 0) {
                    continue;
                }

                long ways;
                if (remainingWays < LIMIT) {
                    // O(1) multinomial update:
                    // ways = remainingWays * count / remaining
                    ways = remainingWays * count / remaining;
                } else {
                    // remainingWays was capped at LIMIT, so recompute exactly.
                    halfFreq[j]--;
                    ways = countWays(halfFreq, remaining - 1);
                    halfFreq[j]++;
                }

                if (ways >= rank) {
                    halfFreq[j]--;
                    result[i] = result[n - 1 - i] = (char) ('a' + j);
                    remaining--;
                    // Remaining search space after fixing this character.
                    remainingWays = ways;
                    break;
                }

                // Skip all palindromes starting with this character.
                rank -= ways;
            }
        }

        return String.valueOf(result);
    }

    private static long countWays(int[] halfFreq, int remaining) {
        long ways = 1;

        for (int i = 0; i < 26; i++) {
            int count = halfFreq[i];

            for (int j = 1; j <= count; j++) {
                ways = ways * (remaining - count + j) / j;

                if (ways > LIMIT) {
                    return LIMIT;
                }
            }

            remaining -= count;
        }

        return ways;
    }

    public static void main(String[] args) {
        System.out.println(smallestPalindrome("ab", 2));      // ""
        System.out.println(smallestPalindrome("aa", 2));      // ""
        System.out.println(smallestPalindrome("bacab", 1));   // abcba
        System.out.println(smallestPalindrome("abba", 1));    // abba
        System.out.println(smallestPalindrome("abba", 2));    // baab
        System.out.println(smallestPalindrome("babab", 1));   // abbba
        System.out.println(smallestPalindrome("babab", 2));   // babab
        System.out.println(smallestPalindrome("daccad", 1));  // acddca
        System.out.println(smallestPalindrome("daccad", 10)); // ""
        System.out.println(smallestPalindrome("edacbeebcade", 45));
    }
}