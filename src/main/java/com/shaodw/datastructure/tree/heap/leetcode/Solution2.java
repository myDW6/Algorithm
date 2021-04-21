package com.shaodw.datastructure.tree.heap.leetcode;

import java.util.*;

/**
 * 使用java标准库提供的优先队列解决leetcode347
 *  java默认是最小堆
 */
public class Solution2 {
    private class Freq {
        int e, freq;//定义元素值和出现的频次

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }
    }

//    private class FreqCompartor implements Comparator<Freq>{
//
//        @Override
//        public int compare(Freq o1, Freq o2) {
//            return o1.freq - o2.freq;
//        }
//    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums){
            if (map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else map.put(num, 1);
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>((o1,o2)->o1.freq - o2.freq);
        for (int key : map.keySet()){
            if (priorityQueue.size() < k){
                priorityQueue.add(new Freq(key, map.get(key)));
            }else if (priorityQueue.peek().freq < map.get(key)){
                priorityQueue.remove();
                priorityQueue.add(new Freq(key, map.get(key)));
            }
        }

        List<Integer> list = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            list.add(priorityQueue.remove().e);
        }
        return list;
    }
}
