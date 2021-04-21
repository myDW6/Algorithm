package com.practice.shaodw.leetcode.listnode;


import com.practice.shaodw.anno.Error;
import com.practice.shaodw.anno.Passed;
import com.practice.shaodw.support.ListNode;

import static com.practice.shaodw.support.ListNodeTool.printNode;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 19:24
 * @Description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class _2_AddTwoNum {
    @Passed(complex = "N", note = "链表倒过来其实就是我们数字相加的逻辑, 用两个指针遍历链表就可以了, 将值依次相加就可以了"
    ,better = _2_AddTwoNum_1.class)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode next = dummy;
        ListNode p1 = l1, p2 = l2;
        int carryVal = 0;
        while (p1 != null ||  p2 != null){
            int sum = carryVal;
            if (p1 != null){
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null){
                sum += p2.val;
                p2 = p2.next;
            }
            boolean carry = sum / 10 > 0;
            int val = carry ? sum % 10 : sum;
            carryVal = carry ? 1 : 0;
            next.next = new ListNode(val);
            next = next.next;
        }
        if (carryVal == 1){
            next.next = new ListNode(1);
        }
        return dummy.next;
    }


    @Error(becauseOf = "最后一次的进位没有考虑")
    public static ListNode addTwoNumbers_error2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode next = dummy;
        ListNode p1 = l1, p2 = l2;
        int carryVal = 0;
        while (p1 != null ||  p2 != null){
            int sum = carryVal;
            if (p1 != null){
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null){
                sum += p2.val;
                p2 = p2.next;
            }
            boolean carry = sum / 10 > 0;
            int val = carry ? sum % 10 : sum;
            carryVal = carry ? 1 : 0;
            next.next = new ListNode(val);
            next = next.next;
        }
        return dummy.next;
    }

    @Error(becauseOf = "当一条链表值取完后 剩下的不能直接添加到末尾 因为还有进位问题")
    public static ListNode addTwoNumbers_ERROR(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode next = dummy;
        ListNode p1 = l1, p2 = l2;
        int carryVal = 0;

        //错误版本1  当一条链表值取完后 剩下的不能直接添加到末尾 因为还有进位问题
        while (p1 != null &&  p2 != null){
            int sum = p1.val + p2.val + carryVal;
            boolean carry = sum / 10 > 0;
            int val = carry ? sum % 10 : sum;
            carryVal = carry ? 1 : 0;
            next.next = new ListNode(val);
            next = next.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1 != null){
            next.next = p1;
        }
        if (p2 != null){
            next.next = p2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);
        listNode1.next = new ListNode(9);
        listNode1.next.next = new ListNode(9);
        listNode1.next.next.next = new ListNode(9);

        ListNode listNode2 = new ListNode(9);
        listNode2.next = new ListNode(9);
        listNode2.next.next = new ListNode(9);
        listNode2.next.next.next = new ListNode(9);
        listNode2.next.next.next.next = new ListNode(9);

        printNode(listNode1);
        printNode(listNode2);
        printNode(addTwoNumbers(listNode1, listNode2));
    }

}
