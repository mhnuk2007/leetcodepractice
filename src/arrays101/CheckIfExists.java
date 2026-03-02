package arrays101;

import java.util.HashSet;
import java.util.Set;

public class CheckIfExists {
    public static void main(String[] args) {
        CheckIfExists obj = new CheckIfExists();
        int[] arr = {10,2,5,3};
        System.out.println(obj.checkIfExist(arr));
    }
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int num : arr){
            if (set.contains(num*2) || (set.contains(num/2)&&(num%2==0))){
                return true;
            }

            set.add(num);

        }
        return false;

    }
}