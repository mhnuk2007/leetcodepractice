package bitmanipulation;

import java.util.HashSet;
import java.util.Set;

public class NumberOfUniqueXorTripletsII {
    public static int uniqueXorTriplets(int[] nums) {
        int[] seen1 = new int[2048];
        for (int a : nums) {
            for (int b : nums) {
                seen1[a^b] = 1;
            }
        }
        int[] seen2 = new int[2048];
        for (int xor = 0; xor < seen1.length; xor++) {
            if (seen1[xor] == 1)
                for (int c : nums) {
                seen2[c^xor] = 1;
            }
        }
        int ans = 0;
        for (int a : seen2) {
            ans += a;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        System.out.println(uniqueXorTriplets(nums1));
        int[] nums2 = {6, 7, 8, 9};
        System.out.println(uniqueXorTriplets(nums2));
        int[] nums3 = new int[1500];
        for (int i = 0; i < 1500; i++) {
            nums3[i] = i + 1;
        }
        System.out.println(uniqueXorTriplets(nums3));
    }
}
