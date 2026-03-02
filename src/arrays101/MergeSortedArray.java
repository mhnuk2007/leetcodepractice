package arrays101;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        int m = 3, n = 3;
        System.out.println(Arrays.toString(num1));
        System.out.println(Arrays.toString(num2));

        MergeSortedArray obj = new MergeSortedArray();
        obj.merge(num1, m, num2, n);
        System.out.println(Arrays.toString(num1));

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //Approach1
//        for (int i = 0; i < n; i++) {
//            nums1[m + i] = nums2[i];
//        }
//        // sort
//        Arrays.sort(nums1);

        //Approach2
//        int[] result = new int[m + n];
//        int i = 0, j = 0, k = 0;
//        while(i < m && j < n){
//            if(nums1[i] < nums2[j]){
//                result[k++] = nums1[i++];
//            } else {
//                result[k++] = nums2[j++];
//            }
//        }
//        while(i < m){
//            result[k++] = nums1[i++];
//        }
//        while(j < n){
//            result[k++] = nums2[j++];
//        }
//        for (int l = 0; l < m + n; l++) {
//            nums1[l] = result[l];
//        }

        //Approach3
        int i = m - 1;
        int j = n - 1;

        for (int k = m + n - 1; k >= 0; k--) {
            if (j < 0) {
                break;
            }
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
        }
    }
}

