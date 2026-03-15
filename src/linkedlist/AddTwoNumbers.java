package linkedlist;

/**
 * LeetCode 2 - Add Two Numbers
 *
 * Problem:
 *   Two non-empty linked lists represent two non-negative integers stored
 *   in reverse order (each node contains a single digit). Add the two
 *   numbers and return the sum as a linked list in reverse order.
 *
 * Approach: Simulate grade-school addition with a carry
 *   Use a dummy head node to simplify result list construction.
 *   At each step: sum = l1.val + l2.val + carry
 *     - New node value = sum % 10
 *     - carry          = sum / 10
 *   Continue while either list has nodes OR carry is non-zero
 *   (handles final carry e.g. 999 + 1 = 1000).
 *
 * Example:
 *   l1 = 2 → 4 → 3  (represents 342)
 *   l2 = 5 → 6 → 4  (represents 465)
 *   sum =    342 + 465 = 807
 *   result = 7 → 0 → 8
 *
 * Time  : O(max(m, n))  — m, n are lengths of l1 and l2
 * Space : O(max(m, n))  — result list length
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        // Test 1: 342 + 465 = 807 → [7, 0, 8]
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        printList(addTwoNumbers(l1, l2));           // Expected: 7 → 0 → 8

        // Test 2: carry propagation — 999 + 1 = 1000 → [0, 0, 0, 1]
        ListNode l3 = new ListNode(9);
        l3.next = new ListNode(9);
        l3.next.next = new ListNode(9);

        ListNode l4 = new ListNode(1);

        printList(addTwoNumbers(l3, l4));           // Expected: 0 → 0 → 0 → 1

        // Test 3: unequal lengths — 99 + 1 = 100 → [0, 0, 1]
        ListNode l5 = new ListNode(9);
        l5.next = new ListNode(9);

        ListNode l6 = new ListNode(1);

        printList(addTwoNumbers(l5, l6));           // Expected: 0 → 0 → 1

        // Test 4: both single digits, no carry — 0 + 0 = 0 → [0]
        printList(addTwoNumbers(new ListNode(0), new ListNode(0))); // Expected: 0
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        return dummy.next;
    }

    // ── Helper ───────────────────────────────────────────────────────────────
    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" → ");
            head = head.next;
        }
        System.out.println(sb);
    }

    // ── ListNode ─────────────────────────────────────────────────────────────
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;                         // fix: was this.val = 0
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}