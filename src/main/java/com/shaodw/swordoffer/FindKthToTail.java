package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020-01-14 23:24
 * @Description: 链表中倒数第k个节点
 * 两个指针 第一个先走k - 1步 然后同时走 第一个指针走完第二个正好走到倒数第k个
 */
public class FindKthToTail {

    private static ListNode findKthToTail(ListNode head, int k){
       if (head == null || k == 0)
           return null;
       ListNode first = head;
       ListNode last = head;

        for (int i = 0; i < k - 1; i++) {
            if (first.next != null){
                first = first.next;
            }else return null;
        }

        while (first.next != null){
            first = first.next;
            last = last.next;
        }
        return last;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        System.out.println(findKthToTail(head, 3).val);

    }




}
