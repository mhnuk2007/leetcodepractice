package math;

import java.util.Arrays;

public class SortedPairGCDQueries {
    private static int[] gcdValues(int[] nums, long[] queries) {
        int maxValue = 0;
        for(int num : nums) if (num > maxValue) maxValue = num;

        int[] freq = new int[maxValue + 1];
        for(int num : nums) freq[num]++;

        int[] divisibleCount = new int[maxValue + 1];
        for(int i = 1; i <= maxValue; i++){
            for (int j = i; j <= maxValue; j+=i) {
                divisibleCount[i] += freq[j];
            }
        }
        long[] pairs = new long[maxValue + 1];
        for(int i = maxValue; i > 0; i--){
            pairs[i] = (long) divisibleCount[i] * (divisibleCount[i] - 1) / 2;
            for (int j = 2 * i; j <= maxValue; j+=i) {
                pairs[i] -= pairs[j];
            }
        }

        long[] prefix = new long[maxValue + 1];
        for(int i = 1; i <= maxValue; i++){
            prefix[i] = prefix[i - 1] + pairs[i];
        }

        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            ans[i] = binarySearch(prefix, queries[i] + 1);
        }
        return ans;
    }

    private static int binarySearch(long[] prefix, long target){
        int i = 1;
        int j = prefix.length - 1;
        while (i < j){
            int mid = i + (j - i) / 2;
            if (prefix[mid] >= target) j = mid;
            else i = mid + 1;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 4, 6, 8};
        long[] queries1 = {0,1,2,3,4,5};
        int[] nums2 = {2, 3, 4};
        long[] queries2 = {0,1,2};

        System.out.println(Arrays.toString(gcdValues(nums1, queries1)));
        System.out.println(Arrays.toString(gcdValues(nums2, queries2)));

    }
}
