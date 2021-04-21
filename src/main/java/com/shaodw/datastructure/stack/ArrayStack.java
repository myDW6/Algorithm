package com.shaodw.datastructure.stack;

import com.shaodw.datastructure.array.Array;

public class ArrayStack<E> implements Stack<E>{

    private Array<E> array;

    public ArrayStack(int capacity){
        this.array = new Array<>(capacity);
    }

    public ArrayStack(){
        this.array = new Array<>();
    }

    @Override
    public void push(E e) {
        this.array.addLast(e);
    }

    @Override
    public E pop() {
        return this.array.removeLast();
    }

    @Override
    public E peek() {
        return this.array.getLast();
    }

    @Override
    public int getSize() {
        return this.array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    public int getCapacity(){
        return this.array.capacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < this.array.getSize(); i++){
            res.append(this.array.get(i));
            if (i != array.getSize() - 1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
