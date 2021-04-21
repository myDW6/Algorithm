package com.shaodw.practice.kmp;

/**
 * @Auther: shaodw
 * @Date: 2020/2/10 15:10
 * @Description: T1的某棵子树是否包含T2 值和结构完全一样
 */
public class KMP_T1SubtreeEqualsT2 {
    /**
     * 思路是使用相同的序列化手段转为字符串后使用KMP比较两个字符串
     */

    public static boolean isSubTree(Node T1, Node T2){
        return KMP.getIndexOf(preSerial(T1), preSerial(T2)) != -1;
    }

    private static String preSerial(Node head){
        if (head == null){
            return "#_";
        }
        String res = head.val + "_";
        res += preSerial(head.left) + preSerial(head.right);
        return res;
    }

    public static class Node{
        int val;
        Node left;
        Node right;

        Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node T1 = new Node(1);
        T1.left = new Node(1);
        T1.right = new Node(1);
        T1.left.left = new Node(1);
        T1.left.right = new Node(1);
        T1.right.right = new Node(1);

        Node T2 = new Node(1);
        T2.right = new Node(1);

        Node T3 = new Node(1);
        T3.right = new Node(2);

        Node T4 = new Node(1);
        T4.left = new Node(1);

        System.out.println(isSubTree(T1, T2));
        System.out.println(isSubTree(T1, T3));
        System.out.println(isSubTree(T1, T4));
    }

}
