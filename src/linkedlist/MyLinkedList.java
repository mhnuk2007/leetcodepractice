package linkedlist;

/**
 * LeetCode 707: Design Linked List
 * <p>
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next.
 * val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node.
 * <p>
 * Implement the MyLinkedList class:
 * - MyLinkedList() Initializes the MyLinkedList object.
 * - int get(int index) Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * - void addAtHead(int val) Add a node of value val before the first element of the linked list.
 * - void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * - void addAtIndex(int index, int val) Add a node of value val before the index-th node in the linked list.
 * - void deleteAtIndex(int index) Delete the index-th node in the linked list, if the index is valid.
 */
public class MyLinkedList {

    private class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /**
     * Initializes the MyLinkedList object.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     */
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        if (head == null) { // List was empty
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Append a node of value val as the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if (head == null) { // List was empty
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals the length of the linked list, the node will be appended to the end.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node newNode = new Node(val);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            head = head.next;
            if (head == null) { // List became empty
                tail = null;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (index == size - 1) { // If we deleted the tail
                tail = current;
            }
        }
        size--;
    }
}
