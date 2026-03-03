package arrays101;

import java.util.Arrays;

/*
    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        MoveZeros obj = new MoveZeros();
        System.out.println("Original array: " + Arrays.toString(nums));
        obj.moveZeroes(nums);
        System.out.println("Processed array: " + Arrays.toString(nums));

    }

    private void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length){
            if (nums[right] != 0){
                int temp = nums[left]; //0
                nums[left] = nums[right]; //1
                nums[right] = temp; //0
                left++;

        } else {
                right++;
            }

    }
    }
}

