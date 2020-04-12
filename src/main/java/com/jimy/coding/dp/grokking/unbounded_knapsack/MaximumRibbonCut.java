package com.jimy.coding.dp.grokking.unbounded_knapsack;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 11-04-2020.
 */
public class MaximumRibbonCut {

    /**
     * Approach 1 : This is brute-force top-down recursive approach.
     */
    static int solveMaximumRibbonCutRecursive(int[] ribbonLengths, int totalLength, int index) {

        int len = ribbonLengths.length;
        // base checks
        if (totalLength == 0) {
            return 0;
        }

        if (index == len || len == 0) {
            return Integer.MIN_VALUE;
        }

        int include = Integer.MIN_VALUE, exclude;
        if (ribbonLengths[index] <= totalLength) {
            int temp = solveMaximumRibbonCutRecursive(ribbonLengths, totalLength - ribbonLengths[index], index);

            if (temp != Integer.MIN_VALUE) {
                include = 1 + temp;
            }
        }
        exclude = solveMaximumRibbonCutRecursive(ribbonLengths, totalLength, index + 1);
        return Math.max(include, exclude);
    }

    static int solveMaximumRibbonCutRecursive(int[] ribbonLengths, int totalLength) {
        int result = solveMaximumRibbonCutRecursive(ribbonLengths, totalLength, 0);
        return result == Integer.MIN_VALUE ? -1 : result;
    }

    /**
     * Approach 1 : This is bottom-up dynamic programming approach.
     */
    static int solveMaximumRibbonCutDP(int[] ribbonLengths, int totalLength) {

        // base checks
        if (totalLength == 0) {
            return 0;
        }

        int len = ribbonLengths.length;
        int[][] dp = new int[len][totalLength + 1];

        for (int row = 0; row < len; row++) {
            for (int column = 1; column <= totalLength; column++) {
                dp[row][column] = Integer.MIN_VALUE;
            }
        }

        for (int row = 0; row < len; row++) {
            for (int column = 1; column <= totalLength; column++) {
                int include = Integer.MIN_VALUE, exclude = Integer.MIN_VALUE;

                if (ribbonLengths[row] <= column && dp[row][column - ribbonLengths[row]] != Integer.MIN_VALUE) {
                    include = 1 + dp[row][column - ribbonLengths[row]];
                }

                if (row > 0) {
                    exclude = dp[row - 1][column];
                }
                dp[row][column] = Math.max(include, exclude);
            }
        }
        // DPUtils.print2DArray(dp, len, totalLength + 1);
        return dp[len - 1][totalLength] == Integer.MIN_VALUE ? -1 : dp[len - 1][totalLength];

    }

    public static void main(String[] args) {
        int[] ribbonLengths1 = {2, 3, 5};
        System.out.println("Result (solveMaximumRibbonCutDP): " + solveMaximumRibbonCutDP(ribbonLengths1, 5));
        System.out.println("Result (solveMaximumRibbonCutRecursive): " + solveMaximumRibbonCutRecursive(ribbonLengths1, 5));

        int[] ribbonLengths2 = {2, 3};
        System.out.println("Result (solveMaximumRibbonCutDP): " + solveMaximumRibbonCutDP(ribbonLengths2, 7));
        System.out.println("Result (solveMaximumRibbonCutRecursive): " + solveMaximumRibbonCutRecursive(ribbonLengths2, 7));

        int[] ribbonLengths3 = {3, 5, 7};
        System.out.println("Result (solveMaximumRibbonCutDP): " + solveMaximumRibbonCutDP(ribbonLengths3, 13));
        System.out.println("Result (solveMaximumRibbonCutRecursive): " + solveMaximumRibbonCutRecursive(ribbonLengths3, 13));

        int[] ribbonLengths4 = {3, 5};
        System.out.println("Result (solveMaximumRibbonCutDP): " + solveMaximumRibbonCutDP(ribbonLengths4, 7));
        System.out.println("Result (solveMaximumRibbonCutRecursive): " + solveMaximumRibbonCutRecursive(ribbonLengths4, 7));
    }
}
