package sortalgrithms;

import java.util.Arrays;

public class BubbleSortRec {
    public static void main(String[] args) {
        int[] arr = {64, 34, 45, 65, 2, 23, 2};
        int[] a = Arrays.copyOf(arr, arr.length);
        int[] b = Arrays.copyOf(arr, arr.length);
        System.out.println("Original array: " + Arrays.toString(a));
        bubbleSort(a);
        System.out.println("Sorted array: " + Arrays.toString(a));
        System.out.println("Original array: " + Arrays.toString(b));
        bubbleSort2(b, b.length - 1, 0);
        System.out.println("Sorted array: " + Arrays.toString(b));
    }

    private static void bubbleSort(int[] arr) {
        bubbleSortHelper(arr, arr.length);

    }

    private static void bubbleSortHelper(int[] arr, int n) {
        // 64, 34, 45, 65,  2, 23, 2
        //                         i
        // 34, 45, 64,  2, 23,  2, 65
        // 34, 45,  2, 23,  2, 64, 65
        // 34,  2, 23,  2, 45, 64, 65
        //  2, 23,  2, 34, 45, 64, 65
        //  2,  2, 23, 34, 45, 64, 65
        if (n == 1) return;
        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                swapped = true;
            }
        }
        if (!swapped) return;
        bubbleSortHelper(arr, n - 1);

    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void bubbleSort2(int[] b, int row, int col) {
        if (row == 0) return;
        if (col < row) {
            if (b[col] > b[col + 1]) swap(b, row, col);
            bubbleSort2(b, row, col + 1);
        } else {
            bubbleSort2(b, row - 1, 0);
        }

    }


}
