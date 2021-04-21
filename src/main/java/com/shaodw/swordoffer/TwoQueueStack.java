package com.shaodw.swordoffer;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 22:58
 * @Description: 反过来 用两个队列实现栈的操作
 */
public class TwoQueueStack {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public void push(int node){
        queue1.add(node);
    }

    public int pop(){
        if (queue1.isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        while (queue1.size() > 1){
            queue2.add(queue1.poll());
        }
        int res = queue1.poll();
        //交换两个队列的引用
        swap();
        return res;
    }

    private void swap(){
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    public static void main(String[] args) {
        TwoQueueStack stack = new TwoQueueStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
