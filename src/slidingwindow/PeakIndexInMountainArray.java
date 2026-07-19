package slidingwindow;

public class PeakIndexInMountainArray {
    private static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    public static void main(String[] args) {


        int[] arr1 = {0, 1, 0};
        System.out.println(peakIndexInMountainArray(arr1)); // 1

        int[] arr2 = {0, 2, 5, 3, 1};
        System.out.println(peakIndexInMountainArray(arr2)); // 2

        int[] arr3 = {3, 5, 3, 2, 0};
        System.out.println(peakIndexInMountainArray(arr3)); // 1

        int[] arr4 = {1, 3, 5, 7, 6, 4, 2};
        System.out.println(peakIndexInMountainArray(arr4)); // 3
    }
}
