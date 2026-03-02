package arrays101;

public class EvenNoOfDigits {
    public int findNumbers(int[] nums) {
        int evenCount = 0;
        for (int num : nums) {
            int digits = (int) Math.log10(num) + 1;
            if (digits % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount;
    }

    public static void main(String[] args) {
        EvenNoOfDigits obj = new EvenNoOfDigits();
        int[] arr = {12,345,2,6,7896};
        System.out.println(obj.findNumbers(arr));
    }

}
