package strings;

/**
 * LeetCode 868 - Binary Gap
 *
 * Find the longest distance between two adjacent 1s in binary representation.
 * If fewer than two 1s exist, return 0.
 *
 * Approach 1: Binary string scan
 *   - Convert to binary string, scan for 1s, track max gap by index difference
 *   - Time: O(log n)   Space: O(log n) — string allocation
 *
 * Approach 2: Bit manipulation — while loop
 *   - Inspect each bit with (n & 1), right shift each iteration
 *   - Explicit currentIdx counter alongside n
 *   - Time: O(log n)   Space: O(1)
 *
 * Approach 3: Bit manipulation — for loop
 *   - Same logic as Approach 2, condensed into a for loop
 *   - Loop condition n > 0 naturally stops when all bits consumed
 *   - Time: O(log n)   Space: O(1)
 */
public class BinaryGap {

    // Approach 1: binary string scan
    public static int binaryGap1(int n) {
        String binary = Integer.toBinaryString(n);
        int maxGap = 0, lastOneIdx = -1;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                if (lastOneIdx != -1) maxGap = Math.max(maxGap, i - lastOneIdx);
                lastOneIdx = i;
            }
        }
        return maxGap;
    }

    // Approach 2: bit manipulation — while loop
    public static int binaryGap2(int n) {
        int maxGap = 0, lastOneIdx = -1, currentIdx = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                if (lastOneIdx != -1) maxGap = Math.max(maxGap, currentIdx - lastOneIdx);
                lastOneIdx = currentIdx;
            }
            currentIdx++;
            n >>>= 1;                               // unsigned shift — safe habit
        }
        return maxGap;
    }

    // Approach 3: bit manipulation — for loop (most compact)
    public static int binaryGap3(int n) {
        int maxGap = 0, lastOneIdx = -1;

        for (int i = 0; n > 0; i++, n >>>= 1) {    // unsigned shift — safe habit
            if ((n & 1) == 1) {
                if (lastOneIdx != -1) maxGap = Math.max(maxGap, i - lastOneIdx);
                lastOneIdx = i;
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        // Test case 1: n=22 → 10110 → gaps: 2,1 → max=2
        System.out.println(binaryGap1(22));    // 2
        System.out.println(binaryGap2(22));    // 2
        System.out.println(binaryGap3(22));    // 2

        // Test case 2: n=5 → 101 → gap: 2 → max=2
        System.out.println(binaryGap1(5));     // 2
        System.out.println(binaryGap2(5));     // 2
        System.out.println(binaryGap3(5));     // 2

        // Test case 3: n=6 → 110 → gap: 1 → max=1
        System.out.println(binaryGap1(6));     // 1
        System.out.println(binaryGap2(6));     // 1
        System.out.println(binaryGap3(6));     // 1

        // Test case 4: n=8 → 1000 → only one 1 → 0
        System.out.println(binaryGap1(8));     // 0
        System.out.println(binaryGap2(8));     // 0
        System.out.println(binaryGap3(8));     // 0

        // Test case 5: n=1 → 1 → only one 1 → 0
        System.out.println(binaryGap1(1));     // 0
        System.out.println(binaryGap2(1));     // 0
        System.out.println(binaryGap3(1));     // 0
    }
}