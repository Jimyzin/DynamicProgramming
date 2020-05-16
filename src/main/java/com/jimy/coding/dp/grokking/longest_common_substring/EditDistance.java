package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 14-05-2020.
 * <p>
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting, inserting, or replacing characters. Write a function to calculate the count of the minimum number of edit operations.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "bat"
 * s2 = "but"
 * Output: 1
 * Explanation: We just need to replace 'a' with 'u' to transform s1 to s2.
 * Example 2:
 * <p>
 * Input: s1 = "abdca"
 * s2 = "cbda"
 * Output: 2
 * Explanation: We can replace first 'a' with 'c' and delete second 'c'.
 * Example 3:
 * <p>
 * Input: s1 = "passpot"
 * s2 = "ppsspqrt"
 * Output: 3
 * Explanation: Replace 'a' with 'p', 'o' with 'q', and insert 'r'.
 */
public class EditDistance {

    /**
     * Approach 1: This is brute-force, top-down recursive approach. Time: O(3 ^ s1.length() + s2.length()),
     * Space: O(s1.length() + s2.length()) -> recursion stack
     */
    static int calculateEDRecursive(String s1, String s2, int i1, int i2) {

        // base conditions
        // if we have reached the end of s1, it means we have to insert all remaining elements of s2
        if (i1 == s1.length()) {
            return s2.length() - i2;
        }

        // if we have reached the end of s2, it means we have to delete all remaining elements of s1
        if (i2 == s2.length()) {
            return s1.length() - i1;
        }

        // if a match is found, proceed to next indices
        if (s1.charAt(i1) == s2.charAt(i2)) {
            return calculateEDRecursive(s1, s2, i1 + 1, i2 + 1);

        }

        int c1 = 1 + calculateEDRecursive(s1, s2, i1 + 1, i2 + 1); //replace
        int c2 = 1 + calculateEDRecursive(s1, s2, i1, i2 + 1); //delete
        int c3 = 1 + calculateEDRecursive(s1, s2, i1 + 1, i2); // insert

        return Math.min(c1, Math.min(c2, c3));

    }

    /**
     * Approach 2: This is bottom-up dynamic programming approach. Time: O(s1.length() * s2.length()),
     * Space: O(s1.length() * s2.length())
     */
    static int calculateEDDP(String s1, String s2) {

        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Initialising 0th column: the number of minimum DELETIONS for a word to become null =
        // number of letters in the word.
        //
        // For example:
        // minimum deletions (b, null) = 1 (only letter b)
        // minimum deletions (ba, null) = 2 (letter b + letter a)
        // minimum deletions (bat, null) = 3 (letter b + letter a + letter c)

        for (int i1 = 1; i1 <= len1; i1++)
            dp[i1][0] = i1;

        // Initialising 0th row: the number of minimum INSERTIONS for null to become a word =
        // number of letters in the word.
        //
        // For example:
        // minimum insertions (null, a) = 1 (only letter a)
        // minimum insertions (null, at) = 2 (letter a + letter t)
        // minimum insertions (null, ati) = 3 (letter a + letter t + letter i)

        for (int i2 = 1; i2 <= len2; i2++)
            dp[0][i2] = i2;

        for (int i1 = 1; i1 <= len1; i1++) {
            for (int i2 = 1; i2 <= len2; i2++) {

                if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) {
                    // If a match is found, no alteration (insertion, deletion or replacement)
                    // is required for current i1 and i2.
                    // Hence, the minimum alterations [s1(from 0 to i1-1), s2(from 0 to i2-1)] = 0 +
                    // minimum alterations [s1(from 0 to i1-2), s2(from 0 to i2-2)]

                    dp[i1][i2] = dp[i1 - 1][i2 - 1];

                } else {
                    // If no match is found, 1 alteration is required for current i1 and i2.
                    // Hence, the minimum alterations [s1(from 0 to i1-1), s2(from 0 to i2-1)] = 1 + min(
                    // minimum deletions [s1(from 0 to i1-1), s2(from 0 to i2-1)]--> dp[i1][i2 - 1],
                    // minimum replacements [s1(from 0 to i1-1), s2(from 0 to i2-1)] --> dp[i1 - 1][i2 - 1],
                    // minimum insertions [s1(from 0 to i1-1), s2(from 0 to i2-1)] --> dp[i1 - 1][i2])

                    dp[i1][i2] = 1 + Math.min(dp[i1][i2 - 1], Math.min(dp[i1 - 1][i2 - 1], dp[i1 - 1][i2]));
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println("--------------------s1 = bat, s2 = ati---------------------");
        System.out.println("Result (calculateEDRecursive):" + calculateEDRecursive("bat", "ati", 0, 0));
        System.out.println("Result (calculateEDDP):" + calculateEDDP("bat", "ati"));
        System.out.println();

        System.out.println("--------------------s1 = abdca, s2 = cbda---------------------");
        System.out.println("Result (calculateEDRecursive):" + calculateEDRecursive("abdca", "cbda", 0, 0));
        System.out.println("Result (calculateEDDP):" + calculateEDDP("abdca", "cbda"));
        System.out.println();

        System.out.println("--------------------s1 = passpot, s2 = ppsspqrt---------------------");
        System.out.println("Result (calculateEDRecursive):" + calculateEDRecursive("passpot", "ppsspqrt", 0, 0));
        System.out.println("Result (calculateEDDP):" + calculateEDDP("passpot", "ppsspqrt"));
    }
}
