package arrays101;

import java.util.Arrays;

public class RemoveDuplicates2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        RemoveDuplicates obj = new RemoveDuplicates();
        int k = obj.removeDuplicates(nums);
        System.out.println("Original array: " + Arrays.toString(nums));
        System.out.println("Count: " + k);
        System.out.println("Modified array: " + Arrays.toString(nums));
        System.out.println("Array after removal: " + Arrays.toString(Arrays.copyOf(nums, k)));
    }

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        // assume last element is unique
        // 1, 2, 2, 3, 3, 3, 4, 5, 5
        for(int i = length -2; i >= 0; i--){
            if(nums[i]==nums[i+1]){
                for(int j = i+1; j<length; j++ ){
                    nums[j-1]= nums[j];
                }
                length--;
            }
        }
        return length;
    }
}
