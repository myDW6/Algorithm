package com.shaodw.datastructure.map;

public interface Map<K, V> {
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V value);

    boolean isEmpty();

    int getSize();
}
