package arrays;

/**
 * LeetCode 1672: Richest Customer Wealth
 *
 * You are given an m x n integer grid accounts where accounts[i][j] is the amount of money
 * the i-th customer has in the j-th bank. Return the wealth that the richest customer has.
 */
public class RichestCustomerWealth {

    /**
     * Finds the maximum row sum in the accounts matrix.
     *
     * @param accounts 2D array of customer balances
     * @return Maximum wealth among all customers
     */
    public int maximumWealth(int[][] accounts) {
        if (accounts == null || accounts.length == 0) {
            return 0;
        }
        int maxWealth = 0;
        for (int[] customer : accounts) {
            int sum = 0;
            for (int money : customer) {
                sum += money;
            }
            if (sum > maxWealth) {
                maxWealth = sum;
            }
        }
        return maxWealth;
    }

    public static void main(String[] args) {
        RichestCustomerWealth solution = new RichestCustomerWealth();

        int[][] accounts1 = {
                {1, 2, 3},
                {3, 2, 1}
        };
        System.out.println("Test Case 1: " + solution.maximumWealth(accounts1));
        // Expected: 6

        int[][] accounts2 = {
                {1, 5},
                {7, 3},
                {3, 5}
        };
        System.out.println("Test Case 2: " + solution.maximumWealth(accounts2));
        // Expected: 10

        int[][] accounts3 = {
                {2, 8, 7},
                {7, 1, 3},
                {1, 9, 5}
        };
        System.out.println("Test Case 3: " + solution.maximumWealth(accounts3));
        // Expected: 17
    }
}
