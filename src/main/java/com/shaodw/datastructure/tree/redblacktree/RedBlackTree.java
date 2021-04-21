package com.shaodw.datastructure.tree.redblacktree;

import com.shaodw.datastructure.array.Array;
import com.shaodw.datastructure.set.FileOperation;

public class RedBlackTree<K extends Comparable<K>,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RedBlackTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //判断节点node的颜色
    private boolean isRed(Node node){
        //红黑树第三条性质 （与其说是性质 不如说成是定义）
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    public void add(K key, V value) {
        root = add(key, value, root);
        root.color = BLACK;//根节点为黑色
    }

    //          node                                x
    //          /   \     左旋转                   /   \
    //         t1    x    ---------->            node   t3
    //              / \                          /  \
    //             t2 t3                        t1  t2
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //          node                                 x
    //          /   \        右旋转                 /  \
    //         x     t2    ---------->            y   node
    //        / \                                      / \
    //       y   t1                                   t1  t2
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //向以node为根的红黑树中插入新的元素 返回插入新节点后新的红黑树的根
    private Node add(K key, V value, Node node){
        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(key, value, node.left);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(key, value, node.right);
        }else{//key.equals(node.key)
            node.value = value;
        }

        //进行红黑树性质的维护
        //具体对应一条逻辑链 满足条件时左旋转 接着右旋转 最后颜色反转 条件不互斥
        //进行左旋转的条件
        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        //进行右旋转的条件
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        //进行颜色翻转的条件
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0){
            return node;
        }
        else if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }
        else return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + "does not exist");
        }else {
            node.value = value;
        }
    }

    public static void main(String[] args) {
        Array<String> array = new Array<>();
        String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";
        if (FileOperation.readFile(fileName, array)) {
            System.out.println("total words : " + array.getSize());
            RedBlackTree<String, Integer> map = new RedBlackTree<>();
            while (!array.isEmpty()){
                String key = array.removeLast();
                if (map.contains(key)){
                    map.set(key, map.get(key) + 1);
                }else {
                    map.add(key, 1);
                }
            }
            System.out.println("total different words : " + map.getSize());
            System.out.println("pride appear times : " + map.get("pride"));
            System.out.println("prejudice appear times : " + map.get("prejudice"));
        }
    }
}
