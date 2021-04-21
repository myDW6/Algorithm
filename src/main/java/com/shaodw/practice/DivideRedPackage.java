package com.shaodw.practice;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: shaodw
 * @Date: 2020/1/24 21:29
 * @Description: 模拟抢红包算法
 */
public class DivideRedPackage {
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum){
        List<Integer> amountList = new ArrayList<>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(1000, 1);
        for (Integer amount : amountList){
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }
}

class RedPackage{
   private int totalAmount;
   private int totalPeople;
   private Random random = new Random();
   private final CountDownLatch latch;

   RedPackage(int totalAmount, int totalPeople){
       this.totalPeople = totalPeople;
       this.totalAmount = totalAmount;
       latch = new CountDownLatch(totalPeople - 1);
   }

   public synchronized void divideAmount(int amount){
       totalAmount -= amount;
       totalPeople--;
   }

    public static void main(String[] args) throws InterruptedException {
       RedPackage redPackage = new RedPackage(100, 10);
       int k = redPackage.totalPeople;
        for (int i = 0; i < k - 1 ; i++) {

            new Thread(()->{

                    int amount = redPackage.random.nextInt(redPackage.totalAmount / redPackage.totalPeople * 2 - 1) + 1;
                    redPackage.divideAmount(amount);
                    System.out.println(Thread.currentThread().getName() + " 抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
                    redPackage.latch.countDown();

            }).start();
        }

        redPackage.latch.await();
        int amount = redPackage.totalAmount;
        System.out.println(Thread.currentThread().getName() + " 抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
    }
}