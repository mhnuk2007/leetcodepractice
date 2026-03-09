package twopointer;

/**
 * GeeksforGeeks Problem: Find Length of Loop
 * <p>
 * Given the head of a linked list, determine whether the list contains a loop.
 * If a loop is present, return the number of nodes in the loop, otherwise return 0.
 * <p>
 * Note: Internally, pos (1-based index) is used to denote the position of the node that tail's next pointer is connected to.
 * If pos = 0, it means the last node points to null, indicating there is no loop.
 * Note that pos is not passed as a parameter.
 * <p>
 * Examples:
 * <p>
 * Input: pos = 2, List: 1 -> 2 -> 3 -> 4 -> 5 (tail connects to 2)
 * Output: 4
 * Explanation: There exists a loop in the linked list and the length of the loop is 4 (2 -> 3 -> 4 -> 5).
 * <p>
 * Input: pos = 3, List: 10 -> 19 -> 33 -> 10 (tail connects to 19)
 * Output: 3
 * Explanation: The loop is from 19 to 10. So length of loop is 19 -> 33 -> 10 = 3.
 * <p>
 * Input: pos = 0, List: 1 -> 2 -> 3
 * Output: 0
 * Explanation: There is no loop.
 * <p>
 * Constraints:
 * 1 <= number of nodes <= 10^5
 * 1 <= node->data <= 10^4
 * 0 <= pos < number of nodes
 */
public class LengthOfLoop {

    /**
     * Finds the length of the loop in a linked list.
     *
     * @param head The head of the linked list.
     * @return The number of nodes in the loop, or 0 if no loop is present.
     */
    public int findLengthOfLoop(ListNode head) {
        if (head == null) return 0;

        ListNode slow = head, fast = head;

        // Step 1: Detect loop using Floyd's algorithm
        boolean loopExists = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                loopExists = true;
                break;
            }
        }

        // Step 2: If no loop, return 0
        if (!loopExists) return 0;

        // Step 3: Count the number of nodes in the loop
        int len = 1;
        ListNode current = slow.next;
        while (current != slow) {
            len++;
            current = current.next;
        }

        return len;
    }
    public static void main(String[] args) {
        LengthOfLoop solution = new LengthOfLoop();

        // Test Case 1: Loop present (pos = 2, 1-based index)
        // List: 1 -> 2 -> 3 -> 4 -> 5
        //             ^         |
        //             |_________|
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2; // Creates a loop: 5 -> 2
        System.out.println("Test Case 1 (Loop: 1->2->3->4->5->2): " + solution.findLengthOfLoop(head1)); // Expected: 4

        // Test Case 2: Loop present (pos = 3, 1-based index)
        // List: 10 -> 19 -> 33
        //              ^     |
        //              |_____|
        ListNode head2 = new ListNode(10);
        ListNode node19 = new ListNode(19);
        ListNode node33 = new ListNode(33);
        head2.next = node19;
        node19.next = node33;
        node33.next = node19; // Creates a loop: 33 -> 19
        System.out.println("Test Case 2 (Loop: 10->19->33->19): " + solution.findLengthOfLoop(head2)); // Expected: 2

        // Test Case 3: No loop
        // List: 1 -> 2 -> 3 -> null
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        System.out.println("Test Case 3 (No Loop: 1->2->3): " + solution.findLengthOfLoop(head3)); // Expected: 0

        // Test Case 4: Single node, no loop
        ListNode head4 = new ListNode(100);
        System.out.println("Test Case 4 (Single Node, No Loop): " + solution.findLengthOfLoop(head4)); // Expected: 0

        // Test Case 5: Single node, loop to itself
        ListNode head5 = new ListNode(200);
        head5.next = head5;
        System.out.println("Test Case 5 (Single Node, Loop to itself): " + solution.findLengthOfLoop(head5)); // Expected: 1

        // Test Case 6: Empty list
        System.out.println("Test Case 6 (Empty List): " + solution.findLengthOfLoop(null)); // Expected: 0
    }
}

