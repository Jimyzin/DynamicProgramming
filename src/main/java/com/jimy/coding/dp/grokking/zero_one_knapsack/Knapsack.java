package com.jimy.coding.dp.grokking.zero_one_knapsack;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 25-03-2020.
 */
public class Knapsack {

    /**
     * This is a recursive top-down approach with memoization.
     */
    static int knapsackRecursive(Integer[][] dp, int[] weights, int[] profits, int capacity, int index) {
        // base conditions
        if (capacity <= 0 || index >= profits.length) {
            return 0;
        }

        if(dp[index][capacity] != null) {
            return dp[index][capacity];
        }

        int profits1 = 0;
        if (weights[index] <= capacity) {
            profits1 = profits[index] + knapsackRecursive(dp, weights, profits, capacity - weights[index], index + 1);
        }

        int profits2 = knapsackRecursive(dp, weights, profits, capacity, index + 1);

        dp[index][capacity] = Math.max(profits1, profits2);
        //DPUtils.print2DArray(dp, 4, 6);
        return dp[index][capacity];
    }

    static int solveKnapsack(int[] weights, int[] profits, int capacity) {
        Integer[][] dp = new Integer[weights.length][capacity + 1];
        return knapsackRecursive(dp, weights, profits, capacity, 0);
    }

    /**
     * This is a bottom-up dynamic programming approach using a 2D array.
     */
    static int knapsackDP(int[] weights, int[] profits, int capacity) {

        int len = weights.length;
        int[][] dp = new int[len][capacity + 1];

        // populating the first column (column index : 0) that represents capacity = 0
        for(int row = 0; row < len; row++) {
           dp[row][0] = 0;
        }

        // populating the first row (row index : 0) with the profit of 0th item only if the weight of the 0th item is
        // lesser than or equal to the column index (column index = capacity at that index)
        for(int column = 1; column <= capacity; column++) {
            if(weights[0] <= column) {
                dp[0][column] = profits[0];
            }
        }

        // populate the remaining cells
        for(int row = 1; row < len; row++) {
            for(int column = 1; column <= capacity; column++) {
                int profit1 = Integer.MIN_VALUE, profit2;

                if(weights[row] <= column) {
                    profit1 = profits[row] + dp[row - 1][column - weights[row]];
                }

                profit2 = dp[row - 1][column];
                dp[row][column] = Math.max(profit1, profit2);
            }
        }

        return dp[len - 1][capacity];

    }

    /**
     * This is a bottom-up dynamic programming approach using a 2D array.
     * This is optimised to have space complexity of O(capacity)
     */
    static int solveKnapsackSpaceOptimised(int[] profits, int[] weights, int capacity) {
        //TODO: Write - Your - Code
        int len = weights.length;
        int[][] dp = new int[2][capacity + 1];

        // populate dp[] for element 0
        for(int i = 0; i <= capacity; i++) {
            if(weights[0] <= i) {
                dp[0][i] = profits[0];
            }
        }

        for(int w = 1; w < len; w++) {
            for(int c = 1; c <= capacity; c++) {
                int profit1 = Integer.MIN_VALUE, profit2;

                if(weights[w] <= c) {
                    profit1 = profits[w] + dp[0][c - weights[w]];
                }

                profit2 = dp[0][c];
                dp[1][c] = Math.max(profit1, profit2);
                //System.out.print(dp[1][c]+"\t");
            }
            //System.out.println();

            for(int c = 1; c <= capacity; c++) {
                dp[0][c] = dp[1][c];
            }
        }

        return dp[1][capacity];

    }

    //Driver method
    public static void main(String[] args) {

        int[] profits = {1, 6, 10, 16};
        int[] weights= {1, 2, 3, 5};

        System.out.println("The result: "+ solveKnapsack(weights, profits, 6));
        System.out.println("The result: "+ knapsackDP(weights, profits, 6));
        System.out.println("The result: "+ solveKnapsackSpaceOptimised(profits, weights, 6));
    }
}
