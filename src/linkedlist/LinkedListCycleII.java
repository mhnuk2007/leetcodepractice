package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII {
    public static void main(String[] args) {
        LinkedListCycleII solution = new LinkedListCycleII();

        // Test Case 1: Cycle at position 1
        // [3, 2, 0, -4] → cycle starts at node with value 2
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // Cycle starts at node2

        ListNode result1 = solution.detectCycle(head1);
        System.out.println("Test 1 (Floyd) - Cycle starts at: " +
                (result1 != null ? result1.val : "null"));  // Expected: 2

        // Recreate for HashSet method
        ListNode head1b = new ListNode(3);
        ListNode node2b = new ListNode(2);
        ListNode node3b = new ListNode(0);
        ListNode node4b = new ListNode(-4);
        head1b.next = node2b;
        node2b.next = node3b;
        node3b.next = node4b;
        node4b.next = node2b;

        ListNode result1b = solution.detectCycleWithSet(head1b);
        System.out.println("Test 1 (Set)   - Cycle starts at: " +
                (result1b != null ? result1b.val : "null"));  // Expected: 2

        // Test Case 2: Cycle at position 0
        // [1, 2] → cycle starts at node with value 1
        ListNode head2 = new ListNode(1);
        ListNode node2_2 = new ListNode(2);
        head2.next = node2_2;
        node2_2.next = head2;  // Cycle starts at head

        ListNode result2 = solution.detectCycle(head2);
        System.out.println("Test 2 (Floyd) - Cycle starts at: " +
                (result2 != null ? result2.val : "null"));  // Expected: 1

        // Test Case 3: No cycle [1]
        ListNode head3 = new ListNode(1);

        ListNode result3 = solution.detectCycle(head3);
        System.out.println("Test 3 (Floyd) - Cycle starts at: " +
                (result3 != null ? result3.val : "null"));  // Expected: null

        ListNode result3b = solution.detectCycleWithSet(head3);
        System.out.println("Test 3 (Set)   - Cycle starts at: " +
                (result3b != null ? result3b.val : "null"));  // Expected: null

        // Test Case 4: No cycle [1, 2, 3, 4]
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        head4.next.next = new ListNode(3);
        head4.next.next.next = new ListNode(4);

        ListNode result4 = solution.detectCycle(head4);
        System.out.println("Test 4 (Floyd) - Cycle starts at: " +
                (result4 != null ? result4.val : "null"));  // Expected: null

        // Test Case 5: Empty list
        ListNode result5 = solution.detectCycle(null);
        System.out.println("Test 5 (Floyd) - Cycle starts at: " +
                (result5 != null ? result5.val : "null"));  // Expected: null

        // Test Case 6: Self-loop
        ListNode head6 = new ListNode(1);
        head6.next = head6;

        ListNode result6 = solution.detectCycle(head6);
        System.out.println("Test 6 (Floyd) - Cycle starts at: " +
                (result6 != null ? result6.val : "null"));  // Expected: 1
    }

    /**
     * Floyd's Cycle Detection Algorithm - Find cycle start
     *
     * Algorithm:
     * Phase 1: Detect cycle using slow/fast pointers
     * Phase 2: Find cycle start
     *   - Reset slow to head
     *   - Move both slow and fast one step at a time
     *   - They will meet at the cycle start
     *
     * Mathematical Proof:
     * Let distance from head to cycle start = F
     * Let distance from cycle start to meeting point = a
     * Let cycle length = C
     *
     * When they meet:
     * - Slow traveled: F + a
     * - Fast traveled: F + a + nC (n = number of complete cycles)
     * - Since fast is 2x speed: 2(F + a) = F + a + nC
     * - Simplify: F + a = nC
     * - Therefore: F = nC - a
     *
     * This means: distance from head to cycle start = 
     *             distance from meeting point to cycle start
     *
     * Time: O(n)
     * Space: O(1)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {  // Cycle detected
                // Phase 2: Find cycle start
                slow = head;  // Reset slow to head

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;  // Move both at same speed
                }

                return slow;  // Meeting point is cycle start
            }
        }

        return null;  // No cycle
    }

    /**
     * HashSet approach - Find cycle start
     *
     * Time: O(n)
     * Space: O(n)
     */
    public ListNode detectCycleWithSet(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode cur = head;

        while (cur != null) {
            if (seen.contains(cur)) {  // First revisited node = cycle start
                return cur;
            }
            seen.add(cur);
            cur = cur.next;
        }

        return null;  // No cycle
    }
}