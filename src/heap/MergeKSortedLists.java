package heap;

import linkedlist.ListNode;

import java.util.PriorityQueue;

import static linkedlist.ListNode.print;

/**
 * LeetCode 23 - Merge K Sorted Lists
 * <p>
 * Problem:
 * Given an array of k linked lists, each sorted in ascending order,
 * merge all of them into one sorted linked list and return it.
 * <p>
 * Approach: Min-heap of size k
 * Seed the heap with the head of each non-null list. Repeatedly poll the
 * minimum node, append it to the result, and push its successor back into
 * the heap. At most k nodes live in the heap at any time.
 * <p>
 * Example:
 * lists = [[1,4,5], [1,3,4], [2,6]]
 * <p>
 * Initial heap : [1(L1), 1(L2), 2(L3)]
 * poll 1(L1) → push 4   heap: [1(L2), 2(L3), 4]
 * poll 1(L2) → push 3   heap: [2(L3), 4,     3]
 * poll 2(L3) → push 6   heap: [3,     4,     6]
 * poll 3     → push 4   heap: [4,     6,     4]
 * poll 4     → push 5   heap: [4,     6,     5]
 * poll 4     → push nil heap: [5,     6       ]
 * poll 5     → push nil heap: [6              ]
 * poll 6     → push nil heap: []
 * <p>
 * result: 1->1->2->3->4->4->5->6
 * <p>
 * Time  : O(n log k) — n total nodes, each heap operation costs log k
 * Space : O(k)       — heap holds at most one node per list
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        // Test 1: standard case - three overlapping sorted lists
        // Expected: 1->1->2->3->4->4->5->6
        System.out.println("Test 1: three sorted lists");
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        print(mergeKLists(new ListNode[]{list1, list2, list3}));

        // Test 2: empty array
        // Expected: null (nothing printed)
        System.out.println("Test 2: empty array");
        print(mergeKLists(new ListNode[]{}));

        // Test 3: array containing one empty list
        // Expected: null (nothing printed)
        System.out.println("Test 3: single null list");
        print(mergeKLists(new ListNode[]{null}));

        // Test 4: single list
        // Expected: 1->2->3
        System.out.println("Test 4: single list");
        ListNode list4 = new ListNode(1);
        list4.next = new ListNode(2);
        list4.next.next = new ListNode(3);
        print(mergeKLists(new ListNode[]{list4}));

        // Test 5: lists of unequal lengths
        // Expected: 1->2->3->4->5->6->7
        System.out.println("Test 5: unequal length lists");
        ListNode list5 = new ListNode(1);
        list5.next = new ListNode(5);
        list5.next.next = new ListNode(6);
        list5.next.next.next = new ListNode(7);

        ListNode list6 = new ListNode(2);

        ListNode list7 = new ListNode(3);
        list7.next = new ListNode(4);

        print(mergeKLists(new ListNode[]{list5, list6, list7}));

        // Test 6: all lists have a single node
        // Expected: 1->2->3
        System.out.println("Test 6: all single-node lists");
        print(mergeKLists(new ListNode[]{
                new ListNode(3),
                new ListNode(1),
                new ListNode(2)
        }));

        // Test 7: lists with duplicate values across all lists
        // Expected: 1->1->1->2->2->2
        System.out.println("Test 7: all duplicates");
        ListNode list8 = new ListNode(1);
        list8.next = new ListNode(2);

        ListNode list9 = new ListNode(1);
        list9.next = new ListNode(2);

        ListNode list10 = new ListNode(1);
        list10.next = new ListNode(2);

        print(mergeKLists(new ListNode[]{list8, list9, list10}));
    }

    /**
     * Merges k sorted linked lists into one sorted linked list.
     *
     * @param lists array of heads of k sorted linked lists
     * @return head of the merged sorted linked list, or null if input is empty
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-heap ordered by node value
        PriorityQueue<ListNode> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Step 1: Seed heap with head of each non-null list
        for (ListNode list : lists) {
            if (list != null) pq.offer(list);
        }

        // Step 2: Poll minimum node, chain it, push its successor
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            if (tail.next != null) pq.offer(tail.next);
        }

        return dummy.next;
    }
}