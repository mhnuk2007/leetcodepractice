package bitmanipulation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3513 — Number of Unique XOR Triplets I.
 *
 * <p>Given {@code nums}, a permutation of the integers {@code 1..n}, count
 * the number of distinct values obtainable as {@code nums[i] ^ nums[j] ^ nums[k]}
 * for {@code i <= j <= k} (repeated indices allowed).
 *
 * <p><b>Key insight:</b> because {@code nums} is guaranteed to contain every
 * integer from 1 to n exactly once, the reachable XOR values are entirely
 * determined by {@code n} alone, independent of the permutation's order.
 * <ul>
 *   <li>For {@code n < 3}, every triplet must repeat at least one index, so
 *       the only reachable values are the array elements themselves — the
 *       answer is simply {@code n}.</li>
 *   <li>For {@code n >= 3}, the permutation's values collectively span every
 *       bit position below the highest bit needed to represent {@code n}.
 *       Every binary number below the next power of two becomes reachable
 *       via some triplet, so the answer is exactly that next power of two.</li>
 * </ul>
 *
 * <p>This closed-form result depends entirely on the permutation guarantee.
 * It does <b>not</b> generalize to arbitrary arrays with duplicates or
 * out-of-range values (see LeetCode 3514, the general-array variant, which
 * has no such closed form). This class is named with the "I" suffix
 * specifically to keep that distinction visible at a glance.
 *
 * <p>Time: O(1). Space: O(1).
 */
public class NumberOfUniqueXorTripletsI {

    /**
     * Returns the number of distinct XOR-triplet values reachable from a
     * permutation of {@code 1..nums.length}, computed as the next power of
     * two strictly greater than {@code n} (for {@code n >= 3}), via
     * {@code 1 << (32 - Integer.numberOfLeadingZeros(n))}.
     */
    public static int uniqueXorTripletsBits(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        return 1 << (32 - Integer.numberOfLeadingZeros(n));
    }

    /**
     * Equivalent formula using {@link Integer#highestOneBit}, kept as a
     * second independent implementation so {@link #runExample} can
     * cross-check two different derivations of the same closed form
     * against each other and against brute force.
     */
    private static int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        return Integer.highestOneBit(n) << 1;
    }

    /**
     * Brute-force reference used to verify {@link #uniqueXorTriplets} and
     * {@link #uniqueXorTripletsBits}. Collects every reachable XOR value
     * directly; not intended for large n given its O(n^3) cost.
     */
    private static int uniqueXorTripletsBruteForce(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int a : nums) {
            for (int b : nums) {
                for (int c : nums) {
                    seen.add(a ^ b ^ c);
                }
            }
        }
        return seen.size();
    }

    public static void main(String[] args) {
        runExample(new int[]{1, 2}, 2);
        runExample(new int[]{3, 1, 2}, 4);
        runExample(new int[]{1, 2, 3, 4}, 8);
        runExample(new int[]{1, 2, 3, 4, 5}, 8);
        runExample(new int[]{1, 2, 3, 4, 5, 6}, 8);
        runExample(new int[]{1, 2, 3, 4, 5, 6, 7}, 8);
        runExample(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 16);
        runExample(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 16);
        runExample(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 16);
        runExample(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, 16);
        runExample(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 16);
    }

    private static void runExample(int[] nums, int expected) {
        int bits = uniqueXorTripletsBits(nums);
        int alt = uniqueXorTriplets(nums);
        int brute = uniqueXorTripletsBruteForce(nums);
        boolean allMatch = bits == expected && alt == expected && brute == expected;
        String status = allMatch ? "PASS" : "FAIL";
        System.out.printf("[%s] nums=%s -> bits=%d alt=%d brute=%d (expected %d)%n",
                status, Arrays.toString(nums), bits, alt, brute, expected);
    }
}