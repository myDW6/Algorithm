package com.shaodw.datastructure.linkedlist.leetcode;

//使用虚拟头节点
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode node = dummyHead;
        while (node.next != null){
            if (node.next.val == val){
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }
        return dummyHead.next;
    }
}
