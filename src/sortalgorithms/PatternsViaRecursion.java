package sortalgorithms;

public class PatternsViaRecursion {
    public static void main(String[] args) {

        printPatternIter(5);
        printPatternRec(5 , 0);
    }

    private static void printPatternIter(int n) {
        for (int row = n; row > 0; row--) {
            for (int col = 0; col < row; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void printPatternRec(int row, int col) {
        if(row == 0) return;
        if(col < row){
            System.out.print("*");
            printPatternRec(row, col + 1);
        } else{
            System.out.println();
            printPatternRec(row - 1, 0);
        }


    }





}
