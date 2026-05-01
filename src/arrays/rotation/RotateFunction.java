package arrays;

public class RotateFunction {
    public static int maxRotateFunction(int[] nums) {
        int F = 0;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            F = F + i * nums[i];
        }

        int ans = F;
        for (int k = 1; k < n; k++) {
            F = F + sum - n * nums[n - k];
            ans = Math.max(ans, F);

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 6};
        System.out.println(maxRotateFunction(nums1));
    }
}
