package com.shaodw.practice.tree;

/**
 * @Auther: shaodw
 * @Date: 2020/2/1 20:29
 * @Description: 请把一段纸条竖着放在桌子上，然后从纸条的下边向 上方对折1次，压出折痕后展开。
 * 此时 折痕是凹下去的，即折痕 突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折 2 次，压出折痕后展开，此时有三条折痕，
 * 从上到下依次是下折 痕、下折痕和上折痕。 给定一 个输入参数N，代表纸条都从下边向上方连续对折N次， 请从上到下打印所有折痕的方向。
 * 例如：N=1时，打印： down N=2时，打印： down down up
 */
public class PaperFolding {
    /**
     * 1:down
     * 2:down down up
     * 3:down down up down down up up
     * 4:down down up down down up up down down down up up down up up
     * 从折纸不断展开 由于是最中间的那个down来产生剩下所有up和down 那么将其看作满二叉树的根节点 每折一次生成新的一层
     * 并且满足根节点是down 每颗子树的左孩子为down 右孩子为up
     * 从一边开始计数 所以打印的应该是该满二叉树的中序遍历结果
     */

   public static void printAllFolds(int N){
        printProcess(1, N, true);
   }

   private static void printProcess(int i, int N, boolean down){
       if (i > N)
           return;
       printProcess(i + 1, N, true);
       System.out.print(down ? "down " : "up ");
       printProcess(i + 1, N, false);
   }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
