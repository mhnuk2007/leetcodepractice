package arrays101;

import java.util.Arrays;

public class HeightChecker {
    public static void main(String[] args) {
        HeightChecker hc = new HeightChecker();
        int[] heights = {1, 1, 4, 2, 1, 3};
        System.out.println(hc.heightChecker(heights));
        System.out.println("Heights array: " + Arrays.toString(heights));


    }

    public int heightChecker(int[] heights) {
        int n = heights.length;
        int[] counts = new int[101];
        int currentHeight = 1;
        int result = 0;
        for(int h : heights){
            counts[h]++;
        }
        for (int i = 0; i < n; i++) {
            while (counts[currentHeight] == 0){
                currentHeight++;
            }
            if(heights[i] != currentHeight){
                result ++;
            }
            counts[currentHeight]--;
        }


        return result;
    }
}
