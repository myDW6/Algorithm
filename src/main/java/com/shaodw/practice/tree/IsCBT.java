package com.shaodw.practice.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: shaodw
 * @Date: 2020/2/1 22:10
 * @Description: 判断一棵树是否是完全二叉树
 * 判断标准：按层遍历
 *          如果一个节点有右无左 不是
 *          一个节点不是两个孩子都全  (两个孩子都没有 有左没右) 开启leaf阶段 接下来每一个节点都得是叶子节点
 */
public class IsCBT {
    public static boolean isCBT(TreeNode head){
        if (head == null)
            return true;
        boolean leaf = false;//从leaf变为true开始 每遇到的节点都必须是叶子节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            TreeNode left = head.left;
            TreeNode right = head.right;
            if ((leaf && (left != null || right != null)) || (right != null && left == null))
                return false;
            //有右没左直接返回false  当开启leaf阶段后 必须是左右两个孩子都为空（叶子节点）
            if (left != null){
                queue.offer(left);
            }
            if (right != null){
                queue.offer(right);
            }
            if (left == null || right == null){
                leaf = true;
            }
            //else {
            //                leaf = true;
            //            }

            //叶阶段开启条件本为 if(left == null || right == null) 但此处左不可能为空了
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);

        TreeTool.printTree(head);
        System.out.println(isCBT(head));
    }
}
