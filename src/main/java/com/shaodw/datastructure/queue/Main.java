package com.shaodw.datastructure.queue;

import java.util.Random;

public class Main {

    private static double testQueue(Queue<Integer> q, int count){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            q.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int count = 500000;
        System.out.println("LoopQueue1: " + testQueue(new LoopQueueAnother<>(), count));
        System.out.println("LoopQueue2: " + testQueue(new LoopQueue<>(), count));
        System.out.println("LinkedListQueue: " + testQueue(new LinkedListQueue<>(), count));
        System.out.println("ArrayQueue: " + testQueue(new ArrayQueue<>(), count));
    }
}
