package slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 187 — Repeated DNA Sequences
 *
 * <p>Given a DNA string {@code s}, return all 10-letter substrings that appear
 * more than once. DNA strings use only the characters A, C, G, T.
 *
 * <p><b>Approach 1 — Sliding Window with Substring Hashing:</b><br>
 * Slide a fixed window of size 10 across the string. Use a {@code seen} set to
 * track visited substrings. If {@code seen.add(sub)} returns false, the substring
 * was already present — add it to {@code repeated}.
 * Simple and correct, but creates a new String object at every step.
 * Time: O(10·N) — substring copy cost per window. Space: O(10·N).
 *
 * <p><b>Approach 2 — Bit Encoding (no String allocation per step):</b><br>
 * Encode each character as a 2-bit value: A=00, C=01, G=10, T=11.
 * Represent the current 10-character window as a 20-bit integer.
 * Slide the window by left-shifting out the oldest character and OR-ing in
 * the new one. Hash integers instead of strings — O(1) per step.
 * Time: O(N). Space: O(N) for the sets.
 *
 * <p>Approach 2 is the expected follow-up answer in a FAANG interview after
 * Approach 1 is accepted.
 *
 * @see <a href="https://leetcode.com/problems/repeated-dna-sequences/">LC 187</a>
 */
public class RepeatedDNASequence {


    // -------------------------------------------------------------------------
    // Approach 1 — Sliding Window with Substring Hashing
    // -------------------------------------------------------------------------

    /**
     * Returns all 10-letter DNA sequences that appear more than once.
     *
     * <p>Uses a two-set pattern: {@code seen} tracks all substrings encountered;
     * {@code repeated} collects confirmed duplicates. {@code Set.add} returns
     * {@code false} when the element already exists — a clean way to detect
     * duplicates without an explicit {@code contains} check beforehand.
     *
     * <p>Loop bound is {@code s.length() - 9} (not {@code s.length() - 10}):
     * the last valid start index is {@code s.length() - 10}, and the exclusive
     * upper bound is therefore {@code s.length() - 10 + 1 = s.length() - 9}.
     *
     * @param s DNA string containing only 'A', 'C', 'G', 'T'
     * @return list of 10-letter substrings appearing more than once
     */
    public static List<String> substringHashing(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i < s.length() - 9; i++) {
            String sub = s.substring(i, i + 10);
            if (!seen.add(sub)) {
                repeated.add(sub);
            }
        }
        return new ArrayList<>(repeated);
    }

    // -------------------------------------------------------------------------
    // Approach 2 — Bit Encoding (rolling integer hash, no String allocation)
    // -------------------------------------------------------------------------

    /**
     * Returns all 10-letter DNA sequences that appear more than once.
     *
     * <p>Encodes each character as 2 bits: A=00, C=01, G=10, T=11.
     * A 10-character window fits in 20 bits of an integer.
     *
     * <p>Sliding the window:
     * <pre>
     *   hash = ((hash << 2) | encode(ch)) & MASK
     * </pre>
     * Left-shift discards the oldest character's 2 bits (they fall off the
     * 20-bit boundary enforced by {@code MASK = 0xFFFFF}), and OR adds the
     * new character's 2 bits at the right.
     *
     * <p>The first valid window is complete only after processing index 9
     * (i.e., when {@code j >= 9}). Before that, we are still building the
     * initial 20-bit value.
     *
     * <p>Integers are hashed instead of Strings — O(1) per step with no heap
     * allocation inside the loop.
     *
     * @param s DNA string containing only 'A', 'C', 'G', 'T'
     * @return list of 10-letter substrings appearing more than once
     */
    public static List<String> bitEncoding(String s) {
        // 2-bit encoding: A=00, C=01, G=10, T=11
        int[] encode = new int[128];
        encode['A'] = 0;
        encode['C'] = 1;
        encode['G'] = 2;
        encode['T'] = 3;

        final int WINDOW = 10;
        final int MASK   = (1 << (WINDOW * 2)) - 1; // 0xFFFFF — keeps only 20 bits

        Set<Integer> seen     = new HashSet<>();
        Set<String>  repeated = new HashSet<>();

        int hash = 0;

        for (int j = 0; j < s.length(); j++) {
            hash = ((hash << 2) | encode[s.charAt(j)]) & MASK;

            if (j >= WINDOW - 1) {                        // first complete window at j=9
                if (!seen.add(hash)) {
                    repeated.add(s.substring(j - WINDOW + 1, j + 1)); // decode only on hit
                }
            }
        }
        return new ArrayList<>(repeated);
    }

    // -------------------------------------------------------------------------
    // Main
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"; // Expected: [AAAAACCCCC, CCCCCAAAAA]
        String s2 = "AAAAAAAAAAAAA";                    // Expected: [AAAAAAAAAA]
        String s3 = "ACGT";                             // Expected: []  (shorter than 10)

        System.out.println("--- Approach 1: Substring Hashing ---");
        System.out.println(substringHashing(s1));
        System.out.println(substringHashing(s2));
        System.out.println(substringHashing(s3));

        System.out.println("--- Approach 2: Bit Encoding ---");
        System.out.println(bitEncoding(s1));
        System.out.println(bitEncoding(s2));
        System.out.println(bitEncoding(s3));
    }
}