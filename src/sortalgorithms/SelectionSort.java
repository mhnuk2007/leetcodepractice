package sortalgrithms;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {64, 34, 45, 65, 2, 23, 2};
        System.out.println("Original array: " + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 64, 34, 45, 65, 2, 23,  2
            //  2, 34, 45, 65,  2, 23, 64
            //  2,  2, 45, 65, 34, 23, 64
            //  2,  2, 23, 65, 34, 45, 64
            //  2,  2, 23, 34, 65, 45, 64
            //  2,  2, 23, 34, 45, 65, 64
            //  2,  2, 23, 34, 45, 64, 65
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) swap(arr, minIndex, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
