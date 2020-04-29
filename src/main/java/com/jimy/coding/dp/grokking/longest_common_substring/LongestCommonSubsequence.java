package com.jimy.coding.dp.grokking.longest_common_substring;

import java.util.HashMap;

/**
 * Created by Jimy on 29-04-2020.
 * <p>
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest subsequence which is common in both the strings.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "abdca"
 * s2 = "cbda"
 * Output: 3
 * Explanation: The longest common subsequence is "bda".
 * Example 2:
 * <p>
 * Input: s1 = "passport"
 * s2 = "ppsspt"
 * Output: 5
 * Explanation: The longest common subsequence is "psspt".
 */
public class LongestCommonSubsequence {

    /**
     * Approach 1: This is brute-force top down recursive approach. Time: O(3 ^ length1 + length2), Space: O(length1 + length2) -> recursion stack
     */
    static int countLCSRecursive(String s1, String s2, int i1, int i2) {

        int length1 = s1.length();
        int length2 = s2.length();

        //base conditions
        if (i1 == length1 || i2 == length2) {
            return 0;
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return 1 + countLCSRecursive(s1, s2, i1 + 1, i2 + 1);
        }

        int c1 = countLCSRecursive(s1, s2, i1, i2 + 1);
        int c2 = countLCSRecursive(s1, s2, i1 + 1, i2);
        return Math.max(c1, c2);
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization. Time: O(length1 x length2), Space: O(length1 x length2)
     */
    private static int countLCSRecursiveWithMemoization(HashMap<String, Integer> dp, String s1, String s2, int i1, int i2) {

        int length1 = s1.length();
        int length2 = s2.length();

        // base condition
        if (i1 == length1 || i2 == length2) {
            return 0;
        }

        // Using string as a key in hashmap used to store intermediate values.
        String search = String.format("%d|%d", i1, i2);
        if (dp.get(search) == null) {

            if (s1.charAt(i1) == s2.charAt(i2)) {
                return 1 + countLCSRecursiveWithMemoization(dp, s1, s2, i1 + 1, i2 + 1);
            }

            int c1 = countLCSRecursiveWithMemoization(dp, s1, s2, i1, i2 + 1);
            int c2 = countLCSRecursiveWithMemoization(dp, s1, s2, i1 + 1, i2);
            dp.put(search, Math.max(c1, c2));
        }

        return dp.get(search);
    }

    static int countLCSRecursiveWithMemoization(String s1, String s2) {
        HashMap<String, Integer> dp = new HashMap<>();
        return countLCSRecursiveWithMemoization(dp, s1, s2, 0, 0);
    }

    /**
     * Approach 3: This is bottom-up pure dynamic programming approach. Time: O(length1 x length2), Space: O(length1 x length2)
     */
    static int countLCSDP(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        int[][] dp = new int[length1 + 1][length2 + 1];
        // The 0th row and 0th column are all zeroes to indicate no match found between (s1, null) and (s2, null).

        for (int row = 1; row <= length1; row++) {
            for (int col = 1; col <= length2; col++) {

                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;

                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
            //DPUtils.print2DArray(dp, length1 + 1, length2 + 1);
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        System.out.println("------------s1 = abdca, s2 = cbda-------------");
        System.out.println("Result (countLCSRecursive): " + countLCSRecursive("abdca", "cbda", 0, 0));
        System.out.println("Result (countLCSRecursiveWithMemoization): " + countLCSRecursiveWithMemoization("abdca", "cbda"));
        System.out.println("Result (countLCSDP): " + countLCSDP("abdca", "cbda"));
        System.out.println();

        System.out.println("------------s1 = ppsspt, s2 = passport-------------");
        System.out.println("Result (countLCSRecursive): " + countLCSRecursive("ppsspt", "passport", 0, 0));
        System.out.println("Result (countLCSRecursiveWithMemoization): " + countLCSRecursiveWithMemoization("ppsspt", "passport"));
        System.out.println("Result (countLCSDP): " + countLCSDP("ppsspt", "passport"));
    }
}
