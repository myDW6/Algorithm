package com.shaodw.datastructure.tree.UnionFind;

/**
 * 并查集结构最终版
 * 数组表示树 从孩子指向父亲
 * 综合树结构 rank优化 并在查找过程中进行路径压缩
 */
public class UnionFind implements UF {
    private int[] parent;
    private int[] rank;//记录每棵树的高度  rank[i]表示以i为根的集合所表示的树的高度

    public UnionFind(int size){
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
        //直接压缩路径
        while (p != parent[p]){
            parent[p] = parent[parent[p]];//路径压缩 降低树的高度
            p = parent[p];
        }
        return p;

        //采用一个递归算法压缩路径 可以使得路径上每一个节点都直接指向根节点 整体性能会低于上面非递归实现
        /*if (p != parent[p]){
            parent[p] = find(parent[p]);
        }
        return parent[p];*/
    }

    /**
     * find的过程中 树的高度会降低  但是我们并没有维护rank数组,正因此不叫height和depth 我们维护的树的相对高度顺序,而并不一定是整颗树深度的具体表示 rank值只是在union时作为参考
     * 出于性能考虑 不维护树的高度
     * @param p
     * @param q
     * @return
     */

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
