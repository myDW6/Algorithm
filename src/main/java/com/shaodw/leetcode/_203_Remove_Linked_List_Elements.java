package com.shaodw.leetcode;

import java.util.List;

/**
 * @author shaodw
 * @date 2021/1/25 21:54
 * @description 删除链表中等于给定值 val 的所有节点。
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class _203_Remove_Linked_List_Elements {


    //使用虚拟头结点
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null){
            if (cur.next.val == val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    //不使用虚拟头结点
    public ListNode removeElements_without_dummyHead(ListNode head, int val) {
        ListNode cur = head;
        if (head == null){
            return null;
        }
        //主要是删除的核心 cur.next = cur.next.next;  那么很重要的我们必须知道当前节点的前一个节点的值才可以顺利删除 但对于头结点的处理 我们得单独处理 所以最好使用虚拟头结点
        while (cur.next != null){
            if (cur.next.val == val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }

        //对头结点处理
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        int val = 6;
        printNode(head);
        _203_Remove_Linked_List_Elements remove_linked_list_elements = new _203_Remove_Linked_List_Elements();
        ListNode listNode = remove_linked_list_elements.removeElements(head, val);
        printNode(listNode);
    }

    static void printNode(ListNode head){
        while (head != null){
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println();
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}



