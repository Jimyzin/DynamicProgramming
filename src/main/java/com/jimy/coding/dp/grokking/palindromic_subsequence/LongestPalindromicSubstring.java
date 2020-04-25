package com.jimy.coding.dp.grokking.palindromic_subsequence;

/**
 * Created by Jimy on 23-04-2020.
 * <p>
 * Given a string, find the length of its Longest Palindromic Substring (LPS). In a palindromic string, elements read the same backward and forward.
 * <p>
 * Example 1:
 * <p>
 * Input: "abdbca"
 * Output: 3
 * Explanation: LPS is "bdb".
 * Example 2:
 * <p>
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "dpd".
 * Example 3:
 * <p>
 * Input: = "pqr"
 * Output: 1
 * Explanation: LPS could be "p", "q" or "r".
 */
public class LongestPalindromicSubstring {

    /**
     * Approach 1: This is brute-force top-down recursive approach. Time: O(3 ^ st.length()), Space: O(st.length()) -> recursion stack
     */
    static int calculateLPSubstringRecursive(String st, int startIndex, int endIndex) {
        //System.out.println("startIndex = "+startIndex+", endIndex = "+ endIndex);

        // base checks
        if (startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return 1;
        }

        int result = Math.max(calculateLPSubstringRecursive(st, startIndex + 1, endIndex),
                calculateLPSubstringRecursive(st, startIndex, endIndex - 1));

        if (st.charAt(startIndex) == st.charAt(endIndex)) {
            int temp = calculateLPSubstringRecursive(st, startIndex + 1, endIndex - 1);
            if (Math.abs(endIndex - startIndex) - 1 == temp) {
                result = 2 + temp;
            }
        }
        return result;
    }

    /**
     * Approach 2: This is bottom-up dynamic programming approach. Tine: O(st.length() ^ 2), Space: O(st.length() ^ 2)
     */
    static int calculateLPSubstringDP(String st) {

        int length = st.length();
        int[][] dp = new int[length][length];

        for (int diagonal = 0; diagonal < length; diagonal++) {
            dp[diagonal][diagonal] = 1;
        }

        //DPUtils.print2DArray(dp, length, length);

        for (int i = 1; i < length; i++) {
            for (int row = 0, column = i; row < length - i && column < length; row++, column++) {

                if (st.charAt(row) == st.charAt(column) && (Math.abs(column - row) - 1 == dp[row + 1][column - 1])) {
                    dp[row][column] = 2 + dp[row + 1][column - 1];

                } else {
                    dp[row][column] = Math.max(dp[row + 1][column], dp[row][column - 1]);
                }
            }
            //DPUtils.print2DArray(dp, length, length);
        }

        return dp[0][length - 1];
    }

    public static void main(String[] args) {
        System.out.println("--------------abdbca----------------");
        System.out.println("Result (calculateLPSubstringRecursive):" + calculateLPSubstringRecursive("abdbca", 0, 5));
        System.out.println("Result (calculateLPSubstringDP):" + calculateLPSubstringDP("abdbca"));
        System.out.println();

        System.out.println("--------------cddpd----------------");
        System.out.println("Result (calculateLPSubstringRecursive):" + calculateLPSubstringRecursive("cddpd", 0, 4));
        System.out.println("Result (calculateLPSubstringDP):" + calculateLPSubstringDP("cddpd"));
        System.out.println();

        System.out.println("--------------pqr----------------");
        System.out.println("Result (calculateLPSubstringRecursive):" + calculateLPSubstringRecursive("pqr", 0, 2));
        System.out.println("Result (calculateLPSubstringDP):" + calculateLPSubstringDP("pqr"));
    }
}
