package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 142: Linked List Cycle II
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * Note that pos is not passed as a parameter.
 * <p>
 * Do not modify the linked list.
 * <p>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycleII {

    /**
     * Approach 1: Floyd's Cycle-Finding Algorithm (Optimal)
     * <p>
     * This algorithm uses two phases:
     * Phase 1: Detect if a cycle exists using a slow and a fast pointer. If they meet, a cycle is present.
     * Phase 2: Find the cycle's starting node. After the pointers meet, reset one pointer (e.g., `slow`) to the head of the list.
     *          Then, move both `slow` and `fast` pointers one step at a time. The node where they meet again is the start of the cycle.
     * <p>
     * Time: O(n)
     * Space: O(1)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) { // Cycle detected
                // Phase 2: Find the entrance of the cycle
                slow = head; // Reset slow pointer to the head
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // The meeting point is the start of the cycle
            }
        }

        return null; // No cycle found
    }

    /**
     * Approach 2: Using a HashSet
     * <p>
     * Time: O(n)
     * Space: O(n)
     */
    public ListNode detectCycleWithSet(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (seen.contains(current)) {
                return current; // The first node we encounter again is the start of the cycle
            }
            seen.add(current);
            current = current.next;
        }

        return null; // No cycle
    }

    public static void main(String[] args) {
        LinkedListCycleII solution = new LinkedListCycleII();

        // Test Case 1: Cycle exists
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node_4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node0;
        node0.next = node_4;
        node_4.next = node2; // Cycle back to node with value 2
        System.out.println("Test Case 1 (Cycle starts at node 2):");
        ListNode result1 = solution.detectCycle(head1);
        System.out.println("  - Floyd's: " + (result1 != null ? result1.val : "null")); // Expected: 2
        ListNode result1b = solution.detectCycleWithSet(head1);
        System.out.println("  - HashSet: " + (result1b != null ? result1b.val : "null")); // Expected: 2
        System.out.println("--------------------");

        // Test Case 2: No cycle
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        System.out.println("Test Case 2 (No cycle):");
        ListNode result2 = solution.detectCycle(head2);
        System.out.println("  - Floyd's: " + (result2 != null ? result2.val : "null")); // Expected: null
        ListNode result2b = solution.detectCycleWithSet(head2);
        System.out.println("  - HashSet: " + (result2b != null ? result2b.val : "null")); // Expected: null
    }
}
