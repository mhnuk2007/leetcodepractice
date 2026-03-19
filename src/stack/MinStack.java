package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * MinStack — supports push, pop, top, and getMin in O(1) time.
 *
 * Approach: Each Container record holds the value and the running minimum
 * at that point. On push, minimum is Math.min(val, current top's minimum).
 * On pop, the previous minimum is automatically restored.
 *
 * Time Complexity : O(1) for all operations
 * Space Complexity: O(n)
 */
public class MinStack {

    private record Container(int val, int min) {}

    private final Deque<Container> stack;

    public MinStack() {
        this.stack = new ArrayDeque<>();
    }

    public void push(int val) {
        int min = stack.isEmpty() ? val : Math.min(val, stack.peek().min());
        stack.push(new Container(val, min));
    }

    public void pop() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack underflow — cannot pop");
        stack.pop();
    }

    public int top() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack is empty — cannot top");
        return stack.peek().val();
    }

    public int getMin() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack is empty — cannot getMin");
        return stack.peek().min();
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