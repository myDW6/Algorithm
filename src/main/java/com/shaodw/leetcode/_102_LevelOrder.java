package com.shaodw.leetcode;

import com.shaodw.anno.Passed;
import com.shaodw.leetcode.support.TreeNode;
import shaodw.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author shaodw
 * @date 2021/6/21 23:11
 * @description 二叉树层序遍历
 */
public class _102_LevelOrder {
    @Passed(note = "层序遍历很好写 一开始卡在了怎么判断这是同一层的节点 答案很简单 当一次处理完成后 存储下此时队列的节点个数就可以了 然后下一次直接一次性弹出那么多个")
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;//当为空时 要求输出[] 而不是[[]]
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null){
                    queue.add(root.left);
                }
                if (root.right != null){
                    queue.add(root.right);
                }
            }
            result.add(list);
        }
        return result;
    }

}
