package com.shaodw.practice.binarysearch;

import java.util.HashMap;

/**
 * @Auther: shaodw
 * @Date: 2020/3/17 09:06
 * @Description: 数字在排序数组中出现的次数 {1,2, 3,3,3,3,4,5} 3次数为4
 */
public class TimesInArray {
    //O(N) + O(N)
    public static int timesInSortedArrayUseMap(int[] arr, int k){
        if (arr == null || arr.length < 1 || k < arr[0] || k > arr[arr.length - 1]){
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.containsKey(arr[i]) ? map.get(arr[i]) + 1 : 1);
        }
        return map.containsKey(k) ? map.get(k) : 0;
    }

    //O(logN) ---- O(N)
    public static int timesInSortedArrayUseBinarySearch(int[] arr, int k){
        if (arr == null || arr.length < 1 || k < arr[0] || k > arr[arr.length - 1]){
            return 0;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l < r){
            mid = l + ((r - l) >> 1);
            if (k < arr[mid]){
                r = mid;
            }else if (k > arr[mid]){
                l = mid + 1;
            }else {
                break;
            }
        }

        if (l == r){
            return 0;
        }
        l = mid - 1;
        r = mid + 1;
        while (l >= 0 && arr[l] == k){
            l--;
        }
        while (r < arr.length && arr[r] == k){
            r++;
        }
        return r - l - 1;//r - l + 1 - 2
    }


    //O(logN) + O(logN) => O(logN)
    public static int timesInSortedArrayBest(int[] arr, int k){
        if (arr == null || arr.length < 1 || k < arr[0] || k > arr[arr.length - 1]){
            return 0;
        }
        int res = 0;
        int first = getFirstK(arr, k, 0, arr.length - 1);
        int last = getLastK(arr, k, 0, arr.length - 1);
        if (first > -1 && last > -1){
            res = last - first + 1;
        }
        return res;
}

    private static int getFirstK(int[] arr, int k, int l, int r){
        if (l > r){
            return -1;
        }
        int mid = l + ((r - l) >> 1);
        if (arr[mid] == k){
            if ((mid > 0 && arr[mid - 1] != k || mid == 0)){
                return mid;
            }else r = mid - 1;
        } else if (arr[mid] > k) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
        return getFirstK(arr, k, l, r);
    }

    private static int getLastK(int[] arr, int k, int l, int r){
        if (l > r){
            return -1;
        }
        int mid = l + ((r - l) >> 1);
        if (arr[mid] == k){
            if (mid < arr.length - 1 && arr[mid + 1] != k || k == arr.length - 1){
                return mid;
            }else {
                l = mid + 1;
            }
        }else if (arr[mid] > k){
            r = mid - 1;
        }else {
            l = mid + 1;
        }
        return getLastK(arr, k, l , r);
    }

    public static void main(String[] args) {
        int[] arr = {1,3,3,3,3,3,4,5};
        int k = 3;

        System.out.println(timesInSortedArrayUseMap(arr, k));
        System.out.println(timesInSortedArrayUseBinarySearch(arr, k));
        System.out.println(timesInSortedArrayBest(arr,k));

        k =1;
        System.out.println(timesInSortedArrayUseMap(arr, k));
        System.out.println(timesInSortedArrayUseBinarySearch(arr, k));
        System.out.println(timesInSortedArrayBest(arr,k));

        k = 1;
        System.out.println(timesInSortedArrayUseMap(arr, k));
        System.out.println(timesInSortedArrayUseBinarySearch(arr, k));
        System.out.println(timesInSortedArrayBest(arr,k));

        k = -1;
        System.out.println(timesInSortedArrayUseMap(arr, k));
        System.out.println(timesInSortedArrayUseBinarySearch(arr, k));
        System.out.println(timesInSortedArrayBest(arr,k));

        k = 6;
        System.out.println(timesInSortedArrayUseMap(arr, k));
        System.out.println(timesInSortedArrayUseBinarySearch(arr, k));
        System.out.println(timesInSortedArrayBest(arr,k));

        k = 2;
        System.out.println(timesInSortedArrayUseMap(arr, k));
        System.out.println(timesInSortedArrayUseBinarySearch(arr, k));
        System.out.println(timesInSortedArrayBest(arr,k));
    }
}
