package deserve.better.life.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/1/23 16:07
 * @description
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class LeetCode77 {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> track = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        backTracking(result, track, n, k, 1);
        return result;
    }

    void backTracking(List<List<Integer>> result, List<Integer> track,
                      int n, int k, int index){
        System.out.println("===========>");
        if (k == track.size()){
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = index; i <= n; i++)
        {
            track.add(i);
            backTracking(result, track, n, k, i + 1);
            track.remove(track.size() - 1);
        }
        System.out.println("<==========");
    }

    public static void main(String[] args) {
        LeetCode77 leetCode77 = new LeetCode77();
        List<List<Integer>> combine = leetCode77.combine(4, 2);
        System.out.println(combine);
    }
}
