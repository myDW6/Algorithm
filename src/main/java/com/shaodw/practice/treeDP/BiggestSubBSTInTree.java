package com.shaodw.practice.treeDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/24 10:36
 * @Description: 给定一棵二叉树的头节点 请返回最大搜索二叉搜索子树的大小
 */

import com.shaodw.practice.tree.TreeNode;
import com.shaodw.practice.tree.TreeTool;

/**
 * 写二叉树题的套路：
 * 求整棵树的什么什么东西
 * 统一的处理逻辑是假设以每一个节点为头的这棵树它的最大二叉搜索子树是什么
 * 如果求出了每一个节点为头的最大二叉搜索子树 答案一定在其中
 *
 *  假设要求以x为头的整棵树的最大二叉搜索子树
 *  第一步：列可能性
 *          1：以x为头的整棵树的最大二叉搜索子树可能来自于它左子树的最大二叉搜索子树
 *          2：以x为头的整棵树的最大二叉搜索子树可能来自于它右子树的最大二叉搜索子树
 *          3：以x为头的整棵树的最大二叉搜索子树可能是：左树整体是二叉搜索树 右树整体是二叉搜索树 我左树的最大值小于我 我右树的最小值大于我 以我为头的整棵树都是二叉搜索树
 *
 *  第二步：为了支持上述三种可能性 我们应该设计收集什么样的信息
 *          主逻辑是嵌套在搜索二叉树的递归过程里面的 一个节点 我先去其左树收集信息 再去其右树上收集信息 回到本体之后 左树的信息我有 右树的信息我也有 我利用这些信息能不能把三种可能性都算出来
 *
 *          整体的计算顺序是子树生成的信息给父树 父树用完后再加工出自己的信息 往上返回
 *
 *          那么为了支持上述三种可能性 我们需要收集哪些信息呢？
 *          为了支持第一种可能性 需要收集左树的最大二叉搜索子树的大小
 *          为了支持第二种可能性 需要收集右树的最大二叉搜索子树的大小
 *          为了支持第二种可能性 需要收集左树的最大二叉搜索子树的头部 （如果这个最大搜索子树的头部等于当前节点的左孩子 说明整颗左树都是搜索二叉树  如果头部不是我的左孩子 说明整颗左树不是搜索二叉树）
 *                             需要收集右树的最大二叉搜索子树的头部
 *                             需要左树的最大值
 *                             需要右树的最小值
 *
 *                       如果有了以上信息 足以支持让我们判断是三种可能性的哪一种
 *
 *           将上述信息化简：
 *                          不管你是左树还是右树 收集来的信息结构都得一样
 *                         1：最大二叉搜索子树的大小
 *                         2：最大二叉搜索子树的头部
 *                         3：这棵树上最大值和最小值
 *  第三步：改递归（假设左和右都给信息 如何利用左和右的信息组织成我要返回的信息）
 */
public class BiggestSubBSTInTree {

    public static TreeNode biggestSubTree(TreeNode head){
        return process(head).head;
    }

    private static ReturnType process(TreeNode head){
        if (head == null){
            return new ReturnType(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);//不干扰判断过程中最大最小值的决策
        }
        TreeNode left = head.left;
        ReturnType leftSubTreeInfo = process(left);
        TreeNode right = head.right;
        ReturnType rightSubTreeInfo = process(right);
        //我的左子树和右子树一定会给我这样的信息 这一共8个信息

        int perhaps1 = leftSubTreeInfo.size;
        int perhaps2 = rightSubTreeInfo.size;
        int perhaps3 = 0;
        if (leftSubTreeInfo.head == left && rightSubTreeInfo.head == right && leftSubTreeInfo.max < head.val && head.val < rightSubTreeInfo.min){
            perhaps3 = leftSubTreeInfo.size + rightSubTreeInfo.size + 1;
        }

        int maxSize = Math.max(Math.max(perhaps1, perhaps2), perhaps3);
        TreeNode maxHead = perhaps1 > perhaps2 ? leftSubTreeInfo.head : rightSubTreeInfo.head;
        maxHead = perhaps3 == maxSize ? head : maxHead;
        return new ReturnType(maxSize, maxHead,
                Math.max(head.val, Math.max(leftSubTreeInfo.max, rightSubTreeInfo.max)),
                Math.min(head.val, Math.min(leftSubTreeInfo.min, rightSubTreeInfo.min))
                );
    }

    //递归函数返回结构的类型
    private static class ReturnType{
        int size;
        TreeNode head;
        int max;
        int min;

        ReturnType(int size, TreeNode head, int max, int min){
            this.size = size;
            this.head = head;
            this.max = max;
            this.min = min;
        }

    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(12);
        head.right.left = new TreeNode(10);
        head.right.left.left = new TreeNode(4);
        head.right.left.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(5);
        head.right.left.right = new TreeNode(14);
        head.right.left.right.left = new TreeNode(11);
        head.right.left.right.right = new TreeNode(15);
        head.right.right = new TreeNode(13);
        head.right.right.left = new TreeNode(20);
        head.right.right.right = new TreeNode(16);

        TreeTool.printTree(head);
        TreeNode subTree = biggestSubTree(head);
        TreeTool.printTree(subTree);
    }
}
