package recursion;

import java.util.Arrays;
import java.util.Random;

/**
 * Quick Sort (Randomized)
 *
 * Problem:
 *   Sort an array of integers in ascending order using the Quick Sort algorithm.
 *
 * Approach: Partition-based recursion (Lomuto partition scheme)
 *   Choose a random element as pivot by swapping it with the last element.
 *   Partition: move all elements less than pivot to the left, greater to the right.
 *   Pivot lands at its correct sorted position.
 *   Recursively sort left and right subarrays.
 *
 * Why randomized pivot?
 *   Fixed pivot (always last) degrades to O(n²) on sorted/reverse-sorted arrays.
 *   Random pivot reduces the chance of worst case — expected O(n log n).
 *
 * Trace: arr = [7, 2, 1, 6, 3, 0, 8, 4], pivot = 4, i = -1
 *   j=0  7 > 4  no action              [7, 2, 1, 6, 3, 0, 8, 4]
 *   j=1  2 < 4  i=0,  swap(0,1)       [2, 7, 1, 6, 3, 0, 8, 4]
 *   j=2  1 < 4  i=1,  swap(1,2)       [2, 1, 7, 6, 3, 0, 8, 4]
 *   j=3  6 > 4  no action              [2, 1, 7, 6, 3, 0, 8, 4]
 *   j=4  3 < 4  i=2,  swap(2,4)       [2, 1, 3, 6, 7, 0, 8, 4]
 *   j=5  0 < 4  i=3,  swap(3,5)       [2, 1, 3, 0, 7, 6, 8, 4]
 *   j=6  8 > 4  no action              [2, 1, 3, 0, 7, 6, 8, 4]
 *   exit → swap(i+1, high)=swap(4,7)  [2, 1, 3, 0, 4, 6, 8, 7]
 *                                                  ↑ pivot placed
 *
 *   LEFT sort(2,1,3,0) pivot=0, i=-1
 *     j=0  2 > 0  no action            [2, 1, 3, 0]
 *     j=1  1 > 0  no action            [2, 1, 3, 0]
 *     j=2  3 > 0  no action            [2, 1, 3, 0]
 *     exit → swap(i+1,high)=swap(0,3) [0, 1, 3, 2]
 *                                       ↑ pivot placed
 *
 *     LEFT of 0: empty → done
 *     RIGHT sort(1,3,2) pivot=2, i=-1
 *       j=0  1 < 2  i=0, swap(0,0)    [1, 3, 2]
 *       j=1  3 > 2  no action          [1, 3, 2]
 *       exit → swap(i+1,high)=swap(1,2)[1, 2, 3]
 *                                           ↑ pivot placed
 *       LEFT  [1] → single element → done
 *       RIGHT [3] → single element → done
 *
 *   LEFT fully sorted: [0, 1, 2, 3]
 *
 *   RIGHT sort(6,8,7) pivot=7, i=-1
 *     j=0  6 < 7  i=0, swap(0,0)      [6, 8, 7]
 *     j=1  8 > 7  no action            [6, 8, 7]
 *     exit → swap(i+1,high)=swap(1,2) [6, 7, 8]
 *                                         ↑ pivot placed
 *     LEFT  [6] → single element → done
 *     RIGHT [8] → single element → done
 *
 *   RIGHT fully sorted: [6, 7, 8]
 *
 *   FINAL: [0,1,2,3] + [4] + [6,7,8] = [0,1,2,3,4,6,7,8] ✓
 *
 * Time  : O(n log n) average — O(n²) worst case (unlucky random pivots)
 * Space : O(log n)   average — O(n) worst case (recursion depth)
 */
public class QuickSort {

    public static void main(String[] args) {
        // Test 1: standard case
        int[] arr1 = {7, 2, 1, 6, 3, 0, 8, 4};
        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr1));
        // Expected: [0, 1, 2, 3, 4, 6, 7, 8]

        // Test 2: already sorted
        int[] arr2 = {1, 2, 3, 4, 5};
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr2));
        // Expected: [1, 2, 3, 4, 5]

        // Test 3: reverse sorted
        int[] arr3 = {5, 4, 3, 2, 1};
        quickSort(arr3, 0, arr3.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr3));
        // Expected: [1, 2, 3, 4, 5]

        // Test 4: single element
        int[] arr4 = {1};
        quickSort(arr4, 0, arr4.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr4));
        // Expected: [1]

        // Test 5: duplicates
        int[] arr5 = {3, 1, 2, 1, 3};
        quickSort(arr5, 0, arr5.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr5));
        // Expected: [1, 1, 2, 3, 3]
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);                           // sort left of pivot
            quickSort(arr, pivotIndex + 1, high);                          // sort right of pivot
        }
    }

    private static final Random rand = new Random();

    public static int partition(int[] arr, int low, int high) {
        int randomIndex = low + rand.nextInt(high - low + 1);      // random index in [low, high]
        swap(arr, randomIndex, high);                                      // move random pivot to end
        int pivot = arr[high];                                             // treat last as pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);                                           // move smaller element left
            }
        }
        swap(arr, i + 1, high);                                            // place pivot in correct position
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i]   = arr[j];
        arr[j]   = temp;
    }
}