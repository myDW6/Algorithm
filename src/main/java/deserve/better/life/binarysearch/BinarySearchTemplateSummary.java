package deserve.better.life.binarysearch;


/**
 * @author shaodw
 * @date 2021/1/11 22:28
 * @description
 */
public class BinarySearchTemplateSummary {


    static int binarySearch(int[] arr, int target){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int left = 0, right = arr.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }




    static int left_bound(int[] arr, int target){
        if (arr.length == 0 )
            return -1;
        int left = 0, right = arr.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else if (arr[mid] == target){
                right = mid - 1;
            }
        }
        if (left >= arr.length || arr[left] != target){
            return -1;
        }
        return left;
    }

    static int right_bound(int[] arr, int target){
        if (arr.length == 0 )
            return -1;
        int left = 0, right = arr.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else if (arr[mid] == target){
                left = mid + 1;
            }
        }
        if (right < 0 || arr[right] != target){
            return -1;
        }
        return right;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,2,2,2,3};
        System.out.println(left_bound(arr, 2));
        System.out.println(right_bound(arr, 2));
    }



}
