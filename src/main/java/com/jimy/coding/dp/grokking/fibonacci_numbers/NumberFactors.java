package com.jimy.coding.dp.grokking.fibonacci_numbers;

/**
 * Created by Jimy on 13-04-2020.
 * <p>
 * Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’ as the sum of 1, 3, or 4.
 * <p>
 * Example 1:
 * <p>
 * n : 4
 * Number of ways = 4
 * Explanation: Following are the four ways we can exoress 'n' : {1,1,1,1}, {1,3}, {3,1}, {4}
 * Example 2:
 * <p>
 * n : 5
 * Number of ways = 6
 * Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1}, {1,1,3}, {1,3,1}, {3,1,1},
 * {1,4}, {4,1}
 */
public class NumberFactors {

    /**
     * Approach 1: This is brute-force, recursive approach. Time: O(3 ^ n), Space: O(n) ->recursive stack
     */
    static int calculateNumberFactorsRecursive(int n) {

        // base checks
        if (n == 0) {
            return 1; // base condition; we don't need to subtract anything, so there's only one way
        }

        if (n == 1) {
            return 1; // we can subtract 1 to be left with zero, so there's only one way
        }

        if (n == 2) {
            return 1; // we can subtract 1 twice to be left with zero, so there's only one way
        }

        if (n == 3) {
            return 2; // 3 -> (1, 1, 1), (3)
        }

        int take1Step = calculateNumberFactorsRecursive(n - 1);
        int take3Steps = calculateNumberFactorsRecursive(n - 3);
        int take4Steps = calculateNumberFactorsRecursive(n - 4);

        return take1Step + take3Steps + take4Steps;
    }

    private static int calculateNumberFactorsRecursiveWithMemoization(int[] dp, int n) {

        // base checks
        if (n == 0) {
            return 1; // base condition; we don't need to subtract anything, so there's only one way
        }

        if (n == 1) {
            return 1; // we can subtract 1 to be left with zero, so there's only one way
        }

        if (n == 2) {
            return 1; // we can subtract 1 twice to be left with zero, so there's only one way
        }

        if (n == 3) {
            return 2; // 3 -> (1, 1, 1), (3)
        }

        if (dp[n] == 0) {
            int take1Step = calculateNumberFactorsRecursiveWithMemoization(dp, n - 1);
            int take3Steps = calculateNumberFactorsRecursiveWithMemoization(dp, n - 3);
            int take4Steps = calculateNumberFactorsRecursiveWithMemoization(dp, n - 4);
            dp[n] = take1Step + take3Steps + take4Steps;
        }

        return dp[n];
    }

    /**
     * Approach 2: This is brute-force, recursive approach with memoization. Time: O(n), Space: O(n)
     */
    static int calculateNumberFactorsRecursiveWithMemoization(int n) {
        int[] dp = new int[n + 1];
        return calculateNumberFactorsRecursiveWithMemoization(dp, n);
    }

    /**
     * Approach 3: This is dynamic programming. Time: O(n), Space: O(n)
     */
    static int calculateNumberFactorsDP(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        for (int num = 4; num <= n; num++) {
            dp[num] = dp[num - 1] + dp[num - 3] + dp[num - 4];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("---------------------Number = 4 ----------------------");
        System.out.println("Result (calculateNumberFactorsRecursive): " + calculateNumberFactorsRecursive(4));
        System.out.println("Result (calculateNumberFactorsRecursiveWithMemoization): " + calculateNumberFactorsRecursiveWithMemoization(4));
        System.out.println("Result (calculateNumberFactorsDP): " + calculateNumberFactorsDP(4));

        System.out.println("---------------------Number = 5 ----------------------");
        System.out.println("Result (calculateNumberFactorsRecursive): " + calculateNumberFactorsRecursive(5));
        System.out.println("Result (calculateNumberFactorsRecursiveWithMemoization): " + calculateNumberFactorsRecursiveWithMemoization(5));
        System.out.println("Result (calculateNumberFactorsDP): " + calculateNumberFactorsDP(5));

        System.out.println("---------------------Number = 6 ----------------------");
        System.out.println("Result (calculateNumberFactorsRecursive): " + calculateNumberFactorsRecursive(6));
        System.out.println("Result (calculateNumberFactorsRecursiveWithMemoization): " + calculateNumberFactorsRecursiveWithMemoization(6));
        System.out.println("Result (calculateNumberFactorsDP): " + calculateNumberFactorsDP(6));
    }
}
