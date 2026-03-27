package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion fundamentals: paired iterative and recursive implementations
 * for common number and digit problems.
 */
public class RecursionSimulation {

    public static void main(String[] args) {
        // Print ascending / descending
        printAsc(5);
        System.out.println();
        printDesc(5);
        System.out.println();

        // Factorial
        System.out.println("Factorial 6: " + fact(6));

        // Sum 1..N
        System.out.println("Sum 1..10: " + sumToN(10));

        // Digit operations
        System.out.println("Sum of digits 123: " + sumOfDigits(123));
        System.out.println("Product of digits 2234: " + prodOfDigits(2234));

        // Reverse number
        System.out.println("Reverse recursive  12345: " + reverseNum(12345));
        System.out.println("Reverse iterative  12345: " + reverseNumIter(12345));

        // Palindrome check
        System.out.println("Is palindrome 1234321: " + isPalindrome(1234321));
        System.out.println("Is palindrome 12343:   " + isPalindrome(12343));

        // Count zeros
        System.out.println("Count zeros iterative  1002304050: " + countZeros(1002304050));
        System.out.println("Count zeros recursive  1002304050: " + countZeroRecursion(1002304050));

        // Number of steps to reduce to zero
        System.out.println("Number of steps iterative  14: " + numberOfSteps(14));
        System.out.println("Number of steps recursive  14: " + numberOfStepsRec(14));

        // Sorted check
        System.out.println("Is {1,2,3,4,5} sorted? " + isSorted(new int[]{1, 2, 3, 4, 5}, 0));
        System.out.println("Is {1,2,6,4,3} sorted? " + isSorted(new int[]{1, 2, 6, 4, 3}, 0));

        // Linear search
        System.out.println("Index of 4 in {1,2,3,4,5} is: " + linearSearch(new int[]{1, 2, 3, 4, 5}, 4, 0));
        System.out.println("Index of 5 in {1,2,3,4,5} is: " + linearSearch(new int[]{1, 2, 3, 4, 5}, 5, 0));
        System.out.println("Index of 6 in {1,2,3,4,5} is: " + linearSearch(new int[]{1, 2, 3, 4, 5}, 6, 0));

        // Binary search
        System.out.println("Index of 4 in {1,2,3,4,5} is: " + binarySearch(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println("Index of 5 in {1,2,3,4,5} is: " + binarySearch(new int[]{1, 2, 3, 4, 5}, 5));
        System.out.println("Index of 6 in {1,2,3,4,5} is: " + binarySearch(new int[]{1, 2, 3, 4, 5}, 6));

        // Search all instances
        List<Integer> result = new ArrayList<>();
        searchAllInstances(new int[]{4, 4, 3, 4, 5}, 4, 0, result);
        System.out.println("Indices of 4 in {4,4,3,4,5} are: " + result);
        System.out.println("Indices of 4 in {4,4,3,4,5} are: " + searchAllInstances2(new int[]{4, 4, 3, 4, 5}, 4, 0));

        // Search in rotated sorted array (LC 33)
        System.out.println("Search 0 in {4,5,6,7,0,1,2}: " + search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println("Search 4 in {4,5,6,7,0,1,2}: " + search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
        System.out.println("Search 3 in {4,5,6,7,0,1,2}: " + search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    // -------------------------------------------------------------------------
    // Print
    // -------------------------------------------------------------------------

    /** Prints 1 2 3 ... n (ascending). */
    private static void printAsc(int n) {
        if (n <= 0) return;
        printAsc(n - 1);
        System.out.print(n + " ");
    }

    /** Prints n ... 2 1 (descending). */
    private static void printDesc(int n) {
        if (n <= 0) return;
        System.out.print(n + " ");
        printDesc(n - 1);
    }

    // -------------------------------------------------------------------------
    // Factorial
    // -------------------------------------------------------------------------

    /** Returns n! recursively. */
    private static int fact(int n) {
        if (n <= 1) return 1;
        return n * fact(n - 1);
    }

    // -------------------------------------------------------------------------
    // Sum 1..N
    // -------------------------------------------------------------------------

    /** Returns 1 + 2 + ... + n recursively. Assumes n >= 1. */
    private static int sumToN(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return n + sumToN(n - 1);
    }

    // -------------------------------------------------------------------------
    // Digit operations
    // -------------------------------------------------------------------------

    /** Returns the sum of digits of n. */
    private static int sumOfDigits(int n) {
        if (n < 10) return n;
        return (n % 10) + sumOfDigits(n / 10);
    }

    /** Returns the product of digits of n. */
    private static int prodOfDigits(int n) {
        if (n < 10) return n;
        return (n % 10) * prodOfDigits(n / 10);
    }

    // -------------------------------------------------------------------------
    // Reverse number
    // -------------------------------------------------------------------------

    /** Returns the digit-reversal of n, iteratively. */
    private static int reverseNumIter(int n) {
        int res = 0;
        while (n > 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }

    /** Returns the digit-reversal of n, recursively. */
    private static int reverseNum(int n) {
        return reverseHelper(n, 0);
    }

    /** Accumulator helper: acc holds the reversed number built so far. */
    private static int reverseHelper(int n, int acc) {
        if (n == 0) return acc;
        return reverseHelper(n / 10, acc * 10 + n % 10);
    }

    // -------------------------------------------------------------------------
    // Palindrome
    // -------------------------------------------------------------------------

    /** Returns true if n reads the same forwards and backwards. */
    private static boolean isPalindrome(int n) {
        return reverseNum(n) == n;
    }

    // -------------------------------------------------------------------------
    // Count zeros
    // -------------------------------------------------------------------------

    /** Returns the count of zero digits in n, iteratively. */
    private static int countZeros(int n) {
        int count = 0;
        do {
            if (n % 10 == 0) count++;
            n /= 10;
        } while (n > 0);
        return count;
    }

    /** Returns the count of zero digits in n, recursively. */
    private static int countZeroRecursion(int n) {
        return countHelper(n, 0);
    }

    /** Accumulator helper: checks current digit, recurses on remaining digits. */
    private static int countHelper(int n, int count) {
        if (n % 10 == 0) count++;
        if (n < 10) return count;
        return countHelper(n / 10, count);
    }

    // -------------------------------------------------------------------------
    // Number of steps to reach zero
    // (even -> divide by 2, odd -> subtract 1)
    // -------------------------------------------------------------------------

    /** Returns the number of steps to reduce num to zero, iteratively. */
    public static int numberOfSteps(int num) {
        int steps = 0;
        while (num > 0) {
            num = (num % 2 == 0) ? num / 2 : num - 1;
            steps++;
        }
        return steps;
    }

    /** Returns the number of steps to reduce num to zero, recursively. */
    public static int numberOfStepsRec(int num) {
        return numberOfStepsHelper(num, 0);
    }

    /** Accumulator helper: steps holds the count built so far. */
    private static int numberOfStepsHelper(int num, int steps) {
        if (num == 0) return steps;
        if (num % 2 == 0) return numberOfStepsHelper(num / 2, steps + 1);
        return numberOfStepsHelper(num - 1, steps + 1);
    }

    // -------------------------------------------------------------------------
    // Array: sorted check, linear search, binary search
    // -------------------------------------------------------------------------

    /**
     * Returns true if nums is sorted in non-decreasing order.
     * Uses <= to allow equal adjacent elements.
     */
    private static boolean isSorted(int[] nums, int index) {
        if (index == nums.length - 1) return true;
        return nums[index] <= nums[index + 1] && isSorted(nums, index + 1);
    }

    /** Returns the index of target in nums, or -1 if not found. Linear search. */
    private static int linearSearch(int[] nums, int target, int index) {
        if (index == nums.length) return -1;
        if (nums[index] == target) return index;
        return linearSearch(nums, target, index + 1);
    }

    /** Returns the index of target in a sorted nums, or -1 if not found. Binary search. */
    private static int binarySearch(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    /** Recursive binary search helper. */
    private static int binarySearchHelper(int[] nums, int target, int start, int end) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) return mid;
        if (nums[mid] > target) return binarySearchHelper(nums, target, start, mid - 1);
        return binarySearchHelper(nums, target, mid + 1, end);
    }

    // -------------------------------------------------------------------------
    // Search all instances
    // -------------------------------------------------------------------------

    /**
     * Side-effect style: appends all indices where target appears in nums
     * into the provided result list.
     */
    private static void searchAllInstances(int[] nums, int target, int index, List<Integer> result) {
        if (index == nums.length) return;
        if (nums[index] == target) result.add(index);
        searchAllInstances(nums, target, index + 1, result);
    }

    /**
     * Return style: returns a new list of all indices where target appears in nums.
     * Note: allocates a new List at each frame — O(n) extra lists.
     */
    private static List<Integer> searchAllInstances2(int[] nums, int target, int index) {
        List<Integer> result = new ArrayList<>();
        if (index == nums.length) return result;
        if (nums[index] == target) result.add(index);
        result.addAll(searchAllInstances2(nums, target, index + 1));
        return result;
    }

    // -------------------------------------------------------------------------
    // Search in rotated sorted array (LC 33)
    // -------------------------------------------------------------------------

    /**
     * Returns the index of target in a rotated sorted array, or -1 if not found.
     * Time: O(log n)
     */
    public static int search(int[] nums, int target) {
        return searchHelper(nums, target, 0, nums.length - 1);
    }

    /**
     * Recursive binary search on a rotated sorted array.
     * At each step, one half is guaranteed to be sorted — check target
     * membership in that half to decide which side to recurse into.
     */
    private static int searchHelper(int[] nums, int target, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;

        // Left half is sorted
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid])
                return searchHelper(nums, target, left, mid - 1);
            else
                return searchHelper(nums, target, mid + 1, right);
        }
        // Right half is sorted
        else {
            if (nums[mid] < target && target <= nums[right])
                return searchHelper(nums, target, mid + 1, right);
            else
                return searchHelper(nums, target, left, mid - 1);
        }
    }
}