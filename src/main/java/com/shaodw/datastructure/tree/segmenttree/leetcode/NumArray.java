package com.shaodw.datastructure.tree.segmenttree.leetcode;

import com.shaodw.datastructure.tree.segmenttree.SegmentTree;

/**
 * leetcode 303 Range Sum Query - Immutable
 * leetcode 307 Range Sum Query - Mutable (使用线段树的更新操作)
 */
class NumArray {

    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        if (nums.length > 0){
            Integer[] arr = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = nums[i];
            }
            segmentTree = new SegmentTree<Integer>(arr, (a,b)->a + b);
        }

    }

    public void update(int i, int val) {
        if (segmentTree == null){
            throw new IllegalArgumentException("SegmentTree is empty");
        }
        segmentTree.set(i,val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new IllegalArgumentException("SegmentTree is empty");
        }
        return segmentTree.query(i,j);
    }
}
