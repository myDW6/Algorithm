package com.shaodw.datastructure.tree.trie;

import com.shaodw.datastructure.array.Array;
import com.shaodw.datastructure.set.BSTSet;
import com.shaodw.datastructure.set.FileOperation;

/**
 * 单词数量越多 trie性能越要好
 */
public class Main {
    private static void testTrieAndBstSet(String fileName){
        Array<String> words = new Array<>();
        if (FileOperation.readFile(fileName, words)){
            long startTime = System.nanoTime();
            BSTSet<String> set = new BSTSet<>();
            for (int i = 0; i < words.getSize(); i++) {
                set.add(words.get(i));
            }
            for (int i = 0; i < words.getSize(); i++) {
                set.contains(words.get(i));
            }
            long endTime = System.nanoTime();
            System.out.println(fileName);
            System.out.println("total different words : " + set.getSize());
            System.out.println("BSTSet taste " + (endTime - startTime) / 1000000000.0);

            startTime = System.nanoTime();
            Trie trie = new Trie();
            for (int i = 0; i < words.getSize(); i++) {
                trie.add(words.get(i));
            }
            for (int i = 0; i < words.getSize(); i++) {
                trie.contains(words.get(i));
            }
            endTime = System.nanoTime();
            System.out.println("total different words : " + trie.getSize());
            System.out.println("Trie taste " + (endTime - startTime) / 1000000000.0);
        }
    }

    public static void main(String[] args) {
        String fileName1 = "src\\com\\shaodw\\datastructure\\set\\a-tale-of-two-cities.txt";
        String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";
        testTrieAndBstSet(fileName);
        System.out.println("-------------------");
        testTrieAndBstSet(fileName1);
    }


}
