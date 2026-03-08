package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListsIntersection {
    public static void main(String[] args) {
        LinkedListsIntersection solution = new LinkedListsIntersection();

        // Test Case 1: intersectVal = 8, skipA = 2, skipB = 3
        System.out.println("=== Test Case 1 ===");

        ListNode intersection1 = new ListNode(8);
        intersection1.next = new ListNode(4);
        intersection1.next.next = new ListNode(5);

        ListNode headA1 = new ListNode(4);
        headA1.next = new ListNode(1);
        headA1.next.next = intersection1;

        ListNode headB1 = new ListNode(5);
        headB1.next = new ListNode(6);
        headB1.next.next = new ListNode(1);
        headB1.next.next.next = intersection1;

        System.out.println("Two-Pointer:   " +
                (solution.getIntersectionNodeTwoPointer(headA1, headB1) != null ?
                        solution.getIntersectionNodeTwoPointer(headA1, headB1).val : "null"));

        System.out.println("HashSet:       " +
                (solution.getIntersectionNodeHashSet(headA1, headB1) != null ?
                        solution.getIntersectionNodeHashSet(headA1, headB1).val : "null"));

        System.out.println("Length Diff:   " +
                (solution.getIntersectionNodeLengthDiff(headA1, headB1) != null ?
                        solution.getIntersectionNodeLengthDiff(headA1, headB1).val : "null"));

        System.out.println("Expected: 8\n");

        // Test Case 2: No intersection
        System.out.println("=== Test Case 2 ===");

        ListNode headA2 = new ListNode(2);
        headA2.next = new ListNode(6);
        headA2.next.next = new ListNode(4);

        ListNode headB2 = new ListNode(1);
        headB2.next = new ListNode(5);

        System.out.println("Two-Pointer:   " +
                (solution.getIntersectionNodeTwoPointer(headA2, headB2) != null ?
                        solution.getIntersectionNodeTwoPointer(headA2, headB2).val : "null"));

        System.out.println("HashSet:       " +
                (solution.getIntersectionNodeHashSet(headA2, headB2) != null ?
                        solution.getIntersectionNodeHashSet(headA2, headB2).val : "null"));

        System.out.println("Length Diff:   " +
                (solution.getIntersectionNodeLengthDiff(headA2, headB2) != null ?
                        solution.getIntersectionNodeLengthDiff(headA2, headB2).val : "null"));

        System.out.println("Expected: null\n");
    }

    /**
     * Approach 1: Two-Pointer (MOST ELEGANT)
     *
     * Time: O(m + n)
     * Space: O(1) - BEST
     */
    public ListNode getIntersectionNodeTwoPointer(ListNode headA, ListNode headB) {
        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        return pointerA;
    }

    /**
     * Approach 2: HashSet (MOST INTUITIVE)
     *
     * Time: O(m + n)
     * Space: O(m) or O(n)
     */
    public ListNode getIntersectionNodeHashSet(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        // Store all nodes from list A
        ListNode currA = headA;
        while (currA != null) {
            set.add(currA);
            currA = currA.next;
        }

        // Find first node from B that's in the set
        ListNode currB = headB;
        while (currB != null) {
            if (set.contains(currB)) {
                return currB;
            }
            currB = currB.next;
        }

        return null;
    }

    /**
     * Approach 3: Length Difference (MOST STRAIGHTFORWARD)
     *
     * Time: O(m + n)
     * Space: O(1)
     */
    public ListNode getIntersectionNodeLengthDiff(ListNode headA, ListNode headB) {
        // Step 1: Calculate lengths
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        // Step 2: Align starting points
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        // Step 3: Move both together until they meet
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    private static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode current = head;
        int count = 0;
        int maxNodes = 20;

        while (current != null && count < maxNodes) {
            sb.append(current.val);
            if (current.next != null && count < maxNodes - 1) {
                sb.append(",");
            }
            current = current.next;
            count++;
        }

        if (count == maxNodes && current != null) {
            sb.append("...");
        }

        sb.append("]");
        return sb.toString();
    }
}