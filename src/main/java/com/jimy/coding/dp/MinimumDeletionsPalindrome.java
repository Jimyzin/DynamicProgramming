package com.jimy.coding.dp;

/**
 * Minimum number of deletions to make a string palindrome
 * Given a string of size ‘n’. The task is to remove or delete minimum number of characters from the string so that the resultant string is palindrome.
 * <p>
 * Note: The order of characters should be maintained.
 * <p>
 * Examples :
 * <p>
 * Input : aebcbda
 * Output : 2
 * Remove characters 'e' and 'd'
 * Resultant string will be 'abcba'
 * which is a palindromic string
 * <p>
 * Input : geeksforgeeks
 * Output : 8
 */
public class MinimumDeletionsPalindrome {

    public static void main(String[] args) {
        String input = "aebcbda";

        //System.out.println("Minimum deletions for "+input+ " through recursion = "+
        //        findMinDeletionsRecursion(input.toCharArray(), 0, input.length() - 1));

        System.out.println("Minimum deletions for "+input+ " through DP = "+
                findMinDeletionsDP(input.toCharArray(), input.length()));
    }

    static int findMinDeletionsRecursion(char[] str, int startIndex, int endIndex) {

        if(startIndex == endIndex)
        return 0;

        if(startIndex == endIndex - 1)
            return str[startIndex] == str[endIndex] ? 0 : 1;

        return str[startIndex] == str[endIndex] ? findMinDeletionsRecursion(str, startIndex + 1, endIndex - 1) :
                (1 + Math.min(findMinDeletionsRecursion(str, startIndex + 1, endIndex), findMinDeletionsRecursion(str, startIndex, endIndex - 1)));
    }

    static int findMinDeletionsDP(char[] str, int n) {

        int[][] table = new int[n][n];

        for(int gap = 1; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; j++, i++) {

                if(str[i] == str[j]) {
                    table[i][j] = table[i + 1][j - 1];
                } else {
                    table[i][j] = 1 + Math.min(table[i][j - 1], table[i + 1][j]);
                }

                printIt(table, n);
                System.out.println("-------------------------------------");
            }

            System.out.println("-------------------------------------");
        }

        return table[0][n - 1];
    }

    static void printIt(int[][] table, int n) {
        for(int a = 0; a < n; a++) {
            for(int b = 0; b < n; b++) {
                System.out.print(table[a][b] +"\t");
            }
            System.out.println();
        }
    }
}
