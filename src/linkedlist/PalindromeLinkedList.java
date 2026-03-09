package linkedlist;

/**
 * LeetCode 234: Palindrome Linked List
 * <p>
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * <p>
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 */
public class PalindromeLinkedList {

    /**
     * Checks if a singly linked list is a palindrome.
     *
     * @param head The head of the linked list.
     * @return true if the list is a palindrome, false otherwise.
     */
    public boolean isPalindrome(ListNode head) {
        // Phase 1: Find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Phase 2: Reverse second half
        ListNode secondHalf = reverse(slow);
        ListNode secondHalfCopy = secondHalf; // save tail to restore later

        // Phase 3: Compare
        boolean result = true;
        ListNode first = head;
        while (secondHalf != null) {
            if (first.val != secondHalf.val) {
                result = false;
                break;
            }
            first = first.next;
            secondHalf = secondHalf.next;
        }

        // Phase 4: Restore
        reverse(secondHalfCopy);

        return result;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        PalindromeLinkedList solution = new PalindromeLinkedList();

        // Test Case 1: Even number of nodes, palindrome
        ListNode head1 = createList(new int[]{1, 2, 2, 1});
        System.out.println("List 1 is a palindrome: " + solution.isPalindrome(head1)); // Expected: true

        // Test Case 2: Odd number of nodes, palindrome
        ListNode head2 = createList(new int[]{1, 2, 3, 2, 1});
        System.out.println("List 2 is a palindrome: " + solution.isPalindrome(head2)); // Expected: true

        // Test Case 3: Not a palindrome
        ListNode head3 = createList(new int[]{1, 2});
        System.out.println("List 3 is a palindrome: " + solution.isPalindrome(head3)); // Expected: false

        // Test Case 4: Single node
        ListNode head4 = createList(new int[]{1});
        System.out.println("List 4 is a palindrome: " + solution.isPalindrome(head4)); // Expected: true
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
}
