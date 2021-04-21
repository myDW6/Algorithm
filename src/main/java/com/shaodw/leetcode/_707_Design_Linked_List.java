package com.shaodw.leetcode;


/**
 * @author shaodw
 * @date 2021/1/26 21:01
 * @description
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 */
public class _707_Design_Linked_List {
    //使用单链表的实现见MyLinkedList类
    //使用双向链表实现 可以算是最优解

    public class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int x) { val = x; }
    }

    class MyLinkedList {
        int size;
        // sentinel nodes as pseudo-head and pseudo-tail
        ListNode head, tail;
        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            // if index is invalid
            if (index < 0 || index >= size) return -1;

            // choose the fastest way: to move from the head
            // or to move from the tail
            ListNode curr = head;
            if (index + 1 < size - index)
                for(int i = 0; i < index + 1; ++i) curr = curr.next;
            else {
                curr = tail;
                for(int i = 0; i < size - index; ++i) curr = curr.prev;
            }

            return curr.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            ListNode pred = head, succ = head.next;

            ++size;
            ListNode toAdd = new ListNode(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            ListNode succ = tail, pred = tail.prev;

            ++size;
            ListNode toAdd = new ListNode(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        //先比较左右那边更小
        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            // If index is greater than the length,
            // the node will not be inserted.
            if (index > size) return;

            // [so weird] If index is negative,
            // the node will be inserted at the head of the list.
            if (index < 0) index = 0;

            // find predecessor and successor of the node to be added
            ListNode pred, succ;
            if (index < size - index) {
                pred = head;
                for(int i = 0; i < index; ++i) pred = pred.next;
                succ = pred.next;
            }
            else {
                succ = tail;
                for (int i = 0; i < size - index; ++i) succ = succ.prev;
                pred = succ.prev;
            }

            // insertion itself
            ++size;
            ListNode toAdd = new ListNode(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            // if the index is invalid, do nothing
            if (index < 0 || index >= size) return;

            // find predecessor and successor of the node to be deleted
            ListNode pred, succ;
            if (index < size - index) {
                pred = head;
                for(int i = 0; i < index; ++i) pred = pred.next;
                succ = pred.next.next;
            }
            else {
                succ = tail;
                for (int i = 0; i < size - index - 1; ++i) succ = succ.prev;
                pred = succ.prev.prev;
            }

            // delete pred.next
            --size;
            pred.next = succ;
            succ.prev = pred;
        }
    }

}
