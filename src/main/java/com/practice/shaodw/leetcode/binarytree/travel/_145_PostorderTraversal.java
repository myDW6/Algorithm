package com.practice.shaodw.leetcode.binarytree.travel;

import com.practice.shaodw.anno.Passed;
import com.practice.shaodw.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/4/21 00:00
 * @description 二叉树后序遍历递归写法
 */
public class _145_PostorderTraversal {
    @Passed(complex = "递归展开 ", note = "固定写法")
    public static List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> vals = new ArrayList<>();
        collect(root, vals);
        return vals;
    }
    private static void collect(TreeNode node, List<Integer> vals){
        if (node == null){
            return;
        }
        collect(node.left, vals);
        collect(node.right, vals);
        vals.add(node.val);
    }






}
