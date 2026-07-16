package arrays.prefix;

import java.util.Arrays;

/**
 * LeetCode 238 — Product of Array Except Self
 *
 * <p>Given an integer array nums, return an array answer such that answer[i] is equal to
 * the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(N) time and without using the division operator.
 *
 * <p><b>Approach 1 — Division-based (with Zero Count):</b><br>
 * We calculate the product of all non-zero elements and count the number of zeroes.
 * <ul>
 *   <li>If `zeroCount > 1`, all answers are 0.</li>
 *   <li>If `zeroCount == 1`, only the index of the zero element gets the product; others get 0.</li>
 *   <li>If `zeroCount == 0`, every index gets `product / nums[i]`.</li>
 * </ul>
 * Time Complexity  — O(N): where N is the length of nums.
 * Space Complexity — O(1): auxiliary space.
 *
 * <p><b>Approach 2 — Prefix & Suffix Products (No Division, O(1) Extra Space):</b><br>
 * We build the prefix products directly in the output array. Then, we scan backwards
 * maintaining a running suffix product and multiplying it into the output array.
 * Time Complexity  — O(N): two passes.
 * Space Complexity — O(1): auxiliary space (ignoring the output array).
 *
 * @see <a href="https://leetcode.com/problems/product-of-array-except-self/">LC 238</a>
 */
public class ProductOfArrayExceptSelf {

    // -------------------------------------------------------------------------
    // Approach 1: Division-based (with Zero Count)
    // -------------------------------------------------------------------------
    public static int[] productExceptSelfDivision(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        
        int product = 1;
        int n = nums.length;
        int zeroCount = 0;
        int[] answer = new int[n];
        
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                product *= num;
            }
        }

        for (int i = 0; i < n; i++) {
            if (zeroCount > 1) {
                answer[i] = 0;
            } else if (zeroCount == 1) {
                answer[i] = (nums[i] == 0) ? product : 0;
            } else {
                answer[i] = product / nums[i];
            }
        }

        return answer;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Prefix & Suffix Products (No Division, O(1) Extra Space)
    // -------------------------------------------------------------------------
    public static int[] productExceptSelfOptimal(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        
        int n = nums.length;
        int[] answer = new int[n];
        
        // Step 1: Compute prefix products in answer
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Step 2: Compute suffix products on the fly and multiply
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return answer;
    }

    public static void main(String[] args) {
        // Test 1: Standard case - No zeroes
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Test 1 (Division): " + Arrays.toString(productExceptSelfDivision(nums1.clone()))); // Expected: [24, 12, 8, 6]
        System.out.println("Test 1 (Optimal):  " + Arrays.toString(productExceptSelfOptimal(nums1.clone())));  // Expected: [24, 12, 8, 6]

        // Test 2: Case with exactly one zero
        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.println("Test 2 (Division): " + Arrays.toString(productExceptSelfDivision(nums2.clone()))); // Expected: [0, 0, 9, 0, 0]
        System.out.println("Test 2 (Optimal):  " + Arrays.toString(productExceptSelfOptimal(nums2.clone())));  // Expected: [0, 0, 9, 0, 0]

        // Test 3: Case with multiple zeroes
        int[] nums3 = {0, 4, 0, 5};
        System.out.println("Test 3 (Division): " + Arrays.toString(productExceptSelfDivision(nums3.clone()))); // Expected: [0, 0, 0, 0]
        System.out.println("Test 3 (Optimal):  " + Arrays.toString(productExceptSelfOptimal(nums3.clone())));  // Expected: [0, 0, 0, 0]
    }
}
