package deserve.better.life.binarysearch;


/**
 * @author shaodw
 * @date 2021/1/10 22:26
 * @description 二分查找
 */
public class BinarySearch {

    //以下为模板写法   不要出现 else，而是把所有情况用 else if 写清楚，这样可以清楚地展现所有细节
    //其中 ... 标记的部分，就是可能出现细节问题的地方，当你见到一个二分查找的代码时，首先注意这几个地方
//    int binarySearch(int[] arr, int target){
//        int left = 0;
//        int right = ...;
//
//        while (...){
//            int mid = left + (right - left) / 2;
//            if (arr[mid] == target){
//                ...
//            }else if (arr[mid] < target){
//                left = ...
//            }else if (arr[mid] > target){
//                right = ...
//            }
//        }
//        return ...
//    }





    /**
     * 寻找左侧边界的二分搜索
     */
    static int left_bound(int[] arr, int target){
        if (arr.length == 0 )
            return -1;
        int left = 0;
        int right = arr.length; //注意
        while (left < right){ //注意
            //因为 right = nums.length 而不是 nums.length - 1。因此每次循环的「搜索区间」是 [left, right) 左闭右开。
            //while(left < right) 终止的条件是 left == right，此时搜索区间 [left, left) 为空，所以可以正确终止
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target){
                right = mid; //找到 target 时不要立即返回，而是缩小「搜索区间」的上界 right，在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左侧边界的目的。
            }else if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid; //注意
            }
            //[left, right) 左闭右开   当 nums[mid] 被检测之后  下一步的搜索区间应该去掉 mid 分割成两个区间  [left,mid) [mid+1, right)
        }
        //while退出时 right = left
        if (left == arr.length){
            //target比所有值大
            return -1;
        }
        return arr[left] == target ? left : -1;
    }



    /**
     * 寻找右侧边界的二分查找
     */
    static int right_bound(int[] arr, int target){
        if (arr.length == 0)
            return -1;
        int left = 0, right = arr.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid;
            }else if (arr[mid] == target){
                left = mid + 1;
            }
        }
//        这里既然是搜索右侧边界，应该返回 right 才对。
//        但是事实上这种写法 退出循环时 left = right所以一样  为什么减一 因为tagert找到时 left = mid + 1;
//        因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 arr[left-1] 可能是 target。
        if (left == 0) return -1;
        return arr[left-1] == target ? (left-1) : -1;
    }


    /**
     * 1. 寻找一个数
     * 最熟悉的，即搜索一个数，如果存在，返回其索引，否则返回 -1
     */
    static int binarySearch(int[] arr, int target){
        int left = 0, right = arr.length - 1;  //注意 初始化 right 的赋值是 nums.length - 1，即最后一个元素的索引，而不是 nums.length
        //这二者可能出现在不同功能的二分查找中，区别是：前者相当于两端都闭区间 [left, right]，后者相当于左闭右开区间 [left, right)，因为索引大小为 nums.length 是越界的。
        //我们这个算法中使用的是前者 [left, right] 两端都闭的区间。这个区间其实就是每次进行搜索的区间。
        while (left <= right){ //注意是<=
            //while(left <= right) 的终止条件是 left == right + 1，写成区间的形式就是 [right + 1, right]，或者带个具体的数字进去 [3, 2]，可见这时候区间为空，因为没有数字既大于等于 3 又小于等于 2 的吧。所以这时候 while 循环终止是正确的，直接返回 -1 即可
            //while(left < right) 的终止条件是 left == right，写成区间的形式就是 [right, right]，或者带个具体的数字进去 [2, 2]，这时候区间非空，还有一个数 2，但此时 while 循环终止了。也就是说这区间 [2, 2] 被漏掉了，索引 2 没有被搜索，如果这时候直接返回 -1 就是错误的。
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                left = mid + 1;  //注意
            }else if (arr[mid] > target){
                right = mid - 1; //注意
            }
        }
        return -1;
    }

    /**
     *闭区间统一写法
     */
    int left_bound_close(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (arr[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (arr[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        // 检查出界情况
        if (left >= arr.length || arr[left] != target)
            return -1;
        return left;
    }

    /**
     * 闭区间统一写法
     */
    static int right_bound_close(int[] arr, int target){
        int left = 0, right = arr.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else if (arr[mid] == target){
                left = mid + 1;
            }
        }
        if (right < 0 || arr[right] != target)
            return -1;
        return right;
    }

}
