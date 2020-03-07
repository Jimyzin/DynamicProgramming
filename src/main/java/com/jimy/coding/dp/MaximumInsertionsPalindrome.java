package com.jimy.coding.dp;

/**
 * Minimum insertions to form a palindrome
 * Given a string str, the task is to find the minimum number of characters to be inserted to convert it to palindrome.
 * Before we go further, let us understand with few examples:
 * <p>
 * ab: Number of insertions required is 1 i.e. bab
 * aa: Number of insertions required is 0 i.e. aa
 * abcd: Number of insertions required is 3 i.e. dcbabcd
 * abcda: Number of insertions required is 2 i.e. adcbcda which is same as number of insertions in the substring bcd(Why?).
 * abcde: Number of insertions required is 4 i.e. edcbabcde
 */

public class MaximumInsertionsPalindrome {

    /**
     * A recursive approach to find minimum insertions in a string to make it palindromic
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    static int findMinInsertionsRecursive(char[] str, int start, int end) {

        if(start > end)
            return Integer.MAX_VALUE;

        if(start == end)
        return 0;

        if(start == end - 1)
            return start == end ? 0 : 1;

        return str[start] == str[end] ? findMinInsertionsRecursive(str, start + 1, end - 1) :
                (1 + (Math.min(findMinInsertionsRecursive(str, start + 1, end), findMinInsertionsRecursive(str, start, end - 1))));
    }

    static int findMinInsertionsDP(char str[], int n)
    {
        // Create a table of size n*n. table[i][j]
        // will store minimum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int table[][] = new int[n][n];
        int l, h, gap;

        // Fill the table
        for (gap = 1; gap < n; ++gap) {
            for (l = 0, h = gap; h < n; ++l, ++h) {
                table[l][h] = (str[l] == str[h]) ?
                        table[l + 1][h - 1] :
                        (Integer.min(table[l][h - 1],
                                table[l + 1][h]) + 1);

                //printIt(table, n);
                //System.out.println("-------------------------------------");
            }
            //System.out.println("-------------------------------------");
        }

        // Return minimum number of insertions
        // for str[0..n-1]
        return table[0][n-1];
    }

    static void printIt(int[][] table, int n) {
        for(int a = 0; a < n; a++) {
            for(int b = 0; b < n; b++) {
                System.out.print(table[a][b] +"\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        String input = "abcd";
        System.out.println("Minimum insertions for "+input+ " through recursion = "+ findMinInsertionsRecursive(input.toCharArray(), 0, input.length() - 1));
        System.out.println("Minimum insertions for "+input+ " through DP = "+ findMinInsertionsDP(input.toCharArray(), input.length()));

        input = "abcda";
        System.out.println("Minimum insertions for "+input+ " through recursion = "+ findMinInsertionsRecursive(input.toCharArray(), 0, input.length() - 1));
        System.out.println("Minimum insertions for "+input+ " through DP = "+ findMinInsertionsRecursive(input.toCharArray(), 0, input.length() - 1));
    }
}
