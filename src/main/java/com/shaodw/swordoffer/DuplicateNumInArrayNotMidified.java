package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020-01-15 13:39
 * @Description: 在一个长度为n + 1的数组中 所有数字都在1-n 因此至少有一个数字重复，找出任意一个重复的数字。不能修改数组
 * 这题和上题很像 但是要求不能破环原数组 我们可以申请一个辅助数组 把原数组中的数依次拷贝 如果原数组值为k 拷贝到下标为k的位置 空间复杂度O(N)
 */
public class DuplicateNumInArrayNotMidified {


    /**
     * 统计start-mid范围内的数 如果超过了需要的数的一半  则在另一半区间查找
     * 使用这种思路 长度为n的数组 调用logn次countRange函数  每次O(N)代价 时间复杂度O(NlogN) 空间复杂度O(1) 时间换空间
     * @param arr
     * @return
     */
    public static int duplicate(int[] arr){
        if (arr == null || arr.length < 1)
            return -1;
        int start = 1;//1 - N-1     1 - mid
        int end = arr.length - 1;
        while (end >= start){
            int mid = start + ((end - start) >> 1);
            int count = countRange(arr, start, mid);
            if (end == start){
                if (count > 1){
                    return start;
                }else break;
            }

            if (count > mid - start + 1){
                end = mid;
            }else start = mid + 1;
        }
        return -1;
    }

    //数组中落在区间start到right的数字个数
    private static int countRange(int[] arr, int start, int right){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= right){
                count++;
            }
        }
        return count;
    }

    public static int duplicateUseTmp(int[] arr){
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (copy[arr[i]] == 0){
                copy[arr[i]] = arr[i];
            }else return arr[i];
        }
        return -1;
    }



    public static void main(String[] args) {
        int[] arr = {2,3,5,4,3,2,6,7};
        System.out.println(duplicate(arr));
        System.out.println(duplicateUseTmp(arr));
    }
}
