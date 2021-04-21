package com.shaodw.datastructure.tree.segmenttree;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;//定义线段树时定义好两个区间如何融合
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length * 4];//需要开辟4倍数组元素大小的静态区间
        buildSegmentTree(0, 0, data.length - 1);
    }

    //在treeIndex的位置创建表示区间l - r的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = leftTreeIndex + 1;
        int mid = l + ((r - l) >> 1);

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        //将左右两棵线段树合并成一棵线段树  具体合并看业务逻辑 使用接口 merge实现
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    //返回区间[l,r]线段树存储的值
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryR < 0 || queryL >= data.length || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //在以treeIndex为根的线段树中[l,r]范围内，搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL && r == queryR)
            return tree[treeIndex];
        int mid = l + ((r - l) >> 1);
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = leftTreeIndex + 1;

        if (queryL > mid){
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else if (queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        return merger.merge(query(leftTreeIndex, l, mid, queryL, mid), query(rightTreeIndex, mid + 1, r, mid + 1, queryR));
    }

    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0,0, data.length - 1, index, e);
    }

    //在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e){
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = leftTreeIndex + 1;
        int mid = l + ((r - l) >> 1);
        if (index > mid){
            set(rightTreeIndex, mid + 1, r, index, e);
        }else set(leftTreeIndex, l, mid, index, e);
        //index位置元素改变后 其父辈位置相应的元素也需要更新
       tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is Illegal");
        }
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子的索引
    private int leftChild(int index){
        return 2 * index + 1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();    
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null){
                res.append(tree[i]);
            }else res.append("null");
            if (i != tree.length - 1){
                res.append(",");
            }
        }
        return res.append("]").toString();
    }
}
