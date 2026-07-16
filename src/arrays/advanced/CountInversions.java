package arrays.advanced;

public class CountInversions {
    private static int count;

    public static int countInversions(int[] nums) {
        count = 0;
        int start = 0;
        int end = nums.length - 1;

        mergeSort(nums, start, end);

        return count;
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) temp[k++] = nums[i++];
            else {
                count += mid - i + 1;
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= end) temp[k++] = nums[j++];

        int idx = 0;
        while (idx < temp.length) nums[start + idx] = temp[idx++];
    }

    public static void main(String[] args) {
        int[] nums = {5,2,8,6,1,3};
        int inversions = countInversions(nums);
        System.out.println("Number of inversions: " + inversions);

    }
}
