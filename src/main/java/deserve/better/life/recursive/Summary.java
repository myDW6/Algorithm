package deserve.better.life.recursive;


/**
 * @author shaodw
 * @date 2021/1/23 13:08
 * @description 总结下对递归的理解
 * 主要是通过打印看这个的顺序
 * 前序遍历的代码在进入某一个节点之前的那个时间点执行，后序遍历代码在离开某个节点之后的那个时间点执行。
 */
public class Summary {

    //数组 数组的递归  先序和后序遍历
    int sum_pre(int[] arr, int i){
        System.out.println("hello");
        if (i == arr.length){
            return 0;
        }
        int me = arr[i];
        int meafter = sum_pre(arr, i + 1);
        int sum = me + meafter;
        System.out.println("bye");
        return sum;
    }

    int sum_post(int[] arr, int i){
        System.out.println("hello");
        if (i == arr.length){
            return 0;
        }
        int meafter = sum_post(arr, i + 1);
        int me = arr[i];
        int sum = me + meafter;
        System.out.println("bye");
        return sum;
    }

    //链表
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val= val;
        }
    }

    static void printPreNode(Node head){
        if (head == null){
            return;
        }
        System.out.println(head.val);
        printPreNode(head.next);
    }

    static void printPostNode(Node head){
        if (head == null){
            return;
        }
        printPostNode(head.next);
        System.out.println(head.val);
    }




}
