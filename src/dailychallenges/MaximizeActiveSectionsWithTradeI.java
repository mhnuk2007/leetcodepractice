package dailychallenges;

public class MaximizeActiveSectionsWithTradeI {
    public static int maxActiveSectionsAfterTrade(String s) {
        int active = 0;
        int maxGain = 0;
        int prevZero = -1;

        int n = s.length();
        int i = 0;
        while (i < n) {
            if(i < n && s.charAt(i) == '0') {
                int start = i;
                while(i < n && s.charAt(i) == '0') i++;
                int currZero  = i - start;
                if(prevZero != -1) maxGain = Math.max(maxGain, prevZero + currZero);
                prevZero = currZero;
            }
            else {
                int start = i;
                while(i < n && s.charAt(i) == '1') i++;
                active += i - start;
            }
        }

        return active + maxGain;
    }

    public static void main(String[] args) {
        String[] testCases = {
                "01",
                "0100",
                "1000100",
                "01010",
                "00010",
                "0010001010000"
        };

        for (String s : testCases) {
            int result = maxActiveSectionsAfterTrade(s);
            System.out.println("Input: " + s);
            System.out.println("Output: " + result);
            System.out.println();
        }
    }
}
