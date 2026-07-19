package _sandbox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumGroups(String[] words) {
        Map<String, Integer> groupId = new HashMap<>();
        int groups = 0;
        for (String w : words) {
            StringBuilder evenSb = new StringBuilder();
            StringBuilder oddSb = new StringBuilder();
            for (int i = 0; i < w.length(); i++) {
                if (i % 2 == 0) evenSb.append(w.charAt(i));
                else oddSb.append(w.charAt(i));
            }
            String key = canonicalRotation(evenSb.toString()) + "#" + canonicalRotation(oddSb.toString());
            if (!groupId.containsKey(key)) groupId.put(key, groups++);
        }
        return groups;
    }

    private String canonicalRotation(String s) {
        if (s.isEmpty()) return s;
        int n = s.length();
        String doubled = s + s;
        int[] failure = new int[2 * n];
        Arrays.fill(failure, -1);
        int k = 0;
        for (int j = 1; j < 2 * n; j++) {
            char sj = doubled.charAt(j);
            int i = failure[j - k - 1];
            while (i != -1 && sj != doubled.charAt(k + i + 1)) {
                if (sj < doubled.charAt(k + i + 1)) k = j - i - 1;
                i = failure[i];
            }
            if (sj != doubled.charAt(k + i + 1)) {
                if (sj < doubled.charAt(k + i + 1)) k = j;
                failure[j - k] = -1;
            } else {
                failure[j - k] = i + 1;
            }
        }
        return doubled.substring(k, k + n);
    }
    public static void main(String[] args) {

        // Example 1
        String[] words1 = {"ntgwz", "zwntg"};
        System.out.println("Example 1:");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + new Solution().minimumGroups(words1));
        System.out.println();

        // Example 2
        String[] words2 = {"abc", "cab", "bac", "acb", "bca", "cba"};
        System.out.println("Example 2:");
        System.out.println("Expected: 3");
        System.out.println("Actual: " + new Solution().minimumGroups(words2));
        System.out.println();

        // Example 3
        String[] words3 = {"leet", "abb", "bab", "deed", "edde", "code", "bba"};
        System.out.println("Example 3:");
        System.out.println("Expected: 5");
        System.out.println("Actual: " + new Solution().minimumGroups(words3));
    }
}