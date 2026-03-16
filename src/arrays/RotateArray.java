package arrays;

import java.util.Arrays;

/**
 * LC 189 - Rotate Array
 *
 * Problem:
 *   Given an integer array nums, rotate the array to the right by k steps.
 *
 * Approach 1 — Reverse (Optimal):
 *   Step 1: reverse entire array       → last k elements are now at front, but reversed
 *   Step 2: reverse first k elements   → first k elements corrected
 *   Step 3: reverse remaining n-k      → rest corrected
 *   Time: O(n)  Space: O(1)
 *
 * Approach 2 — Cyclic Replacements:
 *   Place each element directly at its final position: (i + k) % n
 *   Follow each cycle until it returns to the start index.
 *   Repeat from the next start until all n elements are placed (tracked by count).
 *   Time: O(n)  Space: O(1)
 *
 * Example (Approach 1):
 *   nums=[1,2,3,4,5,6,7], k=3
 *   reverse all   → [7,6,5,4,3,2,1]
 *   reverse [0,2] → [5,6,7,4,3,2,1]
 *   reverse [3,6] → [5,6,7,1,2,3,4] ✓
 *
 * Time  : O(n)
 * Space : O(1)
 */
public class RotateArray {

    public static void main(String[] args) {
        RotateArray solution = new RotateArray();
        int[] arr;

        // Test 1: standard case
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution.rotate(arr, 3);
        System.out.println(Arrays.toString(arr));           // Expected: [5, 6, 7, 1, 2, 3, 4]

        // Test 2: k = 2
        arr = new int[]{-1, -100, 3, 99};
        solution.rotate(arr, 2);
        System.out.println(Arrays.toString(arr));           // Expected: [3, 99, -1, -100]

        // Test 3: k > n — effective rotation is k % n
        arr = new int[]{1, 2};
        solution.rotate(arr, 3);
        System.out.println(Arrays.toString(arr));           // Expected: [2, 1]

        // Test 4: k = 0 — no rotation
        arr = new int[]{1, 2, 3};
        solution.rotate(arr, 0);
        System.out.println(Arrays.toString(arr));           // Expected: [1, 2, 3]

        // Test 5: k = n — full rotation, same array
        arr = new int[]{1, 2, 3};
        solution.rotate(arr, 3);
        System.out.println(Arrays.toString(arr));           // Expected: [1, 2, 3]

        // Test 6: cyclic approach
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution.rotateCyclic(arr, 3);
        System.out.println(Arrays.toString(arr));           // Expected: [5, 6, 7, 1, 2, 3, 4]
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Approach 1 — Reverse (Optimal)
    // Time: O(n)  Space: O(1)
    // ─────────────────────────────────────────────────────────────────────────
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;                          // handles k > n, k = 0, k = n
        if (k == 0) return;

        reverse(nums, 0, n - 1);            // Step 1: reverse all
        reverse(nums, 0, k - 1);            // Step 2: reverse first k
        reverse(nums, k, n - 1);            // Step 3: reverse last n-k
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Approach 2 — Cyclic Replacements
    // Each element is placed directly at its final position (i + k) % n.
    // Follow each cycle back to its start, then advance to the next unvisited
    // start. count ensures all n elements are moved exactly once.
    // Time: O(n)  Space: O(1)
    // ─────────────────────────────────────────────────────────────────────────
    public void rotateCyclic(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;

        int count = 0;
        for (int start = 0; count < n; start++) {
            int curr = start;
            int prev = nums[start];
            do {
                int next = (curr + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                curr = next;
                count++;
            } while (curr != start);       // cycle complete when we return to start
        }
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }
}