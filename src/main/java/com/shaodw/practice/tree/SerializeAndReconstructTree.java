package com.shaodw.practice.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: shaodw
 * @Date: 2020/1/30 13:46
 * @Description: 二叉树的序列化和反序列化
 * 牛客后台如何判断你写的一棵二叉树是否正确 将其按层序列化为字符串后比较
 */
public class SerializeAndReconstructTree {
    /**
     * _分隔符 为了区分12 3 和 1 23这种
     * # 代表null 必须记录null才能恢复
     * @param node
     * @return
     */


    /**
     * 先序方式序列化和反序列化
     * @param node
     * @return
     */
    public static String serialPreOrderTree(TreeNode node){
        if (node == null)
            return "#_";
        String res = node.val + "_";
        res += serialPreOrderTree(node.left);
        res += serialPreOrderTree(node.right);
        return res;
    }

    public static TreeNode reconByPreString(String serial){
        String[] str = serial.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < str.length; i++){
            queue.offer(str[i]);
        }
        return reconPreOrder(queue);
    }

    private static TreeNode reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if (value.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = reconPreOrder(queue);
        root.right = reconPreOrder(queue);
        return root;
    }

    /**
     * 层序遍历和反序列化
     * @param head
     * @return
     */
    public static String serialByLevel(TreeNode head){
        if (head == null){
            return "#_";
        }
        String res = head.val + "_";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            if (head.left != null){
                res += head.left.val + "_";
                queue.offer(head.left);
            }else {
                res += "#_";
            }

            if (head.right != null){
                res += head.right.val + "_";
                queue.offer(head.right);
            }else {
                res += "#_";
            }
        }
        return res;
    }

    public static TreeNode reconByLevelString(String serial){
        if (serial == null)
            return null;
        String[] str = serial.split("_");
        int index = 0;
        TreeNode root = generateNodeByString(str[index++]);
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null){
            queue.offer(root);
        }
        TreeNode node;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(str[index++]);
            node.right = generateNodeByString(str[index++]);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }

    private static TreeNode generateNodeByString(String s){
        return s.equals("#") ? null : new TreeNode(Integer.valueOf(s));
    }



    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        TreeTool.printTree(node);
        System.out.println(serialPreOrderTree(node));
        TreeTool.printTree(reconByPreString(serialPreOrderTree(node)));
        System.out.println("============================================");
        TreeTool.printTree(node);
        System.out.println(serialByLevel(node));
        TreeTool.printTree(reconByLevelString(serialByLevel(node)));
    }


}
