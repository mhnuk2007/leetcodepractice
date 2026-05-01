package linkedlist;

/**
 * LeetCode 707: Design Linked List - Test Class
 * <p>
 * This class contains the main method to test the implementation of the MyLinkedList class.
 */
public class DesignLinkedList {

    public static void main(String[] args) {
        System.out.println("--- Testing MyLinkedList ---");
        MyLinkedList list = new MyLinkedList();

        // Test 1: Add at head
        list.addAtHead(1);
        System.out.println("addAtHead(1): List -> [1], get(0) -> " + list.get(0)); // Expected: 1

        // Test 2: Add at tail
        list.addAtTail(3);
        System.out.println("addAtTail(3): List -> [1, 3], get(1) -> " + list.get(1)); // Expected: 3

        // Test 3: Add at index
        list.addAtIndex(1, 2); // list becomes 1->2->3
        System.out.println("addAtIndex(1, 2): List -> [1, 2, 3], get(1) -> " + list.get(1)); // Expected: 2

        // Test 4: Get existing value
        System.out.println("get(2): " + list.get(2)); // Expected: 3

        // Test 5: Delete at index
        list.deleteAtIndex(1); // list becomes 1->3
        System.out.println("deleteAtIndex(1): List -> [1, 3], get(1) -> " + list.get(1)); // Expected: 3

        // Test 6: Get invalid index
        System.out.println("get(-1): " + list.get(-1)); // Expected: -1

        System.out.println("\n--- Edge Case Testing ---");
        MyLinkedList list2 = new MyLinkedList();

        // Test 7: Delete from empty list
        list2.deleteAtIndex(0);
        System.out.println("deleteAtIndex(0) on empty list: get(0) -> " + list2.get(0)); // Expected: -1

        // Test 8: Add to empty list then delete
        list2.addAtHead(5);
        System.out.println("addAtHead(5): get(0) -> " + list2.get(0)); // Expected: 5
        list2.deleteAtIndex(0);
        System.out.println("deleteAtIndex(0): get(0) -> " + list2.get(0)); // Expected: -1
    }
}
