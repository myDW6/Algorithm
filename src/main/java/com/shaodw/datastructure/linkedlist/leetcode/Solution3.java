package com.shaodw.datastructure.linkedlist.leetcode;

//Recursive
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
//        ListNode node = removeElements(head.next, val);
//        if (head.val == val){
//            return node;
//        }else {
//            head.next = node;
//            return head;
//        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        System.out.println(new Solution3().removeElements(head, 6));
    }
}
