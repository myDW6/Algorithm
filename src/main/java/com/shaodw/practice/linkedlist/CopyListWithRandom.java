package com.shaodw.practice.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shaodw
 * @Date: 2020/1/31 21:07
 * @Description: 复制含有随机指针节点的链表
 * Node类中的value是节点值，next指针和正常单链表中next指针的意义 一 样，
 * 都指向下一个节点，rand指针是Node类中新增的指针，这个指 针可 能指向链表中的任意一个节点，也可能指向null。给定一个由 Node节点类型组成的无环单链表的头节点head，
 * 请实现一个 函数完成 这个链表中所有结构的复制，并返回复制的新链表的头节点。
 * 进阶： 不使用额外的数据结构，只用有限几个变量，且在时间复杂度为 O(N) 内完成原问题要实现的函数。
 */
public class CopyListWithRandom {

    /**
     * 笔试时选择使用辅助空间可以很方便解决这个问题
      */

    public static Node copyListUseMap(Node head){
        if (head == null)
            return null;

        Map<Node, Node> copyNodeMap = new HashMap<>();//存放当前节点节点 与其拷贝节点的映射
        Node cur = head;
        while (cur != null){
            copyNodeMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null){
            Node tmp = copyNodeMap.get(cur);
            tmp.next = copyNodeMap.get(cur.next);
            tmp.rand = copyNodeMap.get(cur.rand);
            cur = cur.next;
        }

        return copyNodeMap.get(head);
    }

    /**
     * 面试时需要想出进阶的写法
     * @param head
     * @return 思考上面使用hash表的原因 存储的是当前节点和当前节点的拷贝节点的映射
     * 那么我们将链表调整成我们当前节点 + 拷贝节点 的样子 设置好rand指针后 再恢复即可
     */
    public static Node copyList(Node head){
        if (head == null)
            return null;

        Node cur = head;
        Node next;
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        //链表已被调整为1->1->2->2->3->3

        cur = head;
        while (cur != null){
            cur.next.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }

        //链表random指针已经正确设置 接下来的任务便是分离这个链表
//        cur = head;
//        next = head.next;
//        Node res = next;
//        while (next.next != null){
//            cur.next = next.next;
//            cur = next.next;
//            next.next = cur.next;
//            next = cur.next;
//        }
//        cur.next = null; // 在上述循环中 对于前一个节点 最后一次处理需要断开和最后一个节点的链接  1->1->2->2->3->3 ====>  1->2->3->3  1->2->3

         //上面是自己想的写法 原链表一定是大于等于2个节点的偶数链表 取第一个节点为cur 其下一个为next
        //当next的下一个节点为空 说明next已经是最后一个节点 否则可以一直进循环  主要是利用两个指针交替调整
         // 这里还提供一种写法
         //这种写法更通用  先用next标记指向下一个cur要遍历到的节点 只要next不为空 说明curCopy可以一直安全链接下去 否则curCopy到了
        //尾部 直接连接上null即可
        cur = head;
        Node res = head.next;
        Node curCopy;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static class Node{
        public int value;
        public Node next;
        public Node rand;

        Node(int value){
            this.value = value;
        }
    }

    private static void printRandomList(Node head){
        while (head != null){
            System.out.print(head.rand != null ? head.value + " ---> " + head.rand.value  + " -> " : head.value + " ---> null " + " -> ");
            head = head.next;
        }
        System.out.println();
    }

    private static void printList(Node head){
        while (head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        head.rand = head.next.next;
        head.next.rand = null;
        head.next.next.rand = head.next.next.next;
        head.next.next.next.rand = head.next;

        printRandomList(head);
        printRandomList(copyListUseMap(head));
        printRandomList(head);
        printRandomList(copyList(head));
        printRandomList(head);
    }
}
