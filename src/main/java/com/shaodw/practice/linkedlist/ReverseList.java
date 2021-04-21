package com.shaodw.practice.linkedlist;

/**
 * @Auther: shaodw
 * @Date: 2020/1/29 20:37
 * @Description: 分别实现反转单向链表和反转双向链表的函数
 * 如果链表长度为N，时间复杂度要求为O(N)，额外空间 复杂度要求为O(1)
 */
public class ReverseList {
    public static Node reverseList(Node head){
        Node pre = null;
        Node next;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode next;
        DoubleNode pre = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static class Node{
        int val;
        Node next;

        Node(int val){
            this.val = val;
        }
    }

    private static class DoubleNode{
        int val;
        DoubleNode next;
        DoubleNode last;

        DoubleNode(int val){
            this.val = val;
        }
    }

    private static void printList(Node head){
        while (head != null){
            if (head.next != null){
                System.out.print(head.val + " -> ");
            }else System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }

    private static void printDoubleList(DoubleNode head){
        while (head != null){
            if (head.next != null){
                System.out.print(head.val + " <=> ");
            }else System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        printList(reverseList(head));

        DoubleNode doubleHead = new DoubleNode(1);
        doubleHead.last = null;
        doubleHead.next = new DoubleNode(2);
        doubleHead.next.last = doubleHead;
        doubleHead.next.next = new DoubleNode(3);
        doubleHead.next.next.last = doubleHead.next;
        doubleHead.next.next.next = new DoubleNode(4);
        doubleHead.next.next.next.last = doubleHead.next.next;
        doubleHead.next.next.next.next = null;
        printDoubleList(reverseDoubleList(doubleHead));

    }


}
