package arrays101;

import java.util.Arrays;

public class ReplaceElement {
    public static void main(String[] args) {
        int[] arr = {17, 18, 5, 4, 6, 1};
        System.out.println(Arrays.toString(arr));
        ReplaceElement obj = new ReplaceElement();
        obj.replaceElements(arr);
        System.out.println(Arrays.toString(arr));


    }

    private void replaceElements(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int max = -1; // or Integer.MIN_VALUE if you want
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, arr[j]);
            }
            arr[i] = max;
        }
    }

}


