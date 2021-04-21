package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 20:41
 * @Description: 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */

/**
 * 若当前节点有右子树 则要求的节点就是其右子树的最左节点
 * 若当前节点没有右子树 但是是其父节点的左子树 那么其父节点就是要求的节点
 *                      是其父节点的右子树 需要沿着父节点往上找 直到找到某个节点是其父节点的左子树
 *
 */
public class GetNextTreeLinkNode {
    public static TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        if (pNode.right != null){
            return getLeftNode(pNode.right);
        } else {
            TreeLinkNode father = pNode.next;
            while (father != null && father.right == pNode){
                pNode = father;
                father = father.next;
            }
            return father;
        }
    }

    public static TreeLinkNode getSuccessorNode(TreeLinkNode node){
        if (node == null)
            return null;
        if (node.right != null){
            return getLeftNode(node.right);
        }else {
            TreeLinkNode father = node.next;
            while (father != null && father.left != node){
                node = father;
                father = father.next;
            }
            return father;
        }
    }

    private static TreeLinkNode getLeftNode(TreeLinkNode node){
        if (node == null) return null;
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    private void printInorder(TreeLinkNode root){
        if (root == null)
            return;
        printInorder(root.left);
        System.out.println(root.val);
        printInorder(root.right);
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(6);
        root.next = null;
        //左子树
        root.left = new TreeLinkNode(3);
        root.left.next = root;
        root.left.left = new TreeLinkNode(1);
        root.left.left.next = root.left;
        root.left.left.right = new TreeLinkNode(2);
        root.left.left.right.next = root.left.left;
        root.left.right = new TreeLinkNode(4);
        root.left.right.next = root.left;
        root.left.right.right = new TreeLinkNode(5);
        root.left.right.right.next = root.left.right;
        //右子树
        root.right = new TreeLinkNode(9);
        root.right.next = root;
        root.right.left = new TreeLinkNode(8);
        root.right.left.next = root.right;
        root.right.left.left = new TreeLinkNode(7);
        root.right.left.left.next = root.right.left;
        root.right.right = new TreeLinkNode(10);
        root.right.right.next = root.right;

        //测试节点
        TreeLinkNode test = root;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.left;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.left.left;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.left.left.right;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.left.right;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.left.right.right;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.right;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.right.left;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.right.left.left;
        System.out.println(test.val + " next -> " + GetNext(test).val);
        test = root.right.right;
        //10's next is null
        System.out.println(test.val + " next -> " + GetNext(test));

    }
}

class TreeLinkNode{
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;//指向父节点的指针

    TreeLinkNode(int val){
        this.val = val;
    }
}
