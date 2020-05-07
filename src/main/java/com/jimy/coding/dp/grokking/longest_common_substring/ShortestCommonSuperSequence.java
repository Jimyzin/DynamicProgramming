package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 06-05-2020.
 */
public class ShortestCommonSuperSequence {

    /**
     * Approach 1: This approach makes use of longest common subsequence calculated using dynamic programming.
     * Time & space complexity same as LongestCommonSubsequence.countLCSDP(s1, s2)
     */
    static int calculateUsingLCSDP(String s1, String s2) {
        int longestCommonSubsequence = LongestCommonSubsequence.countLCSDP(s1, s2);
        return s1.length() + s2.length() - longestCommonSubsequence;
    }

    /**
     * Approach 2: This is brute-force bottom-up recursive approach. Time: O( 2^ s1.length + s2.length), Space: O(s1.length + s2.length)
     */
    static int calculateByRecursion(String s1, String s2, int i1, int i2) {

        int len1 = s1.length();
        int len2 = s2.length();

        // base conditions - If we have reached the end of one string, return the remaining length of the other string.
        // as in this case, we have to take all of the remaining of the other string.
        if (i1 == len1) {
            return len2 - i2;
        }

        if (i2 == len2) {
            return len1 - i1;
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return 1 + calculateByRecursion(s1, s2, i1 + 1, i2 + 1);
        }

        int c1 = 1 + calculateByRecursion(s1, s2, i1 + 1, i2);
        int c2 = 1 + calculateByRecursion(s1, s2, i1, i2 + 1);
        return Math.min(c1, c2);
    }

    /**
     * Approach 3: This is bottom-up recursive approach with memoization. Time: O(s1.length * s2.length), Space: O(s1.length * s2.length)
     */
    private static int calculateByRecursionWithMemoization(Integer[][] dp, String s1, String s2, int i1, int i2) {

        int len1 = s1.length();
        int len2 = s2.length();

        // base conditions - If we have reached the end of one string, return the remaining length of the other string.
        // as in this case, we have to take all of the remaining of the other string.
        if (i1 == len1) {
            return len2 - i2;
        }

        if (i2 == len2) {
            return len1 - i1;
        }

        if (dp[i1][i2] == null) {

            if (s1.charAt(i1) == s2.charAt(i2)) {
                dp[i1][i2] = 1 + calculateByRecursionWithMemoization(dp, s1, s2, i1 + 1, i2 + 1);

            } else {

                int c1 = 1 + calculateByRecursionWithMemoization(dp, s1, s2, i1 + 1, i2);
                int c2 = 1 + calculateByRecursionWithMemoization(dp, s1, s2, i1, i2 + 1);
                dp[i1][i2] = Math.min(c1, c2);
            }
        }
        return dp[i1][i2];
    }

    static int calculateByRecursionWithMemoization(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length()][s2.length()];
        return calculateByRecursionWithMemoization(dp, s1, s2, 0, 0);
    }

    /**
     * Approach 4: This is bottom-up dynamic programming approach Time: O( s1.length * s2.length), Space: O(s1.length * s2.length)
     */
    static int calculateByDP(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        //if one of the strings is of zero length, SCS would be equal to the length of the other string
        // populating 0th row - shortestCommonSuperSequence(null, s2) = index value of s2 elements + 1
        for (int i2 = 1; i2 <= len2; i2++)
            dp[0][i2] = i2;

        // populating 0th column - shortestCommonSuperSequence(s1, null) = index value of s1 elements + 1
        for (int i1 = 1; i1 <= len1; i1++)
            dp[i1][0] = i1;

        for (int i1 = 1; i1 <= len1; i1++) {
            for (int i2 = 1; i2 <= len2; i2++) {

                if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) {
                    // add 1 to the superSequence length formed by s1(0, i1 - 2) and s2(0, i2 - 2)
                    dp[i1][i2] = 1 + dp[i1 - 1][i2 - 1];

                } else {
                    // add 1 to the min[superSequence length formed by s1(0, i1 - 2) and s2(0, i2 - 1) &
                    // superSequence length formed by s1(0, i1 - 1) and s2(0, i2 - 2)]
                    dp[i1][i2] = 1 + Math.min(dp[i1 - 1][i2], dp[i1][i2 - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println("---------------------------s1 = abcf, s2 = bdcf------------------------");
        System.out.println("Result (calculateUsingLCSDP):" + calculateUsingLCSDP("abcf", "bdcf"));
        System.out.println("Result (calculateByRecursion):" + calculateByRecursion("abcf", "bdcf", 0, 0));
        System.out.println("Result (calculateByRecursionWithMemoization):" + calculateByRecursionWithMemoization("abcf", "bdcf"));
        System.out.println("Result (calculateByDP):" + calculateByDP("abcf", "bdcf"));
        System.out.println();

        System.out.println("---------------------------s1 = dynamic, s2 = programming------------------------");
        System.out.println("Result (calculateUsingLCSDP):" + calculateUsingLCSDP("dynamic", "programming"));
        System.out.println("Result (calculateByRecursion):" + calculateByRecursion("dynamic", "programming", 0, 0));
        System.out.println("Result (calculateByRecursionWithMemoization):" + calculateByRecursionWithMemoization("dynamic", "programming"));
        System.out.println("Result (calculateByDP):" + calculateByDP("dynamic", "programming"));

    }
}
