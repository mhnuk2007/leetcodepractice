package recursion;

/**
 * LeetCode 24 - Swap Nodes in Pairs
 * <p>
 * Problem:
 * Given a linked list, swap every two adjacent nodes and return its head.
 * Must swap nodes themselves, not just values.
 * <p>
 * Approach 1: Iterative (dummy head + prev pointer)
 * Use a dummy node before head. For each pair (first, second):
 * - first.next  = second.next
 * - second.next = first
 * - prev.next   = second
 * Advance prev to first (now the second in the pair) and repeat.
 * <p>
 * Approach 2: Recursive
 * Swap head and head.next, then recursively swap the rest.
 * first.next = swapPairs(second.next)
 * second.next = first
 * Return second as new head of this pair.
 * <p>
 * Example:
 * Input:  1 -> 2 -> 3 -> 4
 * Output: 2 -> 1 -> 4 -> 3
 * <p>
 * Time  : O(n) — each node visited once
 * Space : O(1) — iterative; O(n) — recursive (call stack)
 * <p>
 * Approach 3: Recursive with dummy + prev (swapPairsRec2)
 * Same structure as iterative but uses recursion instead of while loop.
 * helper(prev, curr) swaps curr and curr.next, links to prev,
 * then recurses with first as new prev and first.next as new curr.
 * </p>
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SwapNodesInPairs {

    public static void main(String[] args) {
        // Test 1: even length list
        ListNode head1 = buildList(1, 2, 3, 4);
        System.out.print("Original:          ");
        printList(head1);
        System.out.print("Iterative swapped: ");
        printList(swapPairs(head1));
        // Expected: 2 -> 1 -> 4 -> 3 -> null

        // Test 2: recursive on even length
        ListNode head2 = buildList(1, 2, 3, 4);
        System.out.print("Recursive swapped: ");
        printList(swapPairsRec(head2));
        // Expected: 2 -> 1 -> 4 -> 3 -> null

        // Test 3: odd length list
        ListNode head3 = buildList(1, 2, 3);
        System.out.print("Odd list swapped:  ");
        printList(swapPairs(head3));
        // Expected: 2 -> 1 -> 3 -> null

        // Test 4: single node
        ListNode head4 = buildList(1);
        System.out.print("Single node:       ");
        printList(swapPairs(head4));
        // Expected: 1 -> null

        // Test 5: empty list
        System.out.print("Empty list:        ");
        printList(swapPairs(null));
        // Expected: null

        // Test 6: swapPairsRec2 on even length
        ListNode head5 = buildList(1, 2, 3, 4);
        System.out.print("Rec2 swapped:      ");
        printList(swapPairsRec2(head5));
// Expected: 2 -> 1 -> 4 -> 3 -> null
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            first.next = second.next;                                     // detach second
            second.next = first;                                           // reverse pair
            prev.next = second;                                         // connect to prev

            prev = first;                                                  // advance prev
        }
        return dummy.next;
    }

    public static ListNode swapPairsRec(ListNode head) {
        if (head == null || head.next == null) return head;                // base case

        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairsRec(second.next);                          // recurse on rest
        second.next = first;                                               // reverse pair

        return second;                                                     // new head of pair
    }

    public static ListNode swapPairsRec2(ListNode head) {
        ListNode dummy = new ListNode(0);

        helper(dummy, head);

        return dummy.next;

    }

    private static void helper(ListNode prev, ListNode curr) {
        if (curr == null || curr.next == null) return;

        ListNode first = curr;
        ListNode second = curr.next;

        first.next = second.next;
        second.next = first;
        prev.next = second;

        helper(first, first.next);


    }

    private static ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : vals) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}