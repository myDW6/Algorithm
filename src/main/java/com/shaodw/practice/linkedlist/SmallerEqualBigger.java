package com.shaodw.practice.linkedlist;

/**
 * @Auther: shaodw
 * @Date: 2020/1/30 20:15
 * @Description: 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个 整 数pivot。
 * 实现一个调整链表的函数，将链表调整为左部分都是值小于 pivot 的节点，
 * 中间部分都是值等于pivot的节点，右部分都是值大于 pivot的节点。
 */
public class SmallerEqualBigger {

    public static Node listPartition1(Node head, int pivot){
        if (head == null)
            return null;
        Node cur = head;
        int i = 0;
        while (cur != null){
            cur = cur.next;
            i++;
        }
        Node[] arr = new Node[i];
        cur = head;
        for (i = 0; i < arr.length; i++) {
            arr[i] = cur;
            cur = cur.next;
        }

        //partition
        int less = -1;
        int more = arr.length;
        int index = 0;
        while (index < more){
            if (arr[index].val < pivot){
                swap(arr, index++, ++less);
            }else if (arr[index].val > pivot){
                swap(arr, index, --more);
            }else index++;
        }

        for (i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        arr[i - 1].next = null; //这步很关键 不然链表会成环 比如9->0->4->5->1 调整为1->0->5->4->9->0->4->5->1->0->......
        return arr[0];
    }


    private static void swap(Node[] arr, int i, int j){
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 进阶： 在原问题的要求之上再增加如下两个要求
     * 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左 到右的 顺序与原链表中节点的先后次序一致。及实现稳定性 partition过程是无法保证稳定性的
     * 如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
     */
    public static Node listPartition2(Node head, int pivot){
        Node lessHead = null;
        Node lessTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node moreHead = null;
        Node moreTail = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = null;
            if (head.val < pivot){
                if (lessHead == null){
                    lessHead = head;
                    lessTail = head;
                }else {
                    lessTail.next = head;
                    lessTail = head;
                }
            }else if (head.val == pivot){
                if (equalHead == null){
                    equalHead = head;
                    equalTail = head;
                }else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }else {
                if (moreHead == null){
                    moreHead = head;
                    moreTail = head;
                }else {
                    moreTail.next = head;
                    moreTail = head;
                }
            }
            head = next;
        }
        //以上操作成功产生了三条链表 分别是小于pivot 等于 和大于

        //连接小于和等于两条链表
       if (lessTail != null){
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }

       //全部连接
       if (equalTail != null){
           equalTail.next = moreHead;
       }

       return lessHead == null ? equalHead == null ? moreHead : equalHead : lessHead;
    }


    public static void main(String[] args) {
        //使用partition无法保证稳定性
        Node head = new Node(9);
        head.next = new Node(0);
        head.next.next = new Node(4);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(1);
        int pivot = 3;
        printList(listPartition1(head, pivot));

        //调整保持稳定性的方式
        Node head1 = new Node(9);
        head1.next = new Node(0);
        head1.next.next = new Node(4);
        head1.next.next.next = new Node(5);
        head1.next.next.next.next = new Node(1);
        printList(listPartition2(head1, pivot));

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
