package com.practice.shaodw.leetcode.binarytree.travel;



import com.practice.shaodw.anno.Better;
import com.practice.shaodw.anno.Passed;
import com.practice.shaodw.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/4/20 23:15
 * @description 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class _144_PreorderTraversal {
    @Passed(complex = "递归树的深度", note = "固定写法", better = _144_PreorderTraversal_Iter.class)
    public static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> vals = new ArrayList<>();
        collect(root, vals);
        return vals;
    }

    private static void collect(TreeNode node, List<Integer> vals){
        if (node == null){
            return;
        }
        vals.add(node.val);
        collect(node.left, vals);
        collect(node.right, vals);
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



