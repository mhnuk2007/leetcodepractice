package arrays;

import java.util.Arrays;

/**
 * LeetCode 189: Rotate Array
 *
 * Rotate an array to the right by k steps.
 */
public class RotateArray {

    /**
     * Optimal Solution: Reversal Algorithm
     *
     * Steps:
     * 1. Reverse the whole array
     * 2. Reverse first k elements
     * 3. Reverse remaining elements
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void rotate(int[] nums, int k) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        k %= n;

        if (k == 0) {
            return;
        }

        reverse(nums, 0, n - 1);     // Step 1
        reverse(nums, 0, k - 1);     // Step 2
        reverse(nums, k, n - 1);     // Step 3
    }

    /**
     * Reverse helper function
     */
    private void reverse(int[] nums, int start, int end) {

        while (start < end) {

            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    /**
     * Cyclic Replacement Approach
     *
     * Move elements directly to their correct position.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void rotateV2(int[] nums, int k) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        k %= n;

        if (k == 0) {
            return;
        }

        int count = 0;

        for (int start = 0; count < n; start++) {

            int current = start;
            int prev = nums[start];

            do {

                int next = (current + k) % n;

                int temp = nums[next];
                nums[next] = prev;
                prev = temp;

                current = next;
                count++;

            } while (start != current);
        }
    }

    public static void main(String[] args) {

        RotateArray solution = new RotateArray();

        int[] nums1 = {1,2,3,4,5,6,7};
        solution.rotate(nums1,3);
        System.out.println(Arrays.toString(nums1));
        // [5,6,7,1,2,3,4]

        int[] nums2 = {-1,-100,3,99};
        solution.rotate(nums2,2);
        System.out.println(Arrays.toString(nums2));
        // [3,99,-1,-100]

        int[] nums3 = {1,2};
        solution.rotate(nums3,3);
        System.out.println(Arrays.toString(nums3));
        // [2,1]
    }
}