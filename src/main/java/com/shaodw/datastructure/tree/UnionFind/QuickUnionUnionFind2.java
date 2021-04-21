package com.shaodw.datastructure.tree.UnionFind;

/**
 * 数组表示树 从孩子指向父亲
 * 基于size的优化  以树上元素数量为基准
 */
public class QuickUnionUnionFind2 implements UF {
    private int[] parent;
    private int[] sz;//记录每棵树的大小  sz[i]表示以i为根的集合中元素的个数 主要为了在union时将下小树往大树上挂

    public QuickUnionUnionFind2(int size){
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        if (sz[qRoot] > sz[pRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
