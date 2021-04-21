package com.shaodw.datastructure.map.leetcode;

import java.util.*;

/**
 * leetcode349 Given two arrays, write a function to compute their intersection. 不考虑重复元素
 * leetcode350 Given two arrays, write a function to compute their intersection. 考虑重复元素
 */
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Set<Integer> set = new TreeSet<>();
        for (int i : nums1){
            set.add(i);
        }
        for (int i : nums2){
            if (set.contains(i)) {
                arrayList.add(i);
                set.remove(i);
            }
        }
        int[] res = new int[arrayList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = arrayList.get(i);
        }
        return res;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new TreeMap<>();
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i : nums1){
            if (map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else map.put(i,1);
        }
        for (int i : nums2){
            if (map.containsKey(i)){
                arrayList.add(i);
                map.put(i, map.get(i) - 1);
                if (map.get(i) == 0){
                    map.remove(i);
                }
            }
        }
        int[] res = new int[arrayList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = arrayList.get(i);
        }
        return res;

    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] nums = new Solution().intersection(nums1, nums2);
        for (int i : nums){
            System.out.println(i);
        }
        System.out.println("============");
        int[] numss = new Solution().intersect(nums1,nums2);
        for (int i : numss){
            System.out.println(i);
        }
    }
}
