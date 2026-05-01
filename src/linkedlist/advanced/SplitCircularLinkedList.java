package linkedlist;

/**
 * GeeksforGeeks Problem: Split a Circular Linked List into two halves
 * Difficulty: Easy
 * <p>
 * Given a Circular linked list, the task is to split it into two Circular Linked lists.
 * If there are an odd number of nodes in the given circular linked list, then out of the resulting two halved lists,
 * the first list should have one node more than the second list.
 * <p>
 * Examples:
 * <p>
 * Input: LinkedList: 10->4->9
 * Output: 10->4, 9
 * Explanation: After dividing the linked list into 2 parts, the first part contains 10, 4 and the second part contains only 9.
 * <p>
 * Input: LinkedList: 10->4->9->10
 * Output: 10->4, 9->10
 * Explanation: After dividing the linked list into 2 parts, the first part contains 10, 4 and the second part contains 9, 10.
 * <p>
 * Constraints:
 * 2 <= number of nodes <= 105
 * 1 <= node->data <= 103
 */
public class SplitCircularLinkedList {

    // --- Inner Node class: avoids package-level naming conflicts ---
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // --- Java record replaces the verbose ListContainer class (Java 16+) ---
    record ListContainer(Node head1, Node head2) {}

    /**
     * Splits a circular linked list into two halves using the fast and slow pointer method.
     * Time Complexity:  O(n)
     * Space Complexity: O(1)
     *
     * @param head The head of the original circular linked list.
     * @return A ListContainer record containing the heads of the two new circular linked lists.
     */
    public ListContainer splitList(Node head) {
        // Handle edge cases where splitting is not meaningful or possible.
        if (head == null || head.next == head) {
            return new ListContainer(head, null);
        }

        // 1. Start slow at head, fast one step ahead.
        Node slow = head;
        Node fast = head.next;

        // Advance until fast reaches the last or second-to-last node.
        // fast moves 1 or 2 steps per iteration (conditional 2-step advance).
        while (fast != head && fast.next != head) {
            slow = slow.next;
            fast = fast.next;

            if (fast.next != head) {
                fast = fast.next;
            }
        }

        // 2. Save head2 BEFORE any relinking.
        Node head2 = slow.next;

        // 3. Close the second half into a circular list.
        //    fast is at the last node of the original list.
        fast.next = head2;

        // 4. Close the first half into a circular list.
        slow.next = head;

        return new ListContainer(head, head2);
    }

    // -------------------------------------------------------------------------
    // Main — test cases
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        SplitCircularLinkedList solution = new SplitCircularLinkedList();

        System.out.println("--- Test Case 1: Odd number of nodes (3) ---");
        Node head1 = createCircularList(new int[]{10, 4, 9});
        System.out.print("Original:    ");
        printCircularList(head1);
        ListContainer r1 = solution.splitList(head1);
        System.out.print("First half:  ");
        printCircularList(r1.head1()); // Expected: 10 -> 4 -> (10)
        System.out.print("Second half: ");
        printCircularList(r1.head2()); // Expected: 9 -> (9)

        System.out.println("\n--- Test Case 2: Even number of nodes (4) ---");
        Node head2 = createCircularList(new int[]{10, 4, 9, 10});
        System.out.print("Original:    ");
        printCircularList(head2);
        ListContainer r2 = solution.splitList(head2);
        System.out.print("First half:  ");
        printCircularList(r2.head1()); // Expected: 10 -> 4 -> (10)
        System.out.print("Second half: ");
        printCircularList(r2.head2()); // Expected: 9 -> 10 -> (9)

        System.out.println("\n--- Test Case 3: Two nodes ---");
        Node head3 = createCircularList(new int[]{1, 2});
        System.out.print("Original:    ");
        printCircularList(head3);
        ListContainer r3 = solution.splitList(head3);
        System.out.print("First half:  ");
        printCircularList(r3.head1()); // Expected: 1 -> (1)
        System.out.print("Second half: ");
        printCircularList(r3.head2()); // Expected: 2 -> (2)

        System.out.println("\n--- Test Case 4: Odd number of nodes (5) ---");
        Node head4 = createCircularList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original:    ");
        printCircularList(head4);
        ListContainer r4 = solution.splitList(head4);
        System.out.print("First half:  ");
        printCircularList(r4.head1()); // Expected: 1 -> 2 -> 3 -> (1)
        System.out.print("Second half: ");
        printCircularList(r4.head2()); // Expected: 4 -> 5 -> (4)
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Creates a circular linked list from an int array. */
    private static Node createCircularList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        current.next = head; // close the circle
        return head;
    }

    /** Prints a circular linked list as: a -> b -> c -> (a) */
    private static void printCircularList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(" + head.data + ")");
    }
}