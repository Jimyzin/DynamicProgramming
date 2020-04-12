package com.jimy.coding.dp.grokking.unbounded_knapsack;

/**
 * Created by Jimy on 10-04-2020.
 * <p>
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the minimum number of coins needed to make up that amount.
 * <p>
 * Example 1:
 * <p>
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 2
 * Explanation: We need minimum of two coins {2,3} to make a total of '5'
 * Example 2:
 * <p>
 * Denominations: {1,2,3}
 * Total amount: 11
 * Output: 4
 * Explanation: We need minimum four coins {2,3,3,3} to make a total of '11'
 */
public class MinimumCoinChange {

    /**
     * Approach 1: This is brute-force top-down recursive approach with memoization.
     */
    static int solveMinimumCoinChangeRecursiveWithMemoization(Integer[][] dp, int[] denominations, int amount, int index) {

        int len = denominations.length;
        // base checks
        if (amount == 0) {
            return 0;
        }

        if (index == len || amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[index][amount] == null) {
            int include = Integer.MAX_VALUE, exclude = Integer.MAX_VALUE;

            if (denominations[index] <= amount) {
                int temp = solveMinimumCoinChangeRecursiveWithMemoization(dp, denominations, amount - denominations[index], index);
                if (temp != Integer.MAX_VALUE) {
                    include = 1 + temp;
                }
            }
            exclude = solveMinimumCoinChangeRecursiveWithMemoization(dp, denominations, amount, index + 1);
            dp[index][amount] = Math.min(include, exclude);
        }
        return dp[index][amount];
    }

    static int solveMinimumCoinChangeRecursiveWithMemoization(int[] denominations, int amount) {
        Integer[][] dp = new Integer[denominations.length][amount + 1];
        int result = solveMinimumCoinChangeRecursiveWithMemoization(dp, denominations, amount, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * Approach 2: This is bottom-up dynamic programming approach.
     */
    static int solveMinimumCoinChangeDP(int[] denominations, int amount) {

        // base check
        if (amount < 0) {
            return 0;
        }

        int len = denominations.length;
        int[][] dp = new int[len][amount + 1];

        // populating sub-problems
        for (int row = 0; row < len; row++) {
            for (int column = 1; column <= amount; column++) {
                int include = Integer.MAX_VALUE, exclude = Integer.MAX_VALUE;

                if (denominations[row] <= column && dp[row][column - denominations[row]] != Integer.MAX_VALUE) {
                    include = 1 + dp[row][column - denominations[row]];
                }

                if (row > 0) {
                    exclude = dp[row - 1][column];
                }

                dp[row][column] = Math.min(include, exclude);
            }
        }
        // DPUtils.print2DArray(dp, len, amount + 1);

        // Finding out the elements
        int row = len - 1, column = amount;
        while (row > 0 && column > 0) {
            if (dp[row][column] == dp[row - 1][column]) {
                row -= 1;

            } else {
                System.out.print(denominations[row] + "\t");
                column -= denominations[row];
            }
        }
        System.out.println();

        return dp[len - 1][amount] == Integer.MAX_VALUE ? -1 : dp[len - 1][amount];
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        System.out.println("Result (solveMinimumCoinChangeRecursiveWithMemoization): " + solveMinimumCoinChangeRecursiveWithMemoization(denominations, 5));
        System.out.println("Result (solveMinimumCoinChangeDP): " + solveMinimumCoinChangeDP(denominations, 5));
        System.out.println("Result (solveMinimumCoinChangeRecursiveWithMemoization): " + solveMinimumCoinChangeRecursiveWithMemoization(denominations, 11));
        System.out.println("Result (solveMinimumCoinChangeDP): " + solveMinimumCoinChangeDP(denominations, 11));
    }
}
