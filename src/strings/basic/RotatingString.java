package strings.basic;

public class RotatingString {
    /**
     * LeetCode 796 - Rotate String
     *
     * <p>Given two strings s and goal, return true if and only if s can become goal
     * after some number of shifts on s.
     *
     * <p>Approach: String Concatenation
     * <ul>
     *   <li>If lengths differ, they can never be rotations.</li>
     *   <li>If s is rotated, it must be a substring of (s + s).</li>
     * </ul>
     *
     * <p>Complexity:
     * <ul>
     *   <li>Time  : O(n) — where n is the length of string s (contains() uses KMP-like logic)</li>
     *   <li>Space : O(n) — to store the concatenated string</li>
     * </ul>
     */
    public static boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    public static void main(String[] args) {
        // Test Case 1: True rotation
        System.out.println("Test 1: " + rotateString("abcde", "cdeab")); // true

        // Test Case 2: False rotation
        System.out.println("Test 2: " + rotateString("abcde", "abced")); // false

        // Test Case 3: Different lengths
        System.out.println("Test 3: " + rotateString("a", "ab"));       // false

        // Test Case 4: Empty strings
        System.out.println("Test 4: " + rotateString("", ""));           // true
    }

}
