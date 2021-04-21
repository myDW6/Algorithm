package com.shaodw.datastructure.tree.BST;

import java.util.*;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<E extends Comparable<E> > {

    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;//存储元素的个数

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /*
    public void add(E e){
        if (root == null){
            root = new TreeNode(e);
            size++;
        }else add(root,e);
    }

    //向以node为根的二叉搜索树中插入值
    private void add(TreeNode node, E e){
        //ignore the equal case
        if (e.equals(node.e))
            return;
        else if (e.compareTo(node.e) < 0 && node.left == null){
            node.left = new TreeNode(e);
            size++;
            return;
        }
        else if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new TreeNode(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0)
            add(node.left, e);
        else add(node.right, e);//e.compareTo(node.e) > 0
    }
    上述写法过于臃肿，原因在于e.compareTo(node.e) < 0 && node.left == null的逻辑还得往下调用
    以下提供一种简单写法
    */

    public void add(E e){
        root = add(root, e);
    }

    //向以node为根的二叉搜索树中插入值 返回插入后二叉搜索树的根节点
    private Node add(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else return contains(node.right, e);
    }

    public void preOrder(){
        preOrder(root);
    }

    //先序遍历node为根的二叉搜索树
    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    //前序遍历非递归
    public void preOrderUnRer(){
        Stack<Node> stack = new Stack<>();//存储了下一个即将访问的节点
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    //中序遍历非递归
    public void inOrderUnRec(){
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                System.out.println(cur.e);
                cur = cur.right;
            }
        }
    }

    //后序遍历非递归
    public void postOrderUnRec(){
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            Node node = stack1.pop();
            stack2.push(node);

            if (node.left != null){
                stack1.push(node.left);
            }
            if (node.right != null){
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            System.out.println(stack2.pop().e);
        }
    }


    //中序遍历  生成二叉搜索树中元素排序后的结果
    private void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    //后续遍历  为二叉搜索树释放内存 先释放孩子节点 再处理父亲节点 c++
    private void postOrder(Node node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    //寻找二分搜索树的最小元素
    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }

    //返回以node为根的二叉搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //寻找二分搜索树的最大元素
    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }

    //返回以node为根的二叉搜索树的最大值所在的节点
    private Node maximum(Node node){
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    //从二叉搜索树中删除最小值所在节点 返回最小值
    public E removeMin(){
        E result = minimum();
        root = removeMin(root);//删除掉以root为根的二叉搜索树中的最小值 返回新的以root为根的二叉搜索树
        return result;
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

    public E removeMax(){
        E result = maximum();
        root = removeMax(root);
        return result;
    }

    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //删除元素为e的节点
    public void remove(E e){
        root = remove(root, e);
    }

    //删除以node为根的二叉搜索树中值为e的节点 返回删除节点后新的二叉搜索树的根
    private Node remove(Node node, E e){
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else {//e == node.e
            //待删除节点左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //待删除节点右子树为空
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //待删除节点左右子树都不为空
            //找到比待删除节点大的最小节点 即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            //当然也可以取左子树中最大节点来替代 此处使用右子树中最小节点（后继）来取代
            Node successor = minimum(node.right);//找到node的后继节点
            successor.right = removeMin(node.right);
            //removeMin的size-- 但是succossor还指向着该节点 所以相当于白减 此处还需要一个size++  后面删除node再次size-- 所以size没有变化
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    //生成以node为根节点, 深度为depth的二叉搜索树的字符串描述
    private void generateBSTString(Node node, int depth, StringBuilder res){
        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++){
            res.append("--");
        }
        return res.toString();
    }
}
