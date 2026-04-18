package sortalgorithms;

import java.util.Arrays;

public class TwoColorSort {
    public static void main(String[] args) {
        int[] arr = {0,1,1,1,0,0,1,1,0};
        System.out.println("Original array: " + Arrays.toString(arr));
        twoColorSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    private static void twoColorSort(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (arr[i] == 0) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j--;
            }

        }
    }
}
