package dailychallenges;

import java.util.Arrays;

public class FindUniqueBinaryString {
    public static void main(String[] args) {
        String[] nums1 = {"00", "10"};
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println(findDifferentBinaryString(nums1));  // Output: "11"

        String[] nums2 = {"111", "011", "001"};
        System.out.println("\nnums2: " + Arrays.toString(nums2));
        System.out.println(findDifferentBinaryString(nums2));  // Output: "000"

        String[] nums3 = {"01", "10"};
        System.out.println("\nnums3: " + Arrays.toString(nums3));
        System.out.println(findDifferentBinaryString(nums3));  // Output: "00"
    }

    /**
     * Cantor's Diagonal Argument
     * <p>
     * Strategy: For each position i, flip the bit at nums[i][i]
     * Guarantee: Result differs from nums[i] at position i
     * Therefore: Result cannot be any of the input strings
     * <p>
     * Example: ["00", "10"]
     * Position 0: nums[0][0] = '0' → flip to '1'
     * Position 1: nums[1][1] = '0' → flip to '1'
     * Result: "11" (differs from "00" at pos 0, from "10" at pos 1)
     * <p>
     * Time Complexity: O(n) where n = nums.length
     * Space Complexity: O(n) for output (O(1) extra space)
     *
     * @param nums array of binary strings of length n
     * @return a binary string not in nums
     */
    public static String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            // Flip the diagonal bit: nums[i][i]
            sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }

        return sb.toString();
    }
}