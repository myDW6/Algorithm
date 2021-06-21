package com.shaodw.leetcode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shaodw
 * @date 2021/6/21 22:04
 * @description 二叉树前序遍历
 */
public class _144_PreorderTraversal {
    @Passed(idea = "其实自己跟着这个过程 很好想明白  我们是要栈来帮我们模拟递归 那么我在处理每一个节点的时候" +
            "由于只会访问一次 访问完了就弹出丢弃了 所以需要用栈保存当前节点的左右孩子 不为空才保存" +
            "至于顺序 先序是中左右 所以先保存右 那样可以先处理左")
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        _144_PreorderTraversal _144 = new _144_PreorderTraversal();
        _144.preorderTraversal(root).forEach(System.out::println);
    }
}
