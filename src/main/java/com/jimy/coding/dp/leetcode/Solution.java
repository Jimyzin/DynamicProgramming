package com.jimy.coding.dp.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public static int maxEvents(int[][] events) {

        Arrays.sort(events, (e1, e2) -> Integer.compare(e1[0], e2[0]));
        int eventIndex = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int day = Integer.MIN_VALUE;
        int result = 0;
        while (eventIndex != events.length || !pq.isEmpty()) {
            while (eventIndex != events.length && events[eventIndex][0] == day) {
                pq.offer(events[eventIndex][1]);
                ++eventIndex;
            }

            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                pq.poll();
                ++day;
                ++result;
            } else if (eventIndex != events.length) {
                day = events[eventIndex][0];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] events = new int[][]{{1,2},{2,3},{3,4},{1,2}};
        System.out.println(maxEvents(events));
    }
}
