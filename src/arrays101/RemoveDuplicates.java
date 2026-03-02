package arrays101;

import java.util.Arrays;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 4, 5};
        RemoveDuplicates obj = new RemoveDuplicates();
        int k = obj.removeDuplicates(nums);
        System.out.println("Count: " + k);
        System.out.println("Array after removal: " + Arrays.toString(Arrays.copyOf(nums, k)));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
