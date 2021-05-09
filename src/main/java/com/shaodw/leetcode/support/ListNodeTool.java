package com.shaodw.leetcode.support;

/**
 * @author shaodw
 * @date 2021/4/20 23:40
 * @description
 */
public class ListNodeTool {
    public static void printNode(ListNode listNode){
        while (listNode != null){
            System.out.print(listNode.val);
            if (listNode.next != null){
                System.out.print("-> ");
            }
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static ListNode copyListNode(ListNode origin){
        if (origin != null){
            ListNode g = new ListNode(origin.val);
            ListNode head = g;
            while (origin.next != null){
                origin = origin.next;
                g.next = new ListNode(origin.val);
                g = g.next;
            }
            return head;
        }
        return null;
    }
}
