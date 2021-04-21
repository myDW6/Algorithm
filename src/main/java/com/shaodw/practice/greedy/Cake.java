package com.shaodw.practice.greedy;


import java.util.PriorityQueue;

/**
 * @author shaodw
 * @date 2020/12/25 15:56
 * @description
 */
public class Cake {

    public static void main(String[] args) {
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(solution(g, s));
    }


    public static int solution(int[] g, int[] s){
        if (s.length == 0 || g.length == 0){
            return 0;
        }
        PriorityQueue<Integer> cakes = new PriorityQueue<>();
        PriorityQueue<Integer> eat = new PriorityQueue<>();
        int size = 0;
            for (int i = 0; i < s.length; i++) {
               cakes.add(i);
            }
            for (int i = 0; i < g.length; i++) {
                eat.add(i);
            }

            while (!cakes.isEmpty() && !eat.isEmpty()){
                if (cakes.poll() >= eat.peek()) {
                    eat.poll();
                    size++;
                }
            }
            return size;
    }
}
