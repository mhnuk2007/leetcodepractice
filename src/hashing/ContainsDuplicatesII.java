package hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 219 - Contains Duplicate II
 *
 * Problem:
 * Given an integer array nums and an integer k, return true if there exist
 * two distinct indices i and j such that:
 *   - nums[i] == nums[j]
 *   - abs(i - j) <= k
 *
 * Example 1:
 *   Input : nums = [1,2,3,1], k = 3  →  Output: true
 *   Explanation: nums[0]=nums[3]=1, distance=3 <= k=3 ✅
 *
 * Example 2:
 *   Input : nums = [1,0,1,1], k = 1  →  Output: true
 *   Explanation: nums[2]=nums[3]=1, distance=1 <= k=1 ✅
 *
 * Example 3:
 *   Input : nums = [1,2,3,1,2,3], k = 2  →  Output: false
 *   Explanation: all duplicates are more than k=2 apart
 *
 * Approach 1 - HashMap (num → last seen index): O(n) time, O(n) space
 *   Store each number's most recent index.
 *   On revisit, check if i - storedIndex <= k in one combined condition.
 *   Always update to latest index after check — keeps distance minimal.
 *   No Math.abs() needed — left to right scan guarantees i >= stored index.
 *
 * Approach 2 - Sliding Window HashSet: O(n) time, O(k) space ← optimal space
 *   Maintain a window of exactly k elements using a HashSet.
 *   Any duplicate found inside the window is within k distance by definition.
 *   Evict nums[i - k] when window exceeds size k.
 *   Preferred when k << n and memory is constrained.
 *
 * Time Complexity : O(n)
 * Space Complexity: O(n) Approach 1 | O(k) Approach 2
 */
public class ContainsDuplicatesII {

    public static void main(String[] args) {
        // Test 1: duplicate within range — distance 3 = k
        System.out.println(containsNearbyDuplicateMap(new int[]{1,2,3,1}, 3));     // true

        // Test 2: adjacent duplicates — distance 1 = k
        System.out.println(containsNearbyDuplicateMap(new int[]{1,0,1,1}, 1));     // true

        // Test 3: duplicates exist but all exceed k distance
        System.out.println(containsNearbyDuplicateMap(new int[]{1,2,3,1,2,3}, 2)); // false

        // Test 4: k=0 — i != j required, distance can never be 0
        System.out.println(containsNearbyDuplicateMap(new int[]{1,2,3}, 0));       // false

        // Test 5: single element — no duplicate possible
        System.out.println(containsNearbyDuplicateMap(new int[]{1}, 1));           // false

        // Test 6: all same elements — always true for k >= 1
        System.out.println(containsNearbyDuplicateMap(new int[]{2,2,2,2}, 1));     // true
    }

    // Approach 1 — HashMap: num → last seen index
    // O(n) time | O(n) space
    // Combined condition: containsKey + distance check in one line
    // Always update index unconditionally — ensures closest pair is tracked
    public static boolean containsNearbyDuplicateMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // if seen before AND within k distance → found
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) return true;
            map.put(nums[i], i); // always update to latest index
        }

        return false;
    }

    // Approach 2 — Sliding Window HashSet
    // O(n) time | O(k) space ← better when k << n
    // Window of size k — any duplicate within window is within k distance
    public static boolean containsNearbyDuplicateSet(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (window.contains(nums[i])) return true; // duplicate in window

            window.add(nums[i]);

            if (window.size() > k) {
                window.remove(nums[i - k]); // evict element leaving the window
            }
        }

        return false;
    }
}