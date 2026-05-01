package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 205 - Isomorphic Strings
 *
 * Problem:
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if characters in s can be replaced to get t,
 * where:
 *   - Each character in s maps to exactly one character in t.
 *   - Each character in t maps to exactly one character in s.
 *   - No two characters in s may map to the same character in t.
 *
 * Example 1:
 *   Input : s = "egg",   t = "add"   →  Output: true  (e→a, g→d)
 *
 * Example 2:
 *   Input : s = "foo",   t = "bar"   →  Output: false (o maps to a and r)
 *
 * Example 3:
 *   Input : s = "paper", t = "title" →  Output: true  (p→t, a→i, e→l, r→e)
 *
 * Example 4:
 *   Input : s = "badc",  t = "baba"  →  Output: false (b and d both map to b)
 *
 * Approach 1 - Two HashMaps: O(n) time, O(k) space (k = unique chars)
 *   Bidirectional mapping — forward map prevents one-to-many from s,
 *   reverse map prevents two s-chars collapsing to the same t-char.
 *
 * Approach 2 - Two int[256] arrays: O(n) time, O(1) space
 *   Same logic as Approach 1 but faster — direct array access, no boxing.
 *   Store (char + 1) so default value 0 safely means "unmapped".
 *   Without +1, null char '\0' (ASCII 0) is indistinguishable from unmapped.
 *
 * Approach 3 - Single int[512] Last Seen Index: O(n) time, O(1) space ← optimal
 *   Single array split into two halves:
 *     indices [0..255]   → last seen position of each s-character
 *     indices [256..511] → last seen position of each t-character (tc + 256)
 *   At each i, one comparison catches BOTH forward and reverse violations.
 *   Store (i+1) so default 0 safely means "never seen".
 *   Half the memory allocations vs two separate arrays — cleaner and faster.
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        // Test 1: isomorphic — e→a, g→d
        System.out.println(isIsomorphic("egg", "add"));     // true

        // Test 2: not isomorphic — o maps to both a and r
        System.out.println(isIsomorphic("foo", "bar"));     // false

        // Test 3: isomorphic — p→t, a→i, e→l, r→e
        System.out.println(isIsomorphic("paper", "title")); // true

        // Test 4: not isomorphic — b and d both map to b in t
        System.out.println(isIsomorphic("badc", "baba"));   // false

        // Test 5: single character — always isomorphic
        System.out.println(isIsomorphic("a", "b"));         // true

        // Test 6: same strings — always isomorphic
        System.out.println(isIsomorphic("abc", "abc"));     // true

        // Test 7: forward ok but reverse fails
        System.out.println(isIsomorphic("ab", "aa"));       // false
    }

    // Approach 1 — Two HashMaps (bidirectional mapping)
    // O(n) time | O(k) space — k unique characters, max 256
    public static boolean isIsomorphicTwoMaps(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sToT = new HashMap<>(); // forward: s → t
        Map<Character, Character> tToS = new HashMap<>(); // reverse: t → s

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            // forward check: sc must always map to tc
            if (sToT.containsKey(sc) && sToT.get(sc) != tc) return false;

            // reverse check: tc must always map back to sc
            if (tToS.containsKey(tc) && tToS.get(tc) != sc) return false;

            sToT.put(sc, tc);
            tToS.put(tc, sc);
        }

        return true;
    }

    // Approach 2 — Two int[256] arrays
    // O(n) time | O(1) space — faster than HashMap, no boxing overhead
    // Fix: store (char + 1) so 0 unambiguously means "unmapped"
    // Without +1, null char '\0' (ASCII=0) stored as 0 = indistinguishable from unmapped
    public static boolean isIsomorphicTwoArrays(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] sToT = new int[256]; // sToT[sc] = tc + 1
        int[] tToS = new int[256]; // tToS[tc] = sc + 1

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            // forward check: sc must always map to tc
            if (sToT[sc] != 0 && sToT[sc] != tc + 1) return false;

            // reverse check: tc must always map back to sc
            if (tToS[tc] != 0 && tToS[tc] != sc + 1) return false;

            sToT[sc] = tc + 1; // +1 offset: 0 reserved as "unmapped" sentinel
            tToS[tc] = sc + 1;
        }

        return true;
    }

    // Approach 3 — Single int[512] last seen index comparison ← optimal
    // O(n) time | O(1) space — one array, one comparison per character
    //
    // Array layout:
    //   [0..255]   → last seen index+1 of s-characters (indexed by ASCII)
    //   [256..511] → last seen index+1 of t-characters (indexed by ASCII+256)
    //
    // If lastSeen[sc] != lastSeen[tc+256] at any position,
    // the structural pattern of s and t has diverged → not isomorphic.
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        // single array: first 256 slots for s, next 256 for t
        int[] lastSeen = new int[512];

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            // one comparison catches both forward and reverse violations
            if (lastSeen[sc] != lastSeen[tc + 256]) return false;

            // update both halves to same value — structural sync
            lastSeen[sc] = i + 1;       // s-half: store i+1 (0 = never seen)
            lastSeen[tc + 256] = i + 1; // t-half: offset by 256 to avoid collision
        }

        return true;
    }
}