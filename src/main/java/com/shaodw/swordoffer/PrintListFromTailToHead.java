package com.shaodw.swordoffer;


import java.util.ArrayList;
import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 13:23
 * @Description: 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead {
    //每访问到一个节点 先递归输出其后面的节点
    public static ArrayList<Integer> printListFromTailToHeadRecur(ListNode head){
        ArrayList<Integer> list = new ArrayList();
        process(head, list);
        return list;
    }

    private static void process(ListNode head, ArrayList<Integer> list){
        if (head != null){
            if (head.next != null){
                process(head.next, list);
            }
            list.add(head.val);
        }
    }


    //既然想到了栈 就不难想到递归
    public static ArrayList<Integer> printListFromTailToHead(ListNode head){
            ArrayList<Integer> arrayList = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            ListNode cur = head;
            while (cur != null){
                stack.push(cur.val);
                cur = cur.next;
            }
            while (!stack.isEmpty()){
                arrayList.add(stack.pop());
            }
            return arrayList;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        printListFromTailToHead(head).stream().forEach(System.out::println);
        System.out.println("============");
        printListFromTailToHeadRecur(head).stream().forEach(System.out::println);
    }
}
