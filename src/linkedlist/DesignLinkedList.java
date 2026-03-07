package linkedlist;

public class DesignLinkedList {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        // Test 1: Add at head
        list.addAtHead(1);  // [1]
        System.out.println(list.get(0));  // Expected: 1

        // Test 2: Add at tail
        list.addAtTail(3);  // [1, 3]
        System.out.println(list.get(1));  // Expected: 3

        // Test 3: Add at index
        list.addAtIndex(1, 2);  // [1, 2, 3]
        System.out.println(list.get(1));  // Expected: 2

        // Test 4: Delete at index
        list.deleteAtIndex(1);  // [1, 3]
        System.out.println(list.get(1));  // Expected: 3

        // Test 5: Delete head (single element)
        MyLinkedList list2 = new MyLinkedList();
        list2.addAtHead(1);
        list2.deleteAtIndex(0);  // []
        System.out.println(list2.get(0));  // Expected: -1

        // Test 6: Delete tail
        MyLinkedList list3 = new MyLinkedList();
        list3.addAtHead(1);
        list3.addAtTail(2);
        list3.deleteAtIndex(1);  // [1]
        System.out.println(list3.get(0));  // Expected: 1
        System.out.println(list3.get(1));  // Expected: -1
    }
}

class MyLinkedList {

    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;

        if (tail == null) {  // List was empty
            tail = head;
        }
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);

        if (head == null) {  // Empty list
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        if (index == 0) {
            addAtHead(val);
            return;
        }

        if (index == size) {
            addAtTail(val);
            return;
        }

        ListNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        ListNode newNode = new ListNode(val);
        newNode.next = current.next;
        current.next = newNode;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        if (index == 0) {
            head = head.next;

            if (head == null) {  // ✅ FIX: List is now empty
                tail = null;
            }

            size--;
            return;
        }

        ListNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;

        if (index == size - 1) {  // Deleted tail
            tail = current;
        }

        size--;
    }
}
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
