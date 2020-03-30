package com.jimy.coding.dp.grokking.zero_one_knapsack;

/**
 * Created by Jimy on 30-03-2020.
 */
public class SubsetSum {

    /**
     * Approach 1: Brute force. This is a recursive top-down approach.
     */
    static boolean canPartitionRecursive(int[] arr, int target, int index) {

        // base condition
        if (target == 0) {
            return true;
        }

        if (index >= arr.length) {
            return false;
        }

        boolean include = false, exclude;

        // calculate sub-problems
        if (arr[index] <= target) {
            include = canPartitionRecursive(arr, target - arr[index], index + 1);
        }

        exclude = canPartitionRecursive(arr, target, index + 1);

        return include || exclude;
    }

    /**
     * Approach 2: Brute force. This is a recursive top-down approach with memoization.
     */
    static boolean canPartitionRecursiveWithMemoization(Boolean[][] dp, int[] arr, int target, int index) {

        // base condition
        if (target == 0) {
            return true;
        }

        if (index >= arr.length) {
            return false;
        }

        // if previously calculated, fetch it from dp[][]
        if (dp[index][target] != null) {
            return dp[index][target];
        }

        boolean include = false, exclude;

        // calculate sub-problems
        if (arr[index] <= target) {
            include = canPartitionRecursiveWithMemoization(dp, arr, target - arr[index], index + 1);
        }

        exclude = canPartitionRecursiveWithMemoization(dp, arr, target, index + 1);
        dp[index][target] = include || exclude;

        return dp[index][target];
    }

    static boolean canPartitionRecursiveWithMemoization(int[] arr, int target) {
        Boolean[][] dp = new Boolean[arr.length][target + 1];
        return canPartitionRecursiveWithMemoization(dp, arr, target, 0);
    }

    /**
     * Approach 3: This is a bottom-up dynamic programming approach. This is the most efficient.
     */
    static boolean canPartitionDP(int[] arr, int target) {

        // base condition
        if (target == 0) {
            return true;
        }

        int len = arr.length;
        boolean[][] dp = new boolean[len][target + 1];

        // populating 0th row corresponding to the 1st element in arr[]
        for (int column = 1; column <= target; column++) {
            if (arr[0] == column) {
                dp[0][column] = true;
            }
        }

        // populating/calculating remaining sub-problems
        for (int row = 1; row < len; row++) {
            for (int column = 1; column <= target; column++) {

                boolean include = false, exclude;

                if (arr[row] <= column) {
                    include = dp[row - 1][column - arr[row]];
                }

                exclude = dp[row - 1][column];
                dp[row][column] = include || exclude;
            }
        }

        return dp[len - 1][target];

    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 7};
        System.out.println("Result(canPartitionRecursive): " + canPartitionRecursive(arr1, 6, 0));
        System.out.println("Result(canPartitionRecursiveWithMemoization): " + canPartitionRecursiveWithMemoization(arr1, 6));
        System.out.println("Result(canPartitionDP): " + canPartitionDP(arr1, 6));

        int[] arr2 = {1, 2, 7, 1, 5};
        System.out.println("Result(canPartitionRecursive): " + canPartitionRecursive(arr2, 10, 0));
        System.out.println("Result(canPartitionRecursiveWithMemoization): " + canPartitionRecursiveWithMemoization(arr2, 10));
        System.out.println("Result(canPartitionDP): " + canPartitionDP(arr2, 10));

        int[] arr3 = {1, 3, 4, 8};
        System.out.println("Result(canPartitionRecursive): " + canPartitionRecursive(arr3, 6, 0));
        System.out.println("Result(canPartitionRecursiveWithMemoization): " + canPartitionRecursiveWithMemoization(arr3, 6));
        System.out.println("Result(canPartitionDP): " + canPartitionDP(arr3, 6));
    }
}

