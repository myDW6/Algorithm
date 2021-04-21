package com.shaodw.leetcode;

/**
 * @Auther: shaodw
 * @Date: 2020-01-12 11:04
 * @Description: 用链表存储字符串 判断是否是回文字符串
 */
public class _234_IsPalindrome {
    public boolean isPalindrome(ListNode head){
        if (head == null || head.next == null)
            return true;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        //快指针fast一次走两步 慢指针slow一次走一步
        //当快指针走完 慢指针来到终点 同时prev是前部分逆序
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        //如果是奇数个节点  此时慢指针再往前一步
        if (fast != null){
            slow = slow.next;
        }

        //依次比较slow和prev的值
        while (slow != null){
            if (slow.val != prev.val){
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode("a");
        head.next = new ListNode("b");
        head.next.next = new ListNode("b");
        head.next.next.next = new ListNode("a");
        //head.next.next.next.next = new ListNode("c");
        System.out.println(new _234_IsPalindrome().isPalindrome(head));
    }
}

