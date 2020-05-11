package com.jimy.coding.dp.grokking.longest_common_substring;

/**
 * Created by Jimy on 10-05-2020.
 * <p>
 * Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS). A subsequence is considered bitonic if it is monotonically increasing and then monotonically decreasing.
 * <p>
 * Example 1:
 * <p>
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LBS is {2,3,6,10,1}.
 * Example 2:
 * <p>
 * Input: {4,2,5,9,7,6,10,3,1}
 * Output: 7
 * Explanation: The LBS is {4,5,9,7,6,3,1}.
 * Basic Solution #
 * A basic brute-force solution could be to try finding the Longest Decreasing Subsequences (LDS), starting from every number in both directions. So for every index ‘i’ in the given array, we will do two things:
 * <p>
 * Find LDS starting from ‘i’ to the end of the array.
 * Find LDS starting from ‘i’ to the beginning of the array.
 * LBS would be the maximum sum of the above two subsequences.
 *
 * A DP solution could be to try finding the Longest Increasing Subsequences (LDS),
 * Find LIS starting from 0 to the end of the array.
 * Find LIS starting from end of the array to 0.
 * LBS would be the maximum sum of the above two subsequences.
 */
public class LongestBitonicSubsequence {

    //-------- Recursive Approach------


    /**
     * Time: O(2 ^ n), Space: O(n) ->recursion stack
     */
    private static int calculateLDSRecursive(int[] nums, int currentIndex, int previousIndex) {
        int len = nums.length;

        if (currentIndex == len)
            return 0;

        int l1 = 0;
        if (previousIndex == -1 || nums[previousIndex] > nums[currentIndex]) {
            l1 = 1 + calculateLDSRecursive(nums, currentIndex + 1, currentIndex);
        }
        int l2 = calculateLDSRecursive(nums, currentIndex + 1, previousIndex);
        return Math.max(l1, l2);
    }

    /**
     * Time: O(2 ^ n), Space: O(n) ->recursion stack
     */
    private static int calculateLDSReverseRecursive(int[] nums, int currentIndex, int previousIndex) {

        if (currentIndex < 0) {
            return 0;
        }

        int l1 = 0;
        if (previousIndex == -1 || nums[previousIndex] > nums[currentIndex]) {
            l1 = 1 + calculateLDSReverseRecursive(nums, currentIndex - 1, currentIndex);
        }
        int l2 = calculateLDSReverseRecursive(nums, currentIndex - 1, previousIndex);
        return Math.max(l1, l2);
    }

    /**
     * Time: O(2 ^ n), Space: O(n) ->recursion stack
     */
    static int calculateLBSRecursive(int[] nums) {
        int len = nums.length;
        int max = 0;
        for (int a = 0; a < len; a++) {
            int l1 = calculateLDSRecursive(nums, a, -1); // from a till end of the array
            int l2 = calculateLDSReverseRecursive(nums, a, -1); // from a till beginning of the array
            max = Math.max(max, l1 + l2 - 1);
        }
        return max;
    }

    //----------------------------------

    /**
     * Time: O(n ^ 2), Space: O(n)
     */
    static int calculateLBSDP(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int[] revDp = new int[len];
        int max = 0, revMax = 0;

        // Every element will at least form an LIS of length = 1, i.e. containing only itself.
        for (int a = 0; a < len; a++) {
            dp[a] = 1;
            revDp[a] = 1;
        }

        // calculating LIS from start index to end index
        for (int a = 1; a < len; a++) {
            //int lis = dp[a];
            for (int b = 0; b < a; b++) {

                if (nums[a] > nums[b] && dp[a] <= dp[b]) {
                    dp[a] += 1;
                }
            }
        }
        //DPUtils.print1DArray(dp, len);

        // calculating LIS from end index to start index
        for (int a = len - 2; a >= 0; a--) {
            for (int b = len - 1; b > a; b--) {

                if (nums[a] > nums[b] && revDp[a] <= revDp[b]) {
                    revDp[a] += 1;
                }
            }
        }
        //DPUtils.print1DArray(revDp, len);

        for (int a = 0; a < len; a++) {
            max = Math.max(max, dp[a] + revDp[a] - 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("---------------------{4, 2, 3, 6, 10, 1, 12}-------------------------");
        int[] nums1 = {4, 2, 3, 6, 10, 1, 12};
        System.out.println("Result (calculateLBSRecursive): " + calculateLBSRecursive(nums1));
        System.out.println("Result (calculateLBSDP): " + calculateLBSDP(nums1));
        System.out.println();

        System.out.println("---------------------{4, 2, 5, 9, 7, 6, 10, 3, 1}-------------------------");
        int[] nums2 = {4, 2, 5, 9, 7, 6, 10, 3, 1};
        System.out.println("Result (calculateLBSRecursive): " + calculateLBSRecursive(nums2));
        System.out.println("Result (calculateLBSDP): " + calculateLBSDP(nums2));
    }
}
