package com.shaodw.datastructure.tree.AVLTree;

import com.shaodw.datastructure.array.Array;
import com.shaodw.datastructure.set.FileOperation;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    //获得节点node的高度
    private int getHeight(Node node){
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //计算节点的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断该树是否为一棵二分搜索树  利用二分搜索树中序遍历有序来实现
    public boolean isBST(){
        ArrayList<K> array = new ArrayList<>();
        inOrder(root, array);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i - 1).compareTo(array.get(i)) > 0 ){
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> array){
        if (node == null){
            return;
        }
        inOrder(node.left, array);
        array.add(node.key);
        inOrder(node.right, array);
    }

    //判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null)return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public AVLTree(){
        root = null;
        size = 0;
    }

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
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;//当前节点的高度是其两棵子树中较高的那棵的高度加1
        int balanceFactor = getBalanceFactor(node);//计算当前节点的平衡因子 （左子树与右子树的高度差）
        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            //右旋转
            return rightRotate(node);
        }

        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            //左旋转
            return leftRotate(node);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            //对当前节点的左孩子进行一次左旋转 转化成LL的情况
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            //对当前节点的右孩子进行一次右旋转  转化成RR的情况
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //对节点y进行右旋转 返回旋转后的新的根节点x
    //              y
    //             / \
    //            x   t4                     x
    //           / \        y向右旋转       /   \
    //          z   t3     --------->     z     y
    //         / \                       / \   / \
    //        t1  t2                    t1 t2 t3  t4
    private Node rightRotate(Node y){
        Node x = y.left;
        Node t3 = x.right;
        //向右旋转
        x.right = y;
        y.left = t3;
        //维护y和x的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t2 = x.left;
        //向左旋转
        x.left = y;
        y.right = t2;
        //维护y和x的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

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
        Node retNode;//使用retNode来保存删除节点后的返回值 删除有可能改变该树的平衡性  需要对返回之后的节点进行处理
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }

            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }else {
                Node successor = minimum(node.right);//找到node的后继节点
                //  successor.right = removeMin(node.right); removeMin的过程可能会破坏平衡 所以采用下面这种写法
                successor.right = remove(node.right, successor.key);//因为remove已经添加了对平衡性的处理
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null){
            return null;
        }
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;//当前节点的高度是其两棵子树中较高的那棵的高度加1
        int balanceFactor = getBalanceFactor(retNode);//计算当前节点的平衡因子 （左子树与右子树的高度差）
        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            //右旋转
            return rightRotate(retNode);
        }

        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            //左旋转
            return leftRotate(retNode);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            //对当前节点的左孩子进行一次左旋转 转化成LL的情况
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            //对当前节点的右孩子进行一次右旋转  转化成RR的情况
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
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

    public boolean isEmpty() {
        return size == 0;
    }

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
        ArrayList<String> array = new ArrayList<>();
        String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";
        if (FileOperation.readFile(fileName, array)) {
            System.out.println("total words : " + array.size());
            AVLTree<String, Integer> map = new AVLTree<>();
            for (String key : array){
                if (map.contains(key)){
                    map.set(key, map.get(key) + 1);
                }else map.add(key, 1);
            }
            System.out.println("total different words : " + map.getSize());
            System.out.println("pride appear times : " + map.get("pride"));
            System.out.println("prejudice appear times : " + map.get("prejudice"));
            System.out.println(map.isBST());
            System.out.println(map.isBalanced());

            for (String key : array){
                map.remove(key);
                if (!map.isBalanced() || !map.isBST()) {
                    throw new  IllegalArgumentException("Error");
                }
            }
        }
        System.out.println("Success");
    }
}
