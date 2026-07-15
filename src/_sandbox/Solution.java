package _sandbox;

public class Solution {
    public static void main(String[] args) {
        int hash = 0;
        for (int i = 0; i < 10; i++){
            hash = ((hash << 2) | i) & 1111111111 ;
            System.out.println(hash);
        }
    }
}