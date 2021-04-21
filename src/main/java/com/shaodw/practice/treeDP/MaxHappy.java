package com.shaodw.practice.treeDP;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shaodw
 * @Date: 2020/2/25 09:11
 * @Description: 一个公司的上下节关系是一棵多叉树，这个公司要举办晚会，你作为组织者已经摸清了大家的心理：一个员工的直 接上级如果到场，这个员工肯定不会来。
 * 每个员工都有一个活跃度的值，决定谁来你会给这个员工发邀请函，怎么 让舞会的气氛最活跃？返回最大的活跃值。
 * 举例： 给定一个矩阵来表述这种关系
 * matrix = { 1,6
 *            1,5
 *            1,4
 *          }
 * 这个矩阵的含义是： matrix[0] = {1 , 6}，表示0这个员工的直接上级为1,0这个员工自己的活跃度为6
 *                  matrix[1] = {1 , 5}，表示1这个员工的直接上级为1（他自己是这个公司的最大boss）,1这个员工自己的活跃度 为5
 *                  matrix[2] = {1 , 4}，表示2这个员工的直接上级为1,2这个员工自己的活跃度为4
 *                  为了让晚会活跃度最大，应该让1不来，0和2来。最后返回活跃度为10
 */
public class MaxHappy {

    /**
     * 先简化下这个数据结构
     * 多叉树使用数组列表来描述
     */
    private static class Node{
        private int huoyue;
        private List<Node> nexts;
        Node(int huoyue){
            this.huoyue = huoyue;
            nexts = new ArrayList<>();
        }
    }

    /**
     * 可能性 以x为头 x来和x不来
     * x来：x的下级全不来的最大活跃度 + x的活跃度 = 就是x来的最大活跃度
     * x不来：x的所有下级来或者不来的最大活跃度
     * @param head
     * @return
     */
    public static int getMaxHuoyue(Node head){
        if (head == null){
            return 0;
        }
        ReturnData process = process(head);
        return Math.max(process.lai_huo, process.bulai_huo);
    }

    private static class ReturnData{
        private int lai_huo;
        private int bulai_huo;

        ReturnData(int lai_huo, int bulai_huo){
            this.lai_huo = lai_huo;
            this.bulai_huo = bulai_huo;
        }
    }

    private static ReturnData process(Node head){
        int lai_huo = head.huoyue;
        int bulai_huo = 0;
        for (int i = 0; i < head.nexts.size(); i++) {
            Node next = head.nexts.get(i);
            ReturnData info = process(next);
            lai_huo += info.bulai_huo;
            bulai_huo += Math.max(info.lai_huo, info.bulai_huo);
        }
        return new ReturnData(lai_huo, bulai_huo);
    }
}
