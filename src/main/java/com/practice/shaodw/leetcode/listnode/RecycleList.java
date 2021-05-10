package com.practice.shaodw.leetcode.listnode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shaodw
 * @date 2021/5/10 20:24
 * @description 给定一个链表 判断是否有环
 */
public class RecycleList {


    //快慢指针
    @Passed(complex = "时间复杂度N 空间O(1)")
    public static boolean hasCycle(ListNode head){
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }


    @Passed(complex = "时间复杂度N  空间复杂度N")
    public static boolean hasCycleUseSet(ListNode head){
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = head.next;
        System.out.println(hasCycleUseSet(head));
        System.out.println(hasCycle(head));
    }
}
