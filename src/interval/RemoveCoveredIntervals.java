package interval;

import java.util.Arrays;

public class RemoveCoveredIntervals {
    public static int removeCoveredIntervals(int[][] intervals) {
        int count = 0;

        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            boolean isCovered = false;
            for (int j = 0; j < n; j++) {
                if (i != j &&
                        intervals[j][0] <= intervals[i][0] &&
                        intervals[j][1] >= intervals[i][1]) {
                    isCovered = true;
                    break;
                }

            }
            if (!isCovered) count++;

        }
        return count;

    }

    public static int removeCoveredIntervalsOpt(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 1;
        int maxEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][1] > maxEnd) {
                count++;
                maxEnd = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 4},
                {3, 6},
                {2, 8}
        };
        int[][] intervals2 = {
                {1,4},
                {1,5},
                {1,3}
        };

        System.out.println(removeCoveredIntervals(intervals));
        System.out.println(removeCoveredIntervalsOpt(intervals));
        System.out.println(removeCoveredIntervals(intervals2));
        System.out.println(removeCoveredIntervalsOpt(intervals2));

    }
}
