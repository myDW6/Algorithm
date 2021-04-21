package com.shaodw.datastructure.tree.redblacktree;

import com.shaodw.datastructure.map.AVLMap;
import com.shaodw.datastructure.map.BSTMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * 测试红黑树 AVL树 二分搜索树的添加性能
 * 随机添加数据
 */
public class Main2 {
    public static void main(String[] args) {
        int n = 20000000;

        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(random.nextInt(Integer.MAX_VALUE));
        }
        //test BST
        long startTime = System.nanoTime();
        BSTMap<Integer, Integer> bstMap = new BSTMap<>();
        for (Integer i :  arrayList){
            bstMap.add(i, null);
        }
        long endTime = System.nanoTime();
        System.out.println("BST takes " + (endTime - startTime) / 1000000000.0 + " s");

        //test AVL Tree
        startTime = System.nanoTime();
        AVLMap<Integer, Integer> avlMap = new AVLMap<>();
        for (Integer i : arrayList){
            avlMap.add(i, null);
        }
        endTime = System.nanoTime();
        System.out.println("AVL Tree takes " + (endTime - startTime) / 1000000000.0 + " s");

        //test RedBlackTree
        startTime = System.nanoTime();
        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
        for (Integer i : arrayList){
            redBlackTree.add(i, null);
        }
        endTime = System.nanoTime();
        System.out.println("RedBlackTree Tree takes " + (endTime - startTime) / 1000000000.0 + " s");
    }
}
