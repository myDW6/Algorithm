package com.shaodw.practice.random;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shaodw
 * @Date: 2020/3/7 11:25
 * @Description: 给定一个函数生成0-4等概率的随机数 设计一个生成0-6等概率的随机数函数
 */
public class Rand4ToRand6 {

    public static int rand7(){
        while (true){
            int num = rand5() * 5 + rand5();
            if (num < 21){  //24 / 7 * 7
                return num % 7;
            }
        }
    }

    private static int rand5(){
        return (int)(Math.random() * 5);
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 14; i++) {
            int key = rand7();
            if (map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }

        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " times " + entry.getValue());
        }
    }
}
