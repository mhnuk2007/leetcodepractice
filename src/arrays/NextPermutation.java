package arrays;

import java.util.Arrays;

/**
 * LC 31 - Next Permutation
 *
 * Problem:
 *   Rearrange nums into the lexicographically next greater permutation.
 *   If no such permutation exists (array is descending), rearrange to
 *   the smallest permutation (ascending order). Must be done in-place.
 *
 * Approach: Three-step in-place algorithm
 *   Step 1 — Find pivot: scan right-to-left for first nums[i] < nums[i+1].
 *            Pivot is the leftmost element that can be increased.
 *   Step 2 — Swap: find rightmost element > nums[pivot], swap with pivot.
 *            Suffix remains descending after swap.
 *   Step 3 — Reverse suffix: reverse from pivot+1 to end → makes it ascending
 *            (smallest possible suffix after the increment).
 *   Edge case: no pivot (fully descending) → reverse entire array.
 *
 * Example:
 *   [1, 3, 5, 4, 2]
 *    Step 1: pivot = 1 (nums[1]=3 < nums[2]=5)
 *    Step 2: swap nums[1]=3 with nums[3]=4 → [1, 4, 5, 3, 2]
 *    Step 3: reverse suffix [5,3,2] → [1, 4, 2, 3, 5]
 *
 * Time  : O(n)
 * Space : O(1)
 */
public class NextPermutation {

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] arr;

        // Test 1: basic ascending — has next permutation
        arr = new int[]{1, 2, 3};
        np.nextPermutation(arr);
        System.out.println(Arrays.toString(arr)); // Expected: [1, 3, 2]

        // Test 2: basic descending — no next permutation, wraps around
        arr = new int[]{3, 2, 1};
        np.nextPermutation(arr);
        System.out.println(Arrays.toString(arr)); // Expected: [1, 2, 3]

        // Test 3: middle case
        arr = new int[]{1, 3, 5, 4, 2};
        np.nextPermutation(arr);
        System.out.println(Arrays.toString(arr)); // Expected: [1, 4, 2, 3, 5]

        // Test 4: single element
        arr = new int[]{1};
        np.nextPermutation(arr);
        System.out.println(Arrays.toString(arr)); // Expected: [1]

        // Test 5: duplicate elements
        arr = new int[]{1, 1, 5};
        np.nextPermutation(arr);
        System.out.println(Arrays.toString(arr)); // Expected: [1, 5, 1]
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // Step 1: find pivot — rightmost index where nums[i] < nums[i+1]
        int pivot = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // Edge case: no pivot → entire array is descending → reverse all
        if (pivot == -1) {
            reverse(nums, 0);
            return;
        }

        // Step 2: swap pivot with rightmost element greater than nums[pivot]
        for (int i = n - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                swap(nums, i, pivot);
                break;
            }
        }

        // Step 3: reverse suffix after pivot → smallest possible arrangement
        reverse(nums, pivot + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}