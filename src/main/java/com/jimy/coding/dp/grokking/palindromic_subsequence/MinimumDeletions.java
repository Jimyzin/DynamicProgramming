package com.jimy.coding.dp.grokking.palindromic_subsequence;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 26-04-2020.
 */
public class MinimumDeletions {

    /**
     * Approach 1: This is bottom-up dynamic programming approach to purely focus on calculating minimum deletions.
     */
    static int calculateMinimumDeletionsDP(String st) {

        int len = st.length();
        int[][] dp = new int[len][len];

        // Since dp is int[][], all elements have a default value of 0. Hence, there is no need of the below block.
        //for (int a = 0; a < len; a++) {
        //    dp[a][a] = 0; // This signifies all single element is palindromic by itself requiring minimum deletion of 0.
        //}

        for (int startIndex = len - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < len; endIndex++) {

                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    dp[startIndex][endIndex] = dp[startIndex + 1][endIndex - 1];

                } else {
                    dp[startIndex][endIndex] = Math.min(dp[startIndex][endIndex - 1], dp[startIndex + 1][endIndex]) + 1;
                }
            }
            //DPUtils.print2DArray(dp, len, len);
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        System.out.println("--------------abdbca----------------");
        System.out.println("Result (calculateMinimumDeletionsDP):" + calculateMinimumDeletionsDP("abdbca"));
        /**
         * Approach 2: This is st.length() - the length of the longest palindromic subsequence.
         */
        System.out.println("Result (LongestPalindromicSubsequence.calculateLPSLength):" + ("abdbca".length() - LongestPalindromicSubsequence.calculateLPSLengthDP("abdbca")));
        System.out.println();

        System.out.println("--------------cddpd----------------");
        System.out.println("Result (calculateMinimumDeletionsDP):" + calculateMinimumDeletionsDP("cddpd"));
        System.out.println("Result (LongestPalindromicSubsequence.calculateLPSLength):" + ("cddpd".length() - LongestPalindromicSubsequence.calculateLPSLengthDP("cddpd")));
        System.out.println();

        System.out.println("--------------pqr----------------");
        System.out.println("Result (calculateMinimumDeletionsDP):" + calculateMinimumDeletionsDP("pqr"));
        System.out.println("Result (LongestPalindromicSubsequence.calculateLPSLength):" + ("pqr".length() - LongestPalindromicSubsequence.calculateLPSLengthDP("pqr")));
    }
}
