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
        int left = 0;
        int right = arr.length - 1;
        while (left < right && arr[left] < arr[left + 1]) {
            left++;
        }
        if (left == 0 || left == right) {
            return false;
        }
        while (left < right && arr[left] > arr[left + 1]) {
            left++;
        }
        return left == right;

    }
}
