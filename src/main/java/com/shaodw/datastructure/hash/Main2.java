package com.shaodw.datastructure.hash;

import com.shaodw.datastructure.map.BSTMap;
import com.shaodw.datastructure.set.FileOperation;
import com.shaodw.datastructure.tree.AVLTree.AVLTree;
import com.shaodw.datastructure.tree.redblacktree.RedBlackTree;

import java.util.ArrayList;

/**
 * 比较红黑树,avl树和普通二分搜索树 哈希表实现的map性能差异*/
public class Main2 {
    public static void main(String[] args) {
        {
            ArrayList<String> array = new ArrayList<>();
            String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";
            if (FileOperation.readFile(fileName, array)) {
                System.out.println("toatl words " + array.size());
                //Test BST
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
                long endTime = System.nanoTime();
                System.out.println("BST takes " + (endTime - startTime) / 1000000000.0 + " s");

                //Test AVLTree
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

                //Test RedBlackTree
                startTime = System.nanoTime();
                RedBlackTree<String,Integer> redBlackTree = new RedBlackTree<>();
                for (String key : array){
                    if (redBlackTree.contains(key)){
                        redBlackTree.set(key,redBlackTree.get(key) + 1);
                    }else {
                        redBlackTree.add(key, 1);
                    }
                }
                for (String key : array){
                    redBlackTree.contains(key);
                }
                endTime = System.nanoTime();
                System.out.println("RedBlackTree takes " + (endTime - startTime) / 1000000000.0 + " s");

                //Test HashTable
                startTime = System.nanoTime();
                HashTable<String,Integer> hashTable = new HashTable<>();//M的大小影响性能 进行总数的素数
                for (String key : array){
                    if (hashTable.contains(key)){
                        hashTable.set(key,hashTable.get(key) + 1);
                    }else {
                        hashTable.add(key, 1);
                    }
                }
                for (String key : array){
                    hashTable.contains(key);
                }
                endTime = System.nanoTime();
                System.out.println("HashTable takes " + (endTime - startTime) / 1000000000.0 + " s");
            }
        }
    }
}
