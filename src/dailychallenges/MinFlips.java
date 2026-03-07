package dailychallenges;

public class MinFlips {
    public static void main(String[] args) {
        String s = "100011";
        System.out.println(minFlips(s));
    }
    public static int minFlips(String s) {
        int n = s.length();

        int res = Integer.MAX_VALUE;

        int flips1 = 0;
        int flips2 = 0;

        int i = 0;
        int j = 0;

        while (j < 2*n) {
            char expectedChar1 = j%2==0 ? '1' : '0';
            char expectedChar2 = j%2==0 ? '0' : '1';

            if (s.charAt(j%n) != expectedChar1) {
                flips1++;
            }
            if (s.charAt(j%n) != expectedChar2) {
                flips2++;
            }
            if(j-i+1>n){
                expectedChar1 = i%2==0 ? '1' : '0';
                expectedChar2 = i%2==0 ? '0' : '1';

                if(s.charAt(i%n) != expectedChar1){
                    flips1--;
                }
                if(s.charAt(i%n) != expectedChar2){
                    flips2--;
                }
                i++;
            }

            if(j-i+1==n){
                res = Math.min(res, (Math.min(flips1, flips2)));
            }
            j++;
        }
        return res;
    }
}
