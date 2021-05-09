package com.shaodw.leetcode;

/**
 * @Auther: shaodw
 * @Date: 2020-01-12 11:53
 * @Description: 单链表常见操作 单链表反转
 */
public class _206_ReverseList {
    public CommonListNode reverseList(CommonListNode head) {
        CommonListNode cur = head;
        CommonListNode prev = null;
        CommonListNode next = null;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public CommonListNode reverseListRec(CommonListNode head){
        if (head == null || head.next == null)
            return head;
        CommonListNode p = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        CommonListNode head = new CommonListNode(1);
        head.next = new CommonListNode(2);
        head.next.next = new CommonListNode(3);
        head.next.next.next = new CommonListNode(4);
        //ListNode res = new _206_ReverseList().reverseList(head);
        CommonListNode res = new _206_ReverseList().reverseListRec(head);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}


