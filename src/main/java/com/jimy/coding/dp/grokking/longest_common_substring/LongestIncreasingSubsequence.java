package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 30-04-2020.
 * <p>
 * Given a number sequence, find the length of its Longest Increasing Subsequence (LIS). In an increasing subsequence, all the elements are in increasing order (from lowest to highest).
 * <p>
 * Example 1:
 * <p>
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LIS is {2,3,6,10,12}.
 * Example 1:
 * <p>
 * Input: {-4,10,3,7,15}
 * Output: 4
 * Explanation: The LIS is {-4,3,7,15}.
 */
public class LongestIncreasingSubsequence {

    /**
     * Approach 1: This is brute-force top-down recursive approach. Time: O(2 ^ arr.length), Space: O(n) -> recursion stack
     */
    static int findLISRecursive(int[] arr, int currentIndex, int previousIndex) {

        int len = arr.length;

        // base condition
        if (currentIndex == len) {
            return 0;
        }

        int c1 = 0;
        // including arr[currentIndex] if it is greater than the previously included number
        if (previousIndex == -1 || arr[previousIndex] < arr[currentIndex]) {
            c1 = 1 + findLISRecursive(arr, currentIndex + 1, currentIndex);
        }

        // excluding the number at currentIndex
        int c2 = findLISRecursive(arr, currentIndex + 1, previousIndex);
        return Math.max(c1, c2);

    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization. Time: O(arr.length ^ 2), Space: O(n ^ 2)
     */
    private static int findLISRecursiveWithMemoization(Integer[][] dp, int[] arr, int currentIndex, int previousIndex) {

        int len = arr.length;
        // base condition
        if (currentIndex == len) {
            return 0;
        }

        if (dp[currentIndex][previousIndex + 1] == null) {

            int c1 = 0;
            // including arr[currentIndex] if it is greater than the previously included number
            if (previousIndex == -1 || arr[currentIndex] > arr[previousIndex]) {
                c1 = 1 + findLISRecursiveWithMemoization(dp, arr, currentIndex + 1, currentIndex);
            }

            // excluding the number at currentIndex
            int c2 = findLISRecursiveWithMemoization(dp, arr, currentIndex + 1, previousIndex);
            dp[currentIndex][previousIndex + 1] = Math.max(c1, c2);
        }
        return dp[currentIndex][previousIndex + 1];
    }

    /**
     * Approach 3: This is bottom-up dynamic programming. Time: O(arr.length ^ 2), Space: O(n ^ 2)
     */
    static int findLISRecursiveWithMemoization(int[] arr) {
        Integer[][] dp = new Integer[arr.length][arr.length];
        return findLISRecursiveWithMemoization(dp, arr, 0, -1);
    }

    static int findLISDP(int[] arr) {
        int len = arr.length, maxLength = 1;
        int[] dp = new int[len];
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {

                if (arr[i] > arr[j] && dp[j] >= dp[i]) {
                    dp[i] += 1;
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("---------------------- 4, 2, 3, 6, 10, 1, 12 -----------------------");
        System.out.println("Result (findLISRecursive): " + findLISRecursive(new int[]{4, 2, 3, 6, 10, 1, 12}, 0, -1));
        System.out.println("Result (findLISRecursiveWithMemoization): " + findLISRecursiveWithMemoization(new int[]{4, 2, 3, 6, 10, 1, 12}));
        System.out.println("Result (findLISDP): " + findLISDP(new int[]{4, 2, 3, 6, 10, 1, 12}));
        System.out.println();

        System.out.println("---------------------- -4, 10, 3, 7, 15 -----------------------");
        System.out.println("Result (findLISRecursive): " + findLISRecursive(new int[]{-4, 10, 3, 7, 15}, 0, -1));
        System.out.println("Result (findLISRecursiveWithMemoization): " + findLISRecursiveWithMemoization(new int[]{-4, 10, 3, 7, 15}));
        System.out.println("Result (findLISDP): " + findLISDP(new int[]{-4, 10, 3, 7, 15}));
    }
}
