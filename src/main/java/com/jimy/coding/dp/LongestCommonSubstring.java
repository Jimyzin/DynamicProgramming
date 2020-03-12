package com.jimy.coding.dp;

/**
 * Longest Common Substring
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 * Examples :
 * <p>
 * Input : X = “abcdxyz”, y = “xyzabcd”
 * Output : 4
 * The longest common substring is “abcd” and is of length 4.
 * <p>
 * Input : X = “zxabcdezy”, y = “yzabcdezx”
 * Output : 6
 * The longest common substring is “abcdez” and is of length 6.
 */
public class LongestCommonSubstring {

    static int getLongestCommonSubstringDP(char[] str1, char[] str2) {
        int length1 = str1.length;
        int length2 = str2.length;
        int max = Integer.MIN_VALUE;

        int[][] table = new int[length1 + 1][length2 + 1];

        for(int r = 1; r <= length1; r++) {
            for(int c = 1; c <= length2; c++) {

                if(str1[r - 1] == str2[c - 1]) {
                    table[r][c] = table[r - 1][c - 1] + 1;
                }
            }

            //print2DArray(table, length1 + 1, length2 + 1);
            //System.out.println("------------------------------------------");
        }

        for(int r = 1; r <= length1; r++) {
            for(int c = 1; c <= length2; c++) {

                max = Math.max(max, table[r][c]);
            }
        }

        return max;
    }

    static void print2DArray(int[][] table, int rows, int columns) {
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                System.out.print(table[r][c]+"\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Longest Common Substring: "+ getLongestCommonSubstringDP("abcdxyz".toCharArray(),
                "xyzabcd".toCharArray()));

        System.out.println("Longest Common Substring: "+ getLongestCommonSubstringDP("zxabcdezy".toCharArray(),
                "yzabcdezx".toCharArray()));
    }
}
