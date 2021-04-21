package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2019-12-10 15:17
 * @Description:在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 思路：从右上角开始查找 若当前值小于目标值，向下寻找，大于向左寻找，相等返回，越界后不存在。
 */
public class MartrixFind {
    public boolean Find(int target, int [][] array) {
        if (array == null )
            return false;
        int i = 0;
        int j = array[0].length - 1;
        while (i < array.length && j >= 0){
            if (array[i][j] == target)
                return true;
            else if (array[i][j] < target){
                i++;
            }else j--;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };

        System.out.println(new MartrixFind().Find(7,arr));
    }
}
