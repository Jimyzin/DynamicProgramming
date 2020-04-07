package com.jimy.coding.dp.grokking.zero_one_knapsack;

/**
 * Created by Jimy on 05-04-2020.
 * <p>
 * Given a set of positive numbers (non zero) and a target sum ‘S’. Each number should be assigned
 * either a ‘+’ or ‘-’ sign. We need to find out total ways to assign symbols to make the sum of numbers equal to target ‘S’.
 * <p>
 * Example 1: #
 * Input: {1, 1, 2, 3}, S=1
 * Output: 3
 * Explanation: The given set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}
 * Example 2: #
 * Input: {1, 2, 7, 1}, S=9
 * Output: 2
 * Explanation: The given set has '2' ways to make a sum of '9': {+1+2+7-1} & {-1+2+7+1}
 */
public class TargetSum {

/**
 * -----------------------------------------------------------------------------------------------------------------------------------------
 * Solution Theory:

 * This equation can be reduced to the subset sum problem. Let’s assume that ‘Sum(num)’ denotes the total sum of all the numbers, therefore:
 *
 * Sum(s1) + Sum(s2) = Sum(num)
 * Let’s add the above two equations:
 * => Sum(s1) - Sum(s2) + Sum(s1) + Sum(s2) = S + Sum(num)
 * => 2 * Sum(s1) =  S + Sum(num)
 * => Sum(s1) = (S + Sum(num)) / 2
 *
 * This essentially converts our problem to: “Find count of subsets of the given numbers whose sum is equal to”,
 * => (S + Sum(num)) / 2
 * ------------------------------------------------------------------------------------------------------------------------------------------
 */

    /**
     * Approach 1: This is top-down recursive approach. This has time complexity of
     * O(2 ^ array_length) and space complexity of O(array_length) to hold recursion stack.
     */
    static int countTargetSumRecursive(int[] arr, int target, int index, int count) {

        // base condition
        if (target == 0 && index == arr.length) {
            return count + 1;
        }

        if (index == arr.length) {
            return count;
        }

        // calculating remaining sub-problems
        int positive = countTargetSumRecursive(arr, target - arr[index], index + 1, count);
        int negative = countTargetSumRecursive(arr, target + arr[index], index + 1, count);

        return positive + negative;
    }

    /**
     * Approach 2: This is bottom-up dynamic programming approach. This has time complexity of
     * O(array_length x target) and space complexity of O(array_length x target)
     */
    static int countTargetSumDP(int[] arr, int target) {

        int len = arr.length, total = 0;
        for (int a : arr)
            total += a;

        // base condition
        if (total < target || (total + target) % 2 == 1) {
            return 0;
        }

        return countTargetSum(arr, (total + target) / 2);
    }

    /**
     * Approach 3: This is bottom-up dynamic programming approach with optimised space. This has time complexity of
     * O(array_length x target) and space complexity of O(target)
     */
    static int countTargetSumDPSpaceOptimised(int[] arr, int target) {

        int len = arr.length, total = 0;
        for (int a : arr)
            total += a;

        // base condition
        if (total < target || (total + target) % 2 == 1) {
            return 0;
        }

        return countTargetSumSpaceOptimisedApproach(arr, (total + target) / 2);
    }

    private static int countTargetSum(int[] arr, int target) {
        int len = arr.length;

        int[][] dp = new int[len][target + 1];

        // populate the target = 0 column, as we will always have an empty set for zero sum
        for (int row = 0; row < len; row++) {
            dp[row][0] = 1;
        }

        // with only one number, we can form a subset only when the required sum is equal to the number
        for (int column = 1; column <= target; column++) {
            if (arr[0] == column)
                dp[0][column] = 1;
        }

        // populating remaining sub-problems
        for (int row = 1; row < len; row++) {
            for (int column = 1; column <= target; column++) {

                dp[row][column] = dp[row - 1][column];
                if (arr[row] <= column) {
                    dp[row][column] += dp[row - 1][column - arr[row]];
                }
            }
        }
        // DPUtils.print2DArray(dp, len, target + 1);
        return dp[len - 1][target];
    }

    private static int countTargetSumSpaceOptimisedApproach(int[] arr, int target) {
        int len = arr.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        // with only one number, we can form a subset only when the required sum is equal to the number
        for (int tgt = 1; tgt <= target; tgt++) {
            if (arr[0] == tgt)
                dp[tgt] = 1;
        }

        // populating remaining sub-problems
        for (int index = 1; index < len; index++) {
            for (int tgt = target; tgt > 0; tgt--) {

                if (arr[index] <= tgt) {
                    dp[tgt] += dp[tgt - arr[index]];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 3};
        System.out.println("Result (countTargetSumRecursive): " + countTargetSumRecursive(arr1, 1, 0, 0));
        System.out.println("Result (countTargetSumDP): " + countTargetSumDP(arr1, 1));
        System.out.println("Result (countTargetSumDPSpaceOptimised): " + countTargetSumDPSpaceOptimised(arr1, 1));

        int[] arr2 = {1, 2, 7, 1};
        System.out.println("Result (countTargetSumRecursive): " + countTargetSumRecursive(arr2, 9, 0, 0));
        System.out.println("Result (countTargetSumDP): " + countTargetSumDP(arr2, 9));
        System.out.println("Result (countTargetSumDPSpaceOptimised): " + countTargetSumDPSpaceOptimised(arr2, 9));
    }
}
