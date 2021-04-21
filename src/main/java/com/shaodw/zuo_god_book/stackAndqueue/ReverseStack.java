package com.shaodw.zuo_god_book.stackAndqueue;

/**
 * @Auther: shaodw
 * @Date: 2020/3/13 20:08
 * @Description: 如何仅用递归函数和栈操作逆序一个栈
 * 个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 将这个栈转置后，从栈顶到栈底为 1、2、3、4、5，也就是实现栈中元素的逆序，但是只能用递归函数来实现，不能用其他数据结构。
 */

import java.util.Stack;

/**
 * 递归函数一：将栈stack的栈底元素返回并移除。
 * 递归函数二：逆序一个栈
 */
public class ReverseStack {
    public static int getBottomAndRemove(Stack<Integer> stack){
        int cur = stack.pop();
        if (stack.isEmpty()){
            return cur;
        }else {
            int bottom = getBottomAndRemove(stack);
            stack.push(cur);
            return bottom;
        }
    }

    public static void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int bottom = getBottomAndRemove(stack);
        reverseStack(stack);
        stack.push(bottom);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.stream().forEach((s)->System.out.print(s + " "));
        System.out.println();
        reverseStack(stack);
        stack.stream().forEach((s)->System.out.print(s + " "));

    }
}
