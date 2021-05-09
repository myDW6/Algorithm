package com.shaodw.leetcode;

import com.shaodw.anno.Better;
import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.ListNode;
import com.shaodw.leetcode.support.ListNodeTool;

/**
 * @Auther: shaodw
 * @Date: 2019-12-29 16:00
 * @Description: 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 *
 * 思路：数字在链表中逆序存储，那顺序计算同时考虑进位，数字的个位相加对应链表的第一个节点的值相加
 * 还需要考虑的问题是两个链表长度不等，也就是对应的数字位数不同，可在位数短的数字前补0  对应在链表即在链表尾部补节点值为0的节点
 *
 */
public class _2_AddTwoNumbers {

    @Better
    @Passed(complex = "max(m,n)", note = "链表倒过来其实就是我们数字相加的逻辑, 用两个指针遍历链表就可以了, 将值依次相加就可以了")
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, cur = dummyHead;
        int carry = 0;

        while (p != null || q != null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0){
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    @Passed(note = "另一种写法",complex = "max(m,n)")
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
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
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l1Copy = ListNodeTool.copyListNode(l1);
        ListNode l2Copy = ListNodeTool.copyListNode(l2);

        ListNode l = addTwoNumbers(l1, l2);
        ListNodeTool.printNode(l);

        ListNode lCopy = addTwoNumbers1(l1Copy, l2Copy);
        ListNodeTool.printNode(lCopy);
    }
}
