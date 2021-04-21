package com.shaodw.datastructure.hash;

import java.util.TreeMap;

public class HashTable<K, V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};//1610612741比较接近int存储极限的素数
    private static final int upperTol = 10;//平均每个地址承载的元素个数的上界
    private static final int lowerTol = 2;//平均每个地址承载的元素个数的下界
    private int capacityIndex = 0;//哈希表的初始容量对应容量表的索引
    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

    public HashTable(){
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    //将key转化为hashtable[]中的索引
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);//修改
        }else {
            map.put(key, value);
            size ++;
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length){
                resize(capacity[++capacityIndex]);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)){
            ret = map.remove(key);
            size --;
            if (size < lowerTol * M && capacityIndex > 0){
                resize(capacity[--capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K,V> map = hashtable[hash(key)];
        if (!map.containsKey(key)){
            throw new IllegalArgumentException(key + " doesn't exist");
        }
        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM){
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()){
                newHashTable[hash(key)].put(key, map.get(key));//此处需要注意：hash函数中使用了M  (key.hashCode() & 0x7fffffff) % M
                //M 表示哈希表开辟的大小   但此时hash表为新的表 需要 % newM才对  所以需要this.M = newM;
                //但是for循环中又需要遍历原来哈希表数组的大小 所以需要暂存M   int oldM = M;
            }
        }

        this.hashtable = newHashTable;
    }
}
