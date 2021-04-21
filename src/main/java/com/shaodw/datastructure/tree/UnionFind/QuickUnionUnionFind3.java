package com.shaodw.datastructure.tree.UnionFind;

/**
 * 数组表示树 从孩子指向父亲
 * 基于rank的优化  以树高度为基准
 */
public class QuickUnionUnionFind3 implements UF {
    private int[] parent;
    private int[] rank;//记录每棵树的高度  rank[i]表示以i为根的集合所表示的树的高度

    public QuickUnionUnionFind3(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    //查找元素p所对应的集合编号
    //O(h)复杂度 h为树的高度
    private int find(int p ){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    //p和q是否属于同一集合
    //O(h)复杂度 h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并p q元素所在的集合
    //O(h)复杂度 h为树的高度
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        //根据两个元素所在树的rank的不同判断合并的方向
        //将rank低的合并到rank高的集合上
        if (rank[qRoot] > rank[pRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
