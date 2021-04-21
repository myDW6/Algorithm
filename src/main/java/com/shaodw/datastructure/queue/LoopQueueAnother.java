package com.shaodw.datastructure.queue;

/**
 * 以取模的方法实现循环队列
 */
public class LoopQueueAnother<E> implements Queue<E> {

    private E[] array;
    private int front;
    private int tail;
    private int size;

    public LoopQueueAnother(int capacity){
        this.array = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueueAnother(){
        this(10);
    }

    public int capacity(){
        return array.length - 1;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % array.length == front){
            resize(capacity() * 2);
        }
        array[tail] = e;
        tail = (tail + 1) % array.length;
        size++;
    }

    private void resize(int newCapacity){
        E[] tmp = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++){
            tmp[i] = array[(i + front)%array.length];
        }
        array = tmp;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from a empty queue");
        }
        E ret = array[front];
        array[front] = null;
        front = (front + 1)% array.length;
        size--;
        if (size == capacity() / 4 && capacity() / 2 != 0){
            resize(capacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue is empty");
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
        LoopQueueAnother<Integer> queue = new LoopQueueAnother<>(15);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            if (i % 3 == 1){
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
