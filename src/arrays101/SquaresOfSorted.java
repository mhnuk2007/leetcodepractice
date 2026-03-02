package arrays101;

import java.util.Arrays;

public class SquaresOfSorted {
    public int[] sortedSquares(int[] nums) {
        // two pointer
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;

        }
            return result;

    }

    public static void main(String[] args) {
        SquaresOfSorted obj = new SquaresOfSorted();
        int[] arr = {-4,-1,0,3,10};
        int[] result = obj.sortedSquares(arr);

        System.out.println(Arrays.toString(result));
    }
}
