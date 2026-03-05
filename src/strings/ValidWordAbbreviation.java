package strings;

public class ValidWordAbbreviation {
    public static void main(String[] args) {
        String word = "substitution";
        String abbr = "su3i1u2on";
        System.out.println("Is abbreviation: " + abbr + " valid for word: " + word + ": " + (validWordAbbreviation(word, abbr) ? "YES" : "NO"));

    }

    private static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                if (abbr.charAt(j) == '0') {
                    return false;
                }

                int num = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j++) - '0');
                }
                i += num;
            } else {
                if (word.charAt(i) != abbr.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return i == word.length() && j == abbr.length();
    }
}
