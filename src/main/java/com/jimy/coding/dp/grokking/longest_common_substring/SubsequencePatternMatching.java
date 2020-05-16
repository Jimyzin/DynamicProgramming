package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 08-05-2020.
 * <p>
 * Problem Statement #
 * Given a string and a pattern, write a method to count the number of times the pattern appears in the string as a subsequence.
 * <p>
 * Example 1: Input: string: “baxmx”, pattern: “ax”
 * Output: 2
 * Explanation: {baxmx, baxmx}.
 * <p>
 * Example 2:
 * <p>
 * Input: string: “tomorrow”, pattern: “tor”
 * Output: 4
 * Explanation: Following are the four occurences: {tomorrow, tomorrow, tomorrow, tomorrow}.
 */
public class SubsequencePatternMatching {

    /**
     * Approach 1: This is brute-force, top-down recursive approach.
     * Time: O(2 ^ s.length()), Space: O(s.length()) ->recursion stack.
     */
    static int calculateRecursive(String s, String pattern, int i, int j) {
        int len1 = s.length();
        int len2 = pattern.length();

        if (j == len2) {
            return 1;
        }

        if (i == len1) {
            return 0;
        }

        int c1 = 0;
        if (s.charAt(i) == pattern.charAt(j)) {
            c1 = calculateRecursive(s, pattern, i + 1, j + 1);
        }

        int c2 = calculateRecursive(s, pattern, i + 1, j);
        return c1 + c2;
    }

    /**
     * Approach 2: This is bottom-up dynamic programming approach.
     * Time: O(s.length() * pattern.length()), Space: O(s.length() * pattern.length())  .
     */
    static int calculateSPMDP(String s, String pattern) {

        int len = s.length();
        int patternLen = pattern.length();
        int[][] dp = new int[patternLen][len + 1];

        //populating 0th row for matches between 0th element of pattern and s
        for (int a = 1; a <= len; a++) {
            if (pattern.charAt(0) == s.charAt(a - 1)) {
                dp[0][a] = dp[0][a - 1] + 1;

            } else {
                dp[0][a] = dp[0][a - 1];
            }
        }
        //DPUtils.print2DArray(dp, patternLen, len);
        for (int i = 1; i < patternLen; i++) {
            for (int j = 1; j <= len; j++) {

                if (pattern.charAt(i) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];

                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
            //DPUtils.print2DArray(dp, patternLen, len);
        }

        return dp[patternLen - 1][len];
    }

    public static void main(String[] args) {
        System.out.println("--------------------s = baxmx, pattern = ax--------------------");
        System.out.println("Result (calculateSPMDP): " + calculateSPMDP("baxmx", "ax"));
        System.out.println("Result (calculateRecursive): " + calculateRecursive("baxmx", "ax", 0, 0));
        System.out.println();

        System.out.println("--------------------s = tomorrow, pattern = tor--------------------");
        System.out.println("Result (calculateSPMDP): " + calculateSPMDP("tomorrow", "tor"));
        System.out.println("Result (calculateRecursive): " + calculateRecursive("tomorrow", "tor", 0, 0));
        System.out.println();

        System.out.println("--------------------s = tomorrrr, pattern = tor--------------------");
        System.out.println("Result (calculateSPMDP): " + calculateSPMDP("tomorrrr", "tor"));
        System.out.println("Result (calculateRecursive): " + calculateRecursive("tomorrrr", "tor", 0, 0));
        System.out.println();

        System.out.println("--------------------s = baxmxax, pattern = ax--------------------");
        System.out.println("Result (calculateSPMDP): " + calculateSPMDP("baxmxax", "ax"));
        System.out.println("Result (calculateRecursive): " + calculateRecursive("baxmxax", "ax", 0, 0));
    }
}
