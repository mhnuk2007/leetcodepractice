package slidingwindow;

public class MaxSumOfSubArray {
    private int maxSubArraySum(int[] nums, int k){
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int i = 0;
        for(int j = 0; j < nums.length; j++){
            windowSum += nums[j];
            if(j >= k - 1){
                maxSum = Math.max(windowSum, maxSum);
                windowSum -= nums[i++];
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumOfSubArray obj = new MaxSumOfSubArray();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(obj.maxSubArraySum(nums, k));
    }
}
