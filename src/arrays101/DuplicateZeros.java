package arrays101;

import java.util.Arrays;

public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        int j = 0;
        for (int i = 0; i < n && j < n; i++) {
            if (arr[i] == 0) {
                temp[j++] = 0;
                if (j < n) {
                    temp[j++] = 0;
                }
            } else {
                temp[j++] = arr[i];
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }
    public static void main(String[] args) {
        DuplicateZeros obj = new DuplicateZeros();
        int[] arr = {1,0,2,3,0,4,5,0};
        obj.duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));
    }
}
