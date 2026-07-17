package sortalgorithms;

import java.util.*;

/**
 * LeetCode 315. Count of Smaller Numbers After Self
 *
 * For each element in nums, count how many elements to its right are smaller.
 *
 * Approach: Merge Sort (descending order) with (value, originalIndex) pairs
 * -----------------------------------------------------------------------------
 * Each half is sorted in DESCENDING order via the recursive merge sort. During
 * the merge step, whenever a left-half element is strictly greater than the
 * current right-half element, every remaining right-half element (positions j
 * through end) is also smaller than it - because the right half is itself
 * sorted descending. That entire remaining block is credited to the left
 * element in one step via (end - j + 1), rather than accumulated one element
 * at a time.
 *
 * This mirrors the more common ascending-order / rightCount-accumulator
 * formulation of this technique, just built around descending order with a
 * lump-sum credit at the moment a left element "wins" the comparison.
 *
 * Time Complexity:  O(n log n)
 * Space Complexity: O(n)
 */
public class CountSmallerAfterSelf {

    static class Pair {
        int val;
        int idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        counts = new int[nums.length];
        Pair[] numsPair = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsPair[i] = new Pair(nums[i], i);
        }

        mergeSort(numsPair, 0, nums.length - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : counts) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(Pair[] numsPair, int start, int end) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;
        mergeSort(numsPair, start, mid);
        mergeSort(numsPair, mid + 1, end);
        merge(numsPair, start, mid, end);
    }

    private void merge(Pair[] numsPair, int start, int mid, int end) {
        Pair[] temp = new Pair[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if (numsPair[i].val <= numsPair[j].val) {
                temp[k++] = numsPair[j++];
            } else {
                counts[numsPair[i].idx] += end - j + 1;
                temp[k++] = numsPair[i++];
            }
        }

        while (i <= mid) temp[k++] = numsPair[i++];
        while (j <= end) temp[k++] = numsPair[j++];

        int x = 0;
        while (x < temp.length) numsPair[start + x] = temp[x++];
    }

    public static void main(String[] args) {
        CountSmallerAfterSelf sol = new CountSmallerAfterSelf();

        System.out.println(sol.countSmaller(new int[]{5, 2, 6, 1}) + " (expected [2, 1, 1, 0])");

        sol = new CountSmallerAfterSelf();
        System.out.println(sol.countSmaller(new int[]{-1}) + " (expected [0])");

        sol = new CountSmallerAfterSelf();
        System.out.println(sol.countSmaller(new int[]{-1, -1}) + " (expected [0, 0])");

        sol = new CountSmallerAfterSelf();
        System.out.println(sol.countSmaller(new int[]{2, 0, 1}) + " (expected [2, 0, 0])");
    }
}