package arrays101;

import java.util.*;

public class FindDisappearedNumber {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        FindDisappearedNumber obj = new FindDisappearedNumber();
        List<Integer> result = obj.findDisappearedNumbers(nums);
        System.out.println(result);

    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);

        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)){
                result.add(i);
            }
        }

        return result;

    }
}
