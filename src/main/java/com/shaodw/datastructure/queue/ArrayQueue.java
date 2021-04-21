package com.shaodw.datastructure.queue;

import com.shaodw.datastructure.array.Array;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(){
        this.array = new Array<>();
    }

    public ArrayQueue(int capacity){
        this.array = new Array<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        this.array.addLast(e);
    }

    @Override
    public E dequeue() {
        return this.array.removeFirst();
    }

    @Override
    public E getFront() {
        return this.array.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public int getSize() {
        return this.array.getSize();
    }

    public int getCapacity(){
        return this.array.capacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Front [");
        for (int i = 0; i < this.array.getSize(); i++){
            res.append(this.array.get(i));
            if (i != this.array.getSize() - 1){
                res.append(",");
            }
        }
        res.append("] Tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            if (i % 3 == 1){
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
