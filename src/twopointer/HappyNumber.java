package twopointer;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 202: Happy Number
 * <p>
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * 1. Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * 2. Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * 3. Those numbers for which this process ends in 1 are happy.
 * <p>
 * Return true if n is a happy number, and false if not.
 * <p>
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * Example 2:
 * Input: n = 2
 * Output: false
 */
public class HappyNumber {

    public static void main(String[] args) {
        HappyNumber solution = new HappyNumber();

        System.out.println("=== Floyd's Cycle Detection (Optimal) ===");
        System.out.println("Test 1 (19): " + solution.isHappy(19));     // true
        System.out.println("Test 2 (2):  " + solution.isHappy(2));      // false
        System.out.println("Test 3 (1):  " + solution.isHappy(1));      // true
        System.out.println("Test 4 (7):  " + solution.isHappy(7));      // true
        System.out.println("Test 5 (4):  " + solution.isHappy(4));      // false
        System.out.println("Test 6 (100): " + solution.isHappy(100));   // true
        System.out.println("Test 7 (999): " + solution.isHappy(999));   // false

        System.out.println("\n=== HashSet Approach (Alternative) ===");
        System.out.println("Test 1 (19): " + solution.isHappyHashSet(19));  // true
        System.out.println("Test 2 (2):  " + solution.isHappyHashSet(2));   // false
        System.out.println("Test 3 (1):  " + solution.isHappyHashSet(1));   // true

        System.out.println("\n=== Detailed Trace for n=19 ===");
        solution.traceHappyNumber(19);
    }

    /**
     * Approach 1: Floyd's Cycle Detection (OPTIMAL)
     * <p>
     * Uses slow/fast pointers like detecting cycle in linked list.
     * - If happy: eventually reaches 1 (slow == fast == 1)
     * - If not happy: enters cycle (slow == fast != 1)
     * <p>
     * Time: O(log n) - digits reduce logarithmically
     * Space: O(1) - only two pointers
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n); // Start fast one step ahead to enter loop correctly

        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    /**
     * Approach 2: HashSet (Alternative)
     * <p>
     * Track seen numbers to detect cycles.
     * <p>
     * Time: O(log n)
     * Space: O(log n) - stores seen numbers
     */
    public boolean isHappyHashSet(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    /**
     * Calculate sum of squares of digits
     * <p>
     * Example: 19 → 1² + 9² = 1 + 81 = 82
     */
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * Helper method to trace the happy number sequence
     */
    public void traceHappyNumber(int n) {
        System.out.println("Starting with: " + n);
        Set<Integer> seen = new HashSet<>();
        int step = 0;

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            int next = getNext(n);
            System.out.println("Step " + (++step) + ": " + n + " → " +
                    getDigitSquaresString(n) + " = " + next);
            n = next;
        }

        if (n == 1) {
            System.out.println("Step " + (++step) + ": " + n + " (Happy! ✓)");
        } else {
            System.out.println("Cycle detected at: " + n + " (Not Happy ✗)");
        }
    }

    private String getDigitSquaresString(int n) {
        StringBuilder sb = new StringBuilder();
        String numStr = String.valueOf(n);

        for (int i = 0; i < numStr.length(); i++) {
            int digit = numStr.charAt(i) - '0';
            sb.append(digit).append("²");
            if (i < numStr.length() - 1) {
                sb.append(" + ");
            }
        }

        return sb.toString();
    }
}
