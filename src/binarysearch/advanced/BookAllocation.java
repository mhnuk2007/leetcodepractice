package binarysearch.advanced;

/**
 * Book Allocation Problem.
 *
 * <p>Given an array of books, where each element represents the number of
 * pages in that book, and {@code k} students, allocate contiguous ranges of
 * books to students such that each book is assigned to exactly one student,
 * and the maximum number of pages assigned to any single student is
 * minimized.
 *
 * <p><b>Search space:</b>
 * <ul>
 *   <li>{@code left} = the maximum page count among all books (a student
 *       holding that book alone needs at least this many pages)</li>
 *   <li>{@code right} = the sum of all page counts (one student takes
 *       everything)</li>
 * </ul>
 *
 * <p><b>Validity check:</b> can the books be allocated to at most {@code k}
 * students such that no student receives more than {@code maxPages}? This
 * is evaluated greedily — keep adding books to the current student's load
 * until the next book would exceed {@code maxPages}, then start a new
 * student.
 *
 * <p>Time: O(n log(sum(pages))) — binary search over the answer range,
 * O(n) feasibility check per iteration.
 * <br>Space: O(1) auxiliary.
 */
public final class BookAllocation {

    private BookAllocation() {
        // utility class; use findMinPages as the entry point
    }

    /**
     * Finds the minimum possible value of the maximum pages assigned to any
     * student, when {@code pages.length} books are allocated in contiguous
     * ranges to {@code students} students.
     *
     * @return the minimum feasible maximum page count, or {@code -1} if
     *         there are more students than books
     */
    private static int findMinPages(int[] pages, int students) {
        if (students > pages.length) {
            return -1;
        }

        int left = 0;
        int right = 0;
        for (int pageCount : pages) {
            left = Math.max(left, pageCount);
            right += pageCount;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(pages, students, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Checks whether {@code pages} can be split among at most
     * {@code maxStudents} students such that no student receives more than
     * {@code maxPages}.
     *
     * <p>Note: no explicit "single book exceeds maxPages" guard is needed
     * here — {@code findMinPages} initializes {@code left} to the largest
     * single book, so every {@code maxPages} value ever queried is already
     * at least that large.
     */
    private static boolean isValid(int[] pages, int maxStudents, int maxPages) {
        int studentsUsed = 1;
        int currentLoad = 0;

        for (int pageCount : pages) {
            if (currentLoad + pageCount <= maxPages) {
                currentLoad += pageCount;
            } else {
                studentsUsed++;
                currentLoad = pageCount;
            }
        }

        return studentsUsed <= maxStudents;
    }

    public static void main(String[] args) {
        runExample(new int[]{12, 34, 67, 90}, 2, 113);
        runExample(new int[]{15, 17, 20}, 5, -1);
    }

    private static void runExample(int[] pages, int students, int expected) {
        int actual = findMinPages(pages, students);
        String status = actual == expected ? "PASS" : "FAIL";
        System.out.printf("[%s] pages=%s, students=%d -> %d (expected %d)%n",
                status, java.util.Arrays.toString(pages), students, actual, expected);
    }
}