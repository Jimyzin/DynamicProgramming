package com.jimy.coding.dp.grokking.zero_one_knapsack;

/**
 * Created by Jimy on 02-04-2020.
 * <p>
 * Given a set of positive numbers, partition the set into two subsets with a minimum difference between their subset sums.
 * <p>
 * Example 1: #
 * Input: {1, 2, 3, 9}
 * Output: 3
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
 * Example 2: #
 * Input: {1, 2, 7, 1, 5}
 * Output: 0
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of number is '0'. Following are the two subsets: {1, 2, 5} & {7, 1}.
 * Example 3: #
 * Input: {1, 3, 100, 4}
 * Output: 92
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '92'. Here are the two subsets: {1, 3, 4} & {100}.
 */
public class MinimumSubsetSumDifference {

    /**
     * Approach 1: This is brute-force top-down recursive approach.
     */
    static int getMinimumSubsetSumDifferenceRecursive(int[] arr, int index, int subset1, int subset2) {

        // base condition
        if (index >= arr.length) {
            return Math.abs(subset1 - subset2);
        }

        // calculating differences of sub-problems using 0/1 Knapsack-like algorithm
        int diff1 = getMinimumSubsetSumDifferenceRecursive(arr, index + 1, subset1 + arr[index], subset2);
        int diff2 = getMinimumSubsetSumDifferenceRecursive(arr, index + 1, subset1, subset2 + arr[index]);

        return Math.min(diff1, diff2);
    }

    /**
     * Approach 2: Brute-force, top-down, recursive with memoization.
     */
    static int getMinimumSubsetSumDifferenceRecursiveWithMemoization(Integer[][] dp, int[] arr, int index, int subset1, int subset2) {

        // base condition
        if (index >= arr.length) {
            return Math.abs(subset1 - subset2);
        }

        if (dp[index][subset1] == null) {
            // calculating differences of sub-problems using 0/1 Knapsack-like algorithm
            int diff1 = getMinimumSubsetSumDifferenceRecursiveWithMemoization(dp, arr, index + 1, subset1 + arr[index], subset2);
            int diff2 = getMinimumSubsetSumDifferenceRecursiveWithMemoization(dp, arr, index + 1, subset1, subset2 + arr[index]);
            dp[index][subset1] = Math.min(diff1, diff2);
        }

        return dp[index][subset1];
    }

    static int getMinimumSubsetSumDifferenceRecursiveWithMemoization(int[] arr) {
        int total = 0;

        for (int a : arr) {
            total += a;
        }

        Integer[][] dp = new Integer[arr.length][total + 1];
        int result = getMinimumSubsetSumDifferenceRecursiveWithMemoization(dp, arr, 0, 0, 0);
        //DPUtils.print2DArray(dp, arr.length, total + 1);
        return result;
    }

    /**
     * Approach 3: Bottom-up dynamic programming approach.
     */
    static int getMinimumSubsetSumDifferenceDP(int[] arr) {

        int total = 0, len = arr.length;
        for (int a : arr)
            total += a;

        boolean[][] dp = new boolean[len][1 + (total / 2)];

        // populating 0th column representing target = 0. This must, therefore, be true for all arr[] elements.
        for (int row = 0; row < len; row++) {
            dp[row][0] = true;
        }

        // populating the 0th row
        for (int column = 1; column <= total / 2; column++) {
            if (arr[0] == column) {
                dp[0][column] = true;
                break;
            }

        }

        // populating remaining sub-problems
        for (int row = 1; row < len; row++) {
            for (int column = 1; column <= total / 2; column++) {

                boolean include = false, exclude;
                if (arr[row] <= column) {
                    include = dp[row - 1][column - arr[row]];
                }

                exclude = dp[row - 1][column];
                dp[row][column] = include || exclude;
            }
        }

        // searching for the greatest index with value 'true' in the last row
        int subsetSum1 = 0;
        for (int column = (total / 2); column >= 0; column--) {
            if (dp[len - 1][column]) {
                subsetSum1 = column;
                break;
            }
        }
        int subsetSum2 = total - subsetSum1;
        return Math.abs(subsetSum1 - subsetSum2);
    }

    public static void main(String[] main) {
        int[] arr1 = {1, 2, 3, 9};
        int total = 0;

        for (int a : arr1) {
            total += a;
        }
        System.out.println("Result(getMinimumSubsetSumDifferenceRecursive): " + getMinimumSubsetSumDifferenceRecursive(arr1, 0, 0, 0));
        System.out.println("Result(getMinimumSubsetSumDifferenceRecursiveWithMemoization): " + getMinimumSubsetSumDifferenceRecursiveWithMemoization(arr1));
        System.out.println("Result(getMinimumSubsetSumDifferenceDP): " + getMinimumSubsetSumDifferenceDP(arr1));

        int[] arr2 = {1, 2, 7, 1, 5};

        for (int a : arr2) {
            total += a;
        }
        System.out.println("Result(getMinimumSubsetSumDifferenceRecursive): " + getMinimumSubsetSumDifferenceRecursive(arr2, 0, 0, 0));
        System.out.println("Result(getMinimumSubsetSumDifferenceRecursiveWithMemoization): " + getMinimumSubsetSumDifferenceRecursiveWithMemoization(arr2));
        System.out.println("Result(getMinimumSubsetSumDifferenceDP): " + getMinimumSubsetSumDifferenceDP(arr2));

        int[] arr3 = {1, 3, 100, 4};

        for (int a : arr3) {
            total += a;
        }
        System.out.println("Result(getMinimumSubsetSumDifferenceRecursive): " + getMinimumSubsetSumDifferenceRecursive(arr3, 0, 0, 0));
        System.out.println("Result(getMinimumSubsetSumDifferenceRecursiveWithMemoization): " + getMinimumSubsetSumDifferenceRecursiveWithMemoization(arr3));
        System.out.println("Result(getMinimumSubsetSumDifferenceDP): " + getMinimumSubsetSumDifferenceDP(arr3));
    }
}
