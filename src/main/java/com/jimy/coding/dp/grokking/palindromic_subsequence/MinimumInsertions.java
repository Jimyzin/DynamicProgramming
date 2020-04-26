package com.jimy.coding.dp.grokking.palindromic_subsequence;

/**
 * Created by Jimy on 26-04-2020.
 */
public class MinimumInsertions {

    /**
     * Minimum insertions is exactly the same as minimum deletions.
     */
    public static void main(String[] args) {
        System.out.println("--------------abdbca----------------");
        System.out.println("Result (MinimumDeletions.calculateMinimumDeletionsDP):" + MinimumDeletions.calculateMinimumDeletionsDP("abdbca"));
        System.out.println();

        System.out.println("--------------cddpd----------------");
        System.out.println("Result (MinimumDeletions.calculateMinimumDeletionsDP):" + MinimumDeletions.calculateMinimumDeletionsDP("cddpd"));
        System.out.println();

        System.out.println("--------------pqr----------------");
        System.out.println("Result (MinimumDeletions.calculateMinimumDeletionsDP):" + MinimumDeletions.calculateMinimumDeletionsDP("pqr"));
    }
}
