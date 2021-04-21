package com.shaodw.zuo_god_book.stackAndqueue;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/3/13 19:47
 * @Description: 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）
 */


/**
 * 1如果stackPush要往stackPop中压入数据，那么必须一次性把stackPush中的数据全部压入。
 * 2.如果stackPop不为空，stackPush绝对不能向stackPop中压入数据。违反了以上两点都会发生错误。
 */
public class TwoStackQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public TwoStackQueue(){
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void add(int e){
        stack1.push(e);
    }

    public int poll(){
        if (stack2.isEmpty() && stack1.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        if (stack2.isEmpty()){
            transfer();
        }
        return stack2.pop();
    }

    public int peek(){
        if (stack2.isEmpty() && stack1.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        if (stack2.isEmpty()){
            transfer();
        }
        return stack2.peek();
    }

    private void transfer(){
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
