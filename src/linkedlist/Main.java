package linkedlist;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Test 1: Basic Insertions ===");

        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("Inserting at head: 5, 4, 3, 2, 1");

        list.insertAtHead(5);
        list.insertAtHead(4);
        list.insertAtHead(3);
        list.insertAtHead(2);
        list.insertAtHead(1);

        System.out.print("List: ");
        list.display();

        System.out.println("\nInsert at tail (9)");
        list.insertAtTail(9);
        list.display();

        System.out.println("\nInsert 8 at index 4");
        list.insertAtPosition(4, 8);
        list.display();

        System.out.println("\nDelete head");
        list.deleteAtHead();
        list.display();

        System.out.println("\nDelete tail");
        list.deleteAtTail();
        list.display();

        System.out.println("\nDelete at index 3");
        list.deleteAtPosition(3);
        list.display();

        System.out.println("\nDelete value 1");
        System.out.println("Deleted: " + list.deleteByValue(1));
        list.display();

        System.out.println("\nDelete value 3");
        System.out.println("Deleted: " + list.deleteByValue(3));
        list.display();

        System.out.println("\nList Size: " + list.size());

        System.out.println("\nSearch value 4: " + list.searchValue(4));

        System.out.println("\nValue at index 2: " + list.get(2));

        System.out.println("\nIndex of value 4: " + list.indexOf(4));
    }
}