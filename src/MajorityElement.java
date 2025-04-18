public class MajorityElement {
    public static int majorityElement(int[] nums){
        int count = 0, candidate =0;

        int i=0;
        while (i<nums.length){
            if (count == 0){
                candidate = nums[i];
                count =1;
        }
            count+= (candidate==nums[i]) ? 1:-1;
            i++;
        }
        return candidate;
    }
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums));  // Output: 2
    }
}
