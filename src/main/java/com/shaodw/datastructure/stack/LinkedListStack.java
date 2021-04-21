package com.shaodw.datastructure.stack;

import com.shaodw.datastructure.linkedlist.DummyHeadLinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private DummyHeadLinkedList<E> linkedList;

    public LinkedListStack(){
        this.linkedList = new DummyHeadLinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top: ");
        res.append(linkedList);
        return res.toString();
    }
}
