package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxSumOfDistinctSubarray {
    public static long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long windowSum = 0;
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

    public static long maximumSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        int windowSum = 0;
        long maxSum = 0;
        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            windowSum += nums[j];
            if (j - i + 1 > k) {
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) map.remove(nums[i]);
                windowSum -= nums[i];
                i++;
            }
            if (j - i + 1 == k && map.size() == k) maxSum = Math.max(windowSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 4, 2, 9, 9, 9};
        int k = 3;
        System.out.println(maximumSubarraySum(nums, k));
        System.out.println(maximumSubarraySum2(nums, k));
    }
}
