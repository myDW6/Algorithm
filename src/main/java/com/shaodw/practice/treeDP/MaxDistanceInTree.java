package com.shaodw.practice.treeDP;

import com.shaodw.practice.tree.TreeNode;

/**
 * @Auther: shaodw
 * @Date: 2020/2/24 11:57
 * @Description: 二叉树中一个节点可以向上走也可以向下走 那么从节点A总能走到节点B
 * 节点A到节点B的距离：A到B最短路径上的节点个数
 * 求一棵二叉树的最远距离
 */
public class MaxDistanceInTree {

    /**
     * 左树的最大距离 不经过x
     * 右树的最大距离 不经过x
     * 包括我在内的整颗树的最大距离 经过x
     */
    public static int maxDistance(TreeNode head){
        return process(head).distance;
    }

    private static ReturnData process(TreeNode head){
        if (head == null){
            return new ReturnData(0, 0);
        }
        ReturnData leftInfo = process(head.left);
        ReturnData rightInfo = process(head.right);

        int perhaps1 = leftInfo.distance;
        int perhaps2 = rightInfo.distance;
        int perhaps3 = leftInfo.h + rightInfo.h + 1;
        return new ReturnData(Math.max(Math.max(perhaps1, perhaps2), perhaps3), Math.max(leftInfo.h, rightInfo.h) + 1);
    }

    private static class ReturnData{
        int distance; //最大距离
        int h; //深度

        ReturnData(int distance, int h){
            this.distance = distance;
            this.h = h;
        }
    }

}
