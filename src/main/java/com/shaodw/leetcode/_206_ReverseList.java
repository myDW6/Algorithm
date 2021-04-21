package com.shaodw.leetcode;

/**
 * @Auther: shaodw
 * @Date: 2020-01-12 11:53
 * @Description: 单链表常见操作 单链表反转
 */
public class _206_ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        ListNode next = null;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public ListNode reverseListRec(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode p = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        //ListNode res = new _206_ReverseList().reverseList(head);
        ListNode res = new _206_ReverseList().reverseListRec(head);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}


