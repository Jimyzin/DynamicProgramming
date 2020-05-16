package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 16-05-2020.
 * <p>
 * Give three strings ‘m’, ‘n’, and ‘p’, write a method to find out if ‘p’ has been formed by interleaving ‘m’ and ‘n’. ‘p’ would be considered interleaving ‘m’ and ‘n’ if it contains all the letters from ‘m’ and ‘n’ and the order of letters is preserved too.
 * <p>
 * Example 1:
 * <p>
 * Input: m="abd", n="cef", p="abcdef"
 * Output: true
 * Explanation: 'p' contains all the letters from 'm' and 'n' and preserves their order too.
 * Example 2:
 * <p>
 * Input: m="abd", n="cef", p="adcbef"
 * Output: false
 * Explanation: 'p' contains all the letters from 'm' and 'n' but does not preserve the order.
 * Example 3:
 * <p>
 * Input: m="abc", n="def", p="abdccf"
 * Output: false
 * Explanation: 'p' does not contain all the letters from 'm' and 'n'.
 * Example 4:
 * <p>
 * Input: m="abcdef", n="mnop", p="mnaobcdepf"
 * Output: true
 * Explanation: 'p' contains all the letters from 'm' and 'n' and preserves their order too.
 */
public class StringsInterleaving {

    /**
     * Approach 1: This is brute-force top-down recursive approach. Time: O(2 ^ m + n), Space: O(m + n) -> recursion stack.
     */
    static boolean findSIRecursive(String m, String n, String p, int mIndex, int nIndex, int pIndex) {

        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length()) {
            return true;
        }

        if (pIndex == p.length()) {
            return false;
        }

        boolean b1 = false, b2 = false;
        if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
            b1 = findSIRecursive(m, n, p, mIndex + 1, nIndex, pIndex + 1);
        }

        if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
            b2 = findSIRecursive(m, n, p, mIndex, nIndex + 1, pIndex + 1);
        }

        return b1 || b2;
    }

    /**
     * Approach 2: This is bottom-up Dynamic programming approach. Time: O(m * n), Space: O(m * n).
     */
    static boolean findSIDP(String m, String n, String p) {

        int mLen = m.length(), nLen = n.length(), pLen = p.length();

        if (mLen + nLen != pLen) {
            return false;
        }
        boolean[][] dp = new boolean[mLen + 1][nLen + 1];

        for (int mIndex = 0; mIndex <= mLen; mIndex++) {
            for (int nIndex = 0; nIndex <= nLen; nIndex++) {

                if (nIndex == 0 && mIndex == 0)
                    dp[mIndex][nIndex] = true;

                else if (nIndex == 0 && m.charAt(mIndex - 1) == p.charAt(nIndex + mIndex - 1))
                    dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];

                else if (mIndex == 0 && n.charAt(nIndex - 1) == p.charAt(mIndex + nIndex))
                    dp[mIndex][nIndex] = dp[mIndex][nIndex - 1];

                else if (mIndex > 0 && m.charAt(mIndex - 1) == p.charAt(nIndex + mIndex - 1))
                    dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];

                else if (nIndex > 0 && n.charAt(nIndex - 1) == p.charAt(mIndex + nIndex - 1))
                    dp[mIndex][nIndex] = dp[mIndex][nIndex - 1];
            }
        }
        return dp[mLen][nLen];
    }

    /**
     * Approach 3: This is bottom-up dynamic programming approach using LCS. Time: Max[O(m * p), O(n * p)], Space: Max[O(m * p), O(n * p)].
     */
    static boolean findSIUsingLCS(String m, String n, String p) {

        int mLen = m.length(), nLen = n.length(), pLen = p.length();

        if (mLen + nLen == pLen) {
            int mLcs = LongestCommonSubsequence.countLCSDP(m, p);
            int nLcs = LongestCommonSubsequence.countLCSDP(n, p);

            if (mLcs == mLen && nLcs == nLen) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("------------------m = abd, n = cef, p = abcdef---------------------");
        System.out.println("Result (findSIRecursive): " + findSIRecursive("abd", "cef", "abcdef", 0, 0, 0));
        System.out.println("Result (findSIDP): " + findSIDP("abd", "cef", "abcdef"));
        System.out.println("Result (findSIUsingLCS): " + findSIUsingLCS("abd", "cef", "abcdef"));
        System.out.println();

        System.out.println("------------------m = abd, n = cef, p = adcbef---------------------");
        System.out.println("Result (findSIRecursive): " + findSIRecursive("abd", "cef", "adcbef", 0, 0, 0));
        System.out.println("Result (findSIDP): " + findSIDP("abd", "cef", "adcbef"));
        System.out.println("Result (findSIUsingLCS): " + findSIUsingLCS("abd", "cef", "adcbef"));
        System.out.println();

        System.out.println("------------------m = abc, n = def, p = abdccf---------------------");
        System.out.println("Result (findSIRecursive): " + findSIRecursive("abc", "def", "abdccf", 0, 0, 0));
        System.out.println("Result (findSIDP): " + findSIDP("abc", "def", "abdccf"));
        System.out.println("Result (findSIUsingLCS): " + findSIUsingLCS("abc", "def", "abdccf"));
        System.out.println();

        System.out.println("------------------m = abcdef, n = mnop, p = mnaobcdepf---------------------");
        System.out.println("Result (findSIRecursive): " + findSIRecursive("abcdef", "mnop", "mnaobcdepf", 0, 0, 0));
        System.out.println("Result (findSIDP): " + findSIDP("abcdef", "mnop", "mnaobcdepf"));
        System.out.println("Result (findSIUsingLCS): " + findSIUsingLCS("abcdef", "mnop", "mnaobcdepf"));
    }
}
