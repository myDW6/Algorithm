package shaodw;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author shaodw
 * @date 2021/6/21 21:39
 * @description
 */
public class Test {


    static void postreversal(Node root){
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                System.out.println(root.val);
                if (root.left != null){
                    queue.add(root.left);
                }
                if (root.right != null){
                    queue.add(root.right);
                }
            }
            System.out.println("-----");
        }
    }


    public static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        root.right.left.left = new Node(1);
        root.right.left.right = new Node(8);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(6);
        root.right.right.right.right = new Node(9);
        // 1 2 3 4 5 6 7

        postreversal(root);

    }


}
