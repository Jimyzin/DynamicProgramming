package com.jimy.coding.dp.grokking.fibonacci_numbers;

/**
 * Created by Jimy on 12-04-2020.
 * <p>
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 * <p>
 * Example 1:
 * <p>
 * Number of stairs (n) : 3
 * Number of ways = 4
 * Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3}
 * Example 2:
 * <p>
 * Number of stairs (n) : 4
 * Number of ways = 7
 * Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1},
 * {2,2}, {1,3}, {3,1}
 */
public class Staircase {

    /**
     * Approach 1: This is brute-force, top-down and recursive approach. Time: O(3^stairs), Space: O(stairs) -->recursion stack.
     */
    static int countStaircaseRecursive(int stairs) {

        // base checks
        if (stairs == 0) {
            return 1;
        }

        if (stairs == 1) {
            return 1;
        }

        if (stairs == 2) {
            return 2;
        }

        // calculate remaining sub-problems
        int take1Step = countStaircaseRecursive(stairs - 1);
        int take2Step = countStaircaseRecursive(stairs - 2);
        int take3Step = countStaircaseRecursive(stairs - 3);

        return take1Step + take2Step + take3Step;
    }

    /**
     * Approach 2: This is brute-force, top-down and recursive approach with memoization. Time: O(stairs), Space: O(stairs).
     */
    static int countStaircaseRecursiveWithMemoization(Integer[] dp, int stairs) {

        // base checks
        if (stairs == 0) {
            return 1;
        }

        if (stairs == 1) {
            return 1;
        }

        if (stairs == 2) {
            return 2;
        }

        // calculate remaining sub-problems
        if (dp[stairs] == null) {
            int take1Step = countStaircaseRecursive(stairs - 1);
            int take2Step = countStaircaseRecursive(stairs - 2);
            int take3Step = countStaircaseRecursive(stairs - 3);
            dp[stairs] = take1Step + take2Step + take3Step;
        }

        return dp[stairs];
    }

    static int countStaircaseRecursiveWithMemoization(int stairs) {
        Integer[] dp = new Integer[stairs + 1];
        return countStaircaseRecursiveWithMemoization(dp, stairs);
    }

    /**
     * Approach 3: This is bottom-up dynamic programming. Time: O(stairs), Space: O(stairs).
     */
    static int countStaircaseDP(int stairs) {
        int[] dp = new int[stairs + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int level = 3; level <= stairs; level++) {
            dp[level] = dp[level - 1] + dp[level - 2] + dp[level - 3];
        }

        return dp[stairs];
    }

    /**
     * Approach 4: This is bottom-up dynamic programming. Time: O(stairs), Space: O(1).
     */
    static int countStaircaseDPSpaceOptimised(int stairs) {
        int first = 1, second = 1, third = 2, next = 0;

        for (int level = 3; level <= stairs; level++) {
            next = first + second + third;
            first = second;
            second = third;
            third = next;
        }

        return next;
    }

    public static void main(String[] args) {
        System.out.println("Number of stairs: 3");
        System.out.println("Number of ways (countStaircaseRecursive): " + countStaircaseRecursive(3));
        System.out.println("Number of ways (countStaircaseRecursiveWithMemoization): " + countStaircaseRecursiveWithMemoization(3));
        System.out.println("Number of ways (countStaircaseDP): " + countStaircaseDP(3));
        System.out.println("Number of ways (countStaircaseDPSpaceOptimised): " + countStaircaseDPSpaceOptimised(3));
        System.out.println("---------------------------------------------------------------------------------------");

        System.out.println("Number of stairs: 4");
        System.out.println("Number of ways (countStaircaseRecursive): " + countStaircaseRecursive(4));
        System.out.println("Number of ways (countStaircaseRecursiveWithMemoization): " + countStaircaseRecursiveWithMemoization(4));
        System.out.println("Number of ways (countStaircaseDP): " + countStaircaseDP(4));
        System.out.println("Number of ways (countStaircaseDPSpaceOptimised): " + countStaircaseDPSpaceOptimised(4));
        System.out.println("---------------------------------------------------------------------------------------");

        System.out.println("Number of stairs: 5");
        System.out.println("Number of ways (countStaircaseRecursive): " + countStaircaseRecursive(5));
        System.out.println("Number of ways (countStaircaseRecursiveWithMemoization): " + countStaircaseRecursiveWithMemoization(5));
        System.out.println("Number of ways (countStaircaseDP): " + countStaircaseDP(5));
        System.out.println("Number of ways (countStaircaseDPSpaceOptimised): " + countStaircaseDPSpaceOptimised(5));
        System.out.println("---------------------------------------------------------------------------------------");
    }
}