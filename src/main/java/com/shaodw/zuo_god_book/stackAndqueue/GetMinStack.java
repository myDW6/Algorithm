package com.shaodw.zuo_god_book.stackAndqueue;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/3/13 19:27
 * @Description: 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 *
 * 1.pop、push、getMin操作的时间复杂度都是O（1）。2.设计的栈类型可以使用现成的栈结构。
 */
public class GetMinStack {
    private Stack<Integer> data;
    private Stack<Integer> min;

    public GetMinStack(){
        data = new Stack<>();
        min = new Stack<>();
    }

    public void push(int e){
        data.push(e);
        min.push(min.isEmpty() || e < min.peek() ? e : min.peek());
    }

    public int pop(){
        if (data.isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        min.pop();
        return data.pop();
    }

    public int getMin(){
        if (data.isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        return min.peek();
    }

    public static void main(String[] args) {
        GetMinStack minStack = new GetMinStack();
        minStack.push(5);
        minStack.push(4);
        minStack.push(4);
        minStack.push(5);
        minStack.push(3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

}
