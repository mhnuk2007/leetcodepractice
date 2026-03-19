package stack;

public class LinkedListStack {

    private static class Node {
        int data;
        Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void push(int val) {
        head = new Node(val, head);
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow — cannot pop");
            return Integer.MIN_VALUE;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty — cannot peek");
            return Integer.MIN_VALUE;
        }
        return head.data;
    }

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();

        // Test 1: Push and peek
        System.out.println("=== Test 1: Push and peek ===");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Peek: " + stack.peek()); // 30
        System.out.println("Size: " + stack.size()); // 3

        // Test 2: Pop sequence
        System.out.println("\n=== Test 2: Pop sequence ===");
        System.out.println("Pop: "  + stack.pop());  // 30
        System.out.println("Peek: " + stack.peek()); // 20
        System.out.println("Pop: "  + stack.pop());  // 20
        System.out.println("Peek: " + stack.peek()); // 10
        System.out.println("Pop: "  + stack.pop());  // 10

        // Test 3: isEmpty and size after draining
        System.out.println("\n=== Test 3: isEmpty and size ===");
        System.out.println("isEmpty: " + stack.isEmpty()); // true
        System.out.println("Size: "    + stack.size());    // 0

        // Test 4: Underflow
        System.out.println("\n=== Test 4: Underflow ===");
        stack.peek(); // Stack is empty — cannot peek
        stack.pop();  // Stack underflow — cannot pop
    }
}