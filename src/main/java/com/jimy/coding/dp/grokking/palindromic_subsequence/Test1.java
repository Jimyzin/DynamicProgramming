package com.jimy.coding.dp.grokking.palindromic_subsequence;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 25-04-2020.
 */
public class Test1 {

    static int findLPS(String st) {
        int len = st.length();
        int[][] dp = new int[len][len];

        for (int a = 0; a < len; a++) {
            dp[a][a] = 1;
        }

        for (int startIndex = len - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < len; endIndex++) {

                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                } else {
                    dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]);
                }
            }
            DPUtils.print2DArray(dp, len, len);
        }
        return dp[0][len - 1];
    }

    static int findLPSubstring(String st) {
        int len = st.length();
        int[][] dp = new int[len][len];

        for (int a = 0; a < len; a++) {
            dp[a][a] = 1;
        }

        for (int startIndex = len - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < len; endIndex++) {

                if (st.charAt(startIndex) == st.charAt(endIndex) && endIndex - startIndex == dp[startIndex + 1][endIndex - 1]) {
                    dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];

                }
                dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]);

            }
            DPUtils.print2DArray(dp, len, len);
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        System.out.println("abdbca: "+findLPSubstring("abdbca"));
        System.out.println("cddpd: "+findLPSubstring("cddpd"));
        System.out.println("pqr: "+findLPSubstring("pqr"));
    }
}
