package queue;

/**
 * LeetCode: 622 — Design Circular Queue
 *
 * Problem: Design a circular queue supporting enQueue, deQueue,
 * Front, Rear, isEmpty, isFull operations.
 *
 * Approach: Circular array with front and rear pointers that wrap
 * around using modulo. size tracks element count — distinguishes
 * full from empty without wasting a slot.
 *
 * front → index of first element
 * rear  → index of next empty slot
 *
 * Time Complexity : O(1) for all operations
 * Space Complexity: O(k) where k = capacity
 */
public class MyCircularQueue {

    private final int[] arr;
    private final int capacity;
    private int front;
    private int rear;
    private int size;

    public MyCircularQueue(int k) {
        this.capacity = k;
        this.arr = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        arr[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return arr[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {

        // Test 1: Standard enQueue and deQueue
        System.out.println("=== Test 1: Standard operations ===");
        MyCircularQueue q = new MyCircularQueue(3);
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
        MyCircularQueue q2 = new MyCircularQueue(2);
        System.out.println("isEmpty: " + q2.isEmpty()); // true
        q2.enQueue(10);
        q2.enQueue(20);
        System.out.println("isFull: " + q2.isFull());   // true

        // Test 3: Wrap-around behaviour
        System.out.println("\n=== Test 3: Wrap-around ===");
        MyCircularQueue q3 = new MyCircularQueue(3);
        q3.enQueue(1);
        q3.enQueue(2);
        q3.enQueue(3);
        q3.deQueue();           // removes 1, front wraps
        q3.deQueue();           // removes 2
        q3.enQueue(4);          // rear wraps to index 0
        q3.enQueue(5);          // rear wraps to index 1
        System.out.println("Front: " + q3.Front()); // 3
        System.out.println("Rear:  " + q3.Rear());  // 5

        // Test 4: Dequeue on empty
        System.out.println("\n=== Test 4: Dequeue on empty ===");
        MyCircularQueue q4 = new MyCircularQueue(2);
        System.out.println(q4.deQueue()); // false
        System.out.println("Front: " + q4.Front()); // -1
        System.out.println("Rear:  " + q4.Rear());  // -1
    }
}