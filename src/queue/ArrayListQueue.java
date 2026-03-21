package queue;

import java.util.ArrayList;

/**
 * Queue implementation using ArrayList.
 *
 * Approach: Rear is the end of the list (enqueue via add()),
 * front is index 0 (dequeue via remove(0)).
 *
 * Time Complexity : enqueue O(1) amortized, dequeue O(n) — shifts all elements
 * Space Complexity: O(n)
 *
 * Note: No overflow possible — ArrayList grows automatically.
 * Trade-off: dequeue is O(n) due to element shifting. Use LinkedListQueue
 * or ArrayDeque for true O(1) dequeue in production.
 */
public class ArrayListQueue {

    private final ArrayList<Integer> list;

    public ArrayListQueue() {
        this.list = new ArrayList<>();
    }

    public boolean isEmpty() { return list.isEmpty(); }

    public int size() { return list.size(); }

    // Add to rear — O(1) amortized
    public void enqueue(int val) {
        list.add(val);
    }

    // Remove from front — O(n) shifts all elements left
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty — cannot dequeue");
            return Integer.MIN_VALUE;
        }
        return list.remove(0);
    }

    // Peek front without removing — O(1)
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty — cannot peek");
            return Integer.MIN_VALUE;
        }
        return list.get(0);
    }

    public static void main(String[] args) {

        // Test 1: Enqueue and peek
        System.out.println("=== Test 1: Enqueue and peek ===");
        ArrayListQueue q = new ArrayListQueue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Peek: " + q.peek()); // 10
        System.out.println("Size: " + q.size()); // 3

        // Test 2: Dequeue sequence — FIFO order
        System.out.println("\n=== Test 2: Dequeue sequence ===");
        System.out.println("Dequeue: " + q.dequeue()); // 10
        System.out.println("Dequeue: " + q.dequeue()); // 20
        System.out.println("Peek: "    + q.peek());    // 30

        // Test 3: Interleaved enqueue/dequeue
        System.out.println("\n=== Test 3: Interleaved ===");
        q.enqueue(40);
        q.enqueue(50);
        System.out.println("Dequeue: " + q.dequeue()); // 30
        System.out.println("Dequeue: " + q.dequeue()); // 40
        System.out.println("Size: "    + q.size());    // 1

        // Test 4: Drain queue
        System.out.println("\n=== Test 4: Drain ===");
        q.enqueue(60);
        q.enqueue(70);
        while (!q.isEmpty())
            System.out.print(q.dequeue() + " "); // 50 60 70
        System.out.println();

        // Test 5: isEmpty after drain
        System.out.println("\n=== Test 5: isEmpty ===");
        System.out.println("isEmpty: " + q.isEmpty()); // true

        // Test 6: Underflow
        System.out.println("\n=== Test 6: Underflow ===");
        q.dequeue(); // Queue is empty — cannot dequeue
        q.peek();    // Queue is empty — cannot peek
    }
}