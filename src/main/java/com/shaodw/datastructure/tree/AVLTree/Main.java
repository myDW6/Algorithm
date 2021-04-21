package com.shaodw.datastructure.tree.AVLTree;

import com.shaodw.datastructure.map.BSTMap;
import com.shaodw.datastructure.set.FileOperation;

import java.util.ArrayList;
import java.util.Collections;


/**
 * 比较avl树和普通二分搜索树实现的map性能差异
 * Collections.sort(array)使得二分搜索树退化为链表 和 AVL树性能差异发挥到最大
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";
        if (FileOperation.readFile(fileName, array)) {
            //Test BST
            Collections.sort(array);
            long startTime = System.nanoTime();
            BSTMap<String,Integer> bstMap = new BSTMap<>();
            for (String key : array){
                if (bstMap.contains(key)){
                    bstMap.set(key,bstMap.get(key) + 1);
                }else {
                    bstMap.add(key, 1);
                }
            }
            for (String key : array){
                bstMap.contains(key);
            }


            //Test AVLTree
            long endTime = System.nanoTime();
            System.out.println("BST takes " + (endTime - startTime) / 1000000000.0 + " s");
            startTime = System.nanoTime();
            AVLTree<String,Integer> avlTree = new AVLTree<>();
            for (String key : array){
                if (avlTree.contains(key)){
                    avlTree.set(key,avlTree.get(key) + 1);
                }else {
                    avlTree.add(key, 1);
                }
            }
            for (String key : array){
                avlTree.contains(key);
            }
            endTime = System.nanoTime();
            System.out.println("AVLTree takes " + (endTime - startTime) / 1000000000.0 + " s");
        }
    }
}
