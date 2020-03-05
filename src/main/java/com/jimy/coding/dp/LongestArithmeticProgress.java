package com.jimy.coding.dp;

import java.util.*;

public class LongestArithmeticProgress {

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10};
        int l = arr.length;
        Map<Integer, Integer> map = new Hashtable<>();

        for(int a = 0; a < l; a++) {
            for(int b = a + 1; b < l; b++) {
                int diff = arr[b] - arr[a];
                Integer t  = map.get(diff);
                if(t == null) {
                    map.put(diff, 1);
                } else {
                    map.put(diff, t + 1);
                }
            }
        }

        List<Integer> values = new LinkedList<>(map.values());
        Collections.sort(values);

        /*Set<Integer> keySet = map.keySet();
        Iterator<Integer> i = keySet.iterator();
        int max = Integer.MIN_VALUE;
        while(i.hasNext()) {
            int t = map.get(i.next());
            if(t >  max) {
                max = t;
            }
        }*/

        System.out.println(values.get(values.size() - 1) + 1);
    }
}
