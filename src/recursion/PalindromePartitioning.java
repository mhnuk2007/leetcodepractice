package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131 - Palindrome Partitioning
 *
 * Problem:
 *   Given a string s, partition it such that every substring of the partition
 *   is a palindrome. Return all possible palindrome partitioning.
 *
 * Approach: Backtracking with prefix expansion
 *   At each step, try all prefixes s.substring(0, i) for i = 1..length.
 *   If the prefix is a palindrome, add it to current partition and recurse
 *   on the remaining string s.substring(i). Backtrack by removing last added.
 *   Base case: empty string → record current partition.
 *
 * Example:
 *   s = "aab"
 *   "a" ✓ → "a" ✓ → "b" ✓ → ["a","a","b"] ✓
 *   "a" ✓ → "ab"  ✗
 *   "aa" ✓ → "b" ✓ → ["aa","b"] ✓
 *   "aab" ✗
 *
 * Time  : O(n·2^n) — 2^n partitions, palindrome check costs O(n)
 * Space : O(n)     — recursion depth
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("s=\"aab\": " + partition("aab"));
        // Expected: [[a,a,b],[aa,b]]

        // Test 2: all same characters
        System.out.println("s=\"aaa\": " + partition("aaa"));
        // Expected: [[a,a,a],[a,aa],[aa,a],[aaa]]

        // Test 3: single character
        System.out.println("s=\"a\": " + partition("a"));
        // Expected: [[a]]

        // Test 4: no repeated characters
        System.out.println("s=\"abc\": " + partition("abc"));
        // Expected: [[a,b,c]]
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        getAllPartitions(s, new ArrayList<>(), result);
        return result;
    }

    private static void getAllPartitions(String s, List<String> partitions, List<List<String>> result) {
        if (s.isEmpty()) {
            result.add(new ArrayList<>(partitions));                       // valid partition found
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);                               // try prefix of length i
            if (isPalindrome(sub)) {
                partitions.add(sub);                                       // include palindrome prefix
                getAllPartitions(s.substring(i), partitions, result);      // recurse on remainder
                partitions.remove(partitions.size() - 1);                 // backtrack
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++)
            if (s.charAt(i) != s.charAt(n - i - 1)) return false;         // mismatch found
        return true;
    }
}