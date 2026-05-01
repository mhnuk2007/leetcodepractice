package linkedlist;

/**
 * LeetCode 206: Reverse Linked List
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * Example 3:
 * Input: head = []
 * Output: []
 */
public class ReverseLinkedList {

    /**
     * Reverses a singly linked list iteratively.
     * <p>
     * This approach uses three pointers:
     * - `prev`: Tracks the previous node, which will become the next node in the reversed list. Starts as null.
     * - `curr`: The current node being processed. Starts as the head.
     * - `nextTemp`: Temporarily stores the next node in the original list before the link is reversed.
     *
     * @param head The head of the linked list.
     * @return The new head of the reversed linked list.
     */
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next; // Store the next node
            curr.next = prev;              // Reverse the current node's pointer
            prev = curr;                   // Move prev one step forward
            curr = nextTemp;               // Move curr one step forward
        }

        return prev; // `prev` will be the new head at the end
    }

    /**
     * Reverses a singly linked list recursively.
     * <p>
     * The base case is an empty list or a list with a single node, where the list is already reversed.
     * For other cases, we recursively call the function on the rest of the list (`head.next`).
     * When the recursion unwinds, `newHead` is the head of the fully reversed sub-list.
     * We then reverse the pointer of the current node: `head.next.next = head`, and sever the old forward link: `head.next = null`.
     *
     * @param head The head of the linked list.
     * @return The new head of the reversed linked list.
     */
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: if head is null or it's the last node
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);

        // After the above call, head.next is the last node of the original list.
        // We want its `next` pointer to point back to `head`.
        head.next.next = head;
        head.next = null; // Break the original forward link

        return newHead;
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        System.out.println("--- Testing Iterative Solution ---");
        runTestCases(solution, "iterative");

        System.out.println("\n--- Testing Recursive Solution ---");
        runTestCases(solution, "recursive");
    }

    private static void runTestCases(ReverseLinkedList solution, String method) {
        // Test Case 1: A standard list
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original List 1: ");
        printList(head1);
        ListNode reversed1 = method.equals("iterative") ? solution.reverseListIterative(head1) : solution.reverseListRecursive(head1);
        System.out.print("Reversed List 1: ");
        printList(reversed1);

        System.out.println("--------------------");

        // Test Case 2: A list with two nodes
        ListNode head2 = createList(new int[]{1, 2});
        System.out.print("Original List 2: ");
        printList(head2);
        ListNode reversed2 = method.equals("iterative") ? solution.reverseListIterative(head2) : solution.reverseListRecursive(head2);
        System.out.print("Reversed List 2: ");
        printList(reversed2);

        System.out.println("--------------------");

        // Test Case 3: An empty list
        ListNode head3 = createList(new int[]{});
        System.out.print("Original List 3 (Empty): ");
        printList(head3);
        ListNode reversed3 = method.equals("iterative") ? solution.reverseListIterative(head3) : solution.reverseListRecursive(head3);
        System.out.print("Reversed List 3: ");
        printList(reversed3);

        System.out.println("--------------------");

        // Test Case 4: A single node list
        ListNode head4 = createList(new int[]{99});
        System.out.print("Original List 4 (Single Node): ");
        printList(head4);
        ListNode reversed4 = method.equals("iterative") ? solution.reverseListIterative(head4) : solution.reverseListRecursive(head4);
        System.out.print("Reversed List 4: ");
        printList(reversed4);
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to print a linked list from a given node
    private static void printList(ListNode node) {
        ListNode current = node;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
