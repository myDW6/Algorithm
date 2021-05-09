package com.practice.shaodw.leetcode.binarytree.travel;



import com.shaodw.leetcode.support.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author shaodw
 * @date 2021/4/20 23:15
 * @description 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class _144_PreorderTraversal_Iter {


    //栈与队列：匹配问题都是栈的强项，递归的实现就是：每⼀次递归调⽤都会把函数的局部变量、参数值和返回地址等压⼊调⽤栈中，
    // 然后递归返回的时候，从栈顶弹出上⼀次递归的各项参数，所以这就是递归为什么可以返回上⼀层位置的原因。
    //既然是非递归写法 那肯定要使用到栈 已知中序遍历结果是 中 左 右   很明显入栈顺序为右 左 中
    // 每次先处理的是中间节点 那么先将该节点入栈 再将其右孩子入栈 再将其左孩子入栈
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
        return res;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println("先序遍历结果");
        preorderTraversal(root).forEach(System.out::println);
    }


}



