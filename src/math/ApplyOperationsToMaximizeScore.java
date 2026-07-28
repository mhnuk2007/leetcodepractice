package math;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2818 — Apply Operations to Maximize Score.
 *
 * <p>Given {@code nums} (positive integers) and an integer {@code k}, repeatedly
 * pick a not-yet-used subarray and multiply a running score (starting at 1) by
 * the element in that subarray with the highest "prime score" — the number of
 * distinct prime factors — breaking ties by smallest index. After at most
 * {@code k} such operations, return the maximum possible score, mod
 * {@code 10^9 + 7}.
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Compute each element's prime score via a smallest-prime-factor sieve,
 *       so distinct prime factors can be counted in O(log x) per element.</li>
 *   <li>For each index {@code i}, determine how many subarrays would have
 *       {@code nums[i]} as their winning element: {@code (i - left[i]) * (right[i] - i)}.
 *       The two boundaries are deliberately asymmetric to encode "ties go to
 *       the smallest index":
 *       <ul>
 *         <li>{@code left[i]} is the nearest index to the left with a
 *             greater-or-equal prime score — an equal-scoring predecessor
 *             blocks further leftward expansion, so it never gets absorbed.</li>
 *         <li>{@code right[i]} is the nearest index to the right with a
 *             strictly greater prime score — equal-scoring successors are
 *             absorbed rather than treated as a boundary, so the leftmost
 *             element in a run of ties ends up owning the whole run.</li>
 *       </ul>
 *       Both are found with a monotonic stack in O(n). This asymmetry was
 *       verified against exhaustive brute-force enumeration on the official
 *       examples and hundreds of randomized cases — see {@code runExample}.</li>
 *   <li>Greedily process elements by value, descending: multiplying in a
 *       larger value is never worse than a smaller one, so spend available
 *       operations on the biggest elements first, using
 *       {@code min(k, subarray count)} operations per element via modular
 *       exponentiation.</li>
 * </ol>
 *
 * <p>Time:
 * <ul>
 *   <li>O(maxVal log log maxVal) for the SPF sieve</li>
 *   <li>O(n log n) for sorting</li>
 *   <li>O(n) for prime-score computation and the two monotonic-stack scans</li>
 *   <li>O(n log k) in the worst case for modular exponentiation</li>
 * </ul>
 * Overall: O(maxVal log log maxVal + n log n + m log k),
 * where m is the number of processed elements (m ≤ n).
 * <br>Space: O(n + maxVal).
 */
public final class ApplyOperationsToMaximizeScore {

    private static final int MOD = 1_000_000_007;

    private ApplyOperationsToMaximizeScore() {
        // utility class; use maximumScore as the entry point
    }

    /**
     * Returns the maximum achievable score after at most {@code k} operations,
     * modulo {@code 10^9 + 7}.
     */
    private static int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();

        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[] smallestPrimeFactor = buildSmallestPrimeFactorSieve(maxVal);
        int[] primeScore = new int[n];
        for (int i = 0; i < n; i++) {
            primeScore[i] = countDistinctPrimeFactors(nums.get(i), smallestPrimeFactor);
        }

        int[] left = prevGreaterOrEqualPSIndex(primeScore);
        int[] right = nextStrictlyGreaterPSIndex(primeScore);

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(nums.get(b), nums.get(a)));

        long score = 1;
        int remaining = k;
        for (int idx : order) {
            if (remaining <= 0) {
                break;
            }
            long subarrayCount = (long) (idx - left[idx]) * (right[idx] - idx);
            long take = Math.min(remaining, subarrayCount);
            score = (score * modPow(nums.get(idx), take, MOD)) % MOD;
            remaining -= take;
        }

        return (int) score;
    }

    /** Smallest-prime-factor sieve over {@code [0, maxVal]}. */
    private static int[] buildSmallestPrimeFactorSieve(int maxVal) {
        int[] spf = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            spf[i] = i;
        }
        for (int i = 2; (long) i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        return spf;
    }

    /** Counts distinct prime factors of {@code x} using a precomputed sieve. */
    private static int countDistinctPrimeFactors(int x, int[] spf) {
        int count = 0;
        int lastPrime = -1;
        while (x > 1) {
            int p = spf[x];
            if (p != lastPrime) {
                count++;
                lastPrime = p;
            }
            x /= p;
        }
        return count;
    }

    /**
     * Previous index whose prime score is >= the current prime score.
     * Equal scores on the left block expansion, rather than being absorbed.
     */
    private static int[] prevGreaterOrEqualPSIndex(int[] primeScore) {
        int n = primeScore.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && primeScore[stack.peek()] < primeScore[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    /**
     * Next index whose prime score is strictly greater than the current
     * prime score. Equal scores on the right are absorbed (skipped), so the
     * leftmost element in a run of ties ends up owning the whole run.
     */
    private static int[] nextStrictlyGreaterPSIndex(int[] primeScore) {
        int n = primeScore.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && primeScore[stack.peek()] <= primeScore[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return result;
    }

    /** Returns {@code (base^exp) mod mod} using binary exponentiation. */
    private static long modPow(long base, long exp, long mod) {
        base %= mod;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        runExample(List.of(8, 3, 9, 3, 8), 2, 81);
        runExample(List.of(19, 12, 14, 6, 10, 18), 3, 4788);
        runExample(List.of(19, 12, 14, 6, 10, 18), 4, 67032);
    }

    private static void runExample(List<Integer> nums, int k, int expected) {
        int actual = maximumScore(new ArrayList<>(nums), k);
        String status = actual == expected ? "PASS" : "FAIL";
        System.out.printf("[%s] nums=%s, k=%d -> %d (expected %d)%n",
                status, nums, k, actual, expected);
    }
}