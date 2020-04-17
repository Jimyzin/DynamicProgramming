package com.jimy.coding.dp.grokking.fibonacci_numbers;

/**
 * Created by Jimy on 15-04-2020.
 * <p>
 * Problem Statement #
 * Given an array of positive numbers, where each element represents the max number of jumps that can be made forward from that element, write a program to find the minimum number of jumps needed to reach the end of the array (starting from the first element). If an element is 0, then we cannot move through that element.
 * <p>
 * Example 1:
 * <p>
 * Input = {2,1,1,1,4}
 * Output = 3
 * Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4
 * Example 2:
 * <p>
 * Input = {1,1,3,6,9,3,0,1,3}
 * Output = 4
 * Explanation: Starting from index '0', we can reach the last index through: 0->1->2->3->8
 */
public class MinimumJumps {

    /**
     * Approach 1: This is brute-force recursive approach. Time: O(2 ^ input.length), Space: O(input.length) -> recursion stack
     */
    static int countMinJumpsRecursive(int[] input, int currentIndex) {
        int len = input.length;

        // base checks
        if (currentIndex == len - 1) {
            return 0;
        }

        if (currentIndex == len) {
            return Integer.MAX_VALUE;
        }

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int stop = currentIndex + input[currentIndex];

        while (start < len && start <= stop) {
            // jump one step and recurse the remaining array
            int minJumps = countMinJumpsRecursive(input, start++);
            if (minJumps != Integer.MAX_VALUE) {
                totalJumps = Math.min(totalJumps, minJumps + 1);
            }
        }

        return totalJumps;
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization. Time: O(input.length ^ 2), Space: O(input.length)
     */
    static int countMinJumpsRecursiveWithMemoization(int[] dp, int[] input, int currentIndex) {
        int len = input.length;

        // base checks
        if (currentIndex == len - 1) {
            return 0;
        }

        // If an element is 0, then we cannot move through that element
        if (input[currentIndex] == 0)
            return Integer.MAX_VALUE;

        if (currentIndex == len) {
            return Integer.MAX_VALUE;
        }

        if (dp[currentIndex] == 0) {
            int totalJumps = Integer.MAX_VALUE;
            int start = currentIndex + 1;
            int stop = currentIndex + input[currentIndex];

            while (start < len && start <= stop) {
                // jump one step and recurse the remaining array
                int minJumps = countMinJumpsRecursiveWithMemoization(dp, input, start++);
                if (minJumps != Integer.MAX_VALUE) {
                    totalJumps = Math.min(totalJumps, minJumps + 1);
                }
            }
            dp[currentIndex] = totalJumps;
            //DPUtils.print1DArray(dp, len);
        }

        return dp[currentIndex];
    }

    static int countMinJumpsRecursiveWithMemoization(int[] input) {
        int[] dp = new int[input.length];
        return countMinJumpsRecursiveWithMemoization(dp, input, 0);
    }

    /**
     * Approach 3: This is top-down dynamic programming approach. Time: O(2 ^ input.length), Space: O(input.length)
     */
    static int countMinJumpsDP(int[] input) {

        int len = input.length;
        int[] dp = new int[len];

        // initialize with infinity, except the first index which should be zero as we start from there
        for (int a = 1; a < len; a++)
            dp[a] = Integer.MAX_VALUE;

        for (int start = 0; start < len - 1; start++) {
            for (int end = start + 1; end <= start + input[start] && end < len; end++) {
                dp[end] = Math.min(dp[end], dp[start] + 1);
            }
        }

        return dp[len - 1];
    }

    public static void main(String[] args) {
        int[] input1 = {2, 1, 1, 1, 4};
        System.out.println("--------------------{2, 1, 1, 1, 4}--------------------");
        System.out.println("Result (countMinJumpsRecursive): " + countMinJumpsRecursive(input1, 0));
        System.out.println("Result (countMinJumpsRecursiveWithMemoization): " + countMinJumpsRecursiveWithMemoization(input1));
        System.out.println("Result (countMinJumpsDP): " + countMinJumpsDP(input1));

        int[] input2 = {1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println("--------------------{1, 1, 3, 6, 9, 3, 0, 1, 3}--------------------");
        System.out.println("Result (countMinJumpsRecursive): " + countMinJumpsRecursive(input2, 0));
        System.out.println("Result (countMinJumpsRecursiveWithMemoization): " + countMinJumpsRecursiveWithMemoization(input2));
        System.out.println("Result (countMinJumpsDP): " + countMinJumpsDP(input2));
    }
}
