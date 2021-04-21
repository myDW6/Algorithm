package com.shaodw.datastructure.map;

import com.shaodw.datastructure.array.Array;
import com.shaodw.datastructure.set.FileOperation;

/**
 * 基于二叉搜索树的map实现
 * @param <K>
 * @param <V>
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }

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
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //返回以node为根的二叉搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //删除以node为根的二叉搜索树的最小节点 并返回删除节点后新的二叉搜索树的根
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node ;
    }

    private Node remove(Node node, K key){
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
             node.left = remove(node.left, key);
             return node;
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);//找到node的后继节点
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + "does not exist");
        }else {
            node.value = value;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
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

    public static void main(String[] args) {
        Array<String> array = new Array<>();
        String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";
        if (FileOperation.readFile(fileName, array)) {
            System.out.println("total words : " + array.getSize());
            Map<String, Integer> map = new BSTMap<>();
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
