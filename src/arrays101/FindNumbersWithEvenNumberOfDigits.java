package arrays101;

/**
 * LeetCode 1295: Find Numbers with Even Number of Digits
 * <p>
 * Given an array nums of integers, return how many of them contain an even number of digits.
 * <p>
 * Example 1:
 * Input: nums = [12,345,2,6,7896]
 * Output: 2
 * Explanation:
 * 12 contains 2 digits (even number of digits).
 * 345 contains 3 digits (odd number of digits).
 * 2 contains 1 digit (odd number of digits).
 * 6 contains 1 digit (odd number of digits).
 * 7896 contains 4 digits (even number of digits).
 * Therefore, only 12 and 7896 contain an even number of digits.
 * <p>
 * Example 2:
 * Input: nums = [555,901,482,1771]
 * Output: 1
 * Explanation:
 * Only 1771 contains an even number of digits.
 */
public class FindNumbersWithEvenNumberOfDigits {

    /**
     * Approach 1: Using Logarithm
     * Counts digits using the mathematical property: floor(log10(num)) + 1.
     * This is a concise and clever way to count digits for positive integers.
     */
    public int findNumbers_log(int[] nums) {
        int evenCount = 0;
        for (int num : nums) {
            if (num > 0) { // log10 is undefined for 0 and negative numbers
                int digits = (int) Math.log10(num) + 1;
                if (digits % 2 == 0) {
                    evenCount++;
                }
            }
        }
        return evenCount;
    }

    /**
     * Approach 2: Using String Conversion
     * Converts each number to a string and checks the length. This is often the most readable approach.
     */
    public int findNumbers_string(int[] nums) {
        int evenCount = 0;
        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount;
    }

    /**
     * Approach 3: Using Repeated Division
     * Counts digits by repeatedly dividing the number by 10 until it becomes 0.
     * This avoids floating-point math and string conversions.
     */
    public int findNumbers_division(int[] nums) {
        int evenCount = 0;
        for (int num : nums) {
            if (num == 0) continue; // Or handle as 1 digit if needed
            int count = 0;
            int currentNum = num;
            while (currentNum != 0) {
                currentNum /= 10;
                count++;
            }
            if (count % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount;
    }


    public static void main(String[] args) {
        FindNumbersWithEvenNumberOfDigits solution = new FindNumbersWithEvenNumberOfDigits();
        int[] nums1 = {12, 345, 2, 6, 7896};
        int[] nums2 = {555, 901, 482, 1771};

        System.out.println("--- Using Logarithm ---");
        System.out.println("Test Case 1: " + solution.findNumbers_log(nums1)); // Expected: 2
        System.out.println("Test Case 2: " + solution.findNumbers_log(nums2)); // Expected: 1

        System.out.println("\n--- Using String Conversion ---");
        System.out.println("Test Case 1: " + solution.findNumbers_string(nums1)); // Expected: 2
        System.out.println("Test Case 2: " + solution.findNumbers_string(nums2)); // Expected: 1

        System.out.println("\n--- Using Repeated Division ---");
        System.out.println("Test Case 1: " + solution.findNumbers_division(nums1)); // Expected: 2
        System.out.println("Test Case 2: " + solution.findNumbers_division(nums2)); // Expected: 1
    }
}
