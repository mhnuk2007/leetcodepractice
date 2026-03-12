package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 599 - Minimum Index Sum of Two Lists
 *
 * Problem:
 * Given two string arrays list1 and list2, find all strings that appear
 * in both lists with the minimum index sum (i + j), where i is the index
 * in list1 and j is the index in list2.
 * Return all such strings. If multiple strings tie, return all of them.
 *
 * Example 1:
 *   list1 = ["Shogun","Tapioca Express","Burger King","KFC"]
 *   list2 = ["Piatti","The Grill","Hungry Hunter","Shogun"]
 *   Output: ["Shogun"]  → index sum = 0+3 = 3 (only common element)
 *
 * Example 2:
 *   list1 = ["Shogun","Tapioca Express","Burger King","KFC"]
 *   list2 = ["KFC","Shogun","Burger King"]
 *   Output: ["Shogun"]  → index sum = 0+1 = 1 (minimum)
 *
 * Example 3:
 *   list1 = ["happy","sad","good"]
 *   list2 = ["sad","happy","good"]
 *   Output: ["sad","happy"]  → both have index sum = 1 (tied minimum)
 *
 * Approach: HashMap + Single Pass
 *   Step 1: Load list1 into a HashMap (string → index) in O(n).
 *   Step 2: Scan list2 once. For each string found in the map:
 *     - Compute indexSum = list1Index + j
 *     - If indexSum < minSum → reset result list, add string, update minSum
 *     - If indexSum == minSum → append to result list (tie case)
 *     - If indexSum > minSum → skip
 *   No sorting, no second pass, result built on the fly.
 *
 * Why no need to sort or post-process?
 *   minSum is updated greedily as we scan — by the time list2 is exhausted,
 *   result already contains exactly the strings with the minimum index sum.
 *
 * Time Complexity : O(n + m) — one pass each through list1 and list2
 * Space Complexity: O(n) — HashMap stores list1 entries only
 */
public class MinimumIndexSum {

    public static void main(String[] args) {
        // Test 1: single common element
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"Piatti", "The Grill", "Hungry Hunter", "Shogun"}
        ))); // [Shogun]

        // Test 2: multiple common, one has minimum index sum
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KFC", "Shogun", "Burger King"}
        ))); // [Shogun]

        // Test 3: tie — two strings share the minimum index sum
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"happy", "sad", "good"},
                new String[]{"sad", "happy", "good"}
        ))); // [sad, happy]

        // Test 4: first elements match — minimum possible index sum = 0
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"a", "b", "c"},
                new String[]{"a", "x", "y"}
        ))); // [a]

        // Test 5: only one element in each list, they match
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"abc"},
                new String[]{"abc"}
        ))); // [abc]

        // Test 6: all elements common — minimum is first element of each
        System.out.println(Arrays.toString(findRestaurant(
                new String[]{"a", "b", "c"},
                new String[]{"a", "b", "c"}
        ))); // [a]
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {

        // Step 1: load list1 into map — string → index
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        List<String> result = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;

        // Step 2: single pass through list2
        for (int j = 0; j < list2.length; j++) {
            if (!map.containsKey(list2[j])) continue; // not common, skip

            int indexSum = j + map.get(list2[j]);

            if (indexSum < minSum) {
                minSum = indexSum;
                result.clear();            // found better — discard previous
                result.add(list2[j]);
            } else if (indexSum == minSum) {
                result.add(list2[j]);      // tie — keep both
            }
            // indexSum > minSum → skip
        }

        // toArray(new String[0]) — JVM allocates correct size automatically
        return result.toArray(new String[0]);
    }
}