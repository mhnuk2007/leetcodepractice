package math;

// Leetcode: 3345 - Smallest Divisible Digit Product I
public class SmallestDivisibleDigitProductI {
    public static int smallestNumber(int n, int t) {
        while (true) {
            if (isDigitProductDivisible(n, t))
                return n;
            n++;
        }

    }

    private static boolean isDigitProductDivisible(int n, int t) {
        int product = 1;
        while (n > 0) {
            int digit = n % 10;
            if (digit==0) return true;
            n /= 10;
            product *= digit;
        }
        return product%t == 0;
    }

    public static void main(String[] args) {
        System.out.println(smallestNumber(10, 2)); // 10
        System.out.println(smallestNumber(15, 3)); // 16
    }
}
