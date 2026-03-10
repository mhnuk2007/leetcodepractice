package arrays;

/**
 * LeetCode 121: Best Time to Buy and Sell Stock
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * <p>
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * <p>
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit is 0.
 */
public class BestTimeToBuyAndSellStock {

    /**
     * Calculates the maximum profit that can be achieved by buying and selling a stock once.
     * <p>
     * This method uses a "one-pass" approach. It iterates through the prices array while keeping track of two key variables:
     * 1. `minPrice`: The lowest stock price encountered so far.
     * 2. `maxProfit`: The maximum profit found so far.
     * <p>
     * For each day, it updates `minPrice` if the current price is lower. Then, it calculates the potential profit if we were to sell on the current day
     * (current price - minPrice) and updates `maxProfit` if this potential profit is higher.
     *
     * @param prices An array of stock prices for each day.
     * @return The maximum achievable profit.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int currentPrice : prices) {
            // Update the minimum price seen so far
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }
            // Calculate potential profit and update maxProfit if it's higher
            else if (currentPrice - minPrice > maxProfit) {
                maxProfit = currentPrice - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();

        // Test Case 1: Profitable transaction
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test Case 1 (Input: [7,1,5,3,6,4]): " + solution.maxProfit(prices1)); // Expected: 5

        // Test Case 2: No profitable transaction (decreasing prices)
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test Case 2 (Input: [7,6,4,3,1]): " + solution.maxProfit(prices2)); // Expected: 0

        // Test Case 3: Empty array
        int[] prices3 = {};
        System.out.println("Test Case 3 (Input: []): " + solution.maxProfit(prices3)); // Expected: 0

        // Test Case 4: All prices are the same
        int[] prices4 = {5, 5, 5, 5};
        System.out.println("Test Case 4 (Input: [5,5,5,5]): " + solution.maxProfit(prices4)); // Expected: 0
    }
}
