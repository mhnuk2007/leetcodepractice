package slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class MaxSumOfDistinctSubarray {
    public static long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int windowSum = 0;
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i <= n - k; i++) {
            Set<Integer> set = new HashSet<>();
            boolean isDup = false;
            windowSum = 0;
            for (int j = i; j < i + k; j++) {
                if (set.contains(nums[j])) {
                    isDup = true;
                    break;
                }
                set.add(nums[j]);
                windowSum += nums[j];
            }
            if (!isDup) maxSum = Math.max(windowSum, maxSum);

        }
        return maxSum != Long.MIN_VALUE ? maxSum : 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,5,4,2,9,9,9};
        int k = 3;
        System.out.println(maximumSubarraySum(nums, k));
    }
}
