package com.jimy.coding.dp.grokking.palindromic_subsequence;

/**
 * Created by Jimy on 25-04-2020.
 * <p>
 * Given a string, find the total number of palindromic substrings in it. Please note we need to find the total number of substrings and not subsequences.
 * <p>
 * Example 1:
 * <p>
 * Input: "abdbca"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "a", "b", "d", "b", "c", "a", "bdb".
 * Example 2:
 * <p>
 * Input: = "cddpd"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "c", "d", "d", "p", "d", "dd", "dpd".
 * Example 3:
 * <p>
 * Input: = "pqr"
 * Output: 3
 * Explanation: Here are the palindromic substrings,"p", "q", "r".
 */
public class CountOfPalindromicSubstrings {

    /**
     * This is bottom-up dynamic programming approach. Time: O(st.length() ^ 2), Space: O(st.length() ^ 2)
     */
    static int countPalindromicSubstringsDP(String st) {
        int len = st.length();
        boolean[][] dp = new boolean[len][len];
        int count = 0;

        for (int a = 0; a < len; a++) {
            dp[a][a] = true;
            count++;
        }

        for (int startIndex = len - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < len; endIndex++) {

                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                        dp[startIndex][endIndex] = true;
                        count++;
                    }
                }

            }
            //DPUtils.print2DArray(dp, len, len);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println("------------------abdbca---------------------");
        System.out.println("Result (countPalindromicSubstringsDP):" + countPalindromicSubstringsDP("abdbca"));
        System.out.println();

        System.out.println("------------------cddpd---------------------");
        System.out.println("Result (countPalindromicSubstringsDP):" + countPalindromicSubstringsDP("cddpd"));
        System.out.println();

        System.out.println("------------------pqr---------------------");
        System.out.println("Result (countPalindromicSubstringsDP):" + countPalindromicSubstringsDP("pqr"));
    }
}
