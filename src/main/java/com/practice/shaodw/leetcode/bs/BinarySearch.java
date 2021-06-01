package com.practice.shaodw.leetcode.bs;

/**
 * @author shaodw
 * @date 2021/5/12 13:31
 * @description
 */
public class BinarySearch {
    //review standard write

    //case2: target in arr left bound
    public static int bs_left(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int mid = l + (r - l >> 1);
            if (arr[mid] == target){
                r = mid - 1;
            }else if (arr[mid] < target){
                l = mid + 1;
            }else if (arr[mid] > target){
                r = mid - 1;
            }
        }
        //循环结束 l = r + 1;
        if (l >= arr.length || arr[l] != target){
            return -1;
        }
        return l;
    }

    public static int bs_right(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int mid = l + (r - l >> 1);
            if (arr[mid] == target){
                l = mid + 1;
            }else if (arr[mid] < target){
                l = mid + 1;
            }else if (arr[mid] > target){
                r = mid - 1;
            }
        }
        //循环结束 l = r + 1;
        if (r < 0 || arr[r] != target){
            return -1;
        }
        return r;
    }

    public static int bs_open(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        while (l < r){
            int mid = l + (r - l >> 1);
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                l = mid + 1;
            }else if (arr[mid] > target){
                r = mid;
            } }
        return arr[l] == target ? l : -1;
    }

    public static int bs_close(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int mid = l + (r - l >> 1);
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                l = mid + 1;
            }else if (arr[mid] > target){
                r = mid - 1;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        int[] arr = {1,2,2,2,4,6,7};
        int target = 8;
        System.out.println(bs_left(arr, target));
        System.out.println(bs_right(arr, target));
    }

}
