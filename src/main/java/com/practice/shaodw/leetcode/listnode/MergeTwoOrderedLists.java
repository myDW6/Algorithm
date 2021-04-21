package com.practice.shaodw.leetcode.listnode;


import com.practice.shaodw.anno.Passed;
import com.practice.shaodw.support.ListNode;
import com.practice.shaodw.support.ListNodeTool;

/**
 * @author shaodw
 * @date 2021/2/19 14:01
 * @description 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoOrderedLists {

    @Passed(complex = "min(M,N)", note = "谁小填谁的操作 使用虚拟节点方便")
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    //判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果
    @Passed(complex = "m + n", note = "递归函数每次去掉一个元素，直到两个链表都为空,需要调用m+n次, 每次一个赋值操作")
    public ListNode mergeTwoListsRecur(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecur(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecur(l1, l2.next);
            return l2;
        }
        //每次都把最小值压入栈，出栈的时候，将所有数连在一起就可以了。说白了，就是用一个栈维护了顺序。最后的连接，小的连小的，l1 小，就连到 l1,l2 小就连到 l2，最后先返回的，就是最小的头结点。

    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNodeTool.printNode(l1);
        ListNodeTool.printNode(l2);
        ListNode listNode = mergeTwoLists(l1, l2);
        ListNodeTool.printNode(listNode);
    }
}
