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
     * - `next`: Temporarily stores the next node in the original list before the link is reversed.
     * <p>
     * The algorithm iterates through the list, reversing the `next` pointer of each node to point to `prev`,
     * and then advances all three pointers.
     *
     * @param head The head of the linked list.
     * @return The new head of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; // Store the next node
            curr.next = prev;          // Reverse the current node's pointer
            prev = curr;               // Move prev one step forward
            curr = next;               // Move curr one step forward
        }

        return prev; // `prev` will be the new head at the end
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        // Test Case 1: A standard list
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original List 1: ");
        printList(head1);
        ListNode reversed1 = solution.reverseList(head1);
        System.out.print("Reversed List 1: ");
        printList(reversed1); // Expected: 5 -> 4 -> 3 -> 2 -> 1 -> null

        System.out.println("--------------------");

        // Test Case 2: A list with two nodes
        ListNode head2 = createList(new int[]{1, 2});
        System.out.print("Original List 2: ");
        printList(head2);
        ListNode reversed2 = solution.reverseList(head2);
        System.out.print("Reversed List 2: ");
        printList(reversed2); // Expected: 2 -> 1 -> null

        System.out.println("--------------------");

        // Test Case 3: An empty list
        ListNode head3 = createList(new int[]{});
        System.out.print("Original List 3: ");
        printList(head3);
        ListNode reversed3 = solution.reverseList(head3);
        System.out.print("Reversed List 3: ");
        printList(reversed3); // Expected: null
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

    // Definition for singly-linked list.
    // Note: This is often provided by LeetCode. Keeping it here for self-containment.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
