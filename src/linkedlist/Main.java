package linkedlist;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Test 1: Basic Insertions ===");
        SinglyLinkedList list = new SinglyLinkedList();

        // Insert at head
        System.out.println("Inserting at head: 3, 2, 1");
        list.insertAtHead(3);
        list.insertAtHead(2);
        list.insertAtHead(1);
        System.out.print("List: ");
        list.display();

    }


}
