package com.shaodw.practice.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: shaodw
 * @Date: 2020/1/31 22:47
 * @Description: 两个单链表相交的一系列问题
 * 在本题中，单链表可能有环，也可能无环。
 * 给定两个 单链表的头节点 head1和head2，这两个链表可能相交，也可能 不相交。
 * 请实现一个函数， 如果两个链表相交，请返回相交的 第一个节点；如果不相交，返回null 即可。
 * 要求：如果链表1 的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外 空间复杂度请达到O(1)。
 */
public class FindFirstIntersectNode {
    /**
     * 此题比较难 分别考察了单链表有无环 并求入环节点 且还要考虑两个有/无环单链表 相交的一系列情况
     */



    /**
     * 检验单链表是否有环 若有环返回第一个入环节点 返回null代表无环
     * 分别使用HashSet和快慢指针算法求
     */

    private static Node isLoopUseSet(Node head){
        if (head.next == null || head.next.next == null) //调用函数中已经有了head判断空的逻辑
            return null;
        Set<Node> set = new HashSet<>();
        while (head != null){
            if (set.contains(head)){
               return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 快慢指针 快指针走两步 慢指针走一步 如果快指针走到null 无环 否则当快慢指针相遇时 快指针回到开头重新开始走 一次一步
     * 快慢指针最终会在入环节点相遇
     * @return
     */
    private static Node isLoop(Node head){
       if (head.next == null || head.next.next == null)
           return null;
       Node fast = head;
       Node slow = head;
       while (fast != null && fast.next != null){
           fast = fast.next.next;
           slow = slow.next;
           if (slow == fast)
               break;
       }
       if (fast == null || fast.next == null){
           return null;
       }
       //是由于相遇导致的退出循环
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 对于两个无环单链表
     * 采用HashSet先存储其中一条链表所有节点
     * 遍历另一条链表 在set中检索
     */
    private static Node noLoopUseSet(Node head1, Node head2){
       /*if (head1 == null || head2 == null){  /会在主函数中判断 保证两条链表不为空
           return null;
       }*/
       Set<Node> set = new HashSet<>();
       while (head1 != null){
           set.add(head1);
           head1 = head1.next;
       }

       while (head2 != null){
           if (set.contains(head2)){
               return head2;
           }
           head2 = head2.next;
       }

       return null;
    }

    /**
     * 两条无环单链表还有一种方法处理
     * 分别记录两条链表的长度和最后一个节点
     * 如果最后一个节点不等 说明一定不相交
     * 若相等 让长的先走两者差值步  再同时开始走 相等即使相交点
     */
    private static Node noLoop(Node head1, Node head2){
       /* if (head1 == null || head2 == null){ //会在主函数中判断 保证两条链表不为空
            return null;
        }*/
        Node n1 = head1;
        Node n2 = head2;
        int step = 0;
        while (n1.next != null){
            step++;
            n1 = n1.next;
        }

        while (n2.next != null){
            step--;
            n2 = n2.next;
        }
        if (n1 != n2){
            return null;
        }
        n1 = step > 0 ? head1 : head2;
        n2 = n1 == head1 ? head2 : head1;
        step = Math.abs(step);

        while (step-- > 0){
            n1 = n1.next;
        }

        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1;
    }

    /**
     * 如果是一个有环的单链表和一个无环的单链表 是不可能相交的
     */
    /**
     * 那么只剩两个有环链表的情况 一共有三种拓扑结构
     * 1 两个有环链表不相交
     * 2 两个有环链表相交后共用一个环
     * 3 两条链表接入到环上不同点
     */
    public static Node findFirstIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null)
            return null;
        Node loop1 = isLoop(head1);
        Node loop2 = isLoop(head2);
        if (loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null){ //说明是两个有环链表的三种情况
            if (loop1 == loop2){
                //先相交后共用一个环 复用两条单链表相交求相交点的逻辑
                Node n1 = head1;
                Node n2 = head2;
                int step = 0;
                while (n1 != loop1){
                    step++;
                    n1 = n1.next;
                }
                while (n2 != loop2){
                    step--;
                    n2 = n2.next;
                }
                n1 = step > 0 ? head1 : head2;
                n2 = n1 == head1 ? head2 : head1;
                step = Math.abs(step);
                while (step-- > 0){
                    n1 = n1.next;
                }
                while (n1 != n2){
                    n1 = n1.next;
                    n2 = n2.next;
                }
                return n1;
            }else{
                /**
                 * 两个无环链表没有交点或者有两个不同的交点
                 * 让loop1接着走 走到自身都没遇见loop2 说明不相交
                 */

                Node cur = loop1.next;
                while (cur != loop1){
                    if (cur == loop2){
                        return loop2;
                    }
                    cur = cur.next;
                }
                return null;
            }
        }
        //一个有环一个无环不成立
        return null;
    }


    public static class Node{
        int val;
        Node next;

        Node(int val){
            this.val = val;
        }
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).val);
        //返回4或6都对
    }
}
