package stack;

import java.util.ArrayList;

public class ArrayListStack {

    private final ArrayList<Integer> list;

    public ArrayListStack() {
        this.list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void push(int val) {
        list.add(val);
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow — cannot pop");
            return Integer.MIN_VALUE;
        }
        return list.remove(list.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty — cannot peek");
            return Integer.MIN_VALUE;
        }
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {

        ArrayListStack stack = new ArrayListStack();

        // Test 1: Push and peek
        System.out.println("=== Test 1: Push and peek ===");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Peek: " + stack.peek()); // 30

        // Test 2: Pop sequence
        System.out.println("\n=== Test 2: Pop sequence ===");
        System.out.println("Pop: " + stack.pop());   // 30
        System.out.println("Peek: " + stack.peek()); // 20
        System.out.println("Pop: " + stack.pop());   // 20
        System.out.println("Peek: " + stack.peek()); // 10
        System.out.println("Pop: " + stack.pop());   // 10

        // Test 3: isEmpty and size on empty stack
        System.out.println("\n=== Test 3: isEmpty and size ===");
        System.out.println("isEmpty: " + stack.isEmpty()); // true
        System.out.println("Size: " + stack.size());       // 0

        // Test 4: Underflow — pop and peek on empty stack
        System.out.println("\n=== Test 4: Underflow ===");
        stack.peek(); // Stack is empty — cannot peek
        stack.pop();  // Stack underflow — cannot pop

        // Test 5: No overflow — ArrayList grows automatically
        System.out.println("\n=== Test 5: No overflow ===");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6); // no overflow — list auto-resizes
        System.out.println("Size: " + stack.size());       // 6
        System.out.println("Peek: " + stack.peek());       // 6
    }
}