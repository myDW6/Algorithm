package com.shaodw.practice.morristraversal;

/**
 * @Auther: shaodw
 * @Date: 2020/2/20 10:24
 * @Description: morris遍历
 * 经典的二叉树（不考虑工程上的二叉树结构）只有父到左右两个孩子的指针 而没有指回父级的指针 所以需要一个stack来帮我们走回去 递归是递归函数帮我们压栈 非递归是自己设计栈压 但
 * 都无法省掉这个O(h)
 * morris遍历利用二叉树空闲的空间（指向null的引用） 修改原来二叉树的结构使得我们能回去 也即线索二叉树
 * 时间复杂度O(N) 额外空间复杂度O(1)
 * 先中后序遍历额外空间复杂度都是O(h) h为二叉树高度
 *
 *
 * morris遍历是这样一种遍历 如果一个节点有左子树 morris遍历能到这个节点本身两次  如果没有左子树 只到达这个节点一次 而且当我第二次到达这个节点时 左子树所有节点已经遍历完毕
 * 递归版遍历实质上是只要一个节点不空 我们就可以三次来到这个节点的过程 先序中序后序遍历是经典遍历基础上选择时机不同产生的东西
 * morris高度向这个过程致敬 如果当前节点有左子树我能来到当前节点两次 如果没有左子树我能来到当前节点一次  深度模拟了递归版的过程 但是没法三次回到自己
 * 怎么标记是第一次来到一个节点还是第二次来到一个节点 利用左子树最右节点的右指针指向谁这件事来区分
 */

/**
 * morris遍历的标准
 *  *   来到当前节点cur
 *  *   1：如果cur没有左孩子 cur向右移动 cur = cur.right
 *  *   2: 如果cur右左孩子 找到左子树最右的节点 记作mostRight
 *  *        (1):如果mostRight的右指针指向空 让其指向cur 然后cur向左移动 cur = cur.left;
 *  *        (2):如果mostRight的右指针指向cur 让其指向null 然后cur向右移动 cur = cur.right;
 *  *
 *  *                        1
 *  *                      /  \
 *  *                     2    3
 *  *                    / \  / \
 *  *                   4  5 6  7
 *  *
 *  *   cur = 1 [2-1] mostRight = 5; mostRight.right = null ==> 5 -> 1; cur = cur.left = 2;
 *  *   cur = 2 [2-1] mostRight = 4; mostRight.right = null ==> 4 -> 2; cur = cur.left = 4;
 *  *   cur = 4 [1] cur = cur.right = 2;
 *  *   cur = 2 [2-2] mostRight = 4; mostRight.right = cur ==> 4 -> null; cur = cur.right = 5;
 *  *   cur = 5 [1] cur = cur.right = 1;
 *      cur = 1 [2-2] mostRight = 5; mostRight.right = cur ==> 5 -> null; cur = cur.right = 3;
 *      cur = 3 [2-1] mostRight = 6; mostRight.right = null ==> 6 -> 3; cur = cur.left = 6;
 *      cur = 6 [1] cur = cur.right = 3;
 *      cur = 3 [2-2] mostRight.right = cur ==> 6 -> null; cur = cur.right = 7;
 *      cur = 7 [1] cur = cur.right = null;
 *
 */


/**
 * 假如第一次来到x 遍历了一次右边界  等第二次来到x 再遍历一次右边界 只有这样2次遍历右边界
 * 当x再走到其他节点 刚才的右边界是不会被遍历到的
 * 整棵树是可以被右边界分解掉的  每条右边界只会遍历有限几次  所有右边界的节点个数一共是n个
 * 所以来到一个节点x 遍历其右边界无论多少次 遍历节点的整体代价也只是O(N)
 *                 1
 *               /  \
 *             2     3
 *            / \   / \
 *          4   5  6   7
 *         /\  / \
 *        8 9 10 11
 *       来到1  遍历2 5 11
 *       来到2  遍历4 9
 *       来到4  遍历8
 *       第二次来到4 遍历8
 *       第二次来到2 遍历4 9
 *       来到5 遍历10
 *       第二次来到5 遍历10
 *       第二次来到1 遍历2 5 11
 *       整颗左子树被右边界分解 每一条右边界只有两遍 所以遍历左子树过程中 每个节点就果有限次
 */
public class MorrisTraversal {


    //morris中序遍历打印时机埋在 当我一个节点有左子树时 应该在后一次来到这个节点时再打印（中序遍历先处理完左子树 再处理我）
    //                         当我一个节点没有左子树时 第一次和第二次重叠在一起 所以打印即可
    public static void morrisIn(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            //如果左孩子不为空
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){ //两个条件都成立才能往右 因为我们遍历过程中改写了mostRight的右指针
                    mostRight = mostRight.right;
                }
                //mostRight此时是左子树最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            //否则直接cur = cur.right
            //一个cur只要准备往右走时就打印 便是我们morris改中序遍历的打印时机
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    //morris遍历改先序遍历  等同于在递归版先序遍历中把打印行为放在第一次来到该节点时
    public static void morrisPre(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                //一个节点有左子树什么时候是第一次来到这个节点的时候呢？ 是当它发现自己左子树的最右孩子的右指针指向null的时候 这个时候打印便是先序遍历选择的时机
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    //打印行为埋在这里
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }else {
                //当前节点没有左子树 没有先中后左这个问题 只需要打印当前节点 然后往右跑就完事了  他第一次回到自己和第二次回到自己只有一个时刻 在这个时刻打印就可以了
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 先序和中序只是morris序列选择时机不同而已 但实际是一个过程
     */


    /**
     * morris遍历实现后续遍历 递归能三次到一个节点 打印时机选择第三次就是后续 但morris没有第三次回到一个节点
     *
     * 打印时机放在第二次来到一个节点时  而且这个节点必须能两次回到这个节点本身  在这个时机去逆序打印其左子树的右边界
     * 对于上面图的树 morris顺序为1 2 4 2 5 1 3 6 3 7   如果一个节点只能回到自身一次 4 5 6 7直接忽视 选第二次来到这个节点的那些节点
     * 2 1 3 逆序打印其右边界 4 5 2 6 在整个函数退出前 单独打印整棵树的右边界7 3 1
     * 所以后序遍历结果为4 5 2 6 7 3 1
     */

    public static void morrisPos(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    //打印时机埋在这里 在大的if里面 说明有左子树 一个节点可以回到自己两次  这个else说明发现是第二次来到这个节点时
                    printEdge(cur.left);//逆序打印cur.left的右边界
                }
            }
            cur = cur.right;
        }

        //在返回之前 单独逆序打印整颗树的右边界
        printEdge(head);
        System.out.println();
    }

    //额外空间复杂度O(1) 逆序打印这个边界  使用链表反转的操作来实现
    public static void printEdge(Node head){

    }


    private static class Node{
        Node left;
        Node right;
        int val;

        Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        morrisPre(head);
        morrisIn(head);
        morrisPos(head);
    }
}
