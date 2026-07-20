package binarysearch.advanced;

/**
 * LeetCode 410 — Split Array Largest Sum.
 *
 * <p>Given an array of non-negative integers and an integer {@code k},
 * split the array into {@code k} non-empty contiguous subarrays such that
 * the largest sum among these subarrays is minimized.
 *
 * <p><b>Search space:</b>
 * <ul>
 *   <li>{@code left} = the maximum single element in the array (a subarray
 *       containing that element alone needs at least this much capacity)</li>
 *   <li>{@code right} = the sum of the entire array (one subarray takes
 *       everything)</li>
 * </ul>
 *
 * <p><b>Validity check:</b> can the array be split into at most {@code k}
 * contiguous subarrays such that no subarray sum exceeds {@code maxSum}?
 * Evaluated greedily — keep extending the current subarray until the next
 * element would exceed {@code maxSum}, then start a new subarray.
 *
 * <p>No explicit "single element exceeds maxSum" guard is needed in the
 * validity check — {@code left} is initialized to the largest single
 * element, so every {@code maxSum} value ever queried during the search is
 * already at least that large.
 *
 * <p>Time: O(n log(sum(nums))) — binary search over the answer range,
 * O(n) feasibility check per iteration.
 * <br>Space: O(1) auxiliary.
 */
public final class SplitArrayLargestSum {

    private SplitArrayLargestSum() {
        // utility class; use splitArray as the entry point
    }

    /**
     * Finds the minimum possible value of the largest subarray sum when
     * {@code nums} is split into {@code maxSubarrays} contiguous, non-empty
     * subarrays.
     *
     * @return the minimum feasible largest-subarray sum, or {@code -1} if
     *         there are more subarrays requested than elements available
     */
    private static int splitArray(int[] nums, int maxSubarrays) {
        if (maxSubarrays > nums.length) {
            return -1;
        }

        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, maxSubarrays, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Checks whether {@code nums} can be split into at most
     * {@code maxSubarrays} contiguous subarrays such that no subarray sum
     * exceeds {@code maxSum}.
     */
    private static boolean isValid(int[] nums, int maxSubarrays, int maxSum) {
        int subarraysUsed = 1;
        int currentSum = 0;

        for (int num : nums) {
            if (currentSum + num <= maxSum) {
                currentSum += num;
            } else {
                subarraysUsed++;
                currentSum = num;
            }
        }

        return subarraysUsed <= maxSubarrays;
    }

    public static void main(String[] args) {
        runExample(new int[]{7, 2, 5, 10, 8}, 2, 18);
        runExample(new int[]{1, 2, 3, 4, 5}, 2, 9);
    }

    private static void runExample(int[] nums, int maxSubarrays, int expected) {
        int actual = splitArray(nums, maxSubarrays);
        String status = actual == expected ? "PASS" : "FAIL";
        System.out.printf("[%s] nums=%s, k=%d -> %d (expected %d)%n",
            status, java.util.Arrays.toString(nums), maxSubarrays, actual, expected);
    }
}