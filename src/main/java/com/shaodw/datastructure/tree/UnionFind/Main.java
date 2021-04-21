package com.shaodw.datastructure.tree.UnionFind;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
//        System.out.println("数组模拟并查集：" + testUF(new QuickFindUnionFind(size), m));
//        System.out.println("数组存储树结构实现并查集：" + testUF(new QuickUnionUnionFind1(size), m));
        System.out.println("数组存储树结构实现并查集：基于size优化 使得数量少的树挂在数量大的树上 " + testUF(new QuickUnionUnionFind2(size), m));
        System.out.println("数组存储树结构实现并查集：基于rank优化 使得高度低的树挂在高度高的树上 " + testUF(new QuickUnionUnionFind3(size), m));
        System.out.println("数组存储树结构实现并查集：rank优化和路径压缩优化 " + testUF(new UnionFind(size), m));
    }

    private static double testUF(UF uf, int m){
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            uf.union(random.nextInt(size), random.nextInt(size));
        }

        for (int i = 0; i < m; i++) {
            uf.isConnected(random.nextInt(size), random.nextInt(size));
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
