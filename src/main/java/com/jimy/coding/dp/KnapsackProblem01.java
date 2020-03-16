package com.jimy.coding.dp;

/**
 * 0-1 Knapsack Problem
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
 * in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and
 * weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find
 * out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W.
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 */

public class KnapsackProblem01 {

    static int[][] calculateKnapsack(int[] weight, int[] value, int totalWeight) {

        int length = weight.length;
        int[][] table = new int[length + 1][1 + totalWeight];

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= totalWeight; j++) {

                if (weight[i - 1] > j) {
                    table[i][j] = table[i - 1][j];

                } else {

                    table[i][j] = Math.max(value[i - 1] + table[i - 1][j - weight[i - 1]], table[i - 1][j]);

                }
                //DPUtils.print2DArray(table, length + 1, totalWeight + 1);
            }
        }
        return table;
    }

    static void findKnapsackElements(int[] weight, int[][] table, int rows, int columns) {

        int c = columns - 1, r = rows - 1;

        while (c > 0) {

            if (table[r][c] == table[r - 1][c]) {
                r--;
            } else {
                System.out.println(weight[r - 1]);
                c -= weight[r - 1];
            }
        }

    }

    public static void main(String[] args) {

        int[] weight = new int[]{1, 3, 4, 5};
        int[] val = new int[]{1, 4, 5, 7};
        int totalWeight = 7;
        int length = weight.length;
        int[][] table = calculateKnapsack(weight, val, totalWeight);
        System.out.println("0/1 Knapsack value: " + table[length][totalWeight]);
        System.out.println("----------------Elements-----------------");
        findKnapsackElements(weight, table, length + 1, totalWeight + 1);
    }
}
