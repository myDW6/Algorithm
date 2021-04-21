package com.practice.shaodw.leetcode.listnode;


import com.practice.shaodw.anno.Better;
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
public class _2_AddTwoNum_1 {

    @Better(note = "代码优雅", complex = "N")
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); //虚拟头节点
        int carry = 0;
        ListNode next = dummy; //遍历节点
        while (l1 != null ||  l2 != null || carry == 1) {
            next.next = new ListNode(carry);
            next = next.next;
            if (l1 != null){
                next.val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                next.val += l2.val;
                l2 = l2.next;
            }
            carry = next.val / 10; //直接就是1或0 不用傻乎乎  ? 1 : 0判断了;
            next.val %= 10;
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
