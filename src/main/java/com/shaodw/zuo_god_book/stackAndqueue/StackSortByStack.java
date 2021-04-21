package com.shaodw.zuo_god_book.stackAndqueue;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/3/13 21:53
 * @Description: 用一个栈实现另一个栈的排序
 * 一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。
 * 除此之外，可以申请新的变量，但不能申请额外的数据结构。如何完成排序？
 */
public class StackSortByStack {
    public static void stackSort(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()){
            int cur = stack.pop();
            while (!help.isEmpty() && cur > help.peek()){
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(5);
        stack.push(4);
        stack.push(1);
        stack.push(3);
        stackSort(stack);
        stack.stream().forEach(System.out::println);
    }
}
