package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode: 739 — Daily Temperatures
 *
 * Problem: Given an array of daily temperatures, return an array where
 * each element is the number of days to wait for a warmer temperature.
 * If no warmer day exists, the answer is 0.
 *
 * Example:
 * Input : [73, 74, 75, 71, 69, 72, 76, 73]
 * Output: [ 1,  1,  4,  2,  1,  1,  0,  0]
 */
public class DailyTemperatures {

    // ─── Approach 1: Brute Force ──────────────────────────────────────────────
    /**
     * For each day scan every day ahead until a warmer one is found.
     *
     * Time Complexity : O(n²)
     * Space Complexity: O(1) — no extra space beyond output
     */
    public static int[] dailyTemperaturesBrute(int[] temperatures) {
        int n = temperatures.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    days[i] = j - i;
                    break;
                }
            }
        }
        return days;
    }

    // ─── Approach 2: Monotonic Stack ─────────────────────────────────────────
    /**
     * Maintain a stack of indices with unresolved temperatures (decreasing).
     * For each day pop all cooler indices — their wait is current index minus
     * stored index.
     *
     * Time Complexity : O(n) — each index pushed and popped at most once
     * Space Complexity: O(n) — stack holds at most n indices
     */
    public static int[] dailyTemperaturesStack(int[] temperatures) {
        int n = temperatures.length;
        int[] days = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                days[index] = i - index;
            }
            stack.push(i);
        }
        return days;
    }

    // ─── Approach 3: One Pass from the End ───────────────────────────────────
    /**
     * Traverse right to left. For each day jump ahead using already computed
     * results to skip days that cannot be the answer.
     *
     * If temperatures[j] is not warmer than temperatures[i]:
     *   - if days[j] == 0, no warmer day exists beyond j → stop
     *   - otherwise jump ahead by days[j] to the next candidate
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1) — no extra space beyond output
     */
    public static int[] dailyTemperaturesRevTraverse(int[] temperatures) {
        int n = temperatures.length;
        int[] days = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n) {
                if (temperatures[j] > temperatures[i]) {
                    days[i] = j - i; // found warmer day
                    break;
                }
                if (days[j] == 0) break; // no warmer day exists beyond j
                j += days[j];            // jump to next candidate
            }
        }
        return days;
    }

    public static void main(String[] args) {

        // Test 1: Standard case
        System.out.println("=== Test 1: Standard case ===");
        int[] t1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Brute  : " + Arrays.toString(dailyTemperaturesBrute(t1)));
        System.out.println("Stack  : " + Arrays.toString(dailyTemperaturesStack(t1)));
        System.out.println("Reverse: " + Arrays.toString(dailyTemperaturesRevTraverse(t1)));
        // expected: [1, 1, 4, 2, 1, 1, 0, 0]

        // Test 2: Strictly increasing
        System.out.println("\n=== Test 2: Strictly increasing ===");
        int[] t2 = {30, 40, 50, 60};
        System.out.println("Brute  : " + Arrays.toString(dailyTemperaturesBrute(t2)));
        System.out.println("Stack  : " + Arrays.toString(dailyTemperaturesStack(t2)));
        System.out.println("Reverse: " + Arrays.toString(dailyTemperaturesRevTraverse(t2)));
        // expected: [1, 1, 1, 0]

        // Test 3: Strictly decreasing — all zeros
        System.out.println("\n=== Test 3: Strictly decreasing ===");
        int[] t3 = {90, 80, 70, 60};
        System.out.println("Brute  : " + Arrays.toString(dailyTemperaturesBrute(t3)));
        System.out.println("Stack  : " + Arrays.toString(dailyTemperaturesStack(t3)));
        System.out.println("Reverse: " + Arrays.toString(dailyTemperaturesRevTraverse(t3)));
        // expected: [0, 0, 0, 0]

        // Test 4: Single element
        System.out.println("\n=== Test 4: Single element ===");
        int[] t4 = {73};
        System.out.println("Brute  : " + Arrays.toString(dailyTemperaturesBrute(t4)));
        System.out.println("Stack  : " + Arrays.toString(dailyTemperaturesStack(t4)));
        System.out.println("Reverse: " + Arrays.toString(dailyTemperaturesRevTraverse(t4)));
        // expected: [0]

        // Test 5: All equal — all zeros
        System.out.println("\n=== Test 5: All equal ===");
        int[] t5 = {50, 50, 50, 50};
        System.out.println("Brute  : " + Arrays.toString(dailyTemperaturesBrute(t5)));
        System.out.println("Stack  : " + Arrays.toString(dailyTemperaturesStack(t5)));
        System.out.println("Reverse: " + Arrays.toString(dailyTemperaturesRevTraverse(t5)));
        // expected: [0, 0, 0, 0]
    }
}