package sortalgorithms;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {64, 34, 45, 65, 2, 23, 2};
        System.out.println("Original array: " + Arrays.toString(arr));
        int[] arr2 = arr.clone();
        insertionSortIter(arr2);
        System.out.println("Insertion Sort: " + Arrays.toString(arr2));
    }

    // =========================================================================
    // INSERTION SORT — iterative
    //
    // arr = {64, 34, 45, 65, 2, 23, 2}
    //
    // i=1  key=34
    // 64 > 34 → shift
    // [64, 64, 45, 65, 2, 23, 2]
    // insert key=34 at idx=0
    // [34, 64, 45, 65, 2, 23, 2]
    //
    // i=2  key=45
    // 64 > 45 → shift
    // [34, 64, 64, 65, 2, 23, 2]
    // insert key=45 at idx=1
    // [34, 45, 64, 65, 2, 23, 2]
    //
    // i=3  key=65
    // 64 <= 65 → no shift
    // insert key=65 at idx=3
    // [34, 45, 64, 65, 2, 23, 2]
    //
    // i=4  key=2
    // 65 > 2 → shift  [34, 45, 64, 65, 65, 23, 2]
    // 64 > 2 → shift  [34, 45, 64, 64, 65, 23, 2]
    // 45 > 2 → shift  [34, 45, 45, 64, 65, 23, 2]
    // 34 > 2 → shift  [34, 34, 45, 64, 65, 23, 2]
    // insert key=2 at idx=0
    // [2, 34, 45, 64, 65, 23, 2]
    //
    // i=5  key=23
    // 65 > 23 → shift  [2, 34, 45, 64, 65, 65, 2]
    // 64 > 23 → shift  [2, 34, 45, 64, 64, 65, 2]
    // 45 > 23 → shift  [2, 34, 45, 45, 64, 65, 2]
    // 34 > 23 → shift  [2, 34, 34, 45, 64, 65, 2]
    // insert key=23 at idx=1
    // [2, 23, 34, 45, 64, 65, 2]
    //
    // i=6  key=2
    // 65 > 2 → shift  [2, 23, 34, 45, 64, 65, 65]
    // 64 > 2 → shift  [2, 23, 34, 45, 64, 64, 65]
    // 45 > 2 → shift  [2, 23, 34, 45, 45, 64, 65]
    // 34 > 2 → shift  [2, 23, 34, 34, 45, 64, 65]
    // 23 > 2 → shift  [2, 23, 23, 34, 45, 64, 65]
    // insert key=2 at idx=1
    // [2, 2, 23, 34, 45, 64, 65]
    //
    // Sorted: [2, 2, 23, 34, 45, 64, 65]
    // =========================================================================

    private static void insertionSortIter(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}