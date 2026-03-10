package arrays;

public class SearchInRotatedSortedArray {
    /**
     * LeetCode 33: Search in Rotated Sorted Array
     * <p>
     * There is an integer array nums sorted in ascending order (with distinct values).
     * Prior to being passed to your function, nums is possibly rotated at an unknown
     * pivot index k (1 <= k < nums.length).
     * <p>
     * Given the array nums after the rotation and an integer target, return the
     * index of target if it is in nums, or -1 if it is not in nums.
     * <p>
     * You must write an algorithm with O(log n) runtime complexity.
     * <p>
     * Example 1:
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     */
    public int search(int[] nums, int target) {

        int n = nums.length;

        int pivot = findPivot(nums, n);

        if (nums[pivot] <= target && target <= nums[n - 1]) {
            return binarySearch(pivot, n - 1, nums, target);
        }

        return binarySearch(0, pivot - 1, nums, target);
    }

    public int findPivot(int[] nums, int n) {

        int left = 0;
        int right = n - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
    public int binarySearch(int left, int right, int[] nums, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

        // Test Case 1: Rotated array, target exists
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(solution.search(nums1, target));
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums2, 5));  // Expected: 1
        System.out.println(solution.search(nums2, 3));  // Expected: -1

        int[] nums3 = {1};
        System.out.println(solution.search(nums3, 1));  // Expected: 0
        System.out.println(solution.search(nums3, 2));  // Expected: -1

        int[] nums4 = {1, 2, 3, 4, 5};                 // No rotation
        System.out.println(solution.search(nums4, 3));  // Expected: 2
    }
}
