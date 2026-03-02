package arrays101;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        for(int num : nums){
            if (num == 1){
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0;
            }

        }
        return maxCount;

    }
    public static void main(String[] args) {
        MaxConsecutiveOnes obj = new MaxConsecutiveOnes();
        int[] arr = {1,1,0,1,1,1};
        System.out.println(obj.findMaxConsecutiveOnes(arr));
    }
}
