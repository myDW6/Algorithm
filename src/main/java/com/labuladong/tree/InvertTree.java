package com.labuladong.tree;

import com.shaodw.practice.tree.TreeNode;
import com.shaodw.practice.tree.TreeTool;

/**
 * @author shaodw
 * @date 2021/6/1 23:51
 * @description 翻转二叉树
 * 发现只要把二叉树上的每一个节点的左右子节点进行交换，最后的结果就是完全翻转之后的二叉树。
 * 变成了二叉树的遍历问题
 * 采用先序后序 不能中序
 */
public class InvertTree {
    public static TreeNode reverseTree(TreeNode root){
        if (root == null){
            return null;
        }
        reverseTree(root.left);
        reverseTree(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeTool.printTree(root);

        System.out.println("staring reverseing ...");
        TreeTool.printTree(reverseTree(root));


    }


}
