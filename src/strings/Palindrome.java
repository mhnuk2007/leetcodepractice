package strings;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String A = sc.next();

        System.out.println(isPalindrome(A)?"YES":"NO");
    }
    static boolean isPalindrome(String A) {
        int n = A.length();
        for (int i = 0; i < n / 2; i++) {
            if (A.charAt(i) != A.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
