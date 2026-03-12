package hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 387 - First Unique Character in a String
 *
 * Problem:
 * Given a string s, find the first non-repeating character and return
 * its index. If no such character exists, return -1.
 *
 * Example 1:
 *   Input : s = "leetcode"      →  Output: 0  ('l' at index 0 is unique)
 *
 * Example 2:
 *   Input : s = "loveleetcode"  →  Output: 2  ('v' at index 2 is unique)
 *
 * Example 3:
 *   Input : s = "aabb"          →  Output: -1  (no unique character)
 *
 * Approach 1 - HashMap: O(n) time, O(k) space where k = unique chars (max 26)
 *   Pass 1: build frequency map using getOrDefault.
 *   Pass 2: scan left to right, return first index with frequency 1.
 *   Note: map.get(c) returns boxed Integer — use .equals(1) over == 1
 *   to avoid autoboxing comparison pitfalls outside the -128..127 cache range.
 *
 * Approach 2 - int[26] array: O(n) time, O(1) space  ← optimal
 *   Replace HashMap with fixed int[26] since input is lowercase letters only.
 *   Index by (char - 'a') for direct O(1) array access.
 *   Faster in practice — no hashing, no boxing, cache-friendly array access.
 *
 * Time Complexity : O(n) — two passes through the string
 * Space Complexity: O(1) — at most 26 distinct lowercase letters
 */
public class FirstUniqueCharacter {

    public static void main(String[] args) {
        // Test 1: first char is unique
        System.out.println(firstUniqCharMap(  "leetcode"));      // 0
        System.out.println(firstUniqCharArray("leetcode"));      // 0

        // Test 2: unique char in the middle
        System.out.println(firstUniqCharMap(  "loveleetcode"));  // 2
        System.out.println(firstUniqCharArray("loveleetcode"));  // 2

        // Test 3: no unique character
        System.out.println(firstUniqCharMap(  "aabb"));          // -1
        System.out.println(firstUniqCharArray("aabb"));          // -1

        // Test 4: single character — always unique
        System.out.println(firstUniqCharMap(  "z"));             // 0
        System.out.println(firstUniqCharArray("z"));             // 0

        // Test 5: all same characters
        System.out.println(firstUniqCharMap(  "aaaa"));          // -1
        System.out.println(firstUniqCharArray("aaaa"));          // -1

        // Test 6: unique char at last index
        System.out.println(firstUniqCharMap(  "aabbc"));         // 4
        System.out.println(firstUniqCharArray("aabbc"));         // 4
    }

    // Approach 1 — HashMap
    // O(n) time | O(k) space — k unique chars, max 26
    public static int firstUniqCharMap(String s) {
        Map<Character, Integer> map = new HashMap<>();

        // Pass 1: build frequency map
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Pass 2: find first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c).equals(1)) { // .equals() avoids autoboxing pitfall
                return i;
            }
        }

        return -1; // no unique character found
    }

    // Approach 2 — int[26] array ← optimal
    // O(n) time | O(1) space — fixed 26 slots, no hashing or boxing
    public static int firstUniqCharArray(String s) {
        int[] freq = new int[26]; // index 0='a', 1='b', ..., 25='z'

        // Pass 1: count frequency of each character
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++; // map char to index 0-25
        }

        // Pass 2: return index of first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1; // no unique character found
    }
}