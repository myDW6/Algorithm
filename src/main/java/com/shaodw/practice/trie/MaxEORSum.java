package com.shaodw.practice.trie;

/**
 * @Auther: shaodw
 * @Date: 2020/2/26 12:44
 * @Description: 给定一个数组，求子数组的最大异或和。 一个数组的异或和为，数组中所有的数异或起来的结果。
 */
public class MaxEORSum {

    /**
     * O(N * N * N)
     * @param arr
     * @return
     */
    public static int maxEor1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int start = 0; start <= i; start++){
                //枚举start到i的所有异或和结果
                int res = 0;
                for (int k = start; k <= i; k++) {
                    res ^= arr[k];
                }
                max = Math.max(max, res);
            }
        }
        return max;
    }

    /**
     * 异或
     * 如果E1 ^ E2 = E3
     * 那么E1 = E3 ^ E2  E2 = E3 ^ E1
     *
     * @param arr
     * @return 优化start->i的异或和计算
     * O(N * N)
     */
    public static int maxEor2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];//0到i的异或和记在数组中
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i]; //0到i的异或和
            max = Math.max(max, eor);
            for (int start = 1; start <= i; start++){
                //0->i解决 接下来解决1->i 2->i ...
                //start->i的异或和 = 0->i的异或和  ^  0->start-1的异或和
                max = Math.max(max, eor ^ dp[start - 1]);
            }
            dp[i] = eor;
        }
        return max;

    }

    /**
     * 最优解：使用trie结构使得复杂度将为O(N)
     * @param
     */
    private static class Node{
        public Node[] nexts = new Node[2];//0 or 1
    }

    private static class NumTrie{
        private Node head = new Node();

        public void add(int num){
            Node cur = head;
            for (int move = 31; move >= 0; move--){
                int path = ((num >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        private int maxXor(int num){
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move --){
                int path = (num >> move) & 1;
                int best = move == 31 ? path : (path ^ 1);
                best = cur.nexts[best] != null ? best : (best ^ 1);
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }

    public static int maxXorSubArray(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie trie = new NumTrie();
        trie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, trie.maxXor(eor));
            trie.add(eor);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,6,7,8,4,3,5,1,8,0};
        System.out.println(maxEor1(arr));
        System.out.println(maxEor2(arr));
        System.out.println(maxXorSubArray(arr));
    }
}
