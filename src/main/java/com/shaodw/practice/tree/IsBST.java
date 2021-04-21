package com.shaodw.practice.tree;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/2/1 21:32
 * @Description: 判断一棵树是否是搜索二叉树
 */
public class IsBST {
    /**
     * 中序遍历结果依次升序
     * @return
     */
    public static boolean isBST(TreeNode head){
        if (head == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        int pre = Integer.MIN_VALUE;
        while (head != null || !stack.isEmpty()){
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                if (head.val < pre){
                    return false;
                }
                pre = head.val;
                head = head.right;
            }
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
        System.out.println(isBST(head));
    }

}
