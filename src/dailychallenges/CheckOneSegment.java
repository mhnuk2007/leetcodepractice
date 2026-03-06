package dailychallenges;

import java.util.Scanner;

public class CheckOneSegment {
    public static void main(String[] args) {
        System.out.println("Check 1: " + checkOnesSegment("1"));
        System.out.println("Check 1001: " + checkOnesSegment("1001"));
        System.out.println("Check 110: " + checkOnesSegment("110"));
            }

        public static boolean checkOnesSegment(String s) {
            int j = 0;
            for(int i = 0; i < s.length() - 1; i++){
                if(s.charAt(i) == '0' && s.charAt(i+1) == '1'){
                    return false;
                }
            }
            return true;
        }
    }

