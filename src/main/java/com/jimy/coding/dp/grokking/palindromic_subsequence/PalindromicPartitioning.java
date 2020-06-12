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

    /**
     * Approach 2: This is a dynamic programming approach that calculates the minimum number of palindromic substrings in a given string.
     * This, the number of partitions (or cuts) = one less tha the above value.
     *
     */
    static int countPartitionsDP2(String s) {
        int len = s.length();
        int[][] dp = new int[len][len]; // dp[startIndex][endIndex] represents the count of minimum of palindromic partitions between startIndex and endIndex.

        //all elements are palindrome of length at their corresponding indices, resulting in all 1's along the diagonal.
        for (int index = 0; index < len; index++) {
            dp[index][index] = 1;
        }

        // fill the rest of the elements of the top-half array diagonally
        for (int diff = 1; diff < len; diff++) {
            for (int startIndex = 0, endIndex = startIndex + diff; endIndex < len; startIndex++, endIndex++) {

                // if a match is found it may be a palindrome (eg: dbd) or may not (eg: ddpd)
                if (s.charAt(startIndex) == s.charAt(endIndex)) {

                    // it is a palindrome if startIndex is adjacent to endIndex, i.e. a string of length 2 (eg: dd)
                    // else if the rest of the string excluding the startIndex and endIndex is palindrome,
                    //                          i.e s(startIndex + 1, endIndex - 1) = 1 palindromic string (eg: m -->ada<-- m)
                    if (diff == 1 || dp[startIndex + 1][endIndex - 1] == 1) {
                        dp[startIndex][endIndex] = 1;

                    } else {

                        // if the rest of the string excluding the startIndex and endIndex is not a palindrome,
                        // i.e s(startIndex + 1, endIndex - 1) > 1, the minimum number of palindromic strings = MINIMUM_VALUE {
                             // 1(element at startIndex) + min. number of palindromes in s(startIndex + 1, endIndex),
                             // 1(element at endIndex) + min. number of palindromes in s(startIndex, endIndex - 1),
                             // 2(element at starIndex + element at endIndex) + min. number of palindromes in s(startIndex + 1, endIndex - 1) }
                        dp[startIndex][endIndex] = Math.min(Math.min( (1 + dp[startIndex][endIndex - 1]), (1 + dp[startIndex + 1][endIndex]) ),
                                2 + dp[startIndex + 1][endIndex - 1]);
                    }

                } else {

                    // if no match is found, the minimum number of palindromic strings = MINIMUM_VALUE {
                       // 1(element at startIndex) + min. number of palindromes in s(startIndex + 1, endIndex),
                       // 1(element at endIndex) + min. number of palindromes in s(startIndex, endIndex - 1) }
                    dp[startIndex][endIndex] = 1 + Math.min(dp[startIndex][endIndex - 1], dp[startIndex + 1][endIndex]);
                }
            }
        }
        DPUtils.print2DArray(dp, len, len);

        //Minimum number of partitions = Minimum number of palindromes - 1
        return dp[0][len - 1] - 1;
    }

    public static void main(String[] args) {
        System.out.println("--------------abdbca----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("abdbca"));
        System.out.println("Result (countPartitionsDP2):" + countPartitionsDP2("abdbca"));
        System.out.println();

        System.out.println("--------------cddpd----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("cddpd"));
        System.out.println("Result (countPartitionsDP2):" + countPartitionsDP2("cddpd"));
        System.out.println();

        System.out.println("--------------pqr----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("pqr"));
        System.out.println("Result (countPartitionsDP2):" + countPartitionsDP2("pqr"));
        System.out.println();

        System.out.println("--------------pp----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("pp"));
        System.out.println("Result (countPartitionsDP2):" + countPartitionsDP2("pp"));
        System.out.println();

        System.out.println("--------------madam----------------");
        System.out.println("Result (countPartitionsDP):" + countPartitionsDP("madam"));
        System.out.println("Result (countPartitionsDP2):" + countPartitionsDP2("madam"));
    }
}
