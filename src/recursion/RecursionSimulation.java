package recursion;

public class RecursionSimulation {
    public static void main(String[] args) {
        printAsc(5);
        System.out.println();
        printDesc(5);
        System.out.println();
        System.out.println(fact(6));

        System.out.println(sumToN(10));

        System.out.println(sumOfDigits(123));

        System.out.println(prodOfDigits(2234));
        int n = 12345;

        System.out.println("Reverse recursive: 12345 is " + reverseNum(n));
        System.out.println("Reverse Iterative: 12345 is " + reverseNumIter(12345));
        System.out.println("Is palindrome: " + isPalindrome(1234321));
        System.out.println("Is palindrome: " + isPalindrome(12343));
    }

    private static void printAsc(int n) {
        if (n <= 0) return;
        printAsc(n - 1);
        System.out.print(n + " ");
    }


    private static void printDesc(int n) {
        if (n <= 0) return;
        System.out.print(n + " ");
        printDesc(n - 1);
    }

    private static int fact(int n) {
        if (n <= 1) return 1;
        return n * fact(n - 1);
    }

    private static int sumToN(int n) {
        if (n <= 1) return 1;
        return n + sumToN(n - 1);
    }

    private static int sumOfDigits(int n) {
        if (n < 10) return n;
        return (n % 10) + sumOfDigits(n / 10);
    }

    private static int prodOfDigits(int n) {
        if (n < 10) return n;
        return (n % 10) * prodOfDigits(n / 10);
    }

    private static int reverseNumIter(int n) {
        int res = 0;
        while (n > 0) {
            res = res * 10 + n % 10;
            n = n / 10;
        }
        return res;
    }

    private static int reverseNum(int n) {
        return reverseHelper(n, 0);
    }

    private static int reverseHelper(int n, int i) {
        if (n == 0) return i;
        return reverseHelper(n / 10, i * 10 + n % 10);
    }

    private static boolean isPalindrome(int n) {
        int sum = 0, digit = 0, original = n;
        while (n > 0) {

            digit = n % 10;
            sum = 10 * sum + digit;
            n = n / 10;

        }
        System.out.println(sum);
        if (sum == original) return true;
        return false;

    }
}