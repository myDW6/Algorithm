package com.shaodw.practice.greedy;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 16:45
 * @Description: 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数组，里面是一个个具体的项目)，你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。返回这个最多的宣讲场次
 */
public class BestArrange {
    /**
     * 按结束时间最早进行贪心
     */
    public static class Program{
        int start;
        int end;

        Program(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    //cur代表当前时刻
    public static int bestArrange(Program[] programs, int cur){
        Arrays.sort(programs, (a,b)->a.end - b.end);
        int res = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= cur){
                res++;
                cur = programs[i].end;
            }
        }
        return res;
    }
}
