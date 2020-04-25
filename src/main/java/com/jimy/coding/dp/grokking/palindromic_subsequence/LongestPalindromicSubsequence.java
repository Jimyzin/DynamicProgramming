package com.jimy.coding.dp.grokking.palindromic_subsequence;

import com.jimy.coding.dp.utils.DPUtils;

/**
 * Created by Jimy on 20-04-2020.
 */
public class LongestPalindromicSubsequence {

    static int calculateLPSLengthRecursive(String st, int startIndex, int endIndex) {

        // base checks
        if (startIndex > endIndex) {
            return 0;
        }

        // base check -> startIndex == endIndex signifies the length of the string = 1.
        if (startIndex == endIndex) {
            return 1;
        }

        // case 1: elements at the beginning and end are same.
        if (st.charAt(startIndex) == st.charAt(endIndex)) {
            return 2 + calculateLPSLengthRecursive(st, startIndex + 1, endIndex - 1);
        }

        // case 2: elements at the beginning is different from the element at the end
        int length1 = calculateLPSLengthRecursive(st, startIndex, endIndex - 1); // length of a substring excluding the end element
        int length2 = calculateLPSLengthRecursive(st, startIndex + 1, endIndex); // length of a substring excluding the starting element.
        return Math.max(length1, length2);
    }

    static int calculateLPSLengthRecursiveWithMemoization(Integer[][] dp, String st, int startIndex, int endIndex) {

        // base checks
        if (startIndex > endIndex) {
            return 0;
        }

        // base check -> startIndex == endIndex signifies the length of the string = 1.
        if (startIndex == endIndex) {
            return 1;
        }

        if (dp[startIndex][endIndex] == null) {
            // case 1: elements at the beginning and end are same.
            if (st.charAt(startIndex) == st.charAt(endIndex)) {
                return 2 + calculateLPSLengthRecursiveWithMemoization(dp, st, startIndex + 1, endIndex - 1);
            }

            // case 2: elements at the beginning is different from the element at the end
            int length1 = calculateLPSLengthRecursiveWithMemoization(dp, st, startIndex, endIndex - 1); // length of a substring excluding the end element
            int length2 = calculateLPSLengthRecursiveWithMemoization(dp, st, startIndex + 1, endIndex); // length of a substring excluding the starting element.
            dp[startIndex][endIndex] = Math.max(length1, length2);
        }
        return dp[startIndex][endIndex];
    }

    static int calculateLPSLengthDP(String st) {

        int len = st.length();
        int[][] dp = new int[len][len];

        // This signifies all substrings with length 1, which is the maximum length of palindromic subsequence.
        for (int diagonal = 0; diagonal < len; diagonal++) {
            dp[diagonal][diagonal] = 1;
        }
        DPUtils.print2DArray(dp, len, len);

       for (int i = 1; i < len; i++) {
           for (int column = i, row = 0; column < len && row < len - i; column++, row++) {

               // Case 1: If element at the beginning matches with element at the end, add 2 to the
               // maximum length of palindromic subsequences formed by eliminating the starting and the ending element.
               if (st.charAt(row) == st.charAt(column)) {
                   dp[row][column] = 2 + dp[row + 1][column - 1];

               } else {
                   // Case 2: If element at the beginning differ from element at the end, find the
                   // maximum length of palindromic subsequences formed by eliminating the starting and the ending element.
                   dp[row][column] = Math.max(dp[row][column - 1], dp[row + 1][column]);
               }
               //DPUtils.print2DArray(dp, len, len);
           }
           //DPUtils.print2DArray(dp, len, len);
       }

        return dp[0][len - 1];
    }

    static int calculateLPSLengthRecursiveWithMemoization(String st) {
        int len = st.length();
        Integer[][] dp = new Integer[len][len];
        return calculateLPSLengthRecursiveWithMemoization(dp, st, 0, len - 1);
    }

    public static void main(String[] args) {
        System.out.println("--------------abdbca----------------");
        System.out.println("Result (calculateLPSLengthRecursive):" + calculateLPSLengthRecursive("abdbca", 0, 5));
        System.out.println("Result (calculateLPSLengthRecursiveWithMemoization):" + calculateLPSLengthRecursiveWithMemoization("abdbca"));
        System.out.println("Result (calculateLPSLengthDP):" + calculateLPSLengthDP("abdbca"));
        System.out.println();

        System.out.println("--------------cddpd----------------");
        System.out.println("Result (calculateLPSLengthRecursive):" + calculateLPSLengthRecursive("cddpd", 0, 4));
        System.out.println("Result (calculateLPSLengthRecursiveWithMemoization):" + calculateLPSLengthRecursiveWithMemoization("cddpd"));
        System.out.println("Result (calculateLPSLengthDP):" + calculateLPSLengthDP("cddpd"));
        System.out.println();

        System.out.println("--------------pqr----------------");
        System.out.println("Result (calculateLPSLengthRecursive):" + calculateLPSLengthRecursive("pqr", 0, 2));
        System.out.println("Result (calculateLPSLengthRecursiveWithMemoization):" + calculateLPSLengthRecursiveWithMemoization("pqr"));
        System.out.println("Result (calculateLPSLengthDP):" + calculateLPSLengthDP("pqr"));
    }
}
