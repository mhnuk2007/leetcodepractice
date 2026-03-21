package queue;

/**
 * LeetCode 622 — Design Circular Queue (LinkedList-based)
 *
 * Approach: head = Front, tail = Rear. enQueue appends at tail,
 * deQueue advances head. Capacity enforced via size check.
 * No modulo needed — circularity is logical, not physical.
 *
 * Time Complexity : O(1) for all operations
 * Space Complexity: O(k) where k = capacity
 */
public class MyCircularQueueLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; // front
    private Node tail; // rear
    private int size;
    private final int capacity;

    public MyCircularQueueLinkedList(int k) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = head.next;
        if (head == null) tail = null; // queue became empty — reset tail
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return head.data;
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return tail.data; // tail always points to last node — no modulo needed
    }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == capacity; }

    public static void main(String[] args) {

        // Test 1: Standard enQueue and deQueue
        System.out.println("=== Test 1: Standard operations ===");
        MyCircularQueueLinkedList q = new MyCircularQueueLinkedList(3);
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
        MyCircularQueueLinkedList q2 = new MyCircularQueueLinkedList(2);
        System.out.println("isEmpty: " + q2.isEmpty()); // true
        q2.enQueue(10);
        q2.enQueue(20);
        System.out.println("isFull: " + q2.isFull());   // true

        // Test 3: Dequeue then enqueue — capacity restored
        System.out.println("\n=== Test 3: Dequeue then enqueue ===");
        MyCircularQueueLinkedList q3 = new MyCircularQueueLinkedList(3);
        q3.enQueue(1);
        q3.enQueue(2);
        q3.enQueue(3);
        q3.deQueue();           // removes 1
        q3.deQueue();           // removes 2
        q3.enQueue(4);          // capacity slot freed — accepted
        q3.enQueue(5);          // capacity slot freed — accepted
        System.out.println("Front: " + q3.Front()); // 3
        System.out.println("Rear:  " + q3.Rear());  // 5

        // Test 4: Dequeue on empty
        System.out.println("\n=== Test 4: Dequeue on empty ===");
        MyCircularQueueLinkedList q4 = new MyCircularQueueLinkedList(2);
        System.out.println(q4.deQueue());            // false
        System.out.println("Front: " + q4.Front()); // -1
        System.out.println("Rear:  " + q4.Rear());  // -1

        // Test 5: Drain to empty then refill
        System.out.println("\n=== Test 5: Drain and refill ===");
        MyCircularQueueLinkedList q5 = new MyCircularQueueLinkedList(3);
        q5.enQueue(10);
        q5.enQueue(20);
        q5.deQueue();
        q5.deQueue();
        System.out.println("isEmpty: " + q5.isEmpty()); // true
        q5.enQueue(30);
        System.out.println("Front: " + q5.Front());     // 30
        System.out.println("Rear:  " + q5.Rear());      // 30
    }
}