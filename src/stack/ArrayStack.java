package stack;

public class ArrayStack {

    private int[] arr;
    private int top;

    public ArrayStack(int capacity) {
        this.arr = new int[capacity];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public int size() {
        return top + 1;
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("Stack overflow — cannot push: " + val);
            return;
        }
        arr[++top] = val;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow — cannot pop");
            return Integer.MIN_VALUE; // sentinel: clearly not a valid value
        }
        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty — cannot peek");
            return Integer.MIN_VALUE;
        }
        return arr[top];
    }

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);

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

        // Test 5: Fill to capacity then overflow
        System.out.println("\n=== Test 5: Overflow ===");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("isFull: " + stack.isFull()); // true
        System.out.println("Peek: " + stack.peek());     // 5
        stack.push(6); // Stack overflow — cannot push: 6
    }
}