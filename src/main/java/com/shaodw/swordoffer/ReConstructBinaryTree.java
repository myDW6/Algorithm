package com.shaodw.swordoffer;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 14:13
 * @Description: 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * copyOfRange [i,j)
 */
public class ReConstructBinaryTree {
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in){
        if (pre == null || pre.length == 0 || in == null || in.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int index = search(in, 0, in.length - 1, pre[0]);//中序中找到根
        //注意copyOfRange函数 左闭右开
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, index + 1), Arrays.copyOfRange(in, 0, index));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, index + 1, pre.length ), Arrays.copyOfRange(in, index + 1, in.length));
        return root;
    }

    private static int search(int[] in, int start, int end, int key){
        for (int i = start; i <= end ; i++) {
            if (key == in[i]){
                return i;
            }
        }
        return -1;
    }

    public static void printBTree(TreeNode root){
        if (root == null) return;
        System.out.println(root.val);
        printBTree(root.left);
        printBTree(root.right);
    }
    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        printBTree(reConstructBinaryTree(pre, in));
        System.out.println("=============");
        printBTree(process(pre, in));
    }


    //也可以这样写
    public static TreeNode process(int[] pre, int[] in){
        if (pre.length == 0 || in.length == 0)
            return null;
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]){
                root.left = process(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = process(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
}
