package com.shaodw.datastructure.tree.heap.leetcode;

import java.util.*;

/**
 * 代码简化
 */
public class Solution3 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums){
            if (map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else map.put(num, 1);
        }

        /*PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });*/
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        for (int key : map.keySet()){
            if (priorityQueue.size() < k){
                priorityQueue.add(key);
            }else if (map.get(priorityQueue.peek()) < map.get(key)){
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        List<Integer> list = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            list.add(priorityQueue.remove());
        }
        return list;
    }
}
