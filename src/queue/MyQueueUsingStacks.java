package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeeCode 225 — Implement Stack using Queues
 *
 * Problem: Implement a LIFO stack using only two queues.
 * Support push, pop, top, and empty operations.
 *
 * Approach A — Two Queues:
 *   push  → enqueue to q2, drain all of q1 into q2, swap q1 and q2
 *   pop   → dequeue from q1 (front is always the most recently pushed)
 *   top   → peek front of q1
 *
 * Approach B — One Queue (optimal):
 *   push  → offer to queue, then rotate all previous elements to the back
 *            so the newest element always stays at the front
 *   pop   → poll from front
 *   top   → peek front
 *
 * Key insight: after each push, rotate the queue so the newest element
 * is at front — this simulates LIFO using a FIFO structure.
 *
 *   push(1): queue=[1]
 *   push(2): offer(2) → [1,2], rotate 1 time → [2,1]
 *   push(3): offer(3) → [2,1,3], rotate 2 times → [3,2,1]
 *   pop()  → poll() → 3 ✓
 *
 * Time Complexity : push O(n), pop O(1), top O(1)
 * Space Complexity: O(n)
 */
public class MyQueueUsingStacks {

    // ─── Approach A: Two Queues ───────────────────────────────────────────────
    static class MyStackTwoQueues {

        private Deque<Integer> q1; // main queue — front is always top of stack
        private Deque<Integer> q2; // helper queue for push rotation

        public MyStackTwoQueues() {
            q1 = new ArrayDeque<>();
            q2 = new ArrayDeque<>();
        }

        // Push — O(n): enqueue to q2, drain q1 into q2, swap
        public void push(int x) {
            q2.offer(x);                     // new element goes to q2 first
            while (!q1.isEmpty())
                q2.offer(q1.poll());         // drain q1 behind the new element
            Deque<Integer> temp = q1;        // swap references
            q1 = q2;
            q2 = temp;
            // q1 front is now the newest element (top of stack)
        }

        public int pop()   { return q1.poll(); }

        public int top()   { return q1.peek(); }

        public boolean empty() { return q1.isEmpty(); }
    }

    // ─── Approach B: One Queue (optimal) ─────────────────────────────────────
    static class MyStackOneQueue {

        private final Deque<Integer> queue;

        public MyStackOneQueue() {
            queue = new ArrayDeque<>();
        }

        // Push — O(n): offer then rotate all previous elements to back
        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() - 1; i++)
                queue.offer(queue.poll()); // rotate: move front to back
            // newest element is now at front
        }

        public int pop()   { return queue.poll(); }

        public int top()   { return queue.peek(); }

        public boolean empty() { return queue.isEmpty(); }
    }

    // ─── Main: labeled test cases ─────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("=== Two Queue approach ===");
        testStack(new MyStackTwoQueues());

        System.out.println("\n=== One Queue approach ===");
        testStack(new MyStackOneQueue());
    }

    // Shared test runner — same tests for both approaches
    static void testStack(Object stack) {

        // Test 1: LeetCode standard example
        System.out.println("\n--- Test 1: LeetCode standard ---");
        if (stack instanceof MyStackTwoQueues s) {
            s.push(1); s.push(2);
            System.out.println("Top: " + s.top());   // 2
            System.out.println("Pop: " + s.pop());   // 2
            System.out.println("Empty: " + s.empty()); // false
        } else if (stack instanceof MyStackOneQueue s) {
            s.push(1); s.push(2);
            System.out.println("Top: " + s.top());   // 2
            System.out.println("Pop: " + s.pop());   // 2
            System.out.println("Empty: " + s.empty()); // false
        }

        // Test 2: LIFO order
        System.out.println("\n--- Test 2: LIFO order ---");
        if (stack instanceof MyStackTwoQueues s) {
            s.push(10); s.push(20); s.push(30);
            System.out.println("Pop: " + s.pop()); // 30
            System.out.println("Pop: " + s.pop()); // 20
            System.out.println("Pop: " + s.pop()); // 10 — only 1 remains from test 1
        } else if (stack instanceof MyStackOneQueue s) {
            s.push(10); s.push(20); s.push(30);
            System.out.println("Pop: " + s.pop()); // 30
            System.out.println("Pop: " + s.pop()); // 20
            System.out.println("Pop: " + s.pop()); // 10
        }

        // Test 3: Interleaved push/pop
        System.out.println("\n--- Test 3: Interleaved ---");
        if (stack instanceof MyStackTwoQueues s) {
            s.push(1); s.push(2);
            System.out.println("Pop: " + s.pop()); // 2
            s.push(3);
            System.out.println("Top: " + s.top()); // 3
            System.out.println("Pop: " + s.pop()); // 3
            System.out.println("Pop: " + s.pop()); // 1
        } else if (stack instanceof MyStackOneQueue s) {
            s.push(1); s.push(2);
            System.out.println("Pop: " + s.pop()); // 2
            s.push(3);
            System.out.println("Top: " + s.top()); // 3
            System.out.println("Pop: " + s.pop()); // 3
            System.out.println("Pop: " + s.pop()); // 1
        }

        // Test 4: Empty check
        System.out.println("\n--- Test 4: Empty ---");
        if (stack instanceof MyStackTwoQueues s) {
            System.out.println("Empty: " + s.empty()); // true
            s.push(5);
            System.out.println("Empty: " + s.empty()); // false
            s.pop();
            System.out.println("Empty: " + s.empty()); // true
        } else if (stack instanceof MyStackOneQueue s) {
            System.out.println("Empty: " + s.empty()); // true
            s.push(5);
            System.out.println("Empty: " + s.empty()); // false
            s.pop();
            System.out.println("Empty: " + s.empty()); // true
        }
    }
}