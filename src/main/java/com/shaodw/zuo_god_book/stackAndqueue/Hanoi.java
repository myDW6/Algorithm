package com.shaodw.zuo_god_book.stackAndqueue;


import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/3/13 22:20
 * @Description: 现在限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。
 * 求当塔有N层的时候，打印最优移动过程和最优移动总步数。
 * 用以下两种方法解决。● 方法一：递归的方法；● 方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。
 */
public class Hanoi {
    private static int move(String from, String to, String help, int N){
        if (N == 1){
            System.out.println("move " + N + " from " + from + " to " + help);
            System.out.println("move " + N + " from " + help + " to " + to);
            return 2;
        }

        int count = 0;
        count += move(from, to, help, N - 1);
        System.out.println("move " + N + " from " + from + " to " + help);
        count++;
        count += move(to, from, help, N - 1);
        System.out.println("move " + N + " from " + help + " to " + to);
        count++;
        count += move(from, to, help, N - 1);
        return count;
    }

    public static int moveHanoi(int N){
        if (N <= 0){
            return -1;
        }
        return move("left", "right", "middle", N);
    }

    public static void main(String[] args) {
        int N = 2;
        System.out.println("move " + moveHanoi(N) + " steps");
        System.out.println("==============");
        System.out.println(hanoiUseStack(N));
    }


    /**
     * 使用栈来实现
     * 修改后的汉诺塔问题不能让任何塔从“左”直接移动到“右”，也不能从“右”直接移动到“左”，而是要经过中间过程。
     * 也就是说，实际动作只有 4 个：“左”到“中”、“中”到“左”、“中”到“右”、“右”到“中”。
     * 把左、中、右三个地点抽象成栈，依次记为LS、MS和RS。最初所有的塔都在LS上。那么如上4个动作就可以看作是：
     * 某一个栈（from）把栈顶元素弹出，然后压入到另一个栈里（to），作为这一个栈（to）的栈顶。
     * 动作能发生的先决条件是不违反小压大的原则。from栈弹出的元素num如果想压入到to栈中，那么num的值必须小于当前to栈的栈顶
     * 1.我们把4个动作依次定义为：L-＞M、M-＞L、M-＞R和R-＞M。
     * 2.很明显，L-＞M和M-＞L过程互为逆过程，M-＞R和R-＞M互为逆过程。
     * 3.在修改后的汉诺塔游戏中，如果想走出最少步数，那么任何两个相邻的动作都不是互为逆过程的。
     * 举个例子：如果上一步的动作是 L-＞M，那么这一步绝不可能是 M-＞L，直观地解释为：你在上一步把一个栈顶数从“左”移动到“中”，这一步为什么又要移回去呢？这必然不是取得最小步数的走法。同理，M-＞R动作和R-＞M动作也不可能相邻发生。
     *
     * 有了小压大和相邻不可逆原则后，可以推导出两个十分有用的结论--非递归的方法核心结论：
     * 1.游戏的第一个动作一定是L-＞M，这是显而易见的。
     * 2.在走出最少步数过程中的任何时刻，4个动作中只有一个动作不违反小压大和相邻不可逆原则，另外三个动作一定都会违反。
     *
     * 第二个结论很好证明：
     *  假设前一步为L->M 那么当前这一步由于小压大 不能是L->M
     *                              由于相邻不可逆 不能是M->L
     *                          就只剩下 R->M M->R 两者只能存一个  其他三步同理
     */
    private enum Action{
        No,
        LToM,
        MToL,
        RToM,
        MToR
    }

    public static int hanoiUseStack(int N){
        if (N <= 0){
            return -1;
        }
        return hanoi(N, "left" ,"middle", "right");
    }

    public static int hanoi(int N, String left, String middle, String right){
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();

        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);

        for (int i = N; i >= 1; i--) {
            ls.push(i);
        }

        Action[] record = {Action.No};
        int step = 0;
        while (rs.size() != N + 1){
            step += fromStackToStack(record, Action.MToL, Action.LToM, ls, ms, left, middle);
            step += fromStackToStack(record, Action.LToM, Action.MToL, ms, ls, middle, left);
            step += fromStackToStack(record, Action.RToM, Action.MToR, ms, rs, middle, right);
            step += fromStackToStack(record, Action.MToR, Action.RToM, rs, ms, right, middle);
        }
        return step;
    }

    public static int fromStackToStack(Action[] record, Action preAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to){
        if (record[0] != preAct && fStack.peek() < tStack.peek()){
            tStack.push(fStack.pop());
            System.out.println("move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

}
