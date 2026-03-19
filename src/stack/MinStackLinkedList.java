package stack;

/**
 * MinStack — supports push, pop, top, and getMin in O(1) time.
 *
 * Approach: Two parallel linked lists — head tracks actual values,
 * minHead tracks the running minimum at each level. Both grow and
 * shrink together on every push and pop.
 *
 * Time Complexity : O(1) for all operations
 * Space Complexity: O(n) — two nodes allocated per push
 */
public class MinStackLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node minHead;
    private int size;

    public MinStackLinkedList() {
        this.head = null;
        this.minHead = null;
        this.size = 0;
    }

    public void push(int val) {
        head = new Node(val, head);
        if (minHead == null || val <= minHead.data)
            minHead = new Node(val, minHead);
        else
            minHead = new Node(minHead.data, minHead);
        size++;
    }

    public void pop() {
        if (head == null)
            throw new RuntimeException("Stack underflow — cannot pop");
        head = head.next;
        minHead = minHead.next;
        size--;
    }

    public int top() {
        if (head == null)
            throw new RuntimeException("Stack is empty — cannot top");
        return head.data;
    }

    public int getMin() {
        if (minHead == null)
            throw new RuntimeException("Stack is empty — cannot getMin");
        return minHead.data;
    }

    public static void main(String[] args) {

        MinStack stack = new MinStack();

        // Test 1: Basic getMin tracking
        System.out.println("=== Test 1: getMin tracking ===");
        stack.push(-2);
        System.out.println("getMin: " + stack.getMin()); // -2
        stack.push(0);
        System.out.println("getMin: " + stack.getMin()); // -2
        stack.push(-3);
        System.out.println("getMin: " + stack.getMin()); // -3

        // Test 2: getMin restores correctly after pop
        System.out.println("\n=== Test 2: getMin after pop ===");
        stack.pop();
        System.out.println("getMin: " + stack.getMin()); // -2
        System.out.println("top:    " + stack.top());    // 0
        stack.pop();
        System.out.println("getMin: " + stack.getMin()); // -2
        System.out.println("top:    " + stack.top());    // -2

        // Test 3: Duplicate mins
        System.out.println("\n=== Test 3: Duplicate mins ===");
        MinStack s2 = new MinStack();
        s2.push(1);
        s2.push(1);
        s2.push(1);
        System.out.println("getMin: " + s2.getMin()); // 1
        s2.pop();
        System.out.println("getMin: " + s2.getMin()); // 1

        // Test 4: Increasing sequence
        System.out.println("\n=== Test 4: Increasing sequence ===");
        MinStack s3 = new MinStack();
        s3.push(1);
        s3.push(2);
        s3.push(3);
        System.out.println("getMin: " + s3.getMin()); // 1
        s3.pop();
        System.out.println("getMin: " + s3.getMin()); // 1

        // Test 5: Underflow guard
        System.out.println("\n=== Test 5: Underflow ===");
        MinStack s4 = new MinStack();
        try { s4.pop(); }
        catch (RuntimeException e) { System.out.println("Caught: " + e.getMessage()); }
        try { s4.top(); }
        catch (RuntimeException e) { System.out.println("Caught: " + e.getMessage()); }
        try { s4.getMin(); }
        catch (RuntimeException e) { System.out.println("Caught: " + e.getMessage()); }
    }
}