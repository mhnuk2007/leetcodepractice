package sortalgorithms;

import java.util.Arrays;

public class SelectionSortRec {
    public static void main(String[] args) {
        int[] arr = {64, 34, 45, 65, 2, 23, 2};
        int[] a = Arrays.copyOf(arr, arr.length);
        int[] b = Arrays.copyOf(arr, arr.length);
        System.out.println("Original array: " + Arrays.toString(a));
        selectionSort(a, 0, 1, 0);
        System.out.println("Sorted array: " + Arrays.toString(a));

        System.out.println("Original array: " + Arrays.toString(b));
        selectionSort2(b, b.length - 1, 0, 0);
        System.out.println("Sorted array: " + Arrays.toString(b));
    }

    private static void selectionSort(int[] arr, int row, int col, int minIndex) {
        // Places the minimum element of the unsorted suffix at the current row.
        if (row >= arr.length - 1) return;

        if (col < arr.length) {
            if (arr[col] < arr[minIndex]) {
                minIndex = col;
            }
            selectionSort(arr, row, col + 1, minIndex);
        } else {
            swap(arr, minIndex, row);
            selectionSort(arr, row + 1, row + 2, row + 1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void selectionSort2(int[] arr, int row, int col, int maxIndex) {
        // Places the maximum element of the unsorted prefix at the current row.
        if (row == 0) return;

        if (col <= row) {
            if (arr[col] > arr[maxIndex]) {
                maxIndex = col;
            }
            selectionSort2(arr, row, col + 1, maxIndex);
        } else {
            swap(arr, maxIndex, row);
            selectionSort2(arr, row - 1, 0, 0);
        }
    }
}
