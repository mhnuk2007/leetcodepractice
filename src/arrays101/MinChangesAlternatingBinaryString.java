package arrays101;

public class MinChangesAlternatingBinaryString {
    public static void main(String[] args) {
        MinChangesAlternatingBinaryString obj = new MinChangesAlternatingBinaryString();
        // Test cases
        System.out.println(obj.minOperations("0100"));  // 1
        System.out.println(obj.minOperations("10"));    // 0
        System.out.println(obj.minOperations("1111"));  // 2
        System.out.println(obj.minOperations("0"));     // 0
        System.out.println(obj.minOperations("00110"));  // 2
    }

    private int minOperations(String number) {
        int count = 0;
        for (int i = 0; i < number.length(); i++) {
            char expected = (i % 2 == 0) ? '1' : '0';
            if (number.charAt(i) != expected) {
                count++;
            }
        }
        return Math.min(count, number.length() - count);

    }
}
