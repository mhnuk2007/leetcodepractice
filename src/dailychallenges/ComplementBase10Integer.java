package dailychallenges;

/**
 * LeetCode 1009 - Complement of Base 10 Integer
 *
 * Problem:
 * The complement of an integer is obtained by flipping all bits
 * in its binary representation.
 * Return the complement of the given integer n.
 *
 * Example 1:
 *   Input : n = 5   →  binary: 101  →  complement: 010  →  Output: 2
 *
 * Example 2:
 *   Input : n = 7   →  binary: 111  →  complement: 000  →  Output: 0
 *
 * Example 3:
 *   Input : n = 10  →  binary: 1010 →  complement: 0101 →  Output: 5
 *
 * Example 4:
 *   Input : n = 0   →  Output: 1
 *
 * Approach: Bit Mask via While Loop
 *   - Java's ~n flips all 32 bits, producing negative numbers — not what we want.
 *   - Instead, build a mask of all 1s that is exactly as wide as n in binary.
 *   - For each bit in n (via temp >>= 1), grow the mask: (mask << 1) | 1.
 *   - XOR n with the mask to flip only the relevant bits.
 *
 *   Example for n = 5:
 *     mask builds as: 001 → 011 → 111
 *     5 ^ 7 = 101 ^ 111 = 010 = 2
 *
 *   Edge case: n = 0 has no bits to iterate over, return 1 directly.
 *
 * Time Complexity : O(log n) — one iteration per bit in n
 * Space Complexity: O(1)
 */
public class ComplementBase10Integer {

    public static void main(String[] args) {
        // Test 1: 5 → 101 → complement 010 = 2
        System.out.println(bitwiseComplement(5));   // 2

        // Test 2: 7 → 111 → complement 000 = 0
        System.out.println(bitwiseComplement(7));   // 0

        // Test 3: 10 → 1010 → complement 0101 = 5
        System.out.println(bitwiseComplement(10));  // 5

        // Test 4: edge case — 0 → complement = 1
        System.out.println(bitwiseComplement(0));   // 1

        // Test 5: 1 → 1 → complement 0 = 0
        System.out.println(bitwiseComplement(1));   // 0

        // Test 6: power of 2 — 8 → 1000 → complement 0111 = 7
        System.out.println(bitwiseComplement(8));   // 7
    }

    public static int bitwiseComplement(int n) {
        // edge case: 0 has no binary bits to flip
        if (n == 0) return 1;

        int mask = 0;
        int temp = n;

        // build mask of 1s matching the bit-width of n
        while (temp > 0) {
            mask = (mask << 1) | 1; // shift left and append a 1 bit
            temp >>= 1;             // consume one bit of n
        }

        // XOR flips only the bits covered by the mask
        return n ^ mask;
    }
}