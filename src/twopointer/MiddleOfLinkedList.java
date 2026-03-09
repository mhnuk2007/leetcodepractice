package twopointer;

/**
 * LeetCode 876: Middle of the Linked List
 *
 * Given the head of a singly linked list, return the middle node.
 * If there are two middle nodes, return the second one.
 *
 * Approach: Fast-Slow Pointer (Floyd's Algorithm)
 * - Slow moves 1 step, Fast moves 2 steps
 * - When fast reaches end, slow is at middle
 *
 * Time: O(n) - single pass through list
 * Space: O(1) - only two pointers
 */
public class MiddleOfLinkedList {

    /**
     * Finds the middle node using fast-slow pointers.
     *
     * For odd length (n=5): positions 0,1,2,3,4 → middle is 2
     * For even length (n=6): positions 0,1,2,3,4,5 → middle is 3 (second middle)
     *
     * @param head The head of the linked list
     * @return The middle node (or second middle if even length)
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Fast moves 2x speed of slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;  // Slow is at middle
    }

    public static void main(String[] args) {
        MiddleOfLinkedList solution = new MiddleOfLinkedList();

        // Test Case 1: Odd length (5 nodes)
        System.out.println("=== Test Case 1: Odd Length ===");
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(head1);
        ListNode middle1 = solution.middleNode(head1);
        System.out.print("Middle:   ");
        printList(middle1);
        System.out.println("Expected: 3 -> 4 -> 5 -> null\n");

        // Test Case 2: Even length (6 nodes)
        System.out.println("=== Test Case 2: Even Length ===");
        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Original: ");
        printList(head2);
        ListNode middle2 = solution.middleNode(head2);
        System.out.print("Middle:   ");
        printList(middle2);
        System.out.println("Expected: 4 -> 5 -> 6 -> null\n");

        // Test Case 3: Single node
        System.out.println("=== Test Case 3: Single Node ===");
        ListNode head3 = createList(new int[]{1});
        System.out.print("Original: ");
        printList(head3);
        ListNode middle3 = solution.middleNode(head3);
        System.out.print("Middle:   ");
        printList(middle3);
        System.out.println("Expected: 1 -> null\n");

        // Test Case 4: Two nodes
        System.out.println("=== Test Case 4: Two Nodes ===");
        ListNode head4 = createList(new int[]{1, 2});
        System.out.print("Original: ");
        printList(head4);
        ListNode middle4 = solution.middleNode(head4);
        System.out.print("Middle:   ");
        printList(middle4);
        System.out.println("Expected: 2 -> null\n");

        // Test Case 5: Large odd list
        System.out.println("=== Test Case 5: Seven Nodes ===");
        ListNode head5 = createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.print("Original: ");
        printList(head5);
        ListNode middle5 = solution.middleNode(head5);
        System.out.print("Middle:   ");
        printList(middle5);
        System.out.println("Expected: 4 -> 5 -> 6 -> 7 -> null\n");

        // Test Case 6: Three nodes
        System.out.println("=== Test Case 6: Three Nodes ===");
        ListNode head6 = createList(new int[]{1, 2, 3});
        System.out.print("Original: ");
        printList(head6);
        ListNode middle6 = solution.middleNode(head6);
        System.out.print("Middle:   ");
        printList(middle6);
        System.out.println("Expected: 2 -> 3 -> null\n");
    }

    /**
     * Helper method to create a linked list from an array
     */
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

    /**
     * Helper method to print a linked list from a given node
     */
    private static void printList(ListNode node) {
        ListNode current = node;

        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }

        System.out.println(" -> null");
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}