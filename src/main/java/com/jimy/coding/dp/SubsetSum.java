package com.jimy.coding.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubsetSum {

    static boolean isSubsetSum(int[] set, int sum) {
        int l = set.length;
        boolean[][] subset = new boolean[l + 1][sum + 1];

        for(int a = 0; a <= l; a++) {
            subset[a][0] = true;
        }

        for(int i = 1; i <=l; i++) {
            for(int j = 1; j <= sum; j++) {
                if(set[i - 1] > j) {
                    subset[i][j] = subset[i - 1][j];
                } else {
                    subset[i][j] = subset[i - 1][j] || subset[i - 1][j - set[i - 1]];
                 }
            }
        }

        /*for(int a = 0; a <= l; a++) {
            for(int b = 0; b <= sum; b++) {
                System.out.print(subset[a][b]+"\t");
            }
            System.out.println();
        }*/


        //List<Integer> elements = new ArrayList<Integer>();
        if(subset[l][sum]) {
            int c = sum, r = l;
            while(c > 0) {

                while(subset[r][c]) {
                    r -= 1;
                }
                //elements.add(set[r]);
                System.out.print(set[r]+"\t");
                c -= set[r];
                r -= 1;
            }
        }
        System.out.println();

        return subset[l][sum];

    }

    public static void main(String[] args) {
        int[] set = {2, 3, 7, 8, 10, 1};
        int sum = 23;
        boolean result = isSubsetSum(set, sum);

        System.out.println("----------------------------------------");
        System.out.println("Result: "+result);
        System.out.println("----------------------------------------");
    }
}
