package com.shaodw.datastructure.tree.heap;

import com.shaodw.datastructure.array.Array;

import java.util.Random;

/**
 * 用动态数组来存储完全二叉树实现大根堆
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    /**
     * 将任意数组整理成堆的形状
     * 1找到最后一个非叶子节点的索引（最后一个节点的父节点的索引）
     * 2从该非叶子节点开始 往前遍历 将每一个节点进行siftDown操作
     * 相当于一开始便抛弃了几乎所有的叶子节点 即相当于减少一半的个数 比将每一个元素添加到堆中要快
     */

    //将heapify的过程实现在构造中
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--){
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return leftChild(index) + 1;
    }

    //向堆中添加元素 Sift Up
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    //k为需要上浮的元素的索引
    private void siftUp(int k){
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //找到堆中最大元素
    public E findMax(){
        if (data.getSize() == 0){
            throw new IllegalArgumentException("cannot findMax when heap is empty");
        }
        return data.get(0);
    }

    //取出堆中最大元素
    public E extractMax(){
        E e = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return e;
    }

    //k为需要下沉的元素的索引
    private void siftDown(int k){
        while (leftChild(k) < data.getSize()){
            int maxChildIndex = rightChild(k) < data.getSize() && data.get(rightChild(k)).compareTo(data.get(leftChild(k))) > 0 ? rightChild(k) : leftChild(k);
            if (data.get(k).compareTo(data.get(maxChildIndex)) >= 0){
                break;
            }
            data.swap(k, maxChildIndex);
            k = maxChildIndex;
        }
    }

    //提供另一种实现
//    private void siftDown(int k){
//
//        while(leftChild(k) < data.getSize()){
//            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
//            if( j + 1 < data.getSize() &&
//                    data.get(j + 1).compareTo(data.get(j)) > 0 )
//                j ++;
//            // data[j] 是 leftChild 和 rightChild 中的最大值
//
//            if(data.get(k).compareTo(data.get(j)) >= 0 )
//                break;
//
//            data.swap(k, j);
//            k = j;
//        }
//    }

    //取出最大元素,并且替换成新的元素e  O(logN)
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }



    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++){
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[maxHeap.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]){
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("Test maxHeap completed");
    }
}
