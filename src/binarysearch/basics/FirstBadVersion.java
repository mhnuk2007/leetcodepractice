package binarysearch;

/**
 * LeetCode 278 — First Bad Version
 *
 * Problem:
 * You are given n versions [1 ... n].
 * You have an API:
 *      boolean isBadVersion(int version)
 *
 * Once a version is bad, all versions after it are also bad.
 * Return the FIRST bad version.
 *
 * Approach:
 * Binary Search on boundary (First True).
 *
 * Pattern:
 * Good → Good → Good → Bad → Bad
 *                         ↑
 *                 first bad version
 *
 * Key Idea:
 * - If mid is bad → answer could be mid → move left → right = mid
 * - If mid is good → answer must be right → left = mid + 1
 *
 * Why NOT use right = mid - 1?
 * → Because mid might be the first bad version
 *
 * Loop Condition:
 * - Use (left < right) to avoid skipping answer
 *
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */

// ── Simulated VersionControl base class (LeetCode provides this) ─────────────
abstract class VersionControl {
    private int firstBad;

    VersionControl(int firstBad) {
        this.firstBad = firstBad;
    }

    protected boolean isBadVersion(int version) {
        return version >= firstBad;
    }
}

// ── Solution Class ───────────────────────────────────────────────────────────
class Solution1 extends VersionControl {

    Solution1(int firstBad) {
        super(firstBad);
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                right = mid;       // keep mid
            } else {
                left = mid + 1;    // discard mid
            }
        }

        return left;
    }

    public int firstBadVersion2(int n){
        int left = 1, right = n, fbvIdx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                fbvIdx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return fbvIdx;
    }

    // ── Test Cases ──────────────────────────────────────────────────────────
    public static void main(String[] args) {

        // Test 1: Standard case
        System.out.println("=== Test 1: n=5, firstBad=4 ===");
        System.out.println(new Solution1(4).firstBadVersion(5)); // 4
        System.out.println(new Solution1(4).firstBadVersion2(5)); // 4

        // Test 2: First version is bad
        System.out.println("\n=== Test 2: n=5, firstBad=1 ===");
        System.out.println(new Solution1(1).firstBadVersion(5)); // 1
        System.out.println(new Solution1(1).firstBadVersion2(5)); // 1

        // Test 3: Last version is bad
        System.out.println("\n=== Test 3: n=5, firstBad=5 ===");
        System.out.println(new Solution1(5).firstBadVersion(5)); // 5
        System.out.println(new Solution1(5).firstBadVersion2(5)); // 5

        // Test 4: Single element
        System.out.println("\n=== Test 4: n=1, firstBad=1 ===");
        System.out.println(new Solution1(1).firstBadVersion(1)); // 1
        System.out.println(new Solution1(1).firstBadVersion2(1)); // 1

        // Test 5: Large n (checks overflow safety)
        System.out.println("\n=== Test 5: n=2147483647, firstBad=1702766719 ===");
        System.out.println(new Solution1(1702766719)
                .firstBadVersion(2147483647)); // 1702766719
        System.out.println(new Solution1(1702766719)
                .firstBadVersion2(2147483647)); // 1702766719

    }
}