public class FirstOccuranceInAString {
    public static int strStr(String haystack, String needle) {
        int m = needle.length(), n = haystack.length();
        for (int i = 0; i < n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
return -1;
    }

    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";
        System.out.println(strStr(haystack, needle));
    }
}
