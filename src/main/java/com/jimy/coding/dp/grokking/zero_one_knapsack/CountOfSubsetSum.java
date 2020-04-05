package com.jimy.coding.dp.grokking.zero_one_knapsack;

/**
 * Created by Jimy on 05-04-2020.
 * Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.
 * <p>
 * Example 1: #
 * Input: {1, 1, 2, 3}, S=4
 * Output: 3
 * The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
 * Note that we have two similar sets {1, 3}, because we have two '1' in our input.
 * Example 2: #
 * Input: {1, 2, 7, 1, 5}, S=9
 * Output: 3
 * The given set has '3' subsets whose sum is '9': {2, 7}, {1, 7, 1}, {1, 2, 1, 5}
 */
public class CountOfSubsetSum {

    /**
     * Approach 1: This is top-down brute-force recursive approach.
     */
    static int countSubsetSumRecursive(int[] arr, int target, int index, int count) {

        // base conditions
        if (target == 0) {
            return count + 1;
        }

        if (index == arr.length) {
            return count;
        }

        // calculating other sub-problems
        int include = 0, exclude;
        if (arr[index] <= target) {
            include = countSubsetSumRecursive(arr, target - arr[index], index + 1, count);
        }

        exclude = countSubsetSumRecursive(arr, target, index + 1, count);
        return include + exclude;
    }

    /**
     * Approach 2: This is top-down brute-force recursive approach with memoization.
     */
    static int countSubsetSumRecursiveWithMemoization(Integer[][] dp, int[] arr, int target, int index, int count) {

        // base conditions
        if (target == 0) {
            return count + 1;
        }

        if (index == arr.length) {
            return count;
        }

        // calculating other sub-problems
        if (dp[index][target] == null) {
            int include = 0, exclude;
            if (arr[index] <= target) {
                include = countSubsetSumRecursiveWithMemoization(dp, arr, target - arr[index], index + 1, count);
            }

            exclude = countSubsetSumRecursiveWithMemoization(dp, arr, target, index + 1, count);
            dp[index][target] = include + exclude;
        }

        return dp[index][target];
    }

    static int countSubsetSumRecursiveWithMemoization(int[] arr, int target) {
        Integer[][] dp = new Integer[arr.length][target + 1];
        return countSubsetSumRecursiveWithMemoization(dp, arr, target, 0, 0);
    }

    /**
     * Approach 3: This is bottom-up dynamic programming approach.
     */
    static int countSubsetSumsDP(int[] arr, int target) {

        // base condition
        if (target == 0) {
            return 0;
        }

        int len = arr.length;
        int[][] dp = new int[len][target + 1];

        // populate 0th column corresponding to target = 0
        // which is 1 for all arr elements because it can be satisfied by null set.
        for (int row = 0; row < len; row++) {
            dp[row][0] = 1;
        }

        // populate 0th row corresponding to arr[0]
        for (int column = 1; column <= target; column++) {
            if (arr[0] == column)
                dp[0][column] = arr[0];
        }

        for (int row = 1; row < len; row++) {
            for (int column = 1; column <= target; column++) {

                int include = 0, exclude;

                if (arr[row] <= column) {
                    include = dp[row - 1][column - arr[row]];
                }
                exclude = dp[row - 1][column];
                dp[row][column] = include + exclude;
            }
        }
        //DPUtils.print2DArray(dp, len, target + 1);
        return dp[len - 1][target];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 3};
        System.out.println("Count (countSubsetSumRecursive): " + countSubsetSumRecursive(arr1, 4, 0, 0));
        System.out.println("Count (countSubsetSumRecursiveWithMemoization): " + countSubsetSumRecursiveWithMemoization(arr1, 4));
        System.out.println("Count (countSubsetSumsDP): " + countSubsetSumsDP(arr1, 4));

        int[] arr2 = {1, 2, 7, 1, 5};
        System.out.println("Count (countSubsetSumRecursive): " + countSubsetSumRecursive(arr2, 9, 0, 0));
        System.out.println("Count (countSubsetSumRecursiveWithMemoization): " + countSubsetSumRecursiveWithMemoization(arr2, 9));
        System.out.println("Count (countSubsetSumsDP): " + countSubsetSumsDP(arr2, 9));
    }
}
