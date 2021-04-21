package com.shaodw.practice.tree;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/1/30 11:36
 * @Description: 二叉树的先序中序和后序遍历
 * 递归和非递归实现  额外空间复杂度都是O(h) h为二叉树高度
 */
public class PreInPosTraversal {

    /**
     * 递归版本
     * @param root
     */
    public static void preOrderRecur(TreeNode root){
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    public static void inOrderRecur(TreeNode root){
        if (root == null)
            return;
        inOrderRecur(root.left);
        System.out.print(root.val + " ");
        inOrderRecur(root.right);
    }

    public static void posOrderRecur(TreeNode root){
        if (root == null)
            return;
        posOrderRecur(root.left);
        posOrderRecur(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 非递归版本
     */
    public static void preOrderUnRecur(TreeNode root){
        System.out.println("pre order unrecur");
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            System.out.print(root.val + " ");
            if (root.right != null)
            stack.push(root.right);
            if (root.left != null)
            stack.push(root.left);
    }
        System.out.println();
    }

    public static void inOrderUnRecur(TreeNode root){
        System.out.println("in order unrecur: ");
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }
        System.out.println();
    }

    public static void posOrderUnRecur(TreeNode root){
        System.out.println("pos order unrecur: ");
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> help = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            help.push(root);
            if (root.left != null)
                stack.push(root.left);
            if (root.right != null)
                stack.push(root.right);
        }
        while (!help.isEmpty()){
            System.out.print(help.pop().val + " ");
        }
    }



    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        System.out.println("==========recur========");
        System.out.println("pre order recur: ");
        preOrderRecur(head);
        System.out.println();
        System.out.println("in order recur: ");
        inOrderRecur(head);
        System.out.println();
        System.out.println("pos order recur: ");
        posOrderRecur(head);
        System.out.println();

        System.out.println("==========unrecur========");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur(head);
    }


}
