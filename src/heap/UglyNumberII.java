package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode: 264 - Ugly Number II
 *
 * <p>An ugly number is a positive integer whose prime factors are limited to
 * {@code 2}, {@code 3}, and {@code 5}. Given an integer {@code n}, return
 * the {@code n}th ugly number in the sequence:
 * {@code 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...}
 *
 * <p><b>Approach — Min-Heap + HashSet:</b>
 * <ul>
 *   <li>Seed the heap with {@code 1} (the first ugly number).</li>
 *   <li>Each poll produces the next smallest ugly number.</li>
 *   <li>Multiply by 2, 3, and 5 to generate next candidates; use a
 *       {@link HashSet} to prevent duplicates — e.g. {@code 2×3} and
 *       {@code 3×2} both produce 6 but should only enter the heap once.</li>
 *   <li>Repeat {@code n} times; the nth poll is the answer.</li>
 * </ul>
 *
 * <p><b>Trace (n=7, answer=8):</b>
 * <pre>
 * poll  ugly   offers           heap after
 *  1     1     2, 3, 5          [2, 3, 5]
 *  2     2     4, 6, 10         [3, 4, 5, 6, 10]
 *  3     3     6(dup), 9, 15    [4, 5, 6, 9, 10, 15]
 *  4     4     8, 12, 20        [5, 6, 8, 9, 10, 12, 15, 20]
 *  5     5     10(dup), 15(dup) [6, 8, 9, 10, 12, 15, 20]
 *  6     6     12(dup), 18, 30  [8, 9, 10, 12, 15, 18, 20, 30]
 *  7     8  ✓
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Time:  O(n log n) — n polls, heap grows at most 3n</li>
 *   <li>Space: O(n)       — heap + set grow proportionally to n</li>
 * </ul>
 */
public class UglyNumberII {

    public static void main(String[] args) {
        // TC1: n=1 — first ugly number is always 1
        // Expected: 1
        System.out.println("TC1  n=1    → " + nthUglyNumber(1));

        // TC2: LC example — n=10
        // sequence: 1,2,3,4,5,6,8,9,10,12 → 10th = 12
        System.out.println("TC2  n=10   → " + nthUglyNumber(10));

        // TC3: n=2 — Expected: 2
        System.out.println("TC3  n=2    → " + nthUglyNumber(2));

        // TC4: n=3 — Expected: 3
        System.out.println("TC4  n=3    → " + nthUglyNumber(3));

        // TC5: n=4 — Expected: 4
        System.out.println("TC5  n=4    → " + nthUglyNumber(4));

        // TC6: n=5 — Expected: 5
        System.out.println("TC6  n=5    → " + nthUglyNumber(5));

        // TC7: n=6 — Expected: 6
        System.out.println("TC7  n=6    → " + nthUglyNumber(6));

        // TC8: n=7 — skips 7 (not ugly), Expected: 8
        System.out.println("TC8  n=7    → " + nthUglyNumber(7));

        // TC9: n=8 — Expected: 9
        System.out.println("TC9  n=8    → " + nthUglyNumber(8));

        // TC10: n=9 — Expected: 10
        System.out.println("TC10 n=9    → " + nthUglyNumber(9));

        // TC11: upper constraint — n=1690
        // Expected: 2123366400
        System.out.println("TC11 n=1690 → " + nthUglyNumber(1690));
    }

    /**
     * Returns the nth ugly number using a min-heap and HashSet deduplication.
     *
     * @param n rank of the ugly number to find; 1 <= n <= 1690
     * @return the nth ugly number
     */
    private static int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        seen.add(1L);
        minHeap.offer(1L);
        long ugly = 1L;
        while (n-- > 0) {
            ugly = minHeap.poll();
            for (int factor : new int[]{2, 3, 5}) {
                long next = ugly * factor;
                if (seen.add(next)) minHeap.offer(next);
            }
        }
        return (int) ugly;
    }
}