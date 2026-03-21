package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeeCode: 346 — Moving Average from Data Stream
 *
 * Problem: Given a stream of integers and a window size k, return the
 * moving average of the last k elements after each new element arrives.
 *
 * Approach 1 — Queue (optimal):
 *   Maintain a queue of at most k elements and a running sum.
 *   On each next() call: add new value to queue and sum.
 *   If queue exceeds size k, remove the oldest element from sum and queue.
 *   Average = sum / queue.size()
 *
 * Approach 2 — Circular Array:
 *   Fixed array of size k with a pointer that wraps around.
 *   Overwrites oldest element in-place. No queue needed.
 *
 * Time Complexity : O(1) per next() call — both approaches
 * Space Complexity: O(k) — both approaches
 */
public class MovingAverage {

    // ─── Approach 1: Queue + running sum ─────────────────────────────────────
    static class MovingAverageQueue {

        private final Deque<Integer> queue;
        private final int size;
        private double sum;

        public MovingAverageQueue(int size) {
            this.size = size;
            this.queue = new ArrayDeque<>();
            this.sum = 0;
        }

        public double next(int val) {
            queue.offer(val);
            sum += val;
            if (queue.size() > size) {
                sum -= queue.poll(); // remove oldest, subtract from sum
            }
            return sum / queue.size();
        }
    }

    // ─── Approach 2: Circular Array ───────────────────────────────────────────
    static class MovingAverageCircular {

        private final int[] arr;
        private final int size;
        private int count;    // total elements seen so far
        private double sum;

        public MovingAverageCircular(int size) {
            this.size = size;
            this.arr = new int[size];
            this.count = 0;
            this.sum = 0;
        }

        public double next(int val) {
            int index = count % size;       // circular index — wraps around
            sum -= arr[index];              // subtract value being overwritten
            arr[index] = val;               // overwrite with new value
            sum += val;
            count++;
            return sum / Math.min(count, size);
        }
    }

    // ─── Main: labeled test cases ─────────────────────────────────────────────
    public static void main(String[] args) {

        // Test 1: LeetCode standard example
        System.out.println("=== Test 1: LeetCode standard ===");
        MovingAverageQueue q = new MovingAverageQueue(3);
        System.out.println(q.next(1));  // 1.0
        System.out.println(q.next(10)); // 5.5
        System.out.println(q.next(3));  // 4.666...
        System.out.println(q.next(5));  // 6.0  ← 1 dropped

        System.out.println("\n=== Test 1: Circular same input ===");
        MovingAverageCircular c = new MovingAverageCircular(3);
        System.out.println(c.next(1));  // 1.0
        System.out.println(c.next(10)); // 5.5
        System.out.println(c.next(3));  // 4.666...
        System.out.println(c.next(5));  // 6.0

        // Test 2: Window size 1 — always returns latest value
        System.out.println("\n=== Test 2: Window size 1 ===");
        MovingAverageQueue q2 = new MovingAverageQueue(1);
        System.out.println(q2.next(5));  // 5.0
        System.out.println(q2.next(10)); // 10.0
        System.out.println(q2.next(3));  // 3.0

        // Test 3: Window larger than input — no eviction yet
        System.out.println("\n=== Test 3: Window larger than input ===");
        MovingAverageQueue q3 = new MovingAverageQueue(5);
        System.out.println(q3.next(4));  // 4.0
        System.out.println(q3.next(8));  // 6.0
        System.out.println(q3.next(2));  // 4.666...

        // Test 4: All same values
        System.out.println("\n=== Test 4: All same values ===");
        MovingAverageQueue q4 = new MovingAverageQueue(3);
        System.out.println(q4.next(5)); // 5.0
        System.out.println(q4.next(5)); // 5.0
        System.out.println(q4.next(5)); // 5.0
        System.out.println(q4.next(5)); // 5.0
    }
}