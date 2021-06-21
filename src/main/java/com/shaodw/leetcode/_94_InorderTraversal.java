package com.shaodw.leetcode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shaodw
 * @date 2021/6/21 22:41
 * @description 二叉树中序遍历
 */
public class _94_InorderTraversal {

    @Passed(note = "其实这个思路就是一下先来到左下角  那么会面临两种情况 一是左下角这个叶子节点没有右孩子" +
            "没有的话当然只需要简单收集处理下 然后返回往上就可以(因为左中右 没有右)  若有 我们这个问题就变成了整体处理树的问题 " +
            "我们让遍历的节点往右走 这个过程不断需要收集结果(右) 然后当来到一个右子树新的节点了 同样来到最左重复处理")
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

}
