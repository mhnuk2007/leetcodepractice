package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test Case 1: Cycle exists (pos = 1)
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println("Test 1 (Floyd) - Has cycle: " + solution.hasCycle(head1));        // true

        // Need to recreate for Set method (can't reuse cyclic list safely)
        ListNode head1b = new ListNode(3);
        ListNode node2b = new ListNode(2);
        ListNode node3b = new ListNode(0);
        ListNode node4b = new ListNode(-4);
        head1b.next = node2b;
        node2b.next = node3b;
        node3b.next = node4b;
        node4b.next = node2b;

        System.out.println("Test 1 (Set)   - Has cycle: " + solution.hasCycleWithSet(head1b)); // true

        // Test Case 2: Cycle exists (pos = 0)
        ListNode head2 = new ListNode(1);
        ListNode node2_2 = new ListNode(2);
        head2.next = node2_2;
        node2_2.next = head2;

        // Can't test both methods on same cyclic list - would cause infinite loop
        System.out.println("Test 2 (Floyd) - Has cycle: " + solution.hasCycle(head2));        // true

        // Test Case 3: No cycle [1]
        ListNode head3 = new ListNode(1);
        System.out.println("Test 3 (Floyd) - Has cycle: " + solution.hasCycle(head3));        // false
        System.out.println("Test 3 (Set)   - Has cycle: " + solution.hasCycleWithSet(head3)); // false

        // Test Case 4: No cycle [1, 2, 3, 4]
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        head4.next.next = new ListNode(3);
        head4.next.next.next = new ListNode(4);

        System.out.println("Test 4 (Floyd) - Has cycle: " + solution.hasCycle(head4));        // false

        ListNode head4b = new ListNode(1);
        head4b.next = new ListNode(2);
        head4b.next.next = new ListNode(3);
        head4b.next.next.next = new ListNode(4);

        System.out.println("Test 4 (Set)   - Has cycle: " + solution.hasCycleWithSet(head4b)); // false

        // Test Case 5: Empty list
        System.out.println("Test 5 (Floyd) - Has cycle: " + solution.hasCycle(null));         // false
        System.out.println("Test 5 (Set)   - Has cycle: " + solution.hasCycleWithSet(null));  // false

        // Test Case 6: Self-loop
        ListNode head6 = new ListNode(1);
        head6.next = head6;

        System.out.println("Test 6 (Floyd) - Has cycle: " + solution.hasCycle(head6));        // true

        ListNode head6b = new ListNode(1);
        head6b.next = head6b;

        System.out.println("Test 6 (Set)   - Has cycle: " + solution.hasCycleWithSet(head6b)); // true
    }

    /**
     * Floyd's Cycle Detection Algorithm (Tortoise and Hare)
     *
     * Time: O(n) - visits each node at most twice
     * Space: O(1) - only two pointers
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * HashSet approach for cycle detection
     *
     * Time: O(n) - visit each node once
     * Space: O(n) - store all visited nodes
     */
    public boolean hasCycleWithSet(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode cur = head;

        while (cur != null) {
            if (seen.contains(cur)) {  // Already visited = cycle detected
                return true;
            }
            seen.add(cur);
            cur = cur.next;
        }

        return false;  // Reached end = no cycle
    }
}
