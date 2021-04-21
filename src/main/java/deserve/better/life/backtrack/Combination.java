package deserve.better.life.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/1/23 15:55
 * @description 从数组[1,2,3,4]找大小为2的组合
 * [
 *  [1,2],[1,3],[1,4],
 *  [2,3],[2,4],
 *  [3,4]
 * ]
 */
public class Combination {

    void combination(int[] arr, int k){
        backTracking(arr, k, 0);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        Combination combination = new Combination();
        combination.combination(arr, 2);
        System.out.println(result);
    }

    //定义全局变量,不放在参数位置
    static List<Integer> path = new LinkedList<>();
    static List<List<Integer>> result = new LinkedList<>();

    //index控制我们从哪个数开始取 因为组合不能重复 只能看后面的数字
//    void backTracking(int[] arr, int k, int index){
//        System.out.println("hello--->");
//        if (k == path.size()){
//            result.add(new LinkedList<>(path));
//            System.out.println("<----bye end");
//            return;
//        }
//        System.out.println("又得跑for循环");
//        for (int i = index; i < arr.length; i++){
//            path.add(arr[i]);
//            backTracking(arr, k, i + 1);
//            path.remove(path.size() - 1); //回溯
//        }
//        System.out.println("<----bye");
//    }


    //剪枝后的代码
    void backTracking(int[] arr, int k, int index){
        System.out.println("hello--->");
        if (k == path.size()){
            result.add(new LinkedList<>(path));
            System.out.println("<----bye end");
            return;
        }
        System.out.println("又得跑for循环");
        //剪枝 主要是这里, 因为 此时已经找到了path.size()个 我们一共要找k个  所以还需要找(k - path.size())个  我们数组一共只有arr.length个  需要保证 arr.length >= k - path.size()
        //目前我们从i位置开始 往后找 最多能找x个 使得x满足: i + x < arr.length   现在还需要找(k - path.size())个 所以: i + x + (k - path.size()) < arr.length
        // x < arr.length - (k - path.size()) - i
        // x <= arr.length - (k - path.size())  ->    x + 1<= arr.length - (k - path.size()) + 1  => 我们的索引i 最终只能到x 所以
        for (int i = index; i < arr.length - (k - path.size()) + 1; i++){
            path.add(arr[i]);
            backTracking(arr, k, i + 1);
            path.remove(path.size() - 1); //回溯
        }
        System.out.println("<----bye");
    }

}
