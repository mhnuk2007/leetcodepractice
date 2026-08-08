package strings.basic;
//LeetCode 3302 Find the Lexicographically Smallest Valid Sequence
import java.util.Arrays;

public class LexicographicallySmallestValidSequence {
    public static int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] R = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) j--;
            R[i] = (m - 1) - j;
        }

        int[] ans = new int[m];
        int ansIdx = 0;
        j = 0;
        boolean misMatched = false;
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[ansIdx++] = i;
                j++;
            } else if (!misMatched && R[i + 1] >= m - 1 - j) {
                ans[ansIdx++] = i;
                j++;
                misMatched = true;
            }
        }

        if (ansIdx == m) return ans;

        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(validSequence("vbcca", "abc")));
        System.out.println(Arrays.toString(validSequence("bacdc", "abc")));
        System.out.println(Arrays.toString(validSequence("aaaaa", "aaabc")));
        System.out.println(Arrays.toString(validSequence("abc", "ab")));
    }
}
