package binarysearch;

/**
 * LeetCode 69 - Sqrt(x)
 *
 * Problem:
 * Given a non-negative integer x, return the square root of x rounded down
 * to the nearest integer. The returned integer should be non-negative.
 * You must not use any built-in exponent function or operator.
 *
 * Example 1:
 *   Input : x = 4
 *   Output: 2
 *
 * Example 2:
 *   Input : x = 8
 *   Output: 2  (sqrt(8) = 2.82..., floored to 2)
 *
 * Example 3:
 *   Input : x = 0
 *   Output: 0
 *
 * Approach: Binary Search
 *   - Search space is [1, x].
 *   - Find the largest 'mid' such that mid * mid <= x.
 *   - Use (long) cast to prevent integer overflow during multiplication.
 *   - mid = low + (high - low) / 2 avoids overflow in mid calculation itself.
 *
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */
public class SqrtX {

    public static void main(String[] args) {
        // Test 1: perfect square
        System.out.println(mySqrt(4));           // 2

        // Test 2: perfect square
        System.out.println(mySqrt(16));          // 4

        // Test 3: non-perfect square, floor down
        System.out.println(mySqrt(8));           // 2

        // Test 4: zero edge case
        System.out.println(mySqrt(0));           // 0

        // Test 5: one edge case
        System.out.println(mySqrt(1));           // 1

        // Test 6: large value — overflow risk if not using long
        System.out.println(mySqrt(2147483647));  // 46340
    }

    private static int mySqrt(int x) {
        if (x == 0) return 0;

        int low = 1, high = x, result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2; // safe mid to avoid overflow

            if ((long) mid * mid == x) {
                return mid;                   // exact perfect square found
            } else if ((long) mid * mid < x) {
                result = mid;                 // valid floor candidate, try larger
                low = mid + 1;
            } else {
                high = mid - 1;              // mid too large, search lower half
            }
        }

        return result; // largest integer whose square is <= x
    }
}