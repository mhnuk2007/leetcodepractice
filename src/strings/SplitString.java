package strings;

public class SplitString {
    public static void main(String[] args) {
        String s = "He is a very very good boy, isn't he?";
        String[] tokens = s.split("[^A-Za-z]+\s+");

        System.out.println(tokens.length);
        for (String word : tokens) {
            System.out.println(word);
        }
    }
}
