package com.jimy.coding.dp;

/**
 * Input : dcbabcz
 * Output: cbabc, length: 5
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String input = "dscbabcsz";
        int output = getLongestPalindromicSubstring(input);
        System.out.println(output);
}

    static int getLongestPalindromicSubstring(String input) {

        int l = input.length();
        int max = Integer.MIN_VALUE;

        for(int a = 0; a < l; a++) {
            int len = Math.min(a, l - a - 1);
            int index = 1;

            while(index <= len) {
                char left = input.charAt(a - index);
                char right = input.charAt(a + index);

                if(left != right)
                    break;

                index++;
            }

            if(index - 1 > max) {
                max = index - 1;
            }
        }
        return (2 * max) + 1;
    }
}
