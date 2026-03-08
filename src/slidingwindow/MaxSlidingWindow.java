package slidingwindow;

public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println("Maximum sum of subarray of size " + k + ": " + maxSlidingWindow(nums, k));
    }
    private static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int start = 0;
        int max = 0;
        for (int i = 0; i < k; i++) {
            max = Math.max(max, nums[i]);
        }
        for (int i = 1; i < nums.length - k + 1; i++) {



        }

        return result;

    }
}
