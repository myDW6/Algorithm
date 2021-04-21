package com.shaodw.datastructure.linkedlist;

/**
 * 使用虚拟头节点
 * @param <E>
 */
public class DummyHeadLinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }
    }

    //dummyHead是0位置前一个元素节点（-1位置）index从0开始
    private Node dummyHead;
    private int size;

    public DummyHeadLinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        add(0, e);
    }

    /**
     * dummyHead虚拟头节点用处在这，为了保证添加方法的一致性
     * @param index
     * @param e
     */
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, Illegal index.");
        /**
         * 找index前一个位置的节点 从dummyHead开始 找index次
         */
        Node pre = dummyHead;
        for (int i = 0; i < index; i++){
            pre = pre.next;
        }
        pre.next = new Node(e, pre.next);
        size++;
    }

    public void addLast(E e){
        add(size, e);
    }

    //获取链表第index(0~based)位置的元素
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed, Illegal index.");
        /**
         * 找index位置上的元素 从dummyHead的下一个也就是head也就是0索引位置上开始找index次
         */
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    //修改链表第index位置的元素
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed, Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    //查找链表中是否存在元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))return true;
            cur = cur.next;
        }
        return false;
    }

    //删除指定索引位置上的元素
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Delete failed, Illegal index.");
        Node pre = dummyHead;
        for (int i = 0; i < index; i++){
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public void removeElement(E e){
        if (e == null)
            throw new IllegalArgumentException("cannot remove null");
        Node pre = dummyHead;
        while (pre.next != null){
            if (pre.next.e.equals(e)){
                break;
            } pre = pre.next;
        }

        if (pre.next != null){
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
//        TreeNode cur = dummyHead.next;
//        while (cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }

        for (Node cur = dummyHead.next; cur != null; cur = cur.next){
            res.append(cur.e + "->");
        }

        res.append("NULL");
        return res.toString();
    }
}
