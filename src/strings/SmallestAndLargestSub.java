package strings;

public class SmallestAndLargestSub {
    public static void main(String[] args) {
        String s = "welcometojava";
        int k = 3;
        System.out.println(getSmallestAndLargest(s, k));
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);

        // Generate all possible substrings of length k
        for (int i = 0; i <= s.length()-k; i++) {
            String current = s.substring(i, i+k);
            if (current.compareTo(smallest) < 0) {
                smallest = current;
            }
            if (current.compareTo(largest) > 0) {
                largest = current;
            }

        }
        return smallest + "\n" + largest;

    }

}
