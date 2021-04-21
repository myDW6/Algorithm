package com.shaodw.practice.monotonousstack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/2/19 21:35
 * @Description: 环形山峰问题
 * 一个数组 代表环形的山 1 2 4 5 3
 * 1： 相邻的山相互可见
 * 2： a b两座山 有顺时针 逆时针两条路 如果某一条路上出现的所有值都比min(a,b)不大 则a b相互可见
 * 1->3->5->4  1->2->4 这两条路上都有值比1大 所以1和4不能相见
 *
 * 返回能相互看见的山峰对数
 */
public class MontainsAndFlame {

    /**
     * 代表一共有多少对山峰可以相互看见
     * @param arr 环形数组
     * @return
     */

    public static long communications(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        int maxValue = arr[maxIndex];
        //如果有相同值时 先找到最大值 若有多个 找第一个出现的作为我们开始遍历的位置 先将最大值放进单调递减栈
        int index = nextIndex(size, maxIndex);//从最大值位置的下一个开始遍历
        long res = 0L;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(maxValue));

        while (index != maxIndex){//遍历这个环形数组
            int curValue = arr[index];
            while (!stack.isEmpty() && curValue > stack.peek().value){
                //栈中某条记录要弹出  结算山峰对数量
                int times = stack.pop().times;
                res += getInternalSum(times) + (times << 1); //C 2 times + 2 * times
            }

            //当前值没法再弹 原因有2 可能是可以落在栈顶 也可能是和栈顶相等
            if (!stack.isEmpty() && curValue == stack.peek().value){
                stack.peek().times++;
            }else {
                stack.push(new Pair(curValue));
            }
            index = nextIndex(size, index);
        }
        //上述while是遍历过程中结算山峰对

        /**
         *   此时栈中还剩余元素 除了最后一个 和 倒数第二个数需要单独考虑 其他所有数都是对内C K 2对外 2 * k
         *   倒数第二个 需要看最大值的个数 若最大值的个数 >= 2 则为对内C k 2 对外 2 * k  若最大值仅有一个 则为对内C k 2 对外1 * k
         *   最后一个数 也就是全局最大的山峰 C k 2
         */

        while (!stack.isEmpty()){
            int times = stack.pop().times;
            res += getInternalSum(times);//栈中每一个对内都有C k 2这么多对
            //不是倒数第二 和 倒数第一 就都是C k 2 + 2 * k
            if (!stack.isEmpty()){
                //if命中表示是倒数第二 和 倒数第三及以上两种情况
                res += times;
                if (stack.size() > 1){
                    res += times; //倒数第三条及以上的处理 再加一次k
                }else {
                    res += stack.peek().times > 1 ? times : 0; //定制倒数第二条记录
                }
            }
        }

        return res;
    }


    private static int nextIndex(int size, int index){
        return index < size - 1 ? index + 1 : 0;
    }

    /**
     * 连续k个相等的山峰 两俩之间相互可看见 所以为C k 2
     * @param k
     * @return
     */
    private static long getInternalSum(int k){
        return k == 1L ? 0L : (long) k * (long)(k - 1) / 2L;
    }

    private static class Pair{
        int value;
        int times;

        Pair(int value){
            this.value = value;
            this.times = 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int size = scanner.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }
            System.out.println(communications(arr));
        }
        scanner.close();
    }

    /**
     * 如果数组中值都不一样 O(1)
     *
     * 规定找法：小的去找大的  1与3 不统计3与1
     *那么山峰数在3个及以上 我们可以找到最高 和 次高
     * 他们中间i号位置作为小的找大的 往两个方向一遇见刚比他大的就停
     * 最高和次高之间的每一个数都能找出两个山峰对 一个有arr.length个 减去最高和次高
     * 2 * (arr.length - 2)
     * 次高与最高之间还有一对
     * 2 * (arr.length - 2) + 1 => 2 * arr.length - 3
     */
    public static long getNum(int[] arr){
        if (arr == null || arr.length <= 1){
            return 0L;
        }
        return arr.length == 2 ? 1L : (long)((arr.length << 1) - 3);
    }
}
