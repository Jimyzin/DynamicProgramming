package com.jimy.coding.dp.grokking.palindromic_subsequence;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 26-04-2020.
 */
public class PalindromicPartitioning {

    /**
     * This is bottom-up dynamic programming approach. Time: O(st.length ^ 2), Space: O(st.length ^ 2)
     */
    static int countPartitionsDP(String st) {

        int len = st.length();
        int[][] dp = new int[len][len];

        // Since dp is int[][], all elements have a default value of 0. Hence, there is no need of the below block.
        //for (int a = 0; a < len; a++) {
        //    dp[a][a] = 0; // This signifies all single element is palindromic by itself requiring minimum deletion of 0.
        //}

        for (int startIndex = len - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < len; endIndex++) {

                // If st.charAt(startIndex) == st.charAt(endIndex) and st.substring(startIndex + 1, endIndex - 1) does not have any partition,
                // it means that st.substring(startIndex, endIndex) is palindromic as a whole and needs no intermediate partitioning.
                if (st.charAt(startIndex) == st.charAt(endIndex) && dp[startIndex + 1][endIndex - 1] == 0) {
                    dp[startIndex][endIndex] = dp[startIndex + 1][endIndex - 1];

                } else {
                    dp[startIndex][endIndex] = Math.min(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]) + 1;
                }
            }
            //DPUtils.print2DArray(dp, len, len);
        }
        return dp[0][len - 1];

    }

    public static void main(String[] args) {
        System.out.println("--------------abdbca----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("abdbca"));
        System.out.println();

        System.out.println("--------------cddpd----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("cddpd"));
        System.out.println();

        System.out.println("--------------pqr----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("pqr"));
        System.out.println();

        System.out.println("--------------pp----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("pp"));
        System.out.println();

        System.out.println("--------------madam----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("madam"));
    }
}
