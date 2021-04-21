package com.shaodw.practice.trie;

import java.util.HashMap;

/**
 * @Auther: shaodw
 * @Date: 2020/2/4 21:33
 * @Description: 前缀树 假定字符为a-z
 */
public class Trie {
    private class TrieNode{
        int path;  //记录某个字符被划过多少趟
        int end;  //记录以当前字符结尾的个数
        TrieNode[] nexts;

        TrieNode(){
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String str){
        if (str == null)
            return;
        char[] chs = str.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.path++;
        }
        node.end++;
    }

    public void delete(String str){
        if (search(str) != 0){
            char[] chs = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (--node.nexts[index].path == 0){
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

    public int prefixNumber(String pre){
        if (pre == null)
            return 0;
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null){
                return 0;
            }
            node = node.nexts[index];
        }
        return node.path;
    }

    //一个字符串在trie中出现的次数
    public int search(String str){
        if (str == null)
            return 0;
        char[] chs = str.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null){
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("shao"));
        trie.insert("shao");
        trie.insert("shaodw");
        trie.insert("shaoduanwu");
        System.out.println(trie.search("shaodw"));
        System.out.println(trie.prefixNumber("sh"));
        trie.delete("shao");
        trie.delete("s");
        System.out.println(trie.prefixNumber("sh"));
    }
}

/**
 * 一个字符串类型的数组arr1，另一个字符串类型的数组arr2。
 * arr2中有哪些字符，是arr1中出现的？请打印
 * arr2中有哪些字符，是作为arr1中某个字符串前缀出现的？请打印
 * arr2中有哪些字符，是作为arr1中某个字符串前缀出现的？请打印 arr2中出现次数最大的前缀。
 */
class TrieTest{
    public static void charInArrTimes(String[] arr1, String[] arr2){
        Trie trie = new Trie();
        for (String str : arr1){
            trie.insert(str);
        }
        for (String str : arr2){
            if (trie.search(str) > 0) {
                System.out.println(str);
            }
        }
    }

    public static void charPrefix(String[] arr1, String[] arr2){
        Trie trie = new Trie();
        for (String str : arr1){
            trie.insert(str);
        }
        for (String s : arr2){
            if (trie.prefixNumber(s) > 0){
                System.out.println(s);
            }
        }
    }

    public static void charPrefixMax(String[] arr1, String[] arr2){
        Trie trie = new Trie();
        for (String str : arr1){
            trie.insert(str);
        }
        HashMap<String, Integer> map = new HashMap<>();
        int maxIndex = 0;
        for (int i = 1; i < arr2.length; i++) {
            maxIndex = trie.prefixNumber(arr2[maxIndex]) > trie.prefixNumber(arr2[i]) ? maxIndex : i;
        }
        System.out.println(arr2[maxIndex]);
    }

    public static void main(String[] args) {
        String[] arr1 = {"abc", "bcd", "shd", "shaodw", "duanwu", "helloworld"};
        String[] arr2 = {"xyz", "qq", "facebook", "shaodw", "sh", "sh"};
        charInArrTimes(arr1, arr2);
        System.out.println("===================");
        charPrefix(arr1, arr2);
        System.out.println("====================");
        charPrefixMax(arr1, arr2);
    }
}
