package linkedlist;

/**
 * LeetCode 21 - Merge Two Sorted Lists
 *
 * Problem:
 *   Given the heads of two sorted linked lists, merge them into one sorted
 *   linked list by splicing together the nodes. Return the head of the merged list.
 *
 * Approach 1: Iterative (dummy head)
 *   Use a dummy node to build the result. Compare list1 and list2 heads,
 *   append the smaller node to result, advance that pointer. Attach
 *   the remaining non-null list at the end.
 *
 * Approach 2: Recursive
 *   If list1.val <= list2.val, list1 is next node — recurse on list1.next and list2.
 *   Otherwise list2 is next node — recurse on list1 and list2.next.
 *   Base case: either list is null → return the other.
 *
 * Example:
 *   list1 = [1,2,4], list2 = [1,3,4] → [1,1,2,3,4,4]
 *   list1 = [],      list2 = [0]     → [0]
 *
 * Time  : O(m+n) — each node visited once
 * Space : O(1)   — iterative; O(m+n) — recursive call stack
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println("Test 1 iterative:");
        printList(mergeTwoLists(createList(1, 2, 4), createList(1, 3, 4)));
        // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null

        // Test 2: both empty
        System.out.println("Test 2 both empty:");
        printList(mergeTwoLists(null, null));
        // Expected: null

        // Test 3: one empty
        System.out.println("Test 3 one empty:");
        printList(mergeTwoLists(null, createList(0)));
        // Expected: 0 -> null

        // Test 4: recursive standard case
        System.out.println("Test 4 recursive:");
        printList(mergeTwoListsRec(createList(1, 2, 4), createList(1, 3, 4)));
        // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr  = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;                                         // take from list1
                list1     = list1.next;
            } else {
                curr.next = list2;                                         // take from list2
                list2     = list2.next;
            }
            curr = curr.next;
        }
        curr.next = (list1 != null) ? list1 : list2;                      // attach remainder
        return dummy.next;
    }

    public static ListNode mergeTwoListsRec(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;                                   // base case
        if (list2 == null) return list1;                                   // base case
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRec(list1.next, list2);             // list1 is smaller
            return list1;
        } else {
            list2.next = mergeTwoListsRec(list1, list2.next);             // list2 is smaller
            return list2;
        }
    }

    private static ListNode createList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr  = dummy;
        for (int val : vals) { curr.next = new ListNode(val); curr = curr.next; }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) { System.out.print(head.val + " -> "); head = head.next; }
        System.out.println("null");
    }
}