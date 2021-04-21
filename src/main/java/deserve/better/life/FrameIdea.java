package deserve.better.life;

/**
 * @author shaodw
 * @date 2020/12/25 12:39
 * @description labuladong github 框架性思维
 * 所谓框架，就是套路。不管增删查改，这些代码都是永远无法脱离的结构
 */
public class FrameIdea {

//    数据结构的基本操作   遍历 + 访问，再具体一点就是：增删查改   数据结构种类很多，但它们存在的目的都是在不同的应用场景，尽可能高效地增删查改

    //如何遍历 + 访问？我们仍然从最高层来看，各种数据结构的遍历 + 访问无非两种形式：线性的和非线性的。
    //线性就是 for/while 迭代为代表，非线性就是递归为代表。以下几种框架：
    /**
     * 数组遍历框架，典型的线性迭代结构
     */
    void traverse(int[] arr){
        for (int i = 0; i < arr.length; i++) {
             // 迭代访问 arr[i]
        }
    }

    /**
     * 链表遍历框架，兼具迭代和递归结构
     */
    class Node{
        int val;
        Node next;
    }

    void traverse(Node head){
        for (Node p = head; p != null; p = p.next){
            // 迭代访问 p.val
        }

        //or
        Node p = head;
        while (p != null){
            //访问p.val
            p = p.next;
        }
    }

    void traverse_recur(Node head){
        // 递归访问 head.val
        traverse_recur(head.next);
    }

    /**
     * 二叉树遍历框架，典型的非线性递归遍历结构：
     */
    class TreeNode{
        TreeNode left, right;
        int val;
    }

    void traverse(TreeNode root){
        //前序遍历
        traverse(root.left);
        //中序遍历
        traverse(root.right);
        //后续遍历
    }

    /**
     * N叉树的遍历
     */
    class NTreeNode{
        NTreeNode[] childs;
        int val;
    }

    void traverse(NTreeNode root){
        for (NTreeNode child : root.childs){
            traverse(child);
        }
    }

    /**
     * N 叉树的遍历又可以扩展为图的遍历，因为图就是好几 N 叉棵树的结合体。你说图是可能出现环的？这个很好办，用个布尔数组 visited 做标记就行了
     */




    //数据结构是工具，算法是通过合适的工具解决特定问题的方法
    //重点刷和理解 二叉树    二叉树是最容易培养框架思维的，而且大部分算法技巧，本质上都是树的遍历问题。
    //只要涉及递归的问题，都是树的问题。其实很多动态规划问题就是在遍历一棵树，你如果对树的遍历操作烂熟于心，起码知道怎么把思路转化成代码，也知道如何提取别人解法的核心思路。
    //回溯算法就是个 N 叉树的前后序遍历问题，没有例外。
    //动态规划详解中总结的找状态转移方程的几步流程，有时候按照流程写出解法，不知道为啥是对的，反正它就是对了。。。
    //这就是框架的力量，能够保证你在快睡着的时候，依然能写出正确的程序；就算你啥都不会，都能比别人高一个级别。


}
