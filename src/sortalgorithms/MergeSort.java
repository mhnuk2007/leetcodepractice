package sortalgorithms;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {64, 34, 45, 65, 2, 23, 2};
        System.out.println("Original array: " + Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("Sorted array:   " + Arrays.toString(arr));
    }

    // =========================================================================
    // MERGE SORT — iterative (bottom-up)
    //
    // Instead of recursing top-down and splitting, we build sorted runs
    // bottom-up by doubling the width each pass.
    //
    // Recursive top-down thinks:  "split in half, sort each half, merge"
    // Iterative bottom-up thinks: "start with width=1 (every element is a
    //                              sorted run of size 1), merge adjacent pairs,
    //                              double width, repeat"
    //
    // arr = {64, 34, 45, 65, 2, 23, 2}   n=7
    //        idx: 0   1   2   3  4   5  6
    //
    // ── width = 1 ─────────────────────────────────────────────────────────────
    // Treat every single element as a sorted run of size 1.
    // Merge adjacent pairs of runs:
    //
    //   left=0  mid=0  right=1   merge [64] and [34]
    //   → [34, 64, 45, 65, 2, 23, 2]
    //
    //   left=2  mid=2  right=3   merge [45] and [65]
    //   → [34, 64, 45, 65, 2, 23, 2]   (45 < 65, already ordered)
    //
    //   left=4  mid=4  right=5   merge [2] and [23]
    //   → [34, 64, 45, 65, 2, 23, 2]   (2 < 23, already ordered)
    //
    //   left=6  mid=6  right=6   single element, right == left, skip
    //   (right = min(left + 2*width - 1, n-1) = min(7,6) = 6, mid=6 == right → nothing to merge)
    //
    //   After width=1: [34, 64, 45, 65, 2, 23, 2]
    //
    // ── width = 2 ─────────────────────────────────────────────────────────────
    // Sorted runs of size 2: [34,64] | [45,65] | [2,23] | [2]
    // Merge adjacent pairs:
    //
    //   left=0  mid=1  right=3   merge [34, 64] and [45, 65]
    //   → [34, 45, 64, 65, 2, 23, 2]
    //
    //   left=4  mid=5  right=6   merge [2, 23] and [2]
    //   → [34, 45, 64, 65, 2, 2, 23]
    //
    //   After width=2: [34, 45, 64, 65, 2, 2, 23]
    //
    // ── width = 4 ─────────────────────────────────────────────────────────────
    // Sorted runs of size 4: [34,45,64,65] | [2,2,23]
    // Merge adjacent pairs:
    //
    //   left=0  mid=3  right=6   merge [34, 45, 64, 65] and [2, 2, 23]
    //   2  < 34 → pick 2
    //   2  < 34 → pick 2
    //   23 < 34 → pick 23
    //   pick 34, 45, 64, 65
    //   → [2, 2, 23, 34, 45, 64, 65]
    //
    //   After width=4: [2, 2, 23, 34, 45, 64, 65]
    //
    // ── width = 8 ─────────────────────────────────────────────────────────────
    // width >= n → loop ends
    //
    // Sorted: [2, 2, 23, 34, 45, 64, 65]
    // =========================================================================

    private static void mergeSort(int[] arr) {
        int n = arr.length;
        // width: current sorted run size (1, 2, 4, 8, ...)
        for (int width = 1; width < n; width *= 2) {
            // left: start index of left run
            for (int left = 0; left < n - width; left += 2 * width) {
                int mid = left + width - 1;
                int right = Math.min(left + 2 * width - 1, n - 1);

                // Optimization: already sorted
                if (arr[mid] <= arr[mid + 1]) continue;

                merge(arr, left, mid, right);
            }
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= end)  temp[k++] = arr[j++];
        int x = 0;
        while (x < temp.length) arr[start + x] = temp[x++];
    }
}