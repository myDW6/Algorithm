package com.practice.shaodw.leetcode.listnode;


import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.ListNode;
import com.shaodw.leetcode.support.ListNodeTool;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author shaodw
 * @date 2021/2/19 14:41
 * @description 合并 k 个排序链表，返回合并后的排序链表。
 */
public class MergeKSortedLists {
    @Passed(complex = "N", note = "还是谁小填谁 但这个谁小需要使用堆来维护")
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null){
                minHeap.offer(list);
            }
        }

        while (!minHeap.isEmpty()){
            pre.next = minHeap.poll();
            pre = pre.next;
            if (pre.next != null){
                minHeap.offer(pre.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode[] lists = {l1,l2,l3};
        ListNode listNode = mergeKLists(lists);
        ListNodeTool.printNode(listNode);

    }
}
