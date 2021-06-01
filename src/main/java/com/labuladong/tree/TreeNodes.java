package com.labuladong.tree;

import com.shaodw.leetcode.support.TreeNode;

/**
 * @author shaodw
 * @date 2021/6/1 23:47
 * @description 计算二叉树的节点个数 递归思想开始
 */
public class TreeNodes {
    public static int count(TreeNode root){
        if (root == null) return 0;
        return 1 + count(root.left ) + count(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        System.out.println(count(root));
    }
}
