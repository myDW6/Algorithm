package com.labuladong.tree;

/**
 * @author shaodw
 * @date 2021/6/2 00:04
 * @description leetcode116
 * 给一颗完美二叉树 像个等边三角形 每个节点有left right val 还有一个next 需要指向其右侧的那个节点
 * 右侧为空 就指向null
 *
 *
 */
public class ConnectTree {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    //一开始的思路是这样的  每一个节点 将其左孩子的next指向右孩子
    public static Node connectError(Node root){
        if (root == null)return null;
        root.left.next = root.right;
        connectError(root.left);
        connectError(root.right);
        return root;
    }
    //乍一看没问题  但是有问题  就是对于 不是同一个父亲的节点  比如有三层 那第三层的第二个和
    //第三个节点 不是一个父亲  会漏掉

    //一个Node做不到 我们多传递一个
    public static Node connect(Node root){
        if (root == null){
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    //将每一层二叉树节点连接起来」可以细化成「将每两个相邻节点都连接起来」
    private static void connectTwoNode(Node node1, Node node2){
        if (node1 == null || node2 == null)
            return;
        node1.next = node2;// 将传入的两个节点连接
        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }

}
