package heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LC 1046 - Last Stone Weight
 *
 * <p>We have a collection of stones, each with a positive integer weight.
 * Each turn, we smash the two heaviest stones together:
 * <ul>
 *   <li>If both weigh the same, both are destroyed.</li>
 *   <li>If weights differ, the smaller is destroyed and the larger gets
 *       weight reduced by the smaller.</li>
 * </ul>
 * Return the weight of the last remaining stone, or 0 if none remain.
 *
 * <p><b>Approach:</b> Max-Heap (PriorityQueue with reverse order).
 * Always poll the two heaviest in O(log n), push remainder if non-zero.
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Time:  O(n log n) — n polls/offers, each O(log n)</li>
 *   <li>Space: O(n) — heap storage</li>
 * </ul>
 */
public class LastStoneWeight {

    /**
     * Returns the weight of the last remaining stone after all smash rounds.
     *
     * @param stones array of positive integer stone weights; length >= 1
     * @return weight of the surviving stone, or 0 if all stones are destroyed
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) maxHeap.offer(stone);
        while (maxHeap.size() > 1) {
            int first  = maxHeap.poll();
            int second = maxHeap.poll();
            if (first != second) maxHeap.offer(first - second);
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    // -----------------------------------------------------------------------
    // Test cases
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        LastStoneWeight sol = new LastStoneWeight();
        int passed = 0, total = 0;

        // TC1: Example 1 — standard smash sequence
        // [2,7,4,1,8,1] → smash 8,7 → 1 → [2,4,1,1,1] → ... → 1
        total++; if (check(sol.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}), 1,  "TC1")) passed++;

        // TC2: Example 2 — single stone, nothing to smash
        total++; if (check(sol.lastStoneWeight(new int[]{1}),               1,  "TC2")) passed++;

        // TC3: Two equal stones — both destroyed
        total++; if (check(sol.lastStoneWeight(new int[]{3, 3}),            0,  "TC3")) passed++;

        // TC4: Two unequal stones — remainder survives
        total++; if (check(sol.lastStoneWeight(new int[]{5, 3}),            2,  "TC4")) passed++;

        // TC5: All stones equal — all destroyed
        total++; if (check(sol.lastStoneWeight(new int[]{4, 4, 4, 4}),      0,  "TC5")) passed++;

        // TC6: All stones weight 1 — odd count leaves 1
        total++; if (check(sol.lastStoneWeight(new int[]{1, 1, 1}),         1,  "TC6")) passed++;

        // TC7: Already sorted descending
        total++; if (check(sol.lastStoneWeight(new int[]{10, 7, 5, 3}),     1,  "TC7")) passed++;

        // TC8: Large values — no overflow risk with int
        total++; if (check(sol.lastStoneWeight(new int[]{1000, 1000}),      0,  "TC8")) passed++;

        // TC9: One dominant stone — always survives
        total++; if (check(sol.lastStoneWeight(new int[]{10, 1, 1, 1}),     7,  "TC9")) passed++;

        // TC10: Two stones, first smaller than second
        total++; if (check(sol.lastStoneWeight(new int[]{2, 10}),           8, "TC10")) passed++;

        System.out.printf("%nResult: %d/%d passed%n", passed, total);
    }

    /**
     * Compares actual vs expected, prints PASS/FAIL with label.
     *
     * @param actual   value returned by the solution
     * @param expected value we expect
     * @param label    test case identifier for output
     * @return true if actual == expected
     */
    private static boolean check(int actual, int expected, String label) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %s  actual=%d  expected=%d%n",
                label, ok ? "PASS" : "FAIL", actual, expected);
        return ok;
    }
}