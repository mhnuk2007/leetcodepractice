public class MajorityElement {
    public static int majorityElement(int[] nums) {
        int count = 0, candidate = 0;

        int i = 0;
        while (i < nums.length) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else {
                count += (nums[i] == candidate) ? 1 : -1;
            }
            i++;
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {6,5,5};
        System.out.println(majorityElement(nums));
    }
}
