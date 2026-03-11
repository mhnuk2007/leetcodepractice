package twopointer;

import java.util.Map;

/**
 * LeetCode 246 - Strobogrammatic Number
 * <p>
 * A strobogrammatic number looks the same when rotated 180 degrees.
 * Valid digit pairs (left -> right when rotated): 0->0, 1->1, 6->9, 8->8, 9->6
 * <p>
 * Approach: Two pointers from both ends, check each pair against the valid map.
 * Time: O(n), Space: O(1)
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = Map.of
                (
                        '0', '0',
                        '1', '1',
                        '6', '9',
                        '8', '8',
                        '9', '6'
                );
        int i = 0;
        int j = num.length() - 1;
        while (i <= j) {
            char left = num.charAt(i);
            char right = num.charAt(j);
            if (!map.containsKey(left) || map.get(left) != right) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        StrobogrammaticNumber sol = new StrobogrammaticNumber();

        // Test 1: "69" → true
        System.out.println(sol.isStrobogrammatic("69"));   // true

        // Test 2: "88" → true
        System.out.println(sol.isStrobogrammatic("88"));   // true

        // Test 3: "962" → false
        System.out.println(sol.isStrobogrammatic("962"));  // false

        // Test 4: "1" → true (single digit, middle element)
        System.out.println(sol.isStrobogrammatic("1"));    // true

        // Test 5: "2" → false
        System.out.println(sol.isStrobogrammatic("2"));    // false

        // Test 6: "818" → true
        System.out.println(sol.isStrobogrammatic("818"));  // true

        // Test 7: "619" → true
        System.out.println(sol.isStrobogrammatic("619"));  // true
    }
}