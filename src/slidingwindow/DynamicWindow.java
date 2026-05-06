package slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Dynamic Sliding Window — Find Subarray with Sum Equal to Target
 *
 * <p>Given an integer array and a target, find the indices [i, j] of a
 * contiguous subarray whose sum equals the target.
 *
 * <p>Approaches:
 * <ol>
 *   <li>Dynamic Sliding Window — O(n) time, O(1) space
 *       Works only for arrays with positive numbers.
 *       Window expands right, shrinks left when sum exceeds target.</li>
 *   <li>Prefix Sum + HashMap  — O(n) time, O(n) space
 *       Works for arrays with negative numbers too.
 *       Uses prefixSum[j] - prefixSum[i-1] = target → look up prefixSum[j] - target.</li>
 * </ol>
 *
 * <p>Key difference from fixed window:
 * <ul>
 *   <li>Fixed window: shrink exactly when window size == k</li>
 *   <li>Dynamic window: shrink based on a condition (sum > target)</li>
 * </ul>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class DynamicWindow {

    // -------------------------------------------------------------------------
    // Approach 1: Dynamic Sliding Window
    // Time  : O(n) — each element added and removed at most once
    // Space : O(1)
    // Constraint: positive numbers only (shrinking on sum > target is only
    //             valid when all elements are non-negative)
    // -------------------------------------------------------------------------

    /**
     * Finds a subarray with sum equal to target using dynamic sliding window.
     *
     * <p>Expands the window by advancing j. When sum exceeds target, shrinks
     * from the left by advancing i. Returns start and end indices if found.
     *
     * @param nums   input array of positive integers
     * @param target target sum
     * @return int[] {start, end} of the subarray, or empty array if not found
     */
    private static int[] dynamicWindowSum1(int[] nums, int target) {
        int i = 0;
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];                        // expand
            while (sum > target) sum -= nums[i++]; // shrink
            if (sum == target) return new int[]{i, j};
        }
        return new int[0];
    }

    // -------------------------------------------------------------------------
    // Approach 2: Prefix Sum + HashMap
    // Time  : O(n)
    // Space : O(n) — stores prefix sums
    // Constraint: works for arrays with negative numbers
    // -------------------------------------------------------------------------

    /**
     * Finds a subarray with sum equal to target using prefix sum and HashMap.
     *
     * <p>Recurrence: prefixSum[j] - prefixSum[i-1] = target
     * → look up prefixSum[j] - target in the map to find i-1.
     * The map stores {prefixSum → index} for all previously seen prefix sums.
     *
     * @param nums   input array (may contain negatives)
     * @param target target sum
     * @return int[] {start, end} of the subarray, or empty array if not found
     */
    private static int[] dynamicWindowSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0;
        for (int j = 0; j < nums.length; j++) {
            prefixSum += nums[j];
            if (prefixSum == target) return new int[]{0, j};  // subarray from index 0
            if (map.containsKey(prefixSum - target)) {
                int i = map.get(prefixSum - target) + 1;       // i-1 found → start = i
                return new int[]{i, j};
            }
            map.putIfAbsent(prefixSum, j);
        }
        return new int[0];
    }

    // -------------------------------------------------------------------------
    // Helper — print result with indices and subarray values
    // -------------------------------------------------------------------------

    /**
     * Prints the result indices and the corresponding subarray values.
     *
     * @param nums   original array (for value lookup)
     * @param res    int[] {start, end} result from search
     * @param label  label to identify which approach produced the result
     */
    private static void print(int[] nums, int[] res, String label) {
        System.out.print(label + ": ");
        if (res.length == 0) {
            System.out.println("No subarray found");
            return;
        }
        int start = res[0];
        int end   = res[1];
        System.out.println("Indices [" + start + ", " + end + "]"
                + "  →  subarray = " + Arrays.toString(Arrays.copyOfRange(nums, start, end + 1))
                + "  →  sum = " + sumOf(nums, start, end));
    }

    private static int sumOf(int[] nums, int start, int end) {
        int s = 0;
        for (int i = start; i <= end; i++) s += nums[i];
        return s;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: Positive numbers — both approaches
        int[] nums1 = {1, 3, 2, 4, 5, 3, 6, 7};
        int target1 = 16;
        System.out.println("Test 1 (positives, target=" + target1 + "):");
        print(nums1, dynamicWindowSum1(nums1, target1), "  Sliding Window");
        print(nums1, dynamicWindowSum2(nums1, target1), "  Prefix Sum   ");

        System.out.println();

        // Test Case 2: Array with negatives — only Approach 2 valid
        int[] nums2 = {1, 3, -1, -3, 5, 3, 6, 7};
        int target2 = 16;
        System.out.println("Test 2 (with negatives, target=" + target2 + "):");
        System.out.println("  Sliding Window: N/A — negatives not supported");
        print(nums2, dynamicWindowSum2(nums2, target2), "  Prefix Sum   ");

        System.out.println();

        // Test Case 3: Target at beginning
        int[] nums3 = {5, 3, 2, 1, 4};
        int target3 = 5;
        System.out.println("Test 3 (target at start, target=" + target3 + "):");
        print(nums3, dynamicWindowSum1(nums3, target3), "  Sliding Window");
        print(nums3, dynamicWindowSum2(nums3, target3), "  Prefix Sum   ");

        System.out.println();

        // Test Case 4: No subarray found
        int[] nums4 = {1, 2, 3};
        int target4 = 10;
        System.out.println("Test 4 (not found, target=" + target4 + "):");
        print(nums4, dynamicWindowSum1(nums4, target4), "  Sliding Window");
        print(nums4, dynamicWindowSum2(nums4, target4), "  Prefix Sum   ");

        System.out.println();

        // Test Case 5: Single element equals target
        int[] nums5 = {3, 1, 4, 2};
        int target5 = 4;
        System.out.println("Test 5 (single element, target=" + target5 + "):");
        print(nums5, dynamicWindowSum1(nums5, target5), "  Sliding Window");
        print(nums5, dynamicWindowSum2(nums5, target5), "  Prefix Sum   ");

        System.out.println();

        // Test Case 6: Entire array is the target
        int[] nums6 = {2, 3, 1, 4};
        int target6 = 10;
        System.out.println("Test 6 (full array, target=" + target6 + "):");
        print(nums6, dynamicWindowSum1(nums6, target6), "  Sliding Window");
        print(nums6, dynamicWindowSum2(nums6, target6), "  Prefix Sum   ");
    }
}