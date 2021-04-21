package deserve.better.life.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/1/23 13:28
 * @description 解决一个回溯问题，实际上就是一个决策树的遍历过程
 * 只需要思考 3 个问题：
 * 1、路径：也就是已经做出的选择。
 * 2、选择列表：也就是你当前可以做的选择。
 * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
 */
public class BackTrackingIdea {

    //其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」，特别简单。
//
//    res[];
//    void backtrack(路径, 选择列表){
//        if (满足结束条件){
//            result.add(路径);
//            return;
//        }
//        for 选择 in 选择列表{
//            做选择;
//            backtrack(路径, 选择列表)
//            撤销选择;
//        }
//    }


    //   核心
//    for 选择 in 选择列表:
//    # 做选择
//    将该选择从选择列表移除
//    路径.add(选择)
//    backtrack(路径, 选择列表)
//    # 撤销选择
//    路径.remove(选择)
//    将该选择再加入选择列表

//    我们只要在递归之前做出选择，在递归之后撤销刚才的选择，就能正确得到每个节点的选择列表和路径。

    //全排列  不包含重复的数字
    List<List<Integer>> res = new LinkedList<>();

    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

// 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素
// 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        BackTrackingIdea backTrackingIdea = new BackTrackingIdea();
        int[] arr = {1,2,3};
        List<List<Integer>> permute = backTrackingIdea.permute(arr);
        System.out.println(permute);
    }




}
