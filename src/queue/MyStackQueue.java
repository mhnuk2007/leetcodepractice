package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 232 — Implement Queue using Stacks
 *
 * Problem: Implement a FIFO queue using only two stacks.
 * Support push, pop, peek, and empty operations.
 *
 * Approach: Two stacks — inbox and outbox.
 *   push  → always push to inbox
 *   pop   → if outbox is empty, drain inbox into outbox first, then pop outbox
 *   peek  → same as pop but don't remove
 *
 * Key insight: draining inbox into outbox reverses the order — so the
 * oldest element (which should be dequeued first) ends up on top of outbox.
 *
 *   inbox:  [1, 2, 3]  →  drain  →  outbox: [3, 2, 1]
 *                                              ↑ top = 1 (oldest) ✓
 *
 * Amortized O(1): each element is pushed once and popped once across
 * both stacks — total 2 operations per element regardless of how many
 * times pop/peek is called.
 *
 * Time Complexity : push O(1), pop O(1) amortized, peek O(1) amortized
 * Space Complexity: O(n)
 */
public class MyStackQueue {

    private final Deque<Integer> inbox;  // push always goes here
    private final Deque<Integer> outbox; // pop and peek always come from here

    public MyStackQueue() {
        this.inbox  = new ArrayDeque<>();
        this.outbox = new ArrayDeque<>();
    }

    // Push to rear — always onto inbox — O(1)
    public void push(int x) {
        inbox.push(x);
    }

    // Remove from front — O(1) amortized
    public int pop() {
        transfer();
        return outbox.pop();
    }

    // Peek front without removing — O(1) amortized
    public int peek() {
        transfer();
        return outbox.peek();
    }

    public boolean empty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    // Drain inbox into outbox only when outbox is empty
    // This preserves FIFO order — oldest element ends up on top of outbox
    private void transfer() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard example
        System.out.println("=== Test 1: LeetCode standard ===");
        MyStackQueue q = new MyStackQueue();
        q.push(1);
        q.push(2);
        System.out.println("Peek: " + q.peek()); // 1 — oldest element
        System.out.println("Pop:  " + q.pop());  // 1
        System.out.println("Empty: " + q.empty()); // false

        // Test 2: FIFO order preserved across mixed push/pop
        System.out.println("\n=== Test 2: FIFO order ===");
        MyStackQueue q2 = new MyStackQueue();
        q2.push(10);
        q2.push(20);
        q2.push(30);
        System.out.println("Pop: " + q2.pop()); // 10
        System.out.println("Pop: " + q2.pop()); // 20
        q2.push(40);
        System.out.println("Pop: " + q2.pop()); // 30
        System.out.println("Pop: " + q2.pop()); // 40

        // Test 3: Transfer happens only once per batch
        System.out.println("\n=== Test 3: Lazy transfer ===");
        MyStackQueue q3 = new MyStackQueue();
        q3.push(1);
        q3.push(2);
        q3.push(3);
        // outbox is empty — transfer happens here
        System.out.println("Pop: " + q3.pop());  // 1 — triggers transfer
        // outbox still has [2, 3] — no transfer needed
        System.out.println("Pop: " + q3.pop());  // 2 — no transfer
        System.out.println("Pop: " + q3.pop());  // 3 — no transfer

        // Test 4: Interleaved push during outbox drain
        System.out.println("\n=== Test 4: Interleaved push ===");
        MyStackQueue q4 = new MyStackQueue();
        q4.push(1);
        q4.push(2);
        System.out.println("Pop: " + q4.pop()); // 1 — triggers transfer, outbox: [2]
        q4.push(3);                              // goes to inbox, outbox still has [2]
        q4.push(4);                              // goes to inbox
        System.out.println("Pop: " + q4.pop()); // 2 — from outbox, no transfer
        System.out.println("Pop: " + q4.pop()); // 3 — outbox empty, transfer inbox→outbox
        System.out.println("Pop: " + q4.pop()); // 4

        // Test 5: Empty check
        System.out.println("\n=== Test 5: Empty ===");
        MyStackQueue q5 = new MyStackQueue();
        System.out.println("Empty: " + q5.empty()); // true
        q5.push(5);
        System.out.println("Empty: " + q5.empty()); // false
        q5.pop();
        System.out.println("Empty: " + q5.empty()); // true
    }
}