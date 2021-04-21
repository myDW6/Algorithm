package com.shaodw.swordoffer;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 22:31
 * @Description: 两个栈实现队列
 */
public class TwoStackQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 必须等2栈空才能从1栈中转移数据 必须一次全部转移
     */
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty() && stack1.isEmpty()){
            throw new RuntimeException("Queue is empty");
        } else if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }


}
