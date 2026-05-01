package linkedlist;

public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();

        // Test Case 1: Remove 2nd from end
        // [1,2,3,4,5] -> [1,2,3,5]
        System.out.println("=== Test Case 1 ===");
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        System.out.println("Original: " + printList(head1));
        head1 = solution.removeNthFromEnd(head1, 2);
        System.out.println("After removing 2nd from end: " + printList(head1));
        System.out.println("Expected: 1 -> 2 -> 3 -> 5\n");

        // Test Case 2: Remove head (1st from end of single node)
        // [1] -> []
        System.out.println("=== Test Case 2 ===");
        ListNode head2 = new ListNode(1);
        System.out.println("Original: " + printList(head2));
        head2 = solution.removeNthFromEnd(head2, 1);
        System.out.println("After removing 1st from end: " + printList(head2));
        System.out.println("Expected: (empty)\n");

        // Test Case 3: Remove head (2nd from end of 2 nodes)
        // [1,2] -> [2]
        System.out.println("=== Test Case 3 ===");
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        System.out.println("Original: " + printList(head3));
        head3 = solution.removeNthFromEnd(head3, 2);
        System.out.println("After removing 2nd from end: " + printList(head3));
        System.out.println("Expected: 2\n");

        // Test Case 4: Remove last node
        // [1,2,3] -> [1,2]
        System.out.println("=== Test Case 4 ===");
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        head4.next.next = new ListNode(3);
        System.out.println("Original: " + printList(head4));
        head4 = solution.removeNthFromEnd(head4, 1);
        System.out.println("After removing 1st from end: " + printList(head4));
        System.out.println("Expected: 1 -> 2\n");

        // Test Case 5: Two-Pointer approach comparison
        System.out.println("=== Test Case 5: Two-Pointer Approach ===");
        ListNode head5 = new ListNode(1);
        head5.next = new ListNode(2);
        head5.next.next = new ListNode(3);
        head5.next.next.next = new ListNode(4);
        head5.next.next.next.next = new ListNode(5);
        System.out.println("Original: " + printList(head5));
        head5 = solution.removeNthFromEndTwoPointer(head5, 2);
        System.out.println("Two-Pointer result: " + printList(head5));
        System.out.println("Expected: 1 -> 2 -> 3 -> 5\n");
    }

    /**
     * Approach 1: Two-Pass
     *
     * Algorithm:
     * 1. First pass: Calculate length
     * 2. Second pass: Navigate to (len - n - 1)th node
     * 3. Remove the nth node from end
     *
     * Time: O(L) - two passes
     * Space: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        int len = 0;

        // First pass: find length
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        // Edge case: remove head
        if (n == len) {
            return head.next;
        }

        // Second pass: find node before target
        int target = len - n - 1;
        ListNode temp = head;

        for (int i = 0; i < target; i++) {
            temp = temp.next;
        }

        // Remove the target node
        temp.next = temp.next.next;

        return head;
    }

    /**
     * Approach 2: One-Pass with Two Pointers (OPTIMAL)
     *
     * Algorithm:
     * 1. Use dummy node to handle edge cases
     * 2. Move fast pointer n steps ahead
     * 3. Move both fast and slow until fast reaches end
     * 4. Slow will be just before the node to remove
     *
     * Time: O(L) - single pass
     * Space: O(1)
     */
    public ListNode removeNthFromEndTwoPointer(ListNode head, int n) {
        // Dummy node to handle removing head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node
        slow.next = slow.next.next;

        return dummy.next;
    }

    /**
     * Helper method to print linked list
     */
    private static String printList(ListNode head) {
        if (head == null) return "(empty)";

        StringBuilder sb = new StringBuilder();
        ListNode curr = head;

        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }

        return sb.toString();
    }
}
