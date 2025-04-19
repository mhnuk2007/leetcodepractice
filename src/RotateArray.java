import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;

        int[] result = rotateArray(nums, k);
        System.out.println(Arrays.toString(result));
    }

    private static int[] rotateArray(int[] nums, int k) {
        int i = 0;
        int n = nums.length;

        while (i<k){
            int last = nums[n-1];
            int j = n-1;
            while (j>0){
                nums[j]=nums[j-1];
                j--;
            }
            nums[0]= last;
            i++;
        }
        return nums;
    }
}
