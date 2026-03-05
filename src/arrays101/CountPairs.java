// 2824: Count pairs whose sum is less than target
// nums = [-1,1,2,3,1], target = 2
package arrays101;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CountPairs {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(-1);
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        int target = 2;
//        System.out.println("List of integers: " + nums);
//        System.out.println("Number of pairs whose sum is less than " + target +" is: " + countPairs(nums, target));

        List<Integer> nums1 = List.of(3,1,0,4,2); // 0,1,2,3,4
        int target1 = 5;

        System.out.println("List of integers: " + nums1);
        System.out.println("Number of pairs whose sum is less than " + target1 +" is: " + countPairs(new ArrayList<>(nums1), target1));


    }

    private static int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int left = 0;
        int right = nums.size() - 1;
        int count = 0;

        while(left<right){
            int sum = nums.get(left) + nums.get(right);
            if(sum < target){
                count += right - left;
                left++;
            } else {
                right--;
            }

        }
        return count;
    }
}
