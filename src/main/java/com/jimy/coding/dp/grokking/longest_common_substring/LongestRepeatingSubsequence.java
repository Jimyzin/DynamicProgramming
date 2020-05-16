package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 08-05-2020.
 * <p>
 * Given a sequence, find the length of its longest repeating subsequence (LRS). A repeating subsequence will be the one that appears at least twice in the original sequence and is not overlapping (i.e. none of the corresponding characters in the repeating subsequences have the same index).
 * <p>
 * Example 1:
 * <p>
 * Input: “t o m o r r o w”
 * Output: 2
 * Explanation: The longest repeating subsequence is “or” {tomorrow}.
 * <p>
 * Example 2:
 * <p>
 * Input: “a a b d b c e c”
 * Output: 3
 * Explanation: The longest repeating subsequence is “a b c” {a a b d b c e c}.
 * <p>
 * Example 3:
 * <p>
 * Input: “f m f f”
 * Output: 2
 * Explanation: The longest repeating subsequence is “f f” {f m f f, f m f f}. Please note the second last character is shared in LRS.
 */
public class LongestRepeatingSubsequence {

    /**
     * Approach 1: This is brute-force, top-down recursive approach.
     * Time: O(2 ^ s.length()), Space: O(s.length()) -> recursion stack.
     */
    static int calculateLRSRecursive(String s, int i1, int i2) {

        int len = s.length();
        // base conditions
        if (i1 == len || i2 == len) {
            return 0;
        }

        // i1 != i2 check ensures the same characters at same indices do not match. Without this condition the result
        // will always be the length of s, which is the longest common subsequence in one string (compared with itself).
        // PS: The number of longest repeating subsequences (which is also the longest common subsequnce with a string) will always be 2.
        // Adding 1 on match means corresponding letters of the 2 subsequnces are found and hence, the length is incremented.
        if (i1 != i2 && s.charAt(i1) == s.charAt(i2)) {
            return 1 + calculateLRSRecursive(s, i1 + 1, i2 + 1);
        }

        // Apart from match condition, other subsequences are checked here.
        int lrs1 = calculateLRSRecursive(s, i1, i2 + 1);
        int lrs2 = calculateLRSRecursive(s, i1 + 1, i2);

        return Math.max(lrs1, lrs2);
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization.
     * Time: O(s.length() ^ 2), Space: O(s.length() ^ 2).
     */
    private static int calculateLRSRecursiveWithMemoization(Integer[][] dp, String s, int i1, int i2) {

        int len = s.length();
        // base conditions
        if (i1 == len || i2 == len) {
            return 0;
        }

        if (dp[i1][i2] == null) {
            // i1 != i2 check ensures the same characters at same indices do not match. Without this condition the result
            // will always be the length of s, which is the longest common subsequence in one string (compared with itself).
            // PS: The number of longest repeating subsequences (which is also the longest common subsequnce with a string) will always be 2.
            // Adding 1 on match means corresponding letters of the 2 subsequnces are found and hence, the length is incremented.
            if (i1 != i2 && s.charAt(i1) == s.charAt(i2)) {
                dp[i1][i2] = 1 + calculateLRSRecursiveWithMemoization(dp, s, i1 + 1, i2 + 1);

            } else {

                // If no match is found, other subsequences are checked here.
                int lrs1 = calculateLRSRecursiveWithMemoization(dp, s, i1, i2 + 1);
                int lrs2 = calculateLRSRecursiveWithMemoization(dp, s, i1 + 1, i2);
                dp[i1][i2] = Math.max(lrs1, lrs2);
            }
        }
        return dp[i1][i2];
    }

    static int calculateLRSRecursiveWithMemoization(String s) {
        int len = s.length();
        Integer[][] dp = new Integer[len][len];
        return calculateLRSRecursiveWithMemoization(dp, s, 0, 0);
    }

    /**
     * Approach 2: This is bottom-up recursive approach with memoization.
     * Time: O(s.length() ^ 2), Space: O(s.length() ^ 2).
     */
    static int calculateLRSDP(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];

        for (int i1 = 1; i1 <= len; i1++) {
            for (int i2 = 1; i2 <= len; i2++) {

                if (i1 != i2 && s.charAt(i1 - 1) == s.charAt(i2 - 1)) {
                    dp[i1][i2] = 1 + dp[i1 - 1][i2 - 1];

                } else {
                    dp[i1][i2] = Math.max(dp[i1][i2 - 1], dp[i1 - 1][i2]);
                }
            }
        }
        return dp[len][len];
    }

    public static void main(String[] args) {
        System.out.println("-----------------------tomorrow----------------");
        System.out.println("Result (calculateLRSRecursive): " + calculateLRSRecursive("tomorrow", 0, 0));
        System.out.println("Result (calculateLRSRecursiveWithMemoization): " + calculateLRSRecursiveWithMemoization("tomorrow"));
        System.out.println("Result (calculateLRSDP): " + calculateLRSDP("tomorrow"));
        System.out.println();

        System.out.println("-----------------------aabdbcec----------------");
        System.out.println("Result (calculateLRSRecursive): " + calculateLRSRecursive("aabdbcec", 0, 0));
        System.out.println("Result (calculateLRSRecursiveWithMemoization): " + calculateLRSRecursiveWithMemoization("aabdbcec"));
        System.out.println("Result (calculateLRSDP): " + calculateLRSDP("aabdbcec"));
        System.out.println();

        System.out.println("-----------------------fmff----------------");
        System.out.println("Result (calculateLRSRecursive): " + calculateLRSRecursive("fmff", 0, 0));
        System.out.println("Result (calculateLRSRecursiveWithMemoization): " + calculateLRSRecursiveWithMemoization("fmff"));
        System.out.println("Result (calculateLRSDP): " + calculateLRSDP("fmff"));
    }
}
