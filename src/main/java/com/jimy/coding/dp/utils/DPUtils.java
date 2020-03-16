package com.jimy.coding.dp.utils;

/**
 * Created by User on 16-03-2020.
 */
public class DPUtils {

    public static void print2DArray(int[][] arr, int rows, int columns) {

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                System.out.print(arr[r][c]+"\t");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
    }
}
