package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/27 10:05
 * @Description: 排成一条线的纸牌博弈问题
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走 每张纸牌，
 * 规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右 的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 *
 * arr=[1,2,100,4]。 开始时玩家A只能拿走1或4。如果玩家A拿走1，则排列变为[2,100,4]，
 * 接下来玩 家B可以拿走2或4，然后继续轮到玩家A。如果开始时玩家A拿走4，则排列变为 [1,2,100]，
 * 接下来玩家B可以拿走1或100，然后继续轮到玩家A。玩家A作为绝顶 聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。
 * 所以玩家A会先拿1，让排 列变为[2,100,4]，接下来玩家B不管怎么选，100都会被玩家A拿走。玩家A会获胜， 分数为101。所以返回101。
 * arr=[1,100,2]。 开始时玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会 获胜，分数为100。所以返回100。
 */
public class CardsInLine {

    public static int win1(int[] arr){
        return (arr == null || arr.length == 0) ? 0 : Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 绝顶聪明的人先选
     */
    private static int f(int[] arr, int i, int j){
        return i == j ? arr[i] : Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }


    /**
     * 绝顶聪明的人后选
     */
    private static int s(int[] arr, int i, int j){
        return i == j ? 0 : Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    /**
     * f对角线为arr[i] s对角线为0 两张表左下角区域不用看
     * @param arr
     * @return
     */
    public static int windp(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];

        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,100,4};
        System.out.println(win1(arr));
        System.out.println(windp(arr));
        int[] arr1 = {1,100,2};
        System.out.println(win1(arr1));
        System.out.println(windp(arr1));
    }
}
