package linkedlist;

/*
 * Node class representing an element in the singly linked list.
 * Each node stores:
 *  - value (val)
 *  - reference to the next node (next)
 */
public class ListNode {

    int val;        // value stored in node
    ListNode next;  // reference to next node

    // Default constructor
    ListNode() {
        this.val = 0;
        this.next = null;
    }

    // Constructor with value
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    // Constructor with value and next node reference
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}