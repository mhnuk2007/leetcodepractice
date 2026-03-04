package arrays101;

public class ThirdMaximumNumber {
    public static void main(String[] args) {
        int[] nums1 = {3,2,1}; // expected output: 1
        int[] nums2 = {1,2}; // expected output: 2
        int[] nums3 = {2,2,3,1}; // expected output: 1

        ThirdMaximumNumber obj = new ThirdMaximumNumber();
        System.out.println(obj.thirdMax(nums1));
        System.out.println(obj.thirdMax(nums2));
        System.out.println(obj.thirdMax(nums3));



    }

    public int thirdMax(int[] nums) {
        Integer max1 = null, max2 = null, max3 = null;
        for (int num : nums) {
            if (
                    (max1 != null && num == max1) ||
                            (max2 != null && num == max2) ||
                            (max3 != null && num == max3)
            )
                continue;
            if (max1 == null || num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (max2 == null || num > max2) {
                max3 = max2;
                max2 = num;
            } else if (max3 == null || num > max3) {
                max3 = num;
            }
        }

        return (max3 != null) ? max3.intValue() : max1.intValue();


    }
}

