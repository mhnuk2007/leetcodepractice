package patterns;

public class FloydTriangle {
    public static void main(String[] args) {
        int n = 4;
        int k = 1;
        for (int i = 0; i < n; i++) {
            //Floyd's Triangle of positive integers
//            int j = 0;
//            while (j <= i) {
//                System.out.print(k + "  ");
//                j++;
//                k++;
//            }
//            for(int j = 0; j <= i; j++){
//                System.out.print(k + " ");
//                k++;
//            }
            //Floyd's triangle of binary values (0 or 1)

            for(int j = 0; j <= i; j++){
                System.out.print(k%2 + " ");
                k++;
            }

            System.out.println();
        }

    }
}
