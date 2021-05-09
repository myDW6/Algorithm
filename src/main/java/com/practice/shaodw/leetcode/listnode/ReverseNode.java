package com.practice.shaodw.leetcode.listnode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.ListNode;
import com.shaodw.leetcode.support.ListNodeTool;

/**
 * @author shaodw
 * @date 2021/5/9 12:47
 * @description 反转单链表
 */
public class ReverseNode {

    //迭代
    @Passed(complex = "O(n)，其中 n 是链表的长度。需要遍历链表一次")
    public static ListNode reverseNode(ListNode listNode){
        ListNode next;
        ListNode prev = null;
        while (listNode != null){
            next = listNode.next;
            listNode.next = prev;
            prev = listNode;
            listNode = next;
        }
        return prev;
    }

    //递归 reverseNode_Recur将listNode反转
    //1->2->3
    @Passed(complex = "O(n)，其中 n 是链表的长度。需要对链表的每个节点进行反转操作")
    public static ListNode reverseNode_Recur(ListNode head){
        if (head == null || head.next == null){
            return head; //传进来为空 无需反转 head.next为空 表示到了最后一个节点
        }
        ListNode last = reverseNode_Recur(head.next); //先来到最后一个节点 然后呢 最后这个节点就会存上我们的
        //反转后的下一个节点 然后返回
        //对于两个节点的情况
        //head.next.next = head;
        //head.next = null; 不能成环
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        ListNode copyListNode = ListNodeTool.copyListNode(listNode);
        ListNodeTool.printNode(listNode);
        ListNodeTool.printNode(reverseNode(listNode));
        ListNodeTool.printNode(reverseNode_Recur(copyListNode));
    }


}

