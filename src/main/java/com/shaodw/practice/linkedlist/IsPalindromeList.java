package com.shaodw.practice.linkedlist;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/1/29 22:16
 * @Description: 判断一个链表是否为回文结构
 * 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂 度达到O(1)。
 */
public class IsPalindromeList {
    /**
     * 如果是笔试我们可以采用辅助空间大的且非常容易想到的办法帮助我们快速答题 比如这题使用栈
     */

    //need n extra space
    public static boolean isPalindromeListUseStack(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (cur != null){
            if (stack.pop().val != cur.val){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    //need n/2 extra space
    public static boolean isPalindromeListUseStackLessSpace(Node head){
//        if (head == null)
//            return true;
//        Stack<Node> stack = new Stack<>();
//        Node fast = head;
//        Node slow = head;
//        while (fast != null && fast.next != null){
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        slow = fast == null ? slow : slow.next;
        //上面这段代码 最终需要slow多走一步 那我们干脆让slow先走一步 如下
        if (head == null || head.next == null)
            return true;
        Node fast = head;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null){
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()){
            if (stack.pop().val != head.val){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 如果是面试 链表问题我们一般要做到空间最优 此处可将空间优化到O(1)
     * 还是快慢指针 快指针到终点后 慢指针将后部分逆序后比较 最后将顺序恢复
     */
    public static boolean isPalindromeList(Node head){
        if (head == null || head.next == null)
            return true;
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //确保slow停在中间（奇）/ 偏左（偶）位置

        //fast此时一定不会为空 若是奇数个 fast在最后一个节点 否则 在倒数第二个节点
        Node startReverse = reverseList(slow.next);
        fast = startReverse; //记录翻转点 等会恢复使用
        boolean res = true;
        while (fast != null){
            if (fast.val != head.val){
                res = false; //这里用变量标记后返回 因为我们还需要将链表恢复
                break;
            }
            fast = fast.next;
            head = head.next;
        }

        slow.next = reverseList(startReverse);
        return res;
    }

    private static Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        System.out.println(isPalindromeListUseStack(head));
        printList(head);
        System.out.println(isPalindromeListUseStackLessSpace(head));
        printList(head);
        System.out.println(isPalindromeList(head));
        printList(head);
    }

    private static void printList(Node head){
        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }
}
