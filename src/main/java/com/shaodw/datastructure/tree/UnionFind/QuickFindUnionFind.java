package com.shaodw.datastructure.tree.UnionFind;

/**
 * 数组模拟
 * 合并O(N)  查询O(1)
 */
public class QuickFindUnionFind implements UF {
    private int[] id;

    public QuickFindUnionFind(int size){
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;//表示每一个元素属于不同集合
        }
    }

    //查找元素p对象的集合编号
    private int find(int p){
        if (p < 0 || p >= id.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }

    //元素p q是否属于同一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素p和q所属的集合
    @Override
    public void union(int p, int q) {
        int pID = find(p);//找到元素p对应的集合编号
        int qID = find(q);
        if (pID == qID){return;}
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
