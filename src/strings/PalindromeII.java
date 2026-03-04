package strings;

public class PalindromeII {
    public static void main(String[] args) {
        String s = "abcxxba";
        System.out.println("Is 's' a palindrome:==> " + (isPalindrome(s) ? "YES" : "NO"));

    }

    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i), right = s.charAt(j);
            if (left != right) {
                return (isValidPalindrome(s, i+1, j) || isValidPalindrome(s, i, j-1));
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private static boolean isValidPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
