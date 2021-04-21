package com.shaodw.datastructure.linkedlist.leetcode;

/**
 * leetcode203
 * 删除链表中等于给定值 val 的所有节点
 */

//不适应虚拟头节点
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val){
//            ListNode oldHead = head;
//            head = head.next;
//            oldHead.next = null;
            head = head.next;
        }
        if (head == null){
            return null;
        }
        ListNode node = head;
        while (node.next != null){
            if (node.next.val == val){
//                ListNode tmp = node.next;
//                node.next = tmp.next;
//                tmp.next = null;
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        System.out.println(new Solution().removeElements(head, 6));
    }
}
