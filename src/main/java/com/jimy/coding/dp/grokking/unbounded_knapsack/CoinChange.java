package com.jimy.coding.dp.grokking.unbounded_knapsack;

/**
 * Created by Jimy on 10-04-2020.
 * <p>
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the total number of distinct ways to make up that amount.
 * <p>
 * Example:
 * <p>
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 5
 * Explanation: There are five ways to make the change for '5', here are those ways:
 * 1. {1,1,1,1,1}
 * 2. {1,1,1,2}
 * 3. {1,2,2}
 * 4. {1,1,3}
 * 5. {2,3}
 */
public class CoinChange {

    /**
     * Approach 1: This is top-down recursive approach with memoization.
     */
    static int solveCoinChangeRecursiveWithMemoization(Integer[][] dp, int[] denominations, int amount, int index) {

        int len = denominations.length;

        // base checks
        if (amount == 0) {
            return 1;
        }

        if (amount < 0 || index == len) {
            return 0;
        }

        // calculating other sub-problems
        if (dp[index][amount] == null) {
            int include = 0, exclude = 0;

            if (denominations[index] <= amount) {
                include = solveCoinChangeRecursiveWithMemoization(dp, denominations, amount - denominations[index], index);
            }
            exclude = solveCoinChangeRecursiveWithMemoization(dp, denominations, amount, index + 1);
            dp[index][amount] = include + exclude;
        }
        return dp[index][amount];
    }

    static int solveCoinChangeRecursiveWithMemoization(int[] denominations, int amount) {
        Integer[][] dp = new Integer[denominations.length][amount + 1];
        return solveCoinChangeRecursiveWithMemoization(dp, denominations, amount, 0);
    }

    /**
     * Approach 2: This is bottom-up dynamic programming approach.
     */
    static int solveCoinChangeDP(int[] denominations, int amount) {

        int len = denominations.length;
        // base checks
        if (amount < 0) {
            return 0;
        }

        int[][] dp = new int[len][amount + 1];

        // populating 0th column corresponding to amount = 0 which can be fulfilled by a null array
        for (int row = 0; row < len; row++) {
            dp[row][0] = 1;
        }

        // populating remaining sub-problems
        for (int row = 0; row < len; row++) {
            for (int column = 1; column <= amount; column++) {

                int include = 0, exclude = 0;
                if (denominations[row] <= column) {
                    include = dp[row][column - denominations[row]];
                }

                if (row > 0) {
                    exclude = dp[row - 1][column];
                }

                dp[row][column] = include + exclude;
            }
        }
        //DPUtils.print2DArray(dp, len, amount + 1);
        return dp[len - 1][amount];
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        System.out.println("Result (solveCoinChangeRecursive): " + solveCoinChangeRecursiveWithMemoization(denominations, 5));
        System.out.println("Result (solveCoinChangeDP): " + solveCoinChangeDP(denominations, 5));
    }
}
