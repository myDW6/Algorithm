package com.shaodw.datastructure.set;

import com.shaodw.datastructure.linkedlist.DummyHeadLinkedList;

/**
 * 链表和二叉搜索树都是动态数据结构 用来比较二者性能
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {

    private DummyHeadLinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new DummyHeadLinkedList<>();
    }

    //O(N)
    @Override
    public void add(E e) {
        if (!linkedList.contains(e)){
            linkedList.addFirst(e);
        }
    }

    //O(N)
    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    //O(N)
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
