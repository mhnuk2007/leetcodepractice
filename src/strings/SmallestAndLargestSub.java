package strings;

/**
 * HackerRank Problem: Java Substring Comparisons
 * <p>
 * Given a string, s, and an integer, k, complete the function so that it finds the
 * lexicographically smallest and largest substrings of length k.
 * <p>
 * Function Description:
 * Complete the getSmallestAndLargest function in the editor below.
 * getSmallestAndLargest has the following parameters:
 * - string s: a string
 * - int k: an integer
 * <p>
 * Returns:
 * string: a string consisting of the lexicographically smallest and largest substrings, separated by a newline
 * <p>
 * Example:
 * s = "welcometojava"
 * k = 3
 * Substrings of length 3 are: ["wel", "elc", "lco", "com", "ome", "met", "eto", "toj", "oja", "jav", "ava"]
 * The lexicographically smallest is "ava" and the largest is "wel".
 * The function should return "ava\nwel".
 */
public class SmallestAndLargestSub {

    /**
     * Finds the lexicographically smallest and largest substrings of a given length.
     *
     * @param s The input string.
     * @param k The length of the substrings to compare.
     * @return A string containing the smallest substring, a newline, and the largest substring.
     */
    public String getSmallestAndLargest(String s, int k) {
        // Initialize smallest and largest with the first substring
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);

        // Iterate through all possible substrings of length k
        for (int i = 1; i <= s.length() - k; i++) {
            String current = s.substring(i, i + k);
            if (current.compareTo(smallest) < 0) {
                smallest = current;
            }
            if (current.compareTo(largest) > 0) {
                largest = current;
            }
        }
        return smallest + "\n" + largest;
    }

    public static void main(String[] args) {
        SmallestAndLargestSub solution = new SmallestAndLargestSub();

        // Test Case 1
        String s1 = "welcometojava";
        int k1 = 3;
        System.out.println("Test Case 1 (s=\"welcometojava\", k=3):");
        System.out.println(solution.getSmallestAndLargest(s1, k1)); // Expected: "ava\nwel"
        System.out.println("--------------------");

        // Test Case 2
        String s2 = "ASDFHDSFHsdlfhsdlfLDFHSDLFH";
        int k2 = 8;
        System.out.println("Test Case 2 (s=\"ASDFHDSFHsdlfhsdlfLDFHSDLFH\", k=8):");
        System.out.println(solution.getSmallestAndLargest(s2, k2)); // Expected: "ASDFHDSD\n" + "sdlfhsdl"
    }
}
