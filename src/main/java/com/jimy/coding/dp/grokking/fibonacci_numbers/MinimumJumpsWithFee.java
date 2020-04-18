package com.jimy.coding.dp.grokking.fibonacci_numbers;

/**
 * Created by Jimy on 18-04-2020.
 * <p>
 * Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay if you take the step.
 * Implement a method to calculate the minimum fee required to reach the top of the staircase (beyond the top-most step).
 * At every step, you have an option to take either 1 step, 2 steps, or 3 steps. You should assume that you are standing at the first step.
 * <p>
 * Example 1:
 * <p>
 * Number of stairs (n) : 6
 * Fee: {1,2,5,2,1,2}
 * Output: 3
 * Explanation: Starting from index '0', we can reach the top through: 0->3->top
 * The total fee we have to pay will be (1+2).
 * Example 2:
 * <p>
 * Number of stairs (n): 4
 * Fee: {2,3,4,5}
 * Output: 5
 * Explanation: Starting from index '0', we can reach the top through: 0->1->top
 * The total fee we have to pay will be (2+3).
 */
public class MinimumJumpsWithFee {

    /**
     * Approach 1: This is brute-force recursive solution. Time: O(3 ^ stairFees.length) where 3 is the choice of levels (1, 2, 3) &
     * Space: O(stairFees.length) -> recursion stack.
     */
    static int countMinJumpsRecursive(int[] stairFees, int currentStaircaseLevel) {

        // base checks -> currentStaircaseLevel >= stairFees.length indicates all stairs have been covered.
        if (currentStaircaseLevel >= stairFees.length) {
            return 0;
        }

        int minFees = Integer.MAX_VALUE; // infinite value

        // As there is an option to take either 1 step or 2 steps or 3 steps, step varies from 1 to 3.
        for (int step = 1; step <= 3; step++) {
            // calculating current step fees from the currentStaircaseLevel
            int stepFees = stairFees[currentStaircaseLevel] + countMinJumpsRecursive(stairFees, currentStaircaseLevel + step);

            // calculating minimum fees for the current step at the currentStaircaseLevel
            minFees = Math.min(minFees, stepFees);
        }
        return minFees;
    }

    /**
     * Approach 2: This is bottom-up dynamic programming solution through recursion with memoization.
     * Time: O(3 ^ stairFees.length) where 3 is the choice of levels (1, 2, 3) & Space: O(stairFees.length)
     */
    private static int countMinJumpsRecursiveWithMemoization(int[] dp, int[] stairFees, int currentStaircaseLevel) {

        // base checks -> currentStaircaseLevel >= stairFees.length indicates all stairs have been covered.
        if (currentStaircaseLevel >= stairFees.length) {
            return 0;
        }

        int minFees = Integer.MAX_VALUE; // infinite value

        if (dp[currentStaircaseLevel] == 0) {
            // As there is an option to take either 1 step or 2 steps or 3 steps, step varies from 1 to 3.
            for (int step = 1; step <= 3; step++) {
                // calculating current step fees from the currentStaircaseLevel
                int stepFees = stairFees[currentStaircaseLevel] + countMinJumpsRecursiveWithMemoization(dp, stairFees, currentStaircaseLevel + step);

                // calculating minimum fees for the current step at the currentStaircaseLevel
                minFees = Math.min(minFees, stepFees);
            }
            dp[currentStaircaseLevel] = minFees;
        }
        //DPUtils.print1DArray(dp, dp.length);
        return dp[currentStaircaseLevel];
    }

    static int countMinJumpsRecursiveWithMemoization(int[] stairFees) {
        int[] dp = new int[stairFees.length];
        return countMinJumpsRecursiveWithMemoization(dp, stairFees, 0);
    }

    /**
     * Approach 3: This is top-down pure dynamic programming solution. Time: O(stairFees.length) & Space: O(stairFees.length)
     */
    static int countMinJumpsDP(int[] stairFees) {

        int len = stairFees.length;
        int[] dp = new int[len + 1]; // +1 to handle 0th step

        // populating all dp elements to infinity except 0th index corresponding to 0th step
        for (int index = 1; index <= len; index++) {
            dp[index] = Integer.MAX_VALUE;
        }

        for (int index = 1; index <= len; index++) {

            // (index - step) condition is to handle first 3 steps in the staircase; to prevent encountering ArrayIndexOutOfBoundsException
            for (int step = 1; step <= 3 && (index - step) >= 0; step++) {
                dp[index] = Math.min(dp[index], dp[index - step] + stairFees[index - step]);
            }
            //DPUtils.print1DArray(dp, len + 1);
        }

        return dp[len];
    }

    public static void main(String[] args) {

        System.out.println("----------------------{2, 3, 4, 5}--------------------");
        int[] stairFees1 = {2, 3, 4, 5};
        System.out.println("Result (countMinJumpsRecursive): " + countMinJumpsRecursive(stairFees1, 0));
        System.out.println("Result (countMinJumpsRecursiveWithMemoization): " + countMinJumpsRecursiveWithMemoization(stairFees1));
        System.out.println("Result (countMinJumpsDP): " + countMinJumpsDP(stairFees1));
        System.out.println();

        System.out.println("----------------------{1, 2, 5, 2, 1, 2}--------------------");
        int[] stairFees2 = {1, 2, 5, 2, 1, 2};
        System.out.println("Result (countMinJumpsRecursive): " + countMinJumpsRecursive(stairFees2, 0));
        System.out.println("Result (countMinJumpsRecursiveWithMemoization): " + countMinJumpsRecursiveWithMemoization(stairFees2));
        System.out.println("Result (countMinJumpsDP): " + countMinJumpsDP(stairFees2));
        System.out.println();
    }
}