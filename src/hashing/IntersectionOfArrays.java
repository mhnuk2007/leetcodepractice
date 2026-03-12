package hashing;

import java.util.*;

/**
 * LeetCode 349 - Intersection of Two Arrays
 *
 * Problem:
 * Given two integer arrays nums1 and nums2, return an array of their
 * intersection. Each element in the result must be unique, and the
 * result can be returned in any order.
 *
 * Example 1:
 *   Input : nums1 = [1,2,2,1], nums2 = [2,2]
 *   Output: [2]
 *
 * Example 2:
 *   Input : nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *   Output: [9,4]  (order may vary)
 *
 * Approach 1 - Sort + Two Pointers: O(n log n + m log m) time, O(n) space
 *   Sort both arrays. Walk two pointers inward:
 *   - Equal → add to result set (Set handles duplicates), advance both.
 *   - nums1[i] > nums2[j] → advance j.
 *   - nums1[i] < nums2[j] → advance i.
 *
 * Approach 2 - Two HashSets + retainAll: O(n + m) time, O(n + m) space
 *   Add each array to its own Set (deduplicates automatically).
 *   retainAll() keeps only elements common to both sets.
 *   Most concise — one line for the intersection logic.
 *
 * Approach 3 - HashSet + List: O(n + m) time, O(n) space  ← optimal
 *   Add nums1 to a Set. Scan nums2 — if element found in Set,
 *   add to result and remove from Set to prevent duplicate matches.
 *   Only one Set needed → better space than Approach 2.
 */
public class IntersectionOfArrays {

    public static void main(String[] args) {
        // Test 1: standard case
        System.out.println(Arrays.toString(
            intersectionHashSet(new int[]{4,9,5}, new int[]{9,4,9,8,4}))); // [9,4]

        // Test 2: one common element
        System.out.println(Arrays.toString(
            intersectionHashSet(new int[]{1,2,2,1}, new int[]{2,2})));     // [2]

        // Test 3: no intersection
        System.out.println(Arrays.toString(
            intersectionHashSet(new int[]{1,2,3}, new int[]{4,5,6})));     // []

        // Test 4: identical arrays
        System.out.println(Arrays.toString(
            intersectionHashSet(new int[]{1,2,3}, new int[]{1,2,3})));     // [1,2,3]

        // Test 5: one empty array
        System.out.println(Arrays.toString(
            intersectionHashSet(new int[]{}, new int[]{1,2,3})));          // []

        // Test 6: all duplicates in both
        System.out.println(Arrays.toString(
            intersectionHashSet(new int[]{1,1,1}, new int[]{1,1,1})));     // [1]
    }

    // Approach 1 — Sort + Two Pointers
    // O(n log n + m log m) time | O(n) space
    // Best when: arrays are already sorted or sorting cost is acceptable
    public static int[] intersectionTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        Set<Integer> set = new HashSet<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]); // Set deduplicates automatically
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;               // nums2 is behind, advance it
            } else {
                i++;               // nums1 is behind, advance it
            }
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) result[index++] = num;
        return result;
    }

    // Approach 2 — Two HashSets + retainAll
    // O(n + m) time | O(n + m) space
    // Best when: code brevity is preferred
    public static int[] intersectionRetainAll(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);

        // retainAll modifies set1 to keep only elements also in set2
        set1.retainAll(set2);

        int[] result = new int[set1.size()];
        int index = 0;
        for (int num : set1) result[index++] = num;
        return result;
    }

    // Approach 3 — HashSet + List  ← optimal
    // O(n + m) time | O(n) space
    // Best when: space efficiency matters — only one Set needed
    public static int[] intersectionHashSet(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) set.add(num); // load nums1 into set

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            // If the element is in the set, remove it and add to the list.
            // The remove() method returns true if the element was present.
            if (set.remove(num)) {
                list.add(num);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);
        return result;
    }
}
