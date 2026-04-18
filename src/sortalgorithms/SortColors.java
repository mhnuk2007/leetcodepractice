package sortalgorithms;

import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 0, 1, 2, 2, 0, 1};
        System.out.println("Original Array: " + Arrays.toString(nums));
        sortColors(nums);
        System.out.println("Sorted Array: " + Arrays.toString(nums));

    }

    private static void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int index = 0;
        while (index <= j) {
            if (nums[index] == 0) {
                swap(nums, index, i);
                i++;
                index++;
            }  else if (nums[index] == 2) {
                swap(nums, index, j);
                j--;
            } else {
                index++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
