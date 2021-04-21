package com.practice.shaodw.support;

/**
 * @author shaodw
 * @date 2021/4/20 23:26
 * @description leetcode的树节点
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(){}
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
