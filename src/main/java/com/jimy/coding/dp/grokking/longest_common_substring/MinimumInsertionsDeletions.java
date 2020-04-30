package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 30-04-2020.
 * <p>
 * Minimum Deletions & Insertions to Transform a String into another
 * <p>
 * Problem Statement #
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters. Write a function to calculate the count of the minimum number of deletion and insertion operations.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "abc"
 * s2 = "fbc"
 * Output: 1 deletion and 1 insertion.
 * Explanation: We need to delete {'a'} and insert {'f'} to s1 to transform it into s2.
 * Example 2:
 * <p>
 * Input: s1 = "abdca"
 * s2 = "cbda"
 * Output: 2 deletions and 1 insertion.
 * Explanation: We need to delete {'a', 'c'} and insert {'c'} to s1 to transform it into s2.
 * Example 3:
 * <p>
 * Input: s1 = "passport"
 * s2 = "ppsspt"
 * Output: 3 deletions and 1 insertion
 * Explanation: We need to delete {'a', 'o', 'r'} and insert {'p'} to s1 to transform it into s2.
 */
public class MinimumInsertionsDeletions {

    static void findMinInsertionsDeletions(String fromString, String toString) {
        int longestCommonSubsequence = LongestCommonSubsequence.countLCSDP(fromString, toString);
        int deletions = fromString.length() - longestCommonSubsequence;
        int insertions = toString.length() - longestCommonSubsequence;

        System.out.println("Insertions: " + insertions + " & Deletions: " + deletions);
    }

    public static void main(String[] args) {
        System.out.println("--------------------- s1 = abc, s2 = fbc-------------------");
        findMinInsertionsDeletions("abc", "fbc");
        System.out.println();

        System.out.println("--------------------- s1 = abdca, s2 = cbda-------------------");
        findMinInsertionsDeletions("abdca", "cbda");
        System.out.println();

        System.out.println("--------------------- s1 = passport, s2 = ppsspt-------------------");
        findMinInsertionsDeletions("passport", "ppsspt");
    }
}
