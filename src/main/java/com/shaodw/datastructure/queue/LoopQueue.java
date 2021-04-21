package com.shaodw.datastructure.queue;

/**
 * 提供两种实现  主要在于first和tail的索引变化不同
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] array;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        this.array = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % array.length == front){
            resize(capacity() * 2);
        }
        array[tail] = e;
        size++;
        tail = tail + 1 == array.length ? 0 : tail + 1;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("cannot remove from a empty queue");
        }
        E e = array[front];
        array[front] = null;
        front = front + 1 == array.length ? 0 : front + 1;
        size--;
        if (size == capacity() / 4 && capacity() / 2 != 0) {
            resize(capacity() / 2);
        }
        return e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("cannot get from a empty queue");
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int capacity(){
        return array.length - 1;
    }

    private void resize(int newCapacity){
        E[] tmp = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++){
            tmp[i] = array[(front + i) % array.length];
        }
        array = tmp;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue: size = %d, capacity = %d\n", size, capacity()));
        builder.append("front [");
        for (int i = front; i != tail; i = (i + 1)%array.length){
            builder.append(array[i]);
            if ((i + 1)%array.length != tail){
                builder.append(",");
            }
        }
        return builder.append("]").toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>(15);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            if (i % 3 == 1){
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
