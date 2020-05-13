package com.jimy.coding.dp.grokking.longest_common_substring;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 13-05-2020.
 */
public class LongestAlternatingSubsequence {

    /**
     * Approach 1: This is brute-force, top-down recursive approach. Time: O(2 ^ nums.length), Space: O(nums.length) ->recursion stack.
     */
    private static int calculateLASRecursive(int[] nums, int previousIndex, int currentIndex, boolean isIncreasingThenDecreasing) {

        //base conditions
        if (currentIndex == nums.length) {
            return 0;
        }

        int c1 = 0;

        if(isIncreasingThenDecreasing) {

            if (previousIndex == -1 || (nums[previousIndex] > nums[currentIndex])) {
                c1 = 1 + calculateLASRecursive(nums, currentIndex, currentIndex + 1, !isIncreasingThenDecreasing);
            }

        } else {
            if (previousIndex == -1 || (nums[previousIndex] < nums[currentIndex])) {
                c1 = 1 + calculateLASRecursive(nums, currentIndex, currentIndex + 1, !isIncreasingThenDecreasing);
            }
        }

        int c2 = calculateLASRecursive(nums, previousIndex, currentIndex + 1, isIncreasingThenDecreasing);
        return Math.max(c1, c2);
    }

    static int calculateLASRecursive(int[] nums) {

        // Maximum length for a1 < a2 > a3 pattern
        int length1 = calculateLASRecursive(nums, -1, 0, true);

        // Maximum length for a1 > a2 < a3 pattern
        int length2 = calculateLASRecursive(nums, -1, 0, false);

        return Math.max(length1, length2);
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization. Time: O(nums.length ^ 2), Space: O(nums.length ^ 2).
     */
    private static int calculateLASRecursiveWithMemoization(Integer[][] dp, int[] nums, int previousIndex, int currentIndex, boolean isIncreasingThenDecreasing) {

        //base conditions
        if (currentIndex == nums.length) {
            return 0;
        }

        if (dp[previousIndex + 1][currentIndex] == null) {
            int c1 = 0;
            if (isIncreasingThenDecreasing) {

                if (previousIndex == -1 || (nums[previousIndex] > nums[currentIndex])) {
                    c1 = 1 + calculateLASRecursiveWithMemoization(dp, nums, currentIndex, currentIndex + 1, !isIncreasingThenDecreasing);
                }

            } else {
                if (previousIndex == -1 || (nums[previousIndex] < nums[currentIndex])) {
                    c1 = 1 + calculateLASRecursiveWithMemoization(dp, nums, currentIndex, currentIndex + 1, !isIncreasingThenDecreasing);
                }
            }

            int c2 = calculateLASRecursiveWithMemoization(dp, nums, previousIndex, currentIndex + 1, isIncreasingThenDecreasing);
            dp[previousIndex + 1][currentIndex] = Math.max(c1, c2);
        }
        return dp[previousIndex + 1][currentIndex];
    }

    static int calculateLASRecursiveWithMemoization(int[] nums) {

        int len = nums.length;
        Integer[][] dp = new Integer[len][len];

        // Maximum length for a1 < a2 > a3 pattern
        int length1 = calculateLASRecursiveWithMemoization(dp, nums, -1, 0, true);
        //DPUtils.print2DArray(dp, len, len);

        dp = new Integer[len][len];
        // Maximum length for a1 > a2 < a3 pattern
        int length2 = calculateLASRecursiveWithMemoization(dp, nums, -1, 0, false);
        //DPUtils.print2DArray(dp, len, len);
        return Math.max(length1, length2);
    }

    /**
     * Approach 3: This is bottom-up pure dynamic programming approach. Time: O(nums.length ^ 2), Space: O(nums.length).
     */
    static int calculateLASDP(int[] nums) {

        int len = nums.length, max = 0;
        int[][] dp = new int[2][len];

        for (int a = 0; a < len; a++)
            dp[0][a] = dp[1][a] = 1; // initialising LAS lengths to 1 as every single element is an LAS of length 1.

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {
                    // if nums[i] > nums[j], we will consider the LAS ending at j where the last 2 elements are in descending order
                    dp[1][i] = Math.max(dp[0][j] + 1, dp[1][i]);
                    max = Math.max(max, dp[1][i]);

                } else if (nums[i] < nums[j]) {
                    // if nums[i] < nums[j], we will consider the LAS ending at j where the last 2 elements are in ascending order.
                    dp[0][i] = Math.max(dp[1][j] + 1, dp[0][i]);
                    max = Math.max(max, dp[0][i]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("----------------------{1, 2, 3, 4}-------------------");
        System.out.println("Result (calculateLASRecursive): " + calculateLASRecursive(new int[]{1, 2, 3, 4}));
        System.out.println("Result (calculateLASRecursiveWithMemoization): " + calculateLASRecursiveWithMemoization(new int[]{1, 2, 3, 4}));
        System.out.println("Result (calculateLASDP): " + calculateLASDP(new int[]{1, 2, 3, 4}));
        System.out.println();

        System.out.println("----------------------{3, 2, 1, 4}-------------------");
        System.out.println("Result (calculateLASRecursive): " + calculateLASRecursive(new int[]{3, 2, 1, 4}));
        System.out.println("Result (calculateLASRecursiveWithMemoization): " + calculateLASRecursiveWithMemoization(new int[]{3, 2, 1, 4}));
        System.out.println("Result (calculateLASDP): " + calculateLASDP(new int[]{3, 2, 1, 4}));
        System.out.println();

        System.out.println("----------------------{1, 3, 2, 4}-------------------");
        System.out.println("Result (calculateLASRecursive): " + calculateLASRecursive(new int[]{1, 3, 2, 4}));
        System.out.println("Result (calculateLASRecursiveWithMemoization): " + calculateLASRecursiveWithMemoization(new int[]{1, 3, 2, 4}));
        System.out.println("Result (calculateLASDP): " + calculateLASDP(new int[]{1, 3, 2, 4}));
    }
}
