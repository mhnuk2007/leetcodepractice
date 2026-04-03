package recursion;
// REVISED (already solved previously in linkedlist)
/**
 * LeetCode 206 - Reverse Linked List
 *
 * Problem:
 *   Given the head of a singly linked list, reverse the list and return
 *   the new head.
 *
 * Approach 1: Iterative (three pointers)
 *   Track prev, curr, next. For each node, point curr.next to prev,
 *   then advance all three pointers forward. Return prev as new head.
 *
 * Approach 2: Recursive
 *   Recurse to the end of the list. On the way back, reverse the link:
 *   head.next.next = head, head.next = null.
 *   Return newHead (last node) all the way up as the new head.
 *
 * Example:
 *   Input:  1 -> 2 -> 3 -> 4 -> 5
 *   Output: 5 -> 4 -> 3 -> 2 -> 1
 *
 * Time  : O(n) — each node visited once
 * Space : O(1) — iterative; O(n) — recursive (call stack)
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        // Test 1: standard case — iterative
        ListNode head1 = buildList(1, 2, 3, 4, 5);
        System.out.print("Original:   "); printList(head1);
        System.out.print("Iterative:  "); printList(reverseList(head1));
        // Expected: 5 -> 4 -> 3 -> 2 -> 1 -> null

        // Test 2: standard case — recursive
        ListNode head2 = buildList(1, 2, 3, 4, 5);
        System.out.print("Recursive:  "); printList(reverseListRec(head2));
        // Expected: 5 -> 4 -> 3 -> 2 -> 1 -> null

        // Test 3: single node
        ListNode head3 = buildList(1);
        System.out.print("Single:     "); printList(reverseList(head3));
        // Expected: 1 -> null

        // Test 4: empty list
        System.out.print("Empty:      "); printList(reverseList(null));
        // Expected: null

        // Test 5: two nodes
        ListNode head4 = buildList(1, 2);
        System.out.print("Two nodes:  "); printList(reverseList(head4));
        // Expected: 2 -> 1 -> null


        // Test 6: standard case — recursive
        ListNode head5 = buildList(1, 2, 3, 4, 5,6,7);
        System.out.print("Recursive2:  "); printList(reverseListRec2(head5));
        // Expected: 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> null
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;

        }
        return prev;
    }

    public static ListNode reverseListRec(ListNode head) {
        if (head == null || head.next == null) return head;                // base case

        ListNode newHead = reverseListRec(head.next);                      // recurse to end
        head.next.next = head;                                             // reverse link
        head.next = null;                                                  // cut old link

        return newHead;                                                    // propagate new head
    }

    public static ListNode reverseListRec2(ListNode head){
        return helper(null, head);
    }

    private static ListNode helper(ListNode prev, ListNode curr){
        if (curr == null) return prev;

        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;

        return helper(prev, curr);
    }



    private static ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : vals) { curr.next = new ListNode(val); curr = curr.next; }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) { System.out.print(head.val + " -> "); head = head.next; }
        System.out.println("null");
    }
}