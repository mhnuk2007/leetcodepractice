package queue;

/**
 * Queue implementation using a custom singly linked list with fixed capacity.
 *
 * Approach: head = front (dequeue here), tail = rear (enqueue here).
 * enqueue: create new node, link at tail — O(1)
 * dequeue: read front data, advance front — O(1)
 *
 * Time Complexity : O(1) for all operations
 * Space Complexity: O(n) where n = capacity
 */
public class LinkedListQueue {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;
    private final int capacity;

    public LinkedListQueue(int capacity) {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.capacity = capacity;
    }

    public void enqueue(int val) {
        if (isFull()) {
            System.out.println("Queue is full — cannot enqueue: " + val);
            return;
        }
        Node newNode = new Node(val);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty — cannot dequeue");
            return Integer.MIN_VALUE;
        }
        int val = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty — cannot peek");
            return Integer.MIN_VALUE;
        }
        return front.data;
    }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == capacity; }

    public int size() { return size; }

    public static void main(String[] args) {

        // Test 1: Enqueue and peek
        System.out.println("=== Test 1: Enqueue and peek ===");
        LinkedListQueue q = new LinkedListQueue(5);
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

        // Test 4: Drain to empty then re-enqueue
        System.out.println("\n=== Test 4: Drain and refill ===");
        System.out.println("Dequeue: " + q.dequeue()); // 50
        System.out.println("isEmpty: " + q.isEmpty()); // true
        q.enqueue(100);
        q.enqueue(200);
        System.out.println("Peek: " + q.peek());       // 100
        System.out.println("Size: " + q.size());       // 2

        // Test 5: isFull and overflow
        System.out.println("\n=== Test 5: Overflow ===");
        LinkedListQueue small = new LinkedListQueue(2);
        small.enqueue(1);
        small.enqueue(2);
        System.out.println("isFull: " + small.isFull()); // true
        small.enqueue(3); // Queue is full — cannot enqueue: 3

        // Test 6: Underflow
        System.out.println("\n=== Test 6: Underflow ===");
        LinkedListQueue empty = new LinkedListQueue(3);
        empty.dequeue(); // Queue is empty — cannot dequeue
        empty.peek();    // Queue is empty — cannot peek
    }
}