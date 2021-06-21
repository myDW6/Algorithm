package com.shaodw.leetcode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shaodw
 * @date 2021/6/21 22:36
 * @description 二叉树后序遍历
 */
public class _145_PostorderTraversal {

    @Passed(note = "先序是根 左 右  很容易得到 根 右 左  然后整体反转就是 左 右 根")
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<TreeNode> preStack = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();
        preStack.push(root);
        while (!preStack.isEmpty()){
            TreeNode cur = preStack.pop();
            resStack.push(cur);
            if (cur.left != null){
                preStack.push(cur.left);
            }
            if (cur.right != null){
                preStack.push(cur.right);
            }
        }
        while (!resStack.isEmpty()){
            list.add(resStack.pop().val);
        }
        return list;
    }
}
