package com.shaodw.datastructure.tree.heap.leetcode;
/**
 * leetcode 347 Top K Frequent Elements
 * 非空的整型数组中 返回出现频率前k高的元素
 */

import com.shaodw.datastructure.tree.heap.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    private class Freq implements Comparable<Freq>{
        int e, freq;//定义元素值和出现的频次

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq)
                return -1;
            else return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums){
            if (map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else map.put(num, 1);
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for (int key : map.keySet()){
            if (priorityQueue.getSize() < k){
                priorityQueue.enqueue(new Freq(key, map.get(key)));
            }else if (priorityQueue.getFront().freq < map.get(key)){
                priorityQueue.dequeue();
                priorityQueue.enqueue(new Freq(key, map.get(key)));
            }
        }

        List<Integer> list = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            list.add(priorityQueue.dequeue().e);
        }
        return list;
    }
}
