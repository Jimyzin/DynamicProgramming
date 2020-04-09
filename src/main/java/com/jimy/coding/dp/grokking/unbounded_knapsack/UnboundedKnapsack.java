package com.jimy.coding.dp.grokking.unbounded_knapsack;

/**
 * Created by Jimy on 09-04-2020.
 *
 * Given two integer arrays to represent weights and profits of ‘N’ items, we need to find
 * a subset of these items which will give us maximum profit such that their cumulative
 * weight is not more than a given number ‘C’. We can assume an infinite supply of item
 * quantities; therefore, each item can be selected multiple times.
 */
public class UnboundedKnapsack {

    /**
     * Approach 1: This is top-down recursive approach.
     */
    static int solveKnapsackRecursive(int[] weights, int[] profits, int target, int index) {

        // base check
        if (target <= 0 || profits.length == 0 || profits.length != weights.length || index >= profits.length) {
            return 0;
        }

        // calculate other sub-problems
        int profits1 = 0, profits2;
        // including current index
        if (weights[index] <= target) {
            profits1 = profits[index] + solveKnapsackRecursive(weights, profits, target - weights[index], index);
        }
        // excluding current index
        profits2 = solveKnapsackRecursive(weights, profits, target, index + 1);

        return Math.max(profits1, profits2);
    }

    /**
     * Approach 2: This is top-down recursive approach with memoization.
     */
    static int solveKnapsackRecursiveWithMemoization(Integer[][] dp, int[] weights, int[] profits, int target, int index) {
        // base check
        if (target <= 0 || profits.length == 0 || profits.length != weights.length || index >= profits.length) {
            return 0;
        }

        if (dp[index][target] == null) {
            // calculate other sub-problems
            int profits1 = 0, profits2;
            // including current index
            if (weights[index] <= target) {
                profits1 = profits[index] + solveKnapsackRecursive(weights, profits, target - weights[index], index);
            }
            // excluding current index
            profits2 = solveKnapsackRecursive(weights, profits, target, index + 1);
            dp[index][target] = Math.max(profits1, profits2);
        }

        return dp[index][target];
    }

    static int solveKnapsackRecursiveWithMemoization(int[] weights, int[] profits, int target) {

        int len = profits.length;
        Integer[][] dp = new Integer[len][target + 1];
        return solveKnapsackRecursiveWithMemoization(dp, weights, profits, target, 0);
    }

    /**
     * Approach 3: This is bottom-up dynamic programming approach.
     */
    static int solveKnapsackDP(int[] weights, int[] profits, int target) {

        // base checks
        if (target <= 0 || profits.length == 0 || profits.length != weights.length) {
            return 0;
        }

        int len = profits.length;
        int[][] dp = new int[len][target + 1];

        // populating 0th column corresponding to target = 0
        for (int row = 0; row < len; row++) {
            dp[row][0] = 0;
        }

        for (int row = 0; row < len; row++) {
            for (int column = 1; column <= target; column++) {
                int include = 0, exclude = 0;

                if (weights[row] <= column) {
                    include = profits[row] + dp[row][column - weights[row]];
                }

                if (row > 0) {
                    exclude = dp[row - 1][column];
                }

                dp[row][column] = Math.max(include, exclude);
            }
        }
        return dp[len -1][target];
    }

    public static void main(String[] args) {
        int[] weights = {1, 3, 4, 5};
        int[] profits = {15, 50, 60, 90};
        System.out.println("Result (solveKnapsackRecursive): "+ solveKnapsackRecursive(weights, profits, 8, 0));
        System.out.println("Result (solveKnapsackRecursiveWithMemoization): "+ solveKnapsackRecursiveWithMemoization(weights, profits, 8));
        System.out.println("Result (solveKnapsackDP): "+ solveKnapsackDP(weights, profits, 8));
    }
}
