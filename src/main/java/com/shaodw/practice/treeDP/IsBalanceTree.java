package com.shaodw.practice.treeDP;

import com.shaodw.practice.tree.TreeNode;

/**
 * @Auther: shaodw
 * @Date: 2020/1/31 11:38
 * @Description: 判断一棵树是否为平衡二叉树
 * 任意节点其左子树和右子树的高度差不超过1
 */
public class IsBalanceTree {

    /**
     * 这里引用一个方法论 树形DP
     * 二叉树的结构 我们设计一个递归函数 递归函数需要向上一级返回两个信息 是否平衡 同时还有高度
     * 因为对于任意节点x 我们要保证它的左子树平衡 右子树平衡 且子树高度差不能大于1
     * 那么我们封装一个ReturnData类型 用于封装我们递归函数的返回值 不断向上一级传递即可
     */
    public static class ReturnData{
        boolean isBalance;
        int height;

        ReturnData(boolean isBalance, int height){
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalanceTree(TreeNode head){
        return recurProcess(head).isBalance;
    }

    private static ReturnData recurProcess(TreeNode head){
        if (head == null)
            return new ReturnData(true, 0);
        ReturnData leftData = recurProcess(head.left);
        if (!leftData.isBalance){
            return new ReturnData(false, 0);//这里可以返回0是因为当返回false的情况下 这个高度信息没有被利用
        }
        ReturnData rightData = recurProcess(head.right);
        if (!rightData.isBalance){
            return new ReturnData(false, 0);
        }
        //左右子树都不为空 判断高度差
        if (Math.abs(leftData.height - rightData.height) > 1){
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);//两棵子树高的一棵的高度加一返回给我的上级
    }


    /**
     * 至于递归函数怎么设计 我们可以向上面那样 封装成一个新的节点 也可以用一个参数来表示 如下
     * 返回-1代表不平衡
     */
    public static boolean isBalanceTree1(TreeNode head){
        return recurProcess1(head) != -1;
    }

    public static int recurProcess1(TreeNode head){
        if (head == null)
            return 0;
        int left = recurProcess1(head.left);
        if (left == -1){
            return left;
        }
        int right = recurProcess1(head.right);
        if (right == -1){
            return right;
        }
        if (Math.abs(left - right) > 1){
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
       // head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);

        System.out.println(isBalanceTree(head));
        System.out.println(isBalanceTree1(head));
    }
}
