package com.shaodw.practice;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/1/29 23:32
 * @Description:
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
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
       ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(new Solution().isPalindrome(head));
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}

 class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }
}
