package com.shaodw.leetcode;



/**
 * @author shaodw
 * @date 2021/1/26 21:04
 * @description
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 *
 * index表示的节点并非第index个节点，而是索引值为index的节点（索引值从0开始，不包括头节点）
 */
public class MyLinkedList {
    private Node dummy;
    private int size;

    public MyLinkedList() {
        this.dummy = new Node(-1);
        this.size = 0;
    }

    //// 获取到第index个节点数值，如果index是非法数值直接返回-1， 注意index是从0开始的，第0个节点就是头结点
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index > size - 1){
            return -1;
        }
        Node cur = dummy.next;
        while (index -- > 0){
            cur = cur.next;
        }
        return cur.val;
    }


    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        dummy.next = new Node(val, dummy.next);
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node cur = dummy;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = new Node(val);
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    //在第index个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    //  如果index大于链表的长度，则返回空
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size){
            return;
        }
        Node cur = dummy;
        while (index-- > 0){
            cur = cur.next;
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
        size++;
    }



    /** Delete the index-th node in the linked list, if the index is valid. */
    //如果index 大于等于链表的长度，直接return，注意index是从0开始的
    public void deleteAtIndex(int index) {
        if (index >= 0 && index < size){
            Node cur = dummy;
            while (index-- > 0){
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
        }
    }

    class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
        }

        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        //["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        //[[],[1],[3],[1,2],[1],[1],[1]]
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        printNode(myLinkedList);
        myLinkedList.addAtTail(3);
        printNode(myLinkedList);
        myLinkedList.addAtIndex(1,2);
        printNode(myLinkedList);
        System.out.println(myLinkedList.get(1));
        printNode(myLinkedList);
        myLinkedList.deleteAtIndex(1);
        printNode(myLinkedList);
        System.out.println(myLinkedList.get(1));
        printNode(myLinkedList);

    }

    static void printNode(MyLinkedList myLinkedList){
        Node cur = myLinkedList.dummy.next;
        while (cur != null){
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println();
    }
}
