package com.practice.shaodw.leetcode.binarytree.travel;

import com.practice.shaodw.support.TreeNode;
import org.junit.jupiter.api.*;

/**
 * @author shaodw
 * @date 2021/4/21 00:22
 * @description
 */
public class TravelsalTest {

    TreeNode root;

    @BeforeEach
    public void generateTree(){
        System.out.println("构造新的二叉树");
        root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
    }

    @AfterEach
    public void destoryTree(){
        System.out.println("销毁该二叉树");
        root = null;
    }

    @Test
    public void testPreOrderRecur() {
        System.out.println("递归先序遍历结果");
        _144_PreorderTraversal.preorderTraversal(root).forEach(System.out::println);
    }

    @Test
    public void testInOrderRecur() {
        System.out.println("递归中序遍历结果");
        _94_InorderTraversal.inorderTraversal(root).forEach(System.out::println);
    }

    @Test
    public void testPostOrderRecur() {
        System.out.println("递归后序遍历结果");
        _145_PostorderTraversal.postorderTraversal(root).forEach(System.out::println);
    }



}
