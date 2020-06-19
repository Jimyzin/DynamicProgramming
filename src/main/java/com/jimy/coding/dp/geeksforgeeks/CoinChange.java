package com.jimy.coding.dp.geeksforgeeks;

/**
 * Created by Jimy on 19-06-2020.
 * <p>
 * Coin Change | DP-7 https://www.geeksforgeeks.org/coin-change-dp-7/
 * Given a value N, if we want to make change for N cents,
 * and we have infinite supply of each of S = { S1, S2, .. , Sm}
 * valued coins, how many ways can we make the change? The order of coins doesn’t matter.
 * <p>
 * For example, for N = 4 and S = {1,2,3},
 * there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
 * So output should be 4.
 * <p>
 * For N = 10 and S = {2, 5, 3, 6},
 * there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5}
 * and {5,5}. So the output should be 5.
 */
public class CoinChange {

    /**
     * Time: O( s.length x n), Space: O( s.length x n)
     */
    static int countWays(int n, int[] s) {

        int len = s.length;
        int[][] dp = new int[len][n + 1];

        // Calculates combinations using just s[0]. So, the total number of combinations
        // can be either 1 (if s[0] can be used) or 0 (if s[0] cannot be used).
        for (int a = 1; a <= n; a++) {
            if (a % s[0] == 0) {
                dp[0][a] = 1;
            }
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= n; j++) {

                if (s[i] < j) {
                    // This condition means s[i] will be used to form new combinations.
                    // total number of combinations = other combinations to j by s[0]....s[i - 1] +
                    // combinations to form (j - s[i]) using s[0].....s[i]
                    dp[i][j] = dp[i - 1][j] + dp[i][j - s[i]];

                } else if (s[i] > j) {
                    // This condition means that s[i] cannot be used to form j.
                    // As s[i] is useless, so the total number of combinations = other combinations to j by s[0]....s[i - 1]
                    dp[i][j] = dp[i - 1][j];

                } else {
                    // if s[i] == j, it means 1 new combination to form j.
                    // In other words, 1 is added to other combinations to j by s[0]....s[i - 1]
                    dp[i][j] = 1 + dp[i - 1][j];
                }
            }

            //DPUtils.print2DArray(dp, len, n + 1);
        }

        return dp[len - 1][n];
    }

    /**
     * Time: O( s.length x n), Space: O(n)
     */
    static int countWaysSpaceOptimised(int n, int[] s) {

        int len = s.length;
        int[] dp = new int[n + 1];

        // Calculates combinations using just s[0]. So, the total number of combinations
        // can be either 1 (if s[0] can be used) or 0 (if s[0] cannot be used).
        for (int a = 1; a <= n; a++) {
            if (a % s[0] == 0) {
                dp[a] = 1;
            }
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= n; j++) {

                if (s[i] < j) {
                    // This condition means s[i] will be used to form new combinations.
                    // total number of combinations = other combinations to j by s[0]....s[i - 1] +
                    // combinations to form (j - s[i]) using s[0].....s[i]
                    dp[j] = dp[j] + dp[j - s[i]];

                } else if (s[i] > j) {
                    // This condition means that s[i] cannot be used to form j.
                    // As s[i] is useless, so the total number of combinations = other combinations to j by s[0]....s[i - 1]
                    dp[j] = dp[j];

                } else {
                    // if s[i] == j, it means 1 new combination to form j.
                    // In other words, 1 is added to other combinations to j by s[0]....s[i - 1]
                    dp[j] = 1 + dp[j];
                }
            }

            //DPUtils.print2DArray(dp, len, n + 1);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("----------------- n = 4, s[] = {1, 2, 3}---------------");
        System.out.println("countWays = " + countWays(4, new int[]{1, 2, 3}));
        System.out.println("countWaysSpaceOptimised = " + countWaysSpaceOptimised(4, new int[]{1, 2, 3}));
        System.out.println();

        System.out.println("---------------- n = 10, s[] = {2, 5, 3, 6}-------------");
        System.out.println("countWays = " + countWays(10, new int[]{2, 5, 3, 6}));
        System.out.println("countWaysSpaceOptimised = " + countWaysSpaceOptimised(10, new int[]{2, 5, 3, 6}));
    }
}
