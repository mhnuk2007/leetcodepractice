package arrays101;

import java.util.Arrays;

public class SquaresOfSorted {
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        // bubble sort
        for (int i = 0; i < nums.length; i++) {
            int j = 0;
            while (nums[j] > nums[j + 1] && j < nums.length - i - 1) {
                int temp = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = temp;
                j++;
            }

        }
            return nums;

    }

    public static void main(String[] args) {
        SquaresOfSorted obj = new SquaresOfSorted();
        int[] arr = {-4,-1,0,3,10};
        int[] result = obj.sortedSquares(arr);

        System.out.println(Arrays.toString(result));
    }
}
