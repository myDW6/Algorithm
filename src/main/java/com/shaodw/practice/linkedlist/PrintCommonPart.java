package com.shaodw.practice.linkedlist;

/**
 * @Auther: shaodw
 * @Date: 2020/1/29 22:04
 * @Description: 打印两个有序链表的公共部分
 * 利用外排的思想解决
 */
public class PrintCommonPart {
    public static void printCommonPart(Node head1, Node head2){
        System.out.println("Common part: ");
       while (head1 != null && head2 != null){
           if (head1.val == head2.val) {
               System.out.print(head1.val + " ");
               head1 = head1.next;
               head2 = head2.next;
           }else if (head1.val < head2.val){
               head1 = head1.next;
           }else head2 = head2.next;
       }
    }

    public static class Node{
        int val;
        Node next;

        Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(6);

        Node head2 = new Node(1);
        head2.next = new Node(3);
        head2.next.next = new Node(4);
        head2.next.next.next = new Node(6);

        printCommonPart(head1, head2);
    }
}
