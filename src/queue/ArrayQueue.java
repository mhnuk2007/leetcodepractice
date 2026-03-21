package queue;

/**
 * Queue implementation using a circular array.
 *
 * Approach: Use front and rear pointers that wrap around using modulo.
 * This avoids shifting elements and gives true O(1) enqueue and dequeue.
 *
 * front → index of the first element
 * rear  → index of the next empty slot
 *
 * Time Complexity : O(1) for all operations
 * Space Complexity: O(n) where n = capacity
 */
public class ArrayQueue {

    private final int[] arr;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == capacity; }

    public int size() { return size; }

    // Add to rear — O(1)
    public void enqueue(int val) {
        if (isFull()) {
            System.out.println("Queue is full — cannot enqueue: " + val);
            return;
        }
        arr[rear] = val;
        rear = (rear + 1) % capacity; // wrap around
        size++;
    }

    // Remove from front — O(1)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty — cannot dequeue");
            return Integer.MIN_VALUE;
        }
        int val = arr[front];
        front = (front + 1) % capacity; // wrap around
        size--;
        return val;
    }

    // Peek front without removing — O(1)
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty — cannot peek");
            return Integer.MIN_VALUE;
        }
        return arr[front];
    }

    public static void main(String[] args) {

        // Test 1: Enqueue and peek
        System.out.println("=== Test 1: Enqueue and peek ===");
        ArrayQueue q = new ArrayQueue(5);
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

        // Test 3: Wrap-around (circular behaviour)
        System.out.println("\n=== Test 3: Wrap-around ===");
        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60); // rear wraps to index 0
        q.enqueue(70); // rear wraps to index 1
        System.out.println("Size: "    + q.size());    // 5
        System.out.println("Dequeue: " + q.dequeue()); // 30
        System.out.println("Dequeue: " + q.dequeue()); // 40
        System.out.println("Dequeue: " + q.dequeue()); // 50

        // Test 4: isEmpty and isFull
        System.out.println("\n=== Test 4: isEmpty and isFull ===");
        ArrayQueue small = new ArrayQueue(2);
        small.enqueue(1);
        small.enqueue(2);
        System.out.println("isFull: "  + small.isFull());  // true
        System.out.println("isEmpty: " + small.isEmpty()); // false

        // Test 5: Overflow
        System.out.println("\n=== Test 5: Overflow ===");
        small.enqueue(3); // Queue is full — cannot enqueue: 3

        // Test 6: Underflow
        System.out.println("\n=== Test 6: Underflow ===");
        ArrayQueue empty = new ArrayQueue(3);
        empty.dequeue(); // Queue is empty — cannot dequeue
        empty.peek();    // Queue is empty — cannot peek
    }
}