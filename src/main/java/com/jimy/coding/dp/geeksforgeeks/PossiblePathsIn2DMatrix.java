package com.jimy.coding.dp.geeksforgeeks;

/**
 * Created by Jimy on 17-05-2020.
 * <p>
 * Check for possible path in 2D matrix and calculate the number of paths possible.
 * Given a 2D array(m x n). The task is to check if there is any path from top left
 * to bottom right. In the matrix, -1 is considered as blockage (can’t go through
 * this cell) and 0 is considered path cell (can go through it).
 * <p>
 * Note: Top left cell always contains 0
 * <p>
 * Examples:
 * Input : arr[][] = {{ 0, 0, 0, -1, 0},
 * {-1, 0, 0, -1, -1},
 * { 0, 0, 0, -1, 0},
 * {-1, 0, 0, 0, 0},
 * { 0, 0, -1, 0, 0}}
 * Output : Yes
 * <p>
 * Input : arr[][] = {{ 0, 0, 0, -1, 0},
 * {-1, 0, 0, -1, -1},
 * { 0, 0, 0, -1, 0},
 * {-1, 0, -1, 0, 0},
 * { 0, 0, -1, 0, 0}}
 * Output : No
 */
public class PossiblePathsIn2DMatrix {

    /**
     * Approach 1: Checks if a path exists. Time: O(m * n), Space: O(m * n)
     */
    static boolean pathExists(int[][] arr, int rows, int columns) {

        boolean[][] dp = new boolean[rows + 1][columns + 1];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {

                if (i == 1 && j == 1)
                    // corresponding to arr[0][0] = 0
                    dp[i][j] = true;

                else if (arr[i - 1][j - 1] == -1)
                    // for all arr elements equal to -1, i.e not traversable
                    dp[i][j] = false;

                else
                    // if a current cell can be travelled from its top cell or left cell, it is traversable
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        // DPUtils.print2DArray(dp, rows + 1, columns + 1);
        return dp[rows][columns];
    }

    /**
     * Approach 2: Counts the number of paths from origin to destination. If the count > 0, then path exists, else not.
     * Time: O(m * n), Space: O(1) because it calculates and modifies the input array.
     */
    static int countPaths(int[][] arr, int rows, int columns) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (arr[i][j] == -1) {
                    // not traversable cells can be reached in 0 ways.
                    arr[i][j] = 0;

                } else {

                    if (i == 0 && j == 0 && arr[i][j] == 0) {
                        // arr[0][0] can be travelled in just 1 way as it is the first cell to be traversed.
                        arr[i][j] = 1;
                    } else if (i == 0) {
                        // for calculating all cells in the 0th row
                        arr[i][j] = arr[i][j - 1];
                    } else if (j == 0) {
                        // for calculating all cells in the 0th column
                        arr[i][j] = arr[i - 1][j];
                    } else {
                        // all other cell can be reached either from their top or left cell
                        arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                    }
                }
            }
        }
        //DPUtils.print2DArray(arr, rows, columns);
        return arr[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        int[][] arr1 = {{0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, 0, 0, 0},
                {0, 0, -1, 0, 0}};
        System.out.println("Path exists: " + pathExists(arr1, 5, 5));
        System.out.println("Path counts: " + countPaths(arr1, 5, 5));

        int[][] arr2 = {{0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, -1, 0, 0},
                {0, 0, -1, 0, 0}};
        System.out.println("Path exists: " + pathExists(arr2, 5, 5));
        System.out.println("Path counts: " + countPaths(arr2, 5, 5));
    }
}
