package linkedlist;

/*
 * Node class representing an element in the singly linked list.
 * Each node stores:
 *  - value (val)
 *  - reference to the next node (next)
 */
public class ListNode {

    public int val;        // value stored in node
    public ListNode next;  // reference to next node

    // Default constructor
    public ListNode() {
        this.val = 0;
        this.next = null;
    }

    // Constructor with value
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    // Constructor with value and next node reference
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(ListNode head){
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}