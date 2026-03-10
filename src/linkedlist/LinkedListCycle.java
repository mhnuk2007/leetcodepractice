package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 141: Linked List Cycle
 * <p>
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * <p>
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 */
public class LinkedListCycle {

    /**
     * Approach 1: Floyd's Cycle Detection Algorithm (Tortoise and Hare)
     * This is the optimal approach in terms of space complexity.
     * <p>
     * Time: O(n) - In the worst case, each node is visited a constant number of times.
     * Space: O(1) - We only use two pointers.
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // Pointers met, a cycle is detected.
            }
        }

        return false; // Fast pointer reached the end, no cycle.
    }

    /**
     * Approach 2: Using a HashSet
     * This approach is intuitive but uses extra space to store visited nodes.
     * <p>
     * Time: O(n) - We visit each node once.
     * Space: O(n) - In the worst case (no cycle), we store all n nodes in the set.
     */
    public boolean hasCycleWithSet(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (seen.contains(current)) {
                return true; // If we've seen this node before, there's a cycle.
            }
            seen.add(current);
            current = current.next;
        }

        return false; // Reached the end, no cycle.
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test Case 1: Cycle exists
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node_4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node0;
        node0.next = node_4;
        node_4.next = node2; // Cycle back to node with value 2
        System.out.println("Test Case 1 (Cycle exists):");
        System.out.println("  - Floyd's: " + solution.hasCycle(head1));      // Expected: true
        System.out.println("  - HashSet: " + solution.hasCycleWithSet(head1));    // Expected: true
        System.out.println("--------------------");

        // Test Case 2: No cycle
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        System.out.println("Test Case 2 (No cycle):");
        System.out.println("  - Floyd's: " + solution.hasCycle(head2));      // Expected: false
        System.out.println("  - HashSet: " + solution.hasCycleWithSet(head2));    // Expected: false
        System.out.println("--------------------");

        // Test Case 3: Empty list
        System.out.println("Test Case 3 (Empty list):");
        System.out.println("  - Floyd's: " + solution.hasCycle(null));       // Expected: false
        System.out.println("  - HashSet: " + solution.hasCycleWithSet(null));     // Expected: false
        System.out.println("--------------------");

        // Test Case 4: Single node with self-loop
        ListNode head4 = new ListNode(1);
        head4.next = head4;
        System.out.println("Test Case 4 (Self-loop):");
        System.out.println("  - Floyd's: " + solution.hasCycle(head4));      // Expected: true
        System.out.println("  - HashSet: " + solution.hasCycleWithSet(head4));    // Expected: true
    }
}
