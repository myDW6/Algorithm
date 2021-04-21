package com.shaodw.datastructure.stack;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testArrayStack();
        System.out.println("====");
        testLinkedListStack();

        System.out.println("=========");
        System.out.println(testStack(new ArrayStack<>(), 10000000));
        System.out.println(testStack(new LinkedListStack<>(), 10000000));//linkedlist有着更多的new操作  时间比较很复杂
    }

    //测试使用stack运行count次push和pop的时间 单位为秒
    private static double testStack(Stack<Integer> stack, int count){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < count; i++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++){
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static void testLinkedListStack(){
        Stack<Integer> linkedListStack = new LinkedListStack<>();

        for (int i = 0; i < 6; i++){
            linkedListStack.push(i);
            System.out.println(linkedListStack);
        }

        linkedListStack.pop();
        System.out.println(linkedListStack);

        linkedListStack.peek();
        System.out.println(linkedListStack);
    }

    private static void testArrayStack(){
        Stack<Integer> arratStack = new ArrayStack<>();

        for (int i = 0; i < 6; i++){
            arratStack.push(i);
            System.out.println(arratStack);
        }

        arratStack.pop();
        System.out.println(arratStack);

        arratStack.peek();
        System.out.println(arratStack);
    }
}
