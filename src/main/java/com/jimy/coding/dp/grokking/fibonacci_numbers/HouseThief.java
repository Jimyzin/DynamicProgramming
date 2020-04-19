package com.jimy.coding.dp.grokking.fibonacci_numbers;

/**
 * Created by Jimy on 19-04-2020.
 * <p>
 * There are ‘n’ houses built in a line. A thief wants to steal maximum possible money from these houses.
 * The only restriction the thief has is that he can’t steal from two consecutive houses, as that would
 * alert the security system. How should the thief maximize his stealing?
 * <p>
 * Problem Statement #
 * Given a number array representing the wealth of ‘n’ houses, determine the maximum amount of money the
 * thief can steal without alerting the security system.
 * <p>
 * Example 1:
 * <p>
 * Input: {2, 5, 1, 3, 6, 2, 4}
 * Output: 15
 * Explanation: The thief should steal from houses 5 + 6 + 4
 * Example 2:
 * <p>
 * Input: {2, 10, 14, 8, 1}
 * Output: 18
 * Explanation: The thief should steal from houses 10 + 8
 */
public class HouseThief {

    /**
     * Approach 1: This is brute-force recursive solution. Time: O(2 ^ wealth.length), Space: O(wealth.length) -> recursion stack.
     */
    static int findMaxStealRecursive(int[] wealth, int currentIndex) {

        // base check
        if (currentIndex >= wealth.length) {
            return 0;
        }

        // Steal from the current house and skip one to steal from the next.
        int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(wealth, currentIndex + 2);

        // Skip the current house and steal from the adjacent house.
        int stealAdjacent = findMaxStealRecursive(wealth, currentIndex + 1);

        return Math.max(stealCurrent, stealAdjacent);
    }

    /**
     * Approach 2: This is bottom-up dynamic programming solution through recursion with memoization.
     * Time: O(wealth.length), Space: O(wealth.length)
     */
    private static int findMaxStealRecursiveWithMemoization(int[] dp, int[] wealth, int currentIndex) {

        // base check
        if (currentIndex >= wealth.length) {
            return 0;
        }

        if (dp[currentIndex] == 0) {
            // Steal from the current house and skip one to steal from the next.
            int stealCurrent = wealth[currentIndex] + findMaxStealRecursiveWithMemoization(dp, wealth, currentIndex + 2);

            // Skip the current house and steal from the adjacent house.
            int stealAdjacent = findMaxStealRecursiveWithMemoization(dp, wealth, currentIndex + 1);
            dp[currentIndex] = Math.max(stealCurrent, stealAdjacent);
        }

        return dp[currentIndex];
    }

    static int findMaxStealRecursiveWithMemoization(int[] wealth) {
        int[] dp = new int[wealth.length];
        return findMaxStealRecursiveWithMemoization(dp, wealth, 0);
    }

    /**
     * Approach 3: This is top-down pure dynamic programming solution. Time: O(wealth.length), Space: O(wealth.length)
     */
    static int findMaxStealDP(int[] wealth) {

        int len = wealth.length;
        int[] dp = new int[len];
        dp[0] = wealth[0]; // max steal till 0th index = wealth[0]
        dp[1] = wealth[1]; // max steal till 1st index = wealth[1] because adjacent value cannot be included

        for (int currentIndex = 2; currentIndex < len; currentIndex++) {
            // max steal at current index = either (max steal at previous index) or (wealth[currentIndex] + max steal at previous to previous index)
            dp[currentIndex] = Math.max(dp[currentIndex - 1], wealth[currentIndex] + dp[currentIndex - 2]);
        }

        return dp[len - 1];
    }

    /**
     * Approach 4: This is top-down pure dynamic programming solution with space optimised. Time: O(wealth.length), Space: O(1)
     */
    static int findMaxStealDPSpaceOptimised(int[] wealth) {

        int len = wealth.length;
        int first = wealth[0]; // max steal till 0th index = wealth[0]
        int second = wealth[1]; // max steal till 1st index = wealth[1] because adjacent value cannot be included
        int third = 0;

        for (int currentIndex = 2; currentIndex < len; currentIndex++) {
            // max steal at current index = either (max steal at previous index) or (wealth[currentIndex] + max steal at previous to previous index)
            third = Math.max(second, wealth[currentIndex] + first);
            first = second;
            second = third;
        }

        return third;
    }

    public static void main(String[] args) {
        System.out.println("---------------------{2, 10, 14, 8, 1}---------------------");
        int[] wealth1 = {2, 10, 14, 8, 1};
        System.out.println("Result (findMaxStealRecursive): " + findMaxStealRecursive(wealth1, 0));
        System.out.println("Result (findMaxStealRecursiveWithMemoization): " + findMaxStealRecursiveWithMemoization(wealth1));
        System.out.println("Result (findMaxStealDP): " + findMaxStealDP(wealth1));
        System.out.println("Result (findMaxStealDPSpaceOptimised): " + findMaxStealDPSpaceOptimised(wealth1));
        System.out.println();

        System.out.println("---------------------{2, 5, 1, 3, 6, 2, 4}---------------------");
        int[] wealth2 = {2, 5, 1, 3, 6, 2, 4};
        System.out.println("Result (findMaxStealRecursive): " + findMaxStealRecursive(wealth2, 0));
        System.out.println("Result (findMaxStealRecursiveWithMemoization): " + findMaxStealRecursiveWithMemoization(wealth2));
        System.out.println("Result (findMaxStealDP): " + findMaxStealDP(wealth2));
        System.out.println("Result (findMaxStealDPSpaceOptimised): " + findMaxStealDPSpaceOptimised(wealth2));

    }
}
