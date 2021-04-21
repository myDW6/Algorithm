package com.shaodw.leetcode;



/**
 * @author shaodw
 * @date 2021/1/24 19:52
 * @description 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并「原地」修改输入数组。 (不能新建)
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class _27_Remove_Element {

    //其实在数组的处理中 一般不删除元素  直接做覆盖

    //暴力解法: 两个for 遍历每个数字 如果当前数字==val  将后面的依次往前移
    public int removeElement_violence(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val){
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;    // 因为i以后的数值都向前移动了一位，所以i也向前移动一位 这步很关键
                size--;
            }
        }
        return size;
    }


    //快慢指针  通过一个快指针和慢指针在一个for循环下完成两个for循环的工作
    public int removeElement_double_point(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val){
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

    //思路: end标识最后一个元素位置 遍历数组 发现等于val 将该元素end位置元素交换 然后将end-- 交换来的数重复该逻辑 循环跑完, 返回end后一个位置
    public int removeElement_change(int[] nums, int val) {
        int end = nums.length - 1;
        for (int start = 0; start <= end; start++){
            int curVal = nums[start];
            while (curVal == val){
                swap(nums, start, end--);
                if (start > end){
                    break;
                }
                curVal = nums[start];
            }
        }
        return end + 1;
    }

    //其实交换的逻辑可以直接用最后一个值覆盖
    public int removeElement_change_another(int[] nums, int val) {
        int end = nums.length - 1;
        for (int start = 0; start <= end;){
            if (nums[start] == val){
                nums[start] = nums[end--];
            }else {
                start++;
            }
        }
        return end + 1;
    }

    static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,2,3};
        int val = 2;
        //吸取个教训 这里记得拷贝一份初始值
        int[] arr1 = {3,2,2,3};
        int[] arr2 = {3,2,2,3};
        int[] arr3 = {3,2,2,3};
        _27_Remove_Element remove_element = new _27_Remove_Element();
        System.out.println("暴力解法: " +  remove_element.removeElement_violence(arr, val));
        System.out.println("快慢指针解法: " +  remove_element.removeElement_double_point(arr1, val));
        System.out.println("交换解法: " +  remove_element.removeElement_change(arr2, val));
        System.out.println("交换解法另一种思路: " +  remove_element.removeElement_change_another(arr3, val));
    }
}
