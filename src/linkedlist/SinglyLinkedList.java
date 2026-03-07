package linkedlist;

/*
 * Implementation of a Singly Linked List.
 *
 * Maintains:
 *  - head pointer
 *  - tail pointer
 *  - size of list
 */
public class SinglyLinkedList {

    private ListNode head;
    private ListNode tail;
    private int size;

    // Constructor
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
     * Displays the linked list
     * Example: 1 -> 2 -> 3 -> null
     */
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

    /*
     * Insert node at the beginning
     * Time Complexity: O(1)
     */
    public void insertAtHead(int val) {

        ListNode newNode = new ListNode(val);

        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = head;
        }

        size++;
    }

    /*
     * Insert node at the end
     * Time Complexity: O(1) because tail pointer is used
     */
    public void insertAtTail(int val) {

        ListNode newNode = new ListNode(val);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {

            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    /*
     * Insert node at specific index
     * Index starts from 0
     * Time Complexity: O(n)
     */
    public void insertAtPosition(int position, int val) {

        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException(
                    "Position: " + position + ", Size: " + size);
        }

        if (position == 0) {
            insertAtHead(val);
            return;
        }

        if (position == size) {
            insertAtTail(val);
            return;
        }

        ListNode newNode = new ListNode(val);
        ListNode current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        size++;
    }

    /*
     * Delete first node
     * Time Complexity: O(1)
     */
    public void deleteAtHead() {

        if (head == null) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        head = head.next;
        size--;

        // If list becomes empty
        if (head == null) {
            tail = null;
        }
    }

    /*
     * Delete last node
     * Time Complexity: O(n)
     */
    public void deleteAtTail() {

        if (tail == null) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        if (head == tail) {
            head = null;
            tail = null;
            size--;
            return;
        }

        ListNode current = head;

        while (current.next != tail) {
            current = current.next;
        }

        current.next = null;
        tail = current;

        size--;
    }

    /*
     * Delete node at given index
     * Time Complexity: O(n)
     */
    public void deleteAtPosition(int position) {

        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(
                    "Position: " + position + ", Size: " + size);
        }

        if (position == 0) {
            deleteAtHead();
            return;
        }

        if (position == size - 1) {
            deleteAtTail();
            return;
        }

        ListNode current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;

        size--;
    }

    /*
     * Delete first occurrence of value
     * Returns true if deletion happened
     */
    public boolean deleteByValue(int val) {

        if (head == null) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        if (head.val == val) {
            deleteAtHead();
            return true;
        }

        ListNode current = head;

        while (current.next != null) {

            if (current.next.val == val) {

                if (current.next == tail) {
                    tail = current;
                }

                current.next = current.next.next;
                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    /*
     * Returns size of list
     */
    public int size() {
        return size;
    }

    /*
     * Checks if list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Search Operations
     */

    /*
     * Search for a specific value in the list
     * Returns true if value exists
     * Time Complexity: O(n)
     */
    public boolean searchValue(int val) {

        ListNode current = head;

        while (current != null) {

            if (current.val == val) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    /*
     * Get the value at a given position (index)
     * Time Complexity: O(n)
     */
    public int get(int position) {

        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(
                    "Position: " + position + ", Size: " + size);
        }

        ListNode current = head;

        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        return current.val;
    }

    /*
     * Get the index of a value (first occurrence)
     * Time Complexity: O(n)
     */
    public int indexOf(int val) {
        ListNode current = head;
        int index = 0;
        while (current != null) {
            if (current.val == val) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /*
     * ========================
     * Utility Operations
     * ========================
     */

    /*
     * Reverse the linked list
     * Reverses the order of nodes in the list
     * Time Complexity: O(n)
     */

    /*
     * Check if the list is empty
     * Returns true if size is 0
     * Time Complexity: O(1)
     */

    /*
     * Get the number of nodes in the list
     * Time Complexity: O(1)
     */

    /*
     * Get the head node of the list
     * Returns the first node
     * Time Complexity: O(1)
     */

    /*
     * Get the tail node of the list
     * Returns the last node
     * Time Complexity: O(1)
     */

    /*
     * Display the linked list
     * Example output: 1 -> 2 -> 3 -> null
     * Time Complexity: O(n)
     */

    /*
     * Convert the linked list to an array
     * Returns an array containing all node values
     * Time Complexity: O(n)
     */

    /*
     * Get the middle node using the fast and slow pointer technique
     * Time Complexity: O(n)
     */

    /*
     * Detect if the linked list contains a cycle
     * Uses Floyd's Cycle Detection Algorithm
     * Time Complexity: O(n)
     */

    /*
     * Find the Nth node from the end of the list
     * Uses two-pointer technique
     * Time Complexity: O(n)
     */

    /*
     * Remove duplicate values from a sorted linked list
     * Only keeps the first occurrence
     * Time Complexity: O(n)
     */

    /*
     * Sort the linked list using Merge Sort
     * Time Complexity: O(n log n)
     */

}