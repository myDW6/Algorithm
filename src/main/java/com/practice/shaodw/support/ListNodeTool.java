package com.practice.shaodw.support;

/**
 * @author shaodw
 * @date 2021/4/20 23:40
 * @description
 */
public class ListNodeTool {
    public static void printNode(ListNode listNode){
        while (listNode != null){
            System.out.print(listNode.val + "-> ");
            listNode = listNode.next;
        }
        System.out.println();
    }
}
