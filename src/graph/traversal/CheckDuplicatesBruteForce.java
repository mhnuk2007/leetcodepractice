import java.util.Arrays;

public class CheckDuplicatesBruteForce {
    public static void main(String[] args) {
        int[] myArray = {2, 1, 8, 9, 5, 6, 7, 2};
        for (int i = 0; i < myArray.length; i++) {
            for (int j = i + 1; j < myArray.length; j++) { // Corrected loop condition
                if (myArray[j] == myArray[i]) {
                    System.out.println("Duplicate found: " + myArray[j]);
                    return;
                }
            }
        }
        System.out.println("Duplicate not found");

        checkDuplicateBySorting(myArray);
    }

    public static void checkDuplicateBySorting(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] == arr[i]) {
                System.out.println("Duplicate found: " + arr[i]);
                return;
            }
        }
        System.out.println("Duplicate not found");
    }
}