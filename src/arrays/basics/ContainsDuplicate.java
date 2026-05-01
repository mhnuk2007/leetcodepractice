package arrays;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        // Test 1: Has duplicates
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1: " + containsDuplicate(nums1));  // true

        // Test 2: No duplicates
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test 2: " + containsDuplicate(nums2));  // false

        // Test 3: All duplicates
        int[] nums3 = {1, 1, 1, 1};
        System.out.println("Test 3: " + containsDuplicate(nums3));  // true

        // Test 4: Empty array
        int[] nums4 = {};
        System.out.println("Test 4: " + containsDuplicate(nums4));  // false

        // Test 5: Single element
        int[] nums5 = {1};
        System.out.println("Test 5: " + containsDuplicate(nums5));  // false

        // Test 6: Duplicate at end
        int[] nums6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        System.out.println("Test 6: " + containsDuplicate(nums6));  // true

        // Test 7: Negative numbers
        int[] nums7 = {-1, -2, -3, -1};
        System.out.println("Test 7: " + containsDuplicate(nums7));  // true
    }

    /**
     * Check if array contains any duplicates
     *
     * Approach: Use HashSet to track seen elements
     * - If element already in set → duplicate found
     * - Otherwise → add to set and continue
     *
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(n) - worst case, store all elements
     *
     * @param nums input array
     * @return true if any value appears at least twice
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return true;  // Early termination
            }
            seen.add(num);
        }

        return false;  // No duplicates found
    }
}