package sortalgorithms;

import java.util.Arrays;

public class MergeSortRec {
        public static void main(String[] args) {
            int[] arr = {64, 34, 45, 65, 2, 23, 2};
            int start = 0;
            int end = arr.length - 1;
            System.out.println("Original array: " + Arrays.toString(arr));
            mergeSort(arr, start, end);
            System.out.println("Sorted array: " + Arrays.toString(arr));
        }

    private static void mergeSort(int[] arr, int start, int end) {
            if (start >= end) return;
        int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
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
            while (j <= end) temp[k++] = arr[j++];
            int x = 0;
            while (x < temp.length) arr[start + x] = temp[x++];
    }

}
