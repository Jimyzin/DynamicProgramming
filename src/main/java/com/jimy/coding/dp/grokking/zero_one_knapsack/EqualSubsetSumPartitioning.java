package com.jimy.coding.dp.grokking.zero_one_knapsack;

/**
 * Created by Jimy on 30-03-2020.
 */
public class EqualSubsetSumPartitioning {

    /**
     * This is to determine if subset partitioning using pure recursive approach (top-down).
     */
    static boolean canPartitionRecursive(int[] arr, int target, int index) {

        // base conditions
        if(target == 0) {
            return true;
        }

        if(index >= arr.length) {
            return false;
        }


        boolean include = false, exclude;
        if(arr[index] <= target) {
            include = canPartitionRecursive(arr, target - arr[index], index + 1);
        }

        exclude = canPartitionRecursive(arr, target, index + 1);
        return include || exclude;
    }

    /**
     * This is to determine if subset partitioning using pure recursive approach using memoization (top-down).
     */
    static boolean canPartitionRecursiveWithMemoization(Boolean [][] dp, int[] arr, int target, int index) {

        // base conditions
        if(target == 0) {
            return true;
        }

        if(index >= arr.length) {
            return false;
        }

        // if previously calculated, fetch from dp[][]
        if (dp[index][target] != null) {
            return dp[index][target];
        }

        // Otherwise, calculate the result
        boolean include = false, exclude;
        if(arr[index] <= target) {
            include = canPartitionRecursiveWithMemoization(dp, arr, target - arr[index], index + 1);
        }

        exclude = canPartitionRecursiveWithMemoization(dp, arr, target, index + 1);
        dp[index][target] = include || exclude;
        return dp[index][target];
    }

    static boolean canPartitionRecursiveWithMemoization(int[] arr, int target, int index) {
        Boolean[][] dp = new Boolean[arr.length][target + 1];
        return canPartitionRecursiveWithMemoization(dp, arr, target, index);
    }

    /**
     * This is determine if subset partitioning is possible using dynamic programming approach (bottom-up).
     */
    static boolean canPartitionDP(int[] arr) {

        int total = 0;
        for(int a : arr)
            total += a;

        // base condition
        if(total % 2 == 1) {
            return false;
        }

        int len = arr.length;
        int target = total / 2;
        boolean[][] dp = new boolean[len][target + 1];

        // populating the 0th column which signifies target = 0
        for(int row = 0; row < len; row++)
            dp[row][0] = true;

        // populating the 0th row
        for(int column = 1; column <= target; column++) {
            // as only one element is used, the outcome is true only when target
            // (column index = target) equals the 0th array element
            if(arr[0] == column)
                dp[0][column] = true;
        }

        // populate the remaining array positions (remaining sub-problems)
        for(int row = 1; row < len; row++) {
            for(int column = 1; column <= target; column++) {

                boolean include = false, exclude;
                if(column >= arr[row]) {
                    include = dp[row - 1][column - arr[row]];
                }

                exclude = dp[row - 1][column];
                dp[row][column] = include || exclude;
            }
        }
        return dp[len - 1][target];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int total = 0;

        for(int a : arr) {
            total += a;
        }

        System.out.println("Result: "+canPartitionRecursive(arr, total / 2, 0));
        System.out.println("Result: "+canPartitionRecursiveWithMemoization(arr, total / 2, 0));
        System.out.println("Result: "+canPartitionDP(arr));

    }
}
