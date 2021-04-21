package com.shaodw.datastructure.tree.redblacktree;

import com.shaodw.datastructure.map.AVLMap;

import java.util.ArrayList;


/**
 * 测试红黑树 AVL树对有序数据的添加性能
 */
public class Main3 {
    public static void main(String[] args) {
        {
            int n = 20000000;
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arrayList.add(i);
            }

            //test AVL Tree
            long startTime = System.nanoTime();
            AVLMap<Integer, Integer> avlMap = new AVLMap<>();
            for (Integer i : arrayList){
                avlMap.add(i, null);
            }
            long endTime = System.nanoTime();
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
}
