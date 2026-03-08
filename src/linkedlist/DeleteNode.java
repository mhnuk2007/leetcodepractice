package linkedlist;

public class DeleteNode {
    /**
     * LeetCode 237: Delete Node in a Linked List
     *
     * Problem: There is a singly-linked list head and we want to delete a node in it.
     * You are given the node to be deleted node. You will not be given access to the first node of head.
     * All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.
     *
     * Approach: Since we don't have access to the previous node, we copy the value of the
     * next node into the current node and then skip the next node.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void deleteNode(ListNode node) {
        // Copy the value from the next node to the current node
        node.val = node.next.val;
        // Skip the next node
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        DeleteNode solution = new DeleteNode();

        // Create list: 4 -> 5 -> 1 -> 9
        ListNode head = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);
        head.next = node5;
        node5.next = node1;
        node1.next = node9;

        System.out.println("Before deleting 5: " + printList(head));
        solution.deleteNode(node5);
        System.out.println("After deleting 5:  " + printList(head));
    }

    private static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(" -> ");
            curr = curr.next;
        }
        return sb.toString();
    }

}
