package strings;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    static boolean isAnagram(String a, String b) {
        // Complete the function
        if (a.length() != b.length()) {
            return false;
        }

        int[] freq = new int[26];
        for(int i = 0; i < a.length(); i++){
            freq[a.charAt(i) - 'a']++;
            freq[b.charAt(i) - 'a']--;
        }
        for(int count : freq){
            if(count != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String a = scan.next();
        System.out.print("Enter another string: ");
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println((ret) ? "Anagrams" : "Not Anagrams");
    }
}
