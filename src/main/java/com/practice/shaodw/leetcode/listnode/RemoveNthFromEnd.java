package com.practice.shaodw.leetcode.listnode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.ListNode;
import com.shaodw.leetcode.support.ListNodeTool;

/**
 * @author shaodw
 * @date 2021/2/8 09:07
 * @description 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。   给定的 n 保证是有效的
 */
public class RemoveNthFromEnd {
    @Passed(complex = "N", note = "避免两次遍历链表就可以利用这种快慢指针的玩法")
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNodeTool.printNode(head);
        ListNode listNode = removeNthFromEnd(head, 5);
        ListNodeTool.printNode(listNode);
    }
}
