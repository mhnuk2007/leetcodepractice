package arrays;

/**
 * LeetCode 169: Majority Element
 * <p>
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {

    /**
     * Finds the majority element in an array using the Boyer-Moore Voting Algorithm.
     * <p>
     * The algorithm works by maintaining a `candidate` for the majority element and a `count`.
     * It iterates through the array:
     * - If `count` is 0, it sets the current element as the new `candidate`.
     * - If the current element is the same as the `candidate`, it increments `count`.
     * - If the current element is different, it decrements `count`.
     * <p>
     * Because the majority element appears more than n/2 times, it is guaranteed to be the
     * final `candidate` after the single pass. This algorithm is highly efficient,
     * running in O(n) time and O(1) space.
     *
     * @param nums The input array where a majority element is guaranteed to exist.
     * @return The majority element.
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();

        // Test Case 1
        int[] nums1 = {3, 2, 3};
        System.out.println("Test Case 1 (Input: [3,2,3]): " + solution.majorityElement(nums1)); // Expected: 3

        // Test Case 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Test Case 2 (Input: [2,2,1,1,1,2,2]): " + solution.majorityElement(nums2)); // Expected: 2

        // Test Case 3: Majority element at the beginning
        int[] nums3 = {6, 5, 5};
        System.out.println("Test Case 3 (Input: [6,5,5]): " + solution.majorityElement(nums3)); // Expected: 5
    }
}
