package com.shaodw.datastructure.tree.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5,3,6,8,4,2};
        for (int n : nums){
            bst.add(n);
        }
        System.out.println("先序遍历递归实现： ");
        bst.preOrder();
        System.out.println("先序遍历非递归实现： ");
        bst.preOrderUnRer();
        System.out.println("中序遍历递归实现： ");
        bst.inOrder();
        System.out.println("中序遍历非递归实现： ");
        bst.inOrderUnRec();
        System.out.println("后序遍历递归实现： ");
        bst.postOrder();
        System.out.println("后序遍历非递归实现： ");
        bst.postOrderUnRec();
        System.out.println("层序遍历");
        bst.levelOrder();

        System.out.println("===========================");
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        Random random = new Random();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst2.add(random.nextInt(10000));
        }
        System.out.println(bst2.size());//可能重复 这棵树不处理重复元素
        List<Integer> list = new ArrayList<>();
        while (!bst2.isEmpty()){
            list.add(bst2.removeMin());
        }
        System.out.println(list);
        for (int i = 1; i < list.size(); i++){
            if (list.get(i - 1) > list.get(i)){
                System.out.println("removeMin test failed");
            }
        }
        System.out.println("removeMin test successed");



        BinarySearchTree<Integer> bst3 = new BinarySearchTree<>();
        int m = 1000;
        for (int i = 0; i < m; i++) {
            bst3.add(random.nextInt(10000));
        }
        System.out.println(bst3.size());//可能重复 这棵树不处理重复元素
        List<Integer> list2 = new ArrayList<>();
        while (!bst3.isEmpty()){
            list2.add(bst3.removeMax());
        }
        System.out.println(list2);
        for (int i = 1; i < list2.size(); i++){
            if (list2.get(i - 1) < list2.get(i)){
                System.out.println("removeMax test failed");
            }
        }
        System.out.println("removeMax test successed");
    }
}
