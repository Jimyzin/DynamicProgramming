package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 28-04-2020.
 * <p>
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest substring which is common in both the strings.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "abdca"
 * s2 = "cbda"
 * Output: 2
 * Explanation: The longest common substring is "bd".
 * Example 2:
 * <p>
 * Input: s1 = "passport"
 * s2 = "ppsspt"
 * Output: 3
 * Explanation: The longest common substring is "ssp".
 */
public class LongestCommonSubstring {

    /**
     * Approach 1: This is brute-force top-down recursive approach. Time: O(3 ^ (length1 + length2), Space: O(length1 + length2) -> recursion stack.
     */
    static int solveLCSRecursive(String s1, String s2, int index1, int index2, int count) {

        int length1 = s1.length();
        int length2 = s2.length();

        //base conditions
        if (index1 == length1 || index2 == length2) {
            return count;
        }

        if (s1.charAt(index1) == s2.charAt(index2)) {
            count = solveLCSRecursive(s1, s2, index1 + 1, index2 + 1, count + 1);
        }

        int c1 = solveLCSRecursive(s1, s2, index1 + 1, index2, 0);
        int c2 = solveLCSRecursive(s1, s2, index1, index2 + 1, 0);
        return Math.max(count, Math.max(c1, c2));
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization. Time: O(length1 * length2 * min(length1, length2)), Space: O(length1 * length2 * min(length1, length2)).
     */
    private static int solveLCSRecursiveWithMemoization(Integer[][][] dp, String s1, String s2, int index1, int index2, int count) {

        int length1 = s1.length();
        int length2 = s2.length();

        //base conditions
        if (index1 == length1 || index2 == length2) {
            return count;
        }

        if (dp[index1][index2][count] == null) {
            int c1 = count;
            if (s1.charAt(index1) == s2.charAt(index2)) {
                c1 = solveLCSRecursiveWithMemoization(dp, s1, s2, index1 + 1, index2 + 1, count + 1);
            }

            int c2 = solveLCSRecursiveWithMemoization(dp, s1, s2, index1 + 1, index2, 0);
            int c3 = solveLCSRecursiveWithMemoization(dp, s1, s2, index1, index2 + 1, 0);
            dp[index1][index2][count] = Math.max(c1, Math.max(c2, c3));
            ;
        }
        return dp[index1][index2][count];
    }

    static int solveLCSRecursiveWithMemoization(String s1, String s2) {
        Integer[][][] dp = new Integer[s1.length()][s2.length()][Math.min(s1.length(), s2.length())];
        return solveLCSRecursiveWithMemoization(dp, s1, s2, 0, 0, 0);
    }

    /**
     * Approach 3: This is a bottom-up pure dynamic programming approach. Time: O(length1 * length2), Space: O(length1 * length2).
     */
    static int solveLCSDP(String s1, String s2) {

        int length1 = s1.length();
        int length2 = s2.length();

        int[][] dp = new int[length1 + 1][length2 + 1];
        int longestLength = Integer.MIN_VALUE;

        for (int row = 1; row <= length1; row++) {
            for (int col = 1; col <= length2; col++) {

                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                    longestLength = Math.max(longestLength, dp[row][col]);
                }
            }
            //DPUtils.print2DArray(dp, length1 + 1, length2 + 1);
        }
        return longestLength;
    }

    public static void main(String[] args) {
        System.out.println("---------------s1 = abdca, s2 = cbda-----------------------");
        System.out.println("Result (solveLCSRecursive): " + solveLCSRecursive("abdca", "cbda", 0, 0, 0));
        System.out.println("Result (solveLCSRecursiveWithMemoization): " + solveLCSRecursiveWithMemoization("abdca", "cbda"));
        System.out.println("Result (solveLCSDP): " + solveLCSDP("abdca", "cbda"));
        System.out.println();

        System.out.println("---------------s1 = passport, s2 = ppsspt-----------------------");
        System.out.println("Result (solveLCSRecursive): " + solveLCSRecursive("passport", "ppsspt", 0, 0, 0));
        System.out.println("Result (solveLCSRecursiveWithMemoization): " + solveLCSRecursiveWithMemoization("passport", "ppsspt"));
        System.out.println("Result (solveLCSDP): " + solveLCSDP("passport", "ppsspt"));
        System.out.println();
    }
}