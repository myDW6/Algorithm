package com.shaodw.practice.treeDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/24 11:16
 * @Description: 打印整棵树的最大值和最小值
 */
public class MaxMinInTree {

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.right = new Node(3);
        head.right.right = new Node(1);
        printMaxAndMin(head);
    }

    public static void printMaxAndMin(Node head){
        if (head == null){
            return;
        }
        ReturnData res = process(head);
        System.out.println("max -> " + res.max);
        System.out.println("min -> " + res.min);
    }


    /**
     * 每一个节点最大值可能来自于你左树的最大和右树的最大和你
     * 每一个节点最小值可能来自于你左树的最小和右树的最小和你
     * @param head
     * @return
     */
    private static ReturnData process(Node head){
        if (head == null){
            return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ReturnData leftInfo = process(head.left);
        ReturnData rightInfo = process(head.right);
        return new ReturnData(
                Math.max(Math.max(leftInfo.max, rightInfo.max), head.val),
                Math.min(Math.min(leftInfo.min, rightInfo.min), head.val)
        );
    }

    private static class ReturnData{
        int max;
        int min;

        ReturnData(int max, int min){
            this.max = max;
            this.min = min;
        }
    }

    private static class Node{
        Node left;
        Node right;
        int val;

        Node(int val){
            this.val = val;
        }
    }
}
