package com.shaodw.practice.hash;

import java.util.HashMap;

/**
 * @Auther: shaodw
 * @Date: 2020/2/3 15:53
 * @Description: 设计RandomPool结构
 * 设计一种结构，在该结构中有如下三个功能：
 * insert(key)：将某个key加入到该结构，做到不重复加入。
 * delete(key)：将原本在结构中的某个key移除。
 * getRandom()： 等概率随机返回结构中的任何一个key。
 * 【要求】 Insert、delete和getRandom方法的时间复杂度都是 O(1)
 */
public class RandomPool<E> {
    private HashMap<E, Integer> keyIndexMap;
    private HashMap<Integer, E> indexKeyMap;
    private int size;

    public RandomPool(){
        keyIndexMap = new HashMap<>();
        indexKeyMap = new HashMap<>();
        size = 0;
    }

    public void insert(E key){
        if (!keyIndexMap.containsKey(key)){
            keyIndexMap.put(key, size);
            indexKeyMap.put(size++, key);
        }
    }

    public void delete(E key){
        if (keyIndexMap.containsKey(key)){
            int deleteIndex = keyIndexMap.get(key);
            int lastIndex = --size;
            E last = indexKeyMap.get(lastIndex);
            keyIndexMap.put(last, deleteIndex);
            indexKeyMap.put(deleteIndex, last);
            keyIndexMap.remove(key);
            indexKeyMap.remove(lastIndex);
        }
    }

    public E getRandom(){
        return this.size == 0 ? null : indexKeyMap.get((int)(Math.random() * size));
    }

    public static void main(String[] args) {
        RandomPool<String> pool = new RandomPool<>();
        pool.insert("shao");
        pool.insert("duan");
        pool.insert("wu");
        for (int i = 0; i < 100; i++) {
            System.out.print(pool.getRandom() + " ");
        }
        pool.delete("shao");
        System.out.println("\nwill not come true shao");
        for (int i = 0; i < 100; i++) {
            System.out.print(pool.getRandom() + " ");
        }
    }
}
