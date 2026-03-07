package linkedlist;

public class LinkedListCycle {
    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test Case 1: Cycle exists (pos = 1)
        // [3, 2, 0, -4] with tail connecting to index 1
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // Creates cycle at position 1

        System.out.println("Test 1 - Has cycle: " + solution.hasCycle(head1));  // Expected: true

        // Test Case 2: Cycle exists (pos = 0)
        // [1, 2] with tail connecting to index 0
        ListNode head2 = new ListNode(1);
        ListNode node2_2 = new ListNode(2);
        head2.next = node2_2;
        node2_2.next = head2;  // Creates cycle at position 0

        System.out.println("Test 2 - Has cycle: " + solution.hasCycle(head2));  // Expected: true

        // Test Case 3: No cycle
        // [1]
        ListNode head3 = new ListNode(1);
        System.out.println("Test 3 - Has cycle: " + solution.hasCycle(head3));  // Expected: false

        // Test Case 4: No cycle (multiple nodes)
        // [1, 2, 3, 4]
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        head4.next.next = new ListNode(3);
        head4.next.next.next = new ListNode(4);

        System.out.println("Test 4 - Has cycle: " + solution.hasCycle(head4));  // Expected: false

        // Test Case 5: Empty list
        System.out.println("Test 5 - Has cycle: " + solution.hasCycle(null));  // Expected: false

        // Test Case 6: Self-loop
        // [1] with tail connecting to itself
        ListNode head6 = new ListNode(1);
        head6.next = head6;

        System.out.println("Test 6 - Has cycle: " + solution.hasCycle(head6));  // Expected: true
    }

    /**
     * Floyd's Cycle Detection Algorithm (Tortoise and Hare)
     *
     * Algorithm:
     * 1. Use two pointers: slow (moves 1 step) and fast (moves 2 steps)
     * 2. If there's a cycle, fast will eventually meet slow
     * 3. If fast reaches null, there's no cycle
     *
     * Time: O(n) - In worst case, visits each node once
     * Space: O(1) - Only uses two pointers
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps

            if (slow == fast) {         // Pointers met = cycle detected
                return true;
            }
        }

        return false;  // Fast reached end = no cycle
    }
}

