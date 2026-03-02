package arrays101;

import java.util.Arrays;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3, 4, 2, 3, 3, 2, 2, 3, 3, 3, 3};
        int val = 3;
        RemoveElement obj = new RemoveElement();
        int k = obj.removeElement(nums, val);
        System.out.println("Count: " + k);
        System.out.println("Array after removal: " + Arrays.toString(Arrays.copyOf(nums, k)));
    }

    public int removeElement(int[] nums, int val) {
       int left = 0;
       int right = nums.length - 1;

       while (left <= right){
           if (nums[left] == val){
               nums[left] = nums[right];
               right--;
           } else {
               left++;
           }
       }
        return right + 1;
    }
}
