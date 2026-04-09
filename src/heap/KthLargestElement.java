package heap;

import java.util.PriorityQueue;

/**
 * LeetCode 215 - Kth Largest Element in an Array
 * <p>
 * Problem:
 * Given an integer array nums and an integer k, return the kth largest
 * element in the array. Note that it is the kth largest in sorted order,
 * not the kth distinct element.
 * <p>
 * Approach: Min-heap of size k
 * Stream through the array maintaining a min-heap of exactly k elements.
 * Whenever the heap exceeds k, evict the smallest. After all elements are
 * processed, the kth largest sits at the top of the heap.
 * <p>
 * Example:
 * nums = [3,2,1,5,6,4], k = 2
 * <p>
 * offer 3 → heap: [3]
 * offer 2 → heap: [2,3]
 * offer 1 → heap: [1,2,3] → poll → [2,3]   1 evicted
 * offer 5 → heap: [2,3,5] → poll → [3,5]   2 evicted
 * offer 6 → heap: [3,5,6] → poll → [5,6]   3 evicted
 * offer 4 → heap: [4,5,6] → poll → [5,6]   4 evicted
 * <p>
 * peek → 5 (2nd largest) ✅
 * <p>
 * Time  : O(n log k) — n insertions, each heap operation costs log k
 * Space : O(k)       — heap holds at most k elements
 */
public class KthLargestElement {

    public static void main(String[] args) {
        // Test 1: standard case
        // Expected: 5 (2nd largest in [1,2,3,4,5,6])
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Test 1: " + ordinal(k1) + " largest → " + findKthLargest(nums1, k1));

        // Test 2: k = 1 (largest element)
        // Expected: 6
        int[] nums2 = {3, 2, 1, 5, 6, 4};
        int k2 = 1;
        System.out.println("Test 2: " + ordinal(k2) + " largest → " + findKthLargest(nums2, k2));

        // Test 3: k = length (smallest element)
        // Expected: 1
        int[] nums3 = {3, 2, 1, 5, 6, 4};
        int k3 = 6;
        System.out.println("Test 3: " + ordinal(k3) + " largest → " + findKthLargest(nums3, k3));

        // Test 4: duplicates present
        // Expected: 4
        int[] nums4 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k4 = 4;
        System.out.println("Test 4: " + ordinal(k4) + " largest → " + findKthLargest(nums4, k4));

        // Test 5: all elements identical
        // Expected: 2
        int[] nums5 = {2, 2, 2, 2};
        int k5 = 3;
        System.out.println("Test 5: " + ordinal(k5) + " largest → " + findKthLargest(nums5, k5));

        // Test 6: negative numbers
        // Expected: -2
        int[] nums6 = {-1, -2, -3, -4, -5};
        int k6 = 2;
        System.out.println("Test 6: " + ordinal(k6) + " largest → " + findKthLargest(nums6, k6));

        // Test 7: single element
        // Expected: 1
        int[] nums7 = {1};
        int k7 = 1;
        System.out.println("Test 7: " + ordinal(k7) + " largest → " + findKthLargest(nums7, k7));
    }

    /**
     * Returns the kth largest element in the array.
     *
     * @param nums input array of integers
     * @param k    rank of the largest element to find
     * @return the kth largest element
     */
    private static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * Returns the ordinal string representation of a positive integer.
     * Handles special cases 11th, 12th, 13th correctly.
     *
     * @param k positive integer
     * @return ordinal string e.g. "1st", "2nd", "3rd", "4th"
     */
    private static String ordinal(int k) {
        if (k >= 11 && k <= 13) return k + "th";
        return switch (k % 10) {
            case 1  -> k + "st";
            case 2  -> k + "nd";
            case 3  -> k + "rd";
            default -> k + "th";
        };
    }
}