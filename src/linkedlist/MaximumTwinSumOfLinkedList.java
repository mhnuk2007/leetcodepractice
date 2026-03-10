package linkedlist;

public class MaximumTwinSumOfLinkedList {
    /**
     * LeetCode 2130: Maximum Twin Sum of a Linked List
     * <p>
     * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list
     * is known as the twin of the (n-1-i)th node.
     * The twin sum is defined as the sum of a node and its twin.
     * <p>
     * This method finds the maximum twin sum using a three-step approach:
     * 1. Find the middle of the linked list using fast/slow pointers.
     * 2. Reverse the second half of the linked list.
     * 3. Iterate through both halves simultaneously to calculate twin sums and find the maximum.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = reverse(slow);
        ListNode first = head;
        int maxTwinSum = Integer.MIN_VALUE;
        while (secondHalf != null) {
            maxTwinSum = Math.max(maxTwinSum, first.val + secondHalf.val);
            first = first.next;
            secondHalf = secondHalf.next;
        }
        return maxTwinSum;


    }
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        MaximumTwinSumOfLinkedList solution = new MaximumTwinSumOfLinkedList();

        // Test Case 1: [4,2,5,1] → twin(0)=4+1=5, twin(1)=2+5=7
        ListNode head1 = createList(new int[]{4, 2, 5, 1});
        System.out.println("Test 1: " + solution.pairSum(head1)); // Expected: 7

        // Test Case 2: [5,4,2,1] → twin(0)=5+1=6, twin(1)=4+2=6
        ListNode head2 = createList(new int[]{5, 4, 2, 1});
        System.out.println("Test 2: " + solution.pairSum(head2)); // Expected: 6

        // Test Case 3: [1,100] → twin(0)=1+100=101
        ListNode head3 = createList(new int[]{1, 100});
        System.out.println("Test 3: " + solution.pairSum(head3)); // Expected: 101

        // Test Case 4: [1,2,3,4,5,6] → twins: 1+6=7, 2+5=7, 3+4=7
        ListNode head4 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Test 4: " + solution.pairSum(head4)); // Expected: 7
    }

    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
}