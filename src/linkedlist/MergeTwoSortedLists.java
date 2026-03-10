package linkedlist;

/**
 * LeetCode 21: Merge Two Sorted Lists
 * <p>
 * You are given the heads of two sorted linked lists, list1 and list2.
 * <p>
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * <p>
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 * <p>
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class MergeTwoSortedLists {

    /**
     * Merges two sorted linked lists into a single sorted linked list.
     *
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the newly merged sorted linked list.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // TODO: Implement the solution here.
        // Hint: A common approach is to use a dummy node to build the new list.
        // You can solve this either iteratively or recursively.
        return null;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        // Test Case 1
        ListNode list1a = createList(new int[]{1, 2, 4});
        ListNode list1b = createList(new int[]{1, 3, 4});
        System.out.println("Test Case 1:");
        System.out.print("  List 1: ");
        printList(list1a);
        System.out.print("  List 2: ");
        printList(list1b);
        ListNode merged1 = solution.mergeTwoLists(list1a, list1b);
        System.out.print("  Merged: ");
        printList(merged1); // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null
        System.out.println("--------------------");

        // Test Case 2
        ListNode list2a = createList(new int[]{});
        ListNode list2b = createList(new int[]{});
        System.out.println("Test Case 2 (Both empty):");
        ListNode merged2 = solution.mergeTwoLists(list2a, list2b);
        System.out.print("  Merged: ");
        printList(merged2); // Expected: null
        System.out.println("--------------------");

        // Test Case 3
        ListNode list3a = createList(new int[]{});
        ListNode list3b = createList(new int[]{0});
        System.out.println("Test Case 3 (First empty):");
        ListNode merged3 = solution.mergeTwoLists(list3a, list3b);
        System.out.print("  Merged: ");
        printList(merged3); // Expected: 0 -> null
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to print a linked list from a given node
    private static void printList(ListNode node) {
        ListNode current = node;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
