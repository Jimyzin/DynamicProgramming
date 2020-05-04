package com.jimy.coding.dp.grokking.longest_common_substring;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 04-05-2020.
 */
public class MaximumSumIncreasingSubsequence {

    /**
     * Approach 1: This is brute force top down recursive approach. Time: O(2 ^ nums.length), Space: O(nums.length) -> recursion stack
     */
    static int calculateMaxSumIncreasingSubsequenceRecursive(int[] nums, int currentIndex, int prevIndex) {

        // base conditions
        if (currentIndex == nums.length) {
            return 0;
        }

        // include the element at currentIndex in sum if it is greater the element at prevIndex
        int c1 = 0;
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            c1 = nums[currentIndex] + calculateMaxSumIncreasingSubsequenceRecursive(nums, currentIndex + 1, currentIndex);
        }

        // exclude the element at currentIndex in sum
        int c2 = calculateMaxSumIncreasingSubsequenceRecursive(nums, currentIndex + 1, prevIndex);
        return Math.max(c1, c2);
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization. Time: O(nums.length ^ 2), Space: O(nums.length ^ 2)
     */
    private static int calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(Integer[][] dp, int[] nums, int currentIndex, int prevIndex) {

        // base conditions
        if (currentIndex == nums.length) {
            return 0;
        }

        if (dp[currentIndex][prevIndex + 1] == null) {
            // include the element at currentIndex in sum if it is greater the element at prevIndex
            int c1 = 0;
            if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                c1 = nums[currentIndex] + calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(dp, nums, currentIndex + 1, currentIndex);
            }

            // exclude the element at currentIndex in sum
            int c2 = calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(dp, nums, currentIndex + 1, prevIndex);
            dp[currentIndex][prevIndex + 1] = Math.max(c1, c2);
        }
        //DPUtils.print2DArray(dp, nums.length, nums.length);
        return dp[currentIndex][prevIndex + 1];
    }

    static int calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(int[] nums) {
        int len = nums.length;
        Integer[][] dp = new Integer[len][len];
        return calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(dp, nums, 0, -1);
    }

    /**
     * Approach 3: This is bottom-up dynamic programming approach. Time: O(nums.length ^ 2), Space: O(nums.length ^ 2)
     */
    static int calculateMaxSumIncreasingSubsequenceDP(int[] nums) {
        int len = nums.length, maxSum = nums[0];
        int[] dp = new int[len];

        for (int a = 0; a < len; a++)
            dp[a] = nums[a];

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + nums[i]) {
                    dp[i] = dp[j] + nums[i];
                }
            }
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("-----------------------{-4, 10, 3, 7, 15}-----------------------");
        System.out.println("Result (calculateMaxSumIncreasingSubsequenceRecursive): "+calculateMaxSumIncreasingSubsequenceRecursive(new int[]{-4, 10, 3, 7, 15}, 0, -1));
        System.out.println("Result (calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization): "+calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(new int[]{-4, 10, 3, 7, 15}));
        System.out.println("Result (calculateMaxSumIncreasingSubsequenceDP): "+calculateMaxSumIncreasingSubsequenceDP(new int[]{-4, 10, 3, 7, 15}));
        System.out.println();

        System.out.println("-----------------------{4, 1, 2, 6, 10, 1, 12}-----------------------");
        System.out.println("Result (calculateMaxSumIncreasingSubsequenceRecursive): "+calculateMaxSumIncreasingSubsequenceRecursive(new int[]{4, 1, 2, 6, 10, 1, 12}, 0, -1));
        System.out.println("Result (calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization): "+calculateMaxSumIncreasingSubsequenceRecursiveWithMemoization(new int[]{4, 1, 2, 6, 10, 1, 12}));
        System.out.println("Result (calculateMaxSumIncreasingSubsequenceDP): "+calculateMaxSumIncreasingSubsequenceDP(new int[]{4, 1, 2, 6, 10, 1, 12}));
    }
}
