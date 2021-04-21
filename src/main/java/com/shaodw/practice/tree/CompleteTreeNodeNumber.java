package com.shaodw.practice.tree;

/**
 * @Auther: shaodw
 * @Date: 2020/2/2 10:10
 * @Description: 已知一棵完全二叉树，求其节点的个数
 * 要求：时间复杂度低于O(N)，N为这棵树的节点个数 （不让遍历）
 */
public class CompleteTreeNodeNumber {
    /**
     * 沿着左边界一次遍历到底 得到完全二叉树高度
     * 沿着右孩子的左边界遍历一遍 得到右子树的高度
     * 若两者高度相等 说明这棵树的左子树是一棵满二叉树 利用公式N = 2 ^ h - 1可直接求出左子树个数
     * 若高度不相等 说明这棵树的右子树是一棵满二叉树 直接求出右子树节点个数
     * 递归
     *
     * 复杂度
     * 每一层只遍历一个节点 一共O(logN)层
     * 遍历到该节点 会遍历该节点右子树的左边界 O(logN)
     * 所以该算法复杂度为O(logN * logN)
     */

    public static int nodeNum(TreeNode head){
        if (head == null)
            return 0;
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    /**
     * @param node 当前沿着左右某条路径来到了这个node节点
     * @param level 当前进行到多少层 当level == H 递归终止
     * @param H 完全二叉树的总高度
     * @return
     */
    private static int bs(TreeNode node, int level, int H){
        if (level == H){
            return 1;
        }
        if (mostLeftLevel(node.right, level + 1) == H){
            return (1<< (H - level)) + bs(node.right, level + 1, H);
        }else {
            return (1 << (H - level - 1)) + bs(node.left, level + 1, H);
        }
    }

    private static int mostLeftLevel(TreeNode node, int level){
        while (node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);

        System.out.println(nodeNum(head));
    }
}
