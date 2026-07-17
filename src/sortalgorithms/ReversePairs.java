package sortalgorithms;

public class ReversePairs {
    public static int reversePairs(int[] nums) {

        int start = 0;
        int end = nums.length - 1;
        return mergeSort(nums, start, end);
    }

    private static int mergeSort(int[] nums, int start, int end) {
        if (start >= end) return 0;
        int mid = start + (end - start) / 2;
        int count = 0;
        count += mergeSort(nums, start, mid);
        count += mergeSort(nums, mid + 1, end);
        count += countPairs(nums, start, mid, end);
        merge(nums, start, mid, end);
        return count;
    }

    private static int countPairs(int[] nums, int start, int mid, int end) {
        int count = 0;
        int j = mid + 1;
        for (int i = start; i <= mid; i++) {
            while (j <= end && nums[i] > 2L * nums[j]) j++;
            count += j - (mid + 1);
        }
        return count;
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];
        }
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= end) temp[k++] = nums[j++];

        int idx = 0;
        while (idx < temp.length) nums[start+idx] = temp[idx++];
        }


    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{1, 3, 2, 3, 1}) + " (expected 2)");

        System.out.println(reversePairs(new int[]{2, 4, 3, 5, 1}) + " (expected 3)");

        System.out.println(reversePairs(new int[]{1, 2, 3, 4, 5}) + " (expected 0)");

        System.out.println(reversePairs(new int[]{5, 4, 3, 2, 1}) + " (expected 4)");

        System.out.println(reversePairs(new int[]{-5, -5}) + " (expected 1)");

        System.out.println(reversePairs(new int[]{2147483647, 2147483647}) + " (expected 0)");

        System.out.println(reversePairs(new int[]{2147483647, 1073741824}) + " (expected 0)");

        System.out.println(reversePairs(new int[]{2147483647, 1}) + " (expected 1)");
    }
}
