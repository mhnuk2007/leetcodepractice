package strings.basic;

import java.util.Arrays;
import java.util.Collections;

public class SmallestPalindromicRearrangementI {
    public static String smallestPalindrome(String s) {
        int n = s.length();
        char[] result = new char[n];
        int mid = n / 2;
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        int start = 0;
        int end = n - 1;
        for (int i = 0; i < 26 && start < end; i++) {
            char c = (char) ('a' + i);
            while (freq[i] >= 2) {
                result[start++] = c;
                result[end--] = c;
                freq[i] -= 2;
            }
        }
        if (start == end) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] == 1) {
                    result[mid] = (char) ('a' + i);
                    break;
                }
            }
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(smallestPalindrome("z"));
        System.out.println(smallestPalindrome("babab"));
        System.out.println(smallestPalindrome("daccad"));
    }
}
