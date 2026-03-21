package queue;

import java.util.ArrayList;

/**
 * LeetCode 622 — Design Circular Queue (ArrayList-based)
 *
 * Note: ArrayList cannot truly implement a circular queue because:
 * - isFull() must be enforced manually via a capacity field
 * - dequeue via remove(0) is O(n) — shifts all elements
 * - no wrap-around — front is always index 0
 * - Rear() is simply list.get(list.size()-1) — no modulo needed
 *
 * This is NOT a circular queue internally — it is a bounded linear
 * queue backed by an ArrayList. The LeetCode contract is satisfied
 * from the outside, but the circular array behaviour is gone.
 *
 * Time Complexity : enQueue O(1) amortized, deQueue O(n)
 * Space Complexity: O(k)
 */
public class MyCircularQueueArrayList {

    private final ArrayList<Integer> list;
    private final int capacity;

    public MyCircularQueueArrayList(int k) {
        this.capacity = k;
        this.list = new ArrayList<>(k);
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        list.add(value);         // add to rear — O(1) amortized
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        list.remove(0);          // remove from front — O(n) shifts everything
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return list.get(0);      // always index 0 — no wrap-around
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return list.get(list.size() - 1); // always last index — no modulo
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return list.size() == capacity;  // must enforce manually
    }

    public static void main(String[] args) {

        // Test 1: Standard operations
        System.out.println("=== Test 1: Standard operations ===");
        MyCircularQueueArrayList q = new MyCircularQueueArrayList(3);
        System.out.println(q.enQueue(1)); // true
        System.out.println(q.enQueue(2)); // true
        System.out.println(q.enQueue(3)); // true
        System.out.println(q.enQueue(4)); // false — full
        System.out.println("Rear:  " + q.Rear());    // 3
        System.out.println("Front: " + q.Front());   // 1
        System.out.println(q.deQueue());              // true
        System.out.println(q.enQueue(4));             // true
        System.out.println("Rear:  " + q.Rear());    // 4

        // Test 2: isEmpty and isFull
        System.out.println("\n=== Test 2: isEmpty and isFull ===");
        MyCircularQueueArrayList q2 = new MyCircularQueueArrayList(2);
        System.out.println("isEmpty: " + q2.isEmpty()); // true
        q2.enQueue(10);
        q2.enQueue(20);
        System.out.println("isFull: " + q2.isFull());   // true

        // Test 3: No wrap-around — limitation vs circular array
        System.out.println("\n=== Test 3: Dequeue then enqueue ===");
        MyCircularQueueArrayList q3 = new MyCircularQueueArrayList(3);
        q3.enQueue(1);
        q3.enQueue(2);
        q3.enQueue(3);
        q3.deQueue();    // removes 1, list shifts: [2, 3]
        q3.enQueue(4);   // list: [2, 3, 4] — no wrap, just appends
        System.out.println("Front: " + q3.Front()); // 2
        System.out.println("Rear:  " + q3.Rear());  // 4

        // Test 4: Dequeue on empty
        System.out.println("\n=== Test 4: Dequeue on empty ===");
        MyCircularQueueArrayList q4 = new MyCircularQueueArrayList(2);
        System.out.println(q4.deQueue());            // false
        System.out.println("Front: " + q4.Front()); // -1
        System.out.println("Rear:  " + q4.Rear());  // -1
    }
}