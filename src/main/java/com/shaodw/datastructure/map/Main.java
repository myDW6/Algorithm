package com.shaodw.datastructure.map;

import com.shaodw.datastructure.array.Array;
import com.shaodw.datastructure.set.FileOperation;


/**
 * 对比底层实现分别为链表和二叉搜索树 和底层实现为AVL树的map的性能差异
 * 测试词频统计 傲慢 与 偏见在该书中出现的次数 实现同样操作需要的时间差异
 */
public class Main {

    public static void main(String[] args) {
        String fileName = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";

        System.out.println(fileName + " use LinkedListMap tastes " + testMap(new LinkedListMap<>(), fileName));
        System.out.println(fileName + " use BSTMap tastes " + testMap(new BSTMap<>(), fileName));
        System.out.println(fileName + " use AVLTree tastes " + testMap(new AVLMap<>(), fileName));
    }

    private static double testMap(Map<String, Integer> map, String fileName){
        long startTime = System.nanoTime();
        Array<String> array = new Array<>();
        System.out.println(FileOperation.readFile(fileName, array));
        System.out.println("total words: " + array.getSize());
        while (!array.isEmpty()){
            String key = array.removeLast();
            if (map.contains(key)) {
                map.set(key, map.get(key) + 1);
            }else {
                map.add(key, 1);
            }
        }
        System.out.println("total different words : " + map.getSize());
        System.out.println("pride appear times : " + map.get("pride"));
        System.out.println("prejudice appear times : " + map.get("prejudice"));
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
