package heap;

import java.util.PriorityQueue;

/**
 * LeetCode: 1492 - Kth Factor of N
 *
 * <p>Given two positive integers {@code n} and {@code k}, return the {@code k}th
 * factor of {@code n} in ascending order, or {@code -1} if fewer than {@code k}
 * factors exist. A factor of {@code n} is an integer {@code i} such that
 * {@code n % i == 0}.
 *
 * <p><b>Approach 1 — Linear scan (simple):</b>
 * Iterate {@code i} from 1 to {@code n}; collect every factor and return
 * the kth one found. No heap needed — factors naturally appear in ascending
 * order during a forward scan.
 *
 * <p><b>Approach 2 — Factor pairs + Min-Heap (heap practice):</b>
 * Iterate {@code i} from 1 to {@code √n}; when {@code n % i == 0}, both
 * {@code i} and {@code n/i} are factors. Collect all into a min-heap then
 * poll k times. Guard against duplicate when {@code i == n/i} (perfect square).
 *
 * <p><b>Trace (n=12, k=3):</b>
 * <pre>
 * i=1: 12%1==0 → offer 1, offer 12
 * i=2: 12%2==0 → offer 2, offer 6
 * i=3: 12%3==0 → offer 3, offer 4
 * heap after: [1,2,3,4,6,12]
 * poll k-1=2 times → remove 1,2
 * peek → 3 ✓
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>V1: Time O(n),      Space O(1)</li>
 *   <li>V2: Time O(√n log n), Space O(d) where d = number of factors</li>
 * </ul>
 */
class KthFactorOfN {

    public static void main(String[] args) {
        KthFactorOfN sol = new KthFactorOfN();

        // TC1: LC example 1 — n=12, k=3, factors=[1,2,3,4,6,12]
        // Expected: 3
        System.out.println("TC1  n=12 k=3  → V1: " + sol.kthFactorV1(12, 3)
                + " | V2: " + sol.kthFactorV2(12, 3));

        // TC2: LC example 2 — n=7, k=2, factors=[1,7] (prime)
        // Expected: 7
        System.out.println("TC2  n=7  k=2  → V1: " + sol.kthFactorV1(7, 2)
                + " | V2: " + sol.kthFactorV2(7, 2));

        // TC3: LC example 3 — n=4, k=4, factors=[1,2,4] only 3 factors
        // Expected: -1
        System.out.println("TC3  n=4  k=4  → V1: " + sol.kthFactorV1(4, 4)
                + " | V2: " + sol.kthFactorV2(4, 4));

        // TC4: k=1 — always returns 1 (smallest factor of any n)
        // Expected: 1
        System.out.println("TC4  n=12 k=1  → V1: " + sol.kthFactorV1(12, 1)
                + " | V2: " + sol.kthFactorV2(12, 1));

        // TC5: k equals factor count — returns n itself (largest factor)
        // factors of 6=[1,2,3,6], k=4 → Expected: 6
        System.out.println("TC5  n=6  k=4  → V1: " + sol.kthFactorV1(6, 4)
                + " | V2: " + sol.kthFactorV2(6, 4));

        // TC6: Perfect square — n/i == i, must not double-count
        // factors of 9=[1,3,9], k=2 → Expected: 3
        System.out.println("TC6  n=9  k=2  → V1: " + sol.kthFactorV1(9, 2)
                + " | V2: " + sol.kthFactorV2(9, 2));

        // TC7: n=1 — only factor is 1, k=1
        // Expected: 1
        System.out.println("TC7  n=1  k=1  → V1: " + sol.kthFactorV1(1, 1)
                + " | V2: " + sol.kthFactorV2(1, 1));

        // TC8: n=1, k=2 — only one factor exists
        // Expected: -1
        System.out.println("TC8  n=1  k=2  → V1: " + sol.kthFactorV1(1, 2)
                + " | V2: " + sol.kthFactorV2(1, 2));

        // TC9: Large prime — only factors are 1 and n itself
        // factors of 97=[1,97], k=2 → Expected: 97
        System.out.println("TC9  n=97 k=2  → V1: " + sol.kthFactorV1(97, 2)
                + " | V2: " + sol.kthFactorV2(97, 2));

        // TC10: Highly composite number
        // factors of 36=[1,2,3,4,6,9,12,18,36], k=5 → Expected: 6
        System.out.println("TC10 n=36 k=5  → V1: " + sol.kthFactorV1(36, 5)
                + " | V2: " + sol.kthFactorV2(36, 5));
    }

    /**
     * Approach 1: Linear scan — iterate 1..n, return kth factor found.
     * Factors appear in natural ascending order; no sorting needed.
     *
     * @param n the number to factorise; 1 <= n <= 1000
     * @param k rank of the factor to return; 1 <= k <= 1000
     * @return the kth factor of n in ascending order, or -1 if fewer than k factors exist
     */
    public int kthFactorV1(int n, int k) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && --k == 0) return i;
        }
        return -1;
    }

    /**
     * Approach 2: Factor pairs + Min-Heap.
     * Iterates only up to √n; each divisor i yields the pair (i, n/i).
     * Guards against duplicate when i == n/i (perfect square root).
     *
     * @param n the number to factorise; 1 <= n <= 1000
     * @param k rank of the factor to return; 1 <= k <= 1000
     * @return the kth factor of n in ascending order, or -1 if fewer than k factors exist
     */
    public int kthFactorV2(int n, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; (long) i * i <= n; i++) {
            if (n % i == 0) {
                minHeap.offer(i);
                if (i != n / i) minHeap.offer(n / i);  // guard: skip duplicate on perfect square
            }
        }
        while (k-- > 1) {
            if (minHeap.isEmpty()) return -1;
            minHeap.poll();
        }
        return minHeap.isEmpty() ? -1 : minHeap.peek();
    }
}