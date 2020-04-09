package com.jimy.coding.dp.grokking.unbounded_knapsack;

/**
 * Created by Jimy on 09-04-2020.
 *
 * Given a rod of length ‘n’, we are asked to cut the rod and sell the pieces in a way that
 * will maximize the profit. We are also given the price of every piece of length ‘i’ where ‘1 <= i <= n’.
 */
public class RodCutting {

    /**
     * This is dynamic programming approach with time complexity O(prices.length x rodLength)
     * and space complexity O(prices.length x rodLength). This method also lists the selected
     * prices.
     */
    static int solveRodCutting(int[] lengths, int[] prices, int rodLength) {

        int len = prices.length;
        // base checks
        if (rodLength <= 0 || len == 0 || len != lengths.length) {
            return 0;
        }

        int[][] dp = new int[len][rodLength + 1];

        // populating 0th column corresponding to rodLength = 0
        for (int row = 0; row < len; row++) {
            dp[row][0] = 0;
        }

        // populating remaining sub-problems
        for (int row = 0; row < len; row++) {
            for (int column = 1; column <= rodLength; column++) {
                int include = 0, exclude = 0;

                if (lengths[row] <= column) {
                    include = prices[row] + dp[row][column - lengths[row]];
                }

                if (row > 0) {
                    exclude = dp[row - 1][column];
                }

                dp[row][column] = Math.max(include, exclude);
            }
        }

        // finding the elements
        int column = rodLength, row = len - 1;
        while (column > 0 && row > 0) {

            if (dp[row][column] == dp[row - 1][column]) {
                row--;

            } else {
                System.out.print(prices[row]+"\t");
                column -= lengths[row];
            }
        }
        System.out.println(prices[row]);

        return dp[len - 1][rodLength];
    }

    public static void main(String[] args) {
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        System.out.println("Result: "+ solveRodCutting(lengths, prices, 5));
    }
}
