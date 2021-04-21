package com.shaodw.datastructure.set;

import com.shaodw.datastructure.array.Array;

/**
 * 使用两种set的实现类对傲慢与偏见 双城记的单词个数进行统计
 * 基于两者都是动态的数据结构
 * 可看出明显差异
 *
 */
public class Main {
    public static void main(String[] args) {

        String fileName1 = "src\\com\\shaodw\\datastructure\\set\\a-tale-of-two-cities.txt";
        String fileName2 = "src\\com\\shaodw\\datastructure\\set\\pride-and-prejudice.txt";

        System.out.println(fileName1 + " use BSTSet tastes " + testSet(new BSTSet<String>(), fileName1));
        System.out.println(fileName2 + " use BSTSet tastes " + testSet(new BSTSet<String>(), fileName2));
        System.out.println(fileName1 + " use LinkedListSet tastes " + testSet(new LinkedListSet<String>(), fileName1));
        System.out.println(fileName2 + " use LinkedListSet tastes " + testSet(new LinkedListSet<String>(), fileName2));
        System.out.println(fileName1 + " use AVLSet tastes " + testSet(new AVLSet<String>(), fileName1));
        System.out.println(fileName2 + " use AVLSet tastes " + testSet(new AVLSet<String>(), fileName2));
    }

    public static double testSet(Set<String> set, String fileName){
        long startTime = System.nanoTime();
        Array<String> array = new Array<>();
        System.out.println(FileOperation.readFile(fileName, array));
        System.out.println("total words: " + array.getSize());
        while (!array.isEmpty()){
            set.add(array.removeLast());
        }
        System.out.println("total different words " + set.getSize());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
