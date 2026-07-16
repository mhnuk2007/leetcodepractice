package arrays.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56 — Merge Intervals
 *
 * <p>Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping
 * intervals, and return an array of the non-overlapping intervals that cover all the
 * intervals in the input.
 *
 * <p><b>Approach — Greedy with Sorting:</b><br>
 * 1. Sort the intervals by their start coordinates. This ensures that any intervals that
 *    can be merged are adjacent in the sorted array.
 * 2. Iterate through the sorted intervals. Keep track of the current interval's start
 *    and end.
 * 3. For each subsequent interval, if its start is less than or equal to the current end,
 *    it overlaps, so we merge it by updating the current end to `Math.max(current_end, next_end)`.
 * 4. If it does not overlap, we add the current interval to the result and start a new
 *    interval.
 *
 * <p>Time Complexity  — O(N log N): where N is the number of intervals. Sorting dominates.
 * <p>Space Complexity — O(N): for storing the output list/array.
 *
 * @see <a href="https://leetcode.com/problems/merge-intervals/">LC 56</a>
 */
public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        int n = intervals.length;
        List<int[]> result = new ArrayList<>();

        // Sort by start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int i = 0;
        while (i < n) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int j = i + 1;

            // Merge overlapping intervals
            while (j < n && end >= intervals[j][0]) {
                if (end < intervals[j][1]) {
                    end = intervals[j][1];
                }
                j++;
            }

            result.add(new int[]{start, end});
            i = j;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // Test 1: Standard case with overlaps
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = merge(intervals1);
        System.out.println("Test 1: " + Arrays.deepToString(result1)); 
        // Expected: [[1, 6], [8, 10], [15, 18]]

        // Test 2: Nested intervals
        int[][] intervals2 = {{1, 4}, {2, 3}};
        int[][] result2 = merge(intervals2);
        System.out.println("Test 2: " + Arrays.deepToString(result2)); 
        // Expected: [[1, 4]]

        // Test 3: No overlaps
        int[][] intervals3 = {{1, 2}, {3, 4}, {5, 6}};
        int[][] result3 = merge(intervals3);
        System.out.println("Test 3: " + Arrays.deepToString(result3)); 
        // Expected: [[1, 2], [3, 4], [5, 6]]

        // Test 4: Single interval
        int[][] intervals4 = {{1, 5}};
        int[][] result4 = merge(intervals4);
        System.out.println("Test 4: " + Arrays.deepToString(result4)); 
        // Expected: [[1, 5]]
    }
}
