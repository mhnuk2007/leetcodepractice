package linkedlist;

public class SinglyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    // Display
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }

    // Basic Operations
    // Insert at beginning
    public void insertAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
        size++;

        if(tail == null){
            tail = head;
        }
    }

}
