package arrays101;

import java.util.Arrays;

public class ValidMountainArray {
    public static void main(String[] args) {
        ValidMountainArray obj = new ValidMountainArray();
        System.out.println("-- TEST CASE 1 --");
        int[] arr1 = {2,1};
        System.out.println("Is " + Arrays.toString(arr1) + "a valid mountain array? " + obj.validMountainArray(arr1));

        System.out.println("-- TEST CASE 2 --");
        int[] arr2 = {3,5,5};
        System.out.println("Is " + Arrays.toString(arr2) + "a valid mountain array? " + obj.validMountainArray(arr2));

        System.out.println("-- TEST CASE 3 --");
        int[] arr3 = {0,3,2,1};
        System.out.println("Is " + Arrays.toString(arr3) + "a valid mountain array? " + obj.validMountainArray(arr3));

    }

    public boolean validMountainArray(int[] arr) {
        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
            i++;
        }
        if (i == 0 || i == arr.length - 1) {
            return false;
        }
        while (i < arr.length - 1 && arr[i] > arr[i + 1]) {
            i++;
        }
        return i == arr.length - 1;

    }
}
