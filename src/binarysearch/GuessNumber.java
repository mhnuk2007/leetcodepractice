package binarysearch;

/**
 * LeetCode 374 — Guess Number Higher or Lower
 * <p>
 * Problem: A number 1..n is picked. You call guess(int num) which returns:
 * 0  → correct guess
 * -1  → your guess is too high   (go lower  → right = mid - 1)
 * 1  → your guess is too low    (go higher → left  = mid + 1)
 * Return the picked number.
 * <p>
 * Approach: Template 1 binary search on range [1, n].
 * The API return value directly tells us which half to search.
 * No manual comparison needed — guess() does it for us.
 * <p>
 * Common mistake: confusing -1 and 1 return values.
 * -1 means YOUR guess is HIGH → search lower  → right = mid - 1
 * 1 means YOUR guess is LOW  → search higher → left  = mid + 1
 * <p>
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */

// ── Simulated GuessGame base class (LeetCode provides this) ──────────────────
abstract class GuessGame {
    private int picked;

    GuessGame(int picked) {
        this.picked = picked;
    }

    protected int guess(int num) {
        if (num == picked) return 0;  // correct
        else if (num > picked) return -1;  // too high
        else return 1;  // too low
    }
}

class Solution extends GuessGame {

    Solution(int picked) {
        super(picked);
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);

            if (result == 0) return mid;        // correct — found
            else if (result == -1) right = mid - 1;   // too high  — go lower
            else left = mid + 1;   // too low   — go higher
        }
        return -1; // never reached — picked number always in [1,n]
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard — n=10, picked=6
        System.out.println("=== Test 1: n=10, picked=6 ===");
        System.out.println(new Solution(6).guessNumber(10)); // 6

        // Test 2: n=1, picked=1 — single element
        System.out.println("\n=== Test 2: n=1, picked=1 ===");
        System.out.println(new Solution(1).guessNumber(1));  // 1

        // Test 3: picked is the first number
        System.out.println("\n=== Test 3: n=10, picked=1 ===");
        System.out.println(new Solution(1).guessNumber(10)); // 1

        // Test 4: picked is the last number
        System.out.println("\n=== Test 4: n=10, picked=10 ===");
        System.out.println(new Solution(10).guessNumber(10)); // 10

        // Test 5: large n — checks overflow safety of mid formula
        System.out.println("\n=== Test 5: n=2147483647, picked=1702766719 ===");
        System.out.println(new Solution(1702766719).guessNumber(2147483647)); // 1702766719
    }
}